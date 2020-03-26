package lab6;


public class Economist extends Minister {

    /**
     * Calls the superclass' constructor
     *
     * @param intelligence
     * @param experience
     * @param leadership
     */
    public Economist(int intelligence, int experience, int leadership) {
        super(intelligence, experience, leadership);
    }

    /**
     * @return improvement discount rate equals to 1 - (intelligence + experience) / 1500
     */
    @Override
    public double getImprovementDiscountRate() {
        return (double)(1-(this.intelligence + this.experience))/1500;
    }

    /**
     * @param city to collect gold from
     * @return 1.5 times the amount collected by other types of minister
     */
    @Override
    public int collectTax(City city) {
        return Math.round(1.5f * ((city.getPopulation() + this.experience + this.leadership) * 0.2f * (city.getBanks() + 1)));
    }

    /**
     * Example string representation:
     * "Economist | intelligence: 100 | experience: 100 | leadership: 100"
     *
     * @return string representation of this object
     */
    @Override
    public String toString() {
        String result = "Economist | intelligence: " + this.intelligence + " | experience: " + this.experience + " | leadership: " + this.leadership;
        return result;
    }
}
