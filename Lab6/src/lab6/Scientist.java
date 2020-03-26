package lab6;

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
     * @return tech discount rate equals to 1 - (intelligence + experience) / 1500
     */
    @Override
    public double getTechDiscountRate() {
        return (double)(1-(this.intelligence + this.experience))/1500;
    }

    /**
     * @param city to collect science points from
     * @return 1.5 times the amount collected by other types of minister
     */
    @Override
    public int collectSciencePoints(City city) {
        return Math.round(1.5f * ((city.getPopulation() + this.experience + this.intelligence) * 0.2f * (city.getUniversities() + 1)));
    }

    /**
     * Example string representation:
     * "Scientist | intelligence: 100 | experience: 100 | leadership: 100"
     *
     * @return string representation of this object
     */
    @Override
    public String toString() {
        String result = "Scientist | intelligence: " + this.intelligence + " | experience: " + this.experience + " | leadership: " + this.leadership;
        return result;
    }
}
