public interface EnemyActions {
    String attack(Job ally);

    String wait_and_see();

    String skill(Job ally, int skillIndex);
}
