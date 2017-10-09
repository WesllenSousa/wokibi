
package telas.dependencias;

import controle.notificacao.Notificacao;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Wesllen Sousa Lima
 */
public class NotificacaoTableModel extends AbstractTableModel {

    private ArrayList linhas = null;
    private String[] colunas = {"Nome", "Mensagens"};

    public NotificacaoTableModel(ArrayList<Notificacao> dados) {
        setLinhas(dados);
    }
    
    public void addLinha(Object dado) {
        linhas.add(dado);
    }

    public void removeLinha(Object dado) {
        linhas.remove(dado);
    }

    public void atualizaLinha(int index, Object dado) {
        linhas.set(index, dado);
    }

    public void limpaDados() {
        linhas.clear();
    }

    public String[] getColunas() {
        return colunas;
    }

    public ArrayList<Notificacao> getLinhas() {
        return linhas;
    }

    public void setColunas(String[] strings) {
        colunas = strings;
    }

    private void setLinhas(ArrayList<Notificacao> list) {
        linhas = list;
    }

    //Retorna o numero de colunas no modelo
    //@see javax.swing.table.TableModel#getColumnCount()
    @Override
    public int getColumnCount() {
        return getColunas().length;
    }

    //Retorna o numero de linhas existentes no modelo
    //@see javax.swing.table.TableModel#getRowCount()
    @Override
    public int getRowCount() {
        return getLinhas().size();
    }

    @Override
    public String getColumnName(int columnIndex) {
        return colunas[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Notificacao bean = (Notificacao) linhas.get(rowIndex);
        if (columnIndex != -1) {
            switch (columnIndex) {
                case 0:
                    return bean.getNome();
                case 1:
                    return bean.getMensagem();
            }
        } else if (columnIndex == -1) {
            return bean;
        }
        return null;
    }
    
}
