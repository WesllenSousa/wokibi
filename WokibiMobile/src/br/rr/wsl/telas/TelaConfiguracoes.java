package br.rr.wsl.telas;

import br.rr.wsl.R;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

public class TelaConfiguracoes extends Activity implements OnClickListener, OnItemClickListener {
	
	private String[] CONFIGURACOES;
	
	private ImageButton bt_inicio;
	
	private ListView lt_configuracoes;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_configuracoes);
        
        Resources resources = getResources();
        CONFIGURACOES = new String[]{
        		resources.getString(R.string.telaConfiguracoes_servidor), 
        		resources.getString(R.string.telaConfiguracoes_redes), 
        		resources.getString(R.string.telaConfiguracoes_senha),
        		resources.getString(R.string.telaConfiguracoes_opcao), 
        		resources.getString(R.string.telaConfiguracoes_ajuda), 
        		resources.getString(R.string.telaConfiguracoes_sobre)};
        
        bt_inicio = (ImageButton) findViewById(R.id.telaConfiguracoes_bt_inicio);
        bt_inicio.setOnClickListener(this);
        
        lt_configuracoes = (ListView) findViewById(R.id.telaConfiguracoes_lv_configuracoes);
        lt_configuracoes.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, CONFIGURACOES));
        lt_configuracoes.setOnItemClickListener(this);
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
		switch(posicao) {
			case 0: 
				Intent intent1 = new Intent(this, TelaCriarServidor.class);
				startActivity(intent1);
				break;
			case 1:
				Intent intent2 = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
				startActivity(intent2);
				break;
			case 2:
				Intent intent3 = new Intent(this, TelaCriarSenha.class);
				startActivity(intent3);
				break;
			case 3:
				Intent intent4 = new Intent(this, TelaOpcoes.class);
				startActivity(intent4);
				break;
			case 4:
				Intent intent5 = new Intent(this, TelaAjuda.class);
				startActivity(intent5);
				break;
			case 5:
				Intent intent6 = new Intent(this, TelaSobre.class);
				startActivity(intent6);
				break;
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
