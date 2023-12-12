

public abstract class Job extends Character implements LevelUp {
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


    public Job(String name, int level, int dmg, int hp, int maxHp,int exp, int exp_points) {
        super(name, level, dmg, hp,maxHp);
        this.exp = exp;
        this.exp_points = exp_points;
    }






    public static class Mage extends Job implements MusicPlayer{

        public Mage(String name, int level, int dmg, int hp, int maxHp, int exp, int exp_points) {
            super(name, level, dmg, hp, maxHp, exp, exp_points);
        }

        public Mage() {
            super("Mage",1,7,75,75,20,0);
        }

        @Override
        public String attack(Enemy e) {
            MusicPlayer.startEffect("src\\Sounds\\thunder.wav");
            int damage = lightning_bolt();
            e.hp-=damage;
            return this.name + " shocked "+ e.name +" with a lightning bolt";
        }

        @Override
        public String wait_and_see() {
            MusicPlayer.startEffect("src\\Sounds\\wait.wav");
            return this.name + " is watching and observing";
        }

        @Override
        public String skill(Enemy e) {
            MusicPlayer.startEffect("src\\Sounds\\fire.wav");
            int damage = fire_ball();
            e.hp-=damage;
            return this.name + "  casts a fire ball spell to "+e.name;
        }


        int fire_ball(){
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
            this.setExp_points(this.getExp_points() + exp_amount);
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
            this.maxHp += 10;
            this.hp = this.maxHp;
        }
    }

    public static class Knight extends Job{

        public Knight() {
            super("Knight",1,8,250,250,20,0);
        }

        public Knight(String name, int level, int dmg, int hp, int maxHp, int exp, int exp_points) {
            super(name, level, dmg, hp, maxHp, exp, exp_points);
        }

        int slash(){

//            System.out.println(" slashed the enemy and dealt " + dmg + " pts!");
            setTotal_dmg(dmg);

            return dmg;
        }

        int dual_slash(){
//            System.out.println(" slashed the enemy twice and dealt " + dmg + " pts!");
            setTotal_dmg(dmg*2);
            MusicPlayer.startEffect("src\\Sounds\\double-slash.wav");
            return dmg*2;
        }

        @Override
        public String attack(Enemy enemy) {
            int slash_damage = slash();
            enemy.hp -= slash_damage;
            MusicPlayer.startEffect("src\\Sounds\\slash.wav");
            return this.name + " slashed "+enemy.name+" with his sword";
        }

        @Override
        public String wait_and_see() {
            MusicPlayer.startEffect("src\\Sounds\\wait.wav");
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
            this.setExp_points(this.getExp_points() + exp_amount);
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
            this.maxHp += 10;
            this.hp = this.maxHp;
        }
    }

    public static class Priest extends Job {
        public Priest() {
            super("Priest",1,15,120,120,20,0);
        }

        public Priest(String name, int level, int dmg, int hp, int maxHp, int exp, int exp_points) {
            super(name, level, dmg, hp, maxHp, exp, exp_points);
        }


        int light_ray(){
            setTotal_dmg(dmg);
            return this.dmg;
        }

        int holy_smite(){
            int healingPower = 10;
            this.hp += healingPower;
            setTotal_dmg(dmg);

            return this.dmg;
        }

        @Override
        public String attack(Enemy enemy) {
             int a = light_ray();
             enemy.hp -= a;
             MusicPlayer.startEffect("src\\Sounds\\heal1.wav");
             return this.name + " pierced "+enemy.name+" with a light ray";
        }

        @Override
        public String wait_and_see() {
            MusicPlayer.startEffect("src\\Sounds\\wait.wav");
            return this.name + " is watching and observing";
        }

        @Override
        public String skill(Enemy enemy) {
            int a = holy_smite();
            enemy.hp -= a;
            //haha smote ang past tense sa smite??
            MusicPlayer.startEffect("src\\Sounds\\heal1.wav");
            return this.name+" smote "+enemy.name+" and healed himself";
        }
        @Override
        public void gain_exp(int exp_amount) {
            this.setExp_points(this.getExp_points() + exp_amount);
//            System.out.println("exp needed "+getExp()+" "+getExp_points()+"aaaaaaa");

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
            this.dmg +=  level + 5;
            this.maxHp += maxHp + 10;
            this.hp = this.maxHp;
        }
    }
}
