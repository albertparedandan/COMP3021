package pa1;

import pa1.ministers.Minister;
import pa1.technologies.ManufacturingTech;
import pa1.technologies.Technology;
import pa1.technologies.TradingTech;
import pa1.technologies.WarTech;

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

    public boolean isHuman() {
        return isHuman;
    }

    private final boolean isHuman;

    /**
     * Initializes member variables.
     *
     * @param name
     * @param gold
     * @param science
     * @param production
     */
    public Player(String name, int gold, int science, int production, boolean isHuman) {
        technologies.add(new WarTech());
        technologies.add(new TradingTech());
        technologies.add(new ManufacturingTech());


        this.name = name;
        this.gold = gold;
        this.sciencePoint = science;
        this.productionPoint = production;
        this.isHuman = isHuman;
    }

    @Override
    public String toString() {
        return String.format(
                "Player: %s | gold: %d | science points: %d | production points: %d",
                name, gold, sciencePoint, productionPoint);
    }

    /**
     * Decreases the player's gold
     * Cap the value to 0.
     *
     * @param decrement
     */
    public void decreaseGold(int decrement) {

        gold = Math.max(0, gold - decrement);
    }

    /**
     * Decreases the player's science points
     * Cap the value to 0.
     *
     * @param decrement
     */
    public void decreaseSciencePoint(int decrement) {

        sciencePoint = Math.max(0, sciencePoint - decrement);
    }

    /**
     * Decreases the player's production points
     * Cap the value to 0.
     *
     * @param decrement
     */
    public void decreaseProductionPoint(int decrement) {

        productionPoint = Math.max(0, productionPoint - decrement);
    }

    /**
     * Adds player's gold by specified increment.
     * If the increment is negative, invoke decreaseGold() within this method instead.
     *
     * @param increment
     */
    public void addGold(int increment) {

        if (increment < 0)
            decreaseGold(-increment);
        else
            gold += increment;
    }

    /**
     * Adds player's science points by specified increment.
     * If the increment is negative, invoke decreaseSciencePoint() within this method instead.
     *
     * @param increment
     */
    public void addSciencePoint(int increment) {

        if (increment < 0)
            decreaseSciencePoint(-increment);
        else
            sciencePoint += increment;
    }

    /**
     * Adds player's production points by specified increment.
     * If the increment is negative, invoke decreaseProductionPoint() within this method instead.
     *
     * @param increment
     */
    public void addProductionPoint(int increment) {
        if (increment < 0)
            decreaseProductionPoint(-increment);
        else
            productionPoint += increment;
    }

    /**
     * @param city
     * @return true if the given city belongs to this player
     */
    public boolean hasCity(City city) {
        for (var ownCity : cities) {
            if (ownCity.equals(city))
                return true;
        }
        return false;
    }

    /**
     * Hint: use a method in the List class
     *
     * @return true if this player has at least one city
     */
    public boolean hasAnyCity() {
        return !cities.isEmpty();
    }

    /**
     * @return true if the player has at least one ready minister
     */
    public boolean hasReadyMinister() {
        for (var minister : ministers) {
            if (minister.isReady())
                return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return player.name.equals(name);
    }

    // Trivial getters

    public List<City> getCities() {
        return cities;
    }

    public List<Minister> getMinisters() {
        return ministers;
    }

    public List<Minister> getReadyMinisters() {
        var readyMinisters = new ArrayList<Minister>();
        for (var minister : ministers) {
            if (minister.isReady()) {
                readyMinisters.add(minister);
            }
        }
        return readyMinisters;
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
