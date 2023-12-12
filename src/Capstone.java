import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.nio.file.Path;
import java.util.List;

public class Capstone extends JFrame implements MusicPlayer{
    private JPanel mainPanel;
    private JPanel selectPanel;
    private JPanel battlePanel;
    private JPanel infoPanel;
    private JButton bAttack;
    private JButton bSelect2;
    private JComboBox<Job> cbJobs;
    private JLabel imgLabel;
    private JButton bStart;
    private JButton bInfo;
    private JComboBox cbJobInfo;
    private JTextArea jobsInfo;
    private JButton bSkill;
    private JButton bWaS;
    private JButton bSelect;
    private JTextField tfHPEnemy;
    private JTextField tfHPChara;
    private JLabel lbEName;
    private JLabel lbAName;
    private JLabel lbEPic;
    private JLabel lbAPic;
    private JComboBox cbEnemyInfo;
    private JTextArea enemiesInfo;
    private JPanel highscorePanel;
    private JButton sortByDamageButton;
    private JButton sortByNameButton;
    private JButton sortByBossesKilledButton;
    private JButton sortByEnemiesKilledButton;
    private JTextArea taHighScore;
    private JButton highscoresButton;
    private JButton bNewGame;
    private JButton bLoadGame;
    private JPanel startPanel;
    private JButton bGoBack;
    private JTextArea textArea1;
    private Image image;
    private Image EnemyImage;
    private Enemy random_enemy;
    private Job chosen;

    private JScrollPane jScrollPane;
    private JButton bStartMenu;

    private final String highScoreFile = "src/HighScores";
    private int total_dmg;
    private int enemies_killed;
    private int bosses_killed;

    private Clip battle_Sound = null;
    private Clip main_Sound = null;
    public Capstone(){
        if(main_Sound == null){
            main_Sound = MusicPlayer.startMusic("src\\sounds\\main.wav");
        }

        JFrame frame = new JFrame("Group 8 Capstone");
        frame.setContentPane(this.mainPanel);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(700,800);
        frame.setVisible(true);
        cbJobs.addItem(new Job.Priest());
        cbJobs.addItem(new Job.Knight());
        cbJobs.addItem(new Job.Mage());
        cbJobs.setSelectedIndex(-1);


        mainPanel.add(startPanel, "StartPanel");
        mainPanel.add(selectPanel,"SelectPanel");
        mainPanel.add(battlePanel,"BattlePanel");
        mainPanel.add(infoPanel,"InfoPanel");
        mainPanel.add(highscorePanel,"highScorePanel");

        CardLayout cardLayout = (CardLayout) mainPanel.getLayout();


        bStart.addActionListener(e -> {
//
//            if(battleSeq != null){
//                battleSeq.removeFrame();
//
//            textArea1 = (JTextArea) jScrollPane.getViewport().getView();

            // clear text area inside when a battle starts

            textArea1 .setText("");

            // Added this so when creating a new chosen character will start music and end main
            if(battle_Sound == null){
                main_Sound.close();
                main_Sound = null;
                battle_Sound = MusicPlayer.startMusic("src\\sounds\\battle.wav");
            }


            try {
                cardLayout.show(mainPanel, "BattlePanel");
                chosen = (Job) cbJobs.getSelectedItem();


                if (chosen instanceof Job.Priest) {
                    if (chosen == null || !chosen.isAlive()) {
                        chosen = new Job.Priest();
                    }
                    lbAName.setText(chosen.name + " Lvl " + chosen.level);
                    tfHPChara.setText(chosen.hp + " / " + chosen.maxHp);
                    image = ImageIO.read(new File("src/JobImages/priest.png"));
                    ImageIcon icon = new ImageIcon(image.getScaledInstance(250, 250, Image.SCALE_SMOOTH));
                    lbAPic.setIcon(icon);
                } else if (chosen instanceof Job.Knight) {
                    if (chosen == null || !chosen.isAlive()) {
                        chosen = new Job.Knight();
                    }
                    lbAName.setText(chosen.name + " Lvl " + chosen.level);
                    tfHPChara.setText(chosen.hp + " / " + chosen.maxHp);
                    image = ImageIO.read(new File("src/JobImages/knight.png"));
                    ImageIcon icon = new ImageIcon(image.getScaledInstance(250, 250, Image.SCALE_SMOOTH));
                    lbAPic.setIcon(icon);
                } else if (chosen instanceof Job.Mage) {
                    if (chosen == null || !chosen.isAlive()) {
                        chosen = new Job.Mage();
                    }
                    lbAName.setText(chosen.name + " Lvl " + chosen.level);
                    tfHPChara.setText(chosen.hp + " / " + chosen.maxHp);
                    image = ImageIO.read(new File("src/JobImages/mage.png"));
                    ImageIcon icon = new ImageIcon(image.getScaledInstance(250, 250, Image.SCALE_SMOOTH));
                    lbAPic.setIcon(icon);
                }

                getEnemy();


                //level sa kontra
                System.out.println("This is random enemy " + random_enemy);
                //}

//                Enemy random_enemy = /*find way para makuha ni basta kato random thingy i told u hahaah*/
                //} catch (ClassNotFoundException ex) {
                //   System.out.println(ex.getMessage());
//                    JOptionPane.showMessageDialog(this, "Select a class");
            } catch (Exception io) {
                throw new RuntimeException(io);
            }




        });


        bInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "InfoPanel");

                cbJobInfo.removeAllItems();

                cbJobInfo.addItem("Jobs");
                cbJobInfo.addItem("Priest");
                cbJobInfo.addItem("Knight");
                cbJobInfo.addItem("Mage");

                cbEnemyInfo.removeAllItems();

                cbEnemyInfo.addItem("Enemies");
                cbEnemyInfo.addItem("Scorpion");
                cbEnemyInfo.addItem("Suicide Rock");
                cbEnemyInfo.addItem("Skeleton");
                cbEnemyInfo.addItem("Dark Stalker");
                cbEnemyInfo.addItem("Ancient Bishop");
            }
        });

        bSelect.addActionListener(e->{
           //di mogana lolz
            if (chosen.hp > 0) {
                System.out.println("hi");
                int i = JOptionPane.showConfirmDialog(this, "Would you like to save your character's progress?");
                try {
                    if (JOptionPane.YES_OPTION == i) {
                        //Close battle music
                        if(battle_Sound != null){
                            battle_Sound.close();
                            battle_Sound = null;
                            main_Sound = MusicPlayer.startMusic("src\\sounds\\main.wav");
                        }

                        String filePath = "src/gameProgress.txt";
                        List<String> Stats = readBetween(filePath, '!');
                            if (chosen != null && Stats.get(0).equals("Empty")) {
                                changeContent(filePath,'!');
                            }else{
                                int j = JOptionPane.showConfirmDialog(this, "Slot if full\nWould you like to replace the previously saved game?");
                                if(j==JOptionPane.YES_OPTION){
                                    changeContent(filePath,'!');
                                }else if(j==JOptionPane.NO_OPTION){
                                    cardLayout.show(mainPanel, "SelectPanel");
                                }else{
                                    return;
                                }
                            }
                    }else{
                        cardLayout.show(mainPanel, "SelectPanel");
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
                cardLayout.show(mainPanel, "SelectPanel");
        });

        bSelect2.addActionListener(e->{
                cardLayout.show(mainPanel, "SelectPanel");
        });


        cbJobInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedChar = (String) cbJobInfo.getSelectedItem();

                jobsInfo.setText("");

                String symbol;

                if (selectedChar != null) {
                    String charDetails = "src/CharacterDetails";

                    try {
                        String details = Files.readString(Paths.get(charDetails));

                        symbol = switch (selectedChar) {
                            case "Priest" -> "!";
                            case "Knight" -> "@";
                            case "Mage" -> "#";
                            default -> "";
                        };

                        int startSymbol = details.indexOf(symbol);
                        int endSymbol = details.indexOf(symbol, startSymbol + 1);

                        if (startSymbol != -1 && endSymbol != -1) {
                            jobsInfo.setText(details.substring(startSymbol + symbol.length(), endSymbol));
                        } else {
                            jobsInfo.setText("Not found!");
                        }

                    } catch (IOException io) {
                        throw new RuntimeException(io);
                    }
                }
            }
        });


        // Simplified the actionListener for the items below!
        bWaS.addActionListener(e -> performBattleAction(new BattleBuilder(chosen, random_enemy, /*,battleSeq*/ textArea1).setWaitButton(true).setTfJobHP(tfHPChara).setTfEnemyHP(tfHPEnemy)));
        bAttack.addActionListener(e -> performBattleAction(new BattleBuilder(chosen, random_enemy, /*,battleSeq*/ textArea1).setAttackButton(true).setTfJobHP(tfHPChara).setTfEnemyHP(tfHPEnemy)));
        bSkill.addActionListener(e -> performBattleAction(new BattleBuilder(chosen, random_enemy, textArea1).setTfJobHP(tfHPChara).setTfEnemyHP(tfHPEnemy).setSkillButton(true)));
        cbEnemyInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedChar = (String) cbEnemyInfo.getSelectedItem();

                enemiesInfo.setText("");

                String symbol;

                if (selectedChar != null) {
                    String charDetails = "src/CharacterDetails";

                    try {
                        String details = Files.readString(Paths.get(charDetails));

                        symbol = switch (selectedChar) {
                            case "Scorpion" -> "$";
                            case "Suicide Rock" -> "%";
                            case "Skeleton" -> "^";
                            case "Dark Stalker" -> "&";
                            case "Ancient Bishop" -> "*";
                            default -> "";
                        };

                        int startSymbol = details.indexOf(symbol);
                        int endSymbol = details.indexOf(symbol, startSymbol + 1);

                        if (startSymbol != -1 && endSymbol != -1) {
                            enemiesInfo.setText(details.substring(startSymbol + symbol.length(), endSymbol));
                        } else {
                            enemiesInfo.setText("Not found!");
                        }

                    } catch (IOException io) {
                        throw new RuntimeException(io);
                    }
                }
            }
        });

        cbJobs.addActionListener(e -> {

            System.out.println("work");
            Job chosen = (Job) cbJobs.getSelectedItem();
            System.out.println(chosen);
            if (chosen instanceof Job.Knight) {
                try {
                    image = ImageIO.read(new File("src/JobImages/knight.png"));
                    ImageIcon icon = new ImageIcon(image.getScaledInstance(250, 250, Image.SCALE_SMOOTH));
                    imgLabel.setIcon(icon);
                    System.out.println("WOrk");
                } catch (IOException a) {
                    throw new RuntimeException(a);
                }
            } else if (chosen instanceof Job.Mage) {
                try {
                    image = ImageIO.read(new File("src/JobImages/mage.png"));
                    ImageIcon icon = new ImageIcon(image.getScaledInstance(250, 250, Image.SCALE_SMOOTH));
                    imgLabel.setIcon(icon);
                    System.out.println("WOrk");
                } catch (IOException a) {
                    throw new RuntimeException(a);
                }

            } else if (chosen instanceof Job.Priest) {
                try {
                    image = ImageIO.read(new File("src/JobImages/priest.png"));
                    ImageIcon icon = new ImageIcon(image.getScaledInstance(250, 250, Image.SCALE_SMOOTH));
                    imgLabel.setIcon(icon);
                    System.out.println("WOrk");
                } catch (IOException a) {
                    throw new RuntimeException(a);
                }

            }
            System.out.println("worked");

        });


        highscoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel,"highScorePanel");
            }
        });
        sortByNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taHighScore.setText("");
                HighScore hs = new HighScore(taHighScore);
                hs.showByName();
                taHighScore.append("yamete kudasai");
            }
        });
        sortByDamageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taHighScore.setText("");
                HighScore hs = new HighScore(taHighScore);
                hs.showByDamage();
                taHighScore.append("frieren kawaii");

            }
        });
        sortByEnemiesKilledButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taHighScore.setText("");
                HighScore hs = new HighScore(taHighScore);
                hs.showByEnemiesKilled();
                taHighScore.append("niwork sha uwu");
            }
        });
        sortByBossesKilledButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taHighScore.setText("");
                HighScore hs = new HighScore(taHighScore);
                hs.showByBossesKilled();
                taHighScore.append("kaila mong tokoyami towa");
            }
        });
        bNewGame.addActionListener(e->{
            cardLayout.show(mainPanel, "SelectPanel");
        });


        bLoadGame.addActionListener(e -> {
            String symbol;
            String Char = "src/gameProgress.txt";
            String loadedFile;
            List<String> Stats;
            Battle bat;
            Random ran;
            int i = JOptionPane.showConfirmDialog(this,"Would you like to load your previous game?");
            try {
                if (i==JOptionPane.YES_OPTION) {
                    Stats = readBetween(Char, '!');
                    if (!(Stats.get(0).equals("Empty"))) {
                        int j=JOptionPane.showConfirmDialog(this, "Stats: \n Type: " + Stats.get(0) +
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

                            loadJob(Stats);
                            getEnemy();
                            cardLayout.show(mainPanel, "BattlePanel");

                        } else {
                            return;
                        }
                    }else{
                        int j = JOptionPane.showConfirmDialog(this,"No game found\nWould you like to start a new game?");
                            if(j==JOptionPane.YES_OPTION){
                                cardLayout.show(mainPanel,"SelectPanel");
                            }
                    }
                }
            }catch (Exception io){
                throw new RuntimeException(io);
            }
        });

        bStartMenu.addActionListener(e -> {
            cardLayout.show(mainPanel, "StartPanel");
        });

        bGoBack.addActionListener(e->{
            cardLayout.show(mainPanel, "StartPanel");

        });
    }
    private void getEnemy() {
            try {
                Random random = new Random();
                int val;
                // while(chosen.hp>0) {
                val = random.nextInt(5);
                random_enemy = generateEnemy(val);
                random_enemy.level_up(chosen);
                switch (val) {
                    case 0: {
                        tfHPEnemy.setText(random_enemy.hp + " / " + random_enemy.maxHp);
                        lbEName.setText(random_enemy.name + " Lvl " + random_enemy.level);
                        EnemyImage = ImageIO.read(new File("src/EnemyImages/scorpion.png"));
                        ImageIcon icon2 = new ImageIcon(EnemyImage.getScaledInstance(250, 250, Image.SCALE_SMOOTH));
                        lbEPic.setIcon(icon2);
                        break;
                    }
                    case 1: {
                        tfHPEnemy.setText(random_enemy.hp + " / " + random_enemy.maxHp);
                        lbEName.setText(random_enemy.name + " Lvl " + random_enemy.level);
                        EnemyImage = ImageIO.read(new File("src/EnemyImages/ancientbishop.png"));
                        ImageIcon icon2 = new ImageIcon(EnemyImage.getScaledInstance(250, 250, Image.SCALE_SMOOTH));
                        lbEPic.setIcon(icon2);
                        break;
                    }
                    case 2: {
                        tfHPEnemy.setText(random_enemy.hp + " / " + random_enemy.maxHp);
                        lbEName.setText(random_enemy.name + " Lvl " + random_enemy.level);
                        EnemyImage = ImageIO.read(new File("src/EnemyImages/darkstalker.png"));
                        ImageIcon icon2 = new ImageIcon(EnemyImage.getScaledInstance(250, 250, Image.SCALE_SMOOTH));
                        lbEPic.setIcon(icon2);
                        break;
                    }
                    case 3: {
                        tfHPEnemy.setText(random_enemy.hp + " / " + random_enemy.maxHp);
                        lbEName.setText(random_enemy.name + " Lvl " + random_enemy.level);
                        EnemyImage = ImageIO.read(new File("src/EnemyImages/skeleton.png"));
                        ImageIcon icon2 = new ImageIcon(EnemyImage.getScaledInstance(250, 250, Image.SCALE_SMOOTH));
                        lbEPic.setIcon(icon2);
                        break;
                    }
                    case 4: {
                        tfHPEnemy.setText(random_enemy.hp + " / " + random_enemy.maxHp);
                        lbEName.setText(random_enemy.name + " Lvl " + random_enemy.level);
                        EnemyImage = ImageIO.read(new File("src/EnemyImages/suiciderock.png"));
                        ImageIcon icon2 = new ImageIcon(EnemyImage.getScaledInstance(250, 250, Image.SCALE_SMOOTH));
                        lbEPic.setIcon(icon2);
                        break;
                    }
                }
            } catch (IOException io) {
                throw new RuntimeException(io);
            }
        }

    //I Just consolidate all performBattleAction into one since this is simpler
    private void performBattleAction(BattleBuilder battleBuilder) {
        Battle battle = battleBuilder.build();
        BufferedWriter write_hs = null;
        //if magmake ug antoher buffered writer declare lang diri pareha sa line 398
        try {
            write_hs = new BufferedWriter(new FileWriter(highScoreFile,true));
            //and then declare new diri sa try catch pina line 401
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        try {
            battle.performAction();
            total_dmg += chosen.getTotal_dmg();
        } catch (IllegalArgumentException a) {
            JOptionPane.showMessageDialog(null, a.getMessage());
            //lose diri ra masave sa highscores once mapildi na ayaw ni ninyo iadjust
            try {
                write_hs.append(chosen.name + "," + chosen.level + "," + total_dmg + "," + enemies_killed + "," + bosses_killed);
                write_hs.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } finally {
                try {
                    write_hs.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }

            if(!chosen.isAlive()){
                battle_Sound.close();
                battle_Sound = null;
                main_Sound = MusicPlayer.startMusic("src\\sounds\\main.wav");
            }
            bSelect.doClick();
        } catch (IllegalStateException b) {
            int selected = JOptionPane.showConfirmDialog(null, b.getMessage());
            chosen.gain_exp(10);
            enemies_killed++;
            System.out.println("EK: " + enemies_killed);
            if (random_enemy instanceof Enemy.DarkStalker || random_enemy instanceof Enemy.AncientBishop) {
                bosses_killed++;
                System.out.println("BK: " + bosses_killed);
            }
            if (selected == JOptionPane.YES_OPTION) {

                if(!chosen.isAlive()){
                    battle_Sound.close();
                    battle_Sound = null;
                    main_Sound = MusicPlayer.startMusic("src\\sounds\\main.wav");
                }
                bStart.doClick();
            } else if (selected == JOptionPane.NO_OPTION) {
                //basta kani kay musave sha sa iya progress later ni nato iimplement
                System.out.println("EXIT");
            }
        }
    }



    // creates the enemy specified by the random generator
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


    private void loadJob(List<String> ally) {
        try {
            if (ally.get(0).equals("Priest")) {
                chosen = new Job.Priest("Priest", Integer.parseInt(ally.get(1)),
                        Integer.parseInt(ally.get(2)), Integer.parseInt(ally.get(3)),
                        Integer.parseInt(ally.get(4)), Integer.parseInt(ally.get(6)),
                        Integer.parseInt(ally.get(6)));
                lbAName.setText(chosen.name + " Lvl " + chosen.level);
                tfHPChara.setText(chosen.hp + " / " + chosen.maxHp);
                image = ImageIO.read(new File("src/JobImages/priest.png"));
                ImageIcon icon = new ImageIcon(image.getScaledInstance(250, 250, Image.SCALE_SMOOTH));
                lbAPic.setIcon(icon);
            } else if(ally.get(0).equals("Knight")) {
                chosen = new Job.Knight("Knight", Integer.parseInt(ally.get(1)),
                        Integer.parseInt(ally.get(2)), Integer.parseInt(ally.get(3)),
                        Integer.parseInt(ally.get(4)), Integer.parseInt(ally.get(6)),
                        Integer.parseInt(ally.get(6)));
                lbAName.setText(chosen.name + " Lvl " + chosen.level);
                tfHPChara.setText(chosen.hp + " / " + chosen.maxHp);
                image = ImageIO.read(new File("src/JobImages/knight.png"));
                ImageIcon icon = new ImageIcon(image.getScaledInstance(250, 250, Image.SCALE_SMOOTH));
                lbAPic.setIcon(icon);
            } else {
                chosen = new Job.Mage("Mage", Integer.parseInt(ally.get(1)),
                        Integer.parseInt(ally.get(2)), Integer.parseInt(ally.get(3)),
                        Integer.parseInt(ally.get(4)), Integer.parseInt(ally.get(6)),
                        Integer.parseInt(ally.get(6)));
                lbAName.setText(chosen.name + " Lvl " + chosen.level);
                tfHPChara.setText(chosen.hp + " / " + chosen.maxHp);
                image = ImageIO.read(new File("src/JobImages/mage.png"));
                ImageIcon icon = new ImageIcon(image.getScaledInstance(250, 250, Image.SCALE_SMOOTH));
                lbAPic.setIcon(icon);
            }
        }catch(IOException io) {
            throw new RuntimeException(io);
        }
    }

    private List<String> readBetween(String filePath, char symbol) throws IOException{
        char startSymbol, endSymbol;
        startSymbol = endSymbol = symbol;
        Path path = Paths.get(filePath);
        List<String> Stats = Files.readAllLines(path);
        List<String> between = new ArrayList<>();
        boolean isInside = false;

        for(String s : Stats){
            if(s.equals(String.valueOf(startSymbol))){
                isInside = true;
            } else if(s.equals(String.valueOf(endSymbol))){
                isInside = false;
            } else if(isInside){
                between.add(s);
            }
        }
        return between;
    }

    private String saveGame(String stats, String sym, List<String> replacement){
        int startSym = stats.indexOf(sym);
        int endSym = stats.indexOf(sym, startSym+1);

        if(startSym != -1 && endSym != -1){
            String prefix = stats.substring(0, startSym + sym.length());
            String suffix = stats.substring(endSym);
            return prefix + replacement + suffix;
        }else{
            return stats;
        }
    }
    private void writeStats(String path, char sym, List<String> content) throws IOException{
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
            }else if(line.equals(String.valueOf(sym))){
                newFileContent.add(line);
                isInside = false;
            }
        }

        Files.write(Paths.get(path), newFileContent);
    }
    private void changeContent(String filePath,char sym) throws IOException {
        List <String> saved = new ArrayList<>();
        saved.add(chosen.getName());
        saved.add(String.valueOf(chosen.getLevel()));
        saved.add(String.valueOf(chosen.getDmg()));
        saved.add(String.valueOf(chosen.getHp()));
        saved.add(String.valueOf(chosen.maxHp));
        saved.add(String.valueOf(chosen.getExp()));
        saved.add(String.valueOf(chosen.getExp_points()));
        System.out.println(saved);
        writeStats(filePath,sym,saved);
    }
}



