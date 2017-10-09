package entidades.conversa.dao;

import entidades.conversa.bean.ConversaBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import recursos.sqls.Sqls;
import util.Conexao;
import util.DaoException;
import util.DataUtil;

public class ConversaDaoImpl implements ConversaDao {

    public ConversaDaoImpl() {
    }

    @Override
    public Object inserirOuAlterar(Object obj) throws DaoException {
        ConversaBean conversa = (ConversaBean) obj;
        if (conversa.getCodigo() != null) {
            return alterar(conversa);
        } else {
            return inserir(conversa);
        }
    }

    @Override
    public Object inserir(Object obj) throws DaoException {
        Connection con = Conexao.getConexao();
        PreparedStatement pstm = null;
        try {
            ConversaBean conversa = (ConversaBean) obj;

            pstm = con.prepareStatement(Sqls.conversa_inserir());
            pstm.setString(1, DataUtil.formataDataHHmmSS(conversa.getDataHora()));
            pstm.setString(2, conversa.getDescricao());
            pstm.setInt(3, conversa.getDeletar());
            pstm.setInt(4, conversa.getStatus());

            if (pstm.execute() == false) {
                conversa.setCodigo(selecionaUltimoRegistro());
                return conversa;
            } 
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            if(pstm != null) {
                Conexao.fechar(pstm);
            }
            Conexao.fechar(con);
        }
        return null;
    }

    @Override
    public Object alterar(Object obj) throws DaoException {
        Connection con = Conexao.getConexao();
        PreparedStatement pstm = null;
        try {
            ConversaBean conversa = (ConversaBean) obj;

            pstm = con.prepareStatement(Sqls.conversa_alterar());
            pstm.setString(1, DataUtil.formataDataHHmmSS(conversa.getDataHora()));
            pstm.setString(2, conversa.getDescricao());
            pstm.setInt(3, conversa.getDeletar());
            pstm.setInt(4, conversa.getStatus());
            pstm.setInt(5, conversa.getCodigo());

            if (pstm.execute() == false) {
                return conversa;
            }
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            if(pstm != null) {
                Conexao.fechar(pstm);
            }
            Conexao.fechar(con);
        }
        return null;
    }

    @Override
    public Boolean excluir(Object obj) throws DaoException {
        Connection con = Conexao.getConexao();
        PreparedStatement pstm = null;
        try {
            ConversaBean conversa = (ConversaBean) obj;

            pstm = con.prepareStatement(Sqls.conversa_excluir());
            pstm.setInt(1, conversa.getCodigo());
            
            if (pstm.execute()) {
                return true;
            }
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            if(pstm != null) {
                Conexao.fechar(pstm);
            }
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
            ArrayList<Object> conversas = new ArrayList<>();
            
            pstm = con.prepareStatement(Sqls.conversa_listar());
            rs = pstm.executeQuery();
            
            if (rs.next()) {
                do {
                    ConversaBean conversa = new ConversaBean();
                    conversa.setCodigo(rs.getInt(1));
                    conversa.setDataHora(DataUtil.formataDataCalendarHHmmSS(rs.getString(2)));
                    conversa.setDescricao(rs.getString(3));
                    conversa.setDeletar(rs.getInt(4));
                    conversa.setStatus(rs.getInt(5));

                    conversas.add(conversa);
                } while (rs.next());
            }

            return conversas;
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            if(rs != null) {
                Conexao.fechar(rs);
            }
            if(pstm != null) {
                Conexao.fechar(pstm);
            }
            Conexao.fechar(con);
        }
    }

    @Override
    public Object selecionar(Object obj) throws DaoException {
        Connection con = Conexao.getConexao();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            ConversaBean conversa = (ConversaBean) obj;
            
            //"_id=?",
            pstm = con.prepareStatement(Sqls.conversa_selecionar());
            pstm.setInt(1, conversa.getCodigo());
            rs = pstm.executeQuery();
            
            if (rs.next()) {
                conversa.setCodigo(rs.getInt(1));
                conversa.setDataHora(DataUtil.formataDataCalendarHHmmSS(rs.getString(2)));
                conversa.setDescricao(rs.getString(3));
                conversa.setDeletar(rs.getInt(4));
                conversa.setStatus(rs.getInt(5));
            }

            return conversa;
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            if(rs != null) {
                Conexao.fechar(rs);
            }
            if(pstm != null) {
                Conexao.fechar(pstm);
            }
            Conexao.fechar(con);
        }
    }

    @Override
    public ArrayList<Object> listarConversasAtivas() throws DaoException {
        Connection con = Conexao.getConexao();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            ArrayList<Object> conversas = new ArrayList<>();
            //"status=? and deletar=?"
            pstm = con.prepareStatement(Sqls.conversa_listarConversasAtivas());
            pstm.setInt(1, 0);
            pstm.setInt(2, 0);

            rs = pstm.executeQuery();
            
            if (rs.next()) {
                do {
                    ConversaBean conversa = new ConversaBean();
                    conversa.setCodigo(rs.getInt(1));
                    conversa.setDataHora(DataUtil.formataDataCalendarHHmmSS(rs.getString(2)));
                    conversa.setDescricao(rs.getString(3));
                    conversa.setDeletar(rs.getInt(4));
                    conversa.setStatus(rs.getInt(5));

                    conversas.add(conversa);
                } while (rs.next());
            }

            return conversas;
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            if(rs != null) {
                Conexao.fechar(rs);
            }
            if(pstm != null) {
                Conexao.fechar(pstm);
            }
            Conexao.fechar(con);
        }
    }

    @Override
    public ArrayList<Object> listarTodasConversasAtivas() throws DaoException {
        Connection con = Conexao.getConexao();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            ArrayList<Object> conversas = new ArrayList<>();
           
            //"status=? and deletar=?"
            pstm = con.prepareStatement(Sqls.conversa_listarTodasConversasAtivas());
            pstm.setInt(1, 0);
            pstm.setInt(2, 0);
            rs = pstm.executeQuery();
            
            if (rs.next()) {
                do {
                    ConversaBean conversa = new ConversaBean();
                    conversa.setCodigo(rs.getInt(1));
                    conversa.setDataHora(DataUtil.formataDataCalendarHHmmSS(rs.getString(2)));
                    conversa.setDescricao(rs.getString(3));
                    conversa.setDeletar(rs.getInt(4));
                    conversa.setStatus(rs.getInt(5));

                    conversas.add(conversa);
                } while (rs.next());
            }

            return conversas;
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            if(rs != null) {
                Conexao.fechar(rs);
            }
            if(pstm != null) {
                Conexao.fechar(pstm);
            }
            Conexao.fechar(con);
        }
    }

    @Override
    public ArrayList<Object> listarConversasInativas() throws DaoException {
        Connection con = Conexao.getConexao();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            ArrayList<Object> conversas = new ArrayList<>();

            //"status=? and deletar=?"
            pstm = con.prepareStatement(Sqls.conversa_listarConversasInativas());
            pstm.setInt(1, 1);
            pstm.setInt(2, 0);
            rs = pstm.executeQuery();
            
            if (rs.next()) {
                do {
                    ConversaBean conversa = new ConversaBean();
                    conversa.setCodigo(rs.getInt(1));
                    conversa.setDataHora(DataUtil.formataDataCalendarHHmmSS(rs.getString(2)));
                    conversa.setDescricao(rs.getString(3));
                    conversa.setDeletar(rs.getInt(4));
                    conversa.setStatus(rs.getInt(5));

                    conversas.add(conversa);
                } while (rs.next());
            }

            return conversas;
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            if(rs != null) {
                Conexao.fechar(rs);
            }
            if(pstm != null) {
                Conexao.fechar(pstm);
            }
            Conexao.fechar(con);
        }
    }

    @Override
    public ArrayList<Object> listarTodasConversasInativas() throws DaoException {
        Connection con = Conexao.getConexao();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            ArrayList<Object> conversas = new ArrayList<>();
                 
            //"status=? and deletar=?",
            pstm = con.prepareStatement(Sqls.conversa_listarTodasConversasInativas());
            pstm.setInt(1, 1);
            pstm.setInt(2, 0);
            rs = pstm.executeQuery();
            
            if (rs.next()) {
                do {
                    ConversaBean conversa = new ConversaBean();
                    conversa.setCodigo(rs.getInt(1));
                    conversa.setDataHora(DataUtil.formataDataCalendarHHmmSS(rs.getString(2)));
                    conversa.setDescricao(rs.getString(3));
                    conversa.setDeletar(rs.getInt(4));
                    conversa.setStatus(rs.getInt(5));

                    conversas.add(conversa);
                } while (rs.next());
            }

            return conversas;
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            if(rs != null) {
                Conexao.fechar(rs);
            }
            if(pstm != null) {
                Conexao.fechar(pstm);
            }
            Conexao.fechar(con);
        }
    }

    @Override
    public Object selecionarConversaPorDescricao(String descricao) throws DaoException {
        Connection con = Conexao.getConexao();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            //descricao=? and deletar=?
            pstm = con.prepareStatement(Sqls.conversa_selecionarConversaPorDescricao());
            pstm.setString(1, descricao);
            pstm.setInt(2, 0);
            rs = pstm.executeQuery();
            
            if (rs.next()) {
                ConversaBean conversa = new ConversaBean();
                conversa.setCodigo(rs.getInt(1));
                conversa.setDataHora(DataUtil.formataDataCalendarHHmmSS(rs.getString(2)));
                conversa.setDescricao(rs.getString(3));
                conversa.setDeletar(rs.getInt(4));
                conversa.setStatus(rs.getInt(5));

                return conversa;
            }
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            if(rs != null) {
                Conexao.fechar(rs);
            }
            if(pstm != null) {
                Conexao.fechar(pstm);
            }
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
            pstm = con.prepareStatement(Sqls.conversa_listar_simples(),ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rs = pstm.executeQuery();
            Integer ultimo = 0;
            if(rs.last()){
                ultimo = rs.getInt(1);
            }
            return ultimo;
        } catch (SQLException ex) {
            throw new DaoException();
        } finally {
            if(rs != null) {
                Conexao.fechar(rs);
            }
            if(pstm != null) {
                Conexao.fechar(pstm);
            }
            Conexao.fechar(con);
        }
    }
    
}
