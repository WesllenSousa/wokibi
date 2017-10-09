package br.rr.wsl.entidades.conversa.facade;

import java.util.ArrayList;

import br.rr.wsl.util.BaseFacade;
import br.rr.wsl.util.FacadeException;

public interface ConversaFacade extends BaseFacade {
	
	public ArrayList<Object> listarConversasAtivas(Integer clienteServidor) throws FacadeException;
	public ArrayList<Object> listarConversasInativas(Integer clienteServidor) throws FacadeException;
	public Object selecionarConversaPorDescricao(String descricao, Integer clienteServidor) throws FacadeException;
	public ArrayList<Object> listarTodasConversasAtivas() throws FacadeException;
	public ArrayList<Object> listarTodasConversasInativas() throws FacadeException;

}
