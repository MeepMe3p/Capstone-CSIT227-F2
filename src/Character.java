public abstract class Character {
    String name;
    int level;
    int dmg;
    int hp;


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

    public Character(String name, int level, int dmg, int hp) {
        this.name = name;
        this.level = level;
        this.dmg = dmg;
        this.hp = hp;
    }

    @Override
    public String toString() {
        return name;
    }
}
