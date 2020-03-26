package lab6;

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

    public Player(String name, int gold) {
        this.name = name;
        this.gold = gold;
    }

    @Override
    public String toString() {
        return String.format("Player: %s | gold: %d",
                name, gold);
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

    public String getName() {
        return name;
    }
}
