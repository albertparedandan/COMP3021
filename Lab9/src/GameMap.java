
import ministers.Economist;
import ministers.Scientist;
import ministers.WarGeneral;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Holds the necessary components for running the game
 */
public class GameMap {
    private List<Player> players;
    private Map<Integer, List<Integer>> connectedCities;
    private int width, height;

    public GameMap() {
        players = new ArrayList<>();
        connectedCities = new HashMap<>();

        Player US = new Player("US", 10000, 10000, 10000);
        City SanFan = new City(0, "SanFan", 300, 50, 500);
        SanFan.setLocationX(2);
        SanFan.setLocationY(1);
        City LA = new City(1, "LA", 500, 100, 600);
        LA.setLocationX(4);
        LA.setLocationY(1);
        Economist economist = new Economist(80, 100, 60);
        WarGeneral warGeneral = new WarGeneral(100, 80, 60);
        warGeneral.beginTurn();
        Scientist scientist = new Scientist(60, 80, 100);

        players.add(US);
        US.getCities().add(SanFan);
        US.getCities().add(LA);
        US.getMinisters().add(economist);
        US.getMinisters().add(warGeneral);
        US.getMinisters().add(scientist);

        Player CN = new Player("CN", 100, 100, 100);
        City Beijing = new City(2, "Beijing", 200, 0, 100, 2, 2, 2);
        Beijing.setLocationX(1);
        Beijing.setLocationY(2);
        City Shanghai = new City(3, "Shanghai", 500, 0, 800);
        Shanghai.setLocationX(1);
        Shanghai.setLocationY(4);

        players.add(CN);
        CN.getCities().add(Beijing);
        CN.getCities().add(Shanghai);

        Player ID = new Player("ID", 100, 100, 100);
        City Delhi = new City(4, "Delhi", 200, 0, 100);
        Delhi.setLocationX(5);
        Delhi.setLocationY(2);
        City Jaipur = new City(5, "Jaipur", 500, 0, 800);
        Jaipur.setLocationX(5);
        Jaipur.setLocationY(4);

        players.add(ID);
        ID.getCities().add(Delhi);
        ID.getCities().add(Jaipur);

        Player JP = new Player("JP", 100, 100, 100);
        City Tokyo = new City(6, "Tokyo", 200, 0, 100, 1, 1, 1);
        Tokyo.setLocationX(2);
        Tokyo.setLocationY(5);
        City Osaka = new City(7, "Osaka", 500, 0, 800);
        Osaka.setLocationX(4);
        Osaka.setLocationY(5);

        players.add(JP);
        JP.getCities().add(Tokyo);
        JP.getCities().add(Osaka);

        width = 7;
        height = 7;


        int[][] neighbors = {
                {1, 7}, {0, 2}, {3, 1}, {2, 4},
                {5, 3}, {4, 6}, {7, 5}, {0, 6}
        };
        for(int i = 0; i < neighbors.length; ++i) {
            connectedCities.put(i, new ArrayList<>());
            for(int neighbor: neighbors[i]) {
                connectedCities.get(i).add(neighbor);
            }
        }
    }

    public List<Player> getPlayers() {
        return players;
    }


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
