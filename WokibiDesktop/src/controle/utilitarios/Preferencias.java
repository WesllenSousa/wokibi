package controle.utilitarios;

import controle.arquivos.Caminhos;
import controle.arquivos.ManipularArquivoProperties;
import java.io.File;
import java.util.Properties;

public class Preferencias {

    public String getSenhaAnterior() {
        Properties props = ManipularArquivoProperties.lerConfiguracao(new File(Caminhos.config_senhaAnterior()));
        String senhaAnterior="";
        if(props != null) {
            senhaAnterior = props.getProperty("senhaAnterior");
        }
        return senhaAnterior;
    }

    public Boolean getServicoServidor() {
        Properties props = ManipularArquivoProperties.lerConfiguracao(new File(Caminhos.config_servicoServidor()));
        if(props != null) {
            if(props.getProperty("ativo").equals("true")) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    public Boolean getServicoSms() {
        Properties props = ManipularArquivoProperties.lerConfiguracao(new File(Caminhos.config_servicoMensagem()));
        if(props != null) {
            if(props.getProperty("ativo").equals("true")) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }
    
    public Boolean getAudio() {
        Properties props = ManipularArquivoProperties.lerConfiguracao(new File(Caminhos.config_audio()));
        if(props != null) {
            if(props.getProperty("ativo").equals("true")) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }
    
    public String getModem() {
        Properties props = ManipularArquivoProperties.lerConfiguracao(new File(Caminhos.config_modem()));
        if(props != null) {
            return props.getProperty("porta");
        } 
        return null;
    }
    
}
