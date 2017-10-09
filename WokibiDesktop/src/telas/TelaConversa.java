package telas;

import controle.clienteServidor.Cliente;
import controle.clienteServidor.Servidor;
import controle.mensagens.Celular;
import controle.utilitarios.ConstantesDiversas;
import controle.utilitarios.ConstantesTelas;
import controle.utilitarios.Instancias;
import controle.utilitarios.Results;
import entidades.contato.bean.ContatoBean;
import entidades.contato.facade.ContatoFacade;
import entidades.contato.facade.ContatoFacadeImpl;
import entidades.conversa.bean.ConversaBean;
import entidades.conversa.facade.ConversaFacade;
import entidades.conversa.facade.ConversaFacadeImpl;
import java.util.ArrayList;
import java.util.HashMap;
import recursos.iternacionalizacao.Textos;
import telas.dependencias.DialogGeral;
import telas.listas.ExibirListaConversa;
import util.FacadeException;
import util.RedeUtil;

/**
 *
 * @author Wesllen Sousa Lima
 */
public class TelaConversa extends javax.swing.JDialog {

    private ConversaFacade conversaFacade;
    private ContatoFacade contatoFacade;
    private ConversaBean conversaSelecionada;
    private ExibirListaConversa lista = new ExibirListaConversa(this);

    public TelaConversa(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(405, 505);
        scroll_ativo.getViewport().setOpaque(false);
        scroll_inativo.getViewport().setOpaque(false);
        Results.setTelaConversa(TelaConversa.this);
        Instancias.setTelaSelecionada(TelaConversa.this);

        conversaFacade = new ConversaFacadeImpl();
        contatoFacade = new ContatoFacadeImpl();

        preencherListas();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        scroll_inativo = new javax.swing.JScrollPane();
        pn_inativo = new javax.swing.JPanel();
        scroll_ativo = new javax.swing.JScrollPane();
        pn_ativo = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        lb_conversasInativas = new javax.swing.JLabel();
        lb_conversasAtivas = new javax.swing.JLabel();
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

        scroll_inativo.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll_inativo.setOpaque(false);

        pn_inativo.setOpaque(false);

        javax.swing.GroupLayout pn_inativoLayout = new javax.swing.GroupLayout(pn_inativo);
        pn_inativo.setLayout(pn_inativoLayout);
        pn_inativoLayout.setHorizontalGroup(
            pn_inativoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 398, Short.MAX_VALUE)
        );
        pn_inativoLayout.setVerticalGroup(
            pn_inativoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 208, Short.MAX_VALUE)
        );

        scroll_inativo.setViewportView(pn_inativo);

        scroll_inativo.setBounds(0, 280, 400, 210);
        jLayeredPane1.add(scroll_inativo, javax.swing.JLayeredPane.DEFAULT_LAYER);

        scroll_ativo.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll_ativo.setOpaque(false);

        pn_ativo.setOpaque(false);

        javax.swing.GroupLayout pn_ativoLayout = new javax.swing.GroupLayout(pn_ativo);
        pn_ativo.setLayout(pn_ativoLayout);
        pn_ativoLayout.setHorizontalGroup(
            pn_ativoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 398, Short.MAX_VALUE)
        );
        pn_ativoLayout.setVerticalGroup(
            pn_ativoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 198, Short.MAX_VALUE)
        );

        scroll_ativo.setViewportView(pn_ativo);

        scroll_ativo.setBounds(0, 60, 400, 200);
        jLayeredPane1.add(scroll_ativo, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagens/inicio_36.png"))); // NOI18N
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        jLabel4.setBounds(0, 0, 36, 36);
        jLayeredPane1.add(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        lb_conversasInativas.setBackground(new java.awt.Color(51, 51, 51));
        lb_conversasInativas.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lb_conversasInativas.setForeground(new java.awt.Color(255, 255, 255));
        lb_conversasInativas.setText("Conversas Inativas");
        lb_conversasInativas.setOpaque(true);
        lb_conversasInativas.setBounds(0, 260, 400, 15);
        jLayeredPane1.add(lb_conversasInativas, javax.swing.JLayeredPane.DEFAULT_LAYER);

        lb_conversasAtivas.setBackground(new java.awt.Color(51, 51, 51));
        lb_conversasAtivas.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lb_conversasAtivas.setForeground(new java.awt.Color(255, 255, 255));
        lb_conversasAtivas.setText("Conversas Ativas");
        lb_conversasAtivas.setOpaque(true);
        lb_conversasAtivas.setBounds(0, 40, 400, 15);
        jLayeredPane1.add(lb_conversasAtivas, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagens/adicionar_36.png"))); // NOI18N
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
        DialogGeral dialog = new DialogGeral(null, true);
        dialog.abrirTela(DialogGeral.TELA_INFO_SERVIDOR, null);
        dialog.setVisible(true);
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
    private javax.swing.JLabel lb_conversasAtivas;
    private javax.swing.JLabel lb_conversasInativas;
    public javax.swing.JPanel pn_ativo;
    public javax.swing.JPanel pn_inativo;
    private javax.swing.JScrollPane scroll_ativo;
    private javax.swing.JScrollPane scroll_inativo;
    // End of variables declaration//GEN-END:variables

    /*
     * AÇOES ESPECIFICAS
     */
    private void preencherListas() {
        lista.zerarY();
        pn_ativo.removeAll();
        ArrayList<Object> conversasAtivas = listarTodasConversasAtivas();
        if (conversasAtivas != null && !conversasAtivas.isEmpty()) {
            for(Object conversa : conversasAtivas) {
                ConversaBean c = (ConversaBean) conversa;
                lista.exibirAtivo(c.getDescricao(), 1, c.getCodigo());
            }
        }
        pn_inativo.removeAll();
        ArrayList<Object> conversasInativas = listarTodasConversasInativas();
        if (conversasInativas != null && !conversasInativas.isEmpty()) {
            for(Object conversa : conversasInativas) {
                ConversaBean c = (ConversaBean) conversa;
                lista.exibirInativo(c.getDescricao(), 2, c.getCodigo());
            }  
        }
    }

    public void conectar(Integer codigo) {
        ConversaBean c = new ConversaBean();
        c.setCodigo(codigo);
        ConversaBean conversa = (ConversaBean) selecionar(c);
        if (Instancias.getMensagens().confirmacao(Textos.conversa_conectar())) { //Entrar
            if (conversa != null) {
                TelaNovaConversaCliente dialog = new TelaNovaConversaCliente(null, true, conversa.getCodigo());
                dialog.setVisible(true);
            } else {
                TelaNovaConversaCliente dialog = new TelaNovaConversaCliente(null, true);
                dialog.setVisible(true);
            }
        } else { //Criar
            if (Instancias.getServidor() == null) {
                Servidor servidor = new Servidor();
                Thread thread = new Thread(servidor);
                thread.start();
                Results.getTelaPrincipal().onResult(ConstantesTelas.CRIAR_SERVIDOR);
            }
            if (conversa != null) {
                enviarSms(conversa); 
            } else {
                TelaNovaConversaServidor dialog = new TelaNovaConversaServidor(null, true);
                dialog.setVisible(true);
            }
        }
    }
    
    private void enviarSms(ConversaBean conversa) {
        ContatoBean contato = (ContatoBean) selecionarContatoPorNome(conversa.getDescricao());
        if(contato != null) {
            String mensagem = ConstantesDiversas.PADRAO_MENSAGEM + RedeUtil.obtemIpLocal() + "-0-" + contato.getNome();
            Celular sms = new Celular(contato.getCelular(), contato.getDdd(), mensagem);
            if(sms.modem()) {
                Instancias.getMensagens().sucesso(Textos.novaConversaServidor_sms());
            } else {
                Instancias.getMensagens().bug(Textos.novaConversaServidor_smsErro());
            }
        } else {
            Instancias.getMensagens().bug(Textos.conversa_contatoInexistente());
        }
    }

    private void seguirParaTelaMensagens() {
        TelaMensagens dialog = new TelaMensagens(null, true, conversaSelecionada.getCodigo());
        dialog.setVisible(true);
    }

    public void desconectar(Integer codigo) {
        if(Instancias.getMensagens().confirmacao(Textos.conversa_desconectar())) {
            ConversaBean c = new ConversaBean();
            c.setCodigo(codigo);
            ConversaBean conversa = (ConversaBean) selecionar(c);
            Cliente cliente = Instancias.getConversasCliente().get(conversa.getCodigo());
            if(cliente == null) {
                cliente = Instancias.getConversasServidor().get(conversa.getCodigo());
            }
            if (cliente != null) {
                cliente.fecharConexao();
                preencherListas();
            }
        }
    }

    public void perguntarExclusao(Integer codigo) {
        if(Instancias.getMensagens().confirmacao(Textos.dialog_perguntarExcluir())) {
            ConversaBean conversa = new ConversaBean();
            conversa.setCodigo(codigo);
            ConversaBean c = (ConversaBean) selecionar(conversa);
            desconectar(codigo); 
            if (excluir(c) != null) {
                preencherListas();
                Instancias.getMensagens().sucesso(Textos.conversa_excluir());
            }
        }
    }

    /*
     * BANCO DE DADOS
	 *
     */
    private ArrayList<Object> listarTodasConversasAtivas() {
        try {
            return conversaFacade.listarTodasConversasAtivas();
        } catch (FacadeException e) {
        }
        return null;
    }

    private ArrayList<Object> listarTodasConversasInativas() {
        try {
            return conversaFacade.listarTodasConversasInativas();
        } catch (FacadeException e) {
        }
        return null;
    }

    private Object excluir(ConversaBean conversa) {
        try {
            conversa.setDeletar(1);
            return conversaFacade.alterar(conversa);
        } catch (FacadeException e) {
        }
        return false;
    }

    private Object selecionar(ConversaBean conversa) {
        try {
            return conversaFacade.selecionar(conversa);
        } catch (FacadeException e) {
        }
        return false;
    }
    
    private Object selecionarContatoPorNome(String nome) {
        try {
            return contatoFacade.selecionarContatoPorNome(nome);
        } catch (FacadeException e) {
        }
        return false;
    }

    public void onResult(Integer resultado, HashMap<String, String> mapa) {
        Instancias.setTelaSelecionada(TelaConversa.this); 
        switch (resultado) {
            case ConstantesTelas.NOVA_CONVERSA:
                String codigoConversa = mapa.get(ConstantesDiversas.BD_CODIGO_CONVERSA);
                ConversaBean conversa = new ConversaBean();
                conversa.setCodigo(Integer.parseInt(codigoConversa));
                conversaSelecionada = (ConversaBean) selecionar(conversa);
                preencherListas();
                seguirParaTelaMensagens();
                break;
            case ConstantesTelas.NOVA_CONVERSA_SERVIDOR:
                dispose();
                break;
            case ConstantesTelas.NOTIFICAR_CONVERSA:
                String status = mapa.get(ConstantesDiversas.BD_NOTIFICAR_CONVERSA);
                String codigo = mapa.get(ConstantesDiversas.BD_CODIGO_CONVERSA);
                if (Integer.parseInt(status) == 0) {
                    desconectar(Integer.parseInt(codigo));
                } else {
                    preencherListas();
                }
                break;
        }
    }
}
