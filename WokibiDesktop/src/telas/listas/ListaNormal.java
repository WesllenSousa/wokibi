
package telas.listas;

import controle.utilitarios.ConstantesDiversas;
import controle.utilitarios.Instancias;
import java.awt.Color;
import java.awt.event.MouseEvent;
import recursos.Cores;
import telas.*;
import telas.dependencias.DialogGeral;
import telas.dependencias.DialogLista;

/**
 *
 * @author Wesllen Sousa Lima
 */
public class ListaNormal extends javax.swing.JPanel {
    
    private Object tela;
    private Integer identificacao;

    public ListaNormal() {
        initComponents();
        setSize(400, 60); 
    }
    
    public ListaNormal(Integer altura, Integer y, Integer identificacao, Object tela) {
        initComponents();
        setSize(390, altura); 
        setLocation(0, y);
        
        this.identificacao = identificacao;
        this.tela = tela;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb_valor = new javax.swing.JLabel();

        setOpaque(false);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                formMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });

        lb_valor.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lb_valor.setText("Wesllen");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb_valor, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb_valor, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
        setOpaque(true);
        setBackground(Cores.azulClaro());
    }//GEN-LAST:event_formMouseEntered

    private void formMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseExited
        setBackground(Cores.transparente());
        setOpaque(false);
    }//GEN-LAST:event_formMouseExited

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        setOpaque(true);
        setBackground(Cores.azul());
    }//GEN-LAST:event_formMousePressed

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        setBackground(Cores.transparente());
        setOpaque(false);
    }//GEN-LAST:event_formMouseReleased

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        int cont = evt.getButton();
        if(cont == MouseEvent.BUTTON1) {
            if(tela != null) {
                tratarEventosBotaoEsquerdo();
            }
        } else {
            if(tela != null) {
                tratarEventosBotaoDireito();
            }
        }
    }//GEN-LAST:event_formMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lb_valor;
    // End of variables declaration//GEN-END:variables

    public void setValor(String valor) {
        lb_valor.setText(valor);
    }
    
    private void tratarEventosBotaoEsquerdo() {
        if(tela instanceof TelaConfiguracoes) {
            configuracoes();
        } else if(tela instanceof DialogLista) {
            DialogLista dialogLista = (DialogLista) tela;
            if(dialogLista.getTipo() == ConstantesDiversas.TL_LISTA_CONVERSA_ATIVA) {
                conversasAtivas();
            } else if(dialogLista.getTipo() == ConstantesDiversas.TL_LISTA_CONVERSA_INATIVA) {
                conversasInativas();
            } else if(dialogLista.getTipo() == ConstantesDiversas.TL_LISTA_CONTATOS) {
                listaContatos();
            } else if(dialogLista.getTipo() == ConstantesDiversas.TL_LISTA_MENSAGENS) {
                mensagens();
            } else if(dialogLista.getTipo() == ConstantesDiversas.TL_LISTA_CONTATOS_CONECTADOS) {
                contatosConectados();
            } else if(dialogLista.getTipo() == ConstantesDiversas.TL_LISTA_CLIENTES_CONECTADOS) {
                clientesConectados();
            }
            dialogLista.fechar();
        } else if(tela instanceof TelaCriarServidor) {
        } else if(tela instanceof TelaListarContatos) {
            Instancias.setEntidadeSelecionada(identificacao);
            TelaListarContatos t = (TelaListarContatos) tela;
            t.seguirParaTelaEditar();
        } else if(tela instanceof TelaNovaConversaServidor) {
        } else if(tela instanceof TelaClientesConectados) {
            TelaClientesConectados t = (TelaClientesConectados) tela;
            t.setClienteSelecionado(lb_valor.getText());
        }
    }
    
    private void tratarEventosBotaoDireito() {
        if(tela instanceof TelaListarContatos) {
            Instancias.setEntidadeSelecionada(identificacao);
            Instancias.setTelaSelecionada(tela); 
            DialogGeral dialog = new DialogGeral(null, true);
            dialog.abrirTela(DialogGeral.TELA_LISTA, ConstantesDiversas.TL_LISTA_CONTATOS);
            dialog.setVisible(true);
        } else if(tela instanceof TelaClientesConectados) {
            TelaClientesConectados t = (TelaClientesConectados) tela;
            t.setClienteSelecionado(lb_valor.getText());
            Instancias.setTelaSelecionada(tela); 
            DialogGeral dialog = new DialogGeral(null, true);
            dialog.abrirTela(DialogGeral.TELA_LISTA, ConstantesDiversas.TL_LISTA_CLIENTES_CONECTADOS);
            dialog.setVisible(true);
        }
    }
    
    private void configuracoes() {
        switch (identificacao) {
            case 1:
                TelaCriarServidor dialog1 = new TelaCriarServidor(null, true);
                dialog1.setVisible(true);
                break;
            case 2:
                break;
            case 3:
                TelaCriarSenha dialog3 = new TelaCriarSenha(null, true);
                dialog3.setVisible(true);
                break;
            case 4:
                TelaOpcoes dialog4 = new TelaOpcoes(null, true);
                dialog4.setVisible(true);
                break;
            case 5:
                TelaAjuda dialog5 = new TelaAjuda(null, true);
                dialog5.setVisible(true);
                break;
            case 6:
                TelaSobre dialog6 = new TelaSobre(null, true);
                dialog6.setVisible(true);
                break;
        }
    }
    
    private void conversasAtivas() {
        switch (identificacao) {
            case 1:
                TelaMensagens dialog1 = new TelaMensagens(null, true, Instancias.getEntidadeSelecionada());
                dialog1.setVisible(true);
                break;
            case 2:
                TelaConversa t = (TelaConversa) Instancias.getTelaSelecionada();
                t.desconectar(Instancias.getEntidadeSelecionada());
                break;
            case 3:
                TelaConversa t2 = (TelaConversa) Instancias.getTelaSelecionada();
                t2.perguntarExclusao(Instancias.getEntidadeSelecionada());
                break;
        }
    }
    
    private void conversasInativas() {
        switch (identificacao) {
            case 1:
                TelaMensagens dialog1 = new TelaMensagens(null, true, Instancias.getEntidadeSelecionada());
                dialog1.setVisible(true);
                break;
            case 2:
                TelaConversa t = (TelaConversa) Instancias.getTelaSelecionada();
                t.conectar(Instancias.getEntidadeSelecionada());
                break;
            case 3:
                TelaConversa t2 = (TelaConversa) Instancias.getTelaSelecionada();
                t2.perguntarExclusao(Instancias.getEntidadeSelecionada());
                break;
        }
    }
    
    private void listaContatos() {
        TelaListarContatos t = (TelaListarContatos) Instancias.getTelaSelecionada();
        switch (identificacao) {
            case 1:
                t.seguirParaTelaEditar();
                break;
            case 2:
                t.perguntarExlusao();
                break;
        }
    }
    
    private void mensagens() {
        TelaMensagens t = (TelaMensagens) Instancias.getTelaSelecionada();
        switch (identificacao) {
            case 1:
                t.ocultar();
                break;
            case 2:
                t.deletar();
                break;
        }
    }

    private void contatosConectados() {
        TelaContatosConectados t = (TelaContatosConectados) Instancias.getTelaSelecionada();
        switch (identificacao) {
            case 1:
                t.seguirParaTelaAssociarConversa();
                break;
            case 2:
                t.desconectar();
                break;
        }
    }

    private void clientesConectados() {
        TelaClientesConectados t = (TelaClientesConectados) Instancias.getTelaSelecionada();
        switch (identificacao) {
            case 1:
                t.iniciarConversa();
                break;
            case 2:
                t.seguirParaTelaAssociarConversa();
                break;
        }
    }
    
}
