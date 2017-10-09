package br.rr.wsl.controle.clienteServidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import br.rr.wsl.controle.multimidia.ExecutarAudio;
import br.rr.wsl.controle.utilitarios.ConstantesDiversas;
import br.rr.wsl.controle.utilitarios.Instancias;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;

public class Servidor implements Runnable {
	
	private static final String categoria = "wsl";
	
	private ServerSocket serverSocket;
    private HashMap<Integer, ArrayList<ClienteEntrada>> conversas;
    
    private Boolean statusConexao;
    
    public Servidor() {
    	conversas = new HashMap<Integer, ArrayList<ClienteEntrada>>();
        try {
            serverSocket = new ServerSocket(ConstantesDiversas.CS_PORTA);   
            statusConexao = true;
            ExecutarAudio.servidorConectado(Instancias.getContext()); 
            Instancias.setServidor(this);
            Log.i(categoria, "Servidor criado com sucesso!");
        } catch (IOException ex) {
        	Log.e(categoria, "Servidor: "+ex);
            statusConexao = false;
        }    
    }

	@Override
	public void run() {
		while (statusConexao) {            
            esperaConexoes();
        }  
	}
	
	private void esperaConexoes() {
        try {
            Socket socket = serverSocket.accept();   
            Log.i(categoria, "Conexão aceita: "+socket.getInetAddress().getHostAddress());
            
            //Notifica o usuário da conexão de um cliente
            Bundle bundle = new Bundle();
	    	Message message = new Message();
	    	message.what = ConstantesDiversas.HD_CONECTANDO_CLIENTE;
	    	message.setData(bundle);
	    	Instancias.getHandlePrincipal().sendMessage(message);
            
	    	//Prepara a saída dos dados
            ClienteSaida clienteSaida = new ClienteSaida(socket);
            Thread thread = new Thread(clienteSaida);
            thread.start(); 
            	
            //Prepara a entrada dos dados
            ClienteEntrada clienteEntrada = new ClienteEntrada(clienteSaida, this);
            Thread thread1 = new Thread(clienteEntrada);
            thread1.start();
            	
            ExecutarAudio.clienteConectado(Instancias.getContext());    
        } catch (IOException ex) {
        	Log.e(categoria, "Servidor-aceitarConexao: "+ex);
        }
    }
	
	public void enviarDados(Integer identificacaoConversa, Object dado) {
		if(conversas.get(identificacaoConversa) != null)
        for(ClienteEntrada clienteEntrada : conversas.get(identificacaoConversa)) {
        	clienteEntrada.getClienteSaida().enviarDados(dado);
        }
    }
    
    public void enviarDados(Integer identificacaoConversa, Object dado, Integer porta) {
        for(ClienteEntrada clienteEntrada : conversas.get(identificacaoConversa)) {
            if(clienteEntrada.getClienteSaida().getSocket().getPort() == porta) {
            	clienteEntrada.getClienteSaida().enviarDados(dado);
            }
        }
    }
    
    public void fechaConexao() { 	
        do {
	    	for(ArrayList<ClienteEntrada> clientesEntradas : conversas.values()) {
	    		Boolean status = false;
	        	for(ClienteEntrada clienteEntrada : clientesEntradas) {
	        		clienteEntrada.getClienteSaida().fechaConexao();
	        		clienteEntrada.fecharConexao();
	        		status = true;
	        		break;
	        	}
	        	if(status) break;
	        }
        } while(!conversas.values().isEmpty());
        try {
            serverSocket.close();
            statusConexao = false;
            Instancias.setServidor(null);
            ExecutarAudio.servidorDesconectado(Instancias.getContext());   
        } catch (IOException ex) {
        	Log.e(categoria, "Servidor-fecharConexao: "+ex);
        }
    }
	
	/*GETs*/
	public Boolean getStatusConexao() {
		return statusConexao;
	}
 
	public HashMap<Integer, ArrayList<ClienteEntrada>> getConversas() {
		return conversas;
	}

	public void adicionarClienteConversa(Integer identificacaoConversa, ClienteEntrada clienteEntrada) {
		if(!conversas.containsKey(identificacaoConversa)) {
			ArrayList<ClienteEntrada> ce = new ArrayList<ClienteEntrada>();
			conversas.put(identificacaoConversa, ce);
		} 
		ArrayList<ClienteEntrada> clientesEntrada = conversas.get(identificacaoConversa);    
		if(!clientesEntrada.contains(clienteEntrada)) {
			clientesEntrada.add(clienteEntrada);
			Log.i(categoria, "Servidor adicionou o cliente a identificação da conversa: "+identificacaoConversa);
		}
	}
	
	public void removerClienteConversa(Integer identificacaoConversa, ClienteEntrada clienteEntrada) {	   
		ArrayList<ClienteEntrada> clientes = conversas.get(identificacaoConversa);
		if(clientes != null)
		for(ClienteEntrada ce : clientes) {
			if(ce.getClienteSaida().getSocket().getPort() == clienteEntrada.getClienteSaida().getSocket().getPort()) {
				clientes.remove(ce);
				Log.i(categoria, "Servidor removeu o cliente da identificação da conversa: "+identificacaoConversa);
				break;
			}
		}
		if(clientes != null && clientes.isEmpty()) {
			conversas.remove(identificacaoConversa);
			Log.i(categoria, "Servidor removeu a conversa: "+identificacaoConversa);
		}
	}
	
	public Integer gerarIdentificacao() {
		return conversas.size()+1;
	}

}

