package pa1.technologies;

import pa1.Cost;

/**
 * An abstract class that represents in-game technology
 */
public abstract class Technology {

    private int level;

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

    /**
     * Adds level by one
     */
    public void addLevel() {
        level++;
    }

    public int getLevel() {
        return level;
    }

    /**
     * @return upgrade cost of technology
     */
    public abstract Cost getUpgradeCost();
}
