package br.rr.wsl.telas;

import br.rr.wsl.R;
import br.rr.wsl.controle.contatos.Contato;
import br.rr.wsl.controle.contatos.RecuperarContatos;
import br.rr.wsl.controle.contatos.Telefone;
import br.rr.wsl.controle.utilitarios.ConstantesDiversas;
import br.rr.wsl.controle.utilitarios.ConstantesTelas;
import br.rr.wsl.controle.utilitarios.Instancias;
import br.rr.wsl.entidades.contato.bean.ContatoBean;
import br.rr.wsl.entidades.contato.facade.ContatoFacade;
import br.rr.wsl.entidades.contato.facade.ContatoFacadeImpl;
import br.rr.wsl.util.FacadeException;
import br.rr.wsl.util.ValidaTipos;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class TelaCadastroEditarContatos extends Activity implements OnClickListener{
	
	//private static final String CATEGORIA = "wsl";
	
	private ContatoFacade contatoFacade;

	private ImageButton bt_voltar;
	private ImageButton bt_salvar;
	private ImageButton bt_importar;
	
	private EditText et_nome;
	private EditText et_ddd;
	private EditText et_celular;
	private EditText et_email;
	
	private ContatoBean contatoEditar;
	private Integer dono;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_cadastroeditar_contatos);
        
        contatoFacade = (ContatoFacade) new ContatoFacadeImpl(this);
        
        bt_voltar = (ImageButton) findViewById(R.id.telaCadastroEditarContato_bt_voltar);
        bt_voltar.setOnClickListener(this);
        bt_salvar = (ImageButton) findViewById(R.id.telaCadastroEditarContato_bt_salvar);
        bt_salvar.setOnClickListener(this);
        bt_importar = (ImageButton) findViewById(R.id.telaCadastroEditarContato_bt_importar);
        bt_importar.setOnClickListener(this);
        
        et_nome = (EditText) findViewById(R.id.telaCadastroEditarContato_et_nome);
        et_ddd = (EditText) findViewById(R.id.telaCadastroEditarContato_et_ddd);
        et_celular = (EditText) findViewById(R.id.telaCadastroEditarContato_et_celular); 
        et_email = (EditText) findViewById(R.id.telaCadastroEditarContato_et_email);  
        
        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
        	contatoEditar = new ContatoBean();
        	
        	Integer codigo = bundle.getInt(ConstantesDiversas.BD_CODIGO_CONTATO);
        	if(codigo != null && codigo != 0) {
        		contatoEditar.setCodigo(codigo);
        		ContatoBean contato = selecionar(contatoEditar);
        		preencherCampos(contato);
        	}
        	
        	dono = bundle.getInt(ConstantesDiversas.BD_DONO);
        	if(dono != null && dono != 0) {
        		contatoEditar.setAutor(0);
        	}
        }
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
		} else if(v == bt_importar) {
			seguirParaTelaContatos();
		}
	}
	
	/* 
	 * AÇOES ESPECIFICAS
	 * */
	
	private Boolean validaCampos() {
		Resources resources = getResources();
		if(ValidaTipos.stringVazia(et_nome.getText().toString())) {
			Toast.makeText(this, resources.getString(R.string.telaCadastroEditarContato_msg_nome), Toast.LENGTH_SHORT).show();
			return false;
		} else if(!ValidaTipos.isIntegerNumber(et_ddd.getText().toString())) {
			Toast.makeText(this, resources.getString(R.string.telaCadastroEditarContato_msg_ddd), Toast.LENGTH_SHORT).show();
			return false;
		} else if(!ValidaTipos.isIntegerNumber(et_celular.getText().toString())) {
			Toast.makeText(this, resources.getString(R.string.telaCadastroEditarContato_msg_celular), Toast.LENGTH_SHORT).show();
			return false;
		} else if(ValidaTipos.stringVazia(et_email.getText().toString()) && 
				!ValidaTipos.isEmail(et_email.getText().toString())) {
			Toast.makeText(this, resources.getString(R.string.telaCadastroEditarContato_msg_email), Toast.LENGTH_SHORT).show();
			return false;
		} else if(selecionarContatoPorNome(et_nome.getText().toString()) != null) {
			if(contatoEditar != null && contatoEditar.getNome().equals(et_nome.getText().toString())) {
				return true;
			} else {
				Toast.makeText(this, resources.getString(R.string.telaCadastroEditarContato_msg_nomeExiste), Toast.LENGTH_SHORT).show();
				return false;
			}
		}
		return true;
	}
	
	private ContatoBean populaContatoBean() {
		ContatoBean contato = new ContatoBean();
		
		if(contatoEditar != null && contatoEditar.getCodigo() != null)	contato.setCodigo(contatoEditar.getCodigo());
		if(contatoEditar != null && contatoEditar.getAutor() != null) contato.setAutor(contatoEditar.getAutor());
		else contato.setAutor(1); //1 : não é autor	
		if(contatoEditar != null && contatoEditar.getStatus() != null) contato.setStatus(contatoEditar.getStatus());
		else contato.setStatus(1); //1 : desconectado
		contato.setNome(et_nome.getText().toString());
		contato.setDdd(Integer.parseInt(et_ddd.getText().toString()));
		contato.setCelular(Integer.parseInt(et_celular.getText().toString()));
		contato.setEmail(et_email.getText().toString());
		contato.setDeletar(0);
			
		return contato;
	}
	
	private void salvar() {
		if(validaCampos()) {
			ContatoBean contato = inserirOuAlterar(populaContatoBean());
			if(contato != null) {
				Resources resources = getResources();
				Toast.makeText(this, resources.getString(R.string.telaMensagem_msg_salvar), Toast.LENGTH_SHORT).show();
				if(dono != null && dono != 0) {
					Instancias.setDono(contato);
					seguirParaTelaPrincipal();
				} else {
					setResult(ConstantesTelas.EDITAR_EXCLUIR, new Intent());
				}
				finish(); 
			}
		}
	}
	 
	private void preencherCampos(ContatoBean contato) {
		et_nome.setText(contato.getNome().toString());
		et_ddd.setText(contato.getDdd().toString());
		et_celular.setText(contato.getCelular().toString());
		et_email.setText(contato.getEmail().toString());
	}
	
	private void seguirParaTelaPrincipal() {
		Intent intent = new Intent(this, TelaPrincipal.class);
		startActivity(intent);
	}
	
	private void seguirParaTelaContatos() {
		Uri uri = Uri.parse(ConstantesDiversas.URI_CONTATOS);
		Intent intent = new Intent(Intent.ACTION_PICK, uri);
		startActivityForResult(intent, ConstantesTelas.CONTATO_NATIVO);
	}
	
	private void importarContatos(Intent it) {
		RecuperarContatos r = new RecuperarContatos(this);
		Contato contato = r.getContato(it.getData());
		if(contato != null) {
			et_nome.setText(contato.getNome());
			if(contato.getTelefones() != null)
			for(Telefone t : contato.getTelefones()) {
				verificaCelular(t.getTelefone());
				break;
			}
		} 
	}
	
	private void verificaCelular(String telefone) {
		int tamanho = telefone.length();
        String ddd = "";
        String tel ="";
        if(tamanho == 13) {
            ddd = telefone.substring(3, 5);
            tel = telefone.substring(5, 13);
        } else if(tamanho == 11) {
            ddd = telefone.substring(1, 3);
            tel = telefone.substring(3, 11);
        } else if(tamanho == 10) {
            ddd = telefone.substring(0, 2);
            tel = telefone.substring(2, 10);
        } else {
        	tel = telefone;
        }
        et_ddd.setText(ddd);
        et_celular.setText(tel);
	}
	
	/* 
	 * BANCO DE DADOS
	 * */
	
	private ContatoBean selecionar(ContatoBean contato) {
		try {
			return (ContatoBean) contatoFacade.selecionar(contato);
		} catch (FacadeException e) {
		}
		return null;
	}
	
	private ContatoBean inserirOuAlterar(ContatoBean contato) {
		try {
			return (ContatoBean) contatoFacade.inserirOuAlterar(contato);
		} catch (FacadeException e) {
		}
		return null;
	}
	
	private ContatoBean selecionarContatoPorNome(String nome) {
		try {
			return (ContatoBean) contatoFacade.selecionarContatoPorNome(nome);
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
	protected void onActivityResult(int codigo, int resultado, Intent it) {
		switch (resultado) {
			case -1:
				if(it == null) return;
				importarContatos(it);
				break;
		}
	}

}
