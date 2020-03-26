package pa1;


import pa1.ministers.Economist;
import pa1.ministers.Minister;
import pa1.ministers.Scientist;
import pa1.ministers.WarGeneral;

import java.io.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Holds the necessary components for running the game
 */
public class GameMap {
    private List<Player> players;
    private Map<Integer, Cell> cityLocations;
    private Map<Integer, List<Integer>> connectedCities;
    private int width, height;

    /**
     * Load the map from a text file
     * The outline of the map format is given in the assignment description
     *
     * You should instantiate cityLocations and connectedCities here
     *
     * @param filename
     * @throws IOException
     */
    public void loadMap(String filename) throws IOException {
        // TODO have to check if file exists?
        this.cityLocations = new HashMap<Integer, Cell>();
        this.connectedCities = new HashMap<Integer, List<Integer>>();
        File inputFile = new File(filename);

        try(
                Scanner reader = new Scanner(inputFile)
        ) {
            this.width = reader.nextInt();
            this.height = reader.nextInt();
            int boardCities = reader.nextInt();

            for (int i = 0; i < boardCities; i++) {
                int cityID = reader.nextInt();
                String line = reader.next();
                String coor = "";
                char lineArray[] = line.toCharArray();
                int j = 0;
                for (j = 0; lineArray[j] != ','; j++) {
                    coor = coor + lineArray[j];
                }
                int x = Integer.parseInt(coor);
                coor = "";
                for (j = j + 1; j < lineArray.length; j++) {
                    coor = coor + lineArray[j];
                }
                int y = Integer.parseInt(coor);

                Cell temp = new Cell(x, y);
                this.cityLocations.put(cityID, temp);
            }

            for (int i = 0; i < boardCities; i++ ) {
                int cityID = reader.nextInt();

                //TODO assume always 2 cities?
                String line = reader.next();
                String coor = "";
                char lineArray[] = line.toCharArray();
                int j = 0;
                for (j = 0; lineArray[j] != ','; j++) {
                    coor = coor + lineArray[j];
                }
                int x = Integer.parseInt(coor);
                coor = "";
                for (j = j + 1; j < lineArray.length; j++) {
                    coor = coor + lineArray[j];
                }
                int y = Integer.parseInt(coor);
                List<Integer> temp = new ArrayList<Integer>();
                temp.add(x);
                temp.add(y);
                this.connectedCities.put(cityID, temp);
            }
        }
    }

    /**
     * Loads player data from text file
     * The outline of the player file format is given in the assignment description
     *
     * You should instantiate the member variable players here
     *
     * @param filename
     * @throws IOException
     */
    public void loadPlayers(String filename) throws IOException {
        // TODO have to check if file exists?
        players = new ArrayList<Player>();
        File inputFile = new File(filename);
        try(Scanner reader = new Scanner(inputFile)) {
            int numOfPlayers = reader.nextInt();
            for (int i = 0; i < numOfPlayers; i++) {
                String name = reader.next();
                int gold = reader.nextInt();
                int sciencePoint = reader.nextInt();
                int productionPoint = reader.nextInt();
                Player newPlayer = new Player(name, gold, sciencePoint, productionPoint);
                this.players.add(newPlayer);

                int numOfCity = reader.nextInt();
                int numOfMin = reader.nextInt();

                for (int j = 0; j < numOfCity; j++) {
                    int cityID = reader.nextInt();
                    String cName = reader.next();
                    int pop = reader.nextInt();
                    int troops = reader.nextInt();
                    int cropYields= reader.nextInt();

                    City temp = new City(cityID, cName, pop, troops, cropYields);
                    this.players.get(i).getCities().add(temp);
                }

                for (int j = 0; j < numOfMin; j++) {
                    String mName = reader.next();
                    int intel = reader.nextInt();
                    int exp = reader.nextInt();
                    int lead = reader.nextInt();

                    if (mName.equals("Scientist")) {
                        Scientist temp = new Scientist(intel, exp, lead);
                        this.players.get(i).getMinisters().add(temp);
                    } else if (mName.equals("Economist")) {
                        Economist temp = new Economist(intel, exp, lead);
                        this.players.get(i).getMinisters().add(temp);
                    } else if (mName.equals("WarGeneral")) {
                        WarGeneral temp = new WarGeneral(intel, exp, lead);
                        this.players.get(i).getMinisters().add(temp);
                    }
                }
            }
        }
    }

    /**
     * @return list of all cities from every player
     */
    public List<City> getAllCities() {
        // TODO
        List<City> temp = new ArrayList<City>();
        for (int i = 0; i < players.size(); i++) {
            temp.addAll(players.get(i).getCities());
        }

        return temp;
    }

    /**
     * @param city
     * @return Cell object representing the city's location on the game map
     */
    public Cell getCityLocation(City city) {
        // TODO
        return cityLocations.get(city.getId());
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
                .orElse(new Player("", 0, 0, 0));
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

    private void drawLine(char map[][], Cell a, Cell b) {
        if (a.getX() == b.getX()) {
            int minY = Math.min(a.getY(), b.getY());
            int maxY = Math.max(a.getY(), b.getY());

            for (int i = minY; i < maxY; i++) map[i][a.getX()] = '|';

        } else if (a.getY() == b.getY()) {
            int minX = Math.min(a.getX(), b.getX());
            int maxX = Math.max(a.getX(), b.getX());

            Arrays.fill(map[a.getY()], minX, maxX, '-');
        }
    }

    @Override
    public String toString() {
        char[][] map = new char[height][width];
        char[] vertSeparator = new char[width + 2];
        Arrays.fill(vertSeparator, '#');
        for (int i = 0; i < height; i++) Arrays.fill(map[i], ' ');

        for (int cityIdA : connectedCities.keySet()) {
            for (int cityIdB : connectedCities.get(cityIdA)) {
                Cell a = cityLocations.get(cityIdA);
                Cell b = cityLocations.get(cityIdB);
                drawLine(map, a, b);
            }
        }


        for (City city : getAllCities()) {
            Cell location = getCityLocation(city);
            int x = location.getX();
            int y = location.getY();

            char[] line = map[y];
            String cityText = city.getMapRepresentation(getCityOwner(city).getName());
            System.arraycopy(cityText.toCharArray(), 0, line, x - 3, cityText.length());
        }

        StringBuilder sb = new StringBuilder().append(vertSeparator).append('\n');
        for (char[] line : map) sb.append('#').append(line).append('#').append('\n');
        return sb.append(vertSeparator).toString();
    }


}
