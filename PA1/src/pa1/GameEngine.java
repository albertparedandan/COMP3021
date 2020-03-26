package pa1;

import pa1.exceptions.TooPoorException;
import pa1.ministers.Minister;
import pa1.technologies.Technology;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;


/**
 * Implements the game logic
 */
public class GameEngine {

    private GameMap gameMap = new GameMap();
    private int turns;
    private Scanner userInputScanner = new Scanner(System.in);


    /**
     * Determine if the game is over by checking if there is exactly one player with at least one city
     *
     * @return true if game is over, false otherwise
     */
    public boolean isGameOver() {
        // TODO
        // variable to count the number of players with cities
        int check = 0;

        // go through each players and check if they have a city
        // if they have at least 1 city, increment check
        // if they don't, continue with the loop
        for (int i = 0; i < gameMap.getPlayers().size(); i++) {
            if (gameMap.getPlayers().get(i).hasAnyCity()) {
                check++;
            }
            else {
                continue;
            }
        }

        // if only 1 player with at least one city, return true
        if (check == 1) {
            return true;
        }

        return false;

    }

    /**
     * Get the the only player with at least one city
     *
     * @return
     */
    public Player getWinner() {
        // TODO
        // if only 1 player has at least one city, return that player
        if (isGameOver()) {
            for (int i = 0; i < gameMap.getPlayers().size(); i++) {
                if (gameMap.getPlayers().get(i).hasAnyCity()) {
                    return gameMap.getPlayers().get(i);
                } else {
                    continue;
                }
            }
        }
        return null;
    }

    private int getSelection(int min, int max, String name) {
        while (true) {
            try {
                System.out.printf("\nSelect %s (%d-%d):\n", name, min, max);
                int selection = userInputScanner.nextInt();
                if ((selection < min || selection > max) && selection != 0)
                    throw new IndexOutOfBoundsException();
                return selection;

            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.print("Invalid option, choose again");
            }
        }
    }

    private void processPlayersTurn() {
        turns++;
        for (Player player : gameMap.getPlayers()) {

            if (player.hasAnyCity()) {
                System.out.printf("\n\t--- Year %d | %s's turn ---\n\n", turns, player.getName());
                //printMap();

                player.getMinisters().forEach(Minister::beginTurn);
            }

            while (player.hasReadyMinister() && player.hasAnyCity()) {
                System.out.print("\n\n");
                printMap();
                System.out.print("\n\n");
                System.out.println(player);
                System.out.print("\n\n");
                Minister minister = selectMinister(player);

                if (minister == null) break;

                System.out.print("\n\n");
                City city = selectCity(player);

                if (city == null) break;

                System.out.print("\n\n");
                selectAndPerformAction(player, minister, city);
                minister.endTurn();
            }

            player.getCities().forEach(City::growAtTurnEnd);
            player.getCities().forEach(c -> c.invokeRandomEvent(Math.random()));
        }
    }

    private Minister selectMinister(Player player) {
        System.out.println("MINISTER SELECTION");
        for (int i = 0; i < player.getMinisters().size(); i++)
            System.out.printf("\t[%d]\t%s\n", i + 1, player.getMinisters().get(i));

        Minister m = null;
        while (true) {
            int selection = getSelection(1, player.getMinisters().size(), "minister (0 to skip turn)");
            if (selection == 0) break;
            m = player.getMinisters().get(selection - 1);
            if (!m.isReady()) {
                System.out.println("Selected minister already performed a task");
            } else {
                break;
            }
        }

        return m;
    }

    private City selectCity(Player player) {
        System.out.println("CITY SELECTION");
        for (int i = 0; i < player.getCities().size(); i++)
            System.out.printf("\t[%d]\t%s\n", i + 1, player.getCities().get(i));

        int selection = getSelection(1, player.getCities().size(), "city (0 to skip turn)");
        if (selection == 0) return null;
        return player.getCities().get(selection - 1);
    }

    private void selectAndPerformAction(Player player, Minister minister, City city) {

        Cost bankCost = city.getBankCost().getDiscountedCost(minister.getImprovementDiscountRate());
        Cost roadCost = city.getRoadCost().getDiscountedCost(minister.getImprovementDiscountRate());
        Cost uniCost = city.getUniversityCost().getDiscountedCost(minister.getImprovementDiscountRate());

        System.out.println("SELECT MINISTER ACTION");
        System.out.println("\t[ 1]\tCollect tax");
        System.out.println("\t[ 2]\tCollect science point");
        System.out.println("\t[ 3]\tCollect production point");
        System.out.printf("\t[ 4]\tBuild a bank (%d gold, %d production points)\n", bankCost.getGold(), bankCost.getProduction());
        System.out.printf("\t[ 5]\tBuild road (%d gold, %d production points)\n", roadCost.getGold(), roadCost.getProduction());
        System.out.printf("\t[ 6]\tBuild a university (%d gold, %d production points)\n", uniCost.getGold(), uniCost.getProduction());
        System.out.println("\t[ 7]\tImprove crops yield");
        System.out.println("\t[ 8]\tResearch tech");
        System.out.println("\t[ 9]\tSend troops");
        System.out.println("\t[10]\tRecruit troops");
        System.out.println("\t[11]\tSpy on neighbors");

        while (true) {
            try {
                int command = getSelection(1, 11, "action");
                processPlayerCommand(command, player, minister, city);
                break;
            } catch (TooPoorException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private double applyTechBonus(double val, List<Technology> tech, Function<Technology, Double> mapper) {
        return tech.stream().map(mapper).reduce(val, (a, b) -> a * b);
    }

    private void processPlayerCommand(int command, Player player, Minister minister, City city) throws TooPoorException {

        switch (command) {
            case 1:
                double gold = minister.collectTax(city);
                gold = applyTechBonus(gold, player.getTechnologies(), Technology::getGoldBonus);
                player.addGold((int) Math.round(gold));
                break;

            case 2:
                player.addSciencePoint(minister.collectSciencePoints(city));
                break;

            case 3:
                double prod = minister.collectProductionPoints(city);
                prod = applyTechBonus(prod, player.getTechnologies(), Technology::getProductionBonus);
                player.addProductionPoint((int) Math.round(prod));
                break;
            case 4:
                minister.buildBank(player, city);
                break;
            case 5:
                minister.buildRoad(player, city);
                break;
            case 6:
                minister.buildUniversity(player, city);
                break;
            case 7:
                minister.improveCropYield(player, city);
                break;
            case 8:
                researchTech(player, minister);
                break;
            case 9:
                sendTroop(player, minister, city);
                break;
            case 10:
                minister.recruitTroops(player, city);
                break;
            case 11:
                minister.spyOnNeighbors(city, gameMap);
                break;
            default:
                break;
        }
    }

    private void researchTech(Player player, Minister minister) throws TooPoorException {
        System.out.print("\nChoose tech to upgrade:\n");
        int count = 1;
        for (Technology tech : player.getTechnologies()) {
            System.out.printf("[%d]\t%s\n", count++, tech);
        }

        int selection = getSelection(1, 3, "technology");
        Technology target = player.getTechnologies().get(selection - 1);

        minister.upgradeTech(player, target);
    }

    private void sendTroop(Player player, Minister minister, City city) {
        int numTroops = getSelection(0, city.getTroops(), "number of troops to send");

        System.out.println("SELECT ACTION");
        int count = 1;
        for (City neighbor : gameMap.getNeighboringCities(city)) {
            boolean shouldAttack = gameMap.getCityOwner(neighbor) != player;
            System.out.printf("\t[%d]\t%s %s\n", count++, shouldAttack ? "Attack" : "Transfer to", neighbor.getName());
        }

        int selection = getSelection(1, gameMap.getNeighboringCities(city).size(), "troop action");
        City target = gameMap.getNeighboringCities(city).get(selection - 1);
        boolean shouldAttack = gameMap.getCityOwner(target) != player;

        if (shouldAttack) {
            minister.attackCity(city, target, numTroops, player.getTechnologies());

            if (target.getTroops() == 0) {
                Player loser = gameMap.getCityOwner(target);
                loser.getCities().remove(target);
                player.getCities().add(target);
                System.out.printf("%s loses %s to %s\n", loser.getName(), target.getName(), player.getName());
            }

        } else {
            city.decreaseTroops(numTroops);
            target.addTroops(numTroops);
        }
    }

    private void printMap() {
        System.out.println(gameMap);
    }


    public static void main(String args[]) throws IOException {

        GameEngine game = new GameEngine();

        game.gameMap.loadMap("map.txt");
        game.gameMap.loadPlayers("players.txt");

        while (!game.isGameOver()) {
            game.processPlayersTurn();
        }

        Player winner = game.getWinner();
        System.out.printf("Player %s wins the game", winner.getName());

    }
}
