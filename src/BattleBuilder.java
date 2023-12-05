public class BattleBuilder {
    Job job;
    Enemy enemy;
    boolean attackButton;
    boolean skillButton;
    boolean waitButton;

    //KONICHIWAANDAAHOOOY gibuhat na nako ang builder naa rakoy comments didtos uban just chat lang sa discord or sumn if need mog help

    public BattleBuilder(Job job, Enemy enemy) {
        this.job = job;
        this.enemy = enemy;
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
