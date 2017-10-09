package br.rr.wsl.controle.utilitarios;

import java.util.HashMap;

import br.rr.wsl.controle.clienteServidor.Cliente;
import br.rr.wsl.controle.clienteServidor.Servidor;
import br.rr.wsl.entidades.contato.bean.ContatoBean;

import android.content.Context;
import android.os.Handler;

public class Instancias {
	
	private static Context context;
	private static Servidor servidor;
	private static HashMap<Integer, Cliente> conversasCliente = new HashMap<Integer, Cliente>();
	private static HashMap<Integer, Cliente> conversasServidor = new HashMap<Integer, Cliente>();
	private static ContatoBean dono;
	private static Handler handlePrincipal;

	public static Context getContext() {
		return context;
	}

	public static void setContext(Context context) {
		Instancias.context = context;
	}

	public static Servidor getServidor() {
		return servidor;
	}

	public static void setServidor(Servidor servidor) {
		Instancias.servidor = servidor;
	}

	public static HashMap<Integer, Cliente> getConversasCliente() {
		return conversasCliente;
	}

	public static void setConversasCliente(
			HashMap<Integer, Cliente> conversasCliente) {
		Instancias.conversasCliente = conversasCliente;
	}

	public static HashMap<Integer, Cliente> getConversasServidor() {
		return conversasServidor;
	}

	public static void setConversasServidor(
			HashMap<Integer, Cliente> conversasServidor) {
		Instancias.conversasServidor = conversasServidor;
	}

	public static ContatoBean getDono() {
		return dono;
	}

	public static void setDono(ContatoBean dono) {
		Instancias.dono = dono;
	}

	public static Handler getHandlePrincipal() {
		return handlePrincipal;
	}

	public static void setHandlePrincipal(Handler handlePrincipal) {
		Instancias.handlePrincipal = handlePrincipal;
	}
	
}
