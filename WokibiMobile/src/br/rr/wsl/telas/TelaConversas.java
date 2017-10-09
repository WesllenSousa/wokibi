package br.rr.wsl.telas;

import java.util.ArrayList;

import br.rr.wsl.R;
import br.rr.wsl.controle.adapter.ConversasAdapter;
import br.rr.wsl.controle.clienteServidor.Cliente;
import br.rr.wsl.controle.utilitarios.ConstantesDiversas;
import br.rr.wsl.controle.utilitarios.ConstantesTelas;
import br.rr.wsl.controle.utilitarios.Instancias;
import br.rr.wsl.entidades.conversa.bean.ConversaBean;
import br.rr.wsl.entidades.conversa.facade.ConversaFacade;
import br.rr.wsl.entidades.conversa.facade.ConversaFacadeImpl;
import br.rr.wsl.util.FacadeException;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class TelaConversas extends Activity implements OnClickListener, OnItemClickListener, OnItemLongClickListener {
	
	//private static final String CATEGORIA = "wsl";
	
	private ConversaFacade conversaFacade;

	private ImageButton bt_inicio;
	private ImageButton bt_adicionarConversa;
	
	private ListView lv_conversasAtivas;
	private ListView lv_conversasInativas;
	
	private ConversaBean conversaSelecionada;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_conversa);
        
        conversaFacade = new ConversaFacadeImpl(this);

        bt_inicio = (ImageButton) findViewById(R.id.telaConversa_bt_inicio);
        bt_inicio.setOnClickListener(this);
        bt_adicionarConversa = (ImageButton) findViewById(R.id.telaConversa_bt_adicionar);
        bt_adicionarConversa.setOnClickListener(this);
        
        lv_conversasAtivas = (ListView) findViewById(R.id.telaConversa_lv_conversasAtivas);
        lv_conversasAtivas.setOnItemClickListener(this);
        lv_conversasAtivas.setOnItemLongClickListener(this);
        
        lv_conversasInativas = (ListView) findViewById(R.id.telaConversa_lv_conversasInativas);
        lv_conversasInativas.setOnItemClickListener(this);
        lv_conversasInativas.setOnItemLongClickListener(this);
        
        preencherListas();
        registrarAbrirTela();
    }
	
	/* 
	 * EVENTOS
	 * */
	
	@Override
	public void onClick(View v) {
		if(v == bt_inicio) {
			finish();
		} else if(v == bt_adicionarConversa) {
			showDialog(ConstantesTelas.DIALOG_CLIENTE_SERVIDOR);
		}
	}
	
	@Override
	public boolean onItemLongClick(AdapterView<?> pai, View view, int posicao, long id) {
		if(pai == lv_conversasAtivas) {
			conversaSelecionada = (ConversaBean) lv_conversasAtivas.getAdapter().getItem(posicao);
			showDialog(ConstantesTelas.DIALOG_LISTA);
		} else if(pai == lv_conversasInativas) {
			conversaSelecionada = (ConversaBean) lv_conversasInativas.getAdapter().getItem(posicao);
			showDialog(ConstantesTelas.DIALOG_LISTA2);
		}
		return false;
	}

	@Override
	public void onItemClick(AdapterView<?> pai, View view, int posicao, long id) {
		if(pai == lv_conversasAtivas) {
			conversaSelecionada = (ConversaBean) lv_conversasAtivas.getAdapter().getItem(posicao);
		} else if(pai == lv_conversasInativas) {
			conversaSelecionada = (ConversaBean) lv_conversasInativas.getAdapter().getItem(posicao);
		}
		seguirParaTelaMensagens();
	}
	
	/* 
	 * AÇOES ESPECIFICAS
	 * */
	
	private void preencherListas() {
		ArrayList<Object> conversasAtivas = listarTodasConversasAtivas();
		if(conversasAtivas != null && !conversasAtivas.isEmpty()) {
			ConversasAdapter adapter1 = new ConversasAdapter(this, conversasAtivas);
			lv_conversasAtivas.setAdapter(adapter1);
		} else lv_conversasAtivas.setAdapter(null);
		ArrayList<Object> conversasInativas = listarTodasConversasInativas();
		if(conversasInativas != null && !conversasInativas.isEmpty()) {
	        ConversasAdapter adapter2 = new ConversasAdapter(this, conversasInativas);
	        lv_conversasInativas.setAdapter(adapter2);
		} else lv_conversasInativas.setAdapter(null);
	}
	
	private void conectar(ConversaBean conversa, Integer clienteServidor) {
		if(clienteServidor == 1) { //Servidor	
			Intent intent = new Intent(this, TelaNovaConversaCliente.class);
			if(conversa != null) intent.putExtra(ConstantesDiversas.BD_CONECTAR_CONVERSA, conversa.getCodigo());
			startActivityForResult(intent, ConstantesTelas.NOVA_CONVERSA);
		} else if(clienteServidor == 0) { //Cliente
			if(Instancias.getServidor() == null) {
				Intent intent = new Intent(ConstantesDiversas.SV_SERVICO_SERVIDOR);
				startService(intent);
				setResult(ConstantesTelas.CRIAR_SERVIDOR, new Intent());
			}
			Intent intent = new Intent(this, TelaNovaConversaServidor.class);
			if(conversa != null) intent.putExtra(ConstantesDiversas.BD_CONECTAR_CONVERSA, conversa.getCodigo());
			startActivityForResult(intent, ConstantesTelas.NOVA_CONVERSA);
		}
	}
	
	private void seguirParaTelaMensagens() {
		Intent intent = new Intent(this, TelaMensagens.class);
		intent.putExtra(ConstantesDiversas.BD_CODIGO_CONVERSA, conversaSelecionada.getCodigo());
		startActivityForResult(intent, ConstantesTelas.NOTIFICAR_CONVERSA);
	}
	
	private void desconectar() {
		Cliente cliente = null;
		if(Instancias.getConversasCliente().containsKey(conversaSelecionada.getCodigo())) {
			cliente = Instancias.getConversasCliente().get(conversaSelecionada.getCodigo());
    	} else if(Instancias.getConversasServidor().containsKey(conversaSelecionada.getCodigo())) {
    		cliente = Instancias.getConversasServidor().get(conversaSelecionada.getCodigo());
    	}
		if(cliente != null) {
			cliente.fecharConexao();
			preencherListas();
		} 
	}

	private void perguntarExclusao() {
		desconectar();
		if(excluir(conversaSelecionada) != null) {
    		preencherListas();
    		Resources resources = getResources();
    		Toast.makeText(this, resources.getString(R.string.telaConversa_msg_excluir), Toast.LENGTH_SHORT).show();
    	}
	}
	
	/* 
	 * BANCO DE DADOS
	 * */
	
	private ArrayList<Object> listarTodasConversasAtivas() {
		try {
			return conversaFacade.listarTodasConversasAtivas();
		} catch (FacadeException e) {
		}
		return null;
 	}
	
	private ArrayList<Object> listarTodasConversasInativas() {
		try {
			return conversaFacade.listarTodasConversasInativas();
		} catch (FacadeException e) {
		}
		return null;
	}
	
	private Object excluir(ConversaBean conversa) {
		try {
			conversa.setDeletar(1);
			return conversaFacade.alterar(conversa);
		} catch (FacadeException e) {
		}
		return false;
	}
	
	private Object selecionar(ConversaBean conversa) {
		try {
			return conversaFacade.selecionar(conversa);
		} catch (FacadeException e) {
		}
		return false;
	}
	
	/* 
	 * CICLO DE VIDA
	 * */
	
	private void registrarAbrirTela() {
		SharedPreferences preferencias = getSharedPreferences(ConstantesDiversas.PF_NOME_PREFERENCIA, MODE_PRIVATE);
		SharedPreferences.Editor editor = preferencias.edit();
		editor.putBoolean(ConstantesDiversas.PF_TELA_CONVERSA, true);
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
		editor.putBoolean(ConstantesDiversas.PF_TELA_CONVERSA, false);
		editor.commit();
		/*if(Preferencias.getTelaPrincipal(this) == false) {
			Intent intent = new Intent(this, TelaPrincipal.class);
			startActivity(intent);
		}*/
    }
    
    /* 
	 * ACTIVITY
	 * */
    
    @Override
	protected void onActivityResult(int codigo, int resultado, Intent it) {
		if(it == null) return;
		switch (resultado) {
			case ConstantesTelas.NOVA_CONVERSA:
				Integer codigoConversa = it.getExtras().getInt(ConstantesDiversas.BD_CODIGO_CONVERSA);
				ConversaBean conversa = new ConversaBean();
				conversa.setCodigo(codigoConversa);
				conversaSelecionada = (ConversaBean) selecionar(conversa);
				preencherListas();   
				seguirParaTelaMensagens();
				break;
			case ConstantesTelas.NOVA_CONVERSA_SERVIDOR:
				finish();
				break;
			case ConstantesTelas.NOTIFICAR_CONVERSA:
				Integer status = it.getExtras().getInt(ConstantesDiversas.BD_NOTIFICAR_CONVERSA);
				if(status == 0) {
					desconectar();
				} else {
					preencherListas();
				}
				break;
		}
	}
    
    @Override
    protected Dialog onCreateDialog(int id) {
		switch (id) {
        	case ConstantesTelas.DIALOG_LISTA: 
        		return new AlertDialog.Builder(this)
                .setTitle(R.string.dialog_tituloDialogList)
                .setItems(R.array.opcao_conversas, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    	if(which == 0) { //Ver mensagens
                    		seguirParaTelaMensagens();
                    	} else if(which == 1) { //Desconectar
                    		showDialog(ConstantesTelas.DIALOG_SIM_NAO2);
                    	} else if(which == 2) { //Excluir
                    		showDialog(ConstantesTelas.DIALOG_SIM_NAO);
                    	}
                    }
                }).create();
        	case ConstantesTelas.DIALOG_LISTA2: 
        		return new AlertDialog.Builder(this)
                .setTitle(R.string.dialog_tituloDialogList)
                .setItems(R.array.opcao_conversas2, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    	if(which == 0) { //Ver mensagens
                    		seguirParaTelaMensagens();
                    	} else if(which == 1) { //Conectar
                    		conectar(conversaSelecionada, conversaSelecionada.getClienteServidor());
                    	} else if(which == 2) { //Excluir
                    		showDialog(ConstantesTelas.DIALOG_SIM_NAO);
                    	}
                    }
                }).create();
        	case ConstantesTelas.DIALOG_SIM_NAO:
                return new AlertDialog.Builder(this)
                    .setIcon(R.drawable.alerta_50)
                    .setTitle(R.string.dialog_tituloDialogExcluir)
                    .setPositiveButton(R.string.dialog_sim, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                        	perguntarExclusao();
                        }
                    })
                    .setNegativeButton(R.string.dialog_nao, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                        }
                    })
                    .create();
        	case ConstantesTelas.DIALOG_SIM_NAO2:
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
        	case ConstantesTelas.DIALOG_CLIENTE_SERVIDOR:
                return new AlertDialog.Builder(this)
                    .setIcon(R.drawable.alerta_50)
                    .setTitle(R.string.dialog_tituloDialogConversa)
                    .setPositiveButton(R.string.dialog_cliente, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                        	conectar(null, 1);
                        }
                    })
                    .setNegativeButton(R.string.dialog_servidor, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                        	conectar(null, 0);
                        }
                    })
                    .create();
		}
		return null;
	}
    
}
