package br.rr.wsl.util;

import java.util.ArrayList;

public interface BaseFacade {
	
    public Object inserir (Object obj) throws FacadeException;
    public Object inserirOuAlterar (Object obj) throws FacadeException;
    public Object alterar (Object obj) throws FacadeException;
    public Boolean excluir (Object obj) throws FacadeException;
    public ArrayList<Object> listar() throws FacadeException;
    public Object selecionar (Object obj) throws FacadeException;
    
}

