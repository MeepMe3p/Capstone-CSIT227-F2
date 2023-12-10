public abstract class Character {
    String name;
    int level;
    int dmg;
    int hp;
    int maxHp;

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getDmg() {
        return dmg;
    }

    public int getHp() {
        return hp;
    }

    public Character(String name, int level, int dmg, int hp, int maxHp) {
        this.name = name;
        this.level = level;
        this.dmg = dmg;
        this.hp = hp;
        this.maxHp = maxHp;
    }

    @Override
    public String toString() {
        return name;
    }

    public boolean isAlive(){
        return hp >= 0;
    }
}
