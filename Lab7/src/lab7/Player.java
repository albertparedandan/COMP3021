package lab7;

import lab7.ministers.Minister;

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

    // Attributes
    private final String name;
    private int gold;
    private int sciencePoint;
    private int productionPoint;


    public Player(String name, int gold) {
        this.name = name;
        this.gold = gold;
    }

    public Player(String name, int gold, int sciencePoint, int productionPoint) {
        this.name = name;
        this.gold = gold;
        this.sciencePoint = sciencePoint;
        this.productionPoint = productionPoint;
    }

    @Override
    public String toString() {
        return String.format("Player: %s | gold: %d | science point: %d | production point: %d",
                name, gold, sciencePoint, productionPoint);
    }

    public void addGold(int increment) {
        gold = Math.max(0, gold + increment);
    }

    // Trivial getters

    public List<City> getCities() {
        return cities;
    }

    public List<Minister> getMinisters() {
        return ministers;
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
