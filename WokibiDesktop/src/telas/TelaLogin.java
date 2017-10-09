package telas;

import controle.arquivos.Raiz;
import controle.clienteServidor.Servidor;
import controle.utilitarios.ConstantesDiversas;
import controle.utilitarios.Instancias;
import controle.utilitarios.Preferencias;
import entidades.contato.bean.ContatoBean;
import entidades.contato.facade.ContatoFacade;
import entidades.contato.facade.ContatoFacadeImpl;
import entidades.conversa.bean.ConversaBean;
import entidades.conversa.facade.ConversaFacade;
import entidades.conversa.facade.ConversaFacadeImpl;
import java.net.URISyntaxException;
import java.util.ArrayList;
import recursos.iternacionalizacao.Textos;
import util.Criptografia;
import util.FacadeException;

/**
 *
 * @author Wesllen Sousa Lima
 */
public class TelaLogin extends javax.swing.JDialog {
    
    public static boolean fechar = false;

    private ConversaFacade conversaFacade;
    private ContatoFacade contatoFacade;
    private String senhaAnterior;
    private ContatoBean dono;

    public TelaLogin(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(405, 505);
        //obtemRaizExecutavel();
        obtemRaizProjeto();

        conversaFacade = new ConversaFacadeImpl();
        contatoFacade = new ContatoFacadeImpl();

        Preferencias p = new Preferencias();
        senhaAnterior = p.getSenhaAnterior();
        String porta = p.getModem();
        if(porta != null) {
            ConstantesDiversas.PORTA_MODEM = p.getModem();
        }
        
        Servidor servidor = Instancias.getServidor();
        if (servidor == null || (servidor != null && servidor.getStatusConexao() == false)) {
            verificarStatusConversa();
            verificarStatusContato();
        }

        dono = selecionarContatoDono();
        if (dono != null) {
            Instancias.setDono(dono);
        }
        verificaSenha();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        bt_entrar = new javax.swing.JLabel();
        tf_senha = new javax.swing.JTextField();
        bt_conversa = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Wokibi");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        bt_entrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagens/enviar_36.png"))); // NOI18N
        bt_entrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_entrarMouseClicked(evt);
            }
        });
        bt_entrar.setBounds(350, 240, 36, 36);
        jLayeredPane1.add(bt_entrar, javax.swing.JLayeredPane.DEFAULT_LAYER);

        tf_senha.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tf_senha.setText("Senha...");
        tf_senha.setBounds(10, 240, 330, 30);
        jLayeredPane1.add(tf_senha, javax.swing.JLayeredPane.DEFAULT_LAYER);

        bt_conversa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bt_conversa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagens/logo_300.png"))); // NOI18N
        bt_conversa.setBounds(0, 10, 400, 180);
        jLayeredPane1.add(bt_conversa, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagens/fundo_login.png"))); // NOI18N
        jLabel2.setBounds(0, 0, 400, 500);
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
            .addComponent(jLayeredPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-416)/2, (screenSize.height-538)/2, 416, 538);
    }// </editor-fold>//GEN-END:initComponents

    private void bt_entrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_entrarMouseClicked
        autenticar();
    }//GEN-LAST:event_bt_entrarMouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (Instancias.getMensagens().confirmacao(Textos.mensagem_confirmarSaida())) {
            if(Instancias.getTelaPrincipal() != null) {
                Instancias.getTelaPrincipal().fechar();
            } else { 
                System.exit(0);
            }
        }
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bt_conversa;
    private javax.swing.JLabel bt_entrar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField tf_senha;
    // End of variables declaration//GEN-END:variables

    /*
     * AÇOES ESPECIFICAS
	 *
     */
    private void verificaSenha() {
        if (senhaAnterior.equals("")) {
            verificarDono();
        }
    }

    private void autenticar() {
        String senha = Criptografia.MD5(tf_senha.getText().toString());
        if (!senha.equals(senhaAnterior)) {
            Instancias.getMensagens().bug(Textos.login_senhaInvalida());
        } else {
            verificarDono();
        }
    }

    private void verificarDono() {
        if (dono == null) {
            fechar = true;
            Instancias.getMensagens().aviso(Textos.dialog_naoCadastro());
            seguirParaTelaCadastro();
        } else {
            seguirParaTelaPrincipal();
        }
    }

    private void verificarStatusConversa() {
        for (Object conversa : listarConversasAtivas()) {
            alterar((ConversaBean) conversa);
        }
    }

    private void verificarStatusContato() {
        for (Object contato : listarContatosConectados()) {
            alterar((ContatoBean) contato);
        }
    }

    private void seguirParaTelaCadastro() {
        TelaCadastroEditarContato dialog = new TelaCadastroEditarContato(null, true, 0, 1);
        dialog.setVisible(true);
    }

    private void seguirParaTelaPrincipal() {
        fechar = true;
        dispose();
        TelaPrincipal dialog = new TelaPrincipal();
        dialog.setVisible(true);
    }

    /*
     * BANCO DE DADOS
	 *
     */
    public ArrayList<Object> listarConversasAtivas() {
        try {
            return conversaFacade.listarConversasAtivas();
        } catch (FacadeException e) {
        }
        return null;
    }

    public ArrayList<Object> listarContatosConectados() {
        try {
            return contatoFacade.listarContatosConectados();
        } catch (FacadeException e) {
        }
        return null;
    }

    private void alterar(ContatoBean contato) {
        try {
            contato.setStatus(1);
            contatoFacade.alterar(contato);
        } catch (FacadeException e) {
        }
    }

    private void alterar(ConversaBean conversa) {
        try {
            conversa.setStatus(1);
            conversaFacade.alterar(conversa);
        } catch (FacadeException e) {
        }
    }

    private ContatoBean selecionarContatoDono() {
        try {
            return (ContatoBean) contatoFacade.selecionarContatoDono();
        } catch (FacadeException e) {
        }
        return null;
    }

    private void obtemRaizExecutavel() {
        try {
            String caminho = this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            caminho = caminho.substring(1, caminho.lastIndexOf('/') + 1);
            Raiz.setRaiz(caminho);
        } catch (URISyntaxException e) {
        }
    }

    private void obtemRaizProjeto() {
        Raiz.setRaiz(System.getProperty("user.dir"));
    }
    
}
