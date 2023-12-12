import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

interface LevelUp{
    void gain_exp(int exp_amount);
    void level_up();
    void improve_stats();
}
interface Enemy_LevelUp{
    void level_up(Job job);
    void improve_stats();
    int give_exp();
}


interface MusicPlayer {

    static Clip startMusic(String filePath) {
        Clip clip = null;
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);

            // Create a new Clip
            clip = AudioSystem.getClip();

            // Open the Clip with the AudioInputStream
            clip.open(audioInputStream);

            // Add a listener to restart the music when it ends
            clip.loop(Clip.LOOP_CONTINUOUSLY);

            // Start playing the audio
            clip.start();


        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
        return clip;

    }

    static void stopMusic(Clip clip) {
        if (clip.isRunning()) {
            clip.stop();
            clip.close();
        }
    }




}
