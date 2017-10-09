package controle.clienteServidor;

import controle.utilitarios.ConstantesDiversas;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClienteSaida implements Runnable {

    private ObjectOutputStream saida;
    private Socket socket;

    public ClienteSaida(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        configurarSaida();
    }

    private void configurarSaida() {
        try {
            saida = new ObjectOutputStream(socket.getOutputStream());
            saida.flush();
        } catch (IOException ex) {
            System.out.println("Cliente Saída-configurarSaida: " + ex);
        }
    }

    public void enviarDados(Object dado) {
        try {
            saida.writeObject(dado);
            saida.flush();
        } catch (IOException ex) {
            System.out.println("Cliente Saída-enviarDados: " + ex);
        }
    }

    public void fechaConexao() {
        enviarDados(ConstantesDiversas.CS_FIM);
    }

    /*
     * GETs
     */
    public Socket getSocket() {
        return socket;
    }

    public ObjectOutputStream getSaida() {
        return saida;
    }
    
}
