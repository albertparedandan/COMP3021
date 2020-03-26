package technologies;


public class ManufacturingTech extends Technology {

    /**
     * @return production bonus equal to 1 + current level * 0.5
     */
    @Override
    public double getProductionBonus() {
        return 1 + getLevel() * 0.5;
    }

    /**
     * @return String representing this object
     */
    @Override
    public String toString() {
        return String.format("ManufacturingTech (%d)   x%.1f production", getLevel(), getProductionBonus());
    }
}
