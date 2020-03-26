package lab7.exception;

import lab7.Cost;
import lab7.Player;

/**
 * Exception thrown when player does not have enough currencies to afford a cost
 */
// TODO:1
//  correctly define the exception class
public class TooPoorException extends Exception {

    private final Cost cost;
    private final Player player;

    /**
     * Initializes member variables
     *
     * @param player
     * @param cost
     */
    public TooPoorException(Player player, Cost cost) {
        //TODO
        this.player = player;
        this.cost = cost;
    }

    /**
     * Constructs an error message in the form:
     * "need (%d golds, %d production points, %d science points), have (%d golds, %d production points, %d science points)"
     *
     * @return
     */
    @Override
    public String getMessage() {
        //TODO
        // Constructs an error message in the form:
        // "need (%d golds, %d production points, %d science points), have (%d golds, %d production points, %d science points)"
        String result = String.format("need (%d golds, %d production points, %d science points), have (%d golds, %d production points, %d science points", cost.getGold(), cost.getProduction(), cost.getScience(), player.getGold(), player.getProductionPoint(), player.getSciencePoint());
        return result;

    }
}