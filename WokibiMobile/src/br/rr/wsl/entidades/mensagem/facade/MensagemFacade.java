package br.rr.wsl.entidades.mensagem.facade;

import java.util.ArrayList;

import br.rr.wsl.util.BaseFacade;
import br.rr.wsl.util.FacadeException;

public interface MensagemFacade extends BaseFacade {

	public ArrayList<Object> listarMensagensPorConversa(Integer codigo) throws FacadeException;
	public ArrayList<Object> listarMensagensPorContato(Integer codigo) throws FacadeException;
	public ArrayList<Object> listarMensagensPorConversaOcultas(Integer codigo) throws FacadeException;
	
}
