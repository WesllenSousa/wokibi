package br.rr.wsl.telas;

import java.util.ArrayList;

import br.rr.wsl.R;
import br.rr.wsl.controle.adapter.MensagensAdapter;
import br.rr.wsl.controle.clienteServidor.Cliente;
import br.rr.wsl.controle.utilitarios.ConstantesDiversas;
import br.rr.wsl.controle.utilitarios.ConstantesTelas;
import br.rr.wsl.controle.utilitarios.Instancias;
import br.rr.wsl.entidades.conversa.bean.ConversaBean;
import br.rr.wsl.entidades.conversa.facade.ConversaFacade;
import br.rr.wsl.entidades.conversa.facade.ConversaFacadeImpl;
import br.rr.wsl.entidades.mensagem.bean.MensagemBean;
import br.rr.wsl.entidades.mensagem.facade.MensagemFacade;
import br.rr.wsl.entidades.mensagem.facade.MensagemFacadeImpl;
import br.rr.wsl.util.FacadeException;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

public class TelaMensagens extends Activity implements OnClickListener, OnItemLongClickListener {
	
	//private static final String CATEGORIA = "wsl";
	
	private ConversaFacade conversaFacade;
	private MensagemFacade mensagemFacade;
	private Handler handler = new HandleMensagem();
	
	private ImageButton bt_voltar;
	private ImageButton bt_sair;
	private ImageButton bt_adicionarUsuario;
	private ImageButton bt_desocultar;
	
	private ListView lv_mensagens;
	private EditText et_mensagem;
	private ImageButton bt_enviar;
	
	private ConversaBean conversa;
	private Cliente cliente;
	private MensagemBean mensagemSelecionada;
	private Boolean oculto;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_mensagens);
        
        mensagemFacade = new MensagemFacadeImpl(this);
        conversaFacade = new ConversaFacadeImpl(this);
        
        bt_voltar = (ImageButton) findViewById(R.id.telaMensagens_bt_voltar);
        bt_voltar.setOnClickListener(this);
        bt_sair = (ImageButton) findViewById(R.id.telaMensagens_bt_sair);
        bt_sair.setOnClickListener(this);
        bt_enviar = (ImageButton) findViewById(R.id.telaMensagens_bt_enviar);
        bt_enviar.setOnClickListener(this);
        bt_adicionarUsuario = (ImageButton) findViewById(R.id.telaMensagens_bt_adicionarUsuario);
        bt_adicionarUsuario.setOnClickListener(this);
        bt_desocultar = (ImageButton) findViewById(R.id.telaMensagens_bt_desocultar);
        bt_desocultar.setOnClickListener(this);
        
        lv_mensagens = (ListView) findViewById(R.id.telaMensagens_lv_mensagens);
        lv_mensagens.setOnItemLongClickListener(this);
        
        et_mensagem = (EditText) findViewById(R.id.telaMensagens_et_mensagem);
        
        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
        	Integer codigo = bundle.getInt(ConstantesDiversas.BD_CODIGO_CONVERSA);
        	ConversaBean c = new ConversaBean();
        	c.setCodigo(codigo);
        	conversa = (ConversaBean) selecionar(c);
        	if(conversa != null) {
        		preencherMensagens();
        		if(conversa.getStatus() == 1) {
        			verificarBotoes();
        		}
        	}
        }
        
        recuperaCliente();
        verificaNotificacao();
        registrarAbrirTela();
    }
	
	/* 
	 * EVENTOS
	 * */
	
	@Override
	public boolean onItemLongClick(AdapterView<?> pai, View view, int posicao, long id) {
		if(pai == lv_mensagens) {
			mensagemSelecionada = (MensagemBean) lv_mensagens.getAdapter().getItem(posicao);
		}
		showDialog(ConstantesTelas.DIALOG_LISTA);
		return false;
	}

	@Override
	public void onClick(View v) {
		if(v == bt_voltar) {
			Intent intent = new Intent();
			intent.putExtra(ConstantesDiversas.BD_NOTIFICAR_CONVERSA, 1);
			setResult(ConstantesTelas.NOTIFICAR_CONVERSA, intent);
			finish();
		} else if(v == bt_sair) {
			showDialog(ConstantesTelas.DIALOG_SIM_NAO);
		} else if(v == bt_adicionarUsuario) {
			associarUsuario();
		} else if(v == bt_enviar) {
			enviar();
		} else if(v == bt_desocultar) {
			if(oculto) {
				desocultar();
			} else {
				preencherMensagens();
			}
		} 
	}
	
	/* 
	 * AÇOES ESPECIFICAS
	 * */
	
	private void verificarBotoes() {
		et_mensagem.setEnabled(false);
		bt_enviar.setEnabled(false);
		bt_enviar.setImageResource(R.drawable.enviar_desativado_50);
		bt_sair.setImageResource(R.drawable.saird_50);
		bt_sair.setEnabled(false);  
		bt_adicionarUsuario.setEnabled(false);
		bt_adicionarUsuario.setImageResource(R.drawable.adicionar_contato_desativado_50);
	}
	
	private void preencherMensagens() {
		oculto = true;
    	ArrayList<Object> mensagens = listarMensagensPorConversa(conversa.getCodigo());
    	if(mensagens != null && !mensagens.isEmpty()) {
    		MensagensAdapter adapter = new MensagensAdapter(this, mensagens);
        	lv_mensagens.setAdapter(adapter);
        	lv_mensagens.invalidate();
    	} 
    	bt_desocultar.setImageResource(R.drawable.desocultar_50);
	}
	
	private void recuperaCliente() {
		if(Instancias.getConversasCliente() != null && Instancias.getConversasCliente().containsKey(conversa.getCodigo())) {
			cliente = Instancias.getConversasCliente().get(conversa.getCodigo());
    	} else if(Instancias.getConversasServidor() != null && Instancias.getConversasServidor().containsKey(conversa.getCodigo())) {
    		cliente = Instancias.getConversasServidor().get(conversa.getCodigo());
    	}
		if(cliente != null) cliente.getTratarMensagem().setHandler(handler);
	}
	
	private void enviar() {
		if(!et_mensagem.getText().toString().equals("")) {
			new Thread(new Runnable() {      
				@Override
				public void run() {
					if(cliente != null) {
						cliente.enviarDados(ConstantesDiversas.CS_MENSAGEM);
						cliente.enviarDados(et_mensagem.getText().toString());
						cliente.enviarDados(Instancias.getDono().getNome());
					} 
				}
			}).start();	
			if(cliente != null) {
				Resources resources = getResources();
				Toast.makeText(this, resources.getString(R.string.telaMensagem_msg_enviar), Toast.LENGTH_SHORT).show();
			}
		}
	}
	
	public void receber(String mensagem) {	
		et_mensagem.setText("");
		preencherMensagens();
	}
	
	private void associarUsuario() {
		Intent intent = new Intent(this, TelaAssociarContato.class);
		intent.putExtra(ConstantesDiversas.BD_CODIGO_CONVERSA, conversa.getCodigo());
		startActivity(intent);
	}
	
	private void desconectar() {
		Intent intent = new Intent();
		intent.putExtra(ConstantesDiversas.BD_NOTIFICAR_CONVERSA, 0);
		setResult(ConstantesTelas.NOTIFICAR_CONVERSA, intent);
		finish();
	}
	
	private void verificaNotificacao() {
		//Recupera o servico de notificacao
      	NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
      	//cancela a notificacao
      	if(notificationManager != null) notificationManager.cancel(R.string.nome_aplicacao);
	}
	
	private void deletar() {
		mensagemSelecionada.setDeletar(1);
		inserirOuAlterar(mensagemSelecionada);
		preencherMensagens();
	}
	
	private void ocultar() {
		mensagemSelecionada.setOcultar(1);
		inserirOuAlterar(mensagemSelecionada);
		preencherMensagens();
	}
	
	private void desocultar() {
		oculto = false;
		ArrayList<Object> mensagens = listarMensagensPorConversaOcultas(conversa.getCodigo());
    	if(mensagens != null && !mensagens.isEmpty()) {
    		MensagensAdapter adapter = new MensagensAdapter(this, mensagens);
        	lv_mensagens.setAdapter(adapter);
        	lv_mensagens.invalidate();
    	} 
    	bt_desocultar.setImageResource(R.drawable.ocultar_50);
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
	
	private ArrayList<Object> listarMensagensPorConversaOcultas(Integer codigo) {
		try {
			return mensagemFacade.listarMensagensPorConversaOcultas(codigo);
		} catch (FacadeException e) {
		}
		return null;
	}
	
	private Object selecionar(ConversaBean conversa) {
		try {
			return conversaFacade.selecionar(conversa);
		} catch (FacadeException e) {
		}
		return false;
	}
	
	private Object inserirOuAlterar(MensagemBean mensagem) {
		try {
			return mensagemFacade.inserirOuAlterar(mensagem);
		} catch (FacadeException e) {
		}
		return null;
	}
	
	/* 
	 * CICLO DE VIDA
	 * */
	
	private void registrarAbrirTela() {
		SharedPreferences preferencias = getSharedPreferences(ConstantesDiversas.PF_NOME_PREFERENCIA, MODE_PRIVATE);
		SharedPreferences.Editor editor = preferencias.edit();
		editor.putBoolean(ConstantesDiversas.PF_TELA_MENSAGEM, true);
		editor.commit();
	}
	
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
    	SharedPreferences preferencias = getSharedPreferences(ConstantesDiversas.PF_NOME_PREFERENCIA, MODE_PRIVATE);
		SharedPreferences.Editor editor = preferencias.edit();
		editor.putBoolean(ConstantesDiversas.PF_TELA_MENSAGEM, false);
		editor.commit();
		/*if(Preferencias.getTelaConversa(this) == false) {
			Intent intent = new Intent(this, TelaConversas.class);
			startActivity(intent);
		}*/
		if(cliente != null) cliente.getTratarMensagem().setHandler(null);
		Intent intent = new Intent();
		intent.putExtra(ConstantesDiversas.BD_NOTIFICAR_CONVERSA, 1);
		setResult(ConstantesTelas.NOTIFICAR_CONVERSA, intent);
    }
    
    /* 
   	 * ACTIVITY
   	 * */
    
    private class HandleMensagem extends Handler {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
				case ConstantesDiversas.HD_NOVA_MENSAGEM:
					String mensagem = msg.getData().getString(ConstantesDiversas.BD_MENSAGEM);
					receber(mensagem);
					break;
				default:      
					break;
			}
		}
	}
    
    @Override
    protected Dialog onCreateDialog(int id) {
		switch (id) {
        	case ConstantesTelas.DIALOG_SIM_NAO:
                return new AlertDialog.Builder(this)
                    .setIcon(R.drawable.alerta_50)
                    .setTitle(R.string.dialog_tituloDialogDesconectar)
                    .setPositiveButton(R.string.dialog_sim, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                        	desconectar();
                        }
                    })
                    .setNegativeButton(R.string.dialog_nao, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                        }
                    })
                    .create();
        	case ConstantesTelas.DIALOG_LISTA: 
        		return new AlertDialog.Builder(this)
                .setTitle(R.string.dialog_tituloDialogList)
                .setItems(R.array.opcao_listaMensagem, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    	if(which == 0) { //Ocultar
                    		ocultar();
                    	} else if(which == 1) { //Deletar
                    		showDialog(ConstantesTelas.DIALOG_SIM_NAO2);
                    	} 
                    }
                }).create();
        	case ConstantesTelas.DIALOG_SIM_NAO2:
                return new AlertDialog.Builder(this)
                    .setIcon(R.drawable.alerta_50)
                    .setTitle(R.string.dialog_tituloDialogExcluir)
                    .setPositiveButton(R.string.dialog_sim, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                        	deletar();
                        }
                    })
                    .setNegativeButton(R.string.dialog_nao, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                        }
                    })
                    .create();
		}
		return null;
	}

}
