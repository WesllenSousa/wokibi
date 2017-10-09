
package recursos.sqls;

/**
 *
 * @author Wesllen Sousa Lima
 */
public class Sqls {
    
    /*-------Contatos---------------------------------------------------------*/
    
    public static String contato_inserir() {
        return "insert into \"contatos\" (\"nome\", \"ddd\", \"celular\", \"email\","
                + " \"autor\", \"deletar\", \"status\", \"ip\", \"porta\") "
                + "values (?,?,?,?,?,?,?,?,?)";
    }
    
    public static String contato_alterar() {
        return "update \"contatos\" set \"nome\"=?, \"ddd\"=?, \"celular\"=?, \"email\"=?,"
                + " \"autor\"=?, \"deletar\"=?, \"status\"=?, \"ip\"=?, \"porta\"=? "
                + "where \"_id\"=?";
    }
    
    public static String contato_excluir() {
        return "delete from \"contatos\" where \"_id\"=?";
    }
    
    public static String contato_selecionar() {
        return "select \"_id\", \"nome\", \"ddd\", \"celular\", \"email\", \"autor\","
                + " \"deletar\", \"status\", \"ip\", \"porta\" from \"contatos\" "
                + "where \"_id\"=?";
    }
    
    public static String contato_listar() {
        return "select \"_id\", \"nome\", \"ddd\", \"celular\", \"email\", \"autor\", "
                + "\"deletar\", \"status\", \"ip\", \"porta\" from \"contatos\""
                + " order by \"nome\"";
    }
    
    public static String contato_listar_simples() {
        return "select \"_id\", \"nome\", \"ddd\", \"celular\", \"email\", \"autor\", "
                + "\"deletar\", \"status\", \"ip\", \"porta\" from \"contatos\" ";
    }
    
    public static String contato_selecionarContatoDono() {
        return "select \"_id\", \"nome\", \"ddd\", \"celular\", \"email\", \"autor\","
                + " \"deletar\", \"status\", \"ip\", \"porta\" from \"contatos\" "
                + "where \"autor\"=? and \"deletar\"=?";
    }
    
    public static String contato_listarAmigosOrdenadoPorNome() {
        return "select \"_id\", \"nome\", \"ddd\", \"celular\", \"email\", \"autor\","
                + " \"deletar\", \"status\", \"ip\", \"porta\" from \"contatos\" "
                + "where \"autor\"=? and \"deletar\"=? order by \"nome\"";
    }
    
    public static String contato_listarAmigosOrdenadoPorNome(String nome) {
        return "select \"_id\", \"nome\", \"ddd\", \"celular\", \"email\", \"autor\","
                + " \"deletar\", \"status\", \"ip\", \"porta\" from \"contatos\" "
                + "where \"autor\"=? and \"deletar\"=? and \"nome\" like '"+nome+"%' order by \"nome\"";
    }
    
    public static String contato_listarContatosConectados() {
        return "select \"_id\", \"nome\", \"ddd\", \"celular\", \"email\", \"autor\", "
                + "\"deletar\", \"status\", \"ip\", \"porta\" from \"contatos\" "
                + "where \"status\"=? and \"deletar\"=? and \"autor\"=? order by \"nome\"";
    }
    
    public static String contato_listarContatosDesconectados() {
        return "select \"_id\", \"nome\", \"ddd\", \"celular\", \"email\", \"autor\", "
                + "\"deletar\", \"status\", \"ip\", \"porta\" from \"contatos\" "
                + "where \"status\"=? and \"deletar\"=? and \"autor\"=? order by \"nome\"";
    }
    
    public static String contato_selecionarContatoPorNome() {
        return "select \"_id\", \"nome\", \"ddd\", \"celular\", \"email\", \"autor\", "
                + "\"deletar\", \"status\", \"ip\", \"porta\" from \"contatos\" "
                + "where \"nome\"=? and \"deletar\"=?";
    }
    
    public static String contato_selecionarContatoAutor() {
        return "select \"_id\", \"nome\", \"ddd\", \"celular\", \"email\", \"autor\", "
                + "\"deletar\", \"status\", \"ip\", \"porta\" from \"contatos\" "
                + "where \"nome\"=? and \"deletar\"=? and \"autor\"=?";
    }
    
    /*-------Conversa---------------------------------------------------------*/
    
    public static String conversa_inserir() {
        return "insert into \"conversas\" (\"datahora\", \"descricao\", "
                + "\"deletar\", \"status\") values (?,?,?,?) ";
    }
    
    public static String conversa_alterar() {
        return "update \"conversas\" set \"datahora\"=?, \"descricao\"=?, "
                + "\"deletar\"=?, \"status\"=? where \"_id\"=?";
    }
    
    public static String conversa_excluir() {
        return "delete from \"contatos\" where \"_id\"=?";
    }
    
    public static String conversa_selecionar() {
        return "select \"_id\", \"datahora\", \"descricao\", "
                + "\"deletar\", \"status\" from \"conversas\" where \"_id\"=?";
    }
    
    public static String conversa_listar() {
        return "select \"_id\", \"datahora\", \"descricao\", "
                + "\"deletar\", \"status\" from \"conversas\" order by \"descricao\"";
    }
    
    public static String conversa_listar_simples() {
        return "select \"_id\", \"datahora\", \"descricao\",  "
                + "\"deletar\", \"status\" from \"conversas\" ";
    }
    
    public static String conversa_listarConversasAtivas() {
        return "select \"_id\", \"datahora\", \"descricao\", "
                + "\"deletar\", \"status\" from \"conversas\" where \"status\"=? "
                + "and \"deletar\"=?";
    }
     
    public static String conversa_listarTodasConversasAtivas() {
        return "select \"_id\", \"datahora\", \"descricao\", "
                + "\"deletar\", \"status\" from \"conversas\" where \"status\"=? "
                + "and \"deletar\"=?";
    }
    
    public static String conversa_listarConversasInativas() {
        return "select \"_id\", \"datahora\", \"descricao\", "
                + "\"deletar\", \"status\" from \"conversas\" where \"status\"=? "
                + "and \"deletar\"=?";
    }
    
    public static String conversa_listarTodasConversasInativas() {
        return "select \"_id\", \"datahora\", \"descricao\", "
                + "\"deletar\", \"status\" from \"conversas\" where \"status\"=? "
                + "and \"deletar\"=?";
    }
    
    public static String conversa_selecionarConversaPorDescricao() {
        return "select \"_id\", \"datahora\", \"descricao\", "
                + "\"deletar\", \"status\" from \"conversas\" where \"descricao\"=? "
                + "and \"deletar\"=?";
    }
    
    /*-------Mensagem---------------------------------------------------------*/
    
    public static String mensagem_inserir() {
        return "insert into \"mensagens\" (\"conversa\", \"contato\", \"datahora\", "
                + "\"texto\", \"deletar\", \"ocultar\") values (?,?,?,?,?,?)";
    }
    
    public static String mensagem_alterar() {
        return "update \"mensagens\" set \"conversa\"=?, \"contato\"=?, \"datahora\"=?, "
                + "\"texto\"=?, \"deletar\"=?, \"ocultar\"=? where \"_id\"=?";
    }
    
    public static String mensagem_excluir() {
        return "delete from \"mensagens\" where \"_id\"=?";
    }
    
    public static String mensagem_selecionar() {
        return "select \"_id\", \"conversa\", \"contato\", \"datahora\", \"texto\", "
                + "\"deletar\", \"ocultar\" from \"mensagens\" where \"_id\"=?";
    }
    
    public static String mensagem_listar() {
        return "select \"_id\", \"conversa\", \"contato\", \"datahora\", \"texto\", "
                + "\"deletar\", \"ocultar\" from \"mensagens\" order by \"_id\"";
    }
    
    public static String mensagem_listar_simples() {
        return "select \"_id\", \"conversa\", \"contato\", \"datahora\", \"texto\", "
                + "\"deletar\", \"ocultar\" from \"mensagens\" ";
    }
    
    public static String mensagem_listarMensagensPorConversa() {
        return "select \"_id\", \"conversa\", \"contato\", \"datahora\", \"texto\", "
                + "\"deletar\", \"ocultar\" from \"mensagens\" where \"conversa\"=? "
                + "and \"deletar\"=? and \"ocultar\"=? order by \"_id\" ASC";
    }
    
    public static String mensagem_listarMensagensPorConversaOcultas() {
        return "select \"_id\", \"conversa\", \"contato\", \"datahora\", \"texto\", "
                + "\"deletar\", \"ocultar\" from \"mensagens\" where \"conversa\"=? "
                + "and \"deletar\"=? order by \"_id\" ASC";
    }
    
    public static String mensagem_listarMensagensPorContato() {
        return "select \"_id\", \"conversa\", \"contato\", \"datahora\", \"texto\", "
                + "\"deletar\", \"ocultar\" from \"mensagens\" where \"contato\"=? "
                + "and \"deletar\"=? order by \"_id\"";
    }
    
}
