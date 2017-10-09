package br.rr.wsl.telas;

import java.util.ArrayList;

import br.rr.wsl.R;
import br.rr.wsl.controle.mensagens.Sms;
import br.rr.wsl.controle.utilitarios.ConstantesDiversas;
import br.rr.wsl.controle.utilitarios.ConstantesTelas;
import br.rr.wsl.controle.utilitarios.Preferencias;
import br.rr.wsl.entidades.contato.bean.ContatoBean;
import br.rr.wsl.entidades.contato.facade.ContatoFacade;
import br.rr.wsl.entidades.contato.facade.ContatoFacadeImpl;
import br.rr.wsl.util.FacadeException;
import br.rr.wsl.util.RedeUtil;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Html;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ImageButton;

public class TelaNovaConversaServidor extends Activity implements OnClickListener {
	
	//private static final String CATEGORIA = "wsl";
	
	private ContatoFacade contatoFacade;
	
	private ImageButton bt_voltar;
	private ImageButton bt_confirmar;
	
	private CheckBox ck_celular;
	private CheckBox ck_email;
	private ListView lv_contatos;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_novaconversa_servidor);
        
        contatoFacade = new ContatoFacadeImpl(this);
        
        bt_voltar = (ImageButton) findViewById(R.id.telaNovaConveraServidor_bt_voltar);
        bt_voltar.setOnClickListener(this);
        bt_confirmar = (ImageButton) findViewById(R.id.telaNovaConveraServidor_bt_confirmar);
        bt_confirmar.setOnClickListener(this);
        
        lv_contatos = (ListView) findViewById(R.id.telaNovaConveraServidor_lv_contatos);
        lv_contatos.setAdapter(new ArrayAdapter<Object>(this,
                android.R.layout.simple_list_item_multiple_choice, listarContatos()));
        
        ck_celular = (CheckBox) findViewById(R.id.telaNovaConveraServidor_ck_celular);
        ck_email = (CheckBox) findViewById(R.id.telaNovaConveraServidor_ck_email);
    }
	
	/* 
	 * EVENTOS
	 * */

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
		if(ck_celular.isChecked()) {
			if(Preferencias.getServicoSms(this)) {
				enviarSms();
			} else {
				Resources resources = getResources();
				Toast.makeText(this, resources.getString(R.string.telaNovaConversaServidor_msg_servicoMensagem), Toast.LENGTH_SHORT).show();
			}
		} 
		if(ck_email.isChecked()) {
			enviarEmail();
		} 
		if(!ck_celular.isChecked() && !ck_email.isChecked()) {
			Resources resources = getResources();
			Toast.makeText(this, resources.getString(R.string.telaNovaConversaServidor_msg_localizacao), Toast.LENGTH_SHORT).show();
		}
	}
	
	private void enviarSms() {
		Sms sms = new Sms();
		SparseBooleanArray selecionados = lv_contatos.getCheckedItemPositions();
		for(int i = 0; i < lv_contatos.getCount(); i++) {
			if(selecionados.get(i)) {
				ContatoBean contato = (ContatoBean) lv_contatos.getItemAtPosition(i);
				//ip - codigo de associamento - nome da conversa
				String mensagem = ConstantesDiversas.PADRAO_MENSAGEM+RedeUtil.obtemIp(this)+"-0-"+contato.getNome();
				sms.enviarSms(this, ConstantesDiversas.CODIGO_NUMERO+contato.getDdd()+contato.getCelular(), mensagem);
				Resources resources = getResources();
				Toast.makeText(this, resources.getString(R.string.telaAssociarContato_sms)+contato, Toast.LENGTH_SHORT).show();
			}
		}
		
		setResult(ConstantesTelas.NOVA_CONVERSA_SERVIDOR, new Intent());
		finish();
	}
	
	private void enviarEmail() {
		SparseBooleanArray selecionados = lv_contatos.getCheckedItemPositions();
		for(int i = 0; i < lv_contatos.getCount(); i++) {
			if(selecionados.get(i)) {
				ContatoBean contato = (ContatoBean) lv_contatos.getItemAtPosition(i);
				enviar(contato.getNome(), contato.getEmail());
			}
		}
		
		setResult(ConstantesTelas.NOVA_CONVERSA_SERVIDOR, new Intent());
		finish();
	}
	
	public void enviar(String nome, String email) {
		String[] recipients = new String[]{email};
		StringBuilder body = new StringBuilder();
		body.append("<p><b>Prezado(a) " + nome + ",</b><br/>Ip: "+RedeUtil.obtemIp(this));
		         
		Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
		emailIntent.setType("text/html");               
		emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "assunto");
		emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, recipients);
		emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, Html.fromHtml(body.toString()));
		startActivity(Intent.createChooser(emailIntent, "Localização"));
	}
	
	/* 
	 * BANCO DE DADOS
	 * */
	
	private ArrayList<Object> listarContatos() {
		try {
			return contatoFacade.listarAmigosOrdenadoPorNome();
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
