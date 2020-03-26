package pa1.technologies;

import pa1.Cost;

public class TradingTech extends Technology {

    /**
     * @return gold bonus equal to 1 + (level * 0.5);
     */
    @Override
    public double getGoldBonus() {
        return 1 + getLevel() * 0.5;
    }

    /**
     * Upgrade costs:
     * gold = production = science = (current level + 1) * 1000;
     *
     * @return upgrade costs
     */
    @Override
    public Cost getUpgradeCost() {
        int amount = (getLevel() + 1) * 1000;
        return new Cost(amount, amount, amount);
    }

    /**
     * @return String representing this object
     */
    @Override
    public String toString() {
        return String.format(
                "TradingTech (%d)   x%.1f gold",
                getLevel(), getGoldBonus()
        );
    }
}
