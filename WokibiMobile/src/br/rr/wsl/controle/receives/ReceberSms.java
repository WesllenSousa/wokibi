package br.rr.wsl.controle.receives;

import java.util.Calendar;

import br.rr.wsl.controle.mensagens.Sms;
import br.rr.wsl.controle.utilitarios.ConstantesDiversas;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.util.Log;

public class ReceberSms extends BroadcastReceiver{
	
	private static final String CATEGORIA = "wsl";

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i(CATEGORIA, "SMS - Recebendo mensagem");
		
		Sms sms = new Sms();
		SmsMessage msg = sms.receberMensagem(intent);
		
		String mensagem = msg.getDisplayMessageBody();
		
		if(mensagem.length() > 3 && mensagem.substring(0, 4).equals(ConstantesDiversas.PADRAO_MENSAGEM)) {
			ativarServido(context, retiraMensagem(mensagem));
        }
	}
	
	private void ativarServido(Context context, String mensagem) {	
		Intent intent = new Intent(ConstantesDiversas.SV_SERVICO_SMS);
		intent.putExtra(ConstantesDiversas.BD_MENSAGEM, mensagem);
		
		//Recuperando o servico
		PendingIntent pendingServico = PendingIntent.getService(context, 0, intent, 0);
		
		//Prepara os 15 segundos
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(System.currentTimeMillis());
		c.add(Calendar.SECOND, 2);
		
		//Inicia alarme (agendamento para o inicialização do serviço)
		AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		alarmManager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingServico);
		
		Log.i(CATEGORIA, "SMS - Mensagem recebida");
	}
	
	public String retiraMensagem(String mensagem) {
        String ip = "";
        Boolean status = false;
        for(int i = 0; i < mensagem.length(); i++) {
            char c = mensagem.charAt(i);
            if(c == ':') {
                status = true;
                continue;
            }
            if(status) ip += c;
        }
        return ip;
    }

}
