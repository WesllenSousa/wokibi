package br.rr.wsl.entidades.mensagem.dao;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import br.rr.wsl.entidades.contato.bean.ContatoBean;
import br.rr.wsl.entidades.contato.dao.ContatoDaoImpl;
import br.rr.wsl.entidades.conversa.bean.ConversaBean;
import br.rr.wsl.entidades.conversa.dao.ConversaDaoImpl;
import br.rr.wsl.entidades.mensagem.bean.MensagemBean;
import br.rr.wsl.util.Conexao;
import br.rr.wsl.util.DaoException;
import br.rr.wsl.util.DataUtil;

public class MensagemDaoImpl implements MensagemDao {
	
	private SQLiteDatabase db;
	private String TABELA = "mensagens";
	public static String[] COLUNAS = new String[] {"_id","conversa","contato","datahora","texto", "deletar", "ocultar"};

	private Context context;
	
	private ConversaDaoImpl conversaDaoImpl;
	private ContatoDaoImpl contatoDaoImpl;
	
	public MensagemDaoImpl(Context context) {
		this.context = context;
		
		conversaDaoImpl = new ConversaDaoImpl(context);
		contatoDaoImpl = new ContatoDaoImpl(context);
	}
	
	private void abrirConexao() {
		db = Conexao.getConexao(context);
	}
	
	public void fecharConexao() {
		if(db != null) {
			db.close();
		}
	}
	
	@Override
	public Object inserirOuAlterar(Object obj) throws DaoException {
		MensagemBean mensagem = (MensagemBean) obj;	
		if(mensagem.getCodigo() != null) {
			return alterar(mensagem);
		} else {
			return inserir(mensagem);
		}
	}

	@Override
	public Object inserir(Object obj) throws DaoException {
		abrirConexao();
		try {
			MensagemBean mensagem = (MensagemBean) obj;
			
			ContentValues valores = new ContentValues();
			valores.put("conversa", mensagem.getConversa().getCodigo());
			valores.put("contato", mensagem.getContato().getCodigo());
			valores.put("datahora", DataUtil.formataDataHHmmSS(mensagem.getDataHora()));
			valores.put("texto", mensagem.getTexto());
			valores.put("deletar", mensagem.getDeletar());
			valores.put("ocultar", mensagem.getOcultar());
						
			Long id = db.insert(TABELA, "", valores);

			if(id != -1) {
				mensagem.setCodigo(Integer.parseInt(id.toString()));
				return mensagem;
			} 
		} catch (SQLException e) {
			throw new DaoException();
		} finally {
			fecharConexao();
		}
		return null;
	}

	@Override
	public Object alterar(Object obj) throws DaoException {
		abrirConexao();
		try {
			MensagemBean mensagem = (MensagemBean) obj;
			
			ContentValues valores = new ContentValues();
			valores.put("conversa", mensagem.getConversa().getCodigo());
			valores.put("contato", mensagem.getContato().getCodigo());
			valores.put("datahora", DataUtil.formataDataHHmmSS(mensagem.getDataHora()));
			valores.put("texto", mensagem.getTexto());
			valores.put("deletar", mensagem.getDeletar());
			valores.put("ocultar", mensagem.getOcultar());
			
			int linhasAfetadas = db.update(TABELA, valores, "_id=?", new String[] {mensagem.getCodigo().toString()});
			if(linhasAfetadas > 0) {
				return mensagem;
			} 
		} catch (SQLException e) {
			throw new DaoException();
		} finally {
			fecharConexao();
		}
		return null;
	}

	@Override
	public Boolean excluir(Object obj) throws DaoException {
		abrirConexao();
		try {
			MensagemBean mensagem = (MensagemBean) obj;
			
			int linhasAfetadas = db.delete(TABELA, "_id=?", new String[] {mensagem.getCodigo().toString()});
			if(linhasAfetadas > 0) {
				return true;
			} 
		} catch (SQLException e) {
			throw new DaoException();
		} finally {
			fecharConexao();
		}
		return false;
	}

	@Override
	public ArrayList<Object> listar() throws DaoException {
		abrirConexao();
		try {
			ArrayList<Object> mensagens = new ArrayList<Object>();
			Cursor cursor = db.query(TABELA, COLUNAS, 
					null, null, null, null, null);
			if(cursor.moveToFirst()) {
				do {
					MensagemBean mensagem = new MensagemBean();
					mensagem.setCodigo(cursor.getInt(0));
					mensagem.setConversa((ConversaBean)conversaDaoImpl.selecionar(cursor.getInt(1)));
					mensagem.setContato((ContatoBean)contatoDaoImpl.selecionar(cursor.getInt(2)));
					mensagem.setDataHora(DataUtil.formataDataCalendarHHmmSS(cursor.getString(3)));
					mensagem.setTexto(cursor.getString(4));
					mensagem.setDeletar(cursor.getInt(5));
					mensagem.setOcultar(cursor.getInt(6));
					
					mensagens.add(mensagem);
				} while(cursor.moveToNext());
			}
			cursor.close();
			return mensagens;
		} catch (SQLException e) {
			throw new DaoException();
		} finally {
			fecharConexao();
		}
	}

	@Override
	public Object selecionar(Object obj) throws DaoException {
		abrirConexao();
		try {
			MensagemBean mensagem = new MensagemBean();
			Cursor cursor = db.query(true, TABELA, COLUNAS,"_id=?",
					new String[]{mensagem.getCodigo().toString()}, null, null, null, null);
			if(cursor.getCount() > 0) {
				cursor.moveToFirst();
				
				mensagem.setCodigo(cursor.getInt(0));
				ConversaBean conversa = new ConversaBean();
				conversa.setCodigo(cursor.getInt(1));
				mensagem.setConversa((ConversaBean)conversaDaoImpl.selecionar(conversa));
				ContatoBean contato = new ContatoBean();
				contato.setCodigo(cursor.getInt(2));
				mensagem.setContato((ContatoBean)contatoDaoImpl.selecionar(contato));
				mensagem.setDataHora(DataUtil.formataDataCalendarHHmmSS(cursor.getString(3)));
				mensagem.setTexto(cursor.getString(4));
				mensagem.setDeletar(cursor.getInt(5));
				mensagem.setOcultar(cursor.getInt(6));
			}
			cursor.close();
			return mensagem;
		} catch (SQLException e) {
			throw new DaoException();
		} finally {
			fecharConexao();
		}
	}
	
	@Override
	public ArrayList<Object> listarMensagensPorConversa(Integer codigo) throws DaoException {
		abrirConexao();
		try {
			ArrayList<Object> mensagens = new ArrayList<Object>();
			Cursor cursor = db.query(TABELA, COLUNAS, "conversa=? and deletar=? and ocultar=?", 
					new String[]{codigo.toString(),"0","0"}, null, null, "_id DESC", null);
			if(cursor.moveToFirst()) {	
				do {
					MensagemBean mensagem = new MensagemBean();
					mensagem.setCodigo(cursor.getInt(0));
					ConversaBean conversa = new ConversaBean();
					conversa.setCodigo(cursor.getInt(1));
					mensagem.setConversa((ConversaBean)conversaDaoImpl.selecionar(conversa));
					ContatoBean contato = new ContatoBean();
					contato.setCodigo(cursor.getInt(2));
					mensagem.setContato((ContatoBean)contatoDaoImpl.selecionar(contato));
					mensagem.setDataHora(DataUtil.formataDataCalendarHHmmSS(cursor.getString(3)));
					mensagem.setTexto(cursor.getString(4));
					mensagem.setDeletar(cursor.getInt(5));
					mensagem.setOcultar(cursor.getInt(6));
					
					mensagens.add(mensagem);
				} while(cursor.moveToNext());
			}
			cursor.close();
			return mensagens;     
		} catch (SQLException e) {
			throw new DaoException();
		} finally {
			fecharConexao();
		}
	}
	
	@Override
	public ArrayList<Object> listarMensagensPorConversaOcultas(Integer codigo) throws DaoException {
		abrirConexao();
		try {
			ArrayList<Object> mensagens = new ArrayList<Object>();
			Cursor cursor = db.query(TABELA, COLUNAS, "conversa=? and deletar=?", 
					new String[]{codigo.toString(),"0"}, null, null, "_id DESC", null);
			if(cursor.moveToFirst()) {	
				do {
					MensagemBean mensagem = new MensagemBean();
					mensagem.setCodigo(cursor.getInt(0));
					ConversaBean conversa = new ConversaBean();
					conversa.setCodigo(cursor.getInt(1));
					mensagem.setConversa((ConversaBean)conversaDaoImpl.selecionar(conversa));
					ContatoBean contato = new ContatoBean();
					contato.setCodigo(cursor.getInt(2));
					mensagem.setContato((ContatoBean)contatoDaoImpl.selecionar(contato));
					mensagem.setDataHora(DataUtil.formataDataCalendarHHmmSS(cursor.getString(3)));
					mensagem.setTexto(cursor.getString(4));
					mensagem.setDeletar(cursor.getInt(5));
					mensagem.setOcultar(cursor.getInt(6));
					
					mensagens.add(mensagem);
				} while(cursor.moveToNext());
			}
			cursor.close();
			return mensagens;     
		} catch (SQLException e) {
			throw new DaoException();
		} finally {
			fecharConexao();
		}
	}
	
	@Override
	public ArrayList<Object> listarMensagensPorContato(Integer codigo) throws DaoException {
		abrirConexao();
		try {
			ArrayList<Object> mensagens = new ArrayList<Object>();
			Cursor cursor = db.query(TABELA, COLUNAS, "contato=? and deletar=?", 
					new String[]{codigo.toString(),"0"}, null, null, "_id ASC", null);
			if(cursor.moveToFirst()) {	
				do {
					MensagemBean mensagem = new MensagemBean();
					mensagem.setCodigo(cursor.getInt(0));
					ConversaBean conversa = new ConversaBean();
					conversa.setCodigo(cursor.getInt(1));
					mensagem.setConversa((ConversaBean)conversaDaoImpl.selecionar(conversa));
					ContatoBean contato = new ContatoBean();
					contato.setCodigo(cursor.getInt(2));
					mensagem.setContato((ContatoBean)contatoDaoImpl.selecionar(contato));
					mensagem.setDataHora(DataUtil.formataDataCalendarHHmmSS(cursor.getString(3)));
					mensagem.setTexto(cursor.getString(4));
					mensagem.setDeletar(cursor.getInt(5));
					mensagem.setOcultar(cursor.getInt(6));
					
					mensagens.add(mensagem);
				} while(cursor.moveToNext());
			}
			cursor.close();
			return mensagens;     
		} catch (SQLException e) {
			throw new DaoException();
		} finally {
			fecharConexao();
		}
	}

}
