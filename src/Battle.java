import java.util.Random;


public class Battle {
    private Job job;
    private Enemy enemy;
    private boolean attackButton;
    private boolean skillButton;
    private boolean waitButton;

    Random random = new Random();

    // himoag logic ang battle, basically is maselect ang button imo na sha iimplement ang logix

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
    }
    //haluuu im not sure if musugot ka diri ka anyway theodore hi so like ako plan diri kay naay usa ka method nga icall didtos main then
    //kaw bahala sa name sa method basically mag agad ang mga methods nga icall diri sa ya so for example ang naa ras main
    // kay battle.imongmethod(); unya ikaw na bahala sa sud anang imongmethod(); pwede ka mag if true ata


    //Confused pa ko later nlng naho iadjust ang ally action
    public void performAction() {
        //Ally action
        if (attackButton) {
            performAttack();
        } else if (skillButton) {
            performSkill();
        } else if (waitButton) {
            performWait();
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

