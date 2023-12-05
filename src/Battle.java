import javax.swing.*;
import java.util.ArrayList;

public class Battle {
    private Job job;
    private Enemy enemy;
    private boolean attackButton;
    private boolean skillButton;
    private boolean waitButton;


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
}
