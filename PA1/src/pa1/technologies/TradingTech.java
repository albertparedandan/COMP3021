package pa1.technologies;

import pa1.Cost;

public class TradingTech extends Technology {

    /**
     * @return gold bonus equal to 1 + (level * 0.5);
     */
    @Override
    public double getGoldBonus() {
        // TODO
        return 1 + (this.getLevel() * 0.5);
    }

    /**
     * Upgrade costs:
     * gold = production = science = (current level + 1) * 1000;
     *
     * @return upgrade costs
     */
    @Override
    public Cost getUpgradeCost() {
        // TODO
        int costs = (this.getLevel() + 1) * 1000;
        Cost temp = new Cost(costs, costs, costs);
        return temp;
    }

    /**
     * Example string representation:
     * "TradingTech | level: 1 | gold bonus: 1.50"
     *
     * @return String representing this object
     */
    @Override
    public String toString() {
        // TODO
        return String.format("TradingTech | level: %d | gold bonus: %.2f", this.getLevel(), this.getGoldBonus());
    }
}
