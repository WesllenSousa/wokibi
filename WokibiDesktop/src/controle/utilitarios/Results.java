
package controle.utilitarios;

import telas.TelaClientesConectados;
import telas.TelaConversa;
import telas.TelaListarContatos;
import telas.TelaMensagens;
import telas.TelaNovaConversaCliente;
import telas.TelaPrincipal;

/**
 *
 * @author Wesllen Sousa Lima
 */
public class Results {
    
    private static TelaPrincipal telaPrincipal;
    private static TelaConversa telaConversa;
    private static TelaNovaConversaCliente telaNovaConversaCliente;
    private static TelaListarContatos telaListarContatos;
    private static TelaMensagens telaMensagens;
    private static TelaClientesConectados telaClientesConectados;

    public static TelaConversa getTelaConversa() {
        return telaConversa;
    }

    public static void setTelaConversa(TelaConversa telaConversa) {
        Results.telaConversa = telaConversa;
    }

    public static TelaListarContatos getTelaListarContatos() {
        return telaListarContatos;
    }

    public static void setTelaListarContatos(TelaListarContatos telaListarContatos) {
        Results.telaListarContatos = telaListarContatos;
    }

    public static TelaPrincipal getTelaPrincipal() {
        return telaPrincipal;
    }

    public static void setTelaPrincipal(TelaPrincipal telaPrincipal) {
        Results.telaPrincipal = telaPrincipal;
    }

    public static TelaMensagens getTelaMensagens() {
        return telaMensagens;
    }

    public static void setTelaMensagens(TelaMensagens telaMensagens) {
        Results.telaMensagens = telaMensagens;
    }

    public static TelaClientesConectados getTelaClientesConectados() {
        return telaClientesConectados;
    }

    public static void setTelaClientesConectados(TelaClientesConectados telaClientesConectados) {
        Results.telaClientesConectados = telaClientesConectados;
    }

    public static TelaNovaConversaCliente getTelaNovaConversaCliente() {
        return telaNovaConversaCliente;
    }

    public static void setTelaNovaConversaCliente(TelaNovaConversaCliente telaNovaConversaCliente) {
        Results.telaNovaConversaCliente = telaNovaConversaCliente;
    }
    
}
