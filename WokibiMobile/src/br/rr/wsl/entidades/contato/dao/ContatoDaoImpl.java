package br.rr.wsl.entidades.contato.dao;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import br.rr.wsl.entidades.contato.bean.ContatoBean;
import br.rr.wsl.util.Conexao;
import br.rr.wsl.util.DaoException;

public class ContatoDaoImpl implements ContatoDao{
	
	private SQLiteDatabase db;
	private String TABELA = "contatos";
	public static String[] COLUNAS = new String[] {"_id","nome","ddd","celular","email","autor","deletar","status","ip","porta"};
	
	private Context context;
	
	public ContatoDaoImpl(Context context) {
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
		ContatoBean contato = (ContatoBean) obj;	
		if(contato.getCodigo() != null) {
			return alterar(contato);
		} else {
			return inserir(contato);
		}
	}

	@Override
	public Object inserir(Object obj) throws DaoException {
		abrirConexao();
		try {
			ContatoBean contato = (ContatoBean) obj;
			
			ContentValues valores = new ContentValues();
			valores.put("nome", contato.getNome());
			valores.put("ddd", contato.getDdd());
			valores.put("celular", contato.getCelular());
			valores.put("email", contato.getEmail());
			valores.put("autor", contato.getAutor());
			valores.put("deletar", contato.getDeletar());
			valores.put("status", contato.getStatus());
			valores.put("ip", contato.getIp());
			valores.put("porta", contato.getPorta());
	
			Long id = db.insert(TABELA, "", valores);
			
			if(id != -1) {
				contato.setCodigo(Integer.parseInt(id.toString()));
				return contato;
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
			ContatoBean contato = (ContatoBean) obj;
			
			ContentValues valores = new ContentValues();
			valores.put("nome", contato.getNome());
			valores.put("ddd", contato.getDdd());
			valores.put("celular", contato.getCelular());
			valores.put("email", contato.getEmail());
			valores.put("autor", contato.getAutor());
			valores.put("deletar", contato.getDeletar());
			valores.put("status", contato.getStatus());
			valores.put("ip", contato.getIp());
			valores.put("porta", contato.getPorta());
			
			int linhasAfetadas = db.update(TABELA, valores, "_id=?", new String[] {contato.getCodigo().toString()});
			
			if(linhasAfetadas > 0) {
				return contato;
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
			ContatoBean contato = (ContatoBean) obj;
			
			int linhasAfetadas = db.delete(TABELA, "_id=?", new String[] {contato.getCodigo().toString()});
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
			ArrayList<Object> contatos = new ArrayList<Object>();
			Cursor cursor = db.query(TABELA, COLUNAS, null, null, null, null, null);
			if(cursor.moveToFirst()) {	
				do {
					ContatoBean contato = new ContatoBean();
					contato.setCodigo(cursor.getInt(0));
					contato.setNome(cursor.getString(1));
					contato.setDdd(cursor.getInt(2));
					contato.setCelular(cursor.getInt(3));
					contato.setEmail(cursor.getString(4));
					contato.setAutor(cursor.getInt(5));
					contato.setDeletar(cursor.getInt(6));
					contato.setStatus(cursor.getInt(7));
					contato.setIp(cursor.getString(8));
					contato.setPorta(cursor.getInt(9));
					
					contatos.add(contato);
				} while(cursor.moveToNext());
			}
			cursor.close();
			return contatos;
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
			ContatoBean contato = (ContatoBean) obj;
			Cursor cursor = db.query(true, TABELA, COLUNAS,"_id=?",
					new String[]{contato.getCodigo().toString()}, null, null, null, null);
			if(cursor.getCount() > 0) {
				cursor.moveToFirst();
				
				contato.setCodigo(cursor.getInt(0));
				contato.setNome(cursor.getString(1));
				contato.setDdd(cursor.getInt(2));
				contato.setCelular(cursor.getInt(3));
				contato.setEmail(cursor.getString(4));
				contato.setAutor(cursor.getInt(5));
				contato.setDeletar(cursor.getInt(6));
				contato.setStatus(cursor.getInt(7));
				contato.setIp(cursor.getString(8));
				contato.setPorta(cursor.getInt(9));
				
			}
			
			cursor.close();
			return contato;
		} catch (SQLException e) {
			throw new DaoException();
		} finally {
			fecharConexao();
		}
	}

	@Override
	public Object selecionarContatoDono() throws DaoException {
		abrirConexao();
		try {
			Cursor cursor = db.query(true, TABELA, COLUNAS,"autor=? and deletar=?",
					new String[]{"0","0"}, null, null, null, null);
			if(cursor.getCount() > 0) {
				cursor.moveToFirst();
				
				ContatoBean contato = new ContatoBean();
				contato.setCodigo(cursor.getInt(0));
				contato.setNome(cursor.getString(1));
				contato.setDdd(cursor.getInt(2));
				contato.setCelular(cursor.getInt(3));
				contato.setEmail(cursor.getString(4));
				contato.setAutor(cursor.getInt(5));
				contato.setDeletar(cursor.getInt(6));
				contato.setStatus(cursor.getInt(7));
				contato.setIp(cursor.getString(8));
				contato.setPorta(cursor.getInt(9));
				
				cursor.close();
				return contato;
			}
		} catch (SQLException e) {
			throw new DaoException();
		} finally {
			fecharConexao();
		}
		return null;
	}

	@Override
	public ArrayList<Object> listarAmigosOrdenadoPorNome() throws DaoException {
		abrirConexao();
		try {
			ArrayList<Object> contatos = new ArrayList<Object>();
			Cursor cursor = db.query(TABELA, COLUNAS, "autor=? and deletar=?", 
					new String[]{"1","0"}, null, null, "nome ASC", null);
			if(cursor.moveToFirst()) {	
				do {
					ContatoBean contato = new ContatoBean();
					contato.setCodigo(cursor.getInt(0));
					contato.setNome(cursor.getString(1));
					contato.setDdd(cursor.getInt(2));
					contato.setCelular(cursor.getInt(3));
					contato.setEmail(cursor.getString(4));
					contato.setAutor(cursor.getInt(5));
					contato.setDeletar(cursor.getInt(6));
					contato.setStatus(cursor.getInt(7));
					contato.setIp(cursor.getString(8));
					contato.setPorta(cursor.getInt(9));
					
					contatos.add(contato);
				} while(cursor.moveToNext());
			}
			cursor.close();
			return contatos;
		} catch (SQLException e) {
			throw new DaoException();
		} finally {
			fecharConexao();
		}
	}
	
	@Override
	public ArrayList<Object> listarAmigosOrdenadoPorNome(String nome) throws DaoException {
		abrirConexao();
		try {
			ArrayList<Object> contatos = new ArrayList<Object>();
			Cursor cursor = db.query(TABELA, COLUNAS, "autor=? and deletar=? and nome like '"+nome+"%'", 
					new String[]{"1","0"}, null, null, "nome ASC", null);
			if(cursor.moveToFirst()) {	
				do {
					ContatoBean contato = new ContatoBean();
					contato.setCodigo(cursor.getInt(0));
					contato.setNome(cursor.getString(1));
					contato.setDdd(cursor.getInt(2));
					contato.setCelular(cursor.getInt(3));
					contato.setEmail(cursor.getString(4));
					contato.setAutor(cursor.getInt(5));
					contato.setDeletar(cursor.getInt(6));
					contato.setStatus(cursor.getInt(7));
					contato.setIp(cursor.getString(8));
					contato.setPorta(cursor.getInt(9));
					
					contatos.add(contato);
				} while(cursor.moveToNext());
			}
			cursor.close();
			return contatos;
		} catch (SQLException e) {
			throw new DaoException();
		} finally {
			fecharConexao();
		}
	}
	
	@Override
	public ArrayList<Object> listarContatosConectados() throws DaoException {
		abrirConexao();
		try {
			ArrayList<Object> contatos = new ArrayList<Object>();
			Cursor cursor = db.query(TABELA, COLUNAS, "status=? and deletar=? and autor=?", 
					new String[]{"0","0","1"}, null, null, "nome ASC", null);
			if(cursor.moveToFirst()) {	
				do {
					ContatoBean contato = new ContatoBean();
					contato.setCodigo(cursor.getInt(0));
					contato.setNome(cursor.getString(1));
					contato.setDdd(cursor.getInt(2));
					contato.setCelular(cursor.getInt(3));
					contato.setEmail(cursor.getString(4));
					contato.setAutor(cursor.getInt(5));
					contato.setDeletar(cursor.getInt(6));
					contato.setStatus(cursor.getInt(7));
					contato.setIp(cursor.getString(8));
					contato.setPorta(cursor.getInt(9));
					
					contatos.add(contato);
				} while(cursor.moveToNext());
			}
			cursor.close();
			return contatos;
		} catch (SQLException e) {
			throw new DaoException();
		} finally {
			fecharConexao();
		}
	}
	
	@Override
	public ArrayList<Object> listarContatosDesconectados() throws DaoException {
		abrirConexao();
		try {
			ArrayList<Object> contatos = new ArrayList<Object>();
			Cursor cursor = db.query(TABELA, COLUNAS, "status=? and deletar=? and autor=?", 
					new String[]{"1","0","1"}, null, null, "nome ASC", null);
			if(cursor.moveToFirst()) {	
				do {
					ContatoBean contato = new ContatoBean();
					contato.setCodigo(cursor.getInt(0));
					contato.setNome(cursor.getString(1));
					contato.setDdd(cursor.getInt(2));
					contato.setCelular(cursor.getInt(3));
					contato.setEmail(cursor.getString(4));
					contato.setAutor(cursor.getInt(5));
					contato.setDeletar(cursor.getInt(6));
					contato.setStatus(cursor.getInt(7));
					contato.setIp(cursor.getString(8));
					contato.setPorta(cursor.getInt(9));
					
					contatos.add(contato);
				} while(cursor.moveToNext());
			}
			cursor.close();
			return contatos;
		} catch (SQLException e) {
			throw new DaoException();
		} finally {
			fecharConexao();
		}
	}
	
	@Override
	public Object selecionarContatoPorNome(String nome) throws DaoException {
		abrirConexao();
		try {
			Cursor cursor = db.query(true, TABELA, COLUNAS,"nome=? and deletar=?", 
					new String[]{nome,"0"}, null, null, null, null);
			if(cursor.getCount() > 0) {
				cursor.moveToFirst();
				
				ContatoBean contato = new ContatoBean();
				contato.setCodigo(cursor.getInt(0));
				contato.setNome(cursor.getString(1));
				contato.setDdd(cursor.getInt(2));
				contato.setCelular(cursor.getInt(3));
				contato.setEmail(cursor.getString(4));
				contato.setAutor(cursor.getInt(5));
				contato.setDeletar(cursor.getInt(6));
				contato.setStatus(cursor.getInt(7));
				contato.setIp(cursor.getString(8));
				contato.setPorta(cursor.getInt(9));
				
				cursor.close();
				return contato;
			}
		} catch (SQLException e) {
			throw new DaoException();
		} finally {
			fecharConexao();
		}
		return null;
	}
	
	@Override
	public Object selecionarContatoAutor(String nome, Integer autor) throws DaoException {
		abrirConexao();
		try {
			Cursor cursor = db.query(true, TABELA, COLUNAS,"nome=? and deletar=? and autor=?", //Essa selecao não inclui o dono
					new String[]{nome,"0",autor.toString()}, null, null, null, null);
			if(cursor.getCount() > 0) {
				cursor.moveToFirst();
				
				ContatoBean contato = new ContatoBean();
				contato.setCodigo(cursor.getInt(0));
				contato.setNome(cursor.getString(1));
				contato.setDdd(cursor.getInt(2));
				contato.setCelular(cursor.getInt(3));
				contato.setEmail(cursor.getString(4));
				contato.setAutor(cursor.getInt(5));
				contato.setDeletar(cursor.getInt(6));
				contato.setStatus(cursor.getInt(7));
				contato.setIp(cursor.getString(8));
				contato.setPorta(cursor.getInt(9));
				
				cursor.close();
				return contato;
			}
		} catch (SQLException e) {
			throw new DaoException();
		} finally {
			fecharConexao();
		}
		return null;
	}

}
