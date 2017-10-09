package br.rr.wsl.telas;

import br.rr.wsl.R;
import br.rr.wsl.controle.utilitarios.ConstantesDiversas;
import br.rr.wsl.controle.utilitarios.Preferencias;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.ImageButton;

public class TelaOpcoes extends Activity implements OnClickListener {
	
	private ImageButton bt_voltar;
	
	private CheckBox ck_servicoServidor;
	private CheckBox ck_servicoSms;
	private CheckBox ck_audio;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_opcoes);
        
        bt_voltar = (ImageButton) findViewById(R.id.telaOpcoes_bt_voltar);
        bt_voltar.setOnClickListener(this);
        
        ck_servicoServidor = (CheckBox) findViewById(R.id.telaOpcoes_ck_servicoServidor);
        ck_servicoServidor.setOnClickListener(this);
        ck_servicoSms = (CheckBox) findViewById(R.id.telaOpcoes_ck_servicoSMS);
        ck_servicoSms.setOnClickListener(this);
        ck_audio = (CheckBox) findViewById(R.id.telaOpcoes_ck_audio);
        ck_audio.setOnClickListener(this);
        
        verificarPreferencias();
    }
	
	/* 
	 * EVENTOS
	 * */
	
	@Override
	public void onClick(View v) {
		if(v == bt_voltar) {
			finish();
		} else if(v == ck_servicoServidor) {
			if(ck_servicoServidor.isChecked()) servidor(true);
			else servidor(false);
		} else if(v == ck_servicoSms) {
			if(ck_servicoSms.isChecked()) sms(true);
			else sms(false);
		} else if(v == ck_audio) {
			if(ck_audio.isChecked()) audio(true);
			else audio(false);
		}
	}
	
	/* 
	 * AÇOES ESPECIFICAS
	 * */
	
	private void verificarPreferencias() {
		if(Preferencias.getServicoServidor(this)) ck_servicoServidor.setChecked(true);
		else ck_servicoServidor.setChecked(false);
		if(Preferencias.getServicoSms(this)) ck_servicoSms.setChecked(true);
		else ck_servicoSms.setChecked(false);
		if(Preferencias.getAudio(this)) ck_audio.setChecked(true);
		else ck_audio.setChecked(false);
	}
	
	private void servidor(Boolean valor) {
		SharedPreferences preferencias = getSharedPreferences(ConstantesDiversas.PF_NOME_PREFERENCIA, MODE_PRIVATE);
		SharedPreferences.Editor editor = preferencias.edit();
		editor.putBoolean(ConstantesDiversas.PF_SERVICO_SERVIDOR, valor);
		editor.commit();
	}
	
	private void sms(Boolean valor) {
		SharedPreferences preferencias = getSharedPreferences(ConstantesDiversas.PF_NOME_PREFERENCIA, MODE_PRIVATE);
		SharedPreferences.Editor editor = preferencias.edit();
		editor.putBoolean(ConstantesDiversas.PF_SERVICO_SMS, valor);
		editor.commit();
	}
	
	private void audio(Boolean valor) {
		SharedPreferences preferencias = getSharedPreferences(ConstantesDiversas.PF_NOME_PREFERENCIA, MODE_PRIVATE);
		SharedPreferences.Editor editor = preferencias.edit();
		editor.putBoolean(ConstantesDiversas.PF_AUDIO, valor);
		editor.commit();
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
