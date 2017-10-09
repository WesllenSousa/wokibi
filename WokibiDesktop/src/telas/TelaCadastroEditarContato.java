package telas;

import controle.utilitarios.ConstantesTelas;
import controle.utilitarios.Instancias;
import controle.utilitarios.Results;
import entidades.contato.bean.ContatoBean;
import entidades.contato.facade.ContatoFacade;
import entidades.contato.facade.ContatoFacadeImpl;
import recursos.iternacionalizacao.Textos;
import util.FacadeException;
import util.ValidaTipos;

/**
 *
 * @author Wesllen Sousa Lima
 */
public class TelaCadastroEditarContato extends javax.swing.JDialog {

    private ContatoFacade contatoFacade;
    private ContatoBean contatoEditar;
    private Integer dono;

    public TelaCadastroEditarContato(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(405, 505);

        contatoFacade = new ContatoFacadeImpl();
    }

    public TelaCadastroEditarContato(java.awt.Frame parent, boolean modal, Integer codigo, Integer dono) {
        super(parent, modal);
        initComponents();
        setSize(405, 505);

        contatoFacade = new ContatoFacadeImpl();
  
        contatoEditar = new ContatoBean();
        if (codigo != null && codigo != 0) {
            contatoEditar.setCodigo(codigo);
            ContatoBean contato = selecionar(contatoEditar);
            preencherCampos(contato);
        }

        this.dono = dono;
        if (dono != null && dono != 0) {
            contatoEditar.setAutor(0);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabel5 = new javax.swing.JLabel();
        et_nome = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        et_ddd = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        et_celular = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        et_email = new javax.swing.JTextField();
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

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Nome:*");
        jLabel5.setBounds(20, 60, 60, 17);
        jLayeredPane1.add(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);

        et_nome.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        et_nome.setBounds(20, 80, 360, 30);
        jLayeredPane1.add(et_nome, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("DDD:*");
        jLabel6.setBounds(20, 120, 60, 17);
        jLayeredPane1.add(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);

        et_ddd.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        et_ddd.setBounds(20, 140, 360, 30);
        jLayeredPane1.add(et_ddd, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Celular:*");
        jLabel7.setBounds(20, 180, 90, 17);
        jLayeredPane1.add(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);

        et_celular.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        et_celular.setBounds(20, 200, 360, 30);
        jLayeredPane1.add(et_celular, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Email:");
        jLabel8.setBounds(20, 240, 60, 17);
        jLayeredPane1.add(jLabel8, javax.swing.JLayeredPane.DEFAULT_LAYER);

        et_email.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        et_email.setBounds(20, 260, 360, 30);
        jLayeredPane1.add(et_email, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagens/voltar_36.png"))); // NOI18N
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        jLabel4.setBounds(0, 0, 36, 36);
        jLayeredPane1.add(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagens/salvar_36.png"))); // NOI18N
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
        salvar();
    }//GEN-LAST:event_jLabel3MouseClicked

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
    private javax.swing.JTextField et_celular;
    private javax.swing.JTextField et_ddd;
    private javax.swing.JTextField et_email;
    private javax.swing.JTextField et_nome;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    /*
     * AÇOES ESPECIFICAS
     */
    
    private Boolean validaCampos() {
        if (ValidaTipos.stringVazia(et_nome.getText().toString())) {
            Instancias.getMensagens().aviso(Textos.cad_msgNome());
            et_nome.grabFocus();
            return false;
        } else if (!ValidaTipos.isIntegerNumber(et_ddd.getText().toString())) {
            Instancias.getMensagens().aviso(Textos.cad_msgDdd());
            et_ddd.grabFocus();
            return false;
        } else if (!ValidaTipos.isIntegerNumber(et_celular.getText().toString())) {
            Instancias.getMensagens().aviso(Textos.cad_msgCelular());
            et_celular.grabFocus();
            return false;
        } else if (!ValidaTipos.stringVazia(et_email.getText().toString())
                && !ValidaTipos.isEmail(et_email.getText().toString())) {
            Instancias.getMensagens().aviso(Textos.cad_msgEmail());
            et_email.grabFocus();
            return false;
        } else if (selecionarContatoPorNome(et_nome.getText().toString()) != null) {
            et_nome.grabFocus();
            if (contatoEditar != null && contatoEditar.getNome() != null && contatoEditar.getNome().equals(et_nome.getText().toString())) {
                return true;
            } else {
                Instancias.getMensagens().aviso(Textos.cad_msgNomeExiste());
                return false;
            }
        }
        return true;
    }

    private ContatoBean populaContatoBean() {
        ContatoBean contato = new ContatoBean();

        if (contatoEditar != null && contatoEditar.getCodigo() != null) {
            contato.setCodigo(contatoEditar.getCodigo());
        }
        if (contatoEditar != null && contatoEditar.getAutor() != null) {
            contato.setAutor(contatoEditar.getAutor());
        } else {
            contato.setAutor(1); //1 : não é autor	
        }
        if (contatoEditar != null && contatoEditar.getStatus() != null) {
            contato.setStatus(contatoEditar.getStatus());
        } else {
            contato.setStatus(1); //1 : desconectado
        }
        contato.setNome(et_nome.getText().toString());
        contato.setDdd(Integer.parseInt(et_ddd.getText().toString()));
        contato.setCelular(Integer.parseInt(et_celular.getText().toString()));
        contato.setEmail(et_email.getText().toString());
        contato.setDeletar(0);
        contato.setPorta(0);

        return contato;
    }

    private void salvar() {
        if (validaCampos()) {
            ContatoBean contato = inserirOuAlterar(populaContatoBean());
            if (contato != null) {
                Instancias.getMensagens().sucesso(Textos.cad_msgSalvo());
                if (dono != null && dono != 0) {
                    Instancias.setDono(contato);
                    seguirParaTelaPrincipal();
                } else {
                    Results.getTelaListarContatos().onResult(ConstantesTelas.EDITAR_EXCLUIR);
                }
                dispose();
            }
        }
    }

    private void preencherCampos(ContatoBean contato) {
        et_nome.setText(contato.getNome().toString());
        et_ddd.setText(contato.getDdd().toString());
        et_celular.setText(contato.getCelular().toString());
        et_email.setText(contato.getEmail().toString());
    }

    private void seguirParaTelaPrincipal() {
        TelaPrincipal dialog = new TelaPrincipal();
        dialog.setVisible(true);
    }

    /*
     * BANCO DE DADOS
	 *
     */
    private ContatoBean selecionar(ContatoBean contato) {
        try {
            return (ContatoBean) contatoFacade.selecionar(contato);
        } catch (FacadeException e) {
        }
        return null;
    }

    private ContatoBean inserirOuAlterar(ContatoBean contato) {
        try {
            return (ContatoBean) contatoFacade.inserirOuAlterar(contato);
        } catch (FacadeException e) {
        }
        return null;
    }

    private ContatoBean selecionarContatoPorNome(String nome) {
        try {
            return (ContatoBean) contatoFacade.selecionarContatoPorNome(nome);
        } catch (FacadeException e) {
        }
        return null;
    }
    
}
