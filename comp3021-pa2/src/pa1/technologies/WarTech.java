package pa1.technologies;


import pa1.Cost;

public class WarTech extends Technology {

    /**
     * @return attack bonus equal to 1 + level * 0.5
     */
    @Override
    public double getAttackBonus() {
        return 1 + getLevel() * 0.5;
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
        int amount = (getLevel() + 1) * 1000;
        return new Cost(amount, 0, amount);
    }

    /**
     * @return String representing this object
     */
    @Override
    public String toString() {
        return String.format(
                "WarTech (%d)   x%.1f attack",
                getLevel(), getAttackBonus()
        );
    }
}
