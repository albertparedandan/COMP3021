package pa1.technologies;


import pa1.Cost;

public class ManufacturingTech extends Technology {

    /**
     * @return production bonus equal to 1 + current level * 0.5
     */
    @Override
    public double getProductionBonus() {
        return 1 + getLevel() * 0.5;
    }

    /**
     * Upgrade costs:
     * gold = production = (current level + 1) * 1000;
     * science = 0
     *
     * @return upgrade costs
     */
    @Override
    public Cost getUpgradeCost() {
        int amount = (getLevel() + 1) * 1000;
        return new Cost(amount, amount, 0);
    }

    /**
     * @return String representing this object
     */
    @Override
    public String toString() {
        return String.format(
                "ManufacturingTech (%d)   x%.1f production",
                getLevel(), getProductionBonus()
        );
    }
}
