package br.rr.wsl.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import br.rr.wsl.controle.utilitarios.ConstantesDiversas;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ExecutarScript extends SQLiteOpenHelper {
	
	private static final String CATEGORIA = "wsl";
	
	private Context context;
	private SQLiteDatabase bancoDados; 

	public ExecutarScript(Context context) {
		super(context, ConstantesDiversas.NOME_BANCO, null, ConstantesDiversas.VERSAO);
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}
	
	public void criarBancoDados() {
		Boolean existirBanco = checkDataBase();
		if(existirBanco){
			Log.i(CATEGORIA, "Banco existente!");
    	} else {
    		Log.i(CATEGORIA, "Criando banco!");
        	bancoDados = this.getWritableDatabase();
        	copiarBancoDados();
    	}
	}
	
	private Boolean checkDataBase(){
    	SQLiteDatabase checkDB = null;
    	try{
    		String myPath = ConstantesDiversas.PATH_BANCO + ConstantesDiversas.NOME_BANCO;
    		checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    	} catch(SQLiteException e) {
    		Log.e(CATEGORIA, "Banco não existe!");
    	}
    	if(checkDB != null){
    		checkDB.close();
    	}
    	return checkDB != null ? true : false;
    }
	
	private void copiarBancoDados() {	
    	String outFileName = ConstantesDiversas.PATH_BANCO + ConstantesDiversas.NOME_BANCO;
		try {
			InputStream input = context.getAssets().open(ConstantesDiversas.NOME_BANCO);
			OutputStream output = new FileOutputStream(outFileName);
			byte[] buffer = new byte[1024];
	    	int length;
	    	while ((length = input.read(buffer))>0){
	    		output.write(buffer, 0, length);
			}
	    	output.flush();
	    	output.close();
	    	input.close();
		} catch (IOException e) {
			Log.e(CATEGORIA, e.getMessage()+"");
		}
    }
	
	public void abrirBanco() throws SQLException{
		Log.i(CATEGORIA, "Abrindo banco!");
        String path = ConstantesDiversas.PATH_BANCO + ConstantesDiversas.NOME_BANCO;
        bancoDados = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
    }
	
	@Override
	public synchronized void close() {
	    if(bancoDados != null)
	    	bancoDados.close();
	    super.close();
	}

}

/*
 
DROP TABLE IF EXISTS android_metadata;

CREATE TABLE "android_metadata" ("locale" TEXT DEFAULT 'en_US');
INSERT INTO "android_metadata" VALUES ('en_US');

DROP TABLE IF EXISTS contatos;

CREATE TABLE "contatos" (
	"_id" integer primary key autoincrement,
	"nome" text,
	"ddd" integer,
	"celular" integer,
	"email" text,
	"autor" integer,
	"deletar" integer,
	"status" integer,
  "ip" text,  
  "porta" integer
);

DROP TABLE IF EXISTS conversas;

CREATE TABLE "conversas" (
	"_id" integer primary key autoincrement,
	"datahora" text,
	"descricao" text,
	"clienteservidor" integer,
	"deletar" integer,
	"status" integer
);

DROP TABLE IF EXISTS mensagens;

CREATE TABLE "mensagens" (
	"_id" integer primary key autoincrement,
	"conversa" integer,
	"contato" integer,
	"datahora" text,
	"texto" text,
	"deletar" integer,
  FOREIGN KEY ("conversa") REFERENCES "conversas"("_id"), 
  FOREIGN KEY ("contato") REFERENCES "contatos"("_id")
);

 */
