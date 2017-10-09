package br.rr.wsl.telas;

import java.util.ArrayList;

import br.rr.wsl.R;
import br.rr.wsl.controle.adapter.ContatosAdapter;
import br.rr.wsl.controle.utilitarios.ConstantesDiversas;
import br.rr.wsl.controle.utilitarios.ConstantesTelas;
import br.rr.wsl.entidades.contato.bean.ContatoBean;
import br.rr.wsl.entidades.contato.facade.ContatoFacade;
import br.rr.wsl.entidades.contato.facade.ContatoFacadeImpl;
import br.rr.wsl.util.FacadeException;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

public class TelaListarContatos extends Activity implements OnClickListener, OnItemClickListener {
	
	//private static final String CATEGORIA = "wsl";
	
	private ContatoFacade contatoFacade;
	     
	private ImageButton bt_inicio;
	private ImageButton bt_adicionar;
	
	private ListView lv_dono;
	private ListView lv_contatos;
	private EditText et_procurar;
	
	private ContatoBean contatoSelecionado;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_lista_contatos);
        
        contatoFacade = (ContatoFacade) new ContatoFacadeImpl(this);   

        bt_inicio = (ImageButton) findViewById(R.id.telaListaContatos_bt_inicio);
        bt_inicio.setOnClickListener(this);
        bt_adicionar = (ImageButton) findViewById(R.id.telaListaContatos_bt_adicionar);
        bt_adicionar.setOnClickListener(this);

        lv_dono = (ListView) findViewById(R.id.telaListaContatos_lv_dono);
        lv_dono.setOnItemClickListener(this);  
        lv_contatos = (ListView) findViewById(R.id.telaListaContatos_lv_contatos);
        lv_contatos.setOnItemClickListener(this);  
        
        et_procurar = (EditText) findViewById(R.id.telaListaContatos_et_procurar);
        et_procurar.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,int after) {
			}
			@Override
			public void afterTextChanged(Editable s) {
				preencherListas();
			}
		});
        
        preencherListas();
    }
	
	/* 
	 * EVENTOS
	 * */
	
	@Override
	public void onClick(View v) {
		if(v == bt_inicio) {
			finish();
		} else if(v == bt_adicionar) {
			seguirParaTelaCadastro();
		} 
	}
	
	@Override
	public void onItemClick(AdapterView<?> pai, View view, int posicao, long id) {
		if(pai == lv_dono) {
			contatoSelecionado = (ContatoBean) lv_dono.getAdapter().getItem(posicao);
			showDialog(ConstantesTelas.DIALOG_LISTA);
		} else if(pai == lv_contatos) {
			contatoSelecionado = (ContatoBean) lv_contatos.getAdapter().getItem(posicao);
			showDialog(ConstantesTelas.DIALOG_LISTA);
		}
	}
	
	/* 
	 * AÇOES ESPECIFICAS 
	 * */
	
	private void preencherListas() {
		ContatoBean contato = selecionarContatoDono();
		if(contato != null) {
			ArrayList<Object> contatos1 = new ArrayList<Object>();
			contatos1.add(contato);
			ContatosAdapter adapter = new ContatosAdapter(this, contatos1);
	        lv_dono.setAdapter(adapter);
		}	
		ArrayList<Object> contatos2 = null;
		if(et_procurar.getText().toString().equals("")) {
			contatos2 = listarAmigosOrdenadoPorNome();
		} else {
			contatos2 = listarAmigosOrdenadoPorNome(et_procurar.getText().toString());
		}
        if(contatos2 != null) {
	        ContatosAdapter adapter = new ContatosAdapter(this, contatos2);
	        lv_contatos.setAdapter(adapter);
        } 
	}
	
	private void seguirParaTelaCadastro() {
		Intent intent = new Intent(this, TelaCadastroEditarContatos.class);
		startActivityForResult(intent, ConstantesTelas.EDITAR_EXCLUIR);
	}
	
	private void seguirParaTelaEditar() {
		Intent intent = new Intent(this, TelaCadastroEditarContatos.class);
		intent.putExtra(ConstantesDiversas.BD_CODIGO_CONTATO, contatoSelecionado.getCodigo());
		startActivityForResult(intent, ConstantesTelas.EDITAR_EXCLUIR);
	}
	  
	private void perguntarExlusao() {
		if(excluir(contatoSelecionado) != null) {
    		preencherListas();
    		Resources resources = getResources();
    		Toast.makeText(TelaListarContatos.this, resources.getString(R.string.telaListaContatos_msg_contatoExluir), Toast.LENGTH_SHORT).show();
    	}
	}
	
	/* 
	 * BANCO DE DADOS
	 * */
	
	private ArrayList<Object> listarAmigosOrdenadoPorNome(String nome) {
		try {
			return contatoFacade.listarAmigosOrdenadoPorNome(nome);
		} catch (FacadeException e) {
		}
		return null;
	}
	
	private ArrayList<Object> listarAmigosOrdenadoPorNome() {
		try {
			return contatoFacade.listarAmigosOrdenadoPorNome();
		} catch (FacadeException e) {
		}
		return null;
	}
	
	private Object excluir(ContatoBean contato) {
		try {
			contato.setDeletar(1);
			return contatoFacade.alterar(contato);
		} catch (FacadeException e) {
		}
		return false;
	}
	
	private ContatoBean selecionarContatoDono() {
		try {
			return (ContatoBean) contatoFacade.selecionarContatoDono();
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
    
    @Override
	protected void onActivityResult(int codigo, int resultado, Intent it) {
		switch (resultado) {
			case ConstantesTelas.EDITAR_EXCLUIR:
				preencherListas();
				break;
		}
	}
    
    @Override
    protected Dialog onCreateDialog(int id) {
		switch (id) {
        	case ConstantesTelas.DIALOG_LISTA: 
        		return new AlertDialog.Builder(this)
                .setTitle(R.string.dialog_tituloDialogList)
                .setItems(R.array.opcao_listaContatos, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    	if(which == 0) { //Editar
                    		seguirParaTelaEditar();
                    	} else { //Excluir
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
                        	perguntarExlusao();
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
