import javax.swing.*;
import java.awt.Image;

public class DispImageBuilder {
    Job chosen;
    Enemy random_enemy;
    int location;
    Image image;
    JLabel imgLabel;
    JLabel lbAPic;
    JLabel lbEPic;
    JLabel lbAName;
    JTextField tfHPChara;
    JTextField tfHPEnemy;
    JLabel lbEName;
    int enemy_val;

    public DispImageBuilder(Job chosen, Enemy random_enemy, Image image, int location) {
        this.chosen = chosen;
        this.random_enemy = random_enemy;
        this.location = location;
        this.image = image;
    }

    public DispImageBuilder setImgLabel(JLabel imgLabel) {
        this.imgLabel = imgLabel;
        return this;
    }

    public DispImageBuilder setLbAPic(JLabel lbAPic) {
        this.lbAPic = lbAPic;
        return this;
    }

    public DispImageBuilder setLbEPic(JLabel lbEPic) {
        this.lbEPic = lbEPic;
        return this;
    }

    public DispImageBuilder setLbAName(JLabel lbAName) {
        this.lbAName = lbAName;
        return this;
    }

    public DispImageBuilder setTfHPChara(JTextField tfHPChara) {
        this.tfHPChara = tfHPChara;
        return this;
    }

    public DispImageBuilder setTfHPEnemy(JTextField tfHPEnemy) {
        this.tfHPEnemy = tfHPEnemy;
        return this;
    }

    public DispImageBuilder setLbEName(JLabel lbEName) {
        this.lbEName = lbEName;
        return this;
    }

    public DispImageBuilder setEnemy_val(int enemy_val) {
        this.enemy_val = enemy_val;
        return this;
    }
    public DisplayImage build(){
        return new DisplayImage(this);
    }
}
