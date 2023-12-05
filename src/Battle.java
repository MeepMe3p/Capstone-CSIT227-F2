import javax.swing.*;
import java.util.ArrayList;

public class Battle {
    private Job job;
    private Enemy enemy;
    private JButton attackButton1;
    private JButton attackButton;
    private JButton button3;

    public Battle(Job job, Enemy enemy, JButton attackButton1, JButton attackButton, JButton button3) {
        this.job = job;
        this.enemy = enemy;
        this.attackButton1 = attackButton1;
        this.attackButton = attackButton;
        this.button3 = button3;
    }
    // himoag logic ang battle, basically is maselect ang button imo na sha iimplement ang logix
    public int startBattle(){
        final int damage;


        if(attackButton.isSelected()){
            job.attack(enemy);
//            return damage;
        }
        return 0;

    }
}
