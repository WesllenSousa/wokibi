package br.rr.wsl.controle.clienteServidor;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import br.rr.wsl.controle.utilitarios.ConstantesDiversas;

import android.util.Log;

public class ClienteSaida implements Runnable {
	
	private static final String categoria = "wsl";
	
	private ObjectOutputStream saida;
    private Socket socket;
    
    public ClienteSaida(Socket socket) {
    	this.socket = socket;
    }

	@Override
	public void run() {
		configurarSaida();
	}
	
	private void configurarSaida() {
        try {
            saida = new ObjectOutputStream(socket.getOutputStream());
            saida.flush();
        } catch (IOException ex) {
        	Log.e(categoria, "Cliente Saída-configurarSaida: "+ex);
        }
    }
	
	public void enviarDados(Object dado) {
        try {
            saida.writeObject(dado);
            saida.flush();
        } catch (IOException ex) {
        	Log.e(categoria, "Cliente Saída-enviarDados: "+ex);
        }
    } 
	
	public void fechaConexao() {
        enviarDados(ConstantesDiversas.CS_FIM);
    }
	
	/* GETs */

	public Socket getSocket() {
		return socket;
	}

	public ObjectOutputStream getSaida() {
		return saida;
	}

}
