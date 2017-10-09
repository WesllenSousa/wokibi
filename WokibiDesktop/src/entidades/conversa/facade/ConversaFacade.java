package entidades.conversa.facade;

import java.util.ArrayList;
import util.BaseFacade;
import util.FacadeException;

public interface ConversaFacade extends BaseFacade {
	
	public ArrayList<Object> listarConversasAtivas() throws FacadeException;
	public ArrayList<Object> listarConversasInativas() throws FacadeException;
	public Object selecionarConversaPorDescricao(String descricao) throws FacadeException;
	public ArrayList<Object> listarTodasConversasAtivas() throws FacadeException;
	public ArrayList<Object> listarTodasConversasInativas() throws FacadeException;

}
