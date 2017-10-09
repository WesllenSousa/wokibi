package telas;

import controle.mensagens.Celular;
import controle.mensagens.Email;
import controle.utilitarios.*;
import entidades.contato.bean.ContatoBean;
import entidades.contato.facade.ContatoFacade;
import entidades.contato.facade.ContatoFacadeImpl;
import java.awt.Component;
import java.util.ArrayList;
import recursos.iternacionalizacao.Textos;
import telas.listas.ExibirListaChekBox;
import telas.listas.ListaCheckBox;
import util.FacadeException;
import util.RedeUtil;
import util.ValidaTipos;

/**
 *
 * @author Wesllen Sousa Lima
 */
public class TelaNovaConversaServidor extends javax.swing.JDialog {

    private ContatoFacade contatoFacade;
    private Boolean enviado = false;

    public TelaNovaConversaServidor(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(405, 505);
        scroll_contatos.getViewport().setOpaque(false);

        contatoFacade = new ContatoFacadeImpl();
        listarContatosLista();
    }

    public TelaNovaConversaServidor(java.awt.Frame parent, boolean modal, Integer codigo) {
        super(parent, modal);
        initComponents();
        setSize(405, 505);
        scroll_contatos.getViewport().setOpaque(false);

        contatoFacade = new ContatoFacadeImpl();
        listarContatosLista();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        ck_email = new javax.swing.JCheckBox();
        ck_celular = new javax.swing.JCheckBox();
        scroll_contatos = new javax.swing.JScrollPane();
        pn_contatos = new javax.swing.JPanel();
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

        jLabel6.setBackground(new java.awt.Color(51, 51, 51));
        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Você deseja iniciar conversa com...");
        jLabel6.setOpaque(true);
        jLabel6.setBounds(0, 90, 400, 15);
        jLayeredPane1.add(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel7.setBackground(new java.awt.Color(51, 51, 51));
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Informar localização por");
        jLabel7.setOpaque(true);
        jLabel7.setBounds(0, 40, 400, 15);
        jLayeredPane1.add(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);

        ck_email.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ck_email.setText("Email");
        ck_email.setOpaque(false);
        ck_email.setBounds(80, 60, 81, 23);
        jLayeredPane1.add(ck_email, javax.swing.JLayeredPane.DEFAULT_LAYER);

        ck_celular.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ck_celular.setText("Celular");
        ck_celular.setOpaque(false);
        ck_celular.setBounds(0, 60, 81, 23);
        jLayeredPane1.add(ck_celular, javax.swing.JLayeredPane.DEFAULT_LAYER);

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
            .addGap(0, 378, Short.MAX_VALUE)
        );

        scroll_contatos.setViewportView(pn_contatos);

        scroll_contatos.setBounds(0, 110, 400, 380);
        jLayeredPane1.add(scroll_contatos, javax.swing.JLayeredPane.DEFAULT_LAYER);

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

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (Instancias.getMensagens().confirmacao(Textos.mensagem_confirmarSaida())) {
            Instancias.getTelaPrincipal().fechar();
        }
    }//GEN-LAST:event_formWindowClosing

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
       confirmar();
    }//GEN-LAST:event_jLabel3MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox ck_celular;
    private javax.swing.JCheckBox ck_email;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JPanel pn_contatos;
    private javax.swing.JScrollPane scroll_contatos;
    // End of variables declaration//GEN-END:variables

    private void listarContatosLista() {
        ExibirListaChekBox lista = new ExibirListaChekBox();
        for (Object object : listarContatos()) {
            ContatoBean c = (ContatoBean) object;
            lista.exibirNovaConversaServidor(this, c.getNome(), c.getCodigo());
        }
    }

    /*
     * AÇOES ESPECIFICAS
     *
     */
    private void confirmar() {
        if (ck_celular.isSelected()) {
            Preferencias p = new Preferencias();
            if (p.getServicoSms()) {
                enviarSms();
            } else {
                Instancias.getMensagens().aviso(Textos.novaConversaServidor_servicoMensagem());
            }
        }
        if (ck_email.isSelected()) {
            enviarEmail();
        }
        if (!ck_celular.isSelected() && !ck_email.isSelected()) {
            Instancias.getMensagens().aviso(Textos.novaConversaServidor_localizacao());
        }
        if(enviado) {
            Results.getTelaConversa().onResult(ConstantesTelas.NOVA_CONVERSA_SERVIDOR, null);
            dispose();
        }
    }

    private void enviarSms() {
        Component[] components = pn_contatos.getComponents();
        for (int i = 0; i < components.length; i++) {
            ListaCheckBox lista = (ListaCheckBox) components[i];
            if(lista.ck_check.isSelected()) {
                ContatoBean contato = new ContatoBean();
                contato.setCodigo(lista.getIdentificacao());
                ContatoBean c = (ContatoBean) selecionar(contato);
                String mensagem = ConstantesDiversas.PADRAO_MENSAGEM + RedeUtil.obtemIpLocal() + "-0-" + contato.getNome();
                Celular sms = new Celular(c.getCelular(), c.getDdd(), mensagem);
                if(sms.modem()) {
                    Instancias.getMensagens().sucesso(Textos.novaConversaServidor_sms());
                    enviado = true;
                } else {
                    Instancias.getMensagens().bug(Textos.novaConversaServidor_smsErro());
                }
            }
        }
    }

    private void enviarEmail() {
        Component[] components = pn_contatos.getComponents();
        for (int i = 0; i < components.length; i++) {
            ListaCheckBox lista = (ListaCheckBox) components[i];
            if(lista.ck_check.isSelected()) {
                ContatoBean contato = new ContatoBean();
                contato.setCodigo(lista.getIdentificacao());
                ContatoBean c = (ContatoBean) selecionar(contato);
                if(!ValidaTipos.stringVazia(c.getEmail())) {
                    Email email = new Email("smtp.gmail.com", 465);
                    email.origem("wokibi.wsl@gmail.com", "Wokibi");
                    email.autenticacao("wokibi.wsl", "wokibi@java");
                    email.destino(c.getEmail(), c.getNome());
                    email.corpo("Wokibi", "Localização do Servidor: "+RedeUtil.obtemIpLocal());
                    if(email.enviar()) {
                        Instancias.getMensagens().sucesso(Textos.novaConversaServidor_email());
                        enviado = true;
                    }
                } else {
                    Instancias.getMensagens().aviso(Textos.novaConversaServidor_emailInvalido());
                }
            }
        }
    }

    /*
     * BANCO DE DADOS
     */
    private ArrayList<Object> listarContatos() {
        try {
            return contatoFacade.listarAmigosOrdenadoPorNome();
        } catch (FacadeException e) {
        }
        return null;
    }
    
    private Object selecionar(ContatoBean contato) {
        try {
            return contatoFacade.selecionar(contato);
        } catch (FacadeException e) {
        }
        return null;
    }
 
}
