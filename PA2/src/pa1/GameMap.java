package pa1;


import pa1.ministers.Economist;
import pa1.ministers.Minister;
import pa1.ministers.Scientist;
import pa1.ministers.WarGeneral;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Holds the necessary components for running the game
 */
public class GameMap {
    private List<Player> players;
    private Map<Integer, Cell> cityLocations;
    private Map<Integer, List<Integer>> connectedCities;
    private int width, height;

    public GameMap() {
        players = new ArrayList<>();
        cityLocations = new HashMap<>();
        connectedCities = new HashMap<>();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    /**
     * @param filename
     * @throws IOException
     */
    public void loadMap(String filename) throws IOException {
        cityLocations.clear();
        connectedCities.clear();
        File file = new File(filename);
        try (Scanner sc = new Scanner(file)) {
            width = sc.nextInt();
            height = sc.nextInt();
            int numOfCities = sc.nextInt();
            for (int i = 0; i < numOfCities; ++i) {
                int cityId = sc.nextInt();
                String[] coordinates = sc.next().split(",");
                int x = Integer.parseInt(coordinates[0]);
                int y = Integer.parseInt(coordinates[1]);
                Cell location = new Cell(x, y);
                cityLocations.put(cityId, location);
            }

            for (int i = 0; i < numOfCities; ++i) {
                int cityId = sc.nextInt();
                List<Integer> neighbors = new ArrayList<Integer>();
                String line = sc.next().trim();
                if (!line.equals("")) {
                    for (String s : line.split(",")) {
                        int neighbor = Integer.parseInt(s);
                        neighbors.add(neighbor);
                    }
                }
                connectedCities.put(cityId, neighbors);
            }
        }
    }

    /**
     * Loads player data from text file
     *
     * @param filename
     * @throws IOException
     */
    public void loadPlayers(String filename) throws IOException {
        players.clear();
        File file = new File(filename);
        try (Scanner sc = new Scanner(file)) {
            int numOfPlayers = sc.nextInt();
            for (int i = 0; i < numOfPlayers; ++i) {
                String name = sc.next();
                int gold = sc.nextInt();
                int science = sc.nextInt();
                int production = sc.nextInt();
                int numOfCities = sc.nextInt();
                int numOfMinisters = sc.nextInt();
                boolean isHuman = sc.next().equals("Human");
                var player = new Player(name, gold, science, production, isHuman);
                for (int j = 0; j < numOfCities; ++j) {
                    int cityId = sc.nextInt();
                    String cityName = sc.next();
                    int population = sc.nextInt();
                    int troops = sc.nextInt();
                    int cropYields = sc.nextInt();
                    var city = new City(cityId, cityName, population, troops, cropYields);
                    player.getCities().add(city);
                }
                for (int j = 0; j < numOfMinisters; ++j) {
                    String type = sc.next();
                    int intelligence = sc.nextInt();
                    int experience = sc.nextInt();
                    int leadership = sc.nextInt();
                    Minister minister = null;
                    switch (type) {
                        case "Economist":
                            minister = new Economist(intelligence, experience, leadership);
                            break;
                        case "Scientist":
                            minister = new Scientist(intelligence, experience, leadership);
                            break;
                        case "WarGeneral":
                            minister = new WarGeneral(intelligence, experience, leadership);
                            break;
                        default:
                            throw new exceptions.InvalidMinisterTypeException(type);
                    }
                    player.getMinisters().add(minister);
                }
                players.add(player);
            }
        }
    }

    /**
     * @return list of all cities from every player
     */
    public List<City> getAllCities() {
        var cities = new ArrayList<City>();
        for (var player : players) {
            cities.addAll(player.getCities());
        }
        return cities;
    }

    /**
     * @param city
     * @return Cell object representing the city's location on the game map
     */
    public Cell getCityLocation(City city) {
        return cityLocations.getOrDefault(city.getId(), null);
    }

    public City getCityById(int id) {
        return getAllCities().stream()
                .filter(city -> city.getId() == id)
                .findAny()
                .orElse(null);
    }

    public Player getCityOwner(City city) {
        return getPlayers().stream()
                .filter(p -> p.hasCity(city))
                .findFirst()
                .orElse(null);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<City> getNeighboringCities(City city) {
        List<Integer> neighborIds = connectedCities.get(city.getId());
        return neighborIds.stream()
                .map(this::getCityById)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
