
import entidades.conversa.facade.ConversaFacade;
import entidades.conversa.facade.ConversaFacadeImpl;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.FacadeException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Wesllen Sousa Lima
 */
public class Teste {
    
    public static void main(String[] args) {
        ConversaFacade conversaFacade = new ConversaFacadeImpl();
        try {
            for(Object object : conversaFacade.listar()) {
                System.out.println(object);
            }
        } catch (FacadeException ex) {
            Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
