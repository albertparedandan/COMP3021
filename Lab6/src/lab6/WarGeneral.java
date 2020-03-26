package lab6;

public class WarGeneral extends Minister {

    /**
     * Calls the superclass' constructor
     *
     * @param intelligence
     * @param experience
     * @param leadership
     */
    public WarGeneral(int intelligence, int experience, int leadership) {
        super(intelligence, experience, leadership);
    }

    /**
     * @param city to collect production points from
     * @return 1.5 times the amount collected by other types of minister
     */
    @Override
    public int collectProductionPoints(City city) {
        return Math.round(1.5f * ((city.getPopulation() + this.intelligence + this.leadership) * 0.2f * (city.getRoads() + 1)));
    }

    /**
     * Recruits more troops than superclass implementation,
     * troops added to city = 50 + round(leadership * 0.2).
     * <p>
     * Recruitment still costs 500 golds.
     * If the player does not have enough gold, simply do nothing and return.
     *
     * @param player
     * @param city
     */
    @Override
    public void recruitTroops(Player player, City city){
        if (player.getGold() < 500) return;
        player.addGold(-500);
        city.addTroops(50 + Math.round(this.leadership * 0.2f));
    }

    /**
     * Example string representation:
     * "WarGeneral | intelligence: 100 | experience: 100 | leadership: 100"
     *
     * @return string representation of this object
     */
    @Override
    public String toString() {
        String result = "WarGeneral | intelligence: " + this.intelligence + " | experience: " + this.experience + " | leadership: " + this.leadership;
        return result;
    }
}
