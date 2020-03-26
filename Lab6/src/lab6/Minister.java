package lab6;


/**
 * An abstract class that represents a minister in the game.
 * All actions in the game are done through ministers.
 * Therefore this class will contain the bulk of your implementation of the game.
 */
public abstract class Minister {

    // Attributes
    protected final int intelligence, experience, leadership;
//    private boolean isReady = false;

    /**
     * Initializes the attributes of a minister
     *
     * @param intelligence
     * @param experience
     * @param leadership
     */
    protected Minister(int intelligence, int experience, int leadership) {
        this.intelligence = intelligence;
        this.experience = experience;
        this.leadership = leadership;
    }

//    /**
//     * @return Whether or not the minister is ready
//     */
//    public boolean isReady() {
//        return isReady;
//    }

    /**
     * Minister by default does not provide improvement discount rate
     * therefore this method always returns 1.
     * This behavior may be overridden in a sub-class
     *
     * @return 1
     */
    public double getImprovementDiscountRate() {
        return 1;
    }

    /**
     * Minister by default does not provide tech discount rate
     * therefore this method always returns 1.
     * This behavior may be overridden in a sub-class
     *
     * @return 1
     */
    public double getTechDiscountRate() {
        return 1;
    }

    /**
     * Collect gold from a city
     * amount collected = (city population + minister experience + minister leadership) * (# of banks  + 1) * 0.2
     *
     * @param city to collect gold from
     * @return amount of gold collected
     */
    public int collectTax(City city) {
        return Math.round((city.getPopulation() + experience + leadership) * 0.2f * (city.getBanks() + 1));
    }

    /**
     * Collect science points from a city
     * amount collected = (city population + minister experience + minister intelligence) * (# of universities  + 1) * 0.2
     *
     * @param city to collect science points from
     * @return amount of science points collected
     */
    public int collectSciencePoints(City city) {
        
        return Math.round((city.getPopulation() + experience + intelligence) * 0.2f * (city.getUniversities() + 1));
    }

    /**
     * Collect production points from a city
     * amount collected = (city population + minister intelligence + minister leadership) * (# of roads  + 1) * 0.2
     *
     * @param city to collect production points from
     * @return amount of production points collected
     */
    public int collectProductionPoints(City city) {
        return Math.round((city.getPopulation() + intelligence + leadership) * 0.2f * (city.getRoads() + 1));
    }

    /**
     * Recruit troops to be stationed in a city
     * Recruit 50 troops for 500 golds
     * If the player does not have enough gold, simply do nothing and return
     * Overridden in WarGeneral class
     *
     * @param player
     * @param city
     */
    public void recruitTroops(Player player, City city){
        if (player.getGold() < 500) return;
        player.addGold(-500);
        city.addTroops(50);
    }
}


