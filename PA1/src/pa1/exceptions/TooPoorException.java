package pa1.exceptions;

import pa1.Cost;
import pa1.Player;

/**
 * Exception thrown when player does not have enough currencies to afford a cost
 */
// TODO: correctly define the exception class
public class TooPoorException extends Exception{

    private final Cost cost;
    private final int playerGolds;
    private final int playerProductionPoints;
    private final int playerSciencePoints;

    /**
     * Initializes member variables
     *
     * @param player
     * @param cost
     */
    public TooPoorException(Player player, Cost cost) {
        this.cost = cost;
        this.playerGolds = player.getGold();
        this.playerProductionPoints = player.getProductionPoint();
        this.playerSciencePoints = player.getSciencePoint();
    }

    /**
     * Constructs an error message in the form:
     * "need (%d golds, %d production points, %d science points), have (%d golds, %d production points, %d science points)"
     *
     * @return
     */
    @Override
    public String getMessage() {
        String result = String.format("need (%d golds, %d production points, %d science points), have (%d golds, %d production points, %d science points", this.cost.getGold(), this.cost.getProduction(), this.cost.getScience(), this.playerGolds, this.playerProductionPoints, this.playerSciencePoints);
        return result;
    }
}