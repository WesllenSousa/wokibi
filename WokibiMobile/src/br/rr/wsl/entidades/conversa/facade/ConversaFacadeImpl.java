package br.rr.wsl.entidades.conversa.facade;

import java.util.ArrayList;

import android.content.Context;
import br.rr.wsl.entidades.conversa.dao.ConversaDaoImpl;
import br.rr.wsl.util.DaoException;
import br.rr.wsl.util.FacadeException;

public class ConversaFacadeImpl implements ConversaFacade {

	private ConversaDaoImpl conversaDaoImpl;
	
	public ConversaFacadeImpl(Context context) {
		if(conversaDaoImpl == null) {
			conversaDaoImpl = new ConversaDaoImpl(context);
		}
	}

	@Override
	public Object inserir(Object obj) throws FacadeException {
		try {
			return conversaDaoImpl.inserir(obj);
		} catch (DaoException e) {
		}
		return null;
	}

	@Override
	public Object inserirOuAlterar(Object obj) throws FacadeException {
		try {
			return conversaDaoImpl.inserirOuAlterar(obj);
		} catch (DaoException e) {
		}
		return null;
	}

	@Override
	public Object alterar(Object obj) throws FacadeException {
		try {
			return conversaDaoImpl.alterar(obj);
		} catch (DaoException e) {
		}
		return null;
	}

	@Override
	public Boolean excluir(Object obj) throws FacadeException {
		try {
			return conversaDaoImpl.excluir(obj);
		} catch (DaoException e) {
		}
		return null;
	}

	@Override
	public ArrayList<Object> listar() throws FacadeException {
		try {
			return conversaDaoImpl.listar();
		} catch (DaoException e) {
		}
		return null;
	}

	@Override
	public Object selecionar(Object obj) throws FacadeException {
		try {
			return conversaDaoImpl.selecionar(obj);
		} catch (DaoException e) {
		}
		return null;
	}

	@Override
	public ArrayList<Object> listarConversasAtivas(Integer clienteServidor) throws FacadeException {
		try {
			return conversaDaoImpl.listarConversasAtivas(clienteServidor);
		} catch (DaoException e) {
		}
		return null;
	}

	@Override
	public ArrayList<Object> listarConversasInativas(Integer clienteServidor) throws FacadeException {
		try {
			return conversaDaoImpl.listarConversasInativas(clienteServidor);
		} catch (DaoException e) {
		}
		return null;
	}

	@Override
	public Object selecionarConversaPorDescricao(String descricao, Integer clienteServidor) throws FacadeException {
		try {
			return conversaDaoImpl.selecionarConversaPorDescricao(descricao, clienteServidor);
		} catch (DaoException e) {
		}
		return null;
	}

	@Override
	public ArrayList<Object> listarTodasConversasAtivas() throws FacadeException {
		try {
			return conversaDaoImpl.listarTodasConversasAtivas();
		} catch (DaoException e) {
		}
		return null;
	}

	@Override
	public ArrayList<Object> listarTodasConversasInativas() throws FacadeException {
		try {
			return conversaDaoImpl.listarTodasConversasInativas();
		} catch (DaoException e) {
		}
		return null;
	}

}
