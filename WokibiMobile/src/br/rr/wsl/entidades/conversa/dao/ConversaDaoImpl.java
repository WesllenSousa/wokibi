package br.rr.wsl.entidades.conversa.dao;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import br.rr.wsl.entidades.conversa.bean.ConversaBean;
import br.rr.wsl.util.Conexao;
import br.rr.wsl.util.DaoException;
import br.rr.wsl.util.DataUtil;

public class ConversaDaoImpl implements ConversaDao {
	
	private SQLiteDatabase db;
	private String TABELA = "conversas";
	public static String[] COLUNAS = new String[] {"_id","datahora","descricao","clienteservidor","deletar","status"};
	
	private Context context;
	
	public ConversaDaoImpl(Context context) {
		this.context = context;
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
		ConversaBean conversa = (ConversaBean) obj;	
		if(conversa.getCodigo() != null) {
			return alterar(conversa);
		} else {
			return inserir(conversa);
		}
	}

	@Override
	public Object inserir(Object obj) throws DaoException {
		abrirConexao();
		try {
			ConversaBean conversa = (ConversaBean) obj;
			
			ContentValues valores = new ContentValues();
			valores.put("datahora", DataUtil.formataDataHHmmSS(conversa.getDataHora())); 
			valores.put("descricao", conversa.getDescricao());  
			valores.put("clienteservidor", conversa.getClienteServidor());
			valores.put("deletar", conversa.getDeletar());
			valores.put("status", conversa.getStatus());
			
			Long id = db.insert(TABELA, "", valores);
			if(id != -1) {
				conversa.setCodigo(Integer.parseInt(id.toString()));			
				return conversa;
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
			ConversaBean conversa = (ConversaBean) obj;
			
			ContentValues valores = new ContentValues();
			valores.put("datahora", DataUtil.formataDataHHmmSS(conversa.getDataHora()));
			valores.put("descricao", conversa.getDescricao());
			valores.put("clienteservidor", conversa.getClienteServidor());
			valores.put("deletar", conversa.getDeletar());
			valores.put("status", conversa.getStatus());
			
			int linhasAfetadas = db.update(TABELA, valores, "_id=?", new String[] {conversa.getCodigo().toString()});
			if(linhasAfetadas > 0) {
				return conversa;
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
			ConversaBean conversa = (ConversaBean) obj;
			
			int linhasAfetadas = db.delete(TABELA, "_id=?", new String[] {conversa.getCodigo().toString()});
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
			ArrayList<Object> conversas = new ArrayList<Object>();
			Cursor cursor = db.query(TABELA, COLUNAS, 
					null, null, null, null, null);
			if(cursor.moveToFirst()) {
				do {
					ConversaBean conversa = new ConversaBean();
					conversa.setCodigo(cursor.getInt(0));
					conversa.setDataHora(DataUtil.formataDataCalendarHHmmSS(cursor.getString(1)));
					conversa.setDescricao(cursor.getString(2));
					conversa.setClienteServidor(cursor.getInt(3));
					conversa.setDeletar(cursor.getInt(4));
					conversa.setStatus(cursor.getInt(5));
					
					conversas.add(conversa);
				} while(cursor.moveToNext());	
			}
			cursor.close();
			return conversas;
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
			ConversaBean conversa = (ConversaBean) obj;
			Cursor cursor = db.query(true, TABELA, COLUNAS,"_id=?",
					new String[]{conversa.getCodigo().toString()}, null, null, null, null);
			if(cursor.getCount() > 0) {
				cursor.moveToFirst();
				
				conversa.setCodigo(cursor.getInt(0));
				conversa.setDataHora(DataUtil.formataDataCalendarHHmmSS(cursor.getString(1)));
				conversa.setDescricao(cursor.getString(2));
				conversa.setClienteServidor(cursor.getInt(3));
				conversa.setDeletar(cursor.getInt(4));
				conversa.setStatus(cursor.getInt(5));		
				
			}
			cursor.close();
			return conversa;
		} catch (SQLException e) {
			throw new DaoException();
		} finally {
			fecharConexao();
		}
	}
	
	@Override
	public ArrayList<Object> listarConversasAtivas(Integer clienteServidor) throws DaoException {
		abrirConexao();
		try {
			ArrayList<Object> conversas = new ArrayList<Object>();
			Cursor cursor = db.query(TABELA, COLUNAS, "status=? and deletar=? and clienteservidor=?", 
					new String[]{"0","0",clienteServidor.toString()}, null, null, null, null);
			if(cursor.moveToFirst()) {	
				do {
					ConversaBean conversa = new ConversaBean();
					conversa.setCodigo(cursor.getInt(0));
					conversa.setDataHora(DataUtil.formataDataCalendarHHmmSS(cursor.getString(1)));
					conversa.setDescricao(cursor.getString(2));
					conversa.setClienteServidor(cursor.getInt(3));
					conversa.setDeletar(cursor.getInt(4));
					conversa.setStatus(cursor.getInt(5));
					
					conversas.add(conversa);
				} while(cursor.moveToNext());
			}
			cursor.close();
			return conversas;
		} catch (SQLException e) {
			throw new DaoException();
		} finally {
			fecharConexao();
		}
	}
	
	@Override
	public ArrayList<Object> listarTodasConversasAtivas() throws DaoException {
		abrirConexao();
		try {
			ArrayList<Object> conversas = new ArrayList<Object>();
			Cursor cursor = db.query(TABELA, COLUNAS, "status=? and deletar=?", 
					new String[]{"0","0"}, null, null, null, null);
			if(cursor.moveToFirst()) {	
				do {
					ConversaBean conversa = new ConversaBean();
					conversa.setCodigo(cursor.getInt(0));
					conversa.setDataHora(DataUtil.formataDataCalendarHHmmSS(cursor.getString(1)));
					conversa.setDescricao(cursor.getString(2));
					conversa.setClienteServidor(cursor.getInt(3));
					conversa.setDeletar(cursor.getInt(4));
					conversa.setStatus(cursor.getInt(5));
					
					conversas.add(conversa);
				} while(cursor.moveToNext());
			}
			cursor.close();
			return conversas;
		} catch (SQLException e) {
			throw new DaoException();
		} finally {
			fecharConexao();
		}
	}
	
	@Override
	public ArrayList<Object> listarConversasInativas(Integer clienteServidor) throws DaoException {
		abrirConexao();
		try {
			ArrayList<Object> conversas = new ArrayList<Object>();
			Cursor cursor = db.query(TABELA, COLUNAS, "status=? and deletar=? and clienteservidor=?", 
					new String[]{"1","0",clienteServidor.toString()}, null, null, null, null);
			if(cursor.moveToFirst()) {	
				do {
					ConversaBean conversa = new ConversaBean();
					conversa.setCodigo(cursor.getInt(0));
					conversa.setDataHora(DataUtil.formataDataCalendarHHmmSS(cursor.getString(1)));
					conversa.setDescricao(cursor.getString(2));
					conversa.setClienteServidor(cursor.getInt(3));
					conversa.setDeletar(cursor.getInt(4));
					conversa.setStatus(cursor.getInt(5));
					
					conversas.add(conversa);
				} while(cursor.moveToNext());
			}
			cursor.close();
			return conversas;
		} catch (SQLException e) {
			throw new DaoException();
		} finally {
			fecharConexao();
		}
	}
	
	@Override
	public ArrayList<Object> listarTodasConversasInativas() throws DaoException {
		abrirConexao();
		try {
			ArrayList<Object> conversas = new ArrayList<Object>();
			Cursor cursor = db.query(TABELA, COLUNAS, "status=? and deletar=?", 
					new String[]{"1","0"}, null, null, null, null);
			if(cursor.moveToFirst()) {	
				do {
					ConversaBean conversa = new ConversaBean();
					conversa.setCodigo(cursor.getInt(0));
					conversa.setDataHora(DataUtil.formataDataCalendarHHmmSS(cursor.getString(1)));
					conversa.setDescricao(cursor.getString(2));
					conversa.setClienteServidor(cursor.getInt(3));
					conversa.setDeletar(cursor.getInt(4));
					conversa.setStatus(cursor.getInt(5));
					
					conversas.add(conversa);
				} while(cursor.moveToNext());
			}
			cursor.close();
			return conversas;
		} catch (SQLException e) {
			throw new DaoException();
		} finally {
			fecharConexao();
		}
	}
	
	@Override
	public Object selecionarConversaPorDescricao(String descricao, Integer clienteServidor) throws DaoException {
		abrirConexao();
		try {
			Cursor cursor = db.query(true, TABELA, COLUNAS,"descricao=? and clienteservidor=? and deletar=?",
					new String[]{descricao,clienteServidor.toString(),"0"}, null, null, null, null);
			if(cursor.getCount() > 0) {
				cursor.moveToFirst();
				
				ConversaBean conversa = new ConversaBean();
				conversa.setCodigo(cursor.getInt(0));
				conversa.setDataHora(DataUtil.formataDataCalendarHHmmSS(cursor.getString(1)));
				conversa.setDescricao(cursor.getString(2));
				conversa.setClienteServidor(cursor.getInt(3));
				conversa.setDeletar(cursor.getInt(4));
				conversa.setStatus(cursor.getInt(5));
				
				cursor.close();
				return conversa;
			}
		} catch (SQLException e) {
			throw new DaoException();
		} finally {
			fecharConexao();
		}
		return null;
	}

}
