public abstract class Enemy extends Character{

    public Enemy(String name, int level, int dmg, int hp) {
        super(name, level, dmg, hp);
    }

    public abstract void attack(Job ally);

    public abstract void wait_and_see();

    public static class Scorpion extends Enemy{

        public Scorpion(String name, int level, int dmg, int hp) {
            super(name, level, dmg, hp);
        }



        @Override
        public void attack(Job ally) {

        }

        @Override
        public void wait_and_see() {
            System.out.println(this.name + " is watching and observing");
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
            int a = selfDestruct();
        }

        @Override
        public void wait_and_see() {
            System.out.println(this.name + " is watching and observing");
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
        public int stab(){
            System.out.println(this.name + " stabbed you with his sword");
            return dmg;
        }
    }
    public static class DarkStalker extends Enemy{

        public DarkStalker(String name, int level, int dmg, int hp) {
            super(name, level, dmg, hp);
        }

        @Override
        public void attack(Job ally) {

        }

        @Override
        public void wait_and_see() {

        }
        public int darkSlash(){
            System.out.println(this.name + " attacked using his dark sword ");
            return dmg;
        }
    }
    public static class AncientBishop extends Enemy{

        public AncientBishop(String name, int level, int dmg, int hp) {
            super(name, level, dmg, hp);
        }

        @Override
        public void attack(Job ally) {

        }

        @Override
        public void wait_and_see() {
            System.out.println(this.name + " is watching and observing");
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
