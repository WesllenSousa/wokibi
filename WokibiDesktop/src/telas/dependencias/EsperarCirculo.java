
package telas.dependencias;

import controle.utilitarios.Instancias;
import java.util.Calendar;
import util.DataUtil;

/**
 *
 * @author Wesllen Sousa Lima
 */
public class EsperarCirculo extends javax.swing.JDialog{
    
    private Integer tempoInicial, tempoFinal;
    private DialogGeral escolha;
    
    public EsperarCirculo(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        Instancias.setEsperarCirculo(EsperarCirculo.this);

        lbx_busy.setBusy(true);
    }
    
    public EsperarCirculo(java.awt.Frame parent, boolean modal, DialogGeral escolha, String nome) {
        super(parent, modal);
        initComponents();
        Instancias.setEsperarCirculo(EsperarCirculo.this);

        lbx_busy.setBusy(true);
        lbx_busy.setText(nome);
        this.escolha = escolha;
    }

    public EsperarCirculo(java.awt.Frame parent, boolean modal, Integer segundo, String nome) {
        super(parent, modal);
        initComponents();
        Instancias.setEsperarCirculo(EsperarCirculo.this);
       
        lbx_busy.setBusy(true);
        lbx_busy.setText(nome);
        tempoInicial = Integer.parseInt(DataUtil.formataSegundo(Calendar.getInstance()));
        tempoFinal = tempoInicial + segundo;
        
        timer1.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        timer1 = new org.netbeans.examples.lib.timerbean.Timer();
        lbx_busy = new org.jdesktop.swingx.JXBusyLabel();

        timer1.addTimerListener(new org.netbeans.examples.lib.timerbean.TimerListener() {
            public void onTime(java.awt.event.ActionEvent evt) {
                timer1OnTime(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        lbx_busy.setText("Carregando...");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbx_busy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbx_busy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-174)/2, (screenSize.height-86)/2, 174, 86);
    }// </editor-fold>//GEN-END:initComponents

private void timer1OnTime(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timer1OnTime
    tempoInicial = Integer.parseInt(DataUtil.formataSegundo(Calendar.getInstance()));
    if(tempoInicial >= tempoFinal){
        fechar();
    }
}//GEN-LAST:event_timer1OnTime

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        fechar();
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jdesktop.swingx.JXBusyLabel lbx_busy;
    private org.netbeans.examples.lib.timerbean.Timer timer1;
    // End of variables declaration//GEN-END:variables

    public void fechar() {
        escolha.fechar(false);
        dispose();
    }
    
}
