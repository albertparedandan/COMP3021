package lab7;


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

}
