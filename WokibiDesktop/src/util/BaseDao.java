package util;

import java.util.ArrayList;

public interface BaseDao {
	
    public Object inserir (Object obj) throws DaoException;
    public Object inserirOuAlterar (Object obj) throws DaoException;
    public Object alterar (Object obj) throws DaoException;
    public Boolean excluir (Object obj) throws DaoException;
    public ArrayList<Object> listar() throws DaoException;
    public Object selecionar (Object obj) throws DaoException;
    
}

