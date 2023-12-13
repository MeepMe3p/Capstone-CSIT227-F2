import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * This is where all the battle details and the battle sequence is, this uses a BattleBuilder at its constructor
 * because of optional parameters and a possibility for similar constructor parameters, this is also where the scores
 * are saved into the file Highscores
 */
public class Battle {
    private Job job;
    private Enemy enemy;
    private boolean attackButton;
    private boolean skillButton;
    private boolean waitButton;
    private JTextField tfEnemyHP;
    private JTextField tfJobHP;
    private JTextArea battleSeq;

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

    protected Battle(BattleBuilder builder)
    {

        this.job = builder.job;
        this.enemy = builder.enemy;
        this.attackButton = builder.attackButton;
        this.skillButton = builder.skillButton;
        this.waitButton = builder.waitButton;
        this.tfEnemyHP = builder.tfEnemyHP;
        this.tfJobHP = builder.tfJobHP;
        this.battleSeq = builder.battleSeq;
    }

    public void performAction() {
        if(job == null){
            throw new NullPointerException("You need to choose a job!\nWould you like to go back?");
        }
        if (attackButton) {
            battleSeq.append(job.attack(enemy)+"\n" );
            tfJobHP.setText("HP: "+ job.hp+ " / "+ job.maxHp);
            tfEnemyHP.setText("HP: "+ enemy.hp+ " / " + enemy.maxHp);

        } else if (skillButton) {
            battleSeq.append(job.skill(enemy)+"\n");
            tfJobHP.setText("HP: "+ job.hp+ " / "+ job.maxHp);
            tfEnemyHP.setText("HP: "+ enemy.hp+ " / " + enemy.maxHp);
        } else if (waitButton) {
            battleSeq.append(job.wait_and_see()+"\n");
            tfJobHP.setText("HP: "+ job.hp+ " / "+ job.maxHp);
            tfEnemyHP.setText("HP: "+ enemy.hp+ " / " + enemy.maxHp);
        }

        determineEnemyType();

        if(job.hp <= 0){
            tfJobHP.setText("HP: 0 / "+ job.maxHp);

            throw new IllegalArgumentException("You lose");
        }
        if(enemy.hp <= 0){
            tfEnemyHP.setText("HP: 0 / "+ enemy.maxHp);
            throw new IllegalStateException("You win. Would you like to continue?");
        }

    }
    private void determineEnemyType() {
        double[] probabilities = enemy.getProbabilities();
        EnemyActions monster = (EnemyActions) enemy;
        double randomAction = random.nextDouble();

        // Calculate cumulative probabilities
        double AttackProb = probabilities[0];
        double SkillsProb = AttackProb + probabilities[1];

        if (randomAction <= AttackProb) {
            battleSeq.append(monster.attack(job)+"\n");
        } else if (randomAction <= SkillsProb) {
            int numberOfSkills = 1; // You might want to get this dynamically from the enemy
            int randomSkillIndex = random.nextInt(numberOfSkills);
            battleSeq.append(monster.skill(job, randomSkillIndex)+"\n");
        } else {
            battleSeq.append(monster.wait_and_see()+"\n");
        }
        battleSeq.setForeground(Color.WHITE);
    }

    public void saveScore(int total_dmg,int enemies_killed,int bosses_killed){
        BufferedWriter write_hs;

        final String highScoreFile = "src/HighScores";
        try {
            write_hs = new BufferedWriter(new FileWriter(highScoreFile,true));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        try {
            write_hs.append(job.name + "," + job.level + "," + total_dmg + "," + enemies_killed + "," + bosses_killed);
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
    }

}

