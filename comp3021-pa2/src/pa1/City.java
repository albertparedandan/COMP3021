package pa1;

import javafx.scene.image.Image;

/**
 * A class that represents a city in the game
 */
public class City {
    // Metadata
    private final int id;
    private final String name;

    // Attributes
    private int population;
    private int troops;
    private int cropYields;

    // Improvements
    private int banks;
    private int roads;
    private int universities;


    public City(int id, String name, int population, int troops, int cropYields) {
        this.id = id;
        this.name = name;
        this.population = population;
        this.troops = troops;
        this.cropYields = cropYields;
    }

    /**
     * Adds number of banks by one
     */
    public void addBank() {
        ++banks;
    }

    /**
     * Adds number of roads by one
     */
    public void addRoad() {
        ++roads;
    }

    /**
     * Adds number of universities by one
     */
    public void addUniversity() {
        ++universities;
    }

    /**
     * Adds number of troops by specified increment.
     * If the increment is negative, invoke decreaseTroops() within this method instead.
     *
     * @param increment
     */
    public void addTroops(int increment) {
        if (increment < 0) decreaseTroops(-increment);
        else troops += increment;
    }

    /**
     * Decreases number of troops by the amount specified
     * Caps the number of troops at 0
     *
     * @param decrement
     */
    public void decreaseTroops(int decrement) {
        troops = Math.max(troops - decrement, 0);
    }

    /**
     * Add to crop yields the amount specified
     *
     * @param addition
     */
    public void improveCrops(int addition) {
        cropYields += addition;
    }

    /**
     * Calculates excess crops.
     *
     * @return crop yields - population - 2 * troops
     */
    public int getExcessCrops() {
        return cropYields - population - 2 * troops;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }

    public int getTroops() {
        return troops;
    }

    public int getCropYields() {
        return cropYields;
    }

    public int getBanks() {
        return banks;
    }

    public int getRoads() {
        return roads;
    }

    public int getUniversities() {
        return universities;
    }

    @Override
    public String toString() {
        return String.format(
                "%s   %d people   %d troops   %d crop yields   %d banks   %d roads   %d universities",
                name, population, troops, cropYields, banks, roads, universities
        );
    }

    /**
     * Checks whether two cities are equal
     * Two cities are equal when they have the same id
     * Return false if Object o is not an instance of City
     *
     * @param o object to be compared
     * @return result of equality check
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return id == city.id;
    }

    /**
     * Increases population by increment = round(excess crops * 0.5)
     * If the increment turns out to be negative, leave population unchanged
     * Print a line that says:
     * "[city name]'s population has grown by [increment]
     * e.g. "HK's population has grown by 50"
     */
    public String growAtTurnEnd() {
        float excessCrops = getExcessCrops();
        int increment = Math.max(0, Math.round(excessCrops * 0.5f));
        population += increment;
        return String.format("[Turn end] %s's population has grown by %d", name, increment);
    }

    /**
     * Invoke a random event at the end of turn.
     * There are two types of events, a disaster and baby boom.
     * A disaster happens when rand <= 0.4, it halves the population.
     * A baby boom happens when rand > 0.4 AND rand <= 0.8 it multiplies the population by 1.5.
     * Otherwise the population is left unchanged
     * <p>
     * Print a message in the following format when each event happens
     * "A disaster in [city name] has happened, population was reduced significantly"
     * "A baby boom in [city name] has happened, population was increased significantly"
     *
     * @param rand random number between 0 and 1 supplied by the function caller
     */
    public String invokeRandomEvent(double rand) {

        if (rand <= 0.4) {
            population = Math.round(population * 0.5f);
            return String.format("[Random event] A disaster in %s has happened, population was reduced significantly", name);
        } else if (rand <= 0.8) {
            population = Math.round(population * 1.5f);
            return String.format("[Random event] A baby boom in %s has happened, population was increased significantly", name);
        }

        return "";
    }

    /**
     * Cost of building a bank
     * gold = production points = (# of banks + 1) * 400
     *
     * @return Cost object encapsulating the gold and production costs
     */
    public Cost getBankCost() {
        int amount = (banks + 1) * 400;
        return new Cost(amount, amount, 0);
    }

    /**
     * Cost of building a road
     * gold = production points = (# of banks + 1) * 100
     *
     * @return Cost object encapsulating the gold and production costs
     */
    public Cost getRoadCost() {
        int amount = (roads + 1) * 100;
        return new Cost(amount, amount, 0);
    }

    /**
     * Cost of building a university
     * gold = production points = (# of banks + 1) * 1500
     *
     * @return Cost object encapsulating the gold and production costs
     */
    public Cost getUniversityCost() {
        int amount = (universities + 1) * 1500;
        return new Cost(amount, amount, 0);
    }

    /**
     * Return the image to represent this City
     * The
     *
     * @return
     */
    public Image getImage(int width, int height) {

        int size = banks + roads + universities;
        if (size < 3)
            return new Image("city-small.png", width, height, false, false);
        else if (size < 5)
            return new Image("city-medium.png", width, height, false, false);
        else
            return new Image("city-large.png", width, height, false, false);
    }
}
