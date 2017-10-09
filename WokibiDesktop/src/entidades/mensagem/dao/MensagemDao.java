package entidades.mensagem.dao;

import java.util.ArrayList;
import util.BaseDao;
import util.DaoException;

public interface MensagemDao extends BaseDao {

	public ArrayList<Object> listarMensagensPorConversa(Integer codigo) throws DaoException;
	public ArrayList<Object> listarMensagensPorContato(Integer codigo) throws DaoException;
	public ArrayList<Object> listarMensagensPorConversaOcultas(Integer codigo) throws DaoException;
	
}
