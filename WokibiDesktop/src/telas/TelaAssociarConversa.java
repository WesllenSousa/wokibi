package telas;

import controle.clienteServidor.Cliente;
import controle.utilitarios.ConstantesDiversas;
import controle.utilitarios.Instancias;
import entidades.contato.bean.ContatoBean;
import entidades.contato.facade.ContatoFacade;
import entidades.contato.facade.ContatoFacadeImpl;
import entidades.conversa.bean.ConversaBean;
import entidades.conversa.facade.ConversaFacade;
import entidades.conversa.facade.ConversaFacadeImpl;
import java.awt.Component;
import java.util.ArrayList;
import recursos.iternacionalizacao.Textos;
import telas.listas.ExibirListaRadioButton;
import telas.listas.ListaRadioButton;
import util.FacadeException;

/**
 *
 * @author Wesllen Sousa Lima
 */
public class TelaAssociarConversa extends javax.swing.JDialog {
    
    private ContatoFacade contatoFacade;
    private ConversaFacade conversaFacade;
    
    private String nomeContato;

    public TelaAssociarConversa(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(405, 505);
        scroll_conversas.getViewport().setOpaque(false);
        scroll_conversas.getViewport().setOpaque(false);
        
        contatoFacade = new ContatoFacadeImpl();
        conversaFacade = new ConversaFacadeImpl();
        
        preencherListas();
    }

    public TelaAssociarConversa(java.awt.Frame parent, boolean modal, Integer codigo) {
        super(parent, modal);
        initComponents();
        setSize(405, 505);
        scroll_conversas.getViewport().setOpaque(false);
        scroll_conversas.getViewport().setOpaque(false);
        
        contatoFacade = new ContatoFacadeImpl();
        conversaFacade = new ConversaFacadeImpl();
        ContatoBean c = new ContatoBean();
        c.setCodigo(codigo);
        ContatoBean contato = selecionar(c);
        nomeContato = contato.getNome();
        
        preencherListas();
    }

    public TelaAssociarConversa(java.awt.Frame parent, boolean modal, String nomeContato) {
        super(parent, modal);
        initComponents();
        setSize(405, 505);
        scroll_conversas.getViewport().setOpaque(false);
        scroll_conversas.getViewport().setOpaque(false);
        
        contatoFacade = new ContatoFacadeImpl();
        conversaFacade = new ConversaFacadeImpl();
        this.nomeContato = nomeContato;
        
        preencherListas();
    }
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        scroll_conversas = new javax.swing.JScrollPane();
        pn_conversas = new javax.swing.JPanel();
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

        scroll_conversas.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll_conversas.setOpaque(false);

        pn_conversas.setOpaque(false);

        javax.swing.GroupLayout pn_conversasLayout = new javax.swing.GroupLayout(pn_conversas);
        pn_conversas.setLayout(pn_conversasLayout);
        pn_conversasLayout.setHorizontalGroup(
            pn_conversasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 398, Short.MAX_VALUE)
        );
        pn_conversasLayout.setVerticalGroup(
            pn_conversasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 438, Short.MAX_VALUE)
        );

        scroll_conversas.setViewportView(pn_conversas);

        scroll_conversas.setBounds(0, 40, 400, 440);
        jLayeredPane1.add(scroll_conversas, javax.swing.JLayeredPane.DEFAULT_LAYER);

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
        dispose();
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        associar();
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
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JPanel pn_conversas;
    private javax.swing.JScrollPane scroll_conversas;
    // End of variables declaration//GEN-END:variables

    private void preencherListas() {
        ExibirListaRadioButton lista = new ExibirListaRadioButton();
        for (Object conversa : listarTodasConversasAtivas()) {
            ConversaBean c = (ConversaBean) conversa;
            lista.exibir(this, c.getDescricao(), c.getCodigo());
        }
    }

    private void associar() {
        ConversaBean c = new ConversaBean();
        c.setCodigo(Instancias.getEntidadeSelecionada());
        ConversaBean conversa = selecionar(c);
        String ipServidor;
        Cliente cliente = Instancias.getConversasCliente().get(conversa.getCodigo());
        if (cliente == null) {
            cliente = Instancias.getConversasServidor().get(conversa.getCodigo());
        }
        if (cliente != null) {
            ipServidor = cliente.getSocket().getInetAddress().getHostAddress();
            enviarLocalizacaoServidor(ipServidor, cliente);
        } 
        dispose();
    }

    private void enviarLocalizacaoServidor(String ip, Cliente cliente) {
        cliente.enviarDados(ConstantesDiversas.CS_ENCAMINHAR_ASSOCIAR_CONVERSA);
        cliente.enviarDados(ip);
        cliente.enviarDados(cliente.getIdentificacaoConversa());
        cliente.enviarDados(cliente.getConversa().getDescricao() + " / " + Instancias.getDono().getNome());
        cliente.enviarDados(nomeContato);
        Instancias.getMensagens().sucesso(Textos.associarConversa_localizacao());
    }

    /*
     * BANCO DE DADOS
     */
    private ArrayList<Object> listarTodasConversasAtivas() {
        try {
            return conversaFacade.listarTodasConversasAtivas();
        } catch (FacadeException e) {
        }
        return null;
    }

    private ContatoBean selecionar(ContatoBean contato) {
        try {
            return (ContatoBean) contatoFacade.selecionar(contato);
        } catch (FacadeException e) {
        }
        return null;
    }
    
    private ConversaBean selecionar(ConversaBean conversa) {
        try {
            return (ConversaBean) conversaFacade.selecionar(conversa);
        } catch (FacadeException e) {
        }
        return null;
    }
    
    /*
     * TELAS
     */
    
    public void verificarSelecao() {
        Component[] components = pn_conversas.getComponents();
        for (int i = 0; i < components.length; i++) {
            ListaRadioButton lista = (ListaRadioButton) components[i];
            if(lista.rd_radio.isSelected()) {
                if(lista.getIdentificacao() != Instancias.getEntidadeSelecionada()) {
                    lista.rd_radio.setSelected(false);
                }
            }
        }
    }
    
}
