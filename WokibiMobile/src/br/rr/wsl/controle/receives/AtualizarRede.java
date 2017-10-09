package br.rr.wsl.controle.receives;

import br.rr.wsl.controle.utilitarios.ConstantesDiversas;
import br.rr.wsl.controle.utilitarios.Instancias;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;

public class AtualizarRede extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		if(Instancias.getHandlePrincipal() != null) {
			Bundle bundle = new Bundle();
	    	Message message = new Message();
	    	message.what = ConstantesDiversas.HD_ATUALIZAR_REDE;
	    	message.setData(bundle);
	    	Instancias.getHandlePrincipal().sendMessage(message);
		}
	}

}
