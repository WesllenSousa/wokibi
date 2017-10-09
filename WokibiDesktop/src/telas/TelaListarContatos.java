package telas;

import controle.utilitarios.ConstantesTelas;
import controle.utilitarios.Instancias;
import controle.utilitarios.Results;
import entidades.contato.bean.ContatoBean;
import entidades.contato.facade.ContatoFacade;
import entidades.contato.facade.ContatoFacadeImpl;
import java.util.ArrayList;
import recursos.iternacionalizacao.Textos;
import telas.listas.ExibirListaNormal;
import util.FacadeException;

/**
 *
 * @author Wesllen Sousa Lima
 */
public class TelaListarContatos extends javax.swing.JDialog {

    private ContatoFacade contatoFacade;
    private ContatoBean contatoSelecionado;

    public TelaListarContatos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(405, 505);
        Results.setTelaListarContatos(TelaListarContatos.this);
        scroll_dono.getViewport().setOpaque(false);
        scroll_amigos.getViewport().setOpaque(false);

        contatoFacade = new ContatoFacadeImpl();
        preencherListas();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        et_procurar = new javax.swing.JTextField();
        scroll_dono = new javax.swing.JScrollPane();
        pn_dono = new javax.swing.JPanel();
        lb_dono = new javax.swing.JLabel();
        lb_amigos = new javax.swing.JLabel();
        scroll_amigos = new javax.swing.JScrollPane();
        pn_amigos = new javax.swing.JPanel();
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

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagens/inicio_36.png"))); // NOI18N
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        jLabel4.setBounds(0, 0, 36, 36);
        jLayeredPane1.add(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagens/adicionar_36.png"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jLabel3.setBounds(360, 0, 36, 36);
        jLayeredPane1.add(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        et_procurar.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        et_procurar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                et_procurarKeyReleased(evt);
            }
        });
        et_procurar.setBounds(0, 150, 400, 24);
        jLayeredPane1.add(et_procurar, javax.swing.JLayeredPane.DEFAULT_LAYER);

        scroll_dono.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll_dono.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        scroll_dono.setOpaque(false);

        pn_dono.setOpaque(false);

        javax.swing.GroupLayout pn_donoLayout = new javax.swing.GroupLayout(pn_dono);
        pn_dono.setLayout(pn_donoLayout);
        pn_donoLayout.setHorizontalGroup(
            pn_donoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 398, Short.MAX_VALUE)
        );
        pn_donoLayout.setVerticalGroup(
            pn_donoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 68, Short.MAX_VALUE)
        );

        scroll_dono.setViewportView(pn_dono);

        scroll_dono.setBounds(0, 60, 400, 70);
        jLayeredPane1.add(scroll_dono, javax.swing.JLayeredPane.DEFAULT_LAYER);

        lb_dono.setBackground(new java.awt.Color(51, 51, 51));
        lb_dono.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lb_dono.setForeground(new java.awt.Color(255, 255, 255));
        lb_dono.setText("Eu");
        lb_dono.setOpaque(true);
        lb_dono.setBounds(0, 40, 400, 15);
        jLayeredPane1.add(lb_dono, javax.swing.JLayeredPane.DEFAULT_LAYER);

        lb_amigos.setBackground(new java.awt.Color(51, 51, 51));
        lb_amigos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lb_amigos.setForeground(new java.awt.Color(255, 255, 255));
        lb_amigos.setText("Amigos");
        lb_amigos.setOpaque(true);
        lb_amigos.setBounds(0, 130, 400, 15);
        jLayeredPane1.add(lb_amigos, javax.swing.JLayeredPane.DEFAULT_LAYER);

        scroll_amigos.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll_amigos.setOpaque(false);

        pn_amigos.setOpaque(false);

        javax.swing.GroupLayout pn_amigosLayout = new javax.swing.GroupLayout(pn_amigos);
        pn_amigos.setLayout(pn_amigosLayout);
        pn_amigosLayout.setHorizontalGroup(
            pn_amigosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 398, Short.MAX_VALUE)
        );
        pn_amigosLayout.setVerticalGroup(
            pn_amigosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 318, Short.MAX_VALUE)
        );

        scroll_amigos.setViewportView(pn_amigos);

        scroll_amigos.setBounds(0, 170, 400, 320);
        jLayeredPane1.add(scroll_amigos, javax.swing.JLayeredPane.DEFAULT_LAYER);

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

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        seguirParaTelaCadastroEditarContato();
    }//GEN-LAST:event_jLabel3MouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (Instancias.getMensagens().confirmacao(Textos.mensagem_confirmarSaida())) {
            Instancias.getTelaPrincipal().fechar();
        }
    }//GEN-LAST:event_formWindowClosing

    private void et_procurarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_et_procurarKeyReleased
        preencherListas();
    }//GEN-LAST:event_et_procurarKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField et_procurar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lb_amigos;
    private javax.swing.JLabel lb_dono;
    public javax.swing.JPanel pn_amigos;
    public javax.swing.JPanel pn_dono;
    private javax.swing.JScrollPane scroll_amigos;
    private javax.swing.JScrollPane scroll_dono;
    // End of variables declaration//GEN-END:variables

    /*
     * AÇOES ESPECIFICAS
     */
    private void preencherListas() {
        pn_dono.removeAll();
        pn_amigos.removeAll();
        ContatoBean contato = selecionarContatoDono();
        if (contato != null) {
            ArrayList<Object> contatos1 = new ArrayList<>();
            contatos1.add(contato);
            ExibirListaNormal lista = new ExibirListaNormal();
            for (Object c : contatos1) {
                lista.exibirListarContatosDono(c.toString(), this, contato.getCodigo());
            }
        }
        ArrayList<Object> contatos2 = null;
        if(et_procurar.getText().equals("")) {
            contatos2 = listarAmigosOrdenadoPorNome();
        } else {
            contatos2 = listarAmigosOrdenadoPorNome(et_procurar.getText().toString());
        }
        if(contatos2 != null) {
            ExibirListaNormal lista = new ExibirListaNormal();
            for (Object c : contatos2) {
                ContatoBean ct = (ContatoBean) c;
                lista.exibirListarContatosAmigos(c.toString(), this, ct.getCodigo());
            }
        }
        scroll_amigos.repaint();
    }

    private void seguirParaTelaCadastroEditarContato() {
        TelaCadastroEditarContato dialog = new TelaCadastroEditarContato(null, true);
        dialog.setVisible(true);
    }

    public void seguirParaTelaEditar() {
        contatoSelecionado = selecionar(Instancias.getEntidadeSelecionada());
        TelaCadastroEditarContato dialog = new TelaCadastroEditarContato(null, true, contatoSelecionado.getCodigo(), null);
        dialog.setVisible(true);
    }

    public void perguntarExlusao() {
        contatoSelecionado = selecionar(Instancias.getEntidadeSelecionada());
        if(Instancias.getMensagens().confirmacao(Textos.dialog_perguntarExcluir())) {
            if (excluir(contatoSelecionado) != null) {
                preencherListas();
                Instancias.getMensagens().sucesso(Textos.listarContatos_excluir());
            }
        }
    }

    /*
     * BANCO DE DADOS
     *
     */
    private ArrayList<Object> listarAmigosOrdenadoPorNome() {
        try {
            return contatoFacade.listarAmigosOrdenadoPorNome();
        } catch (FacadeException e) {
        }
        return null;
    }
    
    private ArrayList<Object> listarAmigosOrdenadoPorNome(String nome) {
        try {
            return contatoFacade.listarAmigosOrdenadoPorNome(nome);
        } catch (FacadeException e) {
        }
        return null;
    }

    private Object excluir(ContatoBean contato) {
        try {
            contato.setDeletar(1);
            return contatoFacade.alterar(contato);
        } catch (FacadeException e) {
        }
        return false;
    }

    private ContatoBean selecionarContatoDono() {
        try {
            return (ContatoBean) contatoFacade.selecionarContatoDono();
        } catch (FacadeException e) {
        }
        return null;
    }
    
    private ContatoBean selecionar(Integer codigo) {
        try {
            ContatoBean c = new ContatoBean();
            c.setCodigo(codigo);
            return (ContatoBean) contatoFacade.selecionar(c);
        } catch (FacadeException e) {
        }
        return null;
    }

    public void onResult(int resultado) {
        switch (resultado) {
            case ConstantesTelas.EDITAR_EXCLUIR:
                preencherListas();
                break;
        }
    }
    
}
