package telas;

import controle.arquivos.Caminhos;
import controle.arquivos.ManipularArquivoProperties;
import controle.utilitarios.Instancias;
import java.io.File;
import java.util.Properties;
import recursos.iternacionalizacao.Textos;
import util.Criptografia;

/**
 *
 * @author Wesllen Sousa Lima
 */
public class TelaCriarSenha extends javax.swing.JDialog {

    private String senhaAnterior;

    public TelaCriarSenha(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(405, 505);

        Properties props = ManipularArquivoProperties.lerConfiguracao(new File(Caminhos.config_senhaAnterior()));
        if (props != null) {
            senhaAnterior = props.getProperty("senhaAnterior");
        }

        if (senhaAnterior.equals("")) {
            tf_senhaAnterior.setVisible(false);
            lb_senhaAnterior.setVisible(false);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        lb_senhaAnterior = new javax.swing.JLabel();
        tf_senhaAnterior = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tf_novaSenha = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        tf_repetirSenha = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Wokibi");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        lb_senhaAnterior.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lb_senhaAnterior.setText("Senha Anterior:*");
        lb_senhaAnterior.setBounds(20, 60, 140, 17);
        jLayeredPane1.add(lb_senhaAnterior, javax.swing.JLayeredPane.DEFAULT_LAYER);

        tf_senhaAnterior.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tf_senhaAnterior.setBounds(20, 80, 360, 30);
        jLayeredPane1.add(tf_senhaAnterior, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Nova Senha:*");
        jLabel6.setBounds(20, 120, 110, 17);
        jLayeredPane1.add(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);

        tf_novaSenha.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tf_novaSenha.setBounds(20, 140, 360, 30);
        jLayeredPane1.add(tf_novaSenha, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Repetir Senha:*");
        jLabel7.setBounds(20, 180, 120, 17);
        jLayeredPane1.add(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);

        tf_repetirSenha.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tf_repetirSenha.setBounds(20, 200, 360, 30);
        jLayeredPane1.add(tf_repetirSenha, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagens/voltar_36.png"))); // NOI18N
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        jLabel4.setBounds(0, 0, 36, 36);
        jLayeredPane1.add(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagens/salvar_36.png"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jLabel3.setBounds(360, 0, 36, 36);
        jLayeredPane1.add(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagens/fundo_topo_desk.png"))); // NOI18N
        jLabel1.setBounds(0, 0, 400, 40);
        jLayeredPane1.add(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagens/fundo_desk.png"))); // NOI18N
        jLabel2.setBounds(0, 40, 400, 450);
        jLayeredPane1.add(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-416)/2, (screenSize.height-527)/2, 416, 527);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        dispose();
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        salvar();
    }//GEN-LAST:event_jLabel3MouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (Instancias.getMensagens().confirmacao(Textos.mensagem_confirmarSaida())) {
            Instancias.getTelaPrincipal().fechar();
        }
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lb_senhaAnterior;
    private javax.swing.JTextField tf_novaSenha;
    private javax.swing.JTextField tf_repetirSenha;
    private javax.swing.JTextField tf_senhaAnterior;
    // End of variables declaration//GEN-END:variables

    /*
     * AÇOES ESPECIFICAS
	 *
     */
    private Boolean validar() {
        String s;
        if (senhaAnterior.equals("")) {
            s = "";
        } else {
            s = Criptografia.MD5(tf_senhaAnterior.getText().toString());
        }
        if (s.equals(senhaAnterior)) {
            if (tf_novaSenha.getText().toString().equals(tf_repetirSenha.getText().toString())) {
                return true;
            } else {
                Instancias.getMensagens().bug(Textos.login_senhaNaoConrrepondem());
                return false;
            }
        } else {
            Instancias.getMensagens().bug(Textos.login_senhaInvalida());
            return false;
        }
    }

    private void salvar() {
        if (validar()) {
            Properties props = new Properties();
            if (!tf_novaSenha.getText().toString().equals("")) {
                props.setProperty("senhaAnterior", Criptografia.MD5(tf_novaSenha.getText().toString()));
            } else {
                props.setProperty("senhaAnterior", "");
            }
            ManipularArquivoProperties.salvarConfiguracao(props, new File(Caminhos.config_senhaAnterior()),
                "configuração de senha!");
            Instancias.getMensagens().sucesso(Textos.login_senhaAlterada());
            dispose();
        }
    }
    
}
