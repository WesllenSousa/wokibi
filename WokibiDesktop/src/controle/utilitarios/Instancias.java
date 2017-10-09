package controle.utilitarios;

import controle.clienteServidor.Cliente;
import controle.clienteServidor.Servidor;
import controle.mensagens.Mensagens;
import controle.multimidia.ExecutarAudio;
import controle.notificacao.Notificacao;
import entidades.contato.bean.ContatoBean;
import java.util.ArrayList;
import java.util.HashMap;
import telas.TelaContatosConectados;
import telas.TelaPrincipal;
import telas.dependencias.EsperarCirculo;

public class Instancias {

    private static Servidor servidor;
    private static HashMap<Integer, Cliente> conversasCliente = new HashMap<>();
    private static HashMap<Integer, Cliente> conversasServidor = new HashMap<>();
    private static ContatoBean dono;
    private static Mensagens mensagens;
    private static ExecutarAudio executarAudio;
    private static TelaPrincipal telaPrincipal;
    private static TelaContatosConectados telaContatosConectados;
    private static ArrayList<Notificacao> notificacoes;
    private static EsperarCirculo esperarCirculo;
    
    private static Integer entidadeSelecionada;
    private static Object telaSelecionada;

    public static Servidor getServidor() {
        return servidor;
    }

    public static void setServidor(Servidor servidor) {
        Instancias.servidor = servidor;
    }

    public static HashMap<Integer, Cliente> getConversasCliente() {
        return conversasCliente;
    }

    public static void setConversasCliente(
            HashMap<Integer, Cliente> conversasCliente) {
        Instancias.conversasCliente = conversasCliente;
    }

    public static ContatoBean getDono() {
        return dono;
    }

    public static void setDono(ContatoBean dono) {
        Instancias.dono = dono;
    }

    public static Mensagens getMensagens() {
        return mensagens;
    }

    public static void setMensagens(Mensagens mensagens) {
        Instancias.mensagens = mensagens;
    }

    public static ExecutarAudio getExecutarAudio() {
        return executarAudio;
    }

    public static void setExecutarAudio(ExecutarAudio executarAudio) {
        Instancias.executarAudio = executarAudio;
    }

    public static TelaPrincipal getTelaPrincipal() {
        return telaPrincipal;
    }

    public static void setTelaPrincipal(TelaPrincipal telaPrincipal) {
        Instancias.telaPrincipal = telaPrincipal;
    }

    public static Integer getEntidadeSelecionada() {
        return entidadeSelecionada;
    }

    public static void setEntidadeSelecionada(Integer entidadeSelecionada) {
        Instancias.entidadeSelecionada = entidadeSelecionada;
    }

    public static Object getTelaSelecionada() {
        return telaSelecionada;
    }

    public static void setTelaSelecionada(Object telaSelecionada) {
        Instancias.telaSelecionada = telaSelecionada;
    }

    public static ArrayList<Notificacao> getNotificacoes() {
        return notificacoes;
    }

    public static void setNotificacoes(ArrayList<Notificacao> notificacoes) {
        Instancias.notificacoes = notificacoes;
    }

    public static TelaContatosConectados getTelaContatosConectados() {
        return telaContatosConectados;
    }

    public static void setTelaContatosConectados(TelaContatosConectados telaContatosConectados) {
        Instancias.telaContatosConectados = telaContatosConectados;
    }

    public static EsperarCirculo getEsperarCirculo() {
        return esperarCirculo;
    }

    public static void setEsperarCirculo(EsperarCirculo esperarCirculo) {
        Instancias.esperarCirculo = esperarCirculo;
    } 

    public static HashMap<Integer, Cliente> getConversasServidor() {
        return conversasServidor;
    }

    public static void setConversasServidor(HashMap<Integer, Cliente> conversasServidor) {
        Instancias.conversasServidor = conversasServidor;
    }
    
}
