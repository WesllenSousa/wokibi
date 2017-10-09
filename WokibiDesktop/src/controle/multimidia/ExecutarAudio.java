package controle.multimidia;

import controle.arquivos.Caminhos;
import controle.utilitarios.Preferencias;
import java.io.File;

public class ExecutarAudio {

    public void servidorConectado() {
        Preferencias p = new Preferencias();
        if(p.getAudio()) {
            Audio audio = new Audio(Boolean.TRUE);
            audio.executar(new File(Caminhos.audio_servidorConectado()));
        }
    }

    public void servidorDesconectado() {
        Preferencias p = new Preferencias();
        if(p.getAudio()) {
            Audio audio = new Audio(Boolean.TRUE);
            audio.executar(new File(Caminhos.audio_servidorDesconectado()));
        }
    }

    public void clienteConectado() {
        Preferencias p = new Preferencias();
        if(p.getAudio()) {
            Audio audio = new Audio(Boolean.TRUE);
            audio.executar(new File(Caminhos.audio_clienteConectado()));
        }
    }

    public void clienteDesconectado() {
        Preferencias p = new Preferencias();
        if(p.getAudio()) {
            Audio audio = new Audio(Boolean.TRUE);
            audio.executar(new File(Caminhos.audio_clienteDesconectado()));
        }
    }

    public void mensagem() {
        Preferencias p = new Preferencias();
        if(p.getAudio()) {
            Audio audio = new Audio(Boolean.TRUE);
            audio.executar(new File(Caminhos.audio_mensagem()));
        }
    }
    
}
