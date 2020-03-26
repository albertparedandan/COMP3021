package ministers;

public class Economist extends Minister {

    /**
     * Call the superclass' constructor
     * @param intelligence
     * @param experience
     * @param leadership
     */
    public Economist(int intelligence, int experience, int leadership) {
        super(intelligence, experience, leadership);
    }

    /**
     * @return string representation of this object
     */
    @Override
    public String toString() {
        return String.format("Economist   %d exp.   %d lead.   %d intel.   %s",
                getExperience(), getLeadership(), getIntelligence(),
                isReady() ? "READY" : "DONE"
        );
    }
}
