package entidades.mensagem.dao;

import entidades.contato.bean.ContatoBean;
import entidades.contato.dao.ContatoDaoImpl;
import entidades.conversa.bean.ConversaBean;
import entidades.conversa.dao.ConversaDaoImpl;
import entidades.mensagem.bean.MensagemBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import recursos.sqls.Sqls;
import util.Conexao;
import util.DaoException;
import util.DataUtil;

public class MensagemDaoImpl implements MensagemDao {

    private ConversaDaoImpl conversaDaoImpl;
    private ContatoDaoImpl contatoDaoImpl;

    public MensagemDaoImpl() {
        conversaDaoImpl = new ConversaDaoImpl();
        contatoDaoImpl = new ContatoDaoImpl();
    }

    @Override
    public Object inserirOuAlterar(Object obj) throws DaoException {
        MensagemBean mensagem = (MensagemBean) obj;
        if (mensagem.getCodigo() != null) {
            return alterar(mensagem);
        } else {
            return inserir(mensagem);
        }
    }

    @Override
    public Object inserir(Object obj) throws DaoException {
        Connection con = Conexao.getConexao();
        PreparedStatement pstm = null;
        try {
            MensagemBean mensagem = (MensagemBean) obj;

            pstm = con.prepareStatement(Sqls.mensagem_inserir());
            pstm.setInt(1, mensagem.getConversa().getCodigo());
            pstm.setInt(2, mensagem.getContato().getCodigo());
            pstm.setString(3, DataUtil.formataDataHHmmSS(mensagem.getDataHora()));
            pstm.setString(4, mensagem.getTexto());
            pstm.setInt(5, mensagem.getDeletar());
            pstm.setInt(6, mensagem.getOcultar());

            if (pstm.execute() == false) {
                mensagem.setCodigo(selecionaUltimoRegistro());
                return mensagem;
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
            MensagemBean mensagem = (MensagemBean) obj;

            pstm = con.prepareStatement(Sqls.mensagem_alterar());
            pstm.setInt(1, mensagem.getConversa().getCodigo());
            pstm.setInt(2, mensagem.getContato().getCodigo());
            pstm.setString(3, DataUtil.formataDataHHmmSS(mensagem.getDataHora()));
            pstm.setString(4, mensagem.getTexto());
            pstm.setInt(5, mensagem.getDeletar());
            pstm.setInt(6, mensagem.getOcultar());
            pstm.setInt(7, mensagem.getCodigo());

            if (pstm.execute() == false) {
                return mensagem;
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
            MensagemBean mensagem = (MensagemBean) obj;

            pstm = con.prepareStatement(Sqls.mensagem_excluir());
            pstm.setInt(1, mensagem.getCodigo());
            
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
            ArrayList<Object> mensagens = new ArrayList<>();
            
            pstm = con.prepareStatement(Sqls.mensagem_listar());
            rs = pstm.executeQuery();
            
            if (rs.next()) {
                do {
                    MensagemBean mensagem = new MensagemBean();
                    mensagem.setCodigo(rs.getInt(1));
                    mensagem.setConversa((ConversaBean) conversaDaoImpl.selecionar(rs.getInt(2)));
                    mensagem.setContato((ContatoBean) contatoDaoImpl.selecionar(rs.getInt(3)));
                    mensagem.setDataHora(DataUtil.formataDataCalendarHHmmSS(rs.getString(4)));
                    mensagem.setTexto(rs.getString(5));
                    mensagem.setDeletar(rs.getInt(6));
                    mensagem.setOcultar(rs.getInt(7));

                    mensagens.add(mensagem);
                } while (rs.next());
            }

            return mensagens;
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
            MensagemBean mensagem = (MensagemBean) obj;
            
            //"_id=?",
            pstm = con.prepareStatement(Sqls.mensagem_selecionar());
            pstm.setInt(1, mensagem.getCodigo());
            rs = pstm.executeQuery();
            
            if (rs.next()) {
                mensagem.setCodigo(rs.getInt(1));
                ConversaBean conversa = new ConversaBean();
                conversa.setCodigo(rs.getInt(2));
                mensagem.setConversa((ConversaBean) conversaDaoImpl.selecionar(conversa));
                ContatoBean contato = new ContatoBean();
                contato.setCodigo(rs.getInt(3));
                mensagem.setContato((ContatoBean) contatoDaoImpl.selecionar(contato));
                mensagem.setDataHora(DataUtil.formataDataCalendarHHmmSS(rs.getString(4)));
                mensagem.setTexto(rs.getString(5));
                mensagem.setDeletar(rs.getInt(6));
                mensagem.setOcultar(rs.getInt(7));
            }

            return mensagem;
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
    public ArrayList<Object> listarMensagensPorConversa(Integer codigo) throws DaoException {
        Connection con = Conexao.getConexao();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            ArrayList<Object> mensagens = new ArrayList<>();
            
            //"conversa=? and deletar=? and ocultar=?" "_id DESC"
            pstm = con.prepareStatement(Sqls.mensagem_listarMensagensPorConversa());
            pstm.setInt(1, codigo);
            pstm.setInt(2, 0);
            pstm.setInt(3, 0);
            rs = pstm.executeQuery();
            
            if (rs.next()) {
                do {
                    MensagemBean mensagem = new MensagemBean();
                    mensagem.setCodigo(rs.getInt(1));
                    ConversaBean conversa = new ConversaBean();
                    conversa.setCodigo(rs.getInt(2));
                    mensagem.setConversa((ConversaBean) conversaDaoImpl.selecionar(conversa));
                    ContatoBean contato = new ContatoBean();
                    contato.setCodigo(rs.getInt(3));
                    mensagem.setContato((ContatoBean) contatoDaoImpl.selecionar(contato));
                    mensagem.setDataHora(DataUtil.formataDataCalendarHHmmSS(rs.getString(4)));
                    mensagem.setTexto(rs.getString(5));
                    mensagem.setDeletar(rs.getInt(6));
                    mensagem.setOcultar(rs.getInt(7));

                    mensagens.add(mensagem);
                } while (rs.next());
            }

            return mensagens;
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
    public ArrayList<Object> listarMensagensPorConversaOcultas(Integer codigo) throws DaoException {
        Connection con = Conexao.getConexao();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            ArrayList<Object> mensagens = new ArrayList<>();    
            
            //"conversa=? and deletar=?", "_id DESC"
            pstm = con.prepareStatement(Sqls.mensagem_listarMensagensPorConversaOcultas());
            pstm.setInt(1, codigo);
            pstm.setInt(2, 0);
            rs = pstm.executeQuery();
            
            if (rs.next()) {
                do {
                    MensagemBean mensagem = new MensagemBean();
                    mensagem.setCodigo(rs.getInt(1));
                    ConversaBean conversa = new ConversaBean();
                    conversa.setCodigo(rs.getInt(2));
                    mensagem.setConversa((ConversaBean) conversaDaoImpl.selecionar(conversa));
                    ContatoBean contato = new ContatoBean();
                    contato.setCodigo(rs.getInt(3));
                    mensagem.setContato((ContatoBean) contatoDaoImpl.selecionar(contato));
                    mensagem.setDataHora(DataUtil.formataDataCalendarHHmmSS(rs.getString(4)));
                    mensagem.setTexto(rs.getString(5));
                    mensagem.setDeletar(rs.getInt(6));
                    mensagem.setOcultar(rs.getInt(7));

                    mensagens.add(mensagem);
                } while (rs.next());
            }

            return mensagens;
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
    public ArrayList<Object> listarMensagensPorContato(Integer codigo) throws DaoException {
        Connection con = Conexao.getConexao();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            ArrayList<Object> mensagens = new ArrayList<>();  
            
            //"contato=? and deletar=?", "_id ASC"
            pstm = con.prepareStatement(Sqls.mensagem_listarMensagensPorContato());
            pstm.setInt(1, codigo);
            pstm.setInt(2, 0);
            rs = pstm.executeQuery();
            
            if (rs.next()) {
                do {
                    MensagemBean mensagem = new MensagemBean();
                    mensagem.setCodigo(rs.getInt(1));
                    ConversaBean conversa = new ConversaBean();
                    conversa.setCodigo(rs.getInt(2));
                    mensagem.setConversa((ConversaBean) conversaDaoImpl.selecionar(conversa));
                    ContatoBean contato = new ContatoBean();
                    contato.setCodigo(rs.getInt(3));
                    mensagem.setContato((ContatoBean) contatoDaoImpl.selecionar(contato));
                    mensagem.setDataHora(DataUtil.formataDataCalendarHHmmSS(rs.getString(4)));
                    mensagem.setTexto(rs.getString(5));
                    mensagem.setDeletar(rs.getInt(6));
                    mensagem.setOcultar(rs.getInt(7));

                    mensagens.add(mensagem);
                } while (rs.next());
            }

            return mensagens;
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
    
    private Integer selecionaUltimoRegistro() throws DaoException{
        Connection con = Conexao.getConexao();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = Conexao.getConexao();
            pstm = con.prepareStatement(Sqls.mensagem_listar_simples(),ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
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
