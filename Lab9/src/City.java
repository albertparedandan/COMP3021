
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

    // Location of the city, for Lab 9 only
    private int locationX;
    private int locationY;


    public City(int id, String name, int population, int troops, int cropYields) {
        this.id = id;
        this.name = name;
        this.population = population;
        this.troops = troops;
        this.cropYields = cropYields;
    }

    public City(int id, String name, int population, int troops, int cropYields, int banks, int roads, int universities) {
        this(id, name, population, troops, cropYields);
        this.banks = banks;
        this.roads = roads;
        this.universities = universities;
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
     * Return an image of the specified size to represent this city
     * The
     *
     * @return
     */
    public Image getImage(int width, int height) {

        int size = getBanks() + getRoads() + getUniversities();
        if (size < 3)
            return new Image("city-small.png", width, height, false, false);
        else if (size < 5)
            return new Image("city-medium.png", width, height, false, false);
        else
            return new Image("city-large.png", width, height, false, false);
    }

    // getters and setters of location, for Lab 9 only
    public int getLocationX() {
        return locationX;
    }

    public void setLocationX(int locationX) {
        this.locationX = locationX;
    }

    public int getLocationY() {
        return locationY;
    }

    public void setLocationY(int locationY) {
        this.locationY = locationY;
    }
}
