import javax.swing.*;

public class BattleBuilder {
    Job job;
    Enemy enemy;
    boolean attackButton;
    boolean skillButton;
    boolean waitButton;
    JTextField tfEnemyHP;
    JTextField tfJobHP;
    JTextArea battleSeq;

    public BattleBuilder(Job job, Enemy enemy,/*,BattleSequence battleSeq*/JTextArea textField) {
        this.job = job;
        this.enemy = enemy;
      this.battleSeq = textField;
    }

    public BattleBuilder setTfEnemyHP(JTextField tfEnemyHP) {
        this.tfEnemyHP = tfEnemyHP;
        return this;
    }

    public BattleBuilder setTfJobHP(JTextField tfJobHP) {
        this.tfJobHP = tfJobHP;
        return this;
    }

    public BattleBuilder setAttackButton(boolean attackButton) {
        this.attackButton = attackButton;
        return this;
    }

    public BattleBuilder setSkillButton(boolean skillButton) {
        this.skillButton = skillButton;
        return this;
    }

    public BattleBuilder setWaitButton(boolean waitButton) {
        this.waitButton = waitButton;
        return this;
    }
    public Battle build(){
        return new Battle(this);
    }
}
