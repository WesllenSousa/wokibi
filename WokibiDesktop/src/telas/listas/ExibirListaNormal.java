
package telas.listas;

import controle.utilitarios.ConstantesDiversas;
import java.awt.Dimension;
import telas.TelaClientesConectados;
import telas.TelaConfiguracoes;
import telas.TelaCriarServidor;
import telas.TelaListarContatos;
import telas.TelaNovaConversaServidor;
import telas.dependencias.DialogLista;

/**
 *
 * @author Wesllen Sousa Lima
 */
public class ExibirListaNormal{
    
    private Integer y, altura, completar;  
    
    public ExibirListaNormal() {
        y = 1;          //Eixo y
        altura = 60;    //Altura do painel lista
        completar = 15; //Completa o tamanho do painel
    }
    
    public void exibirConfiguracoes(String valor, TelaConfiguracoes tela, Integer identificacao) {
        Dimension dimension = tela.pn_configuracoes.getPreferredSize();
        Integer width = dimension.width;
        dimension.setSize(width, y+altura+completar);
        
        ListaNormal listaNormal = new ListaNormal(altura, y, identificacao, tela);
        listaNormal.setValor(valor);
        
        tela.pn_configuracoes.add(listaNormal);
        tela.pn_configuracoes.setPreferredSize(dimension);
        tela.pn_configuracoes.revalidate();
        
        y += altura;
    }
    
    public void exibirDialogLista(String valor, DialogLista tela, Integer identificacao) {
        Dimension dimension = tela.pn_geral.getPreferredSize();
        Integer width = dimension.width;
        dimension.setSize(width, y+altura+completar);
        
        ListaNormal listaNormal = new ListaNormal(altura, y, identificacao, tela);
        listaNormal.setValor(valor);
        
        tela.pn_geral.add(listaNormal);
        tela.pn_geral.setPreferredSize(dimension);
        tela.pn_geral.revalidate();
        
        y += altura;
    }
    
     public void exibirCriarServidor(String valor, TelaCriarServidor tela, Integer identificacao) {
        Dimension dimension = tela.pn_contatos.getPreferredSize();
        Integer width = dimension.width;
        dimension.setSize(width, y+altura+completar);
        
        ListaNormal listaNormal = new ListaNormal(altura, y, identificacao, tela);
        listaNormal.setValor(valor);
        
        tela.pn_contatos.add(listaNormal);
        tela.pn_contatos.setPreferredSize(dimension);
        tela.pn_contatos.revalidate();
        
        y += altura;
     }
     
     public void exibirListarContatosDono(String valor, TelaListarContatos tela, Integer identificacao) {
        Dimension dimension = tela.pn_dono.getPreferredSize();
        Integer width = dimension.width;
        dimension.setSize(width, y+altura+completar);
        
        ListaNormal listaNormal = new ListaNormal(altura, y, identificacao, tela);
        listaNormal.setValor(valor);
        
        tela.pn_dono.add(listaNormal);
        tela.pn_dono.setPreferredSize(dimension);
        tela.pn_dono.revalidate();
        
        y += altura;
     }
     
     public void exibirListarContatosAmigos(String valor, TelaListarContatos tela, Integer identificacao) {
        Dimension dimension = tela.pn_amigos.getPreferredSize();
        Integer width = dimension.width;
        dimension.setSize(width, y+altura+completar);
        
        ListaNormal listaNormal = new ListaNormal(altura, y, identificacao, tela);
        listaNormal.setValor(valor);
        
        tela.pn_amigos.add(listaNormal);
        tela.pn_amigos.setPreferredSize(dimension);
        tela.pn_amigos.revalidate();
        
        y += altura;
     }
     
     public void exibirNovaConversaServidor(String valor, TelaNovaConversaServidor tela, Integer identificacao) {
        Dimension dimension = tela.pn_contatos.getPreferredSize();
        Integer width = dimension.width;
        dimension.setSize(width, y+altura+completar);
        
        ListaNormal listaNormal = new ListaNormal(altura, y, identificacao, tela);
        listaNormal.setValor(valor);
        
        tela.pn_contatos.add(listaNormal);
        tela.pn_contatos.setPreferredSize(dimension);
        tela.pn_contatos.revalidate();
        
        y += altura;
     }
     
     public void exibirClientesConectados(String valor, TelaClientesConectados tela, Integer identificacao) {
        Dimension dimension = tela.pn_clientes.getPreferredSize();
        Integer width = dimension.width;
        dimension.setSize(width, y+altura+completar);
        
        ListaNormal listaNormal = new ListaNormal(altura, y, identificacao, tela);
        listaNormal.setValor(valor);
        
        tela.pn_clientes.add(listaNormal);
        tela.pn_clientes.setPreferredSize(dimension);
        tela.pn_clientes.revalidate();
        
        y += altura;
     }

}
