package br.rr.wsl.controle.servicos;

import java.util.ArrayList;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import br.rr.wsl.controle.clienteServidor.Cliente;
import br.rr.wsl.controle.utilitarios.ConstantesDiversas;

public class ServicoReceberSms extends Service{
	
	private static final String CATEGORIA = "wsl";
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
	}
	
	@Override
	public void onStart(Intent intent, int start) {
		super.onStart(intent, start);
		Bundle bundle = intent.getExtras();
        if(bundle != null) {
        	String mensagem = bundle.getString(ConstantesDiversas.BD_MENSAGEM);
        	Log.i(CATEGORIA, "SMS: "+mensagem);
        	conectarServidor(mensagem);
        }
	}
	

	@Override
	public void onDestroy() {
		super.onDestroy();
	}
	
	private void conectarServidor(final String mensagem) {
		ArrayList<String> palavras = separaMensagem(mensagem);
        final String ip = palavras.get(0);
        final String identificacaoConversa = palavras.get(1);
        final String descricaoConversa = palavras.get(2);
		
		new Thread() {
			@Override
			public void run() {
				Log.i(CATEGORIA, "SMS - Conectando ao servidor...");
				if(Integer.parseInt(identificacaoConversa) == 0) {
					//Nova conversa
					Cliente cliente = new Cliente(ip, Integer.parseInt(identificacaoConversa), descricaoConversa);
					cliente.getTratarMensagem().setHandler(null);
					Thread thread1 = new Thread(cliente);
					thread1.start();
				} else {
					//Associação de conversa
					Cliente c = new Cliente(ip, Integer.parseInt(identificacaoConversa), descricaoConversa);
					c.getTratarMensagem().setHandler(null);
					c.setIdentificacaoConversa(Integer.parseInt(identificacaoConversa));
					Thread thread2 = new Thread(c);
					thread2.start();
				}
			}
		}.start();    
	}
	
	public static ArrayList<String> separaMensagem(String mensagem) {
        ArrayList<String> palavras = new ArrayList<String>();
        String palavra = "";
        for(int i = 0; i < mensagem.length(); i++) {
            char c = mensagem.charAt(i);
            if(c == '-') {
                palavras.add(palavra);
                palavra = "";
                continue;
            }
            palavra += c;
        }
        palavras.add(palavra);
        return palavras;
    }

}
