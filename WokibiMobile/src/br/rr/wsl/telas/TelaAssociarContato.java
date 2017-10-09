package br.rr.wsl.telas;

import java.util.ArrayList;

import br.rr.wsl.R;
import br.rr.wsl.controle.clienteServidor.Cliente;
import br.rr.wsl.controle.mensagens.Sms;
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
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;

public class TelaAssociarContato extends Activity implements OnClickListener, OnItemClickListener {
	
	//private static final String CATEGORIA = "wsl";
	
	private ContatoFacade contatoFacade;
	private ConversaFacade conversaFacade;
	
	private ImageButton bt_voltar;
	private ImageButton bt_confirmar;
	
	private ListView lv_conectados;
	private ListView lv_desconectados;
	
	private ConversaBean conversa;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_associonar_contato);
        
        contatoFacade = new ContatoFacadeImpl(this);
        conversaFacade = new ConversaFacadeImpl(this);
        
        bt_voltar = (ImageButton) findViewById(R.id.telaAssociarUsuario_bt_voltar);
        bt_voltar.setOnClickListener(this);
        bt_confirmar = (ImageButton) findViewById(R.id.telaAssociarUsuario_bt_confirmar);
        bt_confirmar.setOnClickListener(this);
        
        lv_conectados = (ListView) findViewById(R.id.telaAssociarUsuario_lv_conectados);
        lv_conectados.setAdapter(new ArrayAdapter<Object>(this,
                android.R.layout.simple_list_item_multiple_choice, listarContatosConectados()));
        lv_conectados.setOnItemClickListener(this);
        
        lv_desconectados = (ListView) findViewById(R.id.telaAssociarUsuario_lv_desconectados);
        lv_desconectados.setAdapter(new ArrayAdapter<Object>(this,
                android.R.layout.simple_list_item_multiple_choice, listarContatosDesconectados()));
        lv_desconectados.setOnItemClickListener(this);
        
        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
        	Integer codigo = bundle.getInt(ConstantesDiversas.BD_CODIGO_CONVERSA);
        	ConversaBean c = new ConversaBean();
        	c.setCodigo(codigo);
        	conversa = (ConversaBean) selecionar(c);
        }
    }
	
	/* 
	 * EVENTOS
	 * */
	
	@Override
	public void onItemClick(AdapterView<?> pai, View view, int posicao, long id) {
		if(pai == lv_conectados) {
		} else if(pai == lv_desconectados) {		
		} 
	}

	@Override
	public void onClick(View v) {
		if(v == bt_voltar) {
			finish();
		} else if(v == bt_confirmar) {
			confirmar();
		} 
	}
	
	/* 
	 * AÇOES ESPECIFICAS
	 * */
	
	private void confirmar() {
		Cliente cliente = null;
		String ipServidor = null; 
		if(conversa.getClienteServidor() == 0) cliente = Instancias.getConversasCliente().get(conversa.getCodigo());
		else if(conversa.getClienteServidor() == 1) cliente = Instancias.getConversasServidor().get(conversa.getCodigo());
		if(cliente != null) {
			ipServidor = cliente.getSocket().getInetAddress().getHostAddress();
			enviarLocalizacaoServidor(ipServidor, cliente);
			enviarLocalizacaoMensagem(ipServidor, cliente);
		} else {
			Resources resources = getResources();
			Toast.makeText(this, resources.getString(R.string.geral_servidorNaoDefinido), Toast.LENGTH_SHORT).show();
		}
		finish();
	}
	
	private void enviarLocalizacaoServidor(String ip, Cliente cliente) {
		SparseBooleanArray selecionados1 = lv_conectados.getCheckedItemPositions();
		for(int i = 0; i < lv_conectados.getCount(); i++) {
			if(selecionados1.get(i)) {
				ContatoBean contato = (ContatoBean) lv_conectados.getItemAtPosition(i);
				cliente.enviarDados(ConstantesDiversas.CS_ENCAMINHAR_ASSOCIAR_CONVERSA);
				cliente.enviarDados(ip);
				cliente.enviarDados(cliente.getIdentificacaoConversa());
				cliente.enviarDados(cliente.getConversa().getDescricao() + " / " + Instancias.getDono().getNome());
				cliente.enviarDados(contato.getNome());
				Resources resources = getResources();  
				Toast.makeText(this, resources.getString(R.string.telaAssociarContato_rede)+" "+contato, Toast.LENGTH_SHORT).show();
			}
		}
	}
	
	private void enviarLocalizacaoMensagem(String ip, Cliente cliente) {
		Sms sms = new Sms();
		SparseBooleanArray selecionados2 = lv_desconectados.getCheckedItemPositions();
		for(int i = 0; i < lv_desconectados.getCount(); i++) {
			if(selecionados2.get(i)) {
				ContatoBean contato = (ContatoBean) lv_desconectados.getItemAtPosition(i);
				String mensagem = ConstantesDiversas.PADRAO_MENSAGEM+ip+"-"+cliente.getIdentificacaoConversa()+"-"+cliente.getConversa().getDescricao() + " / "+Instancias.getDono().getNome();
				sms.enviarSms(this, ConstantesDiversas.CODIGO_NUMERO+contato.getDdd()+contato.getCelular(), 
						mensagem);
				Resources resources = getResources();  
				Toast.makeText(this, resources.getString(R.string.telaAssociarContato_sms)+" "+contato, Toast.LENGTH_SHORT).show();
			}
		}
	}
	
	/* 
	 * BANCO DE DADOS
	 * */
	
	private ArrayList<Object> listarContatosConectados() {
		try {
			return contatoFacade.listarContatosConectados();
		} catch (FacadeException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private ArrayList<Object> listarContatosDesconectados() {
		try {
			return contatoFacade.listarContatosDesconectados();
		} catch (FacadeException e) {
			e.printStackTrace();
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
