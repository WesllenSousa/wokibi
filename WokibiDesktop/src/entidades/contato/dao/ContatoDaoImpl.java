package entidades.contato.dao;

import entidades.contato.bean.ContatoBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import recursos.sqls.Sqls;
import util.Conexao;
import util.DaoException;

public class ContatoDaoImpl implements ContatoDao {

    public ContatoDaoImpl() {
    }

    @Override
    public Object inserirOuAlterar(Object obj) throws DaoException {
        ContatoBean contato = (ContatoBean) obj;
        if (contato.getCodigo() != null) {
            return alterar(contato);
        } else {
            return inserir(contato);
        }
    }

    @Override
    public Object inserir(Object obj) throws DaoException {
        Connection con = Conexao.getConexao();
        PreparedStatement pstm = null;
        try {
            ContatoBean contato = (ContatoBean) obj;
       
            pstm = con.prepareStatement(Sqls.contato_inserir());
            pstm.setString(1, contato.getNome());
            pstm.setInt(2, contato.getDdd());
            pstm.setInt(3, contato.getCelular());
            pstm.setString(4, contato.getEmail());
            pstm.setInt(5, contato.getAutor());
            pstm.setInt(6, contato.getDeletar());
            pstm.setInt(7, contato.getStatus());
            pstm.setString(8, contato.getIp());
            pstm.setInt(9, contato.getPorta());

            if (pstm.execute() == false) {
                contato.setCodigo(selecionaUltimoRegistro());
                return contato;
            }
            
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            if(pstm != null) Conexao.fechar(pstm);
            Conexao.fechar(con);
        }
        return null;
    }

    @Override
    public Object alterar(Object obj) throws DaoException {
        Connection con = Conexao.getConexao();
        PreparedStatement pstm = null;
        try {
            ContatoBean contato = (ContatoBean) obj;

            pstm = con.prepareStatement(Sqls.contato_alterar());
            pstm.setString(1, contato.getNome());
            pstm.setInt(2, contato.getDdd());
            pstm.setInt(3, contato.getCelular());
            pstm.setString(4, contato.getEmail());
            pstm.setInt(5, contato.getAutor());
            pstm.setInt(6, contato.getDeletar());
            pstm.setInt(7, contato.getStatus());
            pstm.setString(8, contato.getIp());
            pstm.setInt(9, contato.getPorta()); 
            pstm.setInt(10, contato.getCodigo());

            if (pstm.execute() == false) {
                return contato;
            }
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            if(pstm != null) Conexao.fechar(pstm);
            Conexao.fechar(con);
        }
        return null;
    }

    @Override
    public Boolean excluir(Object obj) throws DaoException {
        Connection con = Conexao.getConexao();
        PreparedStatement pstm = null;
        try {
            ContatoBean contato = (ContatoBean) obj;
            
            pstm = con.prepareStatement(Sqls.contato_excluir());
            pstm.setInt(1, contato.getCodigo());
            
            if (pstm.execute()) {
                return true;
            }
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            if(pstm != null) Conexao.fechar(pstm);
            Conexao.fechar(con);
        }
        return false;
    }

    @Override
    public ArrayList<Object> listar() throws DaoException {
        Connection con = Conexao.getConexao();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            ArrayList<Object> contatos = new ArrayList<>();
            
            pstm = con.prepareStatement(Sqls.contato_listar());
            rs = pstm.executeQuery();
            
            if (rs.next()) {
                do {
                    ContatoBean contato = new ContatoBean();
                    contato.setCodigo(rs.getInt(1));
                    contato.setNome(rs.getString(2));
                    contato.setDdd(rs.getInt(3));
                    contato.setCelular(rs.getInt(4));
                    contato.setEmail(rs.getString(5));
                    contato.setAutor(rs.getInt(6));
                    contato.setDeletar(rs.getInt(7));
                    contato.setStatus(rs.getInt(8));
                    contato.setIp(rs.getString(9));
                    contato.setPorta(rs.getInt(10));

                    contatos.add(contato);
                } while (rs.next());
            }

            return contatos;
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            if(rs != null) Conexao.fechar(rs);
            if(pstm != null) Conexao.fechar(pstm);
            Conexao.fechar(con);
        }
    }

    @Override
    public Object selecionar(Object obj) throws DaoException {
        Connection con = Conexao.getConexao();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            ContatoBean contato = (ContatoBean) obj;
            
            //"_id=?",
            pstm = con.prepareStatement(Sqls.contato_selecionar());
            pstm.setInt(1, contato.getCodigo());
            rs = pstm.executeQuery();
            
            if (rs.next()) {
                contato.setCodigo(rs.getInt(1));
                contato.setNome(rs.getString(2));
                contato.setDdd(rs.getInt(3));
                contato.setCelular(rs.getInt(4));
                contato.setEmail(rs.getString(5));
                contato.setAutor(rs.getInt(6));
                contato.setDeletar(rs.getInt(7));
                contato.setStatus(rs.getInt(8));
                contato.setIp(rs.getString(9));
                contato.setPorta(rs.getInt(10));
            }

            return contato;
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            if(rs != null) Conexao.fechar(rs);
            if(pstm != null) Conexao.fechar(pstm);
            Conexao.fechar(con);
        }
    }

    @Override
    public Object selecionarContatoDono() throws DaoException {
        Connection con = Conexao.getConexao();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            //"autor=? and deletar=?",
            pstm = con.prepareStatement(Sqls.contato_selecionarContatoDono());
            pstm.setInt(1, 0);
            pstm.setInt(2, 0);
            rs = pstm.executeQuery();
             
            if (rs.next()) {
                ContatoBean contato = new ContatoBean();
                contato.setCodigo(rs.getInt(1));
                contato.setNome(rs.getString(2));
                contato.setDdd(rs.getInt(3));
                contato.setCelular(rs.getInt(4));
                contato.setEmail(rs.getString(5));
                contato.setAutor(rs.getInt(6));
                contato.setDeletar(rs.getInt(7));
                contato.setStatus(rs.getInt(8));
                contato.setIp(rs.getString(9));
                contato.setPorta(rs.getInt(10));

                return contato;
            }
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            if(rs != null) Conexao.fechar(rs);
            if(pstm != null) Conexao.fechar(pstm);
            Conexao.fechar(con);
        }
        return null;
    }

    @Override
    public ArrayList<Object> listarAmigosOrdenadoPorNome() throws DaoException {
        Connection con = Conexao.getConexao();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            ArrayList<Object> contatos = new ArrayList<>();

            //"autor=? and deletar=?", "nome ASC"
            pstm = con.prepareStatement(Sqls.contato_listarAmigosOrdenadoPorNome());
            pstm.setInt(1, 1);
            pstm.setInt(2, 0);
            rs = pstm.executeQuery();
            
            if (rs.next()) {
                do {
                    ContatoBean contato = new ContatoBean();
                    contato.setCodigo(rs.getInt(1));
                    contato.setNome(rs.getString(2));
                    contato.setDdd(rs.getInt(3));
                    contato.setCelular(rs.getInt(4));
                    contato.setEmail(rs.getString(5));
                    contato.setAutor(rs.getInt(6));
                    contato.setDeletar(rs.getInt(7));
                    contato.setStatus(rs.getInt(8));
                    contato.setIp(rs.getString(9));
                    contato.setPorta(rs.getInt(10));

                    contatos.add(contato);
                } while (rs.next());
            }

            return contatos;
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            if(rs != null) Conexao.fechar(rs);
            if(pstm != null) Conexao.fechar(pstm);
            Conexao.fechar(con);
        }
    }
    
    @Override
    public ArrayList<Object> listarAmigosOrdenadoPorNome(String nome) throws DaoException {
        Connection con = Conexao.getConexao();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            ArrayList<Object> contatos = new ArrayList<>();

            //"autor=? and deletar=?", "nome ASC"
            pstm = con.prepareStatement(Sqls.contato_listarAmigosOrdenadoPorNome(nome));
            pstm.setInt(1, 1);
            pstm.setInt(2, 0);
            rs = pstm.executeQuery();
            
            if (rs.next()) {
                do {
                    ContatoBean contato = new ContatoBean();
                    contato.setCodigo(rs.getInt(1));
                    contato.setNome(rs.getString(2));
                    contato.setDdd(rs.getInt(3));
                    contato.setCelular(rs.getInt(4));
                    contato.setEmail(rs.getString(5));
                    contato.setAutor(rs.getInt(6));
                    contato.setDeletar(rs.getInt(7));
                    contato.setStatus(rs.getInt(8));
                    contato.setIp(rs.getString(9));
                    contato.setPorta(rs.getInt(10));

                    contatos.add(contato);
                } while (rs.next());
            }

            return contatos;
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            if(rs != null) Conexao.fechar(rs);
            if(pstm != null) Conexao.fechar(pstm);
            Conexao.fechar(con);
        }
    }

    @Override
    public ArrayList<Object> listarContatosConectados() throws DaoException {
        Connection con = Conexao.getConexao();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            ArrayList<Object> contatos = new ArrayList<>();

            //"status=? and deletar=? and autor=?", "nome ASC"
            pstm = con.prepareStatement(Sqls.contato_listarContatosConectados());
            pstm.setInt(1, 0);
            pstm.setInt(2, 0);
            pstm.setInt(3, 1);
            rs = pstm.executeQuery();
            
            if (rs.next()) {
                do {
                    ContatoBean contato = new ContatoBean();
                    contato.setCodigo(rs.getInt(1));
                    contato.setNome(rs.getString(2));
                    contato.setDdd(rs.getInt(3));
                    contato.setCelular(rs.getInt(4));
                    contato.setEmail(rs.getString(5));
                    contato.setAutor(rs.getInt(6));
                    contato.setDeletar(rs.getInt(7));
                    contato.setStatus(rs.getInt(8));
                    contato.setIp(rs.getString(9));
                    contato.setPorta(rs.getInt(10));

                    contatos.add(contato);
                } while (rs.next());
            }

            return contatos;
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            if(rs != null) Conexao.fechar(rs);
            if(pstm != null) Conexao.fechar(pstm);
            Conexao.fechar(con);
        }
    }

    @Override
    public ArrayList<Object> listarContatosDesconectados() throws DaoException {
        Connection con = Conexao.getConexao();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            ArrayList<Object> contatos = new ArrayList<>();

            //"status=? and deletar=? and autor=?", "nome ASC"
            pstm = con.prepareStatement(Sqls.contato_listarContatosDesconectados());
            pstm.setInt(1, 1);
            pstm.setInt(2, 0);
            pstm.setInt(3, 1);
            rs = pstm.executeQuery();
            
            if (rs.next()) {
                do {
                    ContatoBean contato = new ContatoBean();
                    contato.setCodigo(rs.getInt(1));
                    contato.setNome(rs.getString(2));
                    contato.setDdd(rs.getInt(3));
                    contato.setCelular(rs.getInt(4));
                    contato.setEmail(rs.getString(5));
                    contato.setAutor(rs.getInt(6));
                    contato.setDeletar(rs.getInt(7));
                    contato.setStatus(rs.getInt(8));
                    contato.setIp(rs.getString(9));
                    contato.setPorta(rs.getInt(10));

                    contatos.add(contato);
                } while (rs.next());
            }

            return contatos;
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            if(rs != null) Conexao.fechar(rs);
            if(pstm != null) Conexao.fechar(pstm);
            Conexao.fechar(con);
        }
    }

    @Override
    public Object selecionarContatoPorNome(String nome) throws DaoException {
        Connection con = Conexao.getConexao();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            //"nome=? and deletar=?",
            pstm = con.prepareStatement(Sqls.contato_selecionarContatoPorNome());
            pstm.setString(1, nome);
            pstm.setInt(2, 0);
            rs = pstm.executeQuery();
            
            if (rs.next()) {
                ContatoBean contato = new ContatoBean();
                contato.setCodigo(rs.getInt(1));
                contato.setNome(rs.getString(2));
                contato.setDdd(rs.getInt(3));
                contato.setCelular(rs.getInt(4));
                contato.setEmail(rs.getString(5));
                contato.setAutor(rs.getInt(6));
                contato.setDeletar(rs.getInt(7));
                contato.setStatus(rs.getInt(8));
                contato.setIp(rs.getString(9));
                contato.setPorta(rs.getInt(10));

                return contato;
            }
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            if(rs != null) Conexao.fechar(rs);
            if(pstm != null) Conexao.fechar(pstm);
            Conexao.fechar(con);
        }
        return null;
    }

    @Override
    public Object selecionarContatoAutor(String nome, Integer autor) throws DaoException {
        Connection con = Conexao.getConexao();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            //"nome=? and deletar=? and autor=?",
            pstm = con.prepareStatement(Sqls.contato_selecionarContatoAutor());
            pstm.setString(1, nome);
            pstm.setInt(2, 0);
            pstm.setInt(3, autor);
            rs = pstm.executeQuery();
            
            if (rs.next()) {
                ContatoBean contato = new ContatoBean();
                contato.setCodigo(rs.getInt(1));
                contato.setNome(rs.getString(2));
                contato.setDdd(rs.getInt(3));
                contato.setCelular(rs.getInt(4));
                contato.setEmail(rs.getString(5));
                contato.setAutor(rs.getInt(6));
                contato.setDeletar(rs.getInt(7));
                contato.setStatus(rs.getInt(8));
                contato.setIp(rs.getString(9));
                contato.setPorta(rs.getInt(10));

                return contato;
            }
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            if(rs != null) Conexao.fechar(rs);
            if(pstm != null) Conexao.fechar(pstm);
            Conexao.fechar(con);
        }
        return null;
    }
    
    private Integer selecionaUltimoRegistro() throws DaoException{
        Connection con = Conexao.getConexao();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = Conexao.getConexao();
            pstm = con.prepareStatement(Sqls.contato_listar_simples(),ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rs = pstm.executeQuery();
            Integer ultimo = 0;
            if(rs.last()){
                ultimo = rs.getInt(1);
            }
            return ultimo;
        } catch (SQLException ex) {
            throw new DaoException();
        } finally {
            if(rs != null) Conexao.fechar(rs);
            if(pstm != null) Conexao.fechar(pstm);
            Conexao.fechar(con);
        }
    }
    
}
