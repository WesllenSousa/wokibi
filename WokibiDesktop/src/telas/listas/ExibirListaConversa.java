
package telas.listas;

import java.awt.Dimension;
import telas.TelaConversa;

/**
 *
 * @author Wesllen Sousa Lima
 */
public class ExibirListaConversa{
    
    private TelaConversa tela;
    private Integer y, altura, completar;  
    
    public ExibirListaConversa(TelaConversa tela) {
        this.tela = tela;
        y = 1;
        altura = 42;
        completar = 15;
    }
    
    public void exibirAtivo(String usuario, Integer icone, Integer identificacao) {
        Dimension dimension = tela.pn_ativo.getPreferredSize();
        Integer width = dimension.width;
        dimension.setSize(width, y+altura+completar);
        
        ListaConversa listaConversa = new ListaConversa(altura, y);
        listaConversa.setConversaAtivaOuInativa(true);
        listaConversa.setUsuario(usuario);
        listaConversa.setIcone(icone);
        listaConversa.setIdentificacao(identificacao); 
        
        tela.pn_ativo.add(listaConversa);
        tela.pn_ativo.setPreferredSize(dimension);
        tela.pn_ativo.revalidate();
        
        y += altura;
    }
    
    public void exibirInativo(String usuario, Integer icone, Integer identificacao) {
        Dimension dimension = tela.pn_inativo.getPreferredSize();
        Integer width = dimension.width;
        dimension.setSize(width, y+altura+completar);
        
        ListaConversa listaConversa = new ListaConversa(altura, y);
        listaConversa.setConversaAtivaOuInativa(false);
        listaConversa.setUsuario(usuario);
        listaConversa.setIcone(icone);
        listaConversa.setIdentificacao(identificacao);
      
        tela.pn_inativo.add(listaConversa);
        tela.pn_inativo.setPreferredSize(dimension);
        tela.pn_inativo.revalidate();
        
        y += altura;
    }
    
    public void zerarY() {
        y = 1;
    }
    
}
