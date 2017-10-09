package br.rr.wsl.telas;

import java.util.Calendar;

import br.rr.wsl.R;
import br.rr.wsl.controle.utilitarios.ConstantesDiversas;
import br.rr.wsl.controle.utilitarios.ConstantesTelas;
import br.rr.wsl.controle.utilitarios.Instancias;
import br.rr.wsl.util.RedeUtil;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

public class TelaPrincipal extends Activity implements OnClickListener {
	
	//private static final String CATEGORIA = "wsl";
	
	private Handler handle = new HandlePrincipal();
	
	private ImageButton bt_voltar;
	private ImageButton bt_servidor;
	private ImageButton bt_wifi;
	
	private ImageButton bt_conversas;
	private ImageButton bt_contatosConectados;
	private ImageButton bt_contatos;
	private ImageButton bt_configuracoes;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_principal);
        Instancias.setContext(this);
        Instancias.setHandlePrincipal(handle);
        
        bt_voltar = (ImageButton) findViewById(R.id.telaPrincipalServidor_bt_voltar);
        bt_voltar.setOnClickListener(this);
        bt_servidor = (ImageButton) findViewById(R.id.telaPrincipalCliente_bt_miniservidor);
        bt_servidor.setOnClickListener(this);
        bt_wifi = (ImageButton) findViewById(R.id.telaPrincipalServidor_bt_wifi);
        bt_wifi.setOnClickListener(this);
        
        bt_conversas = (ImageButton) findViewById(R.id.telaPrincipalServidor_bt_conversa);
        bt_conversas.setOnClickListener(this);
        bt_contatosConectados = (ImageButton) findViewById(R.id.telaPrincipalServidor_bt_contatoConectados);
        bt_contatosConectados.setOnClickListener(this);
        bt_contatos = (ImageButton) findViewById(R.id.telaPrincipalServidor_bt_contatos);
        bt_contatos.setOnClickListener(this);
        bt_configuracoes = (ImageButton) findViewById(R.id.telaPrincipalServidor_bt_configuracoes);
        bt_configuracoes.setOnClickListener(this);
        
        verificarStatusServidor();
        verificarStatusWifi();
        registrarAbrirTela();
        agendarVerificacaoStatusWifi();
    }
	
	/* 
	 * EVENTOS
	 * */
	
	@Override
	public void onClick(View v) {
		if(v == bt_voltar) {
			showDialog(ConstantesTelas.DIALOG_SIM_NAO2);
		} else if(v == bt_servidor) {
			seguirParaTelaCriarServidor();
		} else if(v == bt_wifi) {
			seguirParaTelaWiFi();
		} 
		
		else if(v == bt_conversas) {
			seguirParaTelaConversas();
		} else if(v == bt_contatosConectados) {
			seguirParaTelaClientesConectados();
		} else if(v == bt_contatos) {
			seguirParaTelaListaContatos();
		} else if(v == bt_configuracoes) {
			seguirParaTelaConfiguracoes();
		} 
	}

	/* 
	 * AÇOES ESPECIFICAS
	 * */
	
	/*Status*/
	private void verificarStatusServidor() {
		if(Instancias.getServidor() != null) {
			if(RedeUtil.obtemIp(this) == null) {
				Instancias.getServidor().fechaConexao();
				Instancias.setServidor(null);
				bt_servidor.setImageResource(R.drawable.servidor_inativo_50);
			} else {
				bt_servidor.setImageResource(R.drawable.servidor_ativo_50);
			}
		} else {
			bt_servidor.setImageResource(R.drawable.servidor_inativo_50);
		}
	}
	
	private void verificarStatusWifi() {
		if(RedeUtil.obtemIp(this) != null) {
			bt_wifi.setImageResource(R.drawable.wifi_ativada_50);
		} else {
			bt_wifi.setImageResource(R.drawable.wifi_desativada_50);
		}
	}
	
	private void agendarVerificacaoStatusWifi() {
		Intent intent = new Intent(ConstantesDiversas.RC_ATUALIZAR_REDE);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(System.currentTimeMillis());
		c.add(Calendar.SECOND, 5);
		AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
		alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), 10*1000, pendingIntent);
	}
	
	/*Telas*/
	private void seguirParaTelaCriarServidor() {
		Intent intent = new Intent(this, TelaCriarServidor.class);
		startActivityForResult(intent, ConstantesTelas.CRIAR_SERVIDOR);
	}
	
	private void seguirParaTelaConversas() {
		Intent intent = new Intent(this, TelaConversas.class);
		startActivityForResult(intent, ConstantesTelas.CRIAR_SERVIDOR);
	}
	
	private void seguirParaTelaClientesConectados() {
		Intent intent = new Intent(this, TelaContatosConectados.class);
		startActivity(intent);
	}
	
	private void seguirParaTelaListaContatos() {
		Intent intent = new Intent(this, TelaListarContatos.class);
		startActivity(intent);
	}
	
	private void seguirParaTelaConfiguracoes() {
		Intent intent = new Intent(this, TelaConfiguracoes.class);
		startActivity(intent);
	}
	
	private void seguirParaTelaWiFi() {
		Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
		startActivity(intent);
	}
	
	/* 
	 * CICLO DE VIDA
	 * */
	
	private void registrarAbrirTela() {
		SharedPreferences preferencias = getSharedPreferences(ConstantesDiversas.PF_NOME_PREFERENCIA, MODE_PRIVATE);
		SharedPreferences.Editor editor = preferencias.edit();
		editor.putBoolean(ConstantesDiversas.PF_TELA_PRINCIPAL, true);
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
		editor.putBoolean(ConstantesDiversas.PF_TELA_PRINCIPAL, false);
		editor.commit();
		//parando alarme atualizar rede
		Intent intent = new Intent(ConstantesDiversas.RC_ATUALIZAR_REDE);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
		AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
		alarmManager.cancel(pendingIntent);
    }
    
    /* 
   	 * ACTIVITY
   	 * */
    
    @Override
	protected void onActivityResult(int codigo, int resultado, Intent it) {
		if(it == null) return;
		switch (resultado) {
			case ConstantesTelas.CRIAR_SERVIDOR:
				verificarStatusServidor();
				break;
		}
	}
    
    @Override
    protected Dialog onCreateDialog(int id) {
		switch (id) {
        	case ConstantesTelas.DIALOG_SIM_NAO2:
                return new AlertDialog.Builder(this)
                    .setIcon(R.drawable.alerta_50)
                    .setTitle(R.string.dialog_tituloDialogSairPrograma)
                    .setPositiveButton(R.string.dialog_sim, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                        	finish();
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
    
    private class HandlePrincipal extends Handler {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
				case ConstantesDiversas.HD_ATUALIZAR_REDE:
					verificarStatusWifi();
					break;
				case ConstantesDiversas.HD_CONECTANDO_CLIENTE:
					Resources resources = Instancias.getContext().getResources();
		            Toast.makeText(TelaPrincipal.this, resources.getString(R.string.geral_clienteConectando), Toast.LENGTH_LONG).show();
					break;
				default:      
					break;
			}
		}
	}

}
