public abstract class Job extends Character {
    private int mana;
    private int stamina;
    private static int armor;

    public abstract void attack(Enemy enemy);
    public abstract void wait_and_see();
    public abstract void skill(Enemy enemy);

    public Job(String name, int level, int dmg, int hp) {
        super(name, level, dmg, hp);
        this.mana = 100;
        this.stamina = 100;
    }

    // Setters
    public void setMana(int mana) {
        // limits mana up to 100
        this.mana = Math.min(mana, 100);
    }

    public void setStamina(int stamina) {
        // limits stamina up to 100
        this.stamina = Math.min(stamina, 100);
    }

    public void setArmor(int armor) {
        Job.armor = armor;
    }

    // Getters
    public int getMana() {
        return mana;
    }

    public int getStamina() {
        return stamina;
    }

    public int getArmor() {
        return armor;
    }

//    public void wait_and_see(){
//        System.out.println(" prepares for its enemies next move.");
//    }

    public static class Mage extends Job {


        @Override
        public void attack(Enemy e) {
            int damage = lightning_bolt();
            e.hp-=damage;
        }

        @Override
        public void wait_and_see() {
            System.out.println(" prepares for its enemies next move.");
        }

        @Override
        public void skill(Enemy e) {
            int damage = fire_ball();
            e.hp-=damage;
        }

        public Mage(String name, int level, int dmg, int hp) {
            super(name, level, dmg, hp);
        }

        void concentrate(){
            int mana_val = 10;
            setMana(getMana() + mana_val);
            System.out.println(" concentrates and recovered" + mana_val + " mana!");
        }

        int fire_ball(){
            System.out.println(" casts a fire ball spell and deals" + dmg + " pts!");
            return dmg;
        }

        int lightning_bolt(){
            System.out.println(" casts a lightning bolt spell and deals" + dmg + " pts!");
            return dmg;
        }
    }

    public static class Knight extends Job {

        public Knight(String name, int level, int dmg, int hp, int armor) {
            super(name, level, dmg, hp);
        }

        void shield(){
            int armor_val = 10;
            setArmor(getArmor() + armor_val);
            System.out.println(" used shield and now has " + armor + " armor!");
        }

        int slash(){
            int dmg = 8;
            System.out.println(" slashed the enemy and dealt " + dmg + " pts!");
            return dmg;
        }

        int dual_slash(){
            int dmg = 16;
            System.out.println(" slashed the enemy twice and dealt " + dmg + " pts!");
            return dmg;
        }

        @Override
        public void attack(Enemy enemy) {
            int slash_damage = slash();
            enemy.hp -= slash_damage;
        }

        @Override
        public void wait_and_see() {
            System.out.println(this.name + " is watching and observing.");
        }

        @Override
        public void skill(Enemy enemy) {
            int dualSlash_damage = dual_slash();
            enemy.hp -= dualSlash_damage;
        }
    }

    public static class Priest extends Job {

        public Priest(String name, int level, int dmg, int hp) {
            super("Priest",1,15,120);
        }

        void heal(){
            int heal_val = 10;
            // need setter
        }

        int light_ray(){
//            int dmg = 8;
            System.out.println(" casts light and deals " + this.dmg + " pts!");
            return this.dmg;
        }

        int holy_smite(){
//            int dmg = 16;
            System.out.println(" invokes Holy Smite and deals " + this.dmg + " pts!");
            int healingPower = 10;
            System.out.println(" casts healing and has recovered" + healingPower + " hp!");
            this.hp += healingPower;

            return this.dmg;
        }

        @Override
        public void attack(Enemy enemy) {
             int a = light_ray();
             enemy.hp -= a;
        }

        @Override
        public void wait_and_see() {
            System.out.println(this.name + " is watching and observing");
        }

        @Override
        public void skill(Enemy enemy) {
            int a = holy_smite();
            enemy.hp -= a;
        }
    }
}
