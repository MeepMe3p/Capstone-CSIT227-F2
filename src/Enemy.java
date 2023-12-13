import java.util.Random;

public abstract class Enemy extends Character implements Enemy_LevelUp{
    Random rand = new Random();
    private double[]  probabilities;

    public Enemy(String name, int level, int dmg, int hp, int maxhp, double[] probabilities) {
        super(name, level, dmg, hp,maxhp);
        this.probabilities = probabilities;
    }

    public double[] getProbabilities() {
        return probabilities;
    }

    public void level_up(Job job) {
        if(job == null){
            throw new NullPointerException("You need to choose a job!\nWould you like to go back?");
        }
        int plusLevel = rand.nextInt(3)-1;
        int num = job.getLevel();
        this.level = num + plusLevel;

        if(this.level == 0 || job.level == 1){
            this.level = 1;
            return;
        }
        improve_stats();
    }

    public void improve_stats() {
        this.dmg += this.level * 3;
        this.maxHp += this.level * 5;
        this.hp = this.maxHp;
    }

    public int give_exp() {
        return level*5;
    }

    //Enemy class type "Scorpion"
    public static class Scorpion extends Enemy implements EnemyActions{
        public Scorpion() {
            super("Scorpion", 1, 20, 100,100,  new double[]{0.4, 0.4});
        }

        @Override
        public String attack(Job ally) {
            ally.hp -= this.dmg;
            return this.name + " attacked dealing " + this.dmg + " damage.";
        }

        @Override
        public String wait_and_see() {
            return this.name + " is watching and observing.";
        }

        @Override
        public String skill(Job ally, int index) {
            if (index == 1) {
                poisonSting(ally);
            }
            return this.name + " stung " + ally.name+" with its stinger";

        }
        private void poisonSting(Job ally){
            int sting_damage = dmg + (level * 5);
            ally.hp -= sting_damage;
        }


    }

    //Enemy class type "Suicide Rock"
    public static class SuicideRock extends Enemy implements EnemyActions{

        public SuicideRock() {
            super("Suicide Rock", 1,20, 100,100,  new double[]{0.4, 0.1});
        }

        @Override
        public String attack(Job ally) {
            throwRock(ally);
            return this.name + " threw an explosive rock at "+ ally.name;
        }

        @Override
        public String wait_and_see() {
            return this.name + " is watching and observing";
        }

        @Override
        public String skill(Job ally, int index) {
            if(index == 0){
                selfDestruct(ally);
            }
            return this.name + " blew itself up";
        }
        private void throwRock(Job ally){
            int damage = (int) (this.hp*0.25);
            ally.hp -= damage+10;
        }

        private void selfDestruct(Job ally){
            int damage = ally.hp / 2;
            ally.hp -= damage;
            this.hp = 0;
        }
        @Override
        public void level_up(Job job) {
            int plusLevel = rand.nextInt(3)-1;
            try{
                int num = job.getLevel();
                this.level = num + plusLevel;
            }catch(NullPointerException a){
                throw new NullPointerException("You need to choose a job!\nWould you like to go back?");
            }
            if(this.level == 0 || job.level == 1){
                this.level = 1;
                return;
            }
            improve_stats();
        }

        @Override
        public void improve_stats() {
            this.dmg += this.level * 3;
            this.maxHp += this.level * 5;
            this.hp = this.maxHp;
        }

        @Override
        public int give_exp() {
            return level * 5;
        }
    }

    //Enemy class type "Skeleton"
    public static class Skeleton extends Enemy implements EnemyActions{

        public Skeleton() {
            super("Skeleton", 1, 20, 80,80,  new double[]{0.4, 0.2});
        }

        @Override
        public String attack(Job ally) {
            int stab_damage = stab();
            ally.hp -= stab_damage;
            return this.name + " stabbed "+ ally.name + " with its sword";
        }

        @Override
        public String wait_and_see() {
            return this.name + " is watching and observing.";
        }

        @Override
        public String skill(Job ally, int index) {
            if(index == 0){
                heal();
            }
            return this.name+ " healed itself";
        }
        private int stab(){
            return dmg;
        }

        private void heal(){
            int max_heal = (int) (this.hp * 0.1);
            this.hp += max_heal;
        }
        @Override
        public void level_up(Job job) {
            int plusLevel = rand.nextInt(3)-1;
            try{
                int num = job.getLevel();
                this.level = num + plusLevel;
            }catch(NullPointerException a){
                throw new NullPointerException("You need to choose a job!\nWould you like to go back?");
            }
            if(this.level == 0 || job.level == 1){
                this.level = 1;
                return;
            }
            improve_stats();
        }

        @Override
        public void improve_stats() {
            this.dmg += this.level * 3;
            this.maxHp += this.level * 5;
            this.hp = this.maxHp;
        }

        @Override
        public int give_exp() {
            return level*5;
        }
    }

    //Enemy class type "Dark Stalker"
    public static class DarkStalker extends Enemy implements EnemyActions{

        public DarkStalker() {
            super("Dark Stalker",1,15,100,100,  new double[]{0.2, 0.8});
        }

        @Override
        public String attack(Job ally) {
            int a = darkSlash();
            ally.hp -= a;
            return this.name + " slashed "+ally.name+" with its dark sword";
        }

        @Override
        public String wait_and_see() {
            return this.name + " is watching and observing";

        }

        @Override
        public String skill(Job ally, int index) {
            if(index == 0){
                triple_slash(ally);
            }
            return this.name + " performed a triple slash at "+ ally.name;
        }

        private int darkSlash(){
            return dmg;
        }
        private void triple_slash(Job ally){
            int damage = (int) ((dmg*0.5)*3);
            ally.hp -= damage;
        }
        @Override
        public void level_up(Job job) {
            int plusLevel = rand.nextInt(3)-1;
            try{
                int num = job.getLevel();
                this.level = num + plusLevel;
            }catch(NullPointerException a){
                throw new NullPointerException("You need to choose a job!\nWould you like to go back?");
            }
            if(this.level == 0 || job.level == 1){
                this.level = 1;
                return;
            }
            improve_stats();
        }

        @Override
        public void improve_stats() {
            this.dmg += this.level * 3;
            this.maxHp += this.level * 5;
            this.hp = this.maxHp;
        }

        @Override
        public int give_exp() {
            return level * 10;
        }

    }
    //Enemy class type "Ancient Bishop"
    public static class AncientBishop extends Enemy implements EnemyActions{

        public AncientBishop() {
            super("Ancient Bishop",1,15,100,100, new double[]{0.2, 0.6});
        }

        @Override
        public String attack(Job ally) {
            int damage = holy_sword();
            ally.hp-=damage;
            return this.name + " used its sword to pierce " + ally.name;
        }

        @Override
        public String wait_and_see() {
            return this.name + " prays and observes";
        }

        @Override
        public  String skill(Job ally, int index) {
            if(index==0){
                heal(ally);
            }
            return this.name + " pierced " + ally.name + " with a beam";
        }

        private void heal(Job ally){
            int heal_power = 10;
            ally.hp -= heal_power;
            this.hp += heal_power;
        }

        private int holy_sword(){
            return dmg;
        }

        @Override
        public void level_up(Job job) {
            int plusLevel = rand.nextInt(3)-1;
            try{
                int num = job.getLevel();
                this.level = num + plusLevel;
            }catch(NullPointerException a){
                throw new NullPointerException("You need to choose a job!\nWould you like to go back?");
            }
            if(this.level == 0 || job.level == 1){
                this.level = 1;
                return;
            }
            improve_stats();
        }

        @Override
        public void improve_stats() {
            this.dmg += this.level * 3;
            this.maxHp += this.level * 5;
            this.hp = this.maxHp;
        }

        @Override
        public int give_exp() {
            return level * 10;
        }
    }
}
