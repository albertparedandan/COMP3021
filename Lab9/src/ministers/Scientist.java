package ministers;


public class Scientist extends Minister {

    /**
     * Calls the superclass' constructor
     *
     * @param intelligence
     * @param experience
     * @param leadership
     */
    public Scientist(int intelligence, int experience, int leadership) {
        super(intelligence, experience, leadership);
    }

    /**
     * @return string representation of this object
     */
    @Override
    public String toString() {
        return String.format("Scientist   %d exp.   %d lead.   %d intel.   %s",
                getExperience(), getLeadership(), getIntelligence(),
                isReady() ? "READY" : "DONE"
        );
    }
}
