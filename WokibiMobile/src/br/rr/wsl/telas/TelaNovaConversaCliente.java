package br.rr.wsl.telas;

import java.util.ArrayList;

import br.rr.wsl.R;
import br.rr.wsl.controle.clienteServidor.Cliente;
import br.rr.wsl.controle.utilitarios.ConstantesDiversas;
import br.rr.wsl.controle.utilitarios.ConstantesTelas;
import br.rr.wsl.controle.utilitarios.Instancias;
import br.rr.wsl.entidades.mensagem.bean.MensagemBean;
import br.rr.wsl.entidades.mensagem.facade.MensagemFacade;
import br.rr.wsl.entidades.mensagem.facade.MensagemFacadeImpl;
import br.rr.wsl.util.FacadeException;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class TelaNovaConversaCliente extends Activity implements OnClickListener{
	
	//private static final String CATEGORIA = "wsl";
	private MensagemFacade mensagemFacade;
	
	private ImageButton bt_voltar;
	private ImageButton bt_confirmar;
	
	private EditText et_ip;
	
	private Handler handler = new HandleNovaConversa();
	private ProgressDialog dialog;
	
	private Cliente cliente;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_novaconversa_cliente);
        
        mensagemFacade = new MensagemFacadeImpl(this);
        
        bt_voltar = (ImageButton) findViewById(R.id.telaNovaConveraCliente_bt_voltar);
        bt_voltar.setOnClickListener(this);
        bt_confirmar = (ImageButton) findViewById(R.id.telaNovaConveraCliente_bt_confirmar);
        bt_confirmar.setOnClickListener(this);
        
        et_ip = (EditText) findViewById(R.id.telaNovaConveraCliente_et_ip);
        
        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
        	Integer codigo = bundle.getInt(ConstantesDiversas.BD_CONECTAR_CONVERSA);
        	verificarIpConversa(codigo);
        }
    }
	
	/* 
	 * EVENTOS
	 * */
	
	@Override
	public void onClick(View v) {
		if(v == bt_voltar) {
			finish();
		} else if(v == bt_confirmar) {
			conectar(et_ip.getText().toString());
		} 
	}
	
	/* 
	 * AÇOES ESPECIFICAS
	 * */
	
	private void verificarIpConversa(Integer codigoConversa) {
    	for(Object obj : listarMensagensPorConversa(codigoConversa)) {
    		MensagemBean mensagem = (MensagemBean) obj;
    		if(mensagem.getContato().getAutor() == 1) {
    			String ip = mensagem.getContato().getIp();
    			if(ip != null) {
	    			et_ip.setText(ip);
	    			conectar(ip);
    			}
    			break;
    		}
    	} 		
	}

	private void conectar(String ip) {
		Resources resources = getResources();
		dialog = ProgressDialog.show(this, resources.getString(R.string.geral_rede), 
				resources.getString(R.string.telaNovaConversaCliente_msg_conectar), true, true);
		cliente = new Cliente(ip, 0, Instancias.getDono().getNome());
		cliente.getTratarMensagem().setHandler(handler);
		Thread thread = new Thread(cliente);
		thread.start();
	}
	
	private void confirmar(Integer codigoConversa) {
		Resources resources = getResources();
		Toast.makeText(this, resources.getString(R.string.telaNovaConversaCliente_msg_conversa), Toast.LENGTH_SHORT).show();
		Intent intent = new Intent();
		intent.putExtra(ConstantesDiversas.BD_CODIGO_CONVERSA, codigoConversa);
		setResult(ConstantesTelas.NOVA_CONVERSA, intent);
		finish();
	}
	
	/*
	 * BANCO DE DADOS
	 * */
	
	private ArrayList<Object> listarMensagensPorConversa(Integer codigo) {
		try {
			return mensagemFacade.listarMensagensPorConversa(codigo);
		} catch (FacadeException e) {
		}
		return null;
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
    
    private class HandleNovaConversa extends Handler {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
				case ConstantesDiversas.HD_NOVA_CONVERSA:
					dialog.dismiss();
					int codigoConversa = msg.getData().getInt(ConstantesDiversas.BD_CODIGO_CONVERSA);
					if(codigoConversa != 0) {
						confirmar(codigoConversa);
					} else {
						Resources resources = getResources();
						Toast.makeText(TelaNovaConversaCliente.this, resources.getString(R.string.geral_servidorNaoEncontrado), Toast.LENGTH_SHORT).show();
					}
					break;
				default:
					break;
			}
		}
	}
    
}
