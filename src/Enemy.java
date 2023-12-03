public abstract class Enemy extends Character{

    public Enemy(String name, int level, int dmg, int hp) {
        super(name, level, dmg, hp);
    }

    public abstract void attack(Job ally);

    public abstract void wait_and_see();
    public abstract void skill(Job ally);

    public static class Scorpion extends Enemy{

        public Scorpion(String name, int level, int dmg, int hp) {
            super(name, level, dmg, hp);
        }



        @Override
        public void attack(Job ally) {
            //abot bitaw maybe kato nalang simple damage ra
        }

        @Override
        public void wait_and_see() {
            System.out.println(this.name + " is watching and observing");
        }

        @Override
        public void skill(Job ally) {
            //Kanang poison sting
            ally.hp -= this.dmg;
        }

        public int poisonSting(){
            System.out.println(this.name +" attacked with his poison stinger");
            return dmg;
        }
    }
    public static class SuicideRock extends Enemy{

        public SuicideRock(String name, int level, int dmg, int hp) {
            super(name, level, dmg, hp);
        }

        @Override
        public void attack(Job ally) {
            // damaging pero maminusan also iya hp... make another method
        }

        @Override
        public void wait_and_see() {
            System.out.println(this.name + " is watching and observing");
        }

        @Override
        public void skill(Job ally) {
            //self destruct 0 hp dretso iya life(huhu rip in pieces) nya half sa current hp ni "ally" ang dmg
        }


        private int selfDestruct(){
            System.out.println(this.name+ " blew himself up ");
            return dmg*2;
        }
    }
    public static class Skeleton extends Enemy{

        public Skeleton(String name, int level, int dmg, int hp) {
            super(name, level, dmg, hp);
        }

        @Override
        public void attack(Job ally) {

        }

        @Override
        public void wait_and_see() {
            System.out.println(this.name + " is watching and observing");
        }

        @Override
        public void skill(Job ally) {

        }

        public int stab(){
            System.out.println(this.name + " stabbed you with his sword");
            return dmg;
        }
    }
    public static class DarkStalker extends Enemy{

        public DarkStalker() {
            super("Dark Stalker",1,15,100);
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

        public AncientBishop(String name, int level, int dmg, int hp) {
            super(name, level, dmg, hp);
        }

        @Override
        public void attack(Job ally) {
            //muattack call ang holy sword basically madamagean ang ally/para,eters
        }

        @Override
        public void wait_and_see() {
            System.out.println(this.name + " is watching and observing");
        }

        @Override
        public void skill(Job ally) {
            //Basically, muheal rani sha sa iya self mucall lang shas heal haha or if palisod2 ta mutimes two iya max hp haha
        }

        public void heal(){
            int heal_power = 10;
            System.out.println(this.name + " healed and recovers "+ heal_power +" hp.");
            this.hp += heal_power;
        }
        public int holy_sword(){
            System.out.println(this.name + " attacked you with his holy sword");
            return dmg;
        }
    }
}
