package entidades.contato.dao;

import java.util.ArrayList;
import util.BaseDao;
import util.DaoException;

public interface ContatoDao extends BaseDao {
	
	public Object selecionarContatoDono() throws DaoException;
	public ArrayList<Object> listarAmigosOrdenadoPorNome() throws DaoException;
	public ArrayList<Object> listarContatosConectados() throws DaoException;
	public Object selecionarContatoPorNome(String nome) throws DaoException;
	public Object selecionarContatoAutor(String nome, Integer autor) throws DaoException;
	public ArrayList<Object> listarContatosDesconectados() throws DaoException;
        public ArrayList<Object> listarAmigosOrdenadoPorNome(String nome) throws DaoException;

}
