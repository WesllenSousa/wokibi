
package telas;

import controle.utilitarios.Instancias;
import recursos.iternacionalizacao.Textos;
import telas.listas.ExibirListaNormal;

/**
 *
 * @author Wesllen Sousa Lima
 */
public class TelaConfiguracoes extends javax.swing.JDialog {

    public TelaConfiguracoes(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setUndecorated(true);
        setSize(405, 505);
        
        preencherLista();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        pn_configuracoes = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
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

        pn_configuracoes.setOpaque(false);

        javax.swing.GroupLayout pn_configuracoesLayout = new javax.swing.GroupLayout(pn_configuracoes);
        pn_configuracoes.setLayout(pn_configuracoesLayout);
        pn_configuracoesLayout.setHorizontalGroup(
            pn_configuracoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        pn_configuracoesLayout.setVerticalGroup(
            pn_configuracoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 450, Short.MAX_VALUE)
        );

        pn_configuracoes.setBounds(0, 40, 400, 450);
        jLayeredPane1.add(pn_configuracoes, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagens/inicio_36.png"))); // NOI18N
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        jLabel4.setBounds(0, 0, 36, 36);
        jLayeredPane1.add(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);

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

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (Instancias.getMensagens().confirmacao(Textos.mensagem_confirmarSaida())) {
            Instancias.getTelaPrincipal().fechar();
        }
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JPanel pn_configuracoes;
    // End of variables declaration//GEN-END:variables

    private void preencherLista() {
        ExibirListaNormal lista = new ExibirListaNormal();
        lista.exibirConfiguracoes(Textos.config_servidor(), this, 1);
        lista.exibirConfiguracoes(Textos.config_redes(), this, 2);
        lista.exibirConfiguracoes(Textos.config_senha(), this, 3);
        lista.exibirConfiguracoes(Textos.config_opcoes(), this, 4);
        lista.exibirConfiguracoes(Textos.config_ajuda(), this, 5);
        lista.exibirConfiguracoes(Textos.config_sobre(), this, 6);
    }
    
}
