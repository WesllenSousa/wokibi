package br.rr.wsl.controle.receives;

import br.rr.wsl.R;
import br.rr.wsl.controle.utilitarios.ConstantesDiversas;
import br.rr.wsl.controle.utilitarios.Instancias;
import br.rr.wsl.telas.TelaMensagens;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class ReceberMensagem extends BroadcastReceiver {
	
	private static final String CATEGORIA = "wsl";

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i(CATEGORIA, "Recebendo mensagem pelo receiver...");
		Bundle bundle = intent.getExtras();
		if(bundle != null) {
			String nomeCliente = bundle.getString(ConstantesDiversas.BD_NOME_CONTATO);
			String mensagem = bundle.getString(ConstantesDiversas.BD_MENSAGEM);
			Integer codigoConversa = bundle.getInt(ConstantesDiversas.BD_CODIGO_CONVERSA);
		
			criaNotificacaoMensagem(nomeCliente, mensagem, codigoConversa);		
		}	
	}
	
	private void criaNotificacaoMensagem(String titulo, String mensagem, Integer codigoConversa) {	
		String msgBarraStatus = mensagem;

		//Recupera servico de notificação
		NotificationManager notificationManager = (NotificationManager) Instancias.getContext().getSystemService(Instancias.getContext().NOTIFICATION_SERVICE);
		Notification notification = new Notification(R.drawable.logo_100, msgBarraStatus, System.currentTimeMillis());
		
		Intent intent = new Intent(Instancias.getContext(), TelaMensagens.class);
		intent.putExtra(ConstantesDiversas.BD_CODIGO_CONVERSA, codigoConversa);
		
		PendingIntent pendingIntent = PendingIntent.getActivity(Instancias.getContext(), 0, intent, 0);
		
		//Configura notificacao com uma determinada intent
		notification.setLatestEventInfo(Instancias.getContext(), titulo, mensagem, pendingIntent);
		
		//Espera 100ms e vibra por 250ms, depois espera por 100ms e vibra por 500ms
		notification.vibrate = new long[]{100,250,100,500};
		
		//vincula a notificacao ao servico de notificacao
		notificationManager.notify(R.string.nome_aplicacao, notification);   
	}

}
