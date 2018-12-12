package Game;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.IOException;

public class SoundReproducer
{
    private String filePath;
    public SoundReproducer(String filePath)
    {
        this.filePath=filePath;

    }

    public void playSound() {
        if (GameResourcesKeeper.isSoundTurnedOn == true) {
            AudioInputStream audioInputStream = null;
            try {
                audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                if (audioInputStream != null) {
                    try {
                        audioInputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }



}
