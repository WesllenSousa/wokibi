
package telas.dependencias;

import recursos.iternacionalizacao.Textos;

/**
 *
 * @author Wesllen Sousa Lima
 */
public class DialogGeral extends javax.swing.JDialog {
    
    public final static Integer TELA_ESPERAR = 1;
    public final static Integer TELA_LISTA = 2;
    public final static Integer TELA_INFO_SERVIDOR = 3;
    
    private Object telaAberta;

    public DialogGeral(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(405, 505);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setOpacity(0.7f);

        jPanel1.setOpaque(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
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
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    public void abrirTela(final Integer tipo, final Integer tela) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                if(tipo == DialogGeral.TELA_ESPERAR) {
                    EsperarCirculo dialog = new EsperarCirculo(null, true, DialogGeral.this, Textos.dialog_conectando());
                    telaAberta = dialog;
                    dialog.setVisible(true);
                } else if(tipo == DialogGeral.TELA_LISTA) {
                    DialogLista dialog = new DialogLista(null, true, DialogGeral.this, 200, 210);
                    telaAberta = dialog;
                    dialog.lista(tela);
                    dialog.setVisible(true); 
                } else if(tipo == DialogGeral.TELA_INFO_SERVIDOR) {
                    DialogCriarEntrar dialog = new DialogCriarEntrar(null, true, DialogGeral.this);
                    telaAberta = dialog;
                    dialog.setVisible(true);
                }
            }
        });
        thread.start();
    }

    public void fechar(Boolean externo) {
        if(externo) {
            if(telaAberta instanceof EsperarCirculo) {
                EsperarCirculo dialog = (EsperarCirculo) telaAberta;
                dialog.fechar();
            } else if(telaAberta instanceof DialogLista) {
                DialogLista dialog = (DialogLista) telaAberta;
                dialog.fechar();
            } else if(telaAberta instanceof DialogCriarEntrar) {
                DialogCriarEntrar dialog = (DialogCriarEntrar) telaAberta;
                dialog.fechar();
            }
        }
        dispose();
    }
    
}
