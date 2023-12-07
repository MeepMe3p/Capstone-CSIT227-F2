import java.util.Random;

public abstract class Enemy extends Character implements Enemy_LevelUp{
    Random rand = new Random();


    public Enemy(String name, int level, int dmg, int hp,int maxhp) {
        super(name, level, dmg, hp,maxhp);
    }

    public static class Scorpion extends Enemy implements EnemyActions{

        public Scorpion() {
            super("Scorpion", 1, 20, 100,100);
        }

        @Override
        public void attack(Job ally) {
            //abot bitaw maybe kato nalang simple damage ra
            ally.hp -= this.dmg;
            System.out.println(this.name + " attacked dealing with " + this.dmg + " damage.");
        }

        @Override
        public void wait_and_see() {
            System.out.println(this.name + " is watching and observing.");
        }

        @Override
        public void skill(Job ally, int index) {
            if (index == 1) {
                poisonSting(ally);
            }
        }
        private void poisonSting(Job ally){
            int sting_damage = dmg + (level * 5);
            ally.hp -= sting_damage;
            System.out.println(this.name + " attacked with his poison stinger and dealt " + sting_damage + " damage");
        }

        @Override
        public void level_up(Job job) {
            int plusLevel = rand.nextInt(3)-1;
            try{
                int num = job.getLevel();
                this.level = num + plusLevel;
            }catch(NullPointerException a){
                throw new NullPointerException("You need to get a job XD");
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
            this.hp = this.level * 5;
        }

        @Override
        public int give_exp(Job job) {
            return 0;
        }
    }

    public static class SuicideRock extends Enemy implements EnemyActions{

        public SuicideRock() {
            super("Suicide Rock", 1,20, 100,100);
        }

        @Override
        public void attack(Job ally) {
            // damaging pero maminusan also iya hp... make another method
            throwRock(ally);
        }

        @Override
        public void wait_and_see() {
            System.out.println(this.name + " is watching and observing");
        }

        @Override
        public void skill(Job ally, int index) {
            if(index == 0){
                selfDestruct(ally);
            }


        }
        private void throwRock(Job ally){
            int damage = (int) ((int)this.hp*0.25);
            ally.hp -= damage+10;
            System.out.println(this.name + " threw an exploding rock and dealt " + damage + " damage");
        }

        //self destruct 0 hp dretso iya life(huhu rip in pieces) nya half sa current hp ni "ally" ang dmg
        private void selfDestruct(Job ally){
            int damage = ally.hp / 2;
            ally.hp -= damage;
            this.hp = 0;
            System.out.println(this.name+ " blew himself up and dealt " + damage + " damage!");;
        }
        @Override
        public void level_up(Job job) {
            int plusLevel = rand.nextInt(3)-1;
            try{
                int num = job.getLevel();
                this.level = num + plusLevel;
            }catch(NullPointerException a){
                throw new NullPointerException("You need to get a job XD");
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
            this.hp = this.level * 5;
        }

        @Override
        public int give_exp(Job job) {
            return 0;
        }
    }

    public static class Skeleton extends Enemy implements EnemyActions{

        public Skeleton() {
            super("Skeleton", 1, 20, 80,80);
        }

        @Override
        public void attack(Job ally) {
            int stab_damage = stab();
            ally.hp -= this.dmg;
            System.out.println(this.name + " stabbed you with his sword dealing with " + stab_damage + " damage");
        }

        @Override
        public void wait_and_see() {
            System.out.println(this.name + " is watching and observing.");
        }

        @Override
        public void skill(Job ally, int index) {
            if(index == 0){
                heal();
            }

        }
        private int stab(){
            System.out.println(this.name + " stabbed you with his sword.");
            return dmg;
        }

        private void heal(){
            int max_heal = this.hp * 2;
            System.out.println(this.name + "ate some calcium and recovers " + max_heal + " hp.");
            this.hp += max_heal;
        }
        @Override
        public void level_up(Job job) {
            int plusLevel = rand.nextInt(3)-1;
            try{
                int num = job.getLevel();
                this.level = num + plusLevel;
            }catch(NullPointerException a){
                throw new NullPointerException("You need to get a job XD");
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
            this.hp = this.level * 5;
        }

        @Override
        public int give_exp(Job job) {
            return 0;
        }
    }

    public static class DarkStalker extends Enemy implements EnemyActions{

        public DarkStalker() {
            super("Dark Stalker",1,15,100,100);
        }

        @Override
        public void attack(Job ally) {
            //muattack call ang dark ssword basically madamagean ang ally
            int a = darkSlash();
            ally.hp -= this.dmg;
            System.out.println(this.name + " slashed you with a dark sword dealing" + a+ " damage");


        }

        @Override
        public void wait_and_see() {
            //limot ko butang haha same ra shas uban
            System.out.println(this.name + " is watching and observing");

        }

        @Override
        public void skill(Job ally, int index) {
            if(index == 0){
                triple_slash(ally);
            }

        }

        private int darkSlash(){
            System.out.println(this.name + " attacked using his dark sword ");
            return dmg;
        }

        //gahuna2 pakog skill ani ni dark stalker haha ako gihuna2 kay like triple attack nalang guro haha
        private void triple_slash(Job ally){
            int damage = (int) ((dmg*0.5)*3);
            ally.hp -= damage;

            System.out.println(this.name + "perfomed a triple slash dealing"+ damage+ " dmg");

        }
        @Override
        public void level_up(Job job) {
            int plusLevel = rand.nextInt(3)-1;
            try{
                int num = job.getLevel();
                this.level = num + plusLevel;
            }catch(NullPointerException a){
                throw new NullPointerException("You need to get a job XD");
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
            this.hp = this.level * 5;
        }

        @Override
        public int give_exp(Job job) {
            return 0;
        }

    }
    public static class AncientBishop extends Enemy implements EnemyActions{

        public AncientBishop() {
            super("Ancient Bishop",1,15,100,100);
        }

        @Override
        public void attack(Job ally) {
            //muattack call ang holy sword basically madamagean ang ally/para,eters
            int damage = holy_sword();
            ally.hp-=damage;
        }

        @Override
        public void wait_and_see() {
            System.out.println(this.name + " is watching and observing");
        }

        @Override
        public void skill(Job ally, int index) {
            if(index==0){
                heal(ally);
            }
        }

        //Basically, muheal rani sha sa iya self mucall lang shas heal haha or if palisod2 ta mutimes two iya max hp haha
        private void heal(Job ally){
            int heal_power = 10;
            ally.hp -= heal_power;
            System.out.println(this.name + " healed and dealt "+ heal_power +" hp.");
            this.hp += heal_power;
        }

        private int holy_sword(){
            System.out.println(this.name + " attacked you with his holy sword");
            return dmg;
        }
        @Override
        public void level_up(Job job) {
            int plusLevel = rand.nextInt(3)-1;
            try{
                int num = job.getLevel();
                this.level = num + plusLevel;
            }catch(NullPointerException a){
                throw new NullPointerException("You need to get a job XD");
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
            this.hp = this.level * 5;
        }

        @Override
        public int give_exp(Job job) {
            return 0;
        }
    }
}
