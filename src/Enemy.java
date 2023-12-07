public abstract class Enemy extends Character{

    public Enemy(String name, int level, int dmg, int hp,int maxhp) {
        super(name, level, dmg, hp,maxhp);
    }

    public abstract void attack(Job ally);

    public abstract void wait_and_see();
    public abstract void skill(Job ally);

    public static class Scorpion extends Enemy{

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
        public void skill(Job ally) {
            //Kanang poison sting
            int sting_damage = poisonSting();
            System.out.println(" attacked with his poison stinger dealing with " + sting_damage + " damage.");
        }

        private int poisonSting(){
            System.out.println(this.name + " attacked with his poison stinger.");
            return dmg + (level * 5);
        }
    }

    public static class SuicideRock extends Enemy{

        public SuicideRock() {
            super("Suicide Rock", 1,20, 100,100);
        }

        @Override
        public void attack(Job ally) {
            // damaging pero maminusan also iya hp... make another method
            ally.hp-=throwRock();
        }

        @Override
        public void wait_and_see() {
            System.out.println(this.name + " is watching and observing");
        }

        @Override
        public void skill(Job ally) {
            //self destruct 0 hp dretso iya life(huhu rip in pieces) nya half sa current hp ni "ally" ang dmg
            this.selfDestruct();
            ally.hp*=0.5;
        }

        private int throwRock(){
            int damage = (int) ((int)this.hp*0.25);
            this.hp-=damage+10;
            System.out.println(this.name + " threw an exploding rock and dealt " + this.dmg + " damage");
            return damage;
        }
        private void selfDestruct(){
            this.hp = 0;
            System.out.println(this.name+ " blew himself up ");;
        }
    }

    public static class Skeleton extends Enemy{

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
        public void skill(Job ally) {
            heal();
        }

        private int stab(){
            System.out.println(this.name + " stabbed you with his sword.");
            return dmg;
        }

        private void heal(){
            int max_heal = this.hp * 2;
            System.out.println(this.name + " healed and recovers " + max_heal + " hp.");
            this.hp += max_heal;
        }
    }

    public static class DarkStalker extends Enemy{

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
        public void skill(Job ally) {
            //gahuna2 pakog skill ani ni dark stalker haha ako gihuna2 kay like triple attack nalang guro haha
            int damage = triple_slash();
            System.out.println(this.name + "perfomed a triple slash dealing"+ damage+ " dmg");
            ally.hp -= damage;
        }

        private int darkSlash(){
            System.out.println(this.name + " attacked using his dark sword ");
            return dmg;
        }
        private int triple_slash(){
            return (int) ((dmg*0.5)*3);
        }

    }
    public static class AncientBishop extends Enemy{

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
        public void skill(Job ally) {
            //Basically, muheal rani sha sa iya self mucall lang shas heal haha or if palisod2 ta mutimes two iya max hp haha
            heal();
            ally.hp-=10;
        }

        private void heal(){
            int heal_power = 10;
            System.out.println(this.name + " healed and recovers "+ heal_power +" hp.");
            this.hp += heal_power;
        }
        private int holy_sword(){
            System.out.println(this.name + " attacked you with his holy sword");
            return dmg;
        }
    }
}
