package pa1;

import pa1.exceptions.TooPoorException;
import pa1.ministers.Minister;
import pa1.technologies.Technology;

import java.util.List;
import java.util.function.Function;


/**
 * Implements the game logic
 */
public class GameEngine {

    private GameMap gameMap = new GameMap();

    public GameMap getMap() {
        return gameMap;
    }

    /**
     * Determine if the game is over by checking if there is only one player with at least one city
     *
     * @return true if game is over, false otherwise
     */
    public boolean isGameOver() {
        int count = 0;
        for (var player : gameMap.getPlayers()) {
            if (player.hasAnyCity())
                ++count;
        }
        return count == 1;
    }

    /**
     * Get the the only player with at least one city
     *
     * @return
     */
    public Player getWinner() {
        if (isGameOver()) {
            for (var player : gameMap.getPlayers()) {
                if (player.hasAnyCity())
                    return player;
            }
        }
        return null;
    }

    private double applyTechBonus(double val, List<Technology> tech, Function<Technology, Double> mapper) {
        return tech.stream().map(mapper).reduce(val, (a, b) -> a * b);
    }

    public String processPlayerCommand(int command, Player player, Minister minister, City city, City target, Technology tech, int troops) throws TooPoorException {

        String message = "";
        switch (command) {
            case 0:
                double gold = minister.collectTax(city);
                gold = applyTechBonus(gold, player.getTechnologies(), Technology::getGoldBonus);
                player.addGold((int) Math.round(gold));
                message = String.format("Obtained %d gold from %s", (int) Math.round(gold), city.getName());
                break;
            case 1:
                int pts = minister.collectSciencePoints(city);
                player.addSciencePoint(pts);
                message = String.format("Obtained %d science points from %s", pts, city.getName());
                break;
            case 2:
                double prod = minister.collectProductionPoints(city);
                prod = applyTechBonus(prod, player.getTechnologies(), Technology::getProductionBonus);
                player.addProductionPoint((int) Math.round(prod));
                message = String.format("Obtained %d production points from %s", (int) Math.round(prod), city.getName());
                break;
            case 3:
                minister.buildBank(player, city);
                message = "Built a bank in " + city.getName();
                break;
            case 4:
                minister.buildRoad(player, city);
                message = "Built a road in " + city.getName();
                break;
            case 5:
                minister.buildUniversity(player, city);
                message = "Built a university in " + city.getName();
                break;
            case 6:
                minister.improveCropYield(player, city);
                message = "Improved crops yield in " + city.getName();
                break;
            case 7:
                researchTech(player, minister, tech);
                message = "Upgraded " + tech.toString();
                break;
            case 8:
                if(troops == 0){
                    return "The specified number of troops is 0. Nothing is done.";
                }
                Player targetOwner = gameMap.getCityOwner(target);
                if (player.equals(targetOwner)) {
                    city.decreaseTroops(troops);
                    target.addTroops(troops);
                    message = String.format("Transferred %d troops from %s to %s", troops, city.getName(), target.getName());
                } else {
                    StringBuilder sb = new StringBuilder();
                    int originalTroops = city.getTroops();
                    sb.append(minister.attackCity(city, target, troops, player.getTechnologies()));
                    // the attacker takes over the city if and only if
                    // 1. no remaining troops in the target city
                    // 2. some sent troops remain, e.g., the attacker sends 100 troops, only 80 troops are lost.
                    // note the remaining troops in the attacking city will stay in the city.
                    if (target.getTroops() == 0 && originalTroops - city.getTroops() < troops) {
                        targetOwner.getCities().remove(target);
                        player.getCities().add(target);
                        sb.append(String.format("\n%s loses %s to %s", targetOwner.getName(), target.getName(), player.getName()));
                    }

                    message = sb.toString();
                }
                break;
            case 9:
                minister.recruitTroops(player, city);
                message = "Recruited troops in " + city.getName();
                break;
            default:
                break;
        }

        minister.endTurn();
        return message;
    }

    private void researchTech(Player player, Minister minister, Technology target) throws TooPoorException {
        minister.upgradeTech(player, target);
    }

}
