package br.rr.wsl.telas;

import br.rr.wsl.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class TelaSobre extends Activity implements OnClickListener {
	
	private ImageButton bt_voltar;
	
	@Override 
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_sobre);
        
        bt_voltar = (ImageButton) findViewById(R.id.telaSobre_bt_voltar);
        bt_voltar.setOnClickListener(this);
    }
	
	/* 
	 * EVENTOS
	 * */

	@Override
	public void onClick(View v) {
		if(v == bt_voltar) {
			finish();
		} 
	}
	
	/* 
	 * AÇOES ESPECIFICAS
	 * */
	
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
