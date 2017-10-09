package br.rr.wsl.entidades.mensagem.facade;

import java.util.ArrayList;

import android.content.Context;
import br.rr.wsl.entidades.mensagem.dao.MensagemDaoImpl;
import br.rr.wsl.util.DaoException;
import br.rr.wsl.util.FacadeException;

public class MensagemFacadeImpl implements MensagemFacade {
	
	private MensagemDaoImpl mensagemDaoImpl;
	
	public MensagemFacadeImpl(Context context) {
		if(mensagemDaoImpl == null) {
			mensagemDaoImpl = new MensagemDaoImpl(context);
		}
	}

	@Override
	public Object inserir(Object obj) throws FacadeException {
		try {
			return mensagemDaoImpl.inserir(obj);
		} catch (DaoException e) {
		}
		return null;
	}

	@Override
	public Object inserirOuAlterar(Object obj) throws FacadeException {
		try {
			return mensagemDaoImpl.inserirOuAlterar(obj);
		} catch (DaoException e) {
		}
		return null;
	}

	@Override
	public Object alterar(Object obj) throws FacadeException {
		try {
			return mensagemDaoImpl.alterar(obj);
		} catch (DaoException e) {
		}
		return null;
	}

	@Override
	public Boolean excluir(Object obj) throws FacadeException {
		try {
			return mensagemDaoImpl.excluir(obj);
		} catch (DaoException e) {
		}
		return null;
	}

	@Override
	public ArrayList<Object> listar() throws FacadeException {
		try {
			return mensagemDaoImpl.listar();
		} catch (DaoException e) {
		}
		return null;
	}

	@Override
	public Object selecionar(Object obj) throws FacadeException {
		try {
			return mensagemDaoImpl.selecionar(obj);
		} catch (DaoException e) {
		}
		return null;
	}

	@Override
	public ArrayList<Object> listarMensagensPorConversa(Integer codigo)	throws FacadeException {
		try {
			return mensagemDaoImpl.listarMensagensPorConversa(codigo);
		} catch (DaoException e) {
		}
		return null;
	}

	@Override
	public ArrayList<Object> listarMensagensPorContato(Integer codigo) throws FacadeException {
		try {
			return mensagemDaoImpl.listarMensagensPorContato(codigo);
		} catch (DaoException e) {
		}
		return null;
	}

	@Override
	public ArrayList<Object> listarMensagensPorConversaOcultas(Integer codigo) throws FacadeException {
		try {
			return mensagemDaoImpl.listarMensagensPorConversaOcultas(codigo);
		} catch (DaoException e) {
		}
		return null;
	}

}
