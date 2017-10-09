package br.rr.wsl.controle.clienteServidor;

import java.io.IOException;
import java.io.ObjectInputStream;

import br.rr.wsl.controle.mensagens.TrataMensagem;
import br.rr.wsl.entidades.contato.bean.ContatoBean;
import br.rr.wsl.entidades.conversa.bean.ConversaBean;

import android.util.Log;

public class ClienteEntrada implements Runnable {
	
	private static final String categoria = "wsl";
	
	private ObjectInputStream entrada;
	
	private Servidor servidor;	
	private ClienteSaida clienteSaida;
	private TrataMensagem tratarMensagem;
	
	private ConversaBean conversa;
	private ContatoBean contato;
	
	private Integer identificacaoConversa;
	
	public ClienteEntrada(ClienteSaida clienteSaida, Servidor servidor) {
		tratarMensagem = new TrataMensagem();
		this.clienteSaida = clienteSaida;
		this.servidor = servidor;
	}
	
	@Override
    public void run() {
		configuraEntrada();
		configuraProcessa();
    }
	
	public void configuraEntrada() {
        try {
            entrada = new ObjectInputStream(clienteSaida.getSocket().getInputStream());   
        } catch (IOException ex) {
        	Log.e(categoria, "Cliente Entrada-configurar Entrada: "+ex);
        }
    }
	
	private void configuraProcessa(){
        try {
            do {
                String mensagem = entrada.readObject().toString();
                tratarMensagem.verificarMensagemServidor(this, mensagem);
            } while (servidor.getStatusConexao());
        } catch (IOException ex) {
        	Log.e(categoria, "Cliente Entrada-configurarProcessar: "+ex);
        } catch (ClassNotFoundException ex) {
        	Log.e(categoria, "Cliente Entrada=configurarProcessar: "+ex);
        } 
    }
	
	public void fecharConexao() {
        try { 
        	servidor.removerClienteConversa(identificacaoConversa, this);
        	tratarMensagem.desconectarConversa(conversa);
        	tratarMensagem.desconectarContato(contato);
        	entrada.close();
        	clienteSaida.getSaida().close();
        	clienteSaida.getSocket().close();
        } catch (IOException ex) {
        	Log.e(categoria, "Cliente Entrada-fecharConexao: "+ex);
        }
    }

	/*GETTERS SETTERS*/
	
	public Servidor getServidor() {
		return servidor;
	}

	public ClienteSaida getClienteSaida() {
		return clienteSaida;
	}

	public ConversaBean getConversa() {
		return conversa;
	}

	public void setConversa(ConversaBean conversa) {
		this.conversa = conversa;
	}

	public ContatoBean getContato() {
		return contato;
	}

	public void setContato(ContatoBean contato) {
		this.contato = contato;
	}

	public ObjectInputStream getEntrada() {
		return entrada;
	}

	public TrataMensagem getTratarMensagem() {
		return tratarMensagem;
	}

	public Integer getIdentificacaoConversa() {
		return identificacaoConversa;
	}

	public void setIdentificacaoConversa(Integer identificacaoConversa) {
		this.identificacaoConversa = identificacaoConversa;
		servidor.adicionarClienteConversa(identificacaoConversa, this);
	}
	
}
