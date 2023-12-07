public abstract class Character {
    String name;
    int level;
    int dmg;
    int hp;
    int maxhp;
    public Character(String name, int level, int dmg, int hp, int maxhp) {
        this.name = name;
        this.level = level;
        this.dmg = dmg;
        this.hp = hp;
        this.maxhp = maxhp;
    }

    @Override
    public String toString() {
        return name;
    }
}
