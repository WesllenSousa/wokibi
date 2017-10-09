
package telas;

import controle.utilitarios.Instancias;
import recursos.iternacionalizacao.Textos;

/**
 *
 * @author Wesllen Sousa Lima
 */
public class TelaAjuda extends javax.swing.JDialog {

    public TelaAjuda(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setUndecorated(true);
        setSize(405, 510);
        
        pn_txt_configuracoes.setText(Textos.ajuda_configuracoes());
        pn_txt_contatos.setText(Textos.ajuda_contatos());
        pn_txt_contatos_conectados.setText(Textos.ajuda_contatos_conectados());
        pn_txt_conversa.setText(Textos.ajuda_conversa());
        pn_txt_servidor.setText(Textos.ajuda_servidor());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        pn_txt_configuracoes = new javax.swing.JTextPane();
        lb_img_configuracoes = new javax.swing.JLabel();
        lb_img_servidor2 = new javax.swing.JLabel();
        pn_txt_servidor = new javax.swing.JTextPane();
        lb_img_conversa1 = new javax.swing.JLabel();
        pn_txt_conversa = new javax.swing.JTextPane();
        lb_img_contatos_conectados = new javax.swing.JLabel();
        pn_txt_contatos_conectados = new javax.swing.JTextPane();
        lb_img_contatos = new javax.swing.JLabel();
        pn_txt_contatos = new javax.swing.JTextPane();
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

        pn_txt_configuracoes.setEditable(false);
        pn_txt_configuracoes.setText("bdfbdfb sf sdf sd sdf sd fsdf sd bdfbdfb sf sdf sd sdf sd fsdf sd bdfbdfb sf sdf sd sdf sd ");
        pn_txt_configuracoes.setOpaque(false);
        pn_txt_configuracoes.setBounds(90, 410, 310, 70);
        jLayeredPane1.add(pn_txt_configuracoes, javax.swing.JLayeredPane.DEFAULT_LAYER);

        lb_img_configuracoes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_img_configuracoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagens/configuracao_72.png"))); // NOI18N
        lb_img_configuracoes.setBounds(10, 410, 70, 70);
        jLayeredPane1.add(lb_img_configuracoes, javax.swing.JLayeredPane.DEFAULT_LAYER);

        lb_img_servidor2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_img_servidor2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagens/servidor_ativo_72.png"))); // NOI18N
        lb_img_servidor2.setBounds(10, 60, 70, 70);
        jLayeredPane1.add(lb_img_servidor2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        pn_txt_servidor.setEditable(false);
        pn_txt_servidor.setText("bdfbdfb sf sdf sd sdf sd fsdf sd bdfbdfb sf sdf sd sdf sd fsdf sd bdfbdfb sf sdf sd sdf sd fsdf sd bdfbdfb sf sdf sd sdf sd fsdf sd bdfbdfb sf sdf sd sdf sd fsdf sd bdfbdfb sf sdf sd sdf sd fsdf sd bdfbdfb sf sdf sd sdf sd fsdf sd bdfbdfb sf sdf sd sdf sd fsdf sd bdfbdfb sf sdf sd sdf sd fsdf sd bdfbdfb sf sdf sd sdf sd fsdf sd ");
        pn_txt_servidor.setOpaque(false);
        pn_txt_servidor.setBounds(90, 50, 300, 80);
        jLayeredPane1.add(pn_txt_servidor, javax.swing.JLayeredPane.DEFAULT_LAYER);

        lb_img_conversa1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_img_conversa1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagens/conversa_cliente_72.png"))); // NOI18N
        lb_img_conversa1.setBounds(10, 150, 70, 70);
        jLayeredPane1.add(lb_img_conversa1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        pn_txt_conversa.setEditable(false);
        pn_txt_conversa.setText("bdfbdfb sf sdf sd sdf sd fsdf sd bdfbdfb sf sdf sd sdf sd fsdf sd bdfbdfb sf sdf sd sdf sd fsdf sd bdfbdfb sf sdf sd sdf sd fsdf sd bdfbdfb sf sdf sd sdf sd fsdf sd bdfbdfb sf sdf sd sdf sd fsdf sd bdfbdfb sf sdf sd sdf sd fsdf sd bdfbdfb sf sdf sd sdf sd fsdf sd bdfbdfb sf sdf sd sdf sd fsdf sd bdfbdfb sf sdf sd sdf sd fsdf sd ");
        pn_txt_conversa.setOpaque(false);
        pn_txt_conversa.setBounds(90, 140, 310, 80);
        jLayeredPane1.add(pn_txt_conversa, javax.swing.JLayeredPane.DEFAULT_LAYER);

        lb_img_contatos_conectados.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_img_contatos_conectados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagens/contatos_conectados_72.png"))); // NOI18N
        lb_img_contatos_conectados.setBounds(10, 240, 70, 70);
        jLayeredPane1.add(lb_img_contatos_conectados, javax.swing.JLayeredPane.DEFAULT_LAYER);

        pn_txt_contatos_conectados.setEditable(false);
        pn_txt_contatos_conectados.setText("bdfbdfb sf sdf sd sdf sd fsdf sd bdfbdfb sf sdf sd sdf sd fsdf sd bdfbdfb sf sdf sd sdf sd fsdf sd bdfbdfb sf sdf sd sdf sd fsdf sd bdfbdfb sf sdf sd sdf sd fsdf sd bdfbdfb sf sdf sd sdf sd fsdf sd bdfbdfb sf sdf sd sdf sd fsdf sd bdfbdfb sf sdf sd sdf sd fsdf sd bdfbdfb sf sdf sd sdf sd fsdf sd bdfbdfb sf sdf sd sdf sd fsdf sd ");
        pn_txt_contatos_conectados.setOpaque(false);
        pn_txt_contatos_conectados.setBounds(90, 230, 310, 80);
        jLayeredPane1.add(pn_txt_contatos_conectados, javax.swing.JLayeredPane.DEFAULT_LAYER);

        lb_img_contatos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_img_contatos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagens/contatos_72.png"))); // NOI18N
        lb_img_contatos.setBounds(10, 330, 70, 70);
        jLayeredPane1.add(lb_img_contatos, javax.swing.JLayeredPane.DEFAULT_LAYER);

        pn_txt_contatos.setEditable(false);
        pn_txt_contatos.setText("bdfbdfb sf sdf sd sdf sd fsdf sd bdfbdfb sf sdf sd sdf sd fsdf sd bdfbdfb sf sdf sd sdf sd fsdf sd bdfbdfb sf sdf sd sdf sd fsdf sd bdfbdfb sf sdf sd sdf sd fsdf sd bdfbdfb sf sdf sd sdf sd fsdf sd bdfbdfb sf sdf sd sdf sd fsdf sd bdfbdfb sf sdf sd sdf sd fsdf sd bdfbdfb sf sdf sd sdf sd fsdf sd bdfbdfb sf sdf sd sdf sd fsdf sd ");
        pn_txt_contatos.setOpaque(false);
        pn_txt_contatos.setBounds(90, 320, 310, 80);
        jLayeredPane1.add(pn_txt_contatos, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagens/voltar_36.png"))); // NOI18N
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
    private javax.swing.JLabel lb_img_configuracoes;
    private javax.swing.JLabel lb_img_contatos;
    private javax.swing.JLabel lb_img_contatos_conectados;
    private javax.swing.JLabel lb_img_conversa1;
    private javax.swing.JLabel lb_img_servidor2;
    private javax.swing.JTextPane pn_txt_configuracoes;
    private javax.swing.JTextPane pn_txt_contatos;
    private javax.swing.JTextPane pn_txt_contatos_conectados;
    private javax.swing.JTextPane pn_txt_conversa;
    private javax.swing.JTextPane pn_txt_servidor;
    // End of variables declaration//GEN-END:variables
}
