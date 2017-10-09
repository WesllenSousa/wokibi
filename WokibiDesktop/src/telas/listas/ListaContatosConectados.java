
package telas.listas;

import controle.utilitarios.ConstantesDiversas;
import controle.utilitarios.Instancias;
import java.awt.Color;
import java.awt.event.MouseEvent;
import recursos.Cores;
import telas.dependencias.DialogGeral;

/**
 *
 * @author Wesllen Sousa Lima
 */
public class ListaContatosConectados extends javax.swing.JPanel {
    
    private Integer identificacao;
    private Integer clienteOuServidor; //0: cliente, 1: servidor

    public ListaContatosConectados() {
        initComponents();
        setSize(400, 42); 
    }
    
    public ListaContatosConectados(Integer altura, Integer y) {
        initComponents();
        setSize(390, altura); 
        setLocation(0, y);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb_usuario = new javax.swing.JLabel();
        lb_ip = new javax.swing.JLabel();

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

        lb_ip.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lb_ip.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_ip.setText("127.0.0.1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(lb_usuario, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb_ip, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lb_usuario, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                .addComponent(lb_ip, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            if(clienteOuServidor == 0) {
                DialogGeral dialog = new DialogGeral(null, true);
                dialog.abrirTela(DialogGeral.TELA_LISTA, ConstantesDiversas.TL_LISTA_CONTATOS_CONECTADOS);
                dialog.setVisible(true);
            } else if(clienteOuServidor == 1) {
                Instancias.getTelaContatosConectados().seguirParaTelaClientesConectados();
            }
        } 
    }//GEN-LAST:event_formMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lb_ip;
    private javax.swing.JLabel lb_usuario;
    // End of variables declaration//GEN-END:variables

    public void setUsuario(String valor) {
        lb_usuario.setText(valor);
    }
    
    public void setIp(String valor) {
        lb_ip.setText(valor);
    }

    public void setClienteOuServidor(Integer clienteOuServidor) {
        this.clienteOuServidor = clienteOuServidor;
    }

    public void setIdentificacao(Integer identificacao) {
        this.identificacao = identificacao;
    }
    
}
