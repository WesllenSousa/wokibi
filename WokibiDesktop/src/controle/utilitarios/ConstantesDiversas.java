package controle.utilitarios;

public class ConstantesDiversas {
	
	//Mensagem
	public static final String PADRAO_MENSAGEM = "wsl:";
	public static final String CODIGO_NUMERO = "+55";
        public static String PORTA_MODEM = "COM3";
	
	//Banco de dados
	public final static String PATH_BANCO = "/data/data/br.rr.wsl/databases/";
	public final static String NOME_BANCO = "projetomobile";
	public final static Integer VERSAO = 1;
	
	//Serviços
	public final static String SV_SERVICO_SERVIDOR = "SV_SERVIDOR";
	public final static String SV_SERVICO_SMS = "SV_SMS";
	
	//Receives
	public final static String RC_RECEBER_MENSAGEM = "RECEBER_MENSAGEM";
	public final static String RC_ATUALIZAR_REDE = "ATUALIZAR_REDE";

	//Uris
	public static final String URI_CONTATOS = "content://com.android.contacts/contacts";
	
	//Preferencias
	public final static String PF_NOME_PREFERENCIA = "pf_senha";
	public final static String PF_GUARDA_SENHA = "pf_guardaSenha";
	public final static String PF_SERVICO_SERVIDOR = "pf_servicoServidor";
	public final static String PF_SERVICO_SMS= "pf_servidoSms";
	public final static String PF_TELA_MENSAGEM = "pf_telaMensagem";
	public final static String PF_TELA_CONVERSA = "pf_telaConversa";
	public final static String PF_TELA_PRINCIPAL = "pf_telaPrincipal";
	
	//Bundles
	public final static String BD_ASSOCIAR_CONVERSA = "bd_1";
	public final static String BD_CODIGO_CONTATO = "bd_2";
	public final static String BD_CODIGO_CONVERSA = "bd_3";
	public final static String BD_DONO = "bd_4";
	public final static String BD_CLIENTE_SERVIDOR = "bd_5";
	public final static String BD_MENSAGEM = "bd_6";
	public final static String BD_CONECTAR_CONVERSA = "bd_7";
	public final static String BD_SERVIDOR_CONECTADO = "bd_8";
	public final static String BD_NOME_CONTATO = "bd_9";
	public final static String BD_ASSOCIAR_CONVERSA_CLIENTE = "bd_10";
	public final static String BD_NOTIFICAR_CONVERSA = "bd_11";
	
	//Cliente Servidor
	public final static int CS_PORTA = 8080;
	public final static String CS_FIM = "cs_1";
	public final static String CS_INICIAR_CONVERSA = "cs_2";
	public final static String CS_INICIAR_CONVERSA_SERVIDOR = "cs_3";
	public final static String CS_CONECTADO = "cs_4";
	public final static String CS_MENSAGEM = "cs_5";
	public final static String CS_CLIENTE_CONECTADOS = "cs_6";
	public final static String CS_ASSOCIAR_CONVERSA = "cs_7";
	public final static String CS_ENCAMINHAR_ASSOCIAR_CONVERSA = "cs_8";
	public final static String CS_CONVERSA_PRIVADA = "cs_9";
        public final static String CS_CONECTADO_SERVIDOR = "cs_10";
        public final static String CS_DADOS_CONTATOS = "cs_11";
        public final static String CS_CRIAR_CONVERSA_PRIVADA = "cs_12";
	
	//Handle
	public final static int HD_NOVA_CONVERSA = 1;
	public final static int HD_NOVA_MENSAGEM = 2;
	public final static int HD_RETORNA_CLIENTES = 3;
	public final static int HD_CONVERSA_PRIVADA = 4;
	public final static int HD_ATUALIZAR_REDE = 5;
              
        //Listas
        public final static int TL_LISTA_CONVERSA_ATIVA = 1;
        public final static int TL_LISTA_CONVERSA_INATIVA = 2;
        public final static int TL_LISTA_CONTATOS = 3;
        public final static int TL_LISTA_MENSAGENS = 4;
        public final static int TL_LISTA_CONTATOS_CONECTADOS = 5;
        public final static int TL_LISTA_CLIENTES_CONECTADOS = 6;

}
