package br.rr.wsl.telas;

import java.util.ArrayList;

import br.rr.wsl.R;
import br.rr.wsl.controle.clienteServidor.Cliente;
import br.rr.wsl.controle.utilitarios.ConstantesDiversas;
import br.rr.wsl.controle.utilitarios.Instancias;
import br.rr.wsl.entidades.contato.bean.ContatoBean;
import br.rr.wsl.entidades.contato.facade.ContatoFacade;
import br.rr.wsl.entidades.contato.facade.ContatoFacadeImpl;
import br.rr.wsl.entidades.conversa.bean.ConversaBean;
import br.rr.wsl.entidades.conversa.facade.ConversaFacade;
import br.rr.wsl.entidades.conversa.facade.ConversaFacadeImpl;
import br.rr.wsl.util.FacadeException;
import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

public class TelaAssociarConversa extends Activity implements OnClickListener, OnItemClickListener {
	
	//private static final String CATEGORIA = "wsl";
	
	private ConversaFacade conversaFacade;
	private ContatoFacade contatoFacade;
	
	private ImageButton bt_voltar;
	private ImageButton bt_confirmar;
	
	private ListView lv_conversas;
	
	private ConversaBean conversa;
	private String nomeContato;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_associonar_conversa);
        
        conversaFacade = new ConversaFacadeImpl(this);
        contatoFacade = new ContatoFacadeImpl(this);
        
        bt_voltar = (ImageButton) findViewById(R.id.telaAssociarConversa_bt_voltar);
        bt_voltar.setOnClickListener(this);
        bt_confirmar = (ImageButton) findViewById(R.id.telaAssociarConversa_bt_confirmar);
        bt_confirmar.setOnClickListener(this);
        
        lv_conversas = (ListView) findViewById(R.id.telaAssociarConversa_lv_conversas);
        
        lv_conversas.setOnItemClickListener(this);
        
        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
        	Integer codigo = bundle.getInt(ConstantesDiversas.BD_ASSOCIAR_CONVERSA);
        	if(codigo != null && codigo != 0) {
	        	ContatoBean c = new ContatoBean();
	        	c.setCodigo(codigo);
	        	ContatoBean contato = selecionar(c);
	        	nomeContato = contato.getNome();
        	} else {
        		nomeContato = bundle.getString(ConstantesDiversas.BD_ASSOCIAR_CONVERSA_CLIENTE);
        	}
        }
        
        preencherListas();
    }
	
	/* 
	 * EVENTOS
	 * */

	@Override
	public void onItemClick(AdapterView<?> pai, View view, int posicao, long id) {
		if(pai == lv_conversas) {
			conversa = (ConversaBean) lv_conversas.getAdapter().getItem(posicao);
		}
	}

	@Override
	public void onClick(View v) {
		if(v == bt_voltar) {
			finish();
		} else if(v == bt_confirmar) {
			associar();
			finish();
		}
	}
	
	/* 
	 * AÇOES ESPECIFICAS
	 * */
	
	private void preencherListas() {
		lv_conversas.setAdapter(new ArrayAdapter<Object>(this,
                android.R.layout.simple_list_item_single_choice, listarTodasConversasAtivas()));
	}
	
	private void associar() {
		Cliente cliente = null;
		String ipServidor = null; 
		if(conversa.getClienteServidor() == 0) cliente = Instancias.getConversasCliente().get(conversa.getCodigo());
		else if(conversa.getClienteServidor() == 1) cliente = Instancias.getConversasServidor().get(conversa.getCodigo());
		if(cliente != null) {
			ipServidor = cliente.getSocket().getInetAddress().getHostAddress();
			enviarLocalizacaoServidor(ipServidor, cliente);
		} else {
			Resources resources = getResources();
			Toast.makeText(this, resources.getString(R.string.geral_servidorNaoDefinido), Toast.LENGTH_SHORT).show();
		}
		finish();
	}
	
	private void enviarLocalizacaoServidor(String ip, Cliente cliente) {
		cliente.enviarDados(ConstantesDiversas.CS_ENCAMINHAR_ASSOCIAR_CONVERSA);
		cliente.enviarDados(ip);
		cliente.enviarDados(cliente.getIdentificacaoConversa());
		cliente.enviarDados(cliente.getConversa().getDescricao() + " / " + Instancias.getDono().getNome());
		cliente.enviarDados(nomeContato);
		Resources resources = getResources();
		Toast.makeText(this, resources.getString(R.string.telaAssociarConversa_localizacao), Toast.LENGTH_SHORT).show();
	}
	
	/* 
	 * BANCO DE DADOS
	 * */
	
	private ArrayList<Object> listarTodasConversasAtivas() {
		try {
			return conversaFacade.listarTodasConversasAtivas();
		} catch (FacadeException e) {
			e.printStackTrace();
		}
		return null;
 	}
	
	private ContatoBean selecionar(ContatoBean contato) {
		try {
			return (ContatoBean) contatoFacade.selecionar(contato);
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

}
