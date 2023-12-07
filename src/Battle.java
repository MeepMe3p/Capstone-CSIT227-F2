import javax.swing.*;
import java.util.Random;


public class Battle {
    private Job job;
    private Enemy enemy;
    private boolean attackButton;
    private boolean skillButton;
    private boolean waitButton;
    private JTextField tfEnemyHP;
    private JTextField tfJobHP;

    Random random = new Random();


    @Override
    public String toString() {
        return "Battle{" +
                "job=" + job +
                ", enemy=" + enemy +
                ", attackButton=" + attackButton +
                ", skillButton=" + skillButton +
                ", waitButton=" + waitButton +
                '}';
    }

    protected Battle(BattleBuilder builder) {
        this.job = builder.job;
        this.enemy = builder.enemy;
        this.attackButton = builder.attackButton;
        this.skillButton = builder.skillButton;
        this.waitButton = builder.waitButton;
        this.tfEnemyHP = builder.tfEnemyHP;
        this.tfJobHP = builder.tfJobHP;


    }

    public void performAction() {
        if (attackButton) {
            job.attack(enemy);
            tfJobHP.setText("HP: "+ job.hp+ " / "+ job.maxhp);
            tfEnemyHP.setText("HP: "+ enemy.hp+ " / " + enemy.maxhp);
        } else if (skillButton) {
            job.skill(enemy);
            tfJobHP.setText("HP: "+ job.hp+ " / "+ job.maxhp);
            tfEnemyHP.setText("HP: "+ enemy.hp+ " / " + enemy.maxhp);
        } else if (waitButton) {
            job.wait_and_see();
            tfJobHP.setText("HP: "+ job.hp+ " / "+ job.maxhp);
            tfEnemyHP.setText("HP: "+ enemy.hp+ " / " + enemy.maxhp);
        }
        //Enemy action
        // Determine the type of enemy and perform their actions
        determineEnemyType();
    }

    private void determineEnemyType() {
        EnemyActions monster = (EnemyActions) enemy;
        int randomAction = random.nextInt(3);
        switch (randomAction) {
            case 0:
                monster.attack(job);
                break;
            case 1:
                int numberOfSkills = 1;
                int randomSkillIndex = random.nextInt(numberOfSkills);
                monster.skill(job, randomSkillIndex);
                break;
            case 2:
                monster.wait_and_see();
                break;
        }
    }


}

