package telas;

import controle.clienteServidor.Cliente;
import controle.mensagens.Celular;
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
import telas.listas.ExibirListaChekBox;
import telas.listas.ListaCheckBox;
import util.FacadeException;

/**
 *
 * @author Wesllen Sousa Lima
 */
public class TelaAssociarContato extends javax.swing.JDialog {
    
    private ContatoFacade contatoFacade;
    private ConversaFacade conversaFacade;
    
    private ConversaBean conversa;

    public TelaAssociarContato(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(405, 505);
        scroll_conectados.getViewport().setOpaque(false);
        scroll_desconectados.getViewport().setOpaque(false);

        contatoFacade = new ContatoFacadeImpl();
        conversaFacade = new ConversaFacadeImpl();
        preencherContatosConectados();
        preencherContatosDesconectados();
    }

    public TelaAssociarContato(java.awt.Frame parent, boolean modal, Integer codigo) {
        super(parent, modal);
        initComponents();
        setSize(405, 505);
        scroll_conectados.getViewport().setOpaque(false);
        scroll_desconectados.getViewport().setOpaque(false);

        contatoFacade = new ContatoFacadeImpl();
        conversaFacade = new ConversaFacadeImpl();
  
        ConversaBean c = new ConversaBean();
        c.setCodigo(codigo);
        conversa = (ConversaBean) selecionar(c);
        preencherContatosConectados();
        preencherContatosDesconectados();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        lb_conversasAtivas = new javax.swing.JLabel();
        lb_conversasAtivas1 = new javax.swing.JLabel();
        scroll_desconectados = new javax.swing.JScrollPane();
        pn_desconectados = new javax.swing.JPanel();
        scroll_conectados = new javax.swing.JScrollPane();
        pn_conectados = new javax.swing.JPanel();
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

        lb_conversasAtivas.setBackground(new java.awt.Color(51, 51, 51));
        lb_conversasAtivas.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lb_conversasAtivas.setForeground(new java.awt.Color(255, 255, 255));
        lb_conversasAtivas.setText("Contatos desconectados");
        lb_conversasAtivas.setOpaque(true);
        lb_conversasAtivas.setBounds(0, 260, 400, 15);
        jLayeredPane1.add(lb_conversasAtivas, javax.swing.JLayeredPane.DEFAULT_LAYER);

        lb_conversasAtivas1.setBackground(new java.awt.Color(51, 51, 51));
        lb_conversasAtivas1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lb_conversasAtivas1.setForeground(new java.awt.Color(255, 255, 255));
        lb_conversasAtivas1.setText("Contatos conectados");
        lb_conversasAtivas1.setOpaque(true);
        lb_conversasAtivas1.setBounds(0, 40, 400, 15);
        jLayeredPane1.add(lb_conversasAtivas1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        scroll_desconectados.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll_desconectados.setOpaque(false);

        pn_desconectados.setOpaque(false);

        javax.swing.GroupLayout pn_desconectadosLayout = new javax.swing.GroupLayout(pn_desconectados);
        pn_desconectados.setLayout(pn_desconectadosLayout);
        pn_desconectadosLayout.setHorizontalGroup(
            pn_desconectadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 398, Short.MAX_VALUE)
        );
        pn_desconectadosLayout.setVerticalGroup(
            pn_desconectadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 208, Short.MAX_VALUE)
        );

        scroll_desconectados.setViewportView(pn_desconectados);

        scroll_desconectados.setBounds(0, 280, 400, 210);
        jLayeredPane1.add(scroll_desconectados, javax.swing.JLayeredPane.DEFAULT_LAYER);

        scroll_conectados.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll_conectados.setOpaque(false);

        pn_conectados.setOpaque(false);

        javax.swing.GroupLayout pn_conectadosLayout = new javax.swing.GroupLayout(pn_conectados);
        pn_conectados.setLayout(pn_conectadosLayout);
        pn_conectadosLayout.setHorizontalGroup(
            pn_conectadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 398, Short.MAX_VALUE)
        );
        pn_conectadosLayout.setVerticalGroup(
            pn_conectadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 198, Short.MAX_VALUE)
        );

        scroll_conectados.setViewportView(pn_conectados);

        scroll_conectados.setBounds(0, 60, 400, 200);
        jLayeredPane1.add(scroll_conectados, javax.swing.JLayeredPane.DEFAULT_LAYER);

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

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagens/fundo_login.png"))); // NOI18N
        jLabel2.setBounds(0, 40, 400, 480);
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
            .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        confirmar();
    }//GEN-LAST:event_jLabel3MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lb_conversasAtivas;
    private javax.swing.JLabel lb_conversasAtivas1;
    public javax.swing.JPanel pn_conectados;
    public javax.swing.JPanel pn_desconectados;
    private javax.swing.JScrollPane scroll_conectados;
    private javax.swing.JScrollPane scroll_desconectados;
    // End of variables declaration//GEN-END:variables

    private void preencherContatosConectados() {
        ExibirListaChekBox lista = new ExibirListaChekBox();
        for (Object object : listarContatosConectados()) {
            ContatoBean c = (ContatoBean) object;
            if(!c.getNome().equals(conversa.getDescricao())) {
                lista.exibirAssociarContatosConectados(this, c.getNome(), c.getCodigo());
            }
        }
    }
    
    private void preencherContatosDesconectados() {
        ExibirListaChekBox lista = new ExibirListaChekBox();
        for (Object object : listarContatosDesconectados()) {
            ContatoBean c = (ContatoBean) object;
            lista.exibirAssociarContatosDesconectados(this, c.getNome(), c.getCodigo());
        }
    }
    
    private void confirmar() {
        String ipServidor;
        Cliente cliente = Instancias.getConversasCliente().get(conversa.getCodigo());
        if (cliente == null) {
            cliente = Instancias.getConversasServidor().get(conversa.getCodigo());
        }
        if (cliente != null) {
            ipServidor = cliente.getSocket().getInetAddress().getHostAddress();
            enviarLocalizacaoServidor(ipServidor, cliente);
            enviarLocalizacaoMensagem(ipServidor, cliente);
        } 
        dispose();
    }

    private void enviarLocalizacaoServidor(String ip, Cliente cliente) {
        Component[] components = pn_conectados.getComponents();
        for (int i = 0; i < components.length; i++) {
            ListaCheckBox lista = (ListaCheckBox) components[i];
            System.out.println(lista.ck_check.isSelected());
            if(lista.ck_check.isSelected()) {
                ContatoBean contato = new ContatoBean();
                contato.setCodigo(lista.getIdentificacao());
                ContatoBean c = (ContatoBean) selecionar(contato);
                cliente.enviarDados(ConstantesDiversas.CS_ENCAMINHAR_ASSOCIAR_CONVERSA);
                cliente.enviarDados(ip);
                cliente.enviarDados(cliente.getIdentificacaoConversa());
                cliente.enviarDados(cliente.getConversa().getDescricao() + " / " + Instancias.getDono().getNome());
                cliente.enviarDados(c.getNome());
                Instancias.getMensagens().sucesso(Textos.associarContatos_requisicao());
            }
        }
    }

    private void enviarLocalizacaoMensagem(String ip, Cliente cliente) {
        Component[] components = pn_desconectados.getComponents();
        for (int i = 0; i < components.length; i++) {
            ListaCheckBox lista = (ListaCheckBox) components[i];
            if(lista.ck_check.isSelected()) {
                ContatoBean contato = new ContatoBean();
                contato.setCodigo(lista.getIdentificacao());
                ContatoBean c = (ContatoBean) selecionar(contato);
                String mensagem = ConstantesDiversas.PADRAO_MENSAGEM + ip + "-" + cliente.getIdentificacaoConversa() + "-" + cliente.getConversa().getDescricao() + " / " + Instancias.getDono().getNome();
                Celular sms = new Celular(c.getCelular(), c.getDdd(), mensagem);
                if(sms.modem()) {
                    Instancias.getMensagens().sucesso(Textos.associarContatos_requisicao());
                } else Instancias.getMensagens().bug(Textos.novaConversaServidor_smsErro());
            }
        }
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

    private ArrayList<Object> listarContatosDesconectados() {
        try {
            return contatoFacade.listarContatosDesconectados();
        } catch (FacadeException e) {
        }
        return null;
    }

    private Object selecionar(ConversaBean conversa) {
        try {
            return conversaFacade.selecionar(conversa);
        } catch (FacadeException e) {
        }
        return false;
    }
    
    private Object selecionar(ContatoBean contato) {
        try {
            return contatoFacade.selecionar(contato);
        } catch (FacadeException e) {
        }
        return false;
    }
    
}
