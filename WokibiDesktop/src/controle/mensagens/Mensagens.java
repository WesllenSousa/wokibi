
package controle.mensagens;

import controle.arquivos.Caminhos;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import recursos.iternacionalizacao.Textos;

/**
 *
 * @author Wesllen Sousa Lima
 */
public class Mensagens {

    public void bug(String mensagem){
        JOptionPane.showMessageDialog(null, mensagem, Textos.mensagem_erro(), JOptionPane.ERROR_MESSAGE,
                new ImageIcon(getClass().getResource(Caminhos.msg_bug())));   
    }
    
    public void sucesso(String mensagem){
        JOptionPane.showMessageDialog(null, mensagem, Textos.mensagem_sucesso(), JOptionPane.WARNING_MESSAGE,
                new ImageIcon(getClass().getResource(Caminhos.msg_sucesso())));
    }
    
    public void aviso(String mensagem){
        JOptionPane.showMessageDialog(null, mensagem, Textos.mensagem_aviso(), JOptionPane.WARNING_MESSAGE,
                new ImageIcon(getClass().getResource(Caminhos.msg_aviso())));
    }

    public boolean confirmacao(String mensagem){
        int conf = JOptionPane.showConfirmDialog(null, mensagem, Textos.mensagem_confirmacao(),
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                new ImageIcon(getClass().getResource(Caminhos.msg_confirmacao())));
        if(conf == JOptionPane.YES_OPTION){
            return true;
        }else{
            return false;
        }
    }

    public String multiplaEscolha(String mensagem, String escolha[]){
        String resp = (String)JOptionPane.showInputDialog(null,
            mensagem, Textos.mensagem_escolha(), JOptionPane.QUESTION_MESSAGE,
            new ImageIcon(getClass().getResource(Caminhos.msg_escolha())),
            escolha, escolha[0]);
        return resp;
    }

    public String inserirDados(String mensagem){
        return (String) JOptionPane.showInputDialog(null, mensagem, Textos.mensagem_informeDado(), 
                JOptionPane.QUESTION_MESSAGE, new ImageIcon(getClass().getResource(Caminhos.msg_entradaDados())), 
                null, null);
    }
    
    public String inserirDadosComValorInicial(String mensagem, String valorInicial){
        return (String) JOptionPane.showInputDialog(null, mensagem, Textos.mensagem_informeDado(), 
                JOptionPane.QUESTION_MESSAGE, new ImageIcon(getClass().getResource(Caminhos.msg_entradaDados())), 
                null, valorInicial);
    }

}
