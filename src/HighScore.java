import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class HighScore {
    JTextArea taHighScore;
    ArrayList <CharacterDetails> sortMeKudasai = new ArrayList<>();
    private String highScoreFile = "src/HighScores";

    public HighScore(JTextArea taHighScore) {
        this.taHighScore = taHighScore;
    }
    public void showByName(){
        sortMeKudasai = create();
        sortMeKudasai.sort(new SortByName());
        for(CharacterDetails cd: sortMeKudasai){
            taHighScore.append(cd.toString()+"\n");
        }
//        taHighScore.append("dddddddddAAAAAAAAAA\n");

    }
    public void showByDamage(){
        sortMeKudasai = create();
        sortMeKudasai.sort(new SortByDamage());
        for(CharacterDetails cd: sortMeKudasai){
            taHighScore.append(cd.toString()+"\n");
        }
//        taHighScore.append("cccccccccccAAAAAAAAAA\n");

    }
    public void showByEnemiesKilled(){
        sortMeKudasai = create();
        sortMeKudasai.sort(new SortByEnemiesKilled());
        for(CharacterDetails cd: sortMeKudasai){
            taHighScore.append(cd.toString()+"\n");
        }
//        taHighScore.append("BBBBBBBBBAAAAAAAA\n");

    }
    public void showByBossesKilled(){
        sortMeKudasai = create();
        sortMeKudasai.sort(new SortByEnemiesKilled());
        for(CharacterDetails cd: sortMeKudasai){
            taHighScore.append(cd.toString()+"\n");
        }
//        taHighScore.append("AAAAAAAAAAAAAAAAA\n");


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
//                System.out.println(amp);
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
    public static class SortByName implements Comparator<CharacterDetails>{

        @Override
        public int compare(CharacterDetails o1, CharacterDetails o2) {
            if(o1.name == o2.name){
                return Integer.compare(o2.dmg_dealt, o1.dmg_dealt);
            }
            return o1.name.compareTo(o2.name);
        }
    }
    public static class SortByEnemiesKilled implements Comparator<CharacterDetails>{
        @Override
        public int compare(CharacterDetails o1, CharacterDetails o2) {
            return Integer.compare(o2.enemies_killed, o1.enemies_killed);
        }
    }
    public static class SortByBossesKilled implements Comparator<CharacterDetails>{
        @Override
        public int compare(CharacterDetails o1, CharacterDetails o2) {
            return Integer.compare(o2.bosses_killed,o1.bosses_killed);
        }
    }
}
