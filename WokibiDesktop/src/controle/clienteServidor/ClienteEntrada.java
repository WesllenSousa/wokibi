package controle.clienteServidor;

import controle.mensagens.TrataMensagem;
import entidades.contato.bean.ContatoBean;
import entidades.conversa.bean.ConversaBean;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ClienteEntrada implements Runnable {

    private ObjectInputStream entrada;
    private Servidor servidor;
    private ClienteSaida clienteSaida;
    private TrataMensagem tratarMensagem;
    private ConversaBean conversa;
    private ContatoBean contato;
    private Integer identificacaoConversa;

    public ClienteEntrada(ClienteSaida clienteSaida, Servidor servidor) {
        tratarMensagem = new TrataMensagem();
        this.clienteSaida = clienteSaida;
        this.servidor = servidor;
    }

    @Override
    public void run() {
        configuraEntrada();
        configuraProcessa();
    }

    public void configuraEntrada() {
        try {
            entrada = new ObjectInputStream(clienteSaida.getSocket().getInputStream());
        } catch (IOException ex) {
            System.out.println("Cliente Entrada-configurar Entrada: " + ex);
        }
    }

    private void configuraProcessa() {
        try {
            do {
                String mensagem = entrada.readObject().toString();
                tratarMensagem.verificarMensagemServidor(this, mensagem);
            } while (servidor.getStatusConexao());
        } catch (IOException ex) {
            System.out.println("Cliente Entrada-configurarProcessar: " + ex);
        } catch (ClassNotFoundException ex) {
            System.out.println("Cliente Entrada=configurarProcessar: " + ex);
        }
    }

    public void fecharConexao() {
        try {
            servidor.removerClienteConversa(identificacaoConversa, this);
            tratarMensagem.desconectarConversa(conversa);
            tratarMensagem.desconectarContato(contato);
            entrada.close();
            clienteSaida.getSaida().close();
            clienteSaida.getSocket().close();
        } catch (IOException ex) {
            System.out.println("Cliente Entrada-fecharConexao: " + ex);
        }
    }
    
    public void fecharConexaoServidor() {
        try { 
            entrada.close();
            clienteSaida.getSaida().close();
            clienteSaida.getSocket().close();
        } catch (IOException ex) {
            System.out.println("Cliente Entrada-fecharConexao: " + ex);
        }
    }

    /*
     * GETTERS SETTERS
     */
    public Servidor getServidor() {
        return servidor;
    }

    public ClienteSaida getClienteSaida() {
        return clienteSaida;
    }

    public ConversaBean getConversa() {
        return conversa;
    }

    public void setConversa(ConversaBean conversa) {
        this.conversa = conversa;
    }

    public ContatoBean getContato() {
        return contato;
    }

    public void setContato(ContatoBean contato) {
        this.contato = contato;
    }

    public ObjectInputStream getEntrada() {
        return entrada;
    }

    public TrataMensagem getTratarMensagem() {
        return tratarMensagem;
    }

    public Integer getIdentificacaoConversa() {
        return identificacaoConversa;
    }

    public void setIdentificacaoConversa(Integer identificacaoConversa) {
        this.identificacaoConversa = identificacaoConversa;
        servidor.adicionarClienteConversa(identificacaoConversa, this);
    }
    
}
