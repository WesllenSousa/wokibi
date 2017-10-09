package br.rr.wsl.controle.contatos;

import java.util.List;

public class Contato {
	
	private String ID;
    private String Nome;
    private String Email;
    private List<Telefone> Telefones;  
    
    public String getID() {
          return ID;
    }
    
    public void setID(String string) {
          ID = string;
    }
    
    public String getNome() {
          return Nome;
    }
    
    public void setNome(String nome) {
          Nome = nome;
    }     
    
    public List<Telefone> getTelefones() {
          return Telefones;
    }
    
    public void setTelefones(List<Telefone> telefones) {
          Telefones = telefones;
    }
    
    public String getEmail() {
          return Email;
    }
    
    public void setEmail(String email) {
          Email = email;
    }    
    
    @Override
    public String toString() {
          // TODO Auto-generated method stub          
          return Nome + "-"  + Telefones.get(0);
    } 

}
