import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
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
    private Image image;
//    private Enemy random_enemy; so ako ni sha gideclare para mag assign mog enemy diri for rNdom
//    private Job chosen; so ako sad ni sha gibutang para mao ni ang iassign sa gichoose sa user uwu

    public Capstone(){
        JFrame frame = new JFrame("Group 8 Capstone");
        frame.setContentPane(this.mainPanel);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(500,800);
        frame.setVisible(true);

        cbJobs.addItem(new Job.Priest("Priest",1,1,1));
        cbJobs.addItem(new Job.Knight("Knight",1,1,1,1));
        cbJobs.addItem(new Job.Mage("Mage",1,1,1));
        cbJobs.setSelectedIndex(-1);

        mainPanel.add(selectPanel,"SelectPanel");
        mainPanel.add(battlePanel,"BattlePanel");
        mainPanel.add(infoPanel,"InfoPanel");

        CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
        bStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel,"BattlePanel");
                //pangitaa paagi para makuha ang giselect sa combobox, and then mag use ug random para sa
                // halu chaz, in here kato ako giingon basically ako nahan man ani once ipislit ni kay naa nay enemy nga magpakita
                //so like ikaw ang muset sa kanang enemies using random like i told u while ago, then sa iyang text field i want it to be for example,
                //hp/maxhp example 90/90 or 34/90 naa pakoy iedit sa tanan or kay dapat naa shay current hp nga field di ra hp only. gambatte soldier
                //and ow ya btw iapil sad ang kanang character sa ubos

//                Enemy random_enemy = /*find way para makuha ni basta kato random thingy i told u hahaah*/
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



        cbJobs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("work");
                Job chosen = (Job) cbJobs.getSelectedItem();
                System.out.println(chosen);
                if(chosen instanceof Job.Knight){
                    try {
                        image = ImageIO.read(new File("src/JobImages/knight.png"));
                        ImageIcon icon = new ImageIcon(image.getScaledInstance(250,250,Image.SCALE_SMOOTH));
                        imgLabel.setIcon(icon);
                        System.out.println("WOrk");
                    } catch (IOException a) {
                        throw new RuntimeException(a);
                    }
                }else if(chosen instanceof Job.Mage){
                    try {
                        image = ImageIO.read(new File("src/JobImages/mage.png"));
                        ImageIcon icon = new ImageIcon(image.getScaledInstance(250,250,Image.SCALE_SMOOTH));
                        imgLabel.setIcon(icon);
                        System.out.println("WOrk");
                    } catch (IOException a) {
                        throw new RuntimeException(a);
                    }

                }else if(chosen instanceof Job.Priest){
                    try {
                        image = ImageIO.read(new File("src/JobImages/priest.png"));
                        ImageIcon icon = new ImageIcon(image.getScaledInstance(250,250,Image.SCALE_SMOOTH));
                        imgLabel.setIcon(icon);
                        System.out.println("WOrk");
                    } catch (IOException a) {
                        throw new RuntimeException(a);
                    }

                }
                System.out.println("worked");
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

        /*YOKOSO KIRAKIRA DOKIDOKI MOCHIMOCHI PUYOPUYO WAKUWAKU WASHOI NA WONDER STAGE YEAAA
        AYM EMU OTORI EMU MEANING SMAAIRUUU hahah gomen wa koy lingaw gahuwat ko ninyo anyway maghuna2 sakog efficient way for now sa kaning battle kay
        if kanotis mo sige kog declare ug new battle chuchu idk basta kana lang sa for now so like sa kinsay maassign nako diri dapita basically sa kaning ubos kay
        mga buttons once pressed mupreform ang ila attack based sa napislitan aaand gagamit kog builder pattern ana */

        bAttack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Battle battle = new BattleBuilder(null,null /*ako ni sha ginullfor now but kani kay ang chosen ug randomenemy*/).setAttackButton(true).build();
//                battle.doBattle(); basta method ni nga tawgon maybe maconsidered ni as Facade HAHHA
//                System.out.println(battle);anyway ignore dis kay ako ni sha gikuan para makita ni nako if niwork ba ang builder
            }
        });
        bSkill.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Battle battle = new BattleBuilder(null,null /*ako ni sha ginullfor now but kani kay ang chosen ug randomenemy*/).setSkillButton(true).build();
//                System.out.println(battle); anyway ignore dis kay ako ni sha gikuan para makita ni nako if niwork ba ang builder
            }
        });
        bWaS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Battle battle = new BattleBuilder(null,null /*ako ni sha ginullfor now but kani kay ang chosen ug randomenemy*/).setWaitButton(true).build();
//                System.out.println(battle); anyway ignore dis kay ako ni sha gikuan para makita ni nako if niwork ba ang builder

            }
        });
    }
}

