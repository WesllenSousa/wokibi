
package telas.listas;

import java.awt.Dimension;
import telas.TelaAssociarConversa;

/**
 *
 * @author Wesllen Sousa Lima
 */
public class ExibirListaRadioButton{
    
    private Integer y, altura;  
    
    public ExibirListaRadioButton() {
        y = 1;
        altura = 43;
    }
    
    public void exibir(TelaAssociarConversa tela, String conversa, Integer identificacao) {
        Dimension dimension = tela.pn_conversas.getPreferredSize();
        Integer width = dimension.width;
        dimension.setSize(width, y+altura);
        
        ListaRadioButton panelMensagem = new ListaRadioButton(altura, y, tela);
        panelMensagem.setValor(conversa);
        panelMensagem.setIdentificacao(identificacao);

        tela.pn_conversas.add(panelMensagem);
        tela.pn_conversas.setPreferredSize(dimension);
        tela.pn_conversas.revalidate();
        
        y += altura;
    }
    
}
