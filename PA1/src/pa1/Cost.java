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
        // TODO
        Cost temp = new Cost(Math.round((float)rate * this.gold), Math.round((float)rate * this.production), this.science);
        return temp;
    }

    /**
     * Two Cost objects are equal if their
     * gold costs AND production costs AND science costs
     * are equal
     * <p>
     * Return false if obj is not an instance of Cost
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        // TODO
        if (obj.getClass() == Cost.class) {

        }
        return false;
    }
}
