/**
 * This class is used for saving the details in order for the highscore class to get the details of the character
 * much more readable
 */
public class CharacterDetails {

    String name;
    int lvl;
    int dmg_dealt;
    int enemies_killed;
    int bosses_killed;

    public CharacterDetails(String name, int lvl, int dmg_dealt, int enemies_killed, int bosses_killed) {
        this.name = name;
        this.lvl = lvl;
        this.dmg_dealt = dmg_dealt;
        this.enemies_killed = enemies_killed;
        this.bosses_killed = bosses_killed;
    }

    @Override
    public String toString() {
        return " Job: "+name+" Level: "+lvl+" Damage Dealt: "+ dmg_dealt+" Enemies Killed: "+ enemies_killed+" Bosses Killed: "+bosses_killed;
    }
}
