package controle.mensagens;

import controle.utilitarios.Instancias;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import recursos.iternacionalizacao.Textos;

/**
 *
 * @author Wesllen Sousa Lima
 */
//Gmail: smtp.gmail.com, Porta: 465
public class Email {

    private HtmlEmail htmlEmail;

    public Email(String host, Integer porta) {
        htmlEmail = new HtmlEmail();
        htmlEmail.setHostName(host); // o servidor SMTP para envio do e-mail
        htmlEmail.setSmtpPort(porta);
    }

    public void origem(String email, String nome) {
        try {
            htmlEmail.setFrom(email, nome);
        } catch (EmailException ex) {
            Instancias.getMensagens().bug(Textos.email_origem() + ex);
        }
    }

    public void destino(String email, String nome) {
        try {
            htmlEmail.addTo(email, nome);
        } catch (EmailException ex) {
            Instancias.getMensagens().bug(Textos.email_destino() + ex);
        }
    }

    public void autenticacao(String usuario, String senha) {
        htmlEmail.setAuthentication(usuario, senha);
        htmlEmail.setSSL(true);
        htmlEmail.setTLS(true);
    }

    public void anexo(String caminho, String nome) {
        try {
            htmlEmail.embed(caminho, nome);
        } catch (EmailException ex) {
            Instancias.getMensagens().bug(Textos.email_anexo() + ex);
        }
    }

    public void corpo(String assunto, String mensagem) {
        htmlEmail.setSubject(assunto);
        try {
            htmlEmail.setHtmlMsg(mensagem);
        } catch (EmailException ex) {
            Instancias.getMensagens().bug(Textos.email_html() + ex);
        }
    }

    public Boolean enviar() {
        try {
            htmlEmail.send();
            return true;
        } catch (EmailException ex) {
            Instancias.getMensagens().bug(Textos.email_enviar() + ex);
            return false;
        }
    }
    
}
