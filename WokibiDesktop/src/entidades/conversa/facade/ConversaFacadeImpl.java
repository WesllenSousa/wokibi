package entidades.conversa.facade;

import entidades.conversa.dao.ConversaDaoImpl;
import java.util.ArrayList;
import util.DaoException;
import util.FacadeException;

public class ConversaFacadeImpl implements ConversaFacade {

    private ConversaDaoImpl conversaDaoImpl;

    public ConversaFacadeImpl() {
        if (conversaDaoImpl == null) {
            conversaDaoImpl = new ConversaDaoImpl();
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
    public ArrayList<Object> listarConversasAtivas() throws FacadeException {
        try {
            return conversaDaoImpl.listarConversasAtivas();
        } catch (DaoException e) {
        }
        return null;
    }

    @Override
    public ArrayList<Object> listarConversasInativas() throws FacadeException {
        try {
            return conversaDaoImpl.listarConversasInativas();
        } catch (DaoException e) {
        }
        return null;
    }

    @Override
    public Object selecionarConversaPorDescricao(String descricao) throws FacadeException {
        try {
            return conversaDaoImpl.selecionarConversaPorDescricao(descricao);
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
