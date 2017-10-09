import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UseDerby {
	private String nomeBD;
	private String driverBD;
	private String stringDeConexao;
	private Connection conexaoBD;
	private Statement executor;
	
	public UseDerby(){
		try{
		//Inicializa variáveis
			iniciar();
		//cria Tabela se ela não existir
			if(!existeTabela())
				criaTabelaNomes();
		//Insere dados na tabela
			inseriDadosTabelaNomes();
		//Mostra dados da tabela
			mostraTabelaNomes();
		//Finaliza conexão com o banco
			finalizar();
		}catch(Exception excep){
			excep.printStackTrace();
		}
	}
	
	private void iniciar() throws SQLException{
		nomeBD = "BDTesteDerby";
		driverBD = "org.apache.derby.jdbc.EmbeddedDriver";
		stringDeConexao = "jdbc:derby:" + nomeBD + ";create=true";
		//conecta na Base de dados (Caso não exista ela será criada)
			conectaBDTeste();
		//Inicializa o executor de comandos na base de dados.	
			executor = conexaoBD.createStatement();
	}
	
	private void conectaBDTeste() throws SQLException {
		conexaoBD = DriverManager.getConnection(stringDeConexao);
		System.out.println("Conexão estabelecida com sucesso na base de dados '" + nomeBD+"'!");
	}
	
	private boolean existeTabela() throws SQLException{
		try {
	           //executa um comando qualquer.
	           executor.execute("update tblNomes set DATA = CURRENT_TIMESTAMP, NOME = 'TEST ENTRY' where 1=3");
	        }  catch (SQLException sqle) {
	           String theError = (sqle).getSQLState();
	           if (theError.equals("42X05")) {  // Table does not exist
	        	   return false;
	            }  else if (theError.equals("42X14") || theError.equals("42821")) {//Estrutura da tabela está errada
	            	System.out.println("\nTabela foi encontrada porém sua estrtura é diferente da esperada, exclua para solucionar o problema!\n");
	            	throw sqle; 
	            } else { 
	               throw sqle; 
	            }
	        }
	        return true;
	}
	
	private void criaTabelaNomes() throws SQLException{
		String createString = "CREATE TABLE tblNomes "
	        +  "(COD INT NOT NULL GENERATED ALWAYS AS IDENTITY " 
	        +  "   CONSTRAINT CodNome_PK PRIMARY KEY, " 
	        +  " DATA TIMESTAMP DEFAULT CURRENT_TIMESTAMP, "
	        +  " NOME VARCHAR(32) NOT NULL) " ;
		executor.execute(createString);
	}
	
	private void inseriDadosTabelaNomes() throws SQLException, IOException{
		String nome = lerNomes();
		PreparedStatement executorInsert = conexaoBD.prepareStatement("insert into tblNomes (NOME) values (?)");
		while(!nome.equalsIgnoreCase("EXIT")){
			executorInsert.setString(1,nome);
			executorInsert.executeUpdate(); 
			nome = lerNomes();
		}
		executorInsert.close();
	}
	
	private String lerNomes() throws IOException{
		BufferedReader ler = new BufferedReader( new InputStreamReader(System.in));
        String nome = "";
        try {
           while ( nome.length() == 0 ) {
              System.out.println("Digite um nome a ser adicionado na base de dados ou 'exit' para sair. ");
              nome = ler.readLine();
           }
        } catch (IOException e) {
        	throw new IOException("Não foi possivel ler o valor digitado." + e.getMessage(), e);
        }
        return nome;
	}
	
	public void mostraTabelaNomes() throws SQLException{
		ResultSet nomesNaTabela = executor.executeQuery("select DATA, NOME from tblNomes order by DATA");
		System.out.println("\n");
		while (nomesNaTabela.next()){
			System.out.println(" | " + nomesNaTabela.getTimestamp(1) + "\t|" + nomesNaTabela.getString(2) );
		}
		System.out.println("\n");
        nomesNaTabela.close();
	}
	
	private void finalizar() throws SQLException{
		//Desconecta da base de dados
			desconectaDB();
		//Encerra serviço da base de dados
			encerraDB();
	}
	
	private void desconectaDB() throws SQLException{
		//Fecha a conexão com o banco.
        conexaoBD.close();
	}
	
	private void encerraDB() throws SQLException{
		// No uso do banco de dados acoplado com a aplicação é importante que ele também seja encerrado.
        if (driverBD.equals("org.apache.derby.jdbc.EmbeddedDriver")) {
           try {
        	   //Encerrando base de dados.
              DriverManager.getConnection("jdbc:derby:;shutdown=true");
           } catch (SQLException se)  {	
              if ( se.getSQLState().equals("XJ015") ) {		
            	  System.out.println("Base de dados foi encerrada com sucesso!");	
              }else{
            	  throw new SQLException("Erro ao encerrar a base de dados! "+se.getMessage(),se.getSQLState(),se);
              }
           }
        }
	}
	
	public static void main  (String[] args) {
		new UseDerby();
	}
}