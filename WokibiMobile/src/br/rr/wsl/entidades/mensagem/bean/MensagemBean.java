package br.rr.wsl.entidades.mensagem.bean;

import java.util.Calendar;

import br.rr.wsl.entidades.contato.bean.ContatoBean;
import br.rr.wsl.entidades.conversa.bean.ConversaBean;

public class MensagemBean {
	
	private Integer codigo;
	private ConversaBean conversa;
	private ContatoBean contato;
	private Calendar dataHora;
	private String texto;
	private Integer deletar;
	private Integer ocultar;
	
	public Integer getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	public ConversaBean getConversa() {
		return conversa;
	}
	
	public void setConversa(ConversaBean conversa) {
		this.conversa = conversa;
	}
	
	public ContatoBean getContato() {
		return contato;
	}
	
	public void setContato(ContatoBean contato) {
		this.contato = contato;
	}
	
	public Calendar getDataHora() {
		return dataHora;
	}
	
	public void setDataHora(Calendar dataHora) {
		this.dataHora = dataHora;
	}
	
	public String getTexto() {
		return texto;
	}
	
	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	public Integer getDeletar() {
		return deletar;
	}
	
	public void setDeletar(Integer deletar) {
		this.deletar = deletar;
	}
	
	public Integer getOcultar() {
		return ocultar;
	}
	
	public void setOcultar(Integer ocultar) {
		this.ocultar = ocultar;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((contato == null) ? 0 : contato.hashCode());
		result = prime * result + ((conversa == null) ? 0 : conversa.hashCode());
		result = prime * result + ((dataHora == null) ? 0 : dataHora.hashCode());
		result = prime * result + ((deletar == null) ? 0 : deletar.hashCode());
		result = prime * result + ((texto == null) ? 0 : texto.hashCode());
		result = prime * result + ((ocultar == null) ? 0 : ocultar.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MensagemBean other = (MensagemBean) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (contato == null) {
			if (other.contato != null)
				return false;
		} else if (!contato.equals(other.contato))
			return false;
		if (conversa == null) {
			if (other.conversa != null)
				return false;
		} else if (!conversa.equals(other.conversa))
			return false;
		if (dataHora == null) {
			if (other.dataHora != null)
				return false;
		} else if (!dataHora.equals(other.dataHora))
			return false;
		if (deletar == null) {
			if (other.deletar != null)
				return false;
		} else if (!deletar.equals(other.deletar))
			return false;
		if (texto == null) {
			if (other.texto != null)
				return false;
		} else if (!texto.equals(other.texto))
			return false;
		if (ocultar == null) {
			if (other.ocultar != null)
				return false;
		} else if (!ocultar.equals(other.ocultar))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return getTexto();
	}	

}
