
import com.jtattoo.plaf.mint.MintLookAndFeel;
import controle.mensagens.Mensagens;
import controle.multimidia.ExecutarAudio;
import controle.notificacao.Notificacao;
import controle.utilitarios.Instancias;
import java.util.ArrayList;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import telas.TelaLogin;
import util.Conexao;


/**
 *
 * @author Wesllen Sousa Lima
 */
public class Main {
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new MintLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
        }
        
        Conexao.criarBanco();
        
        Mensagens mensagens = new Mensagens();
        ExecutarAudio executarAudio = new ExecutarAudio();
        ArrayList<Notificacao> notificacoes = new ArrayList<>();
        Instancias.setMensagens(mensagens);
        Instancias.setExecutarAudio(executarAudio);
        Instancias.setNotificacoes(notificacoes);
        
        TelaLogin dialog = new TelaLogin(null, false);
        if(TelaLogin.fechar) {
            dialog.dispose();
        } else {
            dialog.setVisible(true);
        }
    }
    
}
