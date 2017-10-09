package telas;

import controle.clienteServidor.Cliente;
import controle.clienteServidor.Servidor;
import controle.utilitarios.ConstantesTelas;
import controle.utilitarios.Instancias;
import controle.utilitarios.Preferencias;
import controle.utilitarios.Results;
import entidades.contato.bean.ContatoBean;
import entidades.contato.facade.ContatoFacade;
import entidades.contato.facade.ContatoFacadeImpl;
import java.util.ArrayList;
import java.util.Collection;
import recursos.iternacionalizacao.Textos;
import telas.listas.ExibirListaNormal;
import util.FacadeException;
import util.RedeUtil;

/**
 *
 * @author Wesllen Sousa Lima
 */
public class TelaCriarServidor extends javax.swing.JDialog {
    
    private ContatoFacade contatoFacade;

    public TelaCriarServidor(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(405, 505);
        scroll_contatos.getViewport().setOpaque(false);
        
        contatoFacade = new ContatoFacadeImpl();

        String ip = RedeUtil.obtemIpLocal();
        if (ip != null) {
            lb_ip.setText(ip);
        } else {
            bt_conectarDesconectar.setEnabled(false);
        }

        preencherLista();
        verificarStatus();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabel3 = new javax.swing.JLabel();
        lb_ip = new javax.swing.JLabel();
        scroll_contatos = new javax.swing.JScrollPane();
        pn_contatos = new javax.swing.JPanel();
        bt_conectarDesconectar = new javax.swing.JToggleButton();
        jLabel5 = new javax.swing.JLabel();
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

        jLabel3.setBackground(new java.awt.Color(51, 51, 51));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Contatos conectados");
        jLabel3.setOpaque(true);
        jLabel3.setBounds(0, 150, 400, 15);
        jLayeredPane1.add(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        lb_ip.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lb_ip.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_ip.setText("127.0.0.1");
        lb_ip.setBounds(0, 60, 400, 40);
        jLayeredPane1.add(lb_ip, javax.swing.JLayeredPane.DEFAULT_LAYER);

        scroll_contatos.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll_contatos.setOpaque(false);

        pn_contatos.setOpaque(false);

        javax.swing.GroupLayout pn_contatosLayout = new javax.swing.GroupLayout(pn_contatos);
        pn_contatos.setLayout(pn_contatosLayout);
        pn_contatosLayout.setHorizontalGroup(
            pn_contatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 398, Short.MAX_VALUE)
        );
        pn_contatosLayout.setVerticalGroup(
            pn_contatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 318, Short.MAX_VALUE)
        );

        scroll_contatos.setViewportView(pn_contatos);

        scroll_contatos.setBounds(0, 170, 400, 320);
        jLayeredPane1.add(scroll_contatos, javax.swing.JLayeredPane.DEFAULT_LAYER);

        bt_conectarDesconectar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        bt_conectarDesconectar.setText("Sem rede");
        bt_conectarDesconectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_conectarDesconectarActionPerformed(evt);
            }
        });
        bt_conectarDesconectar.setBounds(0, 100, 400, 50);
        jLayeredPane1.add(bt_conectarDesconectar, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel5.setBackground(new java.awt.Color(51, 51, 51));
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Endereço do celular");
        jLabel5.setOpaque(true);
        jLabel5.setBounds(0, 40, 400, 15);
        jLayeredPane1.add(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);

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

    private void bt_conectarDesconectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_conectarDesconectarActionPerformed
        conectarDesconectar();
    }//GEN-LAST:event_bt_conectarDesconectarActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (Instancias.getMensagens().confirmacao(Textos.mensagem_confirmarSaida())) {
            Instancias.getTelaPrincipal().fechar();
        }
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton bt_conectarDesconectar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lb_ip;
    public javax.swing.JPanel pn_contatos;
    private javax.swing.JScrollPane scroll_contatos;
    // End of variables declaration//GEN-END:variables

    private void preencherLista() {
        Collection<Object> contatos = listarContatosConectados();
        if (contatos != null && !contatos.isEmpty()) {
            ExibirListaNormal lista = new ExibirListaNormal();
            for(Object contato : contatos) { 
                ContatoBean c = (ContatoBean) contato;
                if(!verificaContatoServidor(c.getNome())) {
                    lista.exibirCriarServidor(c.getNome(), this, c.getCodigo());
                }
            }
        } else {
            pn_contatos.removeAll();
        }
    }

    private void verificarStatus() {
        if (Instancias.getServidor() != null) {
            bt_conectarDesconectar.setSelected(true);
            bt_conectarDesconectar.setText(Textos.criarServidor_desativar());
        } else {
            bt_conectarDesconectar.setSelected(false);
            bt_conectarDesconectar.setText(Textos.criarServidor_ativar());
        }
    }

    private void conectarDesconectar() {
        if (bt_conectarDesconectar.isSelected()) {
            Preferencias p = new Preferencias();
            if (p.getServicoServidor()) {
                conectar();
            } else {
                bt_conectarDesconectar.setSelected(false);
                Instancias.getMensagens().aviso(Textos.criarServidor_servicoDesabilido());
            }
        } else {
            if(Instancias.getMensagens().confirmacao(Textos.dialog_perguntarDesconectar())) {
                desconectar();
            } else {
                bt_conectarDesconectar.setSelected(true);
            }
        }
    }

    private void conectar() {
        if (RedeUtil.verificarEnderecoPrivado(RedeUtil.obtemIpLocal())) {
            Instancias.getMensagens().aviso(Textos.dialog_infoIp());
        } 
        Servidor servidor = new Servidor();
        Thread thread = new Thread(servidor);
        thread.start();
        Results.getTelaPrincipal().onResult(ConstantesTelas.CRIAR_SERVIDOR);
        bt_conectarDesconectar.setText(Textos.criarServidor_desativar());
        Instancias.getMensagens().sucesso(Textos.cs_servidorCriado());
    }

    private void desconectar() {
        if (Instancias.getServidor() != null) {
            bt_conectarDesconectar.setText(Textos.criarServidor_ativar());
            Instancias.getServidor().fechaConexao();
            Results.getTelaPrincipal().onResult(ConstantesTelas.CRIAR_SERVIDOR);
            preencherLista();
        }
    }
    
    private boolean verificaContatoServidor(String nome) {
        for (Cliente cliente : Instancias.getConversasServidor().values()) {
            if(cliente.getContato().getNome().equals(nome)) {
                return true;
            }
        }
        return false;
    }

    /*
     * BANCO DE DADOS
	 *
     */
    private ArrayList<Object> listarContatosConectados() {
        try {
            return contatoFacade.listarContatosConectados();
        } catch (FacadeException e) {
        }
        return null;
    }
    
}
