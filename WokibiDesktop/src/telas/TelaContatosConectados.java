package telas;

import controle.clienteServidor.Cliente;
import controle.clienteServidor.ClienteEntrada;
import controle.utilitarios.Instancias;
import entidades.contato.bean.ContatoBean;
import entidades.contato.facade.ContatoFacade;
import entidades.contato.facade.ContatoFacadeImpl;
import java.util.ArrayList;
import recursos.iternacionalizacao.Textos;
import telas.listas.ExibirListaContatosConectados;
import util.FacadeException;

/**
 *
 * @author Wesllen Sousa Lima
 */
public class TelaContatosConectados extends javax.swing.JDialog {
    
    private ContatoFacade contatoFacade;

    public TelaContatosConectados(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(405, 505);
        scroll_clientes.getViewport().setOpaque(false);
        scroll_servidores.getViewport().setOpaque(false);
        Instancias.setTelaSelecionada(TelaContatosConectados.this);
        Instancias.setTelaContatosConectados(TelaContatosConectados.this); 
        
        contatoFacade = new ContatoFacadeImpl();
        preencherListaServidores();
        preencherListaClientes();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        scroll_servidores = new javax.swing.JScrollPane();
        pn_servidores = new javax.swing.JPanel();
        scroll_clientes = new javax.swing.JScrollPane();
        pn_clientes = new javax.swing.JPanel();
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

        jLabel6.setBackground(new java.awt.Color(51, 51, 51));
        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Contatos em que você está conectado");
        jLabel6.setOpaque(true);
        jLabel6.setBounds(0, 260, 400, 15);
        jLayeredPane1.add(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel7.setBackground(new java.awt.Color(51, 51, 51));
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Contatos conectados ao seu celular");
        jLabel7.setOpaque(true);
        jLabel7.setBounds(0, 40, 400, 15);
        jLayeredPane1.add(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);

        scroll_servidores.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll_servidores.setOpaque(false);

        pn_servidores.setOpaque(false);

        javax.swing.GroupLayout pn_servidoresLayout = new javax.swing.GroupLayout(pn_servidores);
        pn_servidores.setLayout(pn_servidoresLayout);
        pn_servidoresLayout.setHorizontalGroup(
            pn_servidoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 398, Short.MAX_VALUE)
        );
        pn_servidoresLayout.setVerticalGroup(
            pn_servidoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 198, Short.MAX_VALUE)
        );

        scroll_servidores.setViewportView(pn_servidores);

        scroll_servidores.setBounds(0, 60, 400, 200);
        jLayeredPane1.add(scroll_servidores, javax.swing.JLayeredPane.DEFAULT_LAYER);

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
            .addGap(0, 198, Short.MAX_VALUE)
        );

        scroll_clientes.setViewportView(pn_clientes);

        scroll_clientes.setBounds(0, 280, 400, 200);
        jLayeredPane1.add(scroll_clientes, javax.swing.JLayeredPane.DEFAULT_LAYER);

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
        setBounds((screenSize.width-418)/2, (screenSize.height-527)/2, 418, 527);
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
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JPanel pn_clientes;
    public javax.swing.JPanel pn_servidores;
    private javax.swing.JScrollPane scroll_clientes;
    private javax.swing.JScrollPane scroll_servidores;
    // End of variables declaration//GEN-END:variables

    private void preencherListaServidores() {
        pn_servidores.removeAll();
        ExibirListaContatosConectados lista = new ExibirListaContatosConectados();
        ArrayList<String> nomes = new ArrayList<>();
        for (Cliente cliente : Instancias.getConversasServidor().values()) {
            if(!nomes.contains(cliente.getContato().getNome())) {
                lista.exibirContatosServidor(this, cliente.getContato().getNome(), cliente.getSocket().getInetAddress().getHostAddress(), 
                    cliente.getConversa().getCodigo());
                nomes.add(cliente.getContato().getNome()); 
            }
        }
    }

    private void preencherListaClientes() {
        pn_clientes.removeAll();
        ExibirListaContatosConectados lista = new ExibirListaContatosConectados();
        for (Object contato : listarClientesConectados()) {
            ContatoBean c = (ContatoBean) contato;
            if(!verificaContatoServidor(c.getNome())) {
                lista.exibirContatosCliente(this, c.getNome(), c.getIp(), c.getCodigo());
            }
        }
    }

    public void desconectar() {
        if(Instancias.getMensagens().confirmacao(Textos.contatosConectados_desconectar())) {
            Boolean verificar = false;
	    if(Instancias.getServidor() != null) {
                for (ArrayList<ClienteEntrada> clientes : Instancias.getServidor().getConversas().values()) {
                    for (ClienteEntrada cliente : clientes) {
                        Boolean status = false;
                        if (cliente.getContato().getCodigo() == Instancias.getEntidadeSelecionada()) {
                            cliente.fecharConexao();
                            preencherListaClientes();
                            Instancias.getConversasCliente().remove(cliente.getConversa().getCodigo());
                            Instancias.getMensagens().sucesso(Textos.contatosConectados_desconectado());
                            verificar = true;
                            status = true;
                            break;
                        }
                        if (status) {
                            break;
                        }
                    }
                }
            }
            if(!verificar) {
                Instancias.getMensagens().aviso(Textos.contatosConectados_verificar());
            }
        }
    }

    public void seguirParaTelaAssociarConversa() {
        TelaAssociarConversa dialog = new TelaAssociarConversa(null, true, Instancias.getEntidadeSelecionada());
        dialog.setVisible(true);
    }

    public void seguirParaTelaClientesConectados() {
        TelaClientesConectados dialog = new TelaClientesConectados(null, true, Instancias.getEntidadeSelecionada());
        dialog.setVisible(true);
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
    private ArrayList<Object> listarClientesConectados() {
        try {
            return contatoFacade.listarContatosConectados();
        } catch (FacadeException e) {
        }
        return null;
    }
    
}
