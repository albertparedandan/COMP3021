package technologies;


/**
 * An abstract class that represents in-game technology
 */
public abstract class Technology {

    private int level = 0;

    /**
     * By default provide no bonus, so returns 1
     *
     * @return 1
     */
    public double getAttackBonus() {
        return 1;
    }

    /**
     * By default provide no bonus, so returns 1
     *
     * @return 1
     */
    public double getGoldBonus() {
        return 1;
    }

    /**
     * By default provide no bonus, so returns 1
     *
     * @return 1
     */
    public double getProductionBonus() {
        return 1;
    }

    public int getLevel() {
        return level;
    }

}
