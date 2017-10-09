package br.rr.wsl.telas;

import java.util.ArrayList;

import br.rr.wsl.R;
import br.rr.wsl.controle.clienteServidor.Servidor;
import br.rr.wsl.controle.utilitarios.ConstantesDiversas;
import br.rr.wsl.controle.utilitarios.ConstantesTelas;
import br.rr.wsl.controle.utilitarios.Instancias;
import br.rr.wsl.controle.utilitarios.Preferencias;
import br.rr.wsl.entidades.contato.bean.ContatoBean;
import br.rr.wsl.entidades.contato.facade.ContatoFacade;
import br.rr.wsl.entidades.contato.facade.ContatoFacadeImpl;
import br.rr.wsl.entidades.conversa.bean.ConversaBean;
import br.rr.wsl.entidades.conversa.facade.ConversaFacade;
import br.rr.wsl.entidades.conversa.facade.ConversaFacadeImpl;
import br.rr.wsl.util.Criptografia;
import br.rr.wsl.util.ExecutarScript;
import br.rr.wsl.util.FacadeException;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageButton;

public class TelaLogin extends Activity implements OnClickListener {
	
	//private static final String CATEGORIA = "wsl";
	
	private ConversaFacade conversaFacade;
	private ContatoFacade contatoFacade;
	
	private EditText et_senha;
	private ImageButton bt_logar;
	
	private String senhaAnterior;
	private ContatoBean dono;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_login);
        
        criarBancoDados();
        
		conversaFacade = new ConversaFacadeImpl(this);
        contatoFacade = (ContatoFacade) new ContatoFacadeImpl(this);
        
        senhaAnterior = Preferencias.getSenhaAnterior(this);

        et_senha = (EditText) findViewById(R.id.telaLogin_et_senha);
        
        bt_logar = (ImageButton) findViewById(R.id.telaLogin_bt_logar);
        bt_logar.setOnClickListener(this);
        
        Servidor servidor = Instancias.getServidor();
        if(servidor == null || (servidor != null && servidor.getStatusConexao() == false)) {
        	verificarStatusConversa();
        	verificarStatusContato();
    	}
        
        dono = selecionarContatoDono();
        if(dono != null) Instancias.setDono(dono);
        verificaSenha();
    }
	
	/* 
	 * EVENTOS
	 * */
	
	@Override
	public void onClick(View v) {
		if(v == bt_logar) {
			autenticar();
		} 
	}
	
	/* 
	 * AÇOES ESPECIFICAS
	 * */
	
	private void verificaSenha() {	
		if(senhaAnterior.equals("")) {
			verificarDono();
		}
	}
	
	private void autenticar() {
		String senha = Criptografia.MD5(et_senha.getText().toString());
		if(!senha.equals(senhaAnterior)) {
			Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
	        findViewById(R.id.telaLogin_et_senha).startAnimation(shake);
		} else {
			verificarDono();
		}
	}
	
	private void verificarDono() {
		if(dono == null) {
			showDialog(ConstantesTelas.DIALOG_INFORMACAO);
		} else {
			seguirParaTelaPrincipal();
		}	
	}
	
	private void verificarStatusConversa() {
    	for(Object conversa : listarConversasAtivas(0)) {
    		alterar((ConversaBean)conversa);
    	}
    	for(Object conversa : listarConversasAtivas(1)) {
    		alterar((ConversaBean)conversa);
    	}
    }
    
    private void verificarStatusContato() {
    	for(Object contato : listarContatosConectados()) {
    		alterar((ContatoBean)contato);
    	}
    }
	
	private void seguirParaTelaCadastro() {
		Intent intent = new Intent(this, TelaCadastroEditarContatos.class);
		intent.putExtra(ConstantesDiversas.BD_DONO, 1);
		startActivity(intent);
		finish();
	}
	
	private void seguirParaTelaPrincipal() {
		Intent intent = new Intent(this, TelaPrincipal.class);
		startActivity(intent);
		finish();
	}
	
	private void criarBancoDados() {
		ExecutarScript script = new ExecutarScript(this);
    	script.criarBancoDados();
 		script.abrirBanco();
	}
	
	/* 
	 * BANCO DE DADOS
	 * */
    
    public ArrayList<Object> listarConversasAtivas(Integer clienteServidor) {
    	try {
			return conversaFacade.listarConversasAtivas(clienteServidor);
		} catch (FacadeException e) {
		}
    	return null;
    }
    
    public ArrayList<Object> listarContatosConectados() {
    	try {
			return contatoFacade.listarContatosConectados();
		} catch (FacadeException e) {
		}
    	return null;
    }
    
    private void alterar(ContatoBean contato) {
    	try {
    		contato.setStatus(1);
			contatoFacade.alterar(contato);
		} catch (FacadeException e) {
		}
    }
    
    private void alterar(ConversaBean conversa) {
    	try {
    		conversa.setStatus(1);
			conversaFacade.alterar(conversa);
		} catch (FacadeException e) {
		}
    }
	
	private ContatoBean selecionarContatoDono() {
		try {
			return (ContatoBean) contatoFacade.selecionarContatoDono();
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
        	case ConstantesTelas.DIALOG_INFORMACAO:
                return new AlertDialog.Builder(this)
                    .setIcon(R.drawable.alerta_50)
                    .setTitle(R.string.dialog_aviso) 
                    .setMessage(R.string.dialog_tituloDialogDono)
                    .setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                        	seguirParaTelaCadastro();
                        }
                    })
                    .create();
		}
		return null;
	}

}
