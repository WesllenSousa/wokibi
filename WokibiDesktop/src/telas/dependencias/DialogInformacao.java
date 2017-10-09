
package telas.dependencias;

import recursos.iternacionalizacao.Textos;

/**
 *
 * @author Wesllen Sousa Lima
 */
public class DialogInformacao extends javax.swing.JDialog {
    
    private DialogGeral escolha;

    public DialogInformacao(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setTitle(Textos.dialog_escolha());
    }
    
    public DialogInformacao(java.awt.Frame parent, boolean modal, DialogGeral escolha) {
        super(parent, modal);
        initComponents();
        setTitle(Textos.dialog_escolha());
        
        this.escolha = escolha;
        pn_mensagem.setText(Textos.dialog_infoIp());
        bt_ok.setText(Textos.dialog_ok());
    }
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pn_geral = new javax.swing.JPanel();
        bt_ok = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        pn_mensagem = new javax.swing.JTextPane();

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

        bt_ok.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bt_ok.setText("OK");
        bt_ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_okActionPerformed(evt);
            }
        });

        pn_mensagem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        pn_mensagem.setText("Você está em uma rede privada. Por esse motivo você não será visível na Internet!");
        jScrollPane1.setViewportView(pn_mensagem);

        javax.swing.GroupLayout pn_geralLayout = new javax.swing.GroupLayout(pn_geral);
        pn_geral.setLayout(pn_geralLayout);
        pn_geralLayout.setHorizontalGroup(
            pn_geralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_geralLayout.createSequentialGroup()
                .addGroup(pn_geralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_geralLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(bt_ok, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 36, Short.MAX_VALUE))
                    .addGroup(pn_geralLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pn_geralLayout.setVerticalGroup(
            pn_geralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_geralLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_ok, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
        setBounds((screenSize.width-216)/2, (screenSize.height-198)/2, 216, 198);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        fechar();
    }//GEN-LAST:event_formWindowClosing

    private void bt_okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_okActionPerformed
        fechar();
    }//GEN-LAST:event_bt_okActionPerformed
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_ok;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JPanel pn_geral;
    private javax.swing.JTextPane pn_mensagem;
    // End of variables declaration//GEN-END:variables
 
    public void fechar() {
        escolha.fechar(false);
        dispose();
    }
    
}
