import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

interface LevelUp{

    /**KONICHIWANDAHOOY!!
     *We separated the implementation for the level up of the enemies and jobs. Job's gain_exp is based on
     * how much is the exp amount which  calls level up and improve the job's class
     * @param exp_amount
     */
    void gain_exp(int exp_amount);
    void level_up();
    void improve_stats();
}
/**
 * Enemy level up is based on the hero's level, either -1, +0, +1 level of the Job's level level level level
 * squared level level, improves stats too depending on level of le enemy
 *
 */
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

    static void startEffect(String filePath) {
        Clip clip = null;
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);

            // Create a new Clip
            clip = AudioSystem.getClip();

            // Open the Clip with the AudioInputStream
            clip.open(audioInputStream);

            // Start playing the audio
            clip.start();


        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }

    }

//    static void stopMusic(Clip clip) {
//        if (clip.isRunning()) {
//            clip.stop();
//            clip.close();
//        }
//    }




}
