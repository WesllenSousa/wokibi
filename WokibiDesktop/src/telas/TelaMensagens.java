package telas;

import controle.arquivos.Caminhos;
import controle.clienteServidor.Cliente;
import controle.notificacao.Notificacao;
import controle.utilitarios.ConstantesDiversas;
import controle.utilitarios.ConstantesTelas;
import controle.utilitarios.Instancias;
import controle.utilitarios.Results;
import entidades.contato.bean.ContatoBean;
import entidades.conversa.bean.ConversaBean;
import entidades.conversa.facade.ConversaFacade;
import entidades.conversa.facade.ConversaFacadeImpl;
import entidades.mensagem.bean.MensagemBean;
import entidades.mensagem.facade.MensagemFacade;
import entidades.mensagem.facade.MensagemFacadeImpl;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import javax.swing.BoundedRangeModel;
import javax.swing.ImageIcon;
import recursos.iternacionalizacao.Textos;
import telas.listas.ExibirListaMensagem;
import util.DataUtil;
import util.FacadeException;

/**
 *
 * @author Wesllen Sousa Lima
 */
public class TelaMensagens extends javax.swing.JDialog {

    private ConversaFacade conversaFacade;
    private MensagemFacade mensagemFacade;
    private ConversaBean conversa;
    private Cliente cliente;
    private Boolean oculto;
    private ExibirListaMensagem lista = new ExibirListaMensagem(this);

    public TelaMensagens(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(405, 515);
        scroll.getViewport().setOpaque(false);
        Results.setTelaMensagens(TelaMensagens.this);
        Instancias.setTelaSelecionada(TelaMensagens.this);

        mensagemFacade = new MensagemFacadeImpl();
        conversaFacade = new ConversaFacadeImpl();
    }

    public TelaMensagens(java.awt.Frame parent, boolean modal, Integer codigo) {
        super(parent, modal);
        initComponents();
        setSize(405, 515);
        scroll.getViewport().setOpaque(false);
        Results.setTelaMensagens(TelaMensagens.this);
        Instancias.setTelaSelecionada(TelaMensagens.this);

        mensagemFacade = new MensagemFacadeImpl();
        conversaFacade = new ConversaFacadeImpl();

        ConversaBean c = new ConversaBean();
        c.setCodigo(codigo);
        conversa = (ConversaBean) selecionar(c);
        if (conversa != null) {
            preencherMensagens();
            if (conversa.getStatus() == 1) {
                verificarBotoes();
            }
        }

        recuperaCliente();
        verificaNotificacao();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        et_mensagem = new javax.swing.JTextArea();
        scroll = new javax.swing.JScrollPane();
        pn_mensagem = new javax.swing.JPanel();
        bt_desocultar = new javax.swing.JLabel();
        bt_adicionarUsuario = new javax.swing.JLabel();
        bt_enviar = new javax.swing.JLabel();
        bt_voltar = new javax.swing.JLabel();
        bt_sair = new javax.swing.JLabel();
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

        et_mensagem.setColumns(20);
        et_mensagem.setLineWrap(true);
        et_mensagem.setRows(5);
        jScrollPane1.setViewportView(et_mensagem);

        jScrollPane1.setBounds(0, 390, 360, 100);
        jLayeredPane1.add(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        scroll.setBackground(new java.awt.Color(255, 255, 255));
        scroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setName("");
        scroll.setOpaque(false);

        pn_mensagem.setOpaque(false);

        javax.swing.GroupLayout pn_mensagemLayout = new javax.swing.GroupLayout(pn_mensagem);
        pn_mensagem.setLayout(pn_mensagemLayout);
        pn_mensagemLayout.setHorizontalGroup(
            pn_mensagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 398, Short.MAX_VALUE)
        );
        pn_mensagemLayout.setVerticalGroup(
            pn_mensagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 348, Short.MAX_VALUE)
        );

        scroll.setViewportView(pn_mensagem);

        scroll.setBounds(0, 40, 400, 350);
        jLayeredPane1.add(scroll, javax.swing.JLayeredPane.DEFAULT_LAYER);

        bt_desocultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagens/desocultar_36.png"))); // NOI18N
        bt_desocultar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_desocultarMouseClicked(evt);
            }
        });
        bt_desocultar.setBounds(280, 0, 36, 36);
        jLayeredPane1.add(bt_desocultar, javax.swing.JLayeredPane.DEFAULT_LAYER);

        bt_adicionarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagens/adicionar_contato_36.png"))); // NOI18N
        bt_adicionarUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_adicionarUsuarioMouseClicked(evt);
            }
        });
        bt_adicionarUsuario.setBounds(320, 0, 36, 36);
        jLayeredPane1.add(bt_adicionarUsuario, javax.swing.JLayeredPane.DEFAULT_LAYER);

        bt_enviar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagens/enviar_36.png"))); // NOI18N
        bt_enviar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_enviarMouseClicked(evt);
            }
        });
        bt_enviar.setBounds(360, 390, 36, 100);
        jLayeredPane1.add(bt_enviar, javax.swing.JLayeredPane.DEFAULT_LAYER);

        bt_voltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagens/voltar_36.png"))); // NOI18N
        bt_voltar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_voltarMouseClicked(evt);
            }
        });
        bt_voltar.setBounds(0, 0, 36, 36);
        jLayeredPane1.add(bt_voltar, javax.swing.JLayeredPane.DEFAULT_LAYER);

        bt_sair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagens/sair_36.png"))); // NOI18N
        bt_sair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_sairMouseClicked(evt);
            }
        });
        bt_sair.setBounds(360, 0, 36, 36);
        jLayeredPane1.add(bt_sair, javax.swing.JLayeredPane.DEFAULT_LAYER);

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
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-415)/2, (screenSize.height-527)/2, 415, 527);
    }// </editor-fold>//GEN-END:initComponents

    private void bt_voltarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_voltarMouseClicked
        if (cliente != null) {
            cliente.getTratarMensagem().setHandler(null);
        }
        if (Results.getTelaConversa() != null) {
            HashMap<String, String> mapa = new HashMap<>();
            mapa.put(ConstantesDiversas.BD_NOTIFICAR_CONVERSA, 1 + "");
            Results.getTelaConversa().onResult(ConstantesTelas.NOTIFICAR_CONVERSA, mapa);
        }
        dispose();
    }//GEN-LAST:event_bt_voltarMouseClicked

    private void bt_adicionarUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_adicionarUsuarioMouseClicked
        seguirParaTelaAssociarContato();
    }//GEN-LAST:event_bt_adicionarUsuarioMouseClicked

    private void bt_enviarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_enviarMouseClicked
        enviar();
    }//GEN-LAST:event_bt_enviarMouseClicked

    private void bt_desocultarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_desocultarMouseClicked
        if (oculto) {
            desocultar();
        } else {
            preencherMensagens();
        }
    }//GEN-LAST:event_bt_desocultarMouseClicked

    private void bt_sairMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_sairMouseClicked
        if (Instancias.getMensagens().confirmacao(Textos.dialog_perguntarDesconectar())) {
            desconectar();
        }
    }//GEN-LAST:event_bt_sairMouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (Instancias.getMensagens().confirmacao(Textos.mensagem_confirmarSaida())) {
            Instancias.getTelaPrincipal().fechar();
        }
    }//GEN-LAST:event_formWindowClosing
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bt_adicionarUsuario;
    private javax.swing.JLabel bt_desocultar;
    private javax.swing.JLabel bt_enviar;
    private javax.swing.JLabel bt_sair;
    private javax.swing.JLabel bt_voltar;
    private javax.swing.JTextArea et_mensagem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JPanel pn_mensagem;
    public javax.swing.JScrollPane scroll;
    // End of variables declaration//GEN-END:variables

    /*
     * AÇOES ESPECIFICAS
     */
    private void verificarBotoes() {
        et_mensagem.setEnabled(false);
        bt_enviar.setEnabled(false);
        bt_enviar.setIcon(new ImageIcon(getClass().getResource(Caminhos.mensagem_enviarDesativado())));
        bt_sair.setIcon(new ImageIcon(getClass().getResource(Caminhos.mensagem_sairDesativado())));
        bt_sair.setEnabled(false);
        bt_adicionarUsuario.setEnabled(false);
        bt_adicionarUsuario.setIcon(new ImageIcon(getClass().getResource(Caminhos.mensagem_addContatoDesativado())));
    }

    private void preencherMensagens() {
        oculto = true;
        pn_mensagem.removeAll();
        lista.zerarY();
        ArrayList<Object> mensagens = listarMensagensPorConversa(conversa.getCodigo());
        String data = "";
        if (mensagens != null && !mensagens.isEmpty()) {
            for (Object mensagem : mensagens) {
                MensagemBean m = (MensagemBean) mensagem;
                String d = DataUtil.formataData(m.getDataHora());
                if (!d.equals(data)) {
                    data = d;
                } else {
                    d = "";
                }
                lista.exibir(d, m);
            }
        }   
        pn_mensagem.repaint();
        bt_desocultar.setIcon(new ImageIcon(getClass().getResource(Caminhos.mensagem_desocultar())));
        rolarAteFinal();
    }

    private void rolarAteFinal() {
        scroll.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
            BoundedRangeModel brm = scroll.getVerticalScrollBar().getModel();
            boolean wasAtBottom = true;
            @Override
            public void adjustmentValueChanged(AdjustmentEvent arg0) {
                if (!brm.getValueIsAdjusting()) {
                    if (wasAtBottom) {
                        brm.setValue(brm.getMaximum());
                    }
                } else {
                    wasAtBottom = ((brm.getValue() + brm.getExtent()) == brm.getMaximum());
                }
            }
        });
    }

    private void recuperaCliente() {
        if (Instancias.getConversasCliente() != null && Instancias.getConversasCliente().containsKey(conversa.getCodigo())) {
            cliente = Instancias.getConversasCliente().get(conversa.getCodigo());
        } else if (Instancias.getConversasServidor() != null && Instancias.getConversasServidor().containsKey(conversa.getCodigo())) {
            cliente = Instancias.getConversasServidor().get(conversa.getCodigo());
        }
        if (cliente != null) {
            cliente.getTratarMensagem().setHandler(this);
        }
    }

    private void enviar() {
        if (!et_mensagem.getText().equals("")) {
            final String mensagem = et_mensagem.getText();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (cliente != null) {
                        cliente.enviarDados(ConstantesDiversas.CS_MENSAGEM);
                        cliente.enviarDados(mensagem);
                        cliente.enviarDados(Instancias.getDono().getNome());
                    }
                }
            }).start();
            inserir(populaMensagem(et_mensagem.getText(), Instancias.getDono(), conversa));
            preencherMensagens();
            et_mensagem.setText("");
        }
    }

    private MensagemBean populaMensagem(String mensagem, ContatoBean contato, ConversaBean conversa) {
        MensagemBean bean = new MensagemBean();

        bean.setContato(contato);
        bean.setConversa(conversa);
        bean.setDataHora(Calendar.getInstance());
        bean.setDeletar(0);
        bean.setOcultar(0);
        bean.setTexto(mensagem);

        return bean;
    }

    public void receber(String mensagem) {
        preencherMensagens();
    }

    private void seguirParaTelaAssociarContato() {
        TelaAssociarContato dialog = new TelaAssociarContato(null, true, conversa.getCodigo());
        dialog.setVisible(true);
    }

    private void desconectar() {
        HashMap<String, String> mapa = new HashMap<>();
        mapa.put(ConstantesDiversas.BD_NOTIFICAR_CONVERSA, 0 + "");
        mapa.put(ConstantesDiversas.BD_CODIGO_CONVERSA, conversa.getCodigo() + "");
        Results.getTelaConversa().onResult(ConstantesTelas.NOTIFICAR_CONVERSA, mapa);
        dispose();
    }

    private void verificaNotificacao() {
        for (Notificacao notificacao : Instancias.getNotificacoes()) {
            if (notificacao.getCodigoConversa() == conversa.getCodigo()) {
                Instancias.getNotificacoes().remove(notificacao);
                break;
            }
        }
        if (Instancias.getNotificacoes().isEmpty()) {
            Instancias.getTelaPrincipal().lb_notificacao.setVisible(false);
        }
    }

    public void deletar() {
        if (Instancias.getMensagens().confirmacao("Deseja realmente excluir a mensagem?")) {
            MensagemBean mensagem = new MensagemBean();
            mensagem.setCodigo(Instancias.getEntidadeSelecionada());
            MensagemBean m = (MensagemBean) selecionar(mensagem);
            m.setDeletar(1);
            inserirOuAlterar(m);
            preencherMensagens();
        }
    }

    public void ocultar() {
        if (Instancias.getMensagens().confirmacao("Deseja realmente ocultar a mensagem?")) {
            MensagemBean mensagem = new MensagemBean();
            mensagem.setCodigo(Instancias.getEntidadeSelecionada());
            MensagemBean m = (MensagemBean) selecionar(mensagem);
            m.setOcultar(1);
            inserirOuAlterar(m);
            preencherMensagens();
        }
    }

    private void desocultar() {
        oculto = false;
        lista.zerarY();
        pn_mensagem.removeAll();
        ArrayList<Object> mensagens = listarMensagensPorConversaOcultas(conversa.getCodigo());
        if (mensagens != null && !mensagens.isEmpty()) {
            for (Object mensagem : mensagens) {
                MensagemBean m = (MensagemBean) mensagem;
                lista.exibir(DataUtil.formataData(m.getDataHora()), m);
            }
        }
        pn_mensagem.repaint();
        bt_desocultar.setIcon(new ImageIcon(getClass().getResource(Caminhos.mensagem_ocultar())));
    }

    /*
     * BANCO DE DADOS
     *
     */
    private ArrayList<Object> listarMensagensPorConversa(Integer codigo) {
        try {
            return mensagemFacade.listarMensagensPorConversa(codigo);
        } catch (FacadeException e) {
        }
        return null;
    }

    private ArrayList<Object> listarMensagensPorConversaOcultas(Integer codigo) {
        try {
            return mensagemFacade.listarMensagensPorConversaOcultas(codigo);
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

    private Object selecionar(MensagemBean mensagem) {
        try {
            return mensagemFacade.selecionar(mensagem);
        } catch (FacadeException e) {
        }
        return false;
    }

    private Object inserirOuAlterar(MensagemBean mensagem) {
        try {
            return mensagemFacade.inserirOuAlterar(mensagem);
        } catch (FacadeException e) {
        }
        return null;
    }

    private Object inserir(MensagemBean mensagem) {
        try {
            return mensagemFacade.inserir(mensagem);
        } catch (FacadeException e) {
        }
        return null;
    }

    public void onResult(Integer resultado, HashMap<String, String> mapa) {
        switch (resultado) {
            case ConstantesDiversas.HD_NOVA_MENSAGEM:
                String mensagem = mapa.get(ConstantesDiversas.BD_MENSAGEM);
                receber(mensagem);
                break;
            default:
                break;
        }
    }

    public ConversaBean getConversa() {
        return conversa;
    }
    
}
