public abstract class Job extends Character implements LevelUp {
    private int mana;
    private int stamina;
    private static int armor;

    private int exp;
    private int exp_points;
    private int total_dmg;

    public int getTotal_dmg() {
        return total_dmg;
    }

    public void setTotal_dmg(int total_dmg) {
        this.total_dmg = total_dmg;
    }

    public abstract String attack(Enemy enemy);
    public abstract String wait_and_see();
    public abstract String skill(Enemy enemy);

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp += exp;
    }

    public int getExp_points() {
        return exp_points;
    }

    public void setExp_points(int exp_points) {
        this.exp_points = exp_points;
    }

    public Job(String name, int level, int dmg, int hp, int maxhp,int exp, int exp_points) {
        super(name, level, dmg, hp,maxhp);
        this.exp = exp;
        this.exp_points = exp_points;
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



    public static class Mage extends Job {


        @Override
        public String attack(Enemy e) {
            int damage = lightning_bolt();
            e.hp-=damage;
            return this.name + " shocked "+ e.name +" with a lightning bolt";
        }

        @Override
        public String wait_and_see() {
            return this.name + " is watching and observing";
        }

        @Override
        public String skill(Enemy e) {
            int damage = fire_ball();
            e.hp-=damage;
            return this.name + " lauched a fireball to "+e.name;
        }

        public Mage() {
            super("Mage",1,7,75,75,20,0);
        }

        void concentrate(){
            int mana_val = 10;
            setMana(getMana() + mana_val);
//            System.out.println(" concentrates and recovered" + mana_val + " mana!");
        }

        int fire_ball(){
//            System.out.println(" casts a fire ball spell and deals" + dmg + " pts!");
            setTotal_dmg(dmg);
            return dmg;
        }

        int lightning_bolt(){
//            System.out.println(" casts a lightning bolt spell and deals" + dmg + " pts!");
            setTotal_dmg(dmg);
            return dmg;
        }

        @Override
        public void gain_exp(int exp_amount) {
            this.setExp_points(this.getExp() + exp_amount);
            if(this.getExp_points() >= this.getExp()){
                this.setExp(10);
                this.setExp_points(0);
                this.level_up();
                System.out.println(this.name + " leveled up");
            }
        }

        @Override
        public void level_up() {
            level++;
            improve_stats();
        }

        @Override
        public void improve_stats() {
            this.dmg +=  5;
            this.maxhp += 10;
            this.hp = this.maxhp;
        }
    }

    public static class Knight extends Job{

        public Knight() {
            super("Knight",1,8,250,250,20,0);
        }

        void shield(){
            int armor_val = 10;
            setArmor(getArmor() + armor_val);
//            System.out.println(" used shield and now has " + armor + " armor!");
        }

        int slash(){

//            System.out.println(" slashed the enemy and dealt " + dmg + " pts!");
            setTotal_dmg(dmg);
            return dmg;
        }

        int dual_slash(){
//            System.out.println(" slashed the enemy twice and dealt " + dmg + " pts!");
            setTotal_dmg(dmg*2);

            return dmg*2;
        }

        @Override
        public String attack(Enemy enemy) {
            int slash_damage = slash();
            enemy.hp -= slash_damage;
            return this.name + " slashed "+enemy.name+" with his sword";
        }

        @Override
        public String wait_and_see() {
            return this.name + " is watching and observing";

        }

        @Override
        public String skill(Enemy enemy) {
            int dualSlash_damage = dual_slash();
            enemy.hp -= dualSlash_damage;
            return this.name + " performed a dual slash to "+ enemy.name;
        }
        @Override
        public void gain_exp(int exp_amount) {
            this.setExp_points(this.getExp() + exp_amount);
            if(this.getExp_points() >= this.getExp()){
                this.setExp(10);
                this.setExp_points(0);
                this.level_up();
                System.out.println(this.name + " leveled up");

            }
        }

        @Override
        public void level_up() {
            level++;
            improve_stats();
        }

        @Override
        public void improve_stats() {
            this.dmg +=  5;
            this.maxhp += 10;
            this.hp = this.maxhp;
        }
    }

    public static class Priest extends Job {

        public Priest() {
            super("Priest",1,15,120,120,20,0);
        }

        void heal(){
            int heal_val = 10;
            // need setter
        }

        int light_ray(){
//            int dmg = 8;
//            System.out.println(" casts light and deals " + this.dmg + " pts!");
            setTotal_dmg(dmg);
            return this.dmg;
        }

        int holy_smite(){
//            int dmg = 16;
//            System.out.println(" invokes Holy Smite and deals " + this.dmg + " pts!");
            int healingPower = 10;
//            System.out.println(" casts healing and has recovered" + healingPower + " hp!");
            this.hp += healingPower;
            setTotal_dmg(dmg);

            return this.dmg;
        }

        @Override
        public String attack(Enemy enemy) {
             int a = light_ray();
             enemy.hp -= a;
             return this.name + " pierced "+enemy.name+" with a light ray";
        }

        @Override
        public String wait_and_see() {
            return this.name + " is watching and observing";
        }

        @Override
        public String skill(Enemy enemy) {
            int a = holy_smite();
            enemy.hp -= a;
            //haha smote ang past tense sa smite??
            return this.name+" smote "+enemy.name+" and healed himself";
        }
        @Override
        public void gain_exp(int exp_amount) {
            this.setExp_points(this.getExp() + exp_amount);
            if(this.getExp_points() >= this.getExp()){
                this.setExp(10);
                this.setExp_points(0);
                this.level_up();
                System.out.println(this.name + " leveled up");
            }
        }

        @Override
        public void level_up() {
            level++;
            improve_stats();
        }

        @Override
        public void improve_stats() {
            this.dmg +=  5;
            this.maxhp += 10;
            this.hp = this.maxhp;
        }
    }
}
