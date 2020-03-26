package pa1.ministers;


import pa1.City;
import pa1.Cost;
import pa1.Player;
import pa1.exceptions.TooPoorException;

public class Economist extends Minister {

    public Economist(int intelligence, int experience, int leadership) {
        super(intelligence, experience, leadership);
    }

    /**
     * @return improvement discount rate equals to 1 - (intelligence + experience) / 1500
     */
    @Override
    public double getImprovementDiscountRate() {
        return 1 - (intelligence + experience) / 1500.;
    }

    /**
     * @param city to collect gold from
     * @return 1.5 times the amount collected by other types of minister
     */
    @Override
    public int collectTax(City city) {
        return Math.round(1.5f * super.collectTax(city));
    }


    /**
     * Economists get a bonus when doing crops improvements
     * Crop improvement still costs 500 gold
     * Crop is improved by 50 + round((intelligence + experience + leadership) * 0.2)
     * If the player does not have enough gold, throw an exception
     * Else decrease 500 golds from the player and improves crops by the calculated amount
     *
     * @param player
     * @param city
     * @throws TooPoorException
     */
    @Override
    public void improveCropYield(Player player, City city) throws TooPoorException {
        int gold = 500;
        if (player.getGold() < gold)
            throw new TooPoorException(player, new Cost(gold, 0, 0));
        int increment = 50 + Math.round((intelligence + experience + leadership) * 0.2f);
        player.decreaseGold(gold);
        city.improveCrops(increment);
    }

    /**
     * @return string representation of this object
     */
    @Override
    public String toString() {
        return String.format(
                "Economist   %d/%d/%d   %s",
                experience, leadership, intelligence,
                isReady() ? "READY" : "DONE"
        );
    }
}
