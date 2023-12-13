import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SaveLoad {
    private Job chosen;
    private Enemy random_enemy;
    private Clip main_Sound;
    private Clip battle_Sound;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public SaveLoad(Job chosen, Enemy random_enemy, Clip main_Sound, Clip battle_Sound, CardLayout cardLayout, JPanel mainPanel) {
        this.chosen = chosen;
        this.random_enemy = random_enemy;
        this.main_Sound = main_Sound;
        this.battle_Sound = battle_Sound;
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
    }

    public void save() {
        System.out.println("hi");
        int i = JOptionPane.showConfirmDialog(null, "Would you like to save your character's progress?");
        try {
            if (JOptionPane.YES_OPTION == i) {
                //Close battle music
                if (battle_Sound != null) {
                    battle_Sound.close();
                    battle_Sound = null;
                    main_Sound = MusicPlayer.startMusic("src\\sounds\\main.wav");
                }

                String filePath = "src/gameProgress.txt";
                List<String> Stats = readBetween(filePath, '!');
                if (chosen != null && Stats.isEmpty()) {
                    changeContent(filePath, '!');
                } else {
                    int j = JOptionPane.showConfirmDialog(null, "Slot has a saved progress\nWould you like to overwrite the previously saved game?");
                    if (j == JOptionPane.YES_OPTION) {
                        changeContent(filePath, '!');
                    } else if (j == JOptionPane.NO_OPTION) {
                        cardLayout.show(mainPanel, "SelectPanel");
                    }
                }
            } else {
                if (battle_Sound != null) {
                    battle_Sound.close();
                    battle_Sound = null;
                    main_Sound = MusicPlayer.startMusic("src\\sounds\\main.wav");
                }
                cardLayout.show(mainPanel, "SelectPanel");
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Job load() {
        String Char = "src/gameProgress.txt";
        List<String> Stats;
        Random ran;
        int i = JOptionPane.showConfirmDialog(null, "Would you like to load your previous game?");
        try {
            if (i == JOptionPane.YES_OPTION) {
                Stats = readBetween(Char, '!');
                if (!(Stats.isEmpty())) {
                    int j = JOptionPane.showConfirmDialog(null, "Stats: \n Type: " + Stats.get(0) +
                            "\n Lvl: " + Stats.get(1) +
                            "\n Dmg: " + Stats.get(2) +
                            "\n Hp: " + Stats.get(3) +
                            "\n Maxhp: " + Stats.get(4) +
                            "\n Exp: " + Stats.get(5) +
                            "\n Exp Pts: " + Stats.get(6));
                    if (j == 0) {
                        ran = new Random();
                        int val = ran.nextInt(4);
                        random_enemy = generateEnemy(val);

                        chosen = loadJob(Stats);
                    }
                } else {
                    int j = JOptionPane.showConfirmDialog(null, "No game found\nWould you like to start a new game?");
                    if (j == JOptionPane.YES_OPTION) {
                        cardLayout.show(mainPanel, "SelectPanel");
                    }
                }
            }else{
                JOptionPane.showMessageDialog(null,"Pagshor dira");
            }
        } catch (Exception io) {
            throw new RuntimeException(io);
        }
        if(chosen == null){
            throw new NullPointerException("ang makabasa gwapo gomen katugon na kayko");
        }
        removeData(Char);
        return chosen;
    }

    /**
     * This method will remove the data inside the file if the loading is successful char, sauce is the source of the file
     * @param sauce
     */
    private void removeData(String sauce){
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(sauce));
            bw.write("Saved Game\n!\n!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally{
            try {
                bw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void changeContent(String filePath, char sym) throws IOException {
        List<String> saved = new ArrayList<>();
        saved.add(chosen.getName());
        saved.add(String.valueOf(chosen.getLevel()));
        saved.add(String.valueOf(chosen.getDmg()));
        saved.add(String.valueOf(chosen.getHp()));
        saved.add(String.valueOf(chosen.maxHp));
        saved.add(String.valueOf(chosen.getExp()));
        saved.add(String.valueOf(chosen.getExp_points()));
        System.out.println(saved);
        writeStats(filePath, sym, saved);
    }

    private List<String> readBetween(String filePath, char symbol) throws IOException {
        char startSymbol, endSymbol;
        startSymbol = endSymbol = symbol;
        Path path = Paths.get(filePath);
        List<String> Stats = Files.readAllLines(path);
        List<String> between = new ArrayList<>();
        boolean isInside = false;

        for (String s : Stats) {
            if (s.equals(String.valueOf(startSymbol))) {
                isInside = true;
            } else if (s.equals(String.valueOf(endSymbol))) {
                isInside = false;
            } else if (isInside) {
                between.add(s);
            }
        }
        return between;
    }

    private void writeStats(String path, char sym, List<String> content) throws IOException {
        List<String> fileContent = Files.readAllLines(Paths.get(path));
        List<String> newFileContent = new ArrayList<>();

        char startSym = sym;
        boolean isInside = false;
        for (String line : fileContent) {
            if (line.equals(String.valueOf(startSym))) {
                isInside = !isInside;
                newFileContent.add(line);
                if (isInside) {
                    newFileContent.addAll(content);
                }
            } else if (!isInside) {
                newFileContent.add(line);
            } else if (line.equals(String.valueOf(sym))) {
                newFileContent.add(line);
                isInside = false;
            }
        }

        Files.write(Paths.get(path), newFileContent);
    }

    private Enemy generateEnemy(int val) {

        switch (val) {
            case 0:
                return new Enemy.Scorpion();
            case 1:
                return new Enemy.AncientBishop();
            case 2:
                return new Enemy.DarkStalker();
            case 3:
                return new Enemy.Skeleton();
            default:
                return new Enemy.SuicideRock();
        }
    }

    private Job loadJob(List<String> ally) {
        if (ally.get(0).equals("Priest")) {
            chosen = new Job.Priest("Priest", Integer.parseInt(ally.get(1)),
                    Integer.parseInt(ally.get(2)), Integer.parseInt(ally.get(3)),
                    Integer.parseInt(ally.get(4)), Integer.parseInt(ally.get(6)),
                    Integer.parseInt(ally.get(6)));
        } else if (ally.get(0).equals("Knight")) {
            chosen = new Job.Knight("Knight", Integer.parseInt(ally.get(1)),
                    Integer.parseInt(ally.get(2)), Integer.parseInt(ally.get(3)),
                    Integer.parseInt(ally.get(4)), Integer.parseInt(ally.get(6)),
                    Integer.parseInt(ally.get(6)));
        } else {
            chosen = new Job.Mage("Mage", Integer.parseInt(ally.get(1)),
                    Integer.parseInt(ally.get(2)), Integer.parseInt(ally.get(3)),
                    Integer.parseInt(ally.get(4)), Integer.parseInt(ally.get(6)),
                    Integer.parseInt(ally.get(6)));
        }
        return chosen;
    }
}
