
package controle.notificacao;

/**
 *
 * @author Wesllen Sousa Lima
 */
public class Notificacao {
    
    private String nome;
    private String mensagem;
    private Integer codigoConversa;

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getCodigoConversa() {
        return codigoConversa;
    }

    public void setCodigoConversa(Integer codigoConversa) {
        this.codigoConversa = codigoConversa;
    }
    
}
