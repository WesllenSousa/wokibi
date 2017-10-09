package controle.arquivos;

/**
 *
 * @author Wesllen Sousa Lima
 */
public class Caminhos {
    
    public static String logo_100(){
        return "/recursos/imagens/logo.png";
    }
    
    /*
     * IMAGENS
     */  
    
    //Dilogo Mensagens
    public static String msg_bug(){
        return "/recursos/imagens/mensagens/bug_64x64.png";
    }
    
    public static String msg_confirmacao(){
        return "/recursos/imagens/mensagens/confirmacao_64x64.png";
    }
    
    public static String msg_sucesso(){
        return "/recursos/imagens/mensagens/sucesso_64x64.png";
    }
    
    public static String msg_aviso(){
        return "/recursos/imagens/mensagens/aviso_64x64.png";
    }
    
    public static String msg_escolha(){
        return "/recursos/imagens/mensagens/escolha_64x64.png";
    }
    
    public static String msg_entradaDados(){
        return "/recursos/imagens/mensagens/inserirDados_64x64.png";
    }
    
    //Status
    public static String geral_servidorAtivo(){
        return "/recursos/imagens/servidor_36.png";
    }
    
    public static String geral_servidorInativo(){
        return "/recursos/imagens/servidord_36.png";
    }
    
    public static String geral_conversaAtiva(){
        return "/recursos/imagens/conversa_ativa_32.png";
    }
    
    public static String geral_conversaInativa(){
        return "/recursos/imagens/conversa_inativa_32.png";
    }
    
    public static String geral_wifiAtiva(){
        return "/recursos/imagens/wifi_ativada_36.png";
    }
    
    public static String geral_wifiInativa(){
        return "/recursos/imagens/wifi_desativadad_36.png";
    }
    
    //Mensagens
    public static String mensagem_enviarDesativado(){
        return "/recursos/imagens/enviar_desativado_36.png";
    }
    
    public static String mensagem_sairDesativado(){
        return "/recursos/imagens/saird_36.png";
    }
    
    public static String mensagem_addContatoDesativado(){
        return "/recursos/imagens/adicionar_contato_desativado_36.png";
    }
    
    public static String mensagem_desocultar(){
        return "/recursos/imagens/desocultar_36.png";
    }
    
    public static String mensagem_ocultar(){
        return "/recursos/imagens/ocultar_36.png";
    }
    
//=================FORA DO ARQUIVO============================================    

    /*
     * AUDIO
     */
    public static String audio_servidorConectado() {
        return Raiz.getRaiz()+"/audio/servidor_criado.wav";
    }

    public static String audio_servidorDesconectado() {
        return Raiz.getRaiz()+"/audio/servidor_parado.wav";
    }

    public static String audio_clienteConectado() {
        return Raiz.getRaiz()+"/audio/cliente_conecado.wav";
    }

    public static String audio_clienteDesconectado() {
        return Raiz.getRaiz()+"/audio/cliente_desconectado.wav";
    }

    public static String audio_mensagem() {
        return Raiz.getRaiz()+"/audio/mensagem.wav";
    }
 
    /*
     * Arquivo
     */
    public static String config_servicoServidor() {
        return Raiz.getRaiz()+"/config/servicoServidor.config";
    }
    
    public static String config_servicoMensagem() {
        return Raiz.getRaiz()+"/config/servicoMensagem.config";
    }
    
    public static String config_senhaAnterior() {
        return Raiz.getRaiz()+"/config/senhaAnterior.config";
    }
    
    public static String config_audio() {
        return Raiz.getRaiz()+"/config/audio.config";
    }
    
    public static String config_modem() {
        return Raiz.getRaiz()+"/config/modem.config";
    }
    
}
