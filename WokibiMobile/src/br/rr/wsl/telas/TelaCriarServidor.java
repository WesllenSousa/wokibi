package br.rr.wsl.telas;

import java.util.ArrayList;

import br.rr.wsl.R;
import br.rr.wsl.controle.servicos.InterfaceServicoServidor;
import br.rr.wsl.controle.servicos.ServicoServidor;
import br.rr.wsl.controle.servicos.ServicoServidor.ServicoServidorBinder;
import br.rr.wsl.controle.utilitarios.ConstantesDiversas;
import br.rr.wsl.controle.utilitarios.ConstantesTelas;
import br.rr.wsl.controle.utilitarios.Instancias;
import br.rr.wsl.controle.utilitarios.Preferencias;
import br.rr.wsl.entidades.contato.facade.ContatoFacade;
import br.rr.wsl.entidades.contato.facade.ContatoFacadeImpl;
import br.rr.wsl.util.FacadeException;
import br.rr.wsl.util.RedeUtil;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.ImageButton;

public class TelaCriarServidor extends Activity implements OnClickListener, ServiceConnection {
	
	private static final String CATEGORIA = "wsl";
	
	private ContatoFacade contatoFacade;
	
	private ServiceConnection servico;
	private InterfaceServicoServidor iServico;
	
	private ImageButton bt_voltar;
	
	private TextView tv_localizacao;
	private ListView lv_contatos;
	private ToggleButton tg_conectarDesconectar;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_criar_servidor);
        
        contatoFacade = new ContatoFacadeImpl(this);
  
        bt_voltar = (ImageButton) findViewById(R.id.telaCriarServidor_bt_voltar);
        bt_voltar.setOnClickListener(this);
        
        lv_contatos = (ListView) findViewById(R.id.telaCriarServidor_lv_contatos);
        
        tg_conectarDesconectar = (ToggleButton) findViewById(R.id.telaCriarServidor_tg_conectarDesconectar);
        tg_conectarDesconectar.setOnClickListener(this);
        
        tv_localizacao = (TextView) findViewById(R.id.telaCriarServidor_tv_localizacao);
        
        String ip = RedeUtil.obtemIp(this);
        if(ip != null) tv_localizacao.setText(ip);
        else tg_conectarDesconectar.setEnabled(false);
        
        preencherLista();
        verificarStatus();
    }
	
	/* 
	 * EVENTOS
	 * */

	@Override
	public void onClick(View v) {
		if(v == bt_voltar) {
			finish();
		} else if(v == tg_conectarDesconectar) {
			conectarDesconectar();
		}
	}
	
	/* 
	 * AÇOES ESPECIFICAS
	 * */
	
	private void iniciarServico() {
		Intent intent = new Intent(ConstantesDiversas.SV_SERVICO_SERVIDOR);
		startService(intent);
		tg_conectarDesconectar.setEnabled(false);
		setResult(ConstantesTelas.CRIAR_SERVIDOR, new Intent());
	}
	
	public void preencherLista() {
		ArrayList<Object> contatos = listarContatosConectados();
		if(contatos != null && !contatos.isEmpty()) {
			lv_contatos.setAdapter(new ArrayAdapter<Object>(this, android.R.layout.simple_list_item_1, contatos));
		} else lv_contatos.setAdapter(null);
	}
	
	private void verificarStatus() {
		if(Instancias.getServidor() != null) {
			conectarServico();
			tg_conectarDesconectar.setChecked(true);
		} else {
			tg_conectarDesconectar.setChecked(false);
		}
	}
	
	private void conectarDesconectar() {
		if(tg_conectarDesconectar.isChecked()) {
			if(Preferencias.getServicoServidor(this)) {
				conectar();
			} else {
				tg_conectarDesconectar.setChecked(false);
				Resources resources = getResources();
				Toast.makeText(this, resources.getString(R.string.telaCriarServidor_servicoDesabilitado), Toast.LENGTH_SHORT).show();
			}
		} else {
			showDialog(ConstantesTelas.DIALOG_SIM_NAO);	
		}
	}
	
	private void conectarServico() {
		servico = this;
        if(servico != null) {
        	bindService(new Intent(this, ServicoServidor.class), servico, Context.BIND_AUTO_CREATE);	
        } else {
        	Resources resources = getResources();
        	Toast.makeText(this, resources.getString(R.string.telaCriarServidor_servicoFalha), Toast.LENGTH_SHORT).show();
        }
	}
	
	private void conectar() {
		if(RedeUtil.verificarEnderecoPrivado(RedeUtil.obtemIp(this))) {
			showDialog(ConstantesTelas.DIALOG_INFORMACAO);
		} else {
			iniciarServico();
		}
	}
	
	private void desconectar() {
		if(iServico != null) {
			iServico.pararServico();
			preencherLista();
			setResult(ConstantesTelas.CRIAR_SERVIDOR, new Intent());
		}
	}
	
	/* 
	 * BANCO DE DADOS
	 * */
	
	private ArrayList<Object> listarContatosConectados() {
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
                        	tg_conectarDesconectar.setChecked(true);
                        }
                    })
                    .create();
        	case ConstantesTelas.DIALOG_INFORMACAO:
                return new AlertDialog.Builder(this)
                    .setIcon(R.drawable.alerta_50)
                    .setTitle(R.string.dialog_aviso) 
                    .setMessage(R.string.dialog_tituloDialogIp)
                    .setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                        	iniciarServico();
                        }
                    })
                    .create();
		}
		return null;
	}

	@Override
	public void onServiceConnected(ComponentName componentName, IBinder binder) {
		Log.i(CATEGORIA, "Conectando ao serviço...");
		ServicoServidorBinder b = (ServicoServidorBinder) binder;
		if(b != null) {
			iServico = b.getServicoServidor();
			verificarStatus();
			setResult(ConstantesTelas.CRIAR_SERVIDOR, new Intent());
		}
	}

	@Override
	public void onServiceDisconnected(ComponentName name) {
		Log.i(CATEGORIA, "Parando serviço...");
		iServico = null;
	}
    
}
