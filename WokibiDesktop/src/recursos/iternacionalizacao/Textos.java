
package recursos.iternacionalizacao;

/**
 *
 * @author Wesllen Sousa Lima
 */
public class Textos {
    
    /* -----------------------TELAS-------------------------------------------*/
    
    
    
    /* -----------------------DIALOGS-----------------------------------------*/
    
    public static String dialog_escolha() {
        return "Escolha";
    }
    
    public static String dialog_conectando() {
        return "Conectando...";
    }
    
    public static String dialog_conectar() {
        return "Conectar";
    }
    
    public static String dialog_desconectar() {
        return "Desconectar";
    }
    
    public static String dialog_perguntarDesconectar() {
        return "Deseja realmente desconectar?";
    }
    
    public static String dialog_perguntarExcluir() {
        return "Deseja realmente excluir?";
    }
    
    public static String dialog_mensagem() {
        return "Mensagem";
    }
    
    public static String dialog_excluir() {
        return "Excluir";
    }
    public static String dialog_editar() {
        return "Editar";
    }    
    
    public static String dialog_ocultar() {
        return "Ocultar";
    }
    
    public static String dialog_associar() {
        return "Associar";
    }
    
    public static String dialog_iniciarConversa() {
        return "Iniciar conversa";
    }
    
    public static String dialog_tipoConversa() {
        return "Voc� deseja entrar ou criar uma conversa?";
    }
    
    public static String dialog_cliente() {
        return "Entrar";
    }
    
    public static String dialog_servidor() {
        return "Criar";
    }
    
    public static String dialog_ok() {
        return "OK";
    }
    
    public static String dialog_infoIp() {
        return "Voc� est� em uma rede privada. Por esse motivo voc� n�o ser� vis�vel na Internet!";
    }
    
    public static String dialog_naoCadastro() {
        return "Voc� n�o possui cadastro ainda, por favor fa�a seu cadastro.";
    }
    
    public static String dialog_servidorNaoEncontrado() {
        return "Servidor n�o encontrado!";
    }
    
    /* -----------------------MENSAGEM----------------------------------------*/
    
    public static String mensagem_erro() {
        return "Error";
    }
    
    public static String mensagem_sucesso() {
        return "Sucesso";
    }
    
    public static String mensagem_aviso() {
        return "Aviso";
    }
    
    public static String mensagem_confirmacao() {
        return "Confirma��o";
    }
    
    public static String mensagem_escolha() {
        return "Escolha";
    }
    
    public static String mensagem_informeDado() {
        return "Informe o dado";
    }
    
    public static String mensagem_confirmarSaida() {
        return "Deseja realmente sair?";
    }
    
    /* -----------------------LOGIN-------------------------------------------*/
    
     public static String login_senhaInvalida() {
         return "Senha inv�lida!";
     }
     
     public static String login_senhaNaoConrrepondem() {
         return "As senhas n�o correspondem!";
     }  
     
     public static String login_senhaAlterada() {
         return "Senha alterada com sucesso!";
     }
    
    /* -----------------------CADASTRO----------------------------------------*/
     
    public static String cad_msgNome() {
        return "Nome vazio ou inv�lido!";
    }
    
    public static String cad_msgDdd() {
        return "DDD vazio ou inv�lido!";
    }
    
    public static String cad_msgCelular() {
        return "Celular vazio ou inv�lido!";
    }
    
    public static String cad_msgEmail() {
        return "Email inv�lido!";
    }
    
    public static String cad_msgNomeExiste() {
        return "Nome j� existente!";
    }
    
    public static String cad_msgSalvo() {
        return "Contato salvo com sucesso!";
    }
    
    /* -----------------------LISTA CONTATOS----------------------------------*/
    
    public static String listarContatos_excluir() {
        return "Contato exclu�do com sucesso!";
    }
     
    /* -----------------------CLIENTE SERVIDOR--------------------------------*/
    
    public static String cs_servidorNaoEncontrado() {
        return "Servidor n�o encontrado!";
    }
    
    public static String cs_servidorCriado() {
        return "Servidor criado com sucesso!";
    }
    
    public static String celular_sucesso() {
        return "Mensagem enviada com sucesso!";
    }
    
    /* -----------------------CONVERSA----------------------------------------*/
    
    public static String conversa_excluir() {   
        return "Conversa exclu�da com sucesso!";
    }
    
    public static String conversa_desconectar() {   
        return "Deseja realmente desconectar essa conversa?";
    }
    
    public static String conversa_conectar() {   
        return "Sim: para ENTRAR conversa.\nN�o: para CHAMAR na conversa.";
    }
    
    public static String conversa_contatoInexistente() {   
        return "Contato inexistente!";
    }
    
    /* -----------------------NOVA CONVERSA CLIENTE---------------------------*/
    
    public static String novaConversaCliente_sucesso() {
        return "Conversa criada com sucesso!";
    }
    
    /* -----------------------NOVA CONVERSA SERVIDOR--------------------------*/
    
    public static String novaConversaServidor_servicoMensagem() {
        return "Servi�o de mensagem desabilitado!";
    }
    
    public static String novaConversaServidor_localizacao() {
        return "Escolha uma forma de localiza��o!";
    }
    
    public static String novaConversaServidor_sms() {
        return "Solicita��o enviada, espere a confirma��o do contato: ";
    }
    
    public static String novaConversaServidor_smsErro() {
        return "Erro ao enviar SMS, verifique se existe um modem conectado!";
    }
    
    public static String novaConversaServidor_email() {
        return "Email enviado com sucesso!";
    }
    
    public static String novaConversaServidor_semRede() {
        return "Voc� n�o est� conectado em nenhuma rede!";
    }
    
    public static String novaConversaServidor_emailInvalido() {
        return "Contato com email inexistente!";
    }
    
    /* -----------------------ASSOCIAR CONTATOS-------------------------------*/
    
    public static String associarContatos_requisicao() {
        return "Requisi��o enviada, aguarde resposta!";
    }
    
    /* -----------------------ASSOCIAR CONVERSA-------------------------------*/
    
    public static String associarConversa_localizacao() {
        return "Requisi��o enviada, aguarde resposta!";
    }
    
    /* -----------------------CONTATOS CONECTADOS-----------------------------*/
    
    public static String contatosConectados_desconectado() {
        return "Contato desconectado!";
    }
    
    public static String contatosConectados_desconectar() {
        return "Deseja realmente desconectar o usu�rio?";
    }
    
    public static String contatosConectados_verificar() {
        return "Voc� n�o pode desconectar um servidor!";
    }
    
    /* -----------------------SMS---------------------------------------------*/
    
    public static String sms_enviar() {
        return "Erro ao enviar SMS!\n";
    }
    
    public static String sms_fechar() {
        return "Erro ao liberar portas do MODEM!\n";
    }
    
    /* -----------------------EMAIL-------------------------------------------*/
    
    public static String email_origem() {
        return "Erro configurar origem do email: \n";
    }
    
    public static String email_destino() {
        return "Erro configurar destino do email: \n";
    }
    
    public static String email_anexo() {
        return "Erro ao anexar arquivo no email: \n";
    }
    
    public static String email_html() {
        return "Erro ao montar HTML no email: \n";
    }
    
    public static String email_enviar() {
        return "Erro ao enviar email: \n";
    }
    
    public static String email_sucesso() {
        return "Email enviado com sucesso!";
    }
    
    /* -----------------------CONEX�O-----------------------------------------*/
    
    public static String conexao_con() {
        return "Erro de conex�o com o banco de dados!\n";
    }
    
    public static String conexao_banco() {
        return "Driver do banco de Dados n�o encontrada!\n";
    }
    
    /* -----------------------CRIAR SERVIDOR----------------------------------*/
    
    public static String criarServidor_servicoDesabilido() {
        return "Servi�o do servidor desabilitado!";
    }
    
    public static String criarServidor_ativar() {
        return "Ativar";
    }
    
    public static String criarServidor_desativar() {
        return "Desativar";
    }
    
    /* -----------------------CONFIGURA��O------------------------------------*/
    
    public static String config_servidor() {
        return "Servidor";
    }
    
    public static String config_redes() {
        return "Redes";
    }
    
    public static String config_senha() {
        return "Senha";
    }
    
    public static String config_opcoes() {
        return "Op��es";
    }
    
    public static String config_ajuda() {
        return "Ajuda";
    }
    
    public static String config_sobre() {
        return "Sobre";
    }
    
    /* -----------------------AJUDA-------------------------------------------*/
    public static String ajuda_servidor() {
        return "Esse � o servidor, ele � respons�vel por criar uma conex�o que vai gerenciar "
             + "suas conversas. Quando ele est� ativo, outras pessoas podem se conectar ao seu "
             + "celular e conversarem entre si.";
    }
    
    public static String ajuda_conversa() {
        return "Cria e armazena as conversa com seus contatos. Voc� tem a op��o de entrar ou "
                + "criar uma conversa. Criar uma conversa � bem simples, voc� indica com que quer "
                + "conversa e escolhe a forma de enviar localiza��o. Entrar numa conversa voc� deve "
                + "informar o endere�o do outro celular.";
    }
    
    public static String ajuda_contatos() {
        return "Aqui voc� visualiza e adiciona seus contatos com quem voc� deseja conversar.";
    }
    
    public static String ajuda_contatos_conectados() {
        return    "Aqui voc� visualiza os contatos que est�o conectados em seu celular e visualiza "
                + "tamb�m os celulares em que voc� est� conectado. Caso voc� esteja conectado a um "
                + "celular, voc� poder� ver quais s�o as pessoas conectados a ele, podendo iniciar "
                + "uma conversas privada com os contatos do seu amigo ou associar esses contatos a "
                + "uma conversa em andamento.";
    }
    
    public static String ajuda_configuracoes() {
        return "Exibe as configura��es de sua aplica��o.";
    }
    
}
