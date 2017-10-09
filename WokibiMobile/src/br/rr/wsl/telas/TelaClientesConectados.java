package br.rr.wsl.telas;

import java.util.ArrayList;

import br.rr.wsl.R;
import br.rr.wsl.controle.clienteServidor.Cliente;
import br.rr.wsl.controle.utilitarios.ConstantesDiversas;
import br.rr.wsl.controle.utilitarios.ConstantesTelas;
import br.rr.wsl.controle.utilitarios.Instancias;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

public class TelaClientesConectados extends Activity implements OnClickListener, OnItemClickListener {
	
	//private static final String CATEGORIA = "wsl";
	
	private ImageButton bt_voltar;
	
	private ListView lv_clientesConectados;
	
	private ProgressDialog dialog;
	private Handler handler = new HandleClientesConectados();
	
	private Cliente servidor;
	private String clienteSelecionado;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_clientes_conectados);
           
        bt_voltar = (ImageButton) findViewById(R.id.telaClientesConectados_bt_voltar);
        bt_voltar.setOnClickListener(this);
        
        lv_clientesConectados = (ListView) findViewById(R.id.telaClientesConectados_lv_clientesConectados);
        lv_clientesConectados.setOnItemClickListener(this);   
        
        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
        	Integer codigo = bundle.getInt(ConstantesDiversas.BD_SERVIDOR_CONECTADO);
        	servidor = Instancias.getConversasServidor().get(codigo);
        }
        
        buscarClientes();
    }
	
	/* 
	 * EVENTOS
	 * */
	
	@Override
	public void onClick(View v) {
		if(v == bt_voltar) {
			finish();
		} 
	}
	
	@Override
	public void onItemClick(AdapterView<?> pai, View view, int posicao, long id) {
		if(pai == lv_clientesConectados) {
			clienteSelecionado = (String) lv_clientesConectados.getAdapter().getItem(posicao); 
			showDialog(ConstantesTelas.DIALOG_LISTA);
		} 
	}
	
	/* 
	 * AÇOES ESPECIFICAS
	 * */
	
	private void buscarClientes() {
		Resources resources = getResources();
		dialog = ProgressDialog.show(this, "Rede", resources.getString(R.string.telaClientesConectados_buscar), true, true);
		new Thread(new Runnable() {      
			@Override
			public void run() {
				servidor.getTratarMensagem().setHandler(handler);
				servidor.enviarDados(ConstantesDiversas.CS_CLIENTE_CONECTADOS);
			}
		}).start();	
	}
	
	private void preencherLista(ArrayList<String> clientes) {
		lv_clientesConectados.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, clientes));
	}
	
	private void iniciarConversa() {
		Resources resources = getResources();
		dialog = ProgressDialog.show(this, "Rede", resources.getString(R.string.telaClientesConectados_iniciarConversa), true, true);
		new Thread(new Runnable() {      
			@Override
			public void run() {
				servidor.getTratarMensagem().setHandler(handler);
				servidor.enviarDados(ConstantesDiversas.CS_CONVERSA_PRIVADA);
				servidor.enviarDados(clienteSelecionado+"-"+Instancias.getDono().getNome());  //Descrição conversa
				servidor.enviarDados(clienteSelecionado);
			}
		}).start();	
	}
	
	private void seguirParaTelaAssociarConversa() {
		Intent intent = new Intent(this, TelaAssociarConversa.class);
		intent.putExtra(ConstantesDiversas.BD_ASSOCIAR_CONVERSA_CLIENTE, clienteSelecionado);
		startActivity(intent);
	}
	
	private void confirmar(Integer codigoConversa) {
		Resources resources = getResources();
		Toast.makeText(this, resources.getString(R.string.telaClientesConectados_conversaCriada), Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(this, TelaMensagens.class);
		intent.putExtra(ConstantesDiversas.BD_CODIGO_CONVERSA, codigoConversa);
		startActivity(intent);
		finish();
	}
	
	/* 
	 * CICLO DE VIDA
	 * */
    
    @Override
    protected void onStart() {
    	super.onStart();
    }
    
    @Override
    protected void onRestart() {
    	super.onRestart();
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    }
    
    @Override
    protected void onPause() {
    	super.onPause();
    }
    
    @Override
    protected void onStop() {
    	super.onStop();
    }
    
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    }
    
    /* 
	 * ACTIVITY
	 * */
    
    private class HandleClientesConectados extends Handler {
    	@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
				case ConstantesDiversas.HD_RETORNA_CLIENTES:
					dialog.dismiss();
					ArrayList<String> clientes = msg.getData().getStringArrayList(ConstantesDiversas.BD_CODIGO_CONVERSA);
					preencherLista(clientes);
					break;
				case ConstantesDiversas.HD_CONVERSA_PRIVADA:
					dialog.dismiss();
					int codigoConversa = msg.getData().getInt(ConstantesDiversas.BD_CODIGO_CONVERSA);
					if(codigoConversa != 0) {
						confirmar(codigoConversa);
					} else {
						Resources resources = getResources();
						Toast.makeText(TelaClientesConectados.this, resources.getString(R.string.geral_servidorNaoEncontrado), Toast.LENGTH_SHORT).show();
					}
					break;
				default:
					break;
			}
		}
    }
    
    @Override
    protected Dialog onCreateDialog(int id) {
		switch (id) {
        	case ConstantesTelas.DIALOG_LISTA: 
        		return new AlertDialog.Builder(this)
                .setTitle(R.string.dialog_tituloDialogList)
                .setItems(R.array.opcao_listaClientesServidor, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    	if(which == 0) { //iniciar conversa
                    		iniciarConversa();
                    	} else if(which == 1) { //associar conversa
                    		seguirParaTelaAssociarConversa();
                    	}
                    }
                }).create();
		}
		return null;
	}

}
