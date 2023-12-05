import javax.swing.*;
import java.util.ArrayList;

public class Battle {
    private Job job;
    private Enemy enemy;
    private boolean attackButton;
    private boolean skillButton;
    private boolean waitButton;


    // himoag logic ang battle, basically is maselect ang button imo na sha iimplement ang logix

    public Battle(Job job, Enemy enemy, boolean attackButton, boolean skillButton, boolean waitButton) {
        this.job = job;
        this.enemy = enemy;
        this.attackButton = attackButton;
        this.skillButton = skillButton;
        this.waitButton = waitButton;
    }
}
