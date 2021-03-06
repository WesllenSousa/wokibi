package entidades.contato.facade;

import java.util.ArrayList;
import util.BaseFacade;
import util.FacadeException;

public interface ContatoFacade extends BaseFacade {
	
	public Object selecionarContatoDono() throws FacadeException;
	public ArrayList<Object> listarAmigosOrdenadoPorNome() throws FacadeException;
	public ArrayList<Object> listarContatosConectados() throws FacadeException;
	public Object selecionarContatoPorNome(String nome) throws FacadeException;
	public Object selecionarContatoAutor(String nome, Integer autor) throws FacadeException;
	public ArrayList<Object> listarContatosDesconectados() throws FacadeException;
        public ArrayList<Object> listarAmigosOrdenadoPorNome(String nome) throws FacadeException;
	
}
