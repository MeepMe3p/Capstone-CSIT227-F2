import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.util.Random;
public class Game extends JFrame implements MusicPlayer{
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
    private static ImageIcon logo = new ImageIcon("src/Images/BuriedBornes_Logo.png");
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
    private Enemy random_enemy;
    private Job chosen;

    private JScrollPane jScrollPane;
    private JButton bStartMenu;

    private HighScore hs;
    private int total_dmg;
    private int enemies_killed;
    private int bosses_killed;

    private Clip battle_Sound = null;
    private Clip main_Sound = null;
    private DisplayImage disp = null;
    private DisplayDetails entityDetails = null;
    private SaveLoad svl;

    public Game(){
        if(main_Sound == null){
            main_Sound = MusicPlayer.startMusic("src\\sounds\\main.wav");
        }

        //creating the main JFrame
        JFrame frame = new JFrame("BuriedBornes - Group 8");
        frame.setContentPane(this.mainPanel);
        ((JComponent) frame.getContentPane()).setBorder(new EmptyBorder(20, 20, 20, 20));
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(700,800);
        frame.setVisible(true);
        frame.setIconImage(logo.getImage());

        JLabel start_bgImage = new JLabel();
        start_bgImage.setIcon(new ImageIcon("src/Images/start_bgImage.jpg"));
        start_bgImage.setHorizontalAlignment(JLabel.CENTER);
        startPanel.setLayout(new BorderLayout());
        startPanel.add(start_bgImage);

        borderButtons();

        //adding the different types of Job
        cbJobs.addItem(new Job.Priest());
        cbJobs.addItem(new Job.Knight());
        cbJobs.addItem(new Job.Mage());
        cbJobs.setSelectedIndex(-1);

        //adding the different panels to the mainPanel to
        //put into a cardlayout
        mainPanel.add(startPanel, "StartPanel");
        mainPanel.add(selectPanel,"SelectPanel");
        mainPanel.add(battlePanel,"BattlePanel");
        mainPanel.add(infoPanel,"InfoPanel");
        mainPanel.add(highscorePanel,"highScorePanel");

        CardLayout cardLayout = (CardLayout) mainPanel.getLayout();

        //starts the battle
        bStart.addActionListener(e -> {
            cardLayout.show(mainPanel, "BattlePanel");
            textArea1 .setText("");
            // Added this so when creating a new chosen character will start music and end main
            if(battle_Sound == null){
                main_Sound.close();
                main_Sound = null;
                battle_Sound = MusicPlayer.startMusic("src\\sounds\\battle.wav");
            }
            getEnemy();


        });

        //goes to the infoPanel wherein it shows the information
        //of the Jobs & Enemies
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

        //goes to the selectPanel
        bSelect.addActionListener(e->{
            if(chosen == null){
                cardLayout.show(mainPanel, "SelectPanel");
                return;
            }
            svl = new SaveLoad(chosen,random_enemy,main_Sound,battle_Sound,cardLayout,mainPanel);
            if(chosen.hp > 0){
                svl.save();
            }
            resetCombobox(cbJobs);
            cbJobs.setSelectedIndex(-1);
            imgLabel.setIcon(null);
            cardLayout.show(mainPanel, "SelectPanel");
        });

        //goes to the selectPanel
        bSelect2.addActionListener(e->{
                cardLayout.show(mainPanel, "SelectPanel");
        });


        //shows the selected Job's info
        cbJobInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedChar = (String) cbJobInfo.getSelectedItem();
                entityDetails = new DisplayDetails(1,selectedChar,null,jobsInfo);
                entityDetails.displayDetails();
            }
        });


        // Simplified the actionListener for the items below!
        bWaS.addActionListener(e -> performBattleAction(new BattleBuilder(chosen, random_enemy, /*,battleSeq*/ textArea1,bStart).setWaitButton(true).setTfJobHP(tfHPChara).setTfEnemyHP(tfHPEnemy)));
        bAttack.addActionListener(e -> performBattleAction(new BattleBuilder(chosen, random_enemy, /*,battleSeq*/ textArea1,bStart).setAttackButton(true).setTfJobHP(tfHPChara).setTfEnemyHP(tfHPEnemy)));
        bSkill.addActionListener(e -> performBattleAction(new BattleBuilder(chosen, random_enemy, textArea1,bStart).setTfJobHP(tfHPChara).setTfEnemyHP(tfHPEnemy).setSkillButton(true)));

        //shows the selected Enemy's info
        cbEnemyInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedChar = (String) cbEnemyInfo.getSelectedItem();
                entityDetails = new DisplayDetails(2,selectedChar,enemiesInfo,null);
                entityDetails.displayDetails();
            }
        });

        cbJobs.addActionListener(e -> {

            chosen = (Job) cbJobs.getSelectedItem();
            disp = new DispImageBuilder(chosen,random_enemy,image,1).setImgLabel(imgLabel).build();
            try{
                disp.displayImages();
            }catch(NullPointerException a){
                JOptionPane.showMessageDialog(null,a.getMessage());
            }
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
                hs = new HighScore(taHighScore);
                hs.showByName();
            }
        });
        sortByDamageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taHighScore.setText("");
                hs = new HighScore(taHighScore);
                hs.showByDamage();
            }
        });
        sortByEnemiesKilledButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taHighScore.setText("");
                hs = new HighScore(taHighScore);
                hs.showByEnemiesKilled();
            }
        });
        sortByBossesKilledButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taHighScore.setText("");
                hs = new HighScore(taHighScore);
                hs.showByBossesKilled();
            }
        });
        bNewGame.addActionListener(e->{
            cardLayout.show(mainPanel, "SelectPanel");
        });

        //loads a previous game
        bLoadGame.addActionListener(e -> {
            svl = new SaveLoad(chosen,random_enemy,main_Sound,battle_Sound,cardLayout,mainPanel);
            try{
                chosen = svl.load();
                getEnemy();
                cardLayout.show(mainPanel, "BattlePanel");
            }catch(NullPointerException a){
                cardLayout.show(mainPanel,"startPanel");
            }
        });

        bStartMenu.addActionListener(e -> {
            cardLayout.show(mainPanel, "StartPanel");
        });

        bGoBack.addActionListener(e->{
            cardLayout.show(mainPanel, "SelectPanel");

        });
    }

    // gets a random enemy and sets the necessary stats on the battlepanel
    public void getEnemy() {
        Random random = new Random();
        int val;
        val = random.nextInt(5);
        random_enemy = generateEnemy(val);
        disp = new DispImageBuilder(chosen,random_enemy,image,2).setTfHPEnemy(tfHPEnemy).setLbEPic(lbEPic).setEnemy_val(val)
                .setLbEName(lbEName).setLbAName(lbAName).setLbAPic(lbAPic).setTfHPChara(tfHPChara).build();
        try{
            random_enemy.level_up(chosen);
            disp.displayImages();

        }catch(NullPointerException e){
            int i = JOptionPane.showConfirmDialog(null,e.getMessage());
            if(i == JOptionPane.YES_OPTION){
                bSelect.doClick();
            }else{
                JOptionPane.showMessageDialog(null,"You will need a job, so you shall return");
                bSelect.doClick();
            }
        }
    }

    //I Just consolidate all performBattleAction into one since this is simpler
    private void performBattleAction(BattleBuilder battleBuilder) {
        Battle battle = battleBuilder.build();
        try {
            battle.performAction();
            total_dmg += chosen.getTotal_dmg();
        } catch (IllegalArgumentException a) {
            JOptionPane.showMessageDialog(null, a.getMessage());
            //saves le scorezz when lost
            battle.saveScore(total_dmg,enemies_killed,bosses_killed);
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
            if (random_enemy instanceof Enemy.DarkStalker || random_enemy instanceof Enemy.AncientBishop) {
                bosses_killed++;
            }
            if (selected == JOptionPane.YES_OPTION) {
                if(!chosen.isAlive()){
                    battle_Sound.close();
                    battle_Sound.close();
                    battle_Sound = null;
                    main_Sound = MusicPlayer.startMusic("src\\sounds\\main.wav");
                }
                bStart.doClick();
            } else{
                bSelect.doClick();
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
    private void resetCombobox(JComboBox<Job> jobs){
        jobs.removeAllItems();
        jobs.addItem(new Job.Priest());
        jobs.addItem(new Job.Knight());
        jobs.addItem(new Job.Mage());
    }

    private void borderButtons() {
        Border border = BorderFactory.createLineBorder(Color.WHITE, 3);
        bNewGame.setBorder(border);
        bLoadGame.setBorder(border);
        highscoresButton.setBorder(border);
        bInfo.setBorder(border);
        bStart.setBorder(border);
        bStartMenu.setBorder(border);
        bSelect.setBorder(border);
        bAttack.setBorder(border);
        bSkill.setBorder(border);
        bWaS.setBorder(border);
    }
}



