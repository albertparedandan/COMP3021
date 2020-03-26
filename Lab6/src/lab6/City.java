package lab6;

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
    private int banks = 1;
    private int roads = 1;
    private int universities = 1;


    public City(int id, String name, int population, int troops, int cropYields) {
        this.id = id;
        this.name = name;
        this.population = population;
        this.troops = troops;
        this.cropYields = cropYields;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    /**
     * Adds number of troops by specified increment.
     * If the increment is negative, invoke decreaseTroops() within this method instead.
     *
     * @param increment
     */
    public void addTroops(int increment) {
        troops = Math.max(0, troops + increment);
    }

    public int getPopulation() {
        return population;
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
        return String.format("%s | population: %d | troops: %d | crop yields: %d | # of banks: %d | # of roads: %d | # of universities: %d",
                name, population, troops, cropYields,  banks, roads, universities);
    }
}
