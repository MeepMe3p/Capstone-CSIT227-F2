public class Job extends Character {
    private int mana;
    private int stamina;
    private int armor;

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
        this.armor = armor;
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

    void wait_and_see(){
        System.out.println(" prepares for its enemies next move.");
    }

    public class Mage extends Job {
        public Mage(String name, int level, int dmg, int hp) {
            super(name, level, dmg, hp);
        }

        void concentrate(){
            int mana_val = 10;
            setMana(getMana() + mana_val);
            System.out.println(" concentrates and recovered" + mana_val + " mana!");
        }

        int fire_ball(){
            int dmg = 10;
            System.out.println(" casts a fire ball spell and deals" + dmg + " pts!");
            return dmg;
        }

        int lightning_bolt(){
            int dmg = 6;
            System.out.println(" casts a lightning bolt spell and deals" + dmg + " pts!");
            return dmg;
        }



    }

    public class Knight extends Job {

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

    }

    public class Priest extends Job {

        public Priest(String name, int level, int dmg, int hp, int healingPower) {
            super(name, level, dmg, hp);
        }

        void heal(){
            int heal_val = 10;
            // need setter
            System.out.println(" casts healing and has recovered" + heal_val + " hp!");
        }

        int light_ray(){
            int dmg = 8;
            System.out.println(" casts light and deals " + dmg + " pts!");
            return dmg;
        }

        int holy_smite(){
            int dmg = 16;
            System.out.println(" invokes Holy Smite and deals " + dmg + " pts!");
            return dmg;
        }
    }
}
