package br.rr.wsl.controle.utilitarios;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferencias {
	
	public static String getSenhaAnterior(Context context) {
		SharedPreferences preferencias = context.getSharedPreferences(ConstantesDiversas.PF_NOME_PREFERENCIA, 0);
		return preferencias.getString(ConstantesDiversas.PF_GUARDA_SENHA, "");
	}
	
	public static Boolean getServicoServidor(Context context) {
		SharedPreferences preferencias = context.getSharedPreferences(ConstantesDiversas.PF_NOME_PREFERENCIA, 0);
		return preferencias.getBoolean(ConstantesDiversas.PF_SERVICO_SERVIDOR, true);
	}
	
	public static Boolean getServicoSms(Context context) {
		SharedPreferences preferencias = context.getSharedPreferences(ConstantesDiversas.PF_NOME_PREFERENCIA, 0);
		return preferencias.getBoolean(ConstantesDiversas.PF_SERVICO_SMS, true);
	}
	
	public static Boolean getTelaMensagem(Context context) {
		SharedPreferences preferencias = context.getSharedPreferences(ConstantesDiversas.PF_NOME_PREFERENCIA, 0);
		return preferencias.getBoolean(ConstantesDiversas.PF_TELA_MENSAGEM, true);
	}
	
	public static Boolean getTelaConversa(Context context) {
		SharedPreferences preferencias = context.getSharedPreferences(ConstantesDiversas.PF_NOME_PREFERENCIA, 0);
		return preferencias.getBoolean(ConstantesDiversas.PF_TELA_CONVERSA, true);
	}
	
	public static Boolean getTelaPrincipal(Context context) {
		SharedPreferences preferencias = context.getSharedPreferences(ConstantesDiversas.PF_NOME_PREFERENCIA, 0);
		return preferencias.getBoolean(ConstantesDiversas.PF_TELA_PRINCIPAL, true);
	}
	
	public static Boolean getAudio(Context context) {
		SharedPreferences preferencias = context.getSharedPreferences(ConstantesDiversas.PF_NOME_PREFERENCIA, 0);
		return preferencias.getBoolean(ConstantesDiversas.PF_AUDIO, true);
	}

}
