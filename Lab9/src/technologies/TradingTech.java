package technologies;

public class TradingTech extends Technology {

    /**
     * @return gold bonus equal to 1 + (level * 0.5);
     */
    @Override
    public double getGoldBonus() {
        return 1 + getLevel() * 0.5;
    }

    /**
     * @return String representing this object
     */
    @Override
    public String toString() {
        return String.format("TradingTech (%d)   x%.1f gold", getLevel(), getGoldBonus());
    }
}
