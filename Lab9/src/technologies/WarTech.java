package technologies;

public class WarTech extends Technology {

    /**
     * @return attack bonus equal to 1 + level * 0.5
     */
    @Override
    public double getAttackBonus() {
        return 1 + getLevel() * 0.5;
    }

    /**
     * @return String representing this object
     */
    @Override
    public String toString() {
        return String.format("WarTech (%d)   x%.1f attack", getLevel(), getAttackBonus());
    }
}
