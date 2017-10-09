package util;

import controle.utilitarios.Instancias;
import java.sql.*;

public class Conexao {

    public static Connection getConexao() {
        Connection connection;
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            connection = DriverManager.getConnection("jdbc:derby:base;create=true");
            return connection;
        } catch (SQLException ex) {
            Instancias.getMensagens().bug("Erro de Conexão com o banco de dados!\n" + ex);
        } catch (ClassNotFoundException ex) {
            Instancias.getMensagens().bug("Classe do banco de Dados não encontrada!\n" + ex);
        }
        return null;
    }

    public static void fechar(Statement stm) {
        try {
            stm.close();
        } catch (SQLException ex) {
        }
    }

    public static void fechar(Connection con) {
        try {
            con.close();
        } catch (SQLException ex) {
        }
    }

    public static void fechar(ResultSet rs) {
        try {
            rs.close();
        } catch (SQLException ex) {
        }
    }

    public static void fechar(PreparedStatement stmt) {
        try {
            stmt.close();
        } catch (SQLException ex) {
        }
    }

    public static void criarBanco() {
        Connection connection = getConexao();
        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE \"contatos\" ( \n"
                    + "\"_id\" int primary key generated always as identity(start with 1, increment by 1), \n"
                    + "\"nome\" varchar(100), \n"
                    + "\"ddd\" int, \n"
                    + "\"celular\" int, \n"
                    + "\"email\" varchar(100), \n"
                    + "\"autor\" int, \n"
                    + "\"deletar\" int, \n"
                    + "\"status\" int, \n"
                    + "\"ip\" varchar(100), \n"
                    + "\"porta\" int \n"
                    + ") \n");
            statement.execute("CREATE TABLE \"conversas\" ( \n"
                    + "\"_id\" int primary key generated always as identity(start with 1, increment by 1), \n"
                    + "\"datahora\" varchar(100), \n"
                    + "\"descricao\" varchar(100), \n"
                    + "\"deletar\" int, \n"
                    + "\"status\" int \n"
                    + ") \n");
            statement.execute("CREATE TABLE \"mensagens\" ( \n"
                    + "\"_id\" int primary key generated always as identity(start with 1, increment by 1), \n"
                    + "\"conversa\" int, \n"
                    + "\"contato\" int, \n"
                    + "\"datahora\" varchar(100), \n"
                    + "\"texto\" varchar(100), \n"
                    + "\"deletar\" int, \n"
                    + "\"ocultar\" int, \n"
                    + "FOREIGN KEY (\"conversa\") REFERENCES \"conversas\"(\"_id\"), \n"
                    + "FOREIGN KEY (\"contato\") REFERENCES \"contatos\"(\"_id\") \n"
                    + ") \n");
            statement.close();
            Conexao.fechar(connection);
            System.out.println("Banco de dados criado com sucesso!");
        } catch (SQLException ex) {
            System.out.println("------------" + ex);
        }
    }
}

/*
 *
 *
 * CREATE TABLE "contatos" ( "_id" int primary key generated always as
 * identity(start with 1, increment by 1), "nome" varchar(100), "ddd" int,
 * "celular" int, "email" varchar(100), "autor" int, "deletar" int, "status"
 * int, "ip" varchar(100), "porta" int );
 *
 * CREATE TABLE "conversas" ( "_id" int primary key generated always as
 * identity(start with 1, increment by 1), "datahora" varchar(100), "descricao"
 * varchar(100), "clienteservidor" int, "deletar" int, "status" int );
 *
 * CREATE TABLE "mensagens" ( "_id" int primary key generated always as
 * identity(start with 1, increment by 1), "conversa" int, "contato" int,
 * "datahora" varchar(100), "texto" varchar(100), "deletar" int, "ocultar" int,
 * FOREIGN KEY ("conversa") REFERENCES "conversas"("_id"), FOREIGN KEY
 * ("contato") REFERENCES "contatos"("_id") );
 *
 *
 */
