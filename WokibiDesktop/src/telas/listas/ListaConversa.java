
package telas.listas;

import controle.arquivos.Caminhos;
import controle.utilitarios.ConstantesDiversas;
import controle.utilitarios.Instancias;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import recursos.Cores;
import telas.TelaMensagens;
import telas.dependencias.DialogGeral;

/**
 *
 * @author Wesllen Sousa Lima
 */
public class ListaConversa extends javax.swing.JPanel {
    
    private Boolean conversaAtivaOuInativa;
    private Integer identificacao;

    public ListaConversa() {
        initComponents();
        setSize(400, 42); 
    }
    
    public ListaConversa(Integer altura, Integer y) {
        initComponents();
        
        setSize(390, altura); 
        setLocation(0, y);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb_usuario = new javax.swing.JLabel();
        lb_icone = new javax.swing.JLabel();

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

        lb_icone.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_icone.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagens/servidor_36.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lb_usuario, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb_icone, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb_usuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lb_icone, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
        setOpaque(true);
        setBackground(Cores.azulClaro());
    }//GEN-LAST:event_formMouseEntered

    private void formMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseExited
        setOpaque(false);
        setBackground(Cores.transparente());       
    }//GEN-LAST:event_formMouseExited

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        setOpaque(true);
        setBackground(Cores.azul());
    }//GEN-LAST:event_formMousePressed

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        setOpaque(false);
        setBackground(Cores.transparente());
    }//GEN-LAST:event_formMouseReleased

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        int cont = evt.getButton();
        if(cont == MouseEvent.BUTTON1) {
            TelaMensagens dialog1 = new TelaMensagens(null, true, identificacao);
            dialog1.setVisible(true);
        } else {
            Instancias.setEntidadeSelecionada(identificacao); 
            if(conversaAtivaOuInativa) {
                DialogGeral dialog = new DialogGeral(null, true);
                dialog.abrirTela(DialogGeral.TELA_LISTA, ConstantesDiversas.TL_LISTA_CONVERSA_ATIVA);
                dialog.setVisible(true);
            } else {
                DialogGeral dialog = new DialogGeral(null, true);
                dialog.abrirTela(DialogGeral.TELA_LISTA, ConstantesDiversas.TL_LISTA_CONVERSA_INATIVA);
                dialog.setVisible(true);
            }
        }
    }//GEN-LAST:event_formMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lb_icone;
    private javax.swing.JLabel lb_usuario;
    // End of variables declaration//GEN-END:variables

    public void setUsuario(String usuario) {
        lb_usuario.setText(usuario);
    }
    
    public void setIcone(Integer icone) {
        if(icone == 1) {
            lb_icone.setIcon(new ImageIcon(getClass().getResource(Caminhos.geral_conversaAtiva())));
        } else if(icone == 2) {
            lb_icone.setIcon(new ImageIcon(getClass().getResource(Caminhos.geral_conversaInativa())));
        }
    }

    public void setIdentificacao(Integer identificacao) {
        this.identificacao = identificacao;
    }

    public void setConversaAtivaOuInativa(Boolean conversaAtivaOuInativa) {
        this.conversaAtivaOuInativa = conversaAtivaOuInativa;
    }
    
}
