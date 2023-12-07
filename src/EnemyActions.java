public interface EnemyActions {
    void attack(Job ally);

    void wait_and_see();

    void skill(Job ally, int skillIndex);
}
