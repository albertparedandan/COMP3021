
import ministers.Minister;
import technologies.ManufacturingTech;
import technologies.Technology;
import technologies.TradingTech;
import technologies.WarTech;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that represents a player in the game.
 * It contains the player's attribute such as gold, science and production points,
 * as well as the player's assets such as ministers, cities, and technologies.
 */
public class Player {

    // Assets
    private final List<Minister> ministers = new ArrayList<>();
    private final List<City> cities = new ArrayList<>();
    private final List<Technology> technologies = new ArrayList<>();

    // Attributes
    private final String name;
    private int gold;
    private int sciencePoint;
    private int productionPoint;

    /**
     * Initializes member variables.
     *
     * @param name
     * @param gold
     * @param science
     * @param production
     */
    public Player(String name, int gold, int science, int production) {
        technologies.add(new WarTech());
        technologies.add(new TradingTech());
        technologies.add(new ManufacturingTech());

        this.name = name;
        this.gold = gold;
        sciencePoint = science;
        productionPoint = production;
    }

    @Override
    public String toString() {
        return String.format("Player: %s | gold: %d | science points: %d | production points: %d",
                name, gold, sciencePoint, productionPoint);
    }

    // Trivial getters

    public List<City> getCities() {
        return cities;
    }

    public List<Minister> getMinisters() {
        return ministers;
    }

    public List<Technology> getTechnologies() {
        return technologies;
    }

    public int getGold() {
        return gold;
    }

    public int getSciencePoint() {
        return sciencePoint;
    }

    public int getProductionPoint() {
        return productionPoint;
    }

    public String getName() {
        return name;
    }

}
