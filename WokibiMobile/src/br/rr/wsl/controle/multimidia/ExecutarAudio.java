package br.rr.wsl.controle.multimidia;

import br.rr.wsl.R;
import br.rr.wsl.controle.utilitarios.Preferencias;
import android.content.Context;
import android.media.MediaPlayer;

public class ExecutarAudio {
	
	public static void servidorConectado(Context context) {
		if(Preferencias.getAudio(context)) {
			MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.servidor_criado);
	        mediaPlayer.start();
		}
	}
	
	public static void servidorDesconectado(Context context) {
		if(Preferencias.getAudio(context)) {
			MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.servidor_parado);
	        mediaPlayer.start();
		}
	}
	
	public static void clienteConectado(Context context) {
		if(Preferencias.getAudio(context)) {
			MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.cliente_conectado);
	        mediaPlayer.start();
		}
	}

	public static void clienteDesconectado(Context context) {
		if(Preferencias.getAudio(context)) {
			MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.cliente_desconectado);
	        mediaPlayer.start();
		}
	}
	
	public static void mensagem(Context context) {
		if(Preferencias.getAudio(context)) {
			MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.mensagem);
	        mediaPlayer.start();
		}
	}

}
