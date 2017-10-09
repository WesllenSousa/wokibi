package telas;

import controle.arquivos.Caminhos;
import controle.clienteServidor.Cliente;
import controle.utilitarios.ConstantesTelas;
import controle.utilitarios.Instancias;
import controle.utilitarios.Results;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import javax.swing.ImageIcon;
import recursos.iternacionalizacao.Textos;
import telas.dependencias.DialogNotificacao;
import util.RedeUtil;

/**
 *
 * @author Wesllen Sousa Lima
 */
public class TelaPrincipal extends javax.swing.JFrame {

    public TelaPrincipal() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource(Caminhos.logo_100())).getImage());
        setSize(405, 505);
        Instancias.setTelaPrincipal(TelaPrincipal.this);
        Results.setTelaPrincipal(TelaPrincipal.this);
        lb_notificacao.setVisible(false);

        verificarStatusServidor();
        verificarStatusWifi();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButton1 = new javax.swing.JRadioButton();
        jPanel1 = new javax.swing.JPanel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        lb_notificacao = new javax.swing.JLabel();
        bt_nome_logo = new javax.swing.JLabel();
        bt_servidor = new javax.swing.JLabel();
        bt_configuracoes = new javax.swing.JLabel();
        bt_contatos_conectados = new javax.swing.JLabel();
        bt_conversa = new javax.swing.JLabel();
        bt_contatos = new javax.swing.JLabel();
        bt_sair = new javax.swing.JLabel();
        bt_wifi = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        jRadioButton1.setText("jRadioButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Wokibi");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        lb_notificacao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_notificacao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagens/notificacao.png"))); // NOI18N
        lb_notificacao.setText("Você tem mensagens");
        lb_notificacao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lb_notificacaoMouseClicked(evt);
            }
        });
        lb_notificacao.setBounds(0, 40, 400, 30);
        jLayeredPane1.add(lb_notificacao, javax.swing.JLayeredPane.DEFAULT_LAYER);

        bt_nome_logo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bt_nome_logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagens/nome_logo_100.png"))); // NOI18N
        bt_nome_logo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_nome_logoMouseClicked(evt);
            }
        });
        bt_nome_logo.setBounds(0, 250, 400, 30);
        jLayeredPane1.add(bt_nome_logo, javax.swing.JLayeredPane.DEFAULT_LAYER);

        bt_servidor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagens/servidor_36.png"))); // NOI18N
        bt_servidor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_servidorMouseClicked(evt);
            }
        });
        bt_servidor.setBounds(320, 0, 36, 36);
        jLayeredPane1.add(bt_servidor, javax.swing.JLayeredPane.DEFAULT_LAYER);

        bt_configuracoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagens/configuracao_72_1.png"))); // NOI18N
        bt_configuracoes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_configuracoesMouseClicked(evt);
            }
        });
        bt_configuracoes.setBounds(300, 380, 80, 70);
        jLayeredPane1.add(bt_configuracoes, javax.swing.JLayeredPane.DEFAULT_LAYER);

        bt_contatos_conectados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagens/contatos_conectados_72.png"))); // NOI18N
        bt_contatos_conectados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_contatos_conectadosMouseClicked(evt);
            }
        });
        bt_contatos_conectados.setBounds(300, 60, 80, 80);
        jLayeredPane1.add(bt_contatos_conectados, javax.swing.JLayeredPane.DEFAULT_LAYER);

        bt_conversa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagens/conversa_cliente_72.png"))); // NOI18N
        bt_conversa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_conversaMouseClicked(evt);
            }
        });
        bt_conversa.setBounds(30, 70, 80, 70);
        jLayeredPane1.add(bt_conversa, javax.swing.JLayeredPane.DEFAULT_LAYER);

        bt_contatos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagens/contatos_72.png"))); // NOI18N
        bt_contatos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_contatosMouseClicked(evt);
            }
        });
        bt_contatos.setBounds(30, 390, 80, 70);
        jLayeredPane1.add(bt_contatos, javax.swing.JLayeredPane.DEFAULT_LAYER);

        bt_sair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagens/sair_36.png"))); // NOI18N
        bt_sair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_sairMouseClicked(evt);
            }
        });
        bt_sair.setBounds(0, 0, 36, 36);
        jLayeredPane1.add(bt_sair, javax.swing.JLayeredPane.DEFAULT_LAYER);

        bt_wifi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagens/wifi_ativada_36.png"))); // NOI18N
        bt_wifi.setBounds(360, 0, 36, 36);
        jLayeredPane1.add(bt_wifi, javax.swing.JLayeredPane.DEFAULT_LAYER);

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

    private void bt_conversaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_conversaMouseClicked
        seguirParaTelaConversas();
    }//GEN-LAST:event_bt_conversaMouseClicked

    private void bt_contatos_conectadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_contatos_conectadosMouseClicked
        seguirParaTelaContatosConectados();
    }//GEN-LAST:event_bt_contatos_conectadosMouseClicked

    private void bt_contatosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_contatosMouseClicked
        seguirParaTelaListaContatos();
    }//GEN-LAST:event_bt_contatosMouseClicked

    private void bt_configuracoesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_configuracoesMouseClicked
        seguirParaTelaConfiguracoes();
    }//GEN-LAST:event_bt_configuracoesMouseClicked

    private void bt_servidorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_servidorMouseClicked
        seguirParaTelaCriarServidor();
    }//GEN-LAST:event_bt_servidorMouseClicked

    private void bt_nome_logoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_nome_logoMouseClicked
        seguirParaTelaSobre();
    }//GEN-LAST:event_bt_nome_logoMouseClicked

    private void bt_sairMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_sairMouseClicked
        if (Instancias.getMensagens().confirmacao(Textos.mensagem_confirmarSaida())) {
            fechar();
        }
    }//GEN-LAST:event_bt_sairMouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (Instancias.getMensagens().confirmacao(Textos.mensagem_confirmarSaida())) {
            fechar();
        }
    }//GEN-LAST:event_formWindowClosing

    private void lb_notificacaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_notificacaoMouseClicked
        DialogNotificacao dialog = new DialogNotificacao(this, true);
        dialog.setVisible(true);
    }//GEN-LAST:event_lb_notificacaoMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bt_configuracoes;
    private javax.swing.JLabel bt_contatos;
    private javax.swing.JLabel bt_contatos_conectados;
    private javax.swing.JLabel bt_conversa;
    private javax.swing.JLabel bt_nome_logo;
    private javax.swing.JLabel bt_sair;
    private javax.swing.JLabel bt_servidor;
    private javax.swing.JLabel bt_wifi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    public javax.swing.JLabel lb_notificacao;
    // End of variables declaration//GEN-END:variables

    /*
     * AÇOES ESPECIFICAS
     */
    /*
     * Status
     */
    private void verificarStatusServidor() {
        if (Instancias.getServidor() != null) {
            if (RedeUtil.obtemIpLocal() == null) {
                Instancias.getServidor().fechaConexao();
                Instancias.setServidor(null);
                bt_servidor.setIcon(new ImageIcon(getClass().getResource(Caminhos.geral_servidorInativo())));
            } else {
                bt_servidor.setIcon(new ImageIcon(getClass().getResource(Caminhos.geral_servidorAtivo())));
            }
        } else {
            bt_servidor.setIcon(new ImageIcon(getClass().getResource(Caminhos.geral_servidorInativo())));
        }
    }

    private void verificarStatusWifi() {
        if (RedeUtil.obtemIpLocal() != null) {
            bt_wifi.setIcon(new ImageIcon(getClass().getResource(Caminhos.geral_wifiAtiva())));
        } else {
            bt_wifi.setIcon(new ImageIcon(getClass().getResource(Caminhos.geral_wifiInativa())));
        }
    }

    /*
     * Telas
     */
    private void seguirParaTelaCriarServidor() {
        TelaCriarServidor dialog = new TelaCriarServidor(null, true);
        dialog.setVisible(true);
    }

    private void seguirParaTelaConversas() {
        TelaConversa dialog = new TelaConversa(null, true);
        dialog.setVisible(true);
    }

    private void seguirParaTelaContatosConectados() {
        TelaContatosConectados dialog = new TelaContatosConectados(null, true);
        dialog.setVisible(true);
    }

    private void seguirParaTelaListaContatos() {
        TelaListarContatos dialog = new TelaListarContatos(null, true);
        dialog.setVisible(true);
    }

    private void seguirParaTelaConfiguracoes() {
        TelaConfiguracoes dialog = new TelaConfiguracoes(null, true);
        dialog.setVisible(true);
    }

    private void seguirParaTelaSobre() {
        TelaSobre dialog = new TelaSobre(this, true);
        dialog.setVisible(true);
    }

    public void onResult(int resultado) {
        switch (resultado) {
            case ConstantesTelas.CRIAR_SERVIDOR:
                verificarStatusServidor();
                break;
        }
    }
    
    public void fechar() {
        if(Instancias.getServidor() != null) {
            Instancias.getServidor().fechaConexao();
        }
        try {
            if(Instancias.getConversasCliente() != null) {
                for (Iterator<Cliente> it = Instancias.getConversasCliente().values().iterator(); it.hasNext();) {
                    Cliente cliente = it.next();
                    cliente.fecharConexao();
                }
            }
            if(Instancias.getConversasServidor() != null) {
                for (Iterator<Cliente> it = Instancias.getConversasServidor().values().iterator(); it.hasNext();) {
                    Cliente cliente = it.next();
                    if(cliente.getConversa() != null) {
                        cliente.fecharConexao();
                    }
                }
            }
        } catch (ConcurrentModificationException ex) {
            System.out.println(ex);
        }
        System.exit(0);
    }
    
}
