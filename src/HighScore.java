import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class HighScore {
    JTextArea taHighScore;
    ArrayList <CharacterDetails> sortMeKudasai = new ArrayList<>();
    /*huhuhuhu tagda ko ninyu pls sige rman mog seen atleast like or reply wid "ok" para makaybaw ko nakakita mos ako message huhuhu
      TAGDA KO NINYOOOOOOO HAHAHAHAAHHA
      NABUHAT NA NAKO ANG STICKING YOUR GYAT FOR THE RIZZLER UR SO SKIBIDI YOURE SO FANUM TAX I WANNA BE YOUR SIGMA
      GIMME YOUR OHIO AYM EMU OTORI EMU MEANING SMAAAAIRRUUUU

     https://www.youtube.com/watch?v=BbeeuzU5Qc8 👈 open na ninyo tutorial ppara saon if naglisod mo or chatta lang ko

      gibuhat na nako ang sort by damage dealt using comparator pagtry daw mog sabot kinsa ang nahan muimplement sa uban
      nga comparators and sorting sayon rana icall rana ang .sort function */
    private String highScoreFile = "src/HighScores";

    public HighScore(JTextArea taHighScore) {
        this.taHighScore = taHighScore;
    }
    public void showByName(){
        sortMeKudasai = create();
        //PAREHAA RAS SHOWBYDAMAGE

    }
    public void showByDamage(){
        sortMeKudasai = create();
        sortMeKudasai.sort(new SortByDamage());
        for(CharacterDetails cd: sortMeKudasai){
            taHighScore.append(cd.toString()+"\n");
        }
    }
    public void showByEnemiesKilled(){
        create();
        //PAREHAA RAS SHOWBYDAMAGE
    }
    public void showByBossesKilled(){
        create();
        //PAREHAA RAS SHOWBYDAMAGE

    }


    private ArrayList<CharacterDetails> create(){
        ArrayList<CharacterDetails> details = new ArrayList<>();
        BufferedReader br = null;
        int count;
        String line;
        String name = null;
        int level = 0;
        int dmg_dealt = 0;
        int enemies_killed = 0;
        int boss_killed = 0;
        try {
            br = new BufferedReader(new FileReader(highScoreFile));
            while((line = br.readLine()) != null){
                String[] row = line.split(",");
                count = 0;
                for(String index : row){
                    switch(count){
                        case 0:
                            name = index;
                            break;
                        case 1:
                            level = Integer.parseInt(index);
                            break;
                        case 2:
                            dmg_dealt = Integer.parseInt(index);
                            break;
                        case 3:
                            enemies_killed = Integer.parseInt(index);
                            break;
                        case 4:
                            boss_killed = Integer.parseInt(index);
                            break;
                    }
                    count++;
                }

                CharacterDetails amp = new CharacterDetails(name,level,dmg_dealt,enemies_killed,boss_killed);
                details.add(amp);
                System.out.println(amp);
            }

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } finally{
            try {
                br.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        return details;
    }
    public static class SortByDamage implements Comparator<CharacterDetails> {

        @Override
        public int compare(CharacterDetails o1, CharacterDetails o2) {
            return Integer.compare(o2.dmg_dealt,o1.dmg_dealt);
        }
    }
    //HIMO MOG COMPARATOR PARA SA NAME, ENEMIES KILLED, BOSS KILLED, SA NAME COMPARATOR IF SAME SILA KAY ISORT BY DAMAGE
    //THANK YOU KAAYU GOMEN KATUGON NA KAAYU KO AAAA HAHAHHAHA
}
