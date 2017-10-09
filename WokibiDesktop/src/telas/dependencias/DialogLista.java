
package telas.dependencias;

import telas.listas.ExibirListaNormal;
import controle.utilitarios.ConstantesDiversas;
import recursos.iternacionalizacao.Textos;

/**
 *
 * @author Wesllen Sousa Lima
 */
public class DialogLista extends javax.swing.JDialog {
    
    private DialogGeral escolha;
    private Integer tipo;

    public DialogLista(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setTitle(Textos.dialog_escolha());
        setSize(200, 210);  
    }
    
    public DialogLista(java.awt.Frame parent, boolean modal, DialogGeral escolha, Integer largura, Integer altura) {
        super(parent, modal);
        initComponents();
        setTitle(Textos.dialog_escolha());
        setSize(largura, altura);  
        
        this.escolha = escolha;
    }
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pn_geral = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Escolha");
        setResizable(false);
        setType(java.awt.Window.Type.POPUP);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        pn_geral.setBackground(new java.awt.Color(255, 255, 255));
        pn_geral.setOpaque(false);

        javax.swing.GroupLayout pn_geralLayout = new javax.swing.GroupLayout(pn_geral);
        pn_geral.setLayout(pn_geralLayout);
        pn_geralLayout.setHorizontalGroup(
            pn_geralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        pn_geralLayout.setVerticalGroup(
            pn_geralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 220, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_geral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_geral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-216)/2, (screenSize.height-258)/2, 216, 258);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        fechar();
    }//GEN-LAST:event_formWindowClosing
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JPanel pn_geral;
    // End of variables declaration//GEN-END:variables
 
    public void lista(Integer tela) {
        ExibirListaNormal lista = new ExibirListaNormal();
        switch (tela) { 
            case ConstantesDiversas.TL_LISTA_CONVERSA_ATIVA:    
                lista.exibirDialogLista(Textos.dialog_mensagem(), this, 1);
                lista.exibirDialogLista(Textos.dialog_desconectar(), this, 2);
                lista.exibirDialogLista(Textos.dialog_excluir(), this, 3);
                break;
            case ConstantesDiversas.TL_LISTA_CONVERSA_INATIVA:    
                lista.exibirDialogLista(Textos.dialog_mensagem(), this, 1);
                lista.exibirDialogLista(Textos.dialog_conectar(), this, 2);
                lista.exibirDialogLista(Textos.dialog_excluir(), this, 3);
                break;
            case ConstantesDiversas.TL_LISTA_CONTATOS:
                lista.exibirDialogLista(Textos.dialog_editar(), this, 1);
                lista.exibirDialogLista(Textos.dialog_excluir(), this, 2);
                break;
            case ConstantesDiversas.TL_LISTA_MENSAGENS:
                lista.exibirDialogLista(Textos.dialog_ocultar(), this, 1);
                lista.exibirDialogLista(Textos.dialog_excluir(), this, 2);
                break;
            case ConstantesDiversas.TL_LISTA_CONTATOS_CONECTADOS:
                lista.exibirDialogLista(Textos.dialog_associar(), this, 1);
                lista.exibirDialogLista(Textos.dialog_desconectar(), this, 2);
                break;
            case ConstantesDiversas.TL_LISTA_CLIENTES_CONECTADOS:
                lista.exibirDialogLista(Textos.dialog_iniciarConversa(), this, 1);
                lista.exibirDialogLista(Textos.dialog_associar(), this, 2);
                break;
        }
        tipo = tela;
    }

    public Integer getTipo() {
        return tipo;
    }
    
    public void fechar() {
        escolha.fechar(false);
        dispose();
    }
    
}
