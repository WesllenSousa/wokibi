package controle.clienteServidor;

import controle.utilitarios.ConstantesDiversas;
import controle.utilitarios.Instancias;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class Servidor implements Runnable {

    private ServerSocket serverSocket;
    private HashMap<Integer, ArrayList<ClienteEntrada>> conversas;
    private Boolean statusConexao;

    public Servidor() {
        conversas = new HashMap<>();
        try {
            serverSocket = new ServerSocket(ConstantesDiversas.CS_PORTA);
            statusConexao = true;
            Instancias.getExecutarAudio().servidorConectado();
            Instancias.setServidor(Servidor.this);
            System.out.println("Servidor criado com sucesso!");
        } catch (IOException ex) {
            System.out.println("Servidor: " + ex);
            statusConexao = false;
        }
    }

    @Override
    public void run() {
        while (statusConexao) {
            esperaConexoes();
        }
    }

    private void esperaConexoes() {
        try {
            Socket socket = serverSocket.accept();
            System.out.println("Conexão aceita: " + socket.getInetAddress().getHostAddress());
            
            ClienteSaida clienteSaida = new ClienteSaida(socket);
            Thread thread = new Thread(clienteSaida);
            thread.start();

            ClienteEntrada clienteEntrada = new ClienteEntrada(clienteSaida, this);
            Thread thread1 = new Thread(clienteEntrada);
            thread1.start();

            Instancias.getExecutarAudio().clienteConectado();
        } catch (IOException ex) {
            System.out.println("Servidor-aceitarConexao: " + ex);
        }
    }

    public void enviarDadosTodos(Integer identificacaoConversa, Object dado, Integer porta) {
        if (conversas.get(identificacaoConversa) != null) {
            for (ClienteEntrada clienteEntrada : conversas.get(identificacaoConversa)) {
                if (clienteEntrada.getClienteSaida().getSocket().getPort() != porta) {
                    clienteEntrada.getClienteSaida().enviarDados(dado);
                }
            }
        }
    }

    public void enviarDadosUm(Integer identificacaoConversa, Object dado, Integer porta) {
        for (ClienteEntrada clienteEntrada : conversas.get(identificacaoConversa)) {
            if (clienteEntrada.getClienteSaida().getSocket().getPort() == porta) {
                clienteEntrada.getClienteSaida().enviarDados(dado);
            }
        }
    }

    public void fechaConexao() {
        do {
            for (ArrayList<ClienteEntrada> clientesEntradas : conversas.values()) {
                Boolean status = false;
                for (ClienteEntrada clienteEntrada : clientesEntradas) {
                    removerClienteConversa(clienteEntrada.getIdentificacaoConversa(), clienteEntrada);
                    clienteEntrada.getTratarMensagem().desconectarConversa(clienteEntrada.getConversa());
                    clienteEntrada.getTratarMensagem().desconectarContato(clienteEntrada.getContato());
                    clienteEntrada.getClienteSaida().fechaConexao();
                    clienteEntrada.fecharConexao();
                    status = true;
                    break;
                }
                if (status) {
                    break;
                }
            }
        } while (!conversas.values().isEmpty());
        try {
            serverSocket.close();
            statusConexao = false;
            Instancias.setServidor(null);
            Instancias.getExecutarAudio().servidorDesconectado();
        } catch (IOException ex) {
            System.out.println("Servidor-fecharConexao: " + ex);
        }
    }

    /*
     * GETs
     */
    public Boolean getStatusConexao() {
        return statusConexao;
    }

    public HashMap<Integer, ArrayList<ClienteEntrada>> getConversas() {
        return conversas;
    }

    public void adicionarClienteConversa(Integer identificacaoConversa, ClienteEntrada clienteEntrada) {
        if (!conversas.containsKey(identificacaoConversa)) {
            ArrayList<ClienteEntrada> ce = new ArrayList<>();
            conversas.put(identificacaoConversa, ce);
        }
        ArrayList<ClienteEntrada> clientesEntrada = conversas.get(identificacaoConversa);
        if (!clientesEntrada.contains(clienteEntrada)) {
            clientesEntrada.add(clienteEntrada);
            System.out.println("Servidor adicionou o cliente a identificação da conversa: " + identificacaoConversa);
        }
    }

    public void removerClienteConversa(Integer identificacaoConversa, ClienteEntrada clienteEntrada) {
        ArrayList<ClienteEntrada> clientes = conversas.get(identificacaoConversa);
        if (clientes != null) {
            for (ClienteEntrada ce : clientes) {
                if (ce.getClienteSaida().getSocket().getPort() == clienteEntrada.getClienteSaida().getSocket().getPort()) {
                    clientes.remove(ce);
                    System.out.println("Servidor removeu o cliente da identificação da conversa: " + identificacaoConversa);
                    break;
                }
            }
        }
        if (clientes != null && clientes.isEmpty()) {
            conversas.remove(identificacaoConversa);
            System.out.println("Servidor removeu a conversa: " + identificacaoConversa);
        }
    }

    public Integer gerarIdentificacao() {
        return conversas.size() + 1;
    }
}
