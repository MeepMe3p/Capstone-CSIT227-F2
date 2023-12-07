import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
    private JComboBox cbCharacter;
    private JTextArea taInfo;
    private JButton bSkill;
    private JButton bWaS;
    private JButton bSelect;
    private JTextField tfHPEnemy;
    private JTextField tfHPChara;
    private JLabel lbEName;
    private JLabel lbAName;
    private JLabel lbEPic;
    private JLabel lbAPic;
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

        enemies[0]=new Enemy.Scorpion();
        enemies[1]=new Enemy.AncientBishop();
        enemies[2]=new Enemy.DarkStalker();
        enemies[3]=new Enemy.Skeleton();
        enemies[4]=new Enemy.SuicideRock();

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
                        lbAName.setText(chosen.name);
                        tfHPChara.setText(chosen.hp+" / " + chosen.maxhp);
                        image = ImageIO.read(new File("src/JobImages/priest.png"));
                        ImageIcon icon = new ImageIcon(image.getScaledInstance(250,250,Image.SCALE_SMOOTH));
                        lbAPic.setIcon(icon);
                    } else if (chosen2==1) {
                        chosen=jobs[1];
                        lbAName.setText(chosen.name);
                        tfHPChara.setText(chosen.hp+" / " + chosen.maxhp);
                        image = ImageIO.read(new File("src/JobImages/knight.png"));
                        ImageIcon icon = new ImageIcon(image.getScaledInstance(250,250,Image.SCALE_SMOOTH));
                        lbAPic.setIcon(icon);
                    } else if (chosen2==2) {
                        chosen=jobs[2];
                        lbAName.setText(chosen.name);
                        tfHPChara.setText(chosen.hp+" / " + chosen.maxhp);
                        image = ImageIO.read(new File("src/JobImages/mage.png"));
                        ImageIcon icon = new ImageIcon(image.getScaledInstance(250,250,Image.SCALE_SMOOTH));
                        lbAPic.setIcon(icon);
                    }
                    cardLayout.show(mainPanel, "BattlePanel");
                    Random random = new Random();
                    int enemy_type;
                   // while(chosen.hp>0) {
                    enemy_type = random.nextInt(5);
                    random_enemy=enemies[enemy_type];
                    switch (enemy_type) {
                        case 0: {
                            tfHPEnemy.setText(random_enemy.hp+" / " + random_enemy.maxhp);
                            lbEName.setText(random_enemy.name);
                            EnemyImage = ImageIO.read(new File("src/EnemyImages/scorpion.png"));
                            ImageIcon icon2 = new ImageIcon(EnemyImage.getScaledInstance(250,250,Image.SCALE_SMOOTH));
                            lbEPic.setIcon(icon2);
                            break;
                        }
                        case 1: {
                            tfHPEnemy.setText(random_enemy.hp+" / " + random_enemy.maxhp);
                            lbEName.setText(random_enemy.name);
                            EnemyImage = ImageIO.read(new File("src/EnemyImages/ancientbishop.png"));
                            ImageIcon icon2 = new ImageIcon(EnemyImage.getScaledInstance(250,250,Image.SCALE_SMOOTH));
                            lbEPic.setIcon(icon2);
                            break;
                        }
                        case 2: {
                            tfHPEnemy.setText(random_enemy.hp+" / " + random_enemy.maxhp);
                            lbEName.setText(random_enemy.name);
                            EnemyImage = ImageIO.read(new File("src/EnemyImages/darkstalker.png"));
                            ImageIcon icon2 = new ImageIcon(EnemyImage.getScaledInstance(250,250,Image.SCALE_SMOOTH));
                            lbEPic.setIcon(icon2);
                        break;
                        }
                        case 3: {
                            tfHPEnemy.setText(random_enemy.hp+" / " + random_enemy.maxhp);
                            lbEName.setText(random_enemy.name);
                            EnemyImage = ImageIO.read(new File("src/EnemyImages/skeleton.png"));
                            ImageIcon icon2 = new ImageIcon(EnemyImage.getScaledInstance(250,250,Image.SCALE_SMOOTH));
                            lbEPic.setIcon(icon2);
                            break;
                        }
                        case 4: {
                            tfHPEnemy.setText(random_enemy.hp+" / " + random_enemy.maxhp);
                            lbEName.setText(random_enemy.name);
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



        });


        bInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel,"InfoPanel");
                //halu raya ikaw diri so like basicaly once maopen ni sha dapat naa nay mga sulod ang combobox pwede raka mag cbCharacter.addItem() para sa each
                //now go to cbCharacter
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



        cbCharacter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //halu raya okaeri nice to meet you again hahaha so like kani sha kay once maselect ang for example ang kanang ya kay ang text area kay maset sha
                //into something depending sa iyang naselect, for now gahuna2 pajud ko if iusa ba nato ka text file each character or iseparate ba nato pwede ta
                //magsabot unsay mas maayu

            }
        });


        bSkill.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Battle battle = new BattleBuilder(chosen,random_enemy).setTfJobHP(tfHPChara).setTfEnemyHP(tfHPEnemy).setSkillButton(true).build();
                battle.performAction();
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
                battle.performAction();

            }

        });


        bAttack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Battle battle = new BattleBuilder(chosen,random_enemy).setAttackButton(true).setTfJobHP(tfHPChara).setTfEnemyHP(tfHPEnemy).build();
                battle.performAction();
            }
        });
    }
    //gets the selected index sa jobs sa select panel
//    public int getJobSelection() {
//        return choice[0];
//    }
}

