
package telas.listas;

import controle.utilitarios.Instancias;
import java.awt.Color;
import java.awt.event.MouseEvent;
import recursos.Cores;
import telas.TelaAssociarConversa;

/**
 *
 * @author Wesllen Sousa Lima
 */
public class ListaRadioButton extends javax.swing.JPanel {
    
    private Integer identificacao;
    private TelaAssociarConversa telaAssociarConversa;

    public ListaRadioButton() {
        initComponents();
        setSize(400, 42); 
    }
    
    public ListaRadioButton(Integer altura, Integer y) {
        initComponents();
        setSize(390, altura); 
        setLocation(0, y);
    }
    
    public ListaRadioButton(Integer altura, Integer y, Object tela) {
        initComponents();
        setSize(390, altura); 
        setLocation(0, y);
        
        telaAssociarConversa = (TelaAssociarConversa) tela;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb_usuario = new javax.swing.JLabel();
        rd_radio = new javax.swing.JRadioButton();

        setOpaque(false);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                formMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });

        lb_usuario.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lb_usuario.setText("Wesllen");

        rd_radio.setOpaque(false);
        rd_radio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rd_radioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lb_usuario, javax.swing.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rd_radio)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb_usuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rd_radio)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
        setOpaque(true);
        setBackground(Cores.azulClaro());
    }//GEN-LAST:event_formMouseEntered

    private void formMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseExited
        setBackground(Cores.transparente());
        setOpaque(false);
    }//GEN-LAST:event_formMouseExited

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        setOpaque(true);
        setBackground(Cores.azul());
    }//GEN-LAST:event_formMousePressed

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        setBackground(Cores.transparente());
        setOpaque(false);
    }//GEN-LAST:event_formMouseReleased

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        int cont = evt.getButton();
        if(cont == MouseEvent.BUTTON1) { 
            Instancias.setEntidadeSelecionada(identificacao);
            telaAssociarConversa.verificarSelecao();
        }
    }//GEN-LAST:event_formMouseClicked

    private void rd_radioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rd_radioActionPerformed
        Instancias.setEntidadeSelecionada(identificacao);
        telaAssociarConversa.verificarSelecao();
    }//GEN-LAST:event_rd_radioActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lb_usuario;
    public javax.swing.JRadioButton rd_radio;
    // End of variables declaration//GEN-END:variables

    public void setValor(String valor) {
        lb_usuario.setText(valor);
    }

    public void setIdentificacao(Integer identificacao) {
        this.identificacao = identificacao;
    }

    public Integer getIdentificacao() {
        return identificacao;
    }
    
}
