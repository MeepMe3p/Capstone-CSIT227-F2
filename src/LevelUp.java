interface LevelUp{
    void gain_exp(int exp_amount);
    void level_up();
    void improve_stats();
}
interface Enemy_LevelUp{
    void level_up(Job job);
    void improve_stats();
    int give_exp(Job job);
}