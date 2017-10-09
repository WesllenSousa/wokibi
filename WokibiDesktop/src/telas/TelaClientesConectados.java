package telas;

import controle.clienteServidor.Cliente;
import controle.utilitarios.ConstantesDiversas;
import controle.utilitarios.Instancias;
import controle.utilitarios.Results;
import java.util.ArrayList;
import java.util.HashMap;
import recursos.iternacionalizacao.Textos;
import telas.dependencias.DialogGeral;
import telas.listas.ExibirListaNormal;

/**
 *
 * @author Wesllen Sousa Lima
 */
public class TelaClientesConectados extends javax.swing.JDialog {
  
    private DialogGeral dialog;
    private Cliente servidor;
    private String clienteSelecionado;

    public TelaClientesConectados(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(405, 505);
        scroll_clientes.getViewport().setOpaque(false);
        Results.setTelaClientesConectados(TelaClientesConectados.this);
    }

    public TelaClientesConectados(java.awt.Frame parent, boolean modal, Integer codigo) {
        super(parent, modal);
        initComponents();
        setSize(405, 505);
        scroll_clientes.getViewport().setOpaque(false);
        Results.setTelaClientesConectados(TelaClientesConectados.this);
        
        servidor = Instancias.getConversasServidor().get(codigo);
        buscarClientes();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        scroll_clientes = new javax.swing.JScrollPane();
        pn_clientes = new javax.swing.JPanel();
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

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagens/voltar_36.png"))); // NOI18N
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        jLabel4.setBounds(0, 0, 36, 36);
        jLayeredPane1.add(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel7.setBackground(new java.awt.Color(51, 51, 51));
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Contatos conectados ao celular do seu amigo");
        jLabel7.setOpaque(true);
        jLabel7.setBounds(0, 40, 400, 15);
        jLayeredPane1.add(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);

        scroll_clientes.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll_clientes.setOpaque(false);

        pn_clientes.setOpaque(false);

        javax.swing.GroupLayout pn_clientesLayout = new javax.swing.GroupLayout(pn_clientes);
        pn_clientes.setLayout(pn_clientesLayout);
        pn_clientesLayout.setHorizontalGroup(
            pn_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 398, Short.MAX_VALUE)
        );
        pn_clientesLayout.setVerticalGroup(
            pn_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 438, Short.MAX_VALUE)
        );

        scroll_clientes.setViewportView(pn_clientes);

        scroll_clientes.setBounds(0, 60, 400, 430);
        jLayeredPane1.add(scroll_clientes, javax.swing.JLayeredPane.DEFAULT_LAYER);

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
        if(servidor != null) {
            servidor.getTratarMensagem().setHandler(null);
        }
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
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JPanel pn_clientes;
    private javax.swing.JScrollPane scroll_clientes;
    // End of variables declaration//GEN-END:variables

    /*
     * AÇOES ESPECIFICAS
     */
    private void buscarClientes() {   
        new Thread(new Runnable() {
            @Override
            public void run() {
                servidor.getTratarMensagem().setHandler(this);
                servidor.enviarDados(ConstantesDiversas.CS_CLIENTE_CONECTADOS);
            }
        }).start();
        dialog = new DialogGeral(null, true);
        dialog.abrirTela(DialogGeral.TELA_ESPERAR, null); //buscando clientes
        dialog.setVisible(true);
    }

    private void preencherLista(ArrayList<String> clientes) {
        clientes.remove(Instancias.getDono().getNome());
        ExibirListaNormal lista = new ExibirListaNormal();
        for(String conversa : clientes) {
            lista.exibirClientesConectados(conversa, this, 0);
        }
    }

    public void iniciarConversa() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                servidor.getTratarMensagem().setHandler(this);
                servidor.enviarDados(ConstantesDiversas.CS_CONVERSA_PRIVADA);
                servidor.enviarDados(clienteSelecionado+"-"+Instancias.getDono().getNome());  //Descrição conversa
                servidor.enviarDados(clienteSelecionado);
            }
        }).start();
        dialog = new DialogGeral(null, true);
        dialog.abrirTela(DialogGeral.TELA_ESPERAR, null); //iniciando conversa privada
        dialog.setVisible(true);
    }

    public void seguirParaTelaAssociarConversa() {
        TelaAssociarConversa d = new TelaAssociarConversa(null, true, clienteSelecionado);
        d.setVisible(true);
    }

    private void confirmar(Integer codigoConversa) {
        TelaMensagens d = new TelaMensagens(null, true, codigoConversa);
        d.setVisible(true);
        dispose();
    }

    public void setClienteSelecionado(String clienteSelecionado) {
        this.clienteSelecionado = clienteSelecionado;
    }
    
    /*
     * ACTIVITY
     */
    public void onResult(Integer resultado, HashMap<String, Object> mapa) {
        switch (resultado) {
            case ConstantesDiversas.HD_RETORNA_CLIENTES:
                dialog.fechar(true);
                ArrayList<String> clientes = (ArrayList<String>) mapa.get(ConstantesDiversas.BD_CODIGO_CONVERSA);
                preencherLista(clientes);
                break;
            case ConstantesDiversas.HD_CONVERSA_PRIVADA:
                dialog.fechar(true);
                Integer codigoConversa = (Integer) mapa.get(ConstantesDiversas.BD_CODIGO_CONVERSA);
                if (codigoConversa != 0) {
                    confirmar(codigoConversa);
                } else {
                    Instancias.getMensagens().bug(Textos.dialog_servidorNaoEncontrado());
                }
                break;
            default:
                break;
        }
    }
    
}
