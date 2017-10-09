
package telas.listas;

import java.awt.Dimension;
import telas.TelaContatosConectados;

/**
 *
 * @author Wesllen Sousa Lima
 */
public class ExibirListaContatosConectados{

    private Integer y, altura;  
    
    public ExibirListaContatosConectados() {
        y = 1;
        altura = 43;
    }
    
    public void exibirContatosServidor(TelaContatosConectados tela, String usuario, String ip, Integer identificacao) {        
        Dimension dimension = tela.pn_servidores.getPreferredSize();
        Integer width = dimension.width;
        dimension.setSize(width, y+altura);
        
        ListaContatosConectados lista = new ListaContatosConectados(altura, y);
        lista.setUsuario(usuario);
        lista.setIp(ip);
        lista.setIdentificacao(identificacao);
        lista.setClienteOuServidor(1);

        tela.pn_servidores.add(lista);
        tela.pn_servidores.setPreferredSize(dimension);
        tela.pn_servidores.revalidate();
        
        y += altura;
    }
    
    public void exibirContatosCliente(TelaContatosConectados tela, String usuario, String ip, Integer identificacao) {        
        Dimension dimension = tela.pn_clientes.getPreferredSize();
        Integer width = dimension.width;
        dimension.setSize(width, y+altura);
        
        ListaContatosConectados lista = new ListaContatosConectados(altura, y);
        lista.setUsuario(usuario);
        lista.setIp(ip);
        lista.setIdentificacao(identificacao);
        lista.setClienteOuServidor(0);

        tela.pn_clientes.add(lista);
        tela.pn_clientes.setPreferredSize(dimension);
        tela.pn_clientes.revalidate();
        
        y += altura;
    }
    
}
