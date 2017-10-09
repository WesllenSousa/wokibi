
package telas.dependencias;

import controle.notificacao.Notificacao;
import controle.utilitarios.Instancias;
import java.util.ArrayList;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import recursos.iternacionalizacao.Textos;
import telas.TelaMensagens;

/**
 *
 * @author Wesllen Sousa Lima
 */
public class DialogNotificacao extends javax.swing.JDialog {

    public DialogNotificacao(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setTitle(Textos.dialog_escolha());
        setSize(200, 210);  
        
        carregarTabela();
    }
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pn_geral = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_notificacao = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Área de notificações");
        setResizable(false);
        setType(java.awt.Window.Type.POPUP);

        pn_geral.setBackground(new java.awt.Color(255, 255, 255));
        pn_geral.setOpaque(false);

        tb_notificacao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tb_notificacao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_notificacaoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_notificacao);

        javax.swing.GroupLayout pn_geralLayout = new javax.swing.GroupLayout(pn_geral);
        pn_geral.setLayout(pn_geralLayout);
        pn_geralLayout.setHorizontalGroup(
            pn_geralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
        );
        pn_geralLayout.setVerticalGroup(
            pn_geralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_geral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_geral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-216)/2, (screenSize.height-296)/2, 216, 296);
    }// </editor-fold>//GEN-END:initComponents

    private void tb_notificacaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_notificacaoMouseClicked
        Integer cont = evt.getClickCount();
        if(cont == 2) {
            exibirMensagem();
        }
    }//GEN-LAST:event_tb_notificacaoMouseClicked
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JPanel pn_geral;
    private javax.swing.JTable tb_notificacao;
    // End of variables declaration//GEN-END:variables
 
    private void carregarTabela() {
        popularTabela(Instancias.getNotificacoes());
    }
    
    private void popularTabela(ArrayList<Notificacao> lista) {
        NotificacaoTableModel modelo = new NotificacaoTableModel(lista);
        tb_notificacao.setModel(modelo);
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modelo);
        tb_notificacao.setRowSorter(sorter);
        definirTamanhoColunas();
        definirAlinhamentoColunas();
    }
    
    private void definirTamanhoColunas() {
        tb_notificacao.getColumnModel().getColumn(0).setPreferredWidth(70);
        tb_notificacao.getColumnModel().getColumn(1).setPreferredWidth(200);
    }

    private void definirAlinhamentoColunas() {
        DefaultTableCellRenderer rendererCentro = new DefaultTableCellRenderer();
        rendererCentro.setHorizontalAlignment(SwingConstants.CENTER);
        DefaultTableCellRenderer rendererDireita = new DefaultTableCellRenderer();
        rendererDireita.setHorizontalAlignment(SwingConstants.RIGHT);
        DefaultTableCellRenderer rendererEsquerda = new DefaultTableCellRenderer();
        rendererEsquerda.setHorizontalAlignment(SwingConstants.LEFT);

        tb_notificacao.getColumnModel().getColumn(0).setCellRenderer(rendererEsquerda);
        tb_notificacao.getColumnModel().getColumn(1).setCellRenderer(rendererEsquerda);
    }
    
    private Notificacao obtemItemSelecionado() {
        return (Notificacao) tb_notificacao.getValueAt(tb_notificacao.getSelectedRow(), -1);
    }
    
    private ArrayList<Notificacao> obtemListaItens() {
        return ((NotificacaoTableModel) tb_notificacao.getModel()).getLinhas();
    }
    
    private void removerTabelaItens() {
        ((NotificacaoTableModel) tb_notificacao.getModel()).removeLinha(obtemItemSelecionado());
        tb_notificacao.clearSelection();
        tb_notificacao.updateUI();
    }

    private void exibirMensagem() {
        Notificacao notificacao = obtemItemSelecionado();
        seguirParaTelaMensagens(notificacao.getCodigoConversa());
        removerTabelaItens();
        if (obtemListaItens().isEmpty()) {
            Instancias.getTelaPrincipal().lb_notificacao.setVisible(false);
        }
        dispose();
    }
    
    private void seguirParaTelaMensagens(final Integer codigo) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                TelaMensagens dialog = new TelaMensagens(null, true, codigo);
                dialog.setVisible(true);
            }
        }).start();
    }
    
}
