package br.rr.wsl.telas;

import br.rr.wsl.R;
import br.rr.wsl.controle.utilitarios.ConstantesDiversas;
import br.rr.wsl.util.Criptografia;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class TelaCriarSenha extends Activity implements OnClickListener {
	
	private ImageButton bt_voltar;
	private ImageButton bt_salvar;
	
	private EditText et_senhaAnterior;
	private EditText et_novaSenha;
	private EditText et_repetirSenha;
	
	private String senhaAnterior;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_criar_senha);
        
        SharedPreferences preferencias = getSharedPreferences(ConstantesDiversas.PF_NOME_PREFERENCIA, 0);
		senhaAnterior = preferencias.getString(ConstantesDiversas.PF_GUARDA_SENHA, "");
        
        bt_voltar = (ImageButton) findViewById(R.id.telaCriarSenha_bt_voltar);
        bt_voltar.setOnClickListener(this);
        bt_salvar = (ImageButton) findViewById(R.id.telaCriarSenha_bt_salvar);
        bt_salvar.setOnClickListener(this);
        
        TextView tv_senhaAnterior = (TextView) findViewById(R.id.telaCriarSenha_tv_senhaAnterior);
        et_senhaAnterior = (EditText) findViewById(R.id.telaCriarSenha_et_senhaAnterior);
        if(senhaAnterior.equals("")) {
        	et_senhaAnterior.setVisibility(View.INVISIBLE);
        	tv_senhaAnterior.setVisibility(View.INVISIBLE);
        }
        et_novaSenha = (EditText) findViewById(R.id.telaCriarSenha_et_novaSenha);
        et_repetirSenha = (EditText) findViewById(R.id.telaCriarSenha_et_repetirSenha);
    }
	
	/* 
	 * EVENTOS
	 * */

	@Override
	public void onClick(View v) {
		if(v == bt_voltar) {
			finish();
		} else if(v == bt_salvar) {
			salvar();
		}
	}
	
	/* 
	 * AÇOES ESPECIFICAS
	 * */
	
	private Boolean validar() {
		String s;
		if(senhaAnterior.equals("")) s = "";
		else s = Criptografia.MD5(et_senhaAnterior.getText().toString());
		if(s.equals(senhaAnterior)) {
			if(et_novaSenha.getText().toString().equals(et_repetirSenha.getText().toString())) {
				return true;
			} else {
				Resources resources = getResources();
				Toast.makeText(this, resources.getString(R.string.telaCriarSenha_msg_senhasNaoCorrespondem), Toast.LENGTH_SHORT).show();
				return false;
			}
		} else {
			Resources resources = getResources();
			Toast.makeText(this, resources.getString(R.string.telaCriarSenha_msg_senhaInvalida), Toast.LENGTH_SHORT).show();
			return false;
		}
	}
	
	private void salvar() {
		if(validar()) {
			SharedPreferences preferencias = getSharedPreferences(ConstantesDiversas.PF_NOME_PREFERENCIA, MODE_PRIVATE);
			SharedPreferences.Editor editor = preferencias.edit();
			if(!et_novaSenha.getText().toString().equals(""))
				editor.putString(ConstantesDiversas.PF_GUARDA_SENHA, Criptografia.MD5(et_novaSenha.getText().toString()));
			else
				editor.putString(ConstantesDiversas.PF_GUARDA_SENHA, "");
			editor.commit();
			Resources resources = getResources();
			Toast.makeText(this, resources.getString(R.string.telaCriarSenha_msg_alterada), Toast.LENGTH_SHORT).show();
			finish();
		}
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
