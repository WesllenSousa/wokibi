package telas;

import controle.arquivos.Caminhos;
import controle.arquivos.ManipularArquivoProperties;
import controle.utilitarios.ConstantesDiversas;
import controle.utilitarios.Instancias;
import controle.utilitarios.Preferencias;
import java.io.*;
import java.util.Properties;
import recursos.iternacionalizacao.Textos;

/**
 *
 * @author Wesllen Sousa Lima
 */
public class TelaOpcoes extends javax.swing.JDialog {

    public TelaOpcoes(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(405, 505);

        verificarPreferencias();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabel5 = new javax.swing.JLabel();
        ck_servicoMensagem = new javax.swing.JCheckBox();
        ck_servicoServidor = new javax.swing.JCheckBox();
        ck_executarAudio = new javax.swing.JCheckBox();
        cb_modem = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
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

        jLabel5.setBackground(new java.awt.Color(51, 51, 51));
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Serviços");
        jLabel5.setOpaque(true);
        jLabel5.setBounds(0, 40, 400, 15);
        jLayeredPane1.add(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);

        ck_servicoMensagem.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        ck_servicoMensagem.setText("Habilitar serviço de mensagem");
        ck_servicoMensagem.setOpaque(false);
        ck_servicoMensagem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ck_servicoMensagemActionPerformed(evt);
            }
        });
        ck_servicoMensagem.setBounds(10, 100, 270, 31);
        jLayeredPane1.add(ck_servicoMensagem, javax.swing.JLayeredPane.DEFAULT_LAYER);

        ck_servicoServidor.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        ck_servicoServidor.setText("Habilitar serviço do servidor");
        ck_servicoServidor.setOpaque(false);
        ck_servicoServidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ck_servicoServidorActionPerformed(evt);
            }
        });
        ck_servicoServidor.setBounds(10, 70, 270, 27);
        jLayeredPane1.add(ck_servicoServidor, javax.swing.JLayeredPane.DEFAULT_LAYER);

        ck_executarAudio.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        ck_executarAudio.setText("Executar áudio");
        ck_executarAudio.setOpaque(false);
        ck_executarAudio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ck_executarAudioActionPerformed(evt);
            }
        });
        ck_executarAudio.setBounds(10, 130, 270, 31);
        jLayeredPane1.add(ck_executarAudio, javax.swing.JLayeredPane.DEFAULT_LAYER);

        cb_modem.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        cb_modem.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "COM1", "COM2", "COM3", "COM4", "COM5", "COM6", "COM7", "COM8", "COM9", "COM10" }));
        cb_modem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_modemActionPerformed(evt);
            }
        });
        cb_modem.setBounds(130, 170, 150, 25);
        jLayeredPane1.add(cb_modem, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel3.setText("Porta do Modem:");
        jLabel3.setBounds(10, 170, 130, 19);
        jLayeredPane1.add(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);

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

    private void ck_servicoServidorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ck_servicoServidorActionPerformed
        if(ck_servicoServidor.isSelected()) {
            servidor(true);
        } else {
            servidor(false);
        }
    }//GEN-LAST:event_ck_servicoServidorActionPerformed

    private void ck_servicoMensagemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ck_servicoMensagemActionPerformed
        if(ck_servicoMensagem.isSelected()) {
            sms(true);
        } else {
            sms(false);
        }
    }//GEN-LAST:event_ck_servicoMensagemActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (Instancias.getMensagens().confirmacao(Textos.mensagem_confirmarSaida())) {
            Instancias.getTelaPrincipal().fechar();
        }
    }//GEN-LAST:event_formWindowClosing

    private void ck_executarAudioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ck_executarAudioActionPerformed
        if(ck_executarAudio.isSelected()) {
            audio(true);
        } else {
            audio(false);
        }
    }//GEN-LAST:event_ck_executarAudioActionPerformed

    private void cb_modemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_modemActionPerformed
        modem(cb_modem.getSelectedItem().toString()); 
    }//GEN-LAST:event_cb_modemActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cb_modem;
    private javax.swing.JCheckBox ck_executarAudio;
    private javax.swing.JCheckBox ck_servicoMensagem;
    private javax.swing.JCheckBox ck_servicoServidor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    private void verificarPreferencias() {
        Preferencias p = new Preferencias();
        if (p.getServicoServidor()) {
            ck_servicoServidor.setSelected(true);
        } else {
            ck_servicoServidor.setSelected(false);
        }
        if (p.getServicoSms()) {
            ck_servicoMensagem.setSelected(true);
        } else {
            ck_servicoMensagem.setSelected(false);
        }
        if (p.getAudio()) {
            ck_executarAudio.setSelected(true);
        } else {
            ck_executarAudio.setSelected(false);
        }
        String porta = p.getModem();
        if(porta != null) {
            cb_modem.setSelectedItem(porta);
        } else {
            cb_modem.setSelectedItem(ConstantesDiversas.PORTA_MODEM); 
        }
    }

    private void servidor(Boolean valor) {
        Properties props = new Properties();
        props.setProperty("ativo", valor+"");
        ManipularArquivoProperties.salvarConfiguracao(props, new File(Caminhos.config_servicoServidor()),
                "configuração do servidor!");
    }

    private void sms(Boolean valor) {
        Properties props = new Properties();
        props.setProperty("ativo", valor+"");
        ManipularArquivoProperties.salvarConfiguracao(props, new File(Caminhos.config_servicoMensagem()),
                "configuração de mensagens!");
    }
    
    private void audio(Boolean valor) {
        Properties props = new Properties();
        props.setProperty("ativo", valor+"");
        ManipularArquivoProperties.salvarConfiguracao(props, new File(Caminhos.config_audio()),
                "configuração de audio!");
    }
    
    private void modem(String valor) {
        Properties props = new Properties();
        props.setProperty("porta", valor);
        ManipularArquivoProperties.salvarConfiguracao(props, new File(Caminhos.config_modem()),
                "configuração da porta do modem!");
    }

}
