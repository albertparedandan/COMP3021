package lab7.ministers;


import lab7.City;
import lab7.Cost;
import lab7.Player;
import lab7.exception.TooPoorException;

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
     * @return 1.5 times the amount collected by other types of ministers
     */
    @Override
    public int collectTax(City city) {
        return Math.round(1.5f * super.collectTax(city));
    }

    /**
     * Example string representation:
     * "Economist | intelligence: 100 | experience: 100 | leadership: 100 | READY" - when isReady() == true
     * "Economist | intelligence: 100 | experience: 100 | leadership: 100 | DONE" - when isReady() == false
     *
     * @return string representation of this object
     */
    @Override
    public String toString() {
        return String.format("Economist | intelligence: %d | experience: %d | leadership: %d",
                intelligence, experience, leadership);
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
        //TODO:2.4
        // If player has less than 500 golds, throw a TooPoorException, the cost is (gold: 500, production: 0, science: 0)


        // Given gode
        if (player.getGold() < 500) {
            Cost temp = new Cost(500, 0 , 0);
            throw new TooPoorException(player, temp);
        }
        else {
            player.addGold(-500);
            city.improveCrops(50 + Math.round((intelligence + experience + leadership) * 0.2f));
        }
    }

}
