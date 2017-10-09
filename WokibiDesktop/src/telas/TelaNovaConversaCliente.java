package telas;

import controle.clienteServidor.Cliente;
import controle.utilitarios.ConstantesDiversas;
import controle.utilitarios.ConstantesTelas;
import controle.utilitarios.Instancias;
import controle.utilitarios.Results;
import entidades.mensagem.bean.MensagemBean;
import entidades.mensagem.facade.MensagemFacade;
import entidades.mensagem.facade.MensagemFacadeImpl;
import java.util.ArrayList;
import java.util.HashMap;
import recursos.iternacionalizacao.Textos;
import telas.dependencias.DialogGeral;
import util.FacadeException;

/**
 *
 * @author Wesllen Sousa Lima
 */
public class TelaNovaConversaCliente extends javax.swing.JDialog {

    private MensagemFacade mensagemFacade;
    private DialogGeral dialog;
    private Cliente cliente;

    public TelaNovaConversaCliente(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(405, 505);
        Results.setTelaNovaConversaCliente(TelaNovaConversaCliente.this);

        mensagemFacade = new MensagemFacadeImpl();
    }

    public TelaNovaConversaCliente(java.awt.Frame parent, boolean modal, Integer codigo) {
        super(parent, modal);
        initComponents();
        setSize(405, 505);
        Results.setTelaNovaConversaCliente(TelaNovaConversaCliente.this);

        mensagemFacade = new MensagemFacadeImpl();
        verificarIpConversa(codigo);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabel5 = new javax.swing.JLabel();
        et_ip = new javax.swing.JTextField();
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

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Endereço do celular (Ip):");
        jLabel5.setBounds(20, 60, 250, 17);
        jLayeredPane1.add(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);

        et_ip.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        et_ip.setBounds(20, 80, 360, 30);
        jLayeredPane1.add(et_ip, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagens/voltar_36.png"))); // NOI18N
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        jLabel4.setBounds(0, 0, 36, 36);
        jLayeredPane1.add(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagens/sucesso_36.png"))); // NOI18N
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
        if(cliente != null) {
            cliente.getTratarMensagem().setHandler(null);
        }
        dispose();
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        conectar(et_ip.getText());
    }//GEN-LAST:event_jLabel3MouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (Instancias.getMensagens().confirmacao(Textos.mensagem_confirmarSaida())) {
            Instancias.getTelaPrincipal().fechar();
        }
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField et_ip;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    /*
     * AÇOES ESPECIFICAS
     */
    private void verificarIpConversa(Integer codigoConversa) {
        for (Object obj : listarMensagensPorConversa(codigoConversa)) {
            MensagemBean mensagem = (MensagemBean) obj;
            if (mensagem.getContato().getAutor() == 1) {
                String ip = mensagem.getContato().getIp();
                System.out.println(ip);
                if (ip != null) {
                    et_ip.setText(ip);
                    conectar(ip);
                }
                break;
            }
        }
    }

    private void conectar(String ip) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                dialog = new DialogGeral(null, true);
                dialog.abrirTela(DialogGeral.TELA_ESPERAR, null);
                dialog.setVisible(true);
            }
        }).start();
        cliente = new Cliente(ip, 0, Instancias.getDono().getNome());
        cliente.getTratarMensagem().setHandler(this); 
        Thread thread = new Thread(cliente);
        thread.start();
    }

    private void confirmar(Integer codigoConversa) {
        Instancias.getMensagens().sucesso(Textos.novaConversaCliente_sucesso());
        HashMap<String, String> mapa = new HashMap<>();
        mapa.put(ConstantesDiversas.BD_CODIGO_CONVERSA, codigoConversa + "");
        Results.getTelaConversa().onResult(ConstantesTelas.NOVA_CONVERSA, mapa);
        dispose();
    }

    /*
     * BANCO DE DADOS
     */
    private ArrayList<Object> listarMensagensPorConversa(Integer codigo) {
        try {
            return mensagemFacade.listarMensagensPorConversa(codigo);
        } catch (FacadeException e) {
        }
        return null;
    }
    
    public void onResult(final int resultado, final HashMap<String, String> mapa) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                switch (resultado) {
                    case ConstantesDiversas.HD_NOVA_CONVERSA:
                        dialog.fechar(true);
                        String codigoConversa = mapa.get(ConstantesDiversas.BD_CODIGO_CONVERSA);
                        if(Integer.parseInt(codigoConversa) != 0) {
                            confirmar(Integer.parseInt(codigoConversa));
                        } else {
                            Instancias.getMensagens().bug(Textos.dialog_servidorNaoEncontrado());
                        }
                        break;
                }
            }
        }).start();
    }
    
}
