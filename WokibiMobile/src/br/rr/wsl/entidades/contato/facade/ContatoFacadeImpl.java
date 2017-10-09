package br.rr.wsl.entidades.contato.facade;

import java.util.ArrayList;

import android.content.Context;
import br.rr.wsl.entidades.contato.dao.ContatoDaoImpl;
import br.rr.wsl.util.DaoException;
import br.rr.wsl.util.FacadeException;

public class ContatoFacadeImpl implements ContatoFacade {
	
	private ContatoDaoImpl contatoDaoImpl;
	
	public ContatoFacadeImpl(Context context) {
		if(contatoDaoImpl == null) {
			contatoDaoImpl = new ContatoDaoImpl(context);
		}
	}

	@Override
	public Object inserir(Object obj) throws FacadeException {
		try {
			return contatoDaoImpl.inserir(obj);
		} catch (DaoException e) {
		}
		return null;
	}

	@Override
	public Object inserirOuAlterar(Object obj) throws FacadeException {
		try {
			return contatoDaoImpl.inserirOuAlterar(obj);
		} catch (DaoException e) {
		}
		return null;
	}

	@Override
	public Object alterar(Object obj) throws FacadeException {
		try {
			return contatoDaoImpl.alterar(obj);
		} catch (DaoException e) {
		}
		return null;
	}

	@Override
	public Boolean excluir(Object obj) throws FacadeException {
		try {
			return contatoDaoImpl.excluir(obj);
		} catch (DaoException e) {
		}
		return null;
	}

	@Override
	public ArrayList<Object> listar() throws FacadeException {
		try {
			return contatoDaoImpl.listar();
		} catch (DaoException e) {
		}
		return null;
	}

	@Override
	public Object selecionar(Object obj) throws FacadeException {
		try {
			return contatoDaoImpl.selecionar(obj);
		} catch (DaoException e) {
		}
		return null;
	}

	@Override
	public Object selecionarContatoDono() throws FacadeException {
		try {
			return contatoDaoImpl.selecionarContatoDono();
		} catch (DaoException e) {
		}
		return null;
	}

	@Override
	public ArrayList<Object> listarAmigosOrdenadoPorNome() throws FacadeException {
		try {
			return contatoDaoImpl.listarAmigosOrdenadoPorNome();
		} catch (DaoException e) {
		}
		return null;
	}

	@Override
	public ArrayList<Object> listarContatosConectados() throws FacadeException {
		try {
			return contatoDaoImpl.listarContatosConectados();
		} catch (DaoException e) {
		}
		return null;
	}

	@Override
	public Object selecionarContatoPorNome(String nome) throws FacadeException {
		try {
			return contatoDaoImpl.selecionarContatoPorNome(nome);
		} catch (DaoException e) {
		}
		return null;
	}

	@Override
	public Object selecionarContatoAutor(String nome, Integer autor) throws FacadeException {
		try {
			return contatoDaoImpl.selecionarContatoAutor(nome, autor);
		} catch (DaoException e) {
		}
		return null;
	}

	@Override
	public ArrayList<Object> listarContatosDesconectados() throws FacadeException {
		try {
			return contatoDaoImpl.listarContatosDesconectados();
		} catch (DaoException e) {
		}
		return null;
	}

	@Override
	public ArrayList<Object> listarAmigosOrdenadoPorNome(String nome)throws FacadeException {
		try {
			return contatoDaoImpl.listarAmigosOrdenadoPorNome(nome);
		} catch (DaoException e) {
		}
		return null;
	}

}
