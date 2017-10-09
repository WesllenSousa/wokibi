
package telas.listas;

import java.awt.Dimension;
import telas.TelaAssociarContato;
import telas.TelaNovaConversaServidor;

/**
 *
 * @author Wesllen Sousa Lima
 */
public class ExibirListaChekBox{

    private Integer y, altura;  
    
    public ExibirListaChekBox() {
        y = 1;
        altura = 43;
    }
    
    public void exibirNovaConversaServidor(TelaNovaConversaServidor tela, String valor, Integer identificacao) {        
        Dimension dimension = tela.pn_contatos.getPreferredSize();
        Integer width = dimension.width;
        dimension.setSize(width, y+altura);
        
        ListaCheckBox lista = new ListaCheckBox(altura, y);
        lista.setValor(valor);
        lista.setIdentificacao(identificacao);

        tela.pn_contatos.add(lista);
        tela.pn_contatos.setPreferredSize(dimension);
        tela.pn_contatos.revalidate();
        
        y += altura;
    }
    
    public void exibirAssociarContatosConectados(TelaAssociarContato tela, String valor, Integer identificacao) {        
        Dimension dimension = tela.pn_conectados.getPreferredSize();
        Integer width = dimension.width;
        dimension.setSize(width, y+altura);
        
        ListaCheckBox lista = new ListaCheckBox(altura, y);
        lista.setValor(valor);
        lista.setIdentificacao(identificacao);

        tela.pn_conectados.add(lista);
        tela.pn_conectados.setPreferredSize(dimension);
        tela.pn_conectados.revalidate();
        
        y += altura;
    }
    
    public void exibirAssociarContatosDesconectados(TelaAssociarContato tela, String valor, Integer identificacao) {        
        Dimension dimension = tela.pn_desconectados.getPreferredSize();
        Integer width = dimension.width;
        dimension.setSize(width, y+altura);
        
        ListaCheckBox lista = new ListaCheckBox(altura, y);
        lista.setValor(valor);
        lista.setIdentificacao(identificacao);

        tela.pn_desconectados.add(lista);
        tela.pn_desconectados.setPreferredSize(dimension);
        tela.pn_desconectados.revalidate();
        
        y += altura;
    }
    
}
