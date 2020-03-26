package pa1;

/**
 * A class that represents a city in the game
 */
public class City {

    // TODO: define instance variables according to the UML
    // Metadata
    private final int id;
    private final String name;

    // Attributes
    private int population, troops, cropYields;

    // Improvements
    private int banks, roads, universities;

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
        // TODO
        this.banks++;
    }

    /**
     * Adds number of roads by one
     */
    public void addRoad() {
        // TODO
        this.roads++;
    }

    /**
     * Adds number of universities by one
     */
    public void addUniversity() {
        // TODO
        this.universities++;
    }

    /**
     * Adds number of troops by specified increment.
     * If the increment is negative, invoke decreaseTroops() within this method instead.
     *
     * @param increment
     */
    public void addTroops(int increment) {
        // TODO
        if (increment < 0) {
            this.decreaseTroops(increment);
        }
        this.troops = this.troops + increment;

    }

    /**
     * Decreases number of troops by the amount specified
     * Caps the number of troops at 0
     *
     * @param decrement
     */
    public void decreaseTroops(int decrement) {
        //TODO
        if (this.troops - decrement > 0) {
            this.troops = this.troops - decrement;
        }
        else {
            this.troops = 0;
        }
    }

    /**
     * Add to crop yields the amount specified
     *
     * @param addition
     */
    public void improveCrops(int addition) {
        // TODO
        this.cropYields = this.cropYields + addition;
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
        // TODO
        City temp = (City) o;
        if (o.getClass() == City.class) {
            if (temp.getId() == this.id) {
                return true;
            }
        }
        return false;
    }

    /**
     * Increases population by increment = round(excess crops * 0.5)
     * If the increment turns out to be negative, leave population unchanged
     * Print a line that says:
     * "Turn end: [city name]'s population has grown by [increment]
     * e.g. "Turn end: HK's population has grown by 50"
     */
    public void growAtTurnEnd() {
        // TODO if no grow, print what?
        int increment = Math.round(this.getExcessCrops() * 0.5f);
        if (increment > 0) {
            this.population = this.population + increment;
            System.out.println("Turn end: " + this.name + "'s population has grown by " + increment);
        }
        else {
            System.out.println("Turn end: " + this.name + "'s population has grown by " + 0);
        }
    }

    /**
     * Invoke a random event at the end of turn.
     * There are two types of events, a disaster and baby boom.
     * A disaster happens when rand <= 0.4, it halves the population.
     * A baby boom happens when rand > 0.4 AND rand <= 0.8 it multiplies the population by 1.5.
     * Otherwise the population is left unchanged
     * <p>
     * Print a message in the following format when each event happens
     * "Random event: A disaster in [city name] has happened, population was reduced significantly"
     * "Random event: A baby boom in [city name] has happened, population was increased significantly"
     *
     * @param rand random number between 0 and 1 supplied by the function caller
     */
    public void invokeRandomEvent(double rand) {
        // TODO print anything when nothing happens?
        if (rand <= 0.4) {
            int newPop = Math.round(this.population * 0.5f);
            this.population = newPop;
            System.out.println("Random event: A disaster in " + this.name + " has happened, population was reduced significantly");
        }
        else if (rand > 0.4 && rand <= 0.8) {
            int newPop = Math.round(this.population * 1.5f);
            this.population = newPop;
            System.out.println("Random event: A baby boom in " + this.name + " has happened, population was increased significantly");
        }
    }

    /**
     * Cost of building a bank
     * gold = production points = (# of banks + 1) * 400
     *
     * @return Cost object encapsulating the gold and production costs
     */
    public Cost getBankCost() {
        // TODO
        Cost temp = new Cost((this.banks + 1) * 400, (this.banks + 1) * 400, 0);
        return temp;
    }

    /**
     * Cost of building a road
     * gold = production points = (# of roads + 1) * 100
     *
     * @return Cost object encapsulating the gold and production costs
     */
    public Cost getRoadCost() {
        // TODO
        Cost temp = new Cost((this.roads + 1) * 100,(this.roads + 1) * 100, 0);
        return temp;
    }

    /**
     * Cost of building a university
     * gold = production points = (# of universities + 1) * 1500
     *
     * @return Cost object encapsulating the gold and production costs
     */
    public Cost getUniversityCost() {
        // TODO
        Cost temp = new Cost((this.universities + 1) * 1500,(this.universities + 1) * 1500,0);
        return temp;
    }

    /**
     * Calculates excess crops.
     *
     * @return crop yields - population - 2 * troops
     */
    public int getExcessCrops() {
        // TODO
        return (this.cropYields - this.population - (2 * this.troops));
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

    public String getMapRepresentation(String playerName) {
        return String.format("[%s:%s]", playerName, getName());
    }

    @Override
    public String toString() {
        return String.format("%s | population: %d | troops: %d | crop yields: %d | excess crops: %d | # of banks: %d | # of roads: %d | # of universities: %d",
                name, population, troops, cropYields, getExcessCrops(), banks, roads, universities);
    }

}
