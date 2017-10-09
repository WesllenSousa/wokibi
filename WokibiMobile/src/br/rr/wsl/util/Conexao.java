package br.rr.wsl.util;

import br.rr.wsl.controle.utilitarios.ConstantesDiversas;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class Conexao {
	
	public static SQLiteDatabase getConexao(Context context) {
		return context.openOrCreateDatabase(ConstantesDiversas.NOME_BANCO, Context.MODE_PRIVATE, null);
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

DROP TABLE IF EXISTS conversas_contatos;

CREATE TABLE "conversas_contatos" (
	"conversa" integer NOT NULL,
	"contato" integer NOT NULL,
  FOREIGN KEY ("conversa") REFERENCES "conversas"("_id"),  
  FOREIGN KEY ("contato") REFERENCES "contatos"("_id")
);

 */
