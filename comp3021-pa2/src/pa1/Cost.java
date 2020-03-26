package pa1;


/**
 * An immutable class that encapsulates the gold, science and production costs.
 */

public class Cost {

    private final int gold, production, science;

    public Cost(int gold, int production, int science) {
        this.gold = gold;
        this.production = production;
        this.science = science;
    }

    public int getGold() {
        return gold;
    }

    public int getProduction() {
        return production;
    }

    public int getScience() {
        return science;
    }

    /**
     * Get a new Cost object with applied discount rate
     *
     * @param rate discount rate
     * @return Discounted Cost = round(rate * current cost)
     */
    public Cost getDiscountedCost(double rate) {
        int gold = (int) Math.round(rate * this.gold);
        int production = (int) Math.round(rate * this.production);
        int science = (int) Math.round(rate * this.science);
        return new Cost(gold, production, science);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cost cost = (Cost) o;
        return gold == cost.gold && production == cost.production &&
                science == cost.science;
    }
}
