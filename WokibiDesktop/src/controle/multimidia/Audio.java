

package controle.multimidia;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author bliss
 */
public class Audio {

    private Boolean assinado;

    public Audio(Boolean assinado){
        this.assinado = assinado;
    }
    
    private AudioFormat assinar(AudioFormat audioFormat) {
        AudioFormat formato = new AudioFormat(
            AudioFormat.Encoding.PCM_SIGNED,
            audioFormat.getSampleRate(),
            16,
            audioFormat.getChannels(),
            audioFormat.getChannels() * 2,
            audioFormat.getSampleRate(),
            false);
        return formato;
    }
    
    private AudioFormat naoAssinar(AudioFormat audioFormat) {
        AudioFormat formato = new AudioFormat(
            AudioFormat.Encoding.PCM_UNSIGNED,
            audioFormat.getSampleRate(),
            8,
            audioFormat.getChannels(),
            audioFormat.getChannels() * 1,
            audioFormat.getSampleRate(),
            false);
        return formato;
    }

    public void executar(File file) {
        try {
            AudioInputStream in = AudioSystem.getAudioInputStream(file);
            AudioFormat formato;
            if(assinado)
                formato = assinar(in.getFormat());
            else
                formato = naoAssinar(in.getFormat());
            try (AudioInputStream din = AudioSystem.getAudioInputStream(formato, in)) {
                DataLine.Info dataLine = new DataLine.Info(SourceDataLine.class, formato);
                try (SourceDataLine line = (SourceDataLine) AudioSystem.getLine(dataLine)) {
                    if(line != null){
                        line.open(formato);
                        byte[] dados = new byte[4096];
                        line.start();
                        int bytesLidos;
                        while((bytesLidos = din.read(dados, 0, dados.length)) != -1){
                            line.write(dados, 0, bytesLidos);
                        }
                    }
                    line.drain();
                    line.stop();
                }
            }
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException ex) {
        }
    }

}
