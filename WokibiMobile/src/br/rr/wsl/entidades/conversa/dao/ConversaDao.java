package br.rr.wsl.entidades.conversa.dao;

import java.util.ArrayList;

import br.rr.wsl.util.BaseDao;
import br.rr.wsl.util.DaoException;

public interface ConversaDao extends BaseDao {
	
	public ArrayList<Object> listarConversasAtivas(Integer clienteServidor) throws DaoException;
	public ArrayList<Object> listarConversasInativas(Integer clienteServidor) throws DaoException;
	public Object selecionarConversaPorDescricao(String descricao, Integer clienteServidor) throws DaoException;
	public ArrayList<Object> listarTodasConversasAtivas() throws DaoException;
	public ArrayList<Object> listarTodasConversasInativas() throws DaoException;

}
