package entidades.conversa.bean;

import java.util.Calendar;

public class ConversaBean {

    private Integer codigo;
    private Calendar dataHora;
    private String descricao;
    private Integer deletar;
    private Integer status;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Calendar getDataHora() {
        return dataHora;
    }

    public void setDataHora(Calendar dataHora) {
        this.dataHora = dataHora;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getDeletar() {
        return deletar;
    }

    public void setDeletar(Integer deletar) {
        this.deletar = deletar;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((codigo == null) ? 0 : codigo.hashCode());
        result = prime * result
                + ((dataHora == null) ? 0 : dataHora.hashCode());
        result = prime * result
                + ((deletar == null) ? 0 : deletar.hashCode());
        result = prime * result
                + ((descricao == null) ? 0 : descricao.hashCode());
        result = prime * result
                + ((status == null) ? 0 : status.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ConversaBean other = (ConversaBean) obj;
        if (codigo == null) {
            if (other.codigo != null) {
                return false;
            }
        } else if (!codigo.equals(other.codigo)) {
            return false;
        }
        if (dataHora == null) {
            if (other.dataHora != null) {
                return false;
            }
        } else if (!dataHora.equals(other.dataHora)) {
            return false;
        }
        if (deletar == null) {
            if (other.deletar != null) {
                return false;
            }
        } else if (!deletar.equals(other.deletar)) {
            return false;
        }
        if (descricao == null) {
            if (other.descricao != null) {
                return false;
            }
        } else if (!descricao.equals(other.descricao)) {
            return false;
        }
        if (status == null) {
            if (other.status != null) {
                return false;
            }
        } else if (!status.equals(other.status)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getDescricao();
    }
    
}
