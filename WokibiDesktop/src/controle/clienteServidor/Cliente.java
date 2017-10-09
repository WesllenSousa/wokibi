package controle.clienteServidor;

import controle.mensagens.TrataMensagem;
import controle.utilitarios.ConstantesDiversas;
import controle.utilitarios.Instancias;
import entidades.contato.bean.ContatoBean;
import entidades.conversa.bean.ConversaBean;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import recursos.iternacionalizacao.Textos;

public class Cliente implements Runnable {

    private Socket socket;
    private ObjectOutputStream saida;
    private ObjectInputStream entrada;
    private Boolean statusConexao;
    private TrataMensagem tratarMensagem;
    private ConversaBean conversa;
    private ContatoBean contato;
    private Integer identificacaoConversa;
    private String host;
    private Integer tipo; //0: conversa aberta pelo cliente, 1: conversa aberta pelo servidor, 2: Associar conversa, 3: conversa privada
    private String nomeConversa;
   
    public Cliente(String host, Integer tipo, String nomeConversa) {
        this.nomeConversa = nomeConversa;
        tratarMensagem = new TrataMensagem();
        this.host = host;
        this.tipo = tipo;
    }

    @Override
    public void run() {
        try {
            socket = new Socket(host, ConstantesDiversas.CS_PORTA);
            statusConexao = true;
            System.out.println("Cliente - Conexão realizada com sucesso! ");
        } catch (IOException ex) {
            System.out.println("Cliente-criarSocket: - "+ host + " - " + ex);
            Instancias.getEsperarCirculo().fechar();
            Instancias.getMensagens().bug(Textos.cs_servidorNaoEncontrado());
            statusConexao = false;
        }
        if (statusConexao) {
            configuraEntradaSaida();
            processarConexao();
        }
    }

    private void configuraEntradaSaida() {
        try {
            saida = new ObjectOutputStream(socket.getOutputStream());
            saida.flush();
            entrada = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
            System.out.println("Cliente: " + ex);
        }
    }

    private void processarConexao() {
        verificarTipoCliente();
        do {
            try {
                String mensagem = entrada.readObject().toString();
                Instancias.getExecutarAudio().mensagem();
                tratarMensagem.verificaMensagemCliente(this, mensagem);
            } catch (IOException | ClassNotFoundException ex) {
                fecharConexao();
                System.out.println("Cliente-processarConexao: " + ex);
            }
        } while (statusConexao);
    }

    public void fecharConexao() {
        enviarDados(ConstantesDiversas.CS_FIM);
        try {
            tratarMensagem.desconectarConversa(conversa);
            tratarMensagem.desconectarContato(contato);
            saida.close();
            entrada.close();
            socket.close();
            statusConexao = false;
            Instancias.getExecutarAudio().clienteDesconectado();
        } catch (IOException ex) {
            System.out.println("Cliente-fecharConexao: " + ex);
        }
    }

    public void enviarDados(Object dado) {
        try {
            saida.writeObject(dado);
            saida.flush();
        } catch (IOException ex) {
            System.out.println("Cliente-enviarDados: " + ex);
        }
    }

    private void verificarTipoCliente() {
        if (tipo == 0) {
            tratarMensagem.enviarMensagemServidor(this, ConstantesDiversas.CS_INICIAR_CONVERSA);
        } else if (tipo == 1) {
            tratarMensagem.enviarMensagemServidor(this, ConstantesDiversas.CS_INICIAR_CONVERSA_SERVIDOR);
        } else if (tipo == 2) {
            tratarMensagem.enviarMensagemServidor(this, ConstantesDiversas.CS_ASSOCIAR_CONVERSA);
        } else if (tipo == 3) {
            tratarMensagem.enviarMensagemServidor(this, ConstantesDiversas.CS_CRIAR_CONVERSA_PRIVADA);
        }
    }

    /*
     * GETs
     */
    public Boolean getStatusConexao() {
        return statusConexao;
    }

    public ObjectInputStream getEntrada() {
        return entrada;
    }

    public TrataMensagem getTratarMensagem() {
        return tratarMensagem;
    }

    public Socket getSocket() {
        return socket;
    }

    public ContatoBean getContato() {
        return contato;
    }

    public void setContato(ContatoBean contato) {
        this.contato = contato;
    }

    public ConversaBean getConversa() {
        return conversa;
    }

    public void setConversa(ConversaBean conversa) {
        this.conversa = conversa;
    }

    public Integer getIdentificacaoConversa() {
        return identificacaoConversa;
    }

    public void setIdentificacaoConversa(Integer identificacaoConversa) {
        this.identificacaoConversa = identificacaoConversa;
    }

    public String getNomeConversa() {
        return nomeConversa;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }
    
}
