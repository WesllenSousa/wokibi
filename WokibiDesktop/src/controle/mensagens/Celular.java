
package controle.mensagens;

import controle.mensagens.modem.SerialToGsm;
import controle.utilitarios.ConstantesDiversas;

/**
 *
 * @author Wesllen Sousa Lima
 */
public class Celular {
    
    public static SerialToGsm serialToGsm = new SerialToGsm(ConstantesDiversas.PORTA_MODEM);
    
    private String numero;
    private String ddd;
    private String mensagem;
    
    public Celular(Integer numero, Integer ddd, String mensagem) {
        this.numero = numero.toString();
        this.ddd = ddd.toString();
        this.mensagem = mensagem;
    }
    
    public Boolean modem() {  
        if(serialToGsm.iniciar()) {
            String numeroCompleto = "+55" + ddd + numero;
            String sss = serialToGsm.sendSms(numeroCompleto, mensagem);
            serialToGsm.liberarPortas();
            if(!sss.equals("ERROR")) {
                return true;      
            }
            return false;
        } else return false;
    }
    
}
