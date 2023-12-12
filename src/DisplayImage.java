import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
public class DisplayImage {
    private final Job chosen;
    private final Enemy random_enemy;
    private final int location;
    private Image image;
    private final JLabel imgLabel;
    private final JLabel lbAPic;
    private final JLabel lbEPic;
    private final JLabel lbAName;
    private final JTextField tfHPChara;
    private final JTextField tfHPEnemy;
    private final JLabel lbEName;
    private final int enemy_val;


    public DisplayImage(DispImageBuilder builder) {
        this.chosen = builder.chosen;
        this.random_enemy = builder.random_enemy;
        this.location = builder.location;
        this.image = builder.image;
        this.imgLabel = builder.imgLabel;
        this.lbAPic = builder.lbAPic;
        this.lbEPic = builder.lbEPic;
        this.lbAName = builder.lbAName;
        this.tfHPChara = builder.tfHPChara;
        this.tfHPEnemy = builder.tfHPEnemy;
        this.lbEName = builder.lbEName;
        this.enemy_val = builder.enemy_val;
    }

    public void displayImages(){
        if(location == 1){
            mainPage();
        }else if(location ==2 ){
            attackPage();
        }
    }
    private void attackPage(){
        if(chosen == null){
            throw new NullPointerException("You need to choose a job!\nWould you like to go back?");
        }
        try {
            if (chosen instanceof Job.Priest) {
                lbAName.setText(chosen.name + " Lvl " + chosen.level);
                tfHPChara.setText(chosen.hp + " / " + chosen.maxHp);
                image = ImageIO.read(new File("src/JobImages/priest.png"));
                ImageIcon icon = new ImageIcon(image.getScaledInstance(250, 250, Image.SCALE_SMOOTH));
                lbAPic.setIcon(icon);
            } else if (chosen instanceof Job.Knight) {
                lbAName.setText(chosen.name + " Lvl " + chosen.level);
                tfHPChara.setText(chosen.hp + " / " + chosen.maxHp);
                image = ImageIO.read(new File("src/JobImages/knight.png"));
                ImageIcon icon = new ImageIcon(image.getScaledInstance(250, 250, Image.SCALE_SMOOTH));
                lbAPic.setIcon(icon);
            } else if (chosen instanceof Job.Mage) {
                lbAName.setText(chosen.name + " Lvl " + chosen.level);
                tfHPChara.setText(chosen.hp + " / " + chosen.maxHp);
                image = ImageIO.read(new File("src/JobImages/mage.png"));
                ImageIcon icon = new ImageIcon(image.getScaledInstance(250, 250, Image.SCALE_SMOOTH));
                lbAPic.setIcon(icon);
            }

            System.out.println("This is random enemy " + random_enemy);
        } catch (Exception io) {
            throw new RuntimeException(io);
        }
        try {

            switch (enemy_val) {
                case 0: {
                    tfHPEnemy.setText(random_enemy.hp + " / " + random_enemy.maxHp);
                    lbEName.setText(random_enemy.name + " Lvl " + random_enemy.level);
                    image = ImageIO.read(new File("src/EnemyImages/scorpion.png"));
                    ImageIcon icon2 = new ImageIcon(image.getScaledInstance(250, 250, Image.SCALE_SMOOTH));
                    lbEPic.setIcon(icon2);
                    break;
                }
                case 1: {
                    tfHPEnemy.setText(random_enemy.hp + " / " + random_enemy.maxHp);
                    lbEName.setText(random_enemy.name + " Lvl " + random_enemy.level);
                    image = ImageIO.read(new File("src/EnemyImages/ancientbishop.png"));
                    ImageIcon icon2 = new ImageIcon(image.getScaledInstance(250, 250, Image.SCALE_SMOOTH));
                    lbEPic.setIcon(icon2);
                    break;
                }
                case 2: {
                    tfHPEnemy.setText(random_enemy.hp + " / " + random_enemy.maxHp);
                    lbEName.setText(random_enemy.name + " Lvl " + random_enemy.level);
                    image = ImageIO.read(new File("src/EnemyImages/darkstalker.png"));
                    ImageIcon icon2 = new ImageIcon(image.getScaledInstance(250, 250, Image.SCALE_SMOOTH));
                    lbEPic.setIcon(icon2);
                    break;
                }
                case 3: {
                    tfHPEnemy.setText(random_enemy.hp + " / " + random_enemy.maxHp);
                    lbEName.setText(random_enemy.name + " Lvl " + random_enemy.level);
                    image = ImageIO.read(new File("src/EnemyImages/skeleton.png"));
                    ImageIcon icon2 = new ImageIcon(image.getScaledInstance(250, 250, Image.SCALE_SMOOTH));
                    lbEPic.setIcon(icon2);
                    break;
                }
                case 4: {
                    tfHPEnemy.setText(random_enemy.hp + " / " + random_enemy.maxHp);
                    lbEName.setText(random_enemy.name + " Lvl " + random_enemy.level);
                    image = ImageIO.read(new File("src/EnemyImages/suiciderock.png"));
                    ImageIcon icon2 = new ImageIcon(image.getScaledInstance(250, 250, Image.SCALE_SMOOTH));
                    lbEPic.setIcon(icon2);
                    break;
                }
            }
        } catch (IOException io) {
            throw new RuntimeException(io);
        }



    }
    private void mainPage(){
        if(chosen instanceof Job.Knight){
            try {
                image = ImageIO.read(new File("src/JobImages/knight.png"));
                ImageIcon icon = new ImageIcon(image.getScaledInstance(250,250, Image.SCALE_SMOOTH));
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
    }
}
