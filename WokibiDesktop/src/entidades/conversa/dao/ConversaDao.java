package entidades.conversa.dao;

import java.util.ArrayList;
import util.BaseDao;
import util.DaoException;

public interface ConversaDao extends BaseDao {
	
	public ArrayList<Object> listarConversasAtivas() throws DaoException;
	public ArrayList<Object> listarConversasInativas() throws DaoException;
	public Object selecionarConversaPorDescricao(String descricao) throws DaoException;
	public ArrayList<Object> listarTodasConversasAtivas() throws DaoException;
	public ArrayList<Object> listarTodasConversasInativas() throws DaoException;

}
