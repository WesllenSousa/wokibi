package br.rr.wsl.controle.servicos;

import br.rr.wsl.controle.clienteServidor.Servidor;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class ServicoServidor extends Service implements InterfaceServicoServidor, Runnable {
	
	private static final String CATEGORIA = "wsl";
	
	private final IBinder conexao = new ServicoServidorBinder();
	
	private Servidor servidor;

	@Override
	public void run() {	
		servidor = new Servidor();
		Thread thread = new Thread(servidor);
		thread.start();
	}
	
	@Override
	public void onCreate() {
	}

	@Override
	public IBinder onBind(Intent intent) {
		return conexao;
	}
	
	@Override
	public void onStart(Intent intent, int start) {
		super.onStart(intent, start);
		Log.i(CATEGORIA, "Iniciando o serviço do servidor...");
		new Thread(this).start();
	}

	@Override
	public Servidor getServidor() {
		return servidor;
	}
	
	@Override
	public void pararServico() {
		servidor.fechaConexao();
		Log.i(CATEGORIA, "Serviço servidor parado.");
		stopSelf();
	}
	
	//Cria uma classe que extenda a Binder
	public class ServicoServidorBinder extends Binder {
		public InterfaceServicoServidor getServicoServidor() {
			return ServicoServidor.this;
		}
	}

}
