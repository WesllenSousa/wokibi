
package telas.dependencias;

import controle.clienteServidor.Servidor;
import controle.utilitarios.ConstantesTelas;
import controle.utilitarios.Instancias;
import controle.utilitarios.Results;
import recursos.iternacionalizacao.Textos;
import telas.TelaNovaConversaCliente;
import telas.TelaNovaConversaServidor;
import util.RedeUtil;

/**
 *
 * @author Wesllen Sousa Lima
 */
public class DialogCriarEntrar extends javax.swing.JDialog {
    
    private DialogGeral escolha;

    public DialogCriarEntrar(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setTitle(Textos.dialog_escolha());
    }
    
    public DialogCriarEntrar(java.awt.Frame parent, boolean modal, DialogGeral escolha) {
        super(parent, modal);
        initComponents();
        setTitle(Textos.dialog_escolha());
        
        this.escolha = escolha;
        lb_mensagem.setText(Textos.dialog_tipoConversa());
        bt_entrar.setText(Textos.dialog_cliente());
        bt_criar.setText(Textos.dialog_servidor());
    }
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pn_geral = new javax.swing.JPanel();
        bt_entrar = new javax.swing.JButton();
        bt_criar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        lb_mensagem = new javax.swing.JTextPane();

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

        bt_entrar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bt_entrar.setText("Entrar");
        bt_entrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_entrarActionPerformed(evt);
            }
        });

        bt_criar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bt_criar.setText("Criar");
        bt_criar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_criarActionPerformed(evt);
            }
        });

        jScrollPane1.setBackground(new java.awt.Color(204, 204, 204));

        lb_mensagem.setEditable(false);
        lb_mensagem.setBackground(new java.awt.Color(234, 234, 234));
        lb_mensagem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jScrollPane1.setViewportView(lb_mensagem);

        javax.swing.GroupLayout pn_geralLayout = new javax.swing.GroupLayout(pn_geral);
        pn_geral.setLayout(pn_geralLayout);
        pn_geralLayout.setHorizontalGroup(
            pn_geralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_geralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pn_geralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(pn_geralLayout.createSequentialGroup()
                        .addComponent(bt_entrar, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_criar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pn_geralLayout.setVerticalGroup(
            pn_geralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_geralLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn_geralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bt_entrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_criar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        setBounds((screenSize.width-216)/2, (screenSize.height-156)/2, 216, 156);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        fechar();
    }//GEN-LAST:event_formWindowClosing

    private void bt_criarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_criarActionPerformed
        if(RedeUtil.obtemIpLocal() != null) {
            if (RedeUtil.verificarEnderecoPrivado(RedeUtil.obtemIpLocal())) {
                Instancias.getMensagens().aviso(Textos.dialog_infoIp());
            } 
            if(Instancias.getServidor() == null) {
                Servidor servidor = new Servidor();
                Thread thread = new Thread(servidor);
                thread.start();
                Results.getTelaPrincipal().onResult(ConstantesTelas.CRIAR_SERVIDOR);
            }
            TelaNovaConversaServidor dialog = new TelaNovaConversaServidor(null, true);
            dialog.setVisible(true);
            fechar();
        } else {
            Instancias.getMensagens().bug(Textos.novaConversaServidor_semRede());
        }
    }//GEN-LAST:event_bt_criarActionPerformed

    private void bt_entrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_entrarActionPerformed
        TelaNovaConversaCliente dialog = new TelaNovaConversaCliente(null, true);
        dialog.setVisible(true);
        fechar();
    }//GEN-LAST:event_bt_entrarActionPerformed
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_criar;
    private javax.swing.JButton bt_entrar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane lb_mensagem;
    public javax.swing.JPanel pn_geral;
    // End of variables declaration//GEN-END:variables
 
    public void fechar() {
        escolha.fechar(false);
        dispose();
    }
    
}
