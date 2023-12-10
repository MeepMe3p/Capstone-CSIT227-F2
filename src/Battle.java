import javax.swing.*;
import java.awt.*;
import java.util.Random;


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

    protected Battle(BattleBuilder builder) {
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
        if (attackButton) {
            battleSeq.append(job.attack(enemy)+"\n");
            job.attack(enemy);
            tfJobHP.setText("HP: "+ job.hp+ " / "+ job.maxHp);
            tfEnemyHP.setText("HP: "+ enemy.hp+ " / " + enemy.maxHp);
        } else if (skillButton) {
            battleSeq.append(job.skill(enemy)+"\n");
            job.skill(enemy);
            tfJobHP.setText("HP: "+ job.hp+ " / "+ job.maxHp);
            tfEnemyHP.setText("HP: "+ enemy.hp+ " / " + enemy.maxHp);
        } else if (waitButton) {
            battleSeq.append(job.wait_and_see()+"\n");
            job.wait_and_see();
            tfJobHP.setText("HP: "+ job.hp+ " / "+ job.maxHp);
            tfEnemyHP.setText("HP: "+ enemy.hp+ " / " + enemy.maxHp);
        }

        determineEnemyType();

        // note so ako ni gituyo lahi ang ithrow kay ako idea naay 2 ka catches depedning if win or lose
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
            monster.wait_and_see();
        }
        battleSeq.setForeground(Color.WHITE);
    }

//    private void determineEnemyType() {
//        double[] probabilities = enemy.getProbabilities();
//        EnemyActions monster = (EnemyActions) enemy;
//        double randomAction = random.nextDouble();
//        if(randomAction > 0 && randomAction <= probabilities[0])
//        switch ( {
//            case 0:
//                monster.attack(job);
//                break;
//            case 1:
//                int numberOfSkills = 1;
//                int randomSkillIndex = random.nextInt(numberOfSkills);
//                monster.skill(job, randomSkillIndex);
//                break;
//            case 2:
//                monster.wait_and_see();
//                break;
//        }
//    }


}

