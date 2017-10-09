
package telas.listas;

import controle.utilitarios.ConstantesDiversas;
import controle.utilitarios.Instancias;
import entidades.mensagem.bean.MensagemBean;
import java.awt.event.MouseEvent;
import telas.dependencias.DialogGeral;

/**
 *
 * @author Wesllen Sousa Lima
 */
public class ListaMensagem extends javax.swing.JPanel {
    
    private MensagemBean mensagem;

    public ListaMensagem() {
        initComponents();
        setSize(399, 50); 
    }
    
    public ListaMensagem(Integer altura, Integer y, String data, MensagemBean mensagem) {
        initComponents();
        
        setSize(390, altura); 
        setLocation(0, y);
        verificaData(data);
        this.mensagem = mensagem;
        setNome();
        setMensagem();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb_nome = new javax.swing.JLabel();
        pn_mensagem = new javax.swing.JTextPane();
        lb_data = new javax.swing.JLabel();

        setOpaque(false);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        lb_nome.setBackground(new java.awt.Color(204, 204, 204));
        lb_nome.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lb_nome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_nome.setText("Nome");
        lb_nome.setOpaque(true);

        pn_mensagem.setEditable(false);
        pn_mensagem.setOpaque(false);

        lb_data.setBackground(new java.awt.Color(0, 0, 0));
        lb_data.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lb_data.setForeground(new java.awt.Color(255, 255, 255));
        lb_data.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_data.setText("data");
        lb_data.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb_nome, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
            .addComponent(pn_mensagem)
            .addComponent(lb_data, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lb_data)
                .addGap(1, 1, 1)
                .addComponent(lb_nome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pn_mensagem, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        int cont = evt.getButton();
        if(cont != MouseEvent.BUTTON1) {
            Instancias.setEntidadeSelecionada(mensagem.getCodigo()); 
            DialogGeral dialog = new DialogGeral(null, true);
            dialog.abrirTela(DialogGeral.TELA_LISTA, ConstantesDiversas.TL_LISTA_MENSAGENS);
            dialog.setVisible(true);
        } 
    }//GEN-LAST:event_formMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lb_data;
    private javax.swing.JLabel lb_nome;
    public javax.swing.JTextPane pn_mensagem;
    // End of variables declaration//GEN-END:variables

    private void setNome() {
        lb_nome.setText(mensagem.getContato().getNome());
    }
    
    private void setMensagem() {
        pn_mensagem.setText(mensagem.getTexto());
    }

    private void verificaData(String data) {
        if(!data.equals("")) {
            lb_data.setText(data);
        } else {
            lb_data.setVisible(false);
        }
    }

}
