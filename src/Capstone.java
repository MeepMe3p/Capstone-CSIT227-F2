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
    private JPanel loadPanel;
    private JButton bGoBack;
    private JRadioButton rbGame2;
    private JRadioButton rbGame1;
    private JRadioButton rbGame3;
    private JButton bStartBtn;
    private JTextArea textArea1;
    private Image image;
    private Image EnemyImage;
    private Enemy random_enemy;
    private Job chosen;

    private JScrollPane jScrollPane;

    private final String highScoreFile = "src/HighScores";
    private int total_dmg;
    private int enemies_killed;
    private int bosses_killed;

    private Clip battle_Sound = null;
    private Clip main_Sound = null;
    public Capstone(){
        if(main_Sound == null){
            main_Sound = MusicPlayer.startMusic("C:\\Users\\hp\\Downloads\\main.wav");
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
        mainPanel.add(loadPanel, "LoadPanel");

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
                battle_Sound = MusicPlayer.startMusic("C:\\Users\\hp\\Downloads\\battle.wav");
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
            //if (chosen.hp <= 0) {
                System.out.println("hi");
                try {
                    JOptionPane.showConfirmDialog(this, "Would you like to save your character's progress?");
                int slot = 0;
                    System.out.println(slot);
                if (JOptionPane.YES_OPTION == 0) {
                    //Close battle music
                    if(battle_Sound != null){
                        battle_Sound.close();
                        battle_Sound = null;
                        main_Sound = MusicPlayer.startMusic("C:\\Users\\hp\\Downloads\\main.wav");
                    }

                    String filePath = "src/gameProgress.txt";
                    List<String> Stats = readBetween(filePath, '!');
                        if (slot == 0 && chosen != null) {
                            changeContent(filePath,'!');
                        } else if (slot == 1) {
                            changeContent(filePath,'@');
                        } else if (slot == 2) {
                            changeContent(filePath,'#');
                        } else {
                            JOptionPane.showConfirmDialog(this, "Slot is full.\n Would you like to replace slot 3?");
                        }
                }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
           // }
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
            cardLayout.show(mainPanel, "LoadPanel");
        });
        bGoBack.addActionListener(e -> {
            cardLayout.show(mainPanel, "StartPanel");
        });

        bStartBtn.addActionListener(e->{
            String selected = "Game2";

            String symbol;

            if (selected != null) {
                String Char = "src/gameProgress.txt";

//                try {
//                    String details = Files.readString(Paths.get(Char));
//                    symbol = switch (selected) {
//                        case "Game1" -> "!";
//                        case "Game2" -> "@";
//                        case "Game3" -> "#";
//                        default -> "";
//                    };
//
//                    int startSym = details.indexOf(symbol);
//                    int endSym = details.indexOf(symbol, startSym + 1);
//
//                    if (startSym != -1 && endSym != -1) {
//                        System.out.println(details.substring(startSym + symbol.length(), endSym));
//                    }
//                } catch (IOException io) {
//                    throw new RuntimeException(io);
//                }
        String loadedFile;
        List<String> Stats;
        Battle bat;
        Random ran;
        try {
            if (rbGame1.isSelected()) {
                Stats = readBetween(Char,'!');
                if (Stats.size() != 0) {
                    JOptionPane.showConfirmDialog(this, "Stats: \n Type: " + Stats.get(0) +
                            "\n Lvl: " + Stats.get(1) +
                            "\n Dmg: " + Stats.get(2) +
                            "\n Hp: " + Stats.get(3) +
                            "\n Maxhp: " + Stats.get(4) +
                            "\n Exp: " + Stats.get(5) +
                            "\n Exp Pts: " + Stats.get(6));
                if (JOptionPane.YES_OPTION == 0) {
                    ran = new Random();
                    int val = ran.nextInt(4);
                    random_enemy = generateEnemy(val);

                    loadJob(Stats);
                    getEnemy();
                    cardLayout.show(mainPanel, "BattlePanel");

                } else if (JOptionPane.CANCEL_OPTION == 2) {
                    return;
                } else if (JOptionPane.NO_OPTION == 1) {
                    return;
                }
                }else{
                        System.out.println("No Game Found");
                }
            } else if(rbGame2.isSelected()){
                Stats = readBetween(Char,'@');
                if (Stats.size() != 0) {
                    JOptionPane.showConfirmDialog(null, "Stats: \n Type: " + Stats.get(0) +
                            "\n Lvl: " + Stats.get(1) +
                            "\n Dmg: " + Stats.get(2) +
                            "\n Hp: " + Stats.get(3) +
                            "\n Maxhp: " + Stats.get(4) +
                            "\n Exp: " + Stats.get(5) +
                            "\n Exp Pts: " + Stats.get(6));
                    if (JOptionPane.YES_OPTION == 0) {
                        ran = new Random();
                        int val = ran.nextInt(4);
                        random_enemy = generateEnemy(val);

                        loadJob(Stats);
                        getEnemy();
                        cardLayout.show(mainPanel, "BattlePanel");

                    } else if (JOptionPane.CANCEL_OPTION == 2) {
                        return;
                    } else if (JOptionPane.NO_OPTION == 1) {
                        return;
                    }
                }else{
                    System.out.println("No Game Found");
                }
            } else if (rbGame3.isSelected()) {
                bStart.doClick();
            }
        }catch (Exception io){
            throw new RuntimeException(io);
        }
            }
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
                main_Sound = MusicPlayer.startMusic("C:\\Users\\hp\\Downloads\\main.wav");
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
                    main_Sound = MusicPlayer.startMusic("C:\\Users\\hp\\Downloads\\main.wav");
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

//    private static List<String> readAndAssign(String filePath) throws IOException{
//        Path path = Paths.get(filePath);
//        return Files.readAllLines(path);
//    }

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
    private int getSlot() throws IOException {
        List<String> tempslot = readBetween("src/gameProgress.txt",'$');
        int slot = Integer.parseInt(tempslot.toString());
        return slot;
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
    private void addSlot(){
        char sym = '$';
    }
}



