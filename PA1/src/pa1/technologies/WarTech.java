package pa1.technologies;


import pa1.Cost;

public class WarTech extends Technology {

    /**
     * @return attack bonus equal to 1 + level * 0.5
     */
    @Override
    public double getAttackBonus() {
        // TODO
        return (1 + (this.getLevel() * 0.5));
    }

    /**
     * Upgrade costs:
     * gold = science = (current level + 1) * 1000;
     * production = 0
     *
     * @return upgrade costs
     */
    @Override
    public Cost getUpgradeCost() {
        // TODO
        int costs = (this.getLevel() + 1) * 1000;
        Cost temp = new Cost(costs, 0, costs);
        return temp;
    }

    /**
     * Example string representation:
     * "WarTech | level: 1 | attack bonus: 1.50"
     *
     * @return String representing this object
     */
    @Override
    public String toString() {
        // TODO
        return String.format("WarTech | level: %d | attack bonus: %.2f", this.getLevel(), this.getAttackBonus());
    }
}
