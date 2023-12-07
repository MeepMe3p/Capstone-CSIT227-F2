import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class Capstone extends JFrame{
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
    private Image image;
    private Image EnemyImage;
    private Enemy[] enemies = new Enemy[5];
    private Job[] jobs = new Job[3];
    private Enemy random_enemy;
    private Job chosen;
    private int chosen2;

    public Capstone(){

        JFrame frame = new JFrame("Group 8 Capstone");
        frame.setContentPane(this.mainPanel);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(700,800);
        frame.setVisible(true);
        final int[] choice={0};
        choice[0]=0;
        cbJobs.addItem(new Job.Priest());
        cbJobs.addItem(new Job.Knight());
        cbJobs.addItem(new Job.Mage());
        cbJobs.setSelectedIndex(-1);

//        MESSAGE TO CHAZ iwala nalang nang mga choice etc2 nimo iuse lang ang function nga cbJobs.getSelectedItem() kay if mag ingana ka marredundant sha

        jobs[0] = new Job.Priest();
        jobs[1] = new Job.Knight();
        jobs[2] = new Job.Mage();


        mainPanel.add(selectPanel,"SelectPanel");
        mainPanel.add(battlePanel,"BattlePanel");
        mainPanel.add(infoPanel,"InfoPanel");

        CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
        bStart.addActionListener(e-> {
                try {
//                    int jobSelect = getJobSelection();
            System.out.println(choice);
                    if (chosen2==0) {
                        chosen=jobs[0];
                        lbAName.setText(chosen.name+" Lvl "+ chosen.level);
                        tfHPChara.setText(chosen.hp+" / " + chosen.maxhp);
                        image = ImageIO.read(new File("src/JobImages/priest.png"));
                        ImageIcon icon = new ImageIcon(image.getScaledInstance(250,250,Image.SCALE_SMOOTH));
                        lbAPic.setIcon(icon);
                    } else if (chosen2==1) {
                        chosen=jobs[1];
                        lbAName.setText(chosen.name+" Lvl "+ chosen.level);
                        tfHPChara.setText(chosen.hp+" / " + chosen.maxhp);
                        image = ImageIO.read(new File("src/JobImages/knight.png"));
                        ImageIcon icon = new ImageIcon(image.getScaledInstance(250,250,Image.SCALE_SMOOTH));
                        lbAPic.setIcon(icon);
                    } else if (chosen2==2) {
                        chosen=jobs[2];
                        lbAName.setText(chosen.name+" Lvl "+ chosen.level);
                        tfHPChara.setText(chosen.hp+" / " + chosen.maxhp);
                        image = ImageIO.read(new File("src/JobImages/mage.png"));
                        ImageIcon icon = new ImageIcon(image.getScaledInstance(250,250,Image.SCALE_SMOOTH));
                        lbAPic.setIcon(icon);
                    }
                    cardLayout.show(mainPanel, "BattlePanel");
                    Random random = new Random();
                    int val;
                   // while(chosen.hp>0) {
                    val = random.nextInt(5);
                    random_enemy= generateEnemy(val);
                    switch (val) {
                        case 0: {
                            tfHPEnemy.setText(random_enemy.hp+" / " + random_enemy.maxhp);
                            lbEName.setText(random_enemy.name + " Lvl "+ random_enemy.level);
                            EnemyImage = ImageIO.read(new File("src/EnemyImages/scorpion.png"));
                            ImageIcon icon2 = new ImageIcon(EnemyImage.getScaledInstance(250,250,Image.SCALE_SMOOTH));
                            lbEPic.setIcon(icon2);
                            break;
                        }
                        case 1: {
                            tfHPEnemy.setText(random_enemy.hp+" / " + random_enemy.maxhp);
                            lbEName.setText(random_enemy.name+" Lvl "+ random_enemy.level);
                            EnemyImage = ImageIO.read(new File("src/EnemyImages/ancientbishop.png"));
                            ImageIcon icon2 = new ImageIcon(EnemyImage.getScaledInstance(250,250,Image.SCALE_SMOOTH));
                            lbEPic.setIcon(icon2);
                            break;
                        }
                        case 2: {
                            tfHPEnemy.setText(random_enemy.hp+" / " + random_enemy.maxhp);
                            lbEName.setText(random_enemy.name+ " Lvl "+ random_enemy.level);
                            EnemyImage = ImageIO.read(new File("src/EnemyImages/darkstalker.png"));
                            ImageIcon icon2 = new ImageIcon(EnemyImage.getScaledInstance(250,250,Image.SCALE_SMOOTH));
                            lbEPic.setIcon(icon2);
                        break;
                        }
                        case 3: {
                            tfHPEnemy.setText(random_enemy.hp+" / " + random_enemy.maxhp);
                            lbEName.setText(random_enemy.name+" Lvl "+ random_enemy.level);
                            EnemyImage = ImageIO.read(new File("src/EnemyImages/skeleton.png"));
                            ImageIcon icon2 = new ImageIcon(EnemyImage.getScaledInstance(250,250,Image.SCALE_SMOOTH));
                            lbEPic.setIcon(icon2);
                            break;
                        }
                        case 4: {
                            tfHPEnemy.setText(random_enemy.hp+" / " + random_enemy.maxhp);
                            lbEName.setText(random_enemy.name+" Lvl "+ random_enemy.level);
                            EnemyImage = ImageIO.read(new File("src/EnemyImages/suiciderock.png"));
                                ImageIcon icon2 = new ImageIcon(EnemyImage.getScaledInstance(250, 250, Image.SCALE_SMOOTH));
                                lbEPic.setIcon(icon2);
                        break;
                        }
                    }
                    //}

//                Enemy random_enemy = /*find way para makuha ni basta kato random thingy i told u hahaah*/
                //} catch (ClassNotFoundException ex) {
                 //   System.out.println(ex.getMessage());
//                    JOptionPane.showMessageDialog(this, "Select a class");
                }catch (Exception io){
                    throw new RuntimeException(io);
                }

            //level sa kontra
            random_enemy.level_up(chosen);



        });


        bInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel,"InfoPanel");

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

        bSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel,"SelectPanel");
            }
        });
        bSelect2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel,"SelectPanel");
            }
        });



        cbJobInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedChar = (String) cbJobInfo.getSelectedItem();

                jobsInfo.setText("");

                String symbol;

                if(selectedChar != null) {
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
                        int endSymbol = details.indexOf(symbol, startSymbol+1);

                        if(startSymbol != -1 && endSymbol != -1) {
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

        cbEnemyInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedChar = (String) cbEnemyInfo.getSelectedItem();

                enemiesInfo.setText("");

                String symbol;

                if(selectedChar != null) {
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
                        int endSymbol = details.indexOf(symbol, startSymbol+1);

                        if(startSymbol != -1 && endSymbol != -1) {
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


        bSkill.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Battle battle = new BattleBuilder(chosen,random_enemy).setTfJobHP(tfHPChara).setTfEnemyHP(tfHPEnemy).setSkillButton(true).build();
                try{
                    battle.performAction();
                }catch(IllegalArgumentException a){
                    JOptionPane.showMessageDialog(null, a.getMessage());
                }catch(IllegalStateException b){
                    int selected = JOptionPane.showConfirmDialog(null,b.getMessage());
                    chosen.gain_exp(10);
                    if(selected == JOptionPane.YES_OPTION){
                        bStart.doClick();
                    }else if(selected == JOptionPane.NO_OPTION){
                        //basta kani kay musave sha sa iya progress later ni nato iimplement
                        System.out.println("EXIT");
                    }
                }
            }
        });
        cbJobs.addActionListener(e->{

                System.out.println("work");
                Job chosen = (Job) cbJobs.getSelectedItem();
                chosen2=cbJobs.getSelectedIndex();
                System.out.println(chosen);
                if(chosen instanceof Job.Knight){
                    try {
                        image = ImageIO.read(new File("src/JobImages/knight.png"));
                        ImageIcon icon = new ImageIcon(image.getScaledInstance(250,250,Image.SCALE_SMOOTH));
                        imgLabel.setIcon(icon);
                        System.out.println("WOrk");
                        choice[0]=1;
                    } catch (IOException a) {
                        throw new RuntimeException(a);
                    }
                }else if(chosen instanceof Job.Mage){
                    try {
                        image = ImageIO.read(new File("src/JobImages/mage.png"));
                        ImageIcon icon = new ImageIcon(image.getScaledInstance(250,250,Image.SCALE_SMOOTH));
                        imgLabel.setIcon(icon);
                        System.out.println("WOrk");
                        choice[0]=3;
                    } catch (IOException a) {
                        throw new RuntimeException(a);
                    }

                }else if(chosen instanceof Job.Priest){
                    try {
                        image = ImageIO.read(new File("src/JobImages/priest.png"));
                        ImageIcon icon = new ImageIcon(image.getScaledInstance(250,250,Image.SCALE_SMOOTH));
                        imgLabel.setIcon(icon);
                        System.out.println("WOrk");
                        choice[0]=1;
                    } catch (IOException a) {
                       throw new RuntimeException(a);
                    }

                }
                System.out.println("worked");

        });
        bWaS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Battle battle = new BattleBuilder(chosen,random_enemy).setWaitButton(true).setTfJobHP(tfHPChara).setTfEnemyHP(tfHPEnemy).build();
                try{
                    battle.performAction();
                }catch(IllegalArgumentException a){
                    JOptionPane.showMessageDialog(null, a.getMessage());
                }catch(IllegalStateException b){
                    int selected = JOptionPane.showConfirmDialog(null,b.getMessage());
                    chosen.gain_exp(10);
                    if(selected == JOptionPane.YES_OPTION){
                        bStart.doClick();
                    }else if(selected == JOptionPane.NO_OPTION){
                        //basta kani kay musave sha sa iya progress later ni nato iimplement
                        System.out.println("EXIT");
                    }
                }

            }

        });


        bAttack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Battle battle = new BattleBuilder(chosen,random_enemy).setAttackButton(true).setTfJobHP(tfHPChara).setTfEnemyHP(tfHPEnemy).build();
                try{
                    battle.performAction();
                }catch(IllegalArgumentException a){
                    JOptionPane.showMessageDialog(null, a.getMessage());
                }catch(IllegalStateException b){
                    int selected = JOptionPane.showConfirmDialog(null,b.getMessage());
                    chosen.gain_exp(10);
                    if(selected == JOptionPane.YES_OPTION){
                        bStart.doClick();
                    }else if(selected == JOptionPane.NO_OPTION){
                        //basta kani kay musave sha sa iya progress later ni nato iimplement
                        System.out.println("EXIT");
                    }
                }
            }
        });


    }

    private Enemy generateEnemy(int val) {
        switch (val){
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


    //gets the selected index sa jobs sa select panel
//    public int getJobSelection() {
//        return choice[0];
//    }
}

