package br.rr.wsl.telas;

import java.util.ArrayList;

import br.rr.wsl.R;
import br.rr.wsl.controle.adapter.ClientesConectadosAdapter;
import br.rr.wsl.controle.adapter.ServidoresConectadosAdapter;
import br.rr.wsl.controle.clienteServidor.Cliente;
import br.rr.wsl.controle.clienteServidor.ClienteEntrada;
import br.rr.wsl.controle.utilitarios.ConstantesDiversas;
import br.rr.wsl.controle.utilitarios.ConstantesTelas;
import br.rr.wsl.controle.utilitarios.Instancias;
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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

public class TelaContatosConectados extends Activity implements OnClickListener, OnItemClickListener {
	
	//private static final String CATEGORIA = "wsl";
	
	private ContatoFacade contatoFacade;
	
	private ImageButton bt_inicio;
	
	private ListView lv_servidoresConectados;
	private ListView lv_clientesConectados;
	
	private ContatoBean contatoSelecionado;
	private Resources resources;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_contatos_conectados);
        resources = getResources();
        
        contatoFacade = new ContatoFacadeImpl(this);
    
        bt_inicio = (ImageButton) findViewById(R.id.telaContatosConectados_bt_inicio);
        bt_inicio.setOnClickListener(this);
        
        lv_clientesConectados = (ListView) findViewById(R.id.telaContatosConectados_lv_clientesConectados);
        lv_clientesConectados.setOnItemClickListener(this);
        lv_servidoresConectados = (ListView) findViewById(R.id.telaContatosConectados_lv_servidoresConectados);
        lv_servidoresConectados.setOnItemClickListener(this);

        preencherListaServidores();
        preencherListaClientes(); 
    }
	
	/* 
	 * EVENTOS
	 * */
	
	@Override
	public void onClick(View v) {
		if(v == bt_inicio) {
			finish();
		}
	}
	
	@Override   
	public void onItemClick(AdapterView<?> pai, View view, int posicao, long id) {
		if(pai == lv_clientesConectados) {
			contatoSelecionado = (ContatoBean) lv_clientesConectados.getAdapter().getItem(posicao);
			showDialog(ConstantesTelas.DIALOG_LISTA);
		} else if(pai == lv_servidoresConectados) {
			Cliente cliente = (Cliente) lv_servidoresConectados.getAdapter().getItem(posicao);
			seguirParaTelaClientesConectados(cliente);
		}
	}
	
	/* 
	 * AÇOES ESPECIFICAS
	 * */
	
	private void preencherListaServidores() {
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		for(Cliente cliente : Instancias.getConversasServidor().values()) {
			clientes.add(cliente);
		}
		ServidoresConectadosAdapter adapter = new ServidoresConectadosAdapter(this, clientes);
		lv_servidoresConectados.setAdapter(adapter);
	}
	
	private void preencherListaClientes() {
		ClientesConectadosAdapter adapter = new ClientesConectadosAdapter(this, listarClientesConectados());
		lv_clientesConectados.setAdapter(adapter);
	}

	private void desconectar() {
		Boolean verificar = false;
		if(Instancias.getServidor() != null) {
			for(ArrayList<ClienteEntrada> clientes : Instancias.getServidor().getConversas().values()) {       	
	        	for(ClienteEntrada cliente : clientes) {
	        		Boolean status = false;
					if(cliente.getConversa().getClienteServidor() == 0 && 
							cliente.getContato().getCodigo() == contatoSelecionado.getCodigo()) {
						cliente.fecharConexao();
						preencherListaClientes();
						Instancias.getConversasCliente().remove(cliente.getConversa());
						Toast.makeText(this, resources.getString(R.string.telaContatosConectados_msg_contatoDesconectado), Toast.LENGTH_SHORT).show();
						verificar = true;
						status = true;
						break;
					}
					if(status) break;
				}
			}
		}
		if(!verificar) Toast.makeText(this, resources.getString(R.string.telaContatosConectados_msg_verificar), Toast.LENGTH_SHORT).show();
	}
	
	private void seguirParaTelaAssociarConversa() {
		Intent intent = new Intent(this, TelaAssociarConversa.class);
		intent.putExtra(ConstantesDiversas.BD_ASSOCIAR_CONVERSA, contatoSelecionado.getCodigo());
		startActivity(intent);
	}
	
	private void seguirParaTelaClientesConectados(Cliente cliente) {
		Intent intent = new Intent(this, TelaClientesConectados.class);
		intent.putExtra(ConstantesDiversas.BD_SERVIDOR_CONECTADO, cliente.getConversa().getCodigo());
		startActivity(intent);
	}
	 
	/* 
	 * BANCO DE DADOS
	 * */
	
	private ArrayList<Object> listarClientesConectados() {
		try {
			return contatoFacade.listarContatosConectados();
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
    protected Dialog onCreateDialog(int id) {
		switch (id) {
        	case ConstantesTelas.DIALOG_LISTA: 
        		return new AlertDialog.Builder(this)
                .setTitle(R.string.dialog_tituloDialogList)
                .setItems(R.array.opcao_listaClientesConectados, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    	if(which == 0) { //associar conversa
                    		seguirParaTelaAssociarConversa();
                    	} else if(which == 1) { //desconectar
                    		showDialog(ConstantesTelas.DIALOG_SIM_NAO);
                    	}
                    }
                }).create();
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
		}
		return null;
	}
    
}
