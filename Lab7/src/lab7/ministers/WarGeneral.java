package lab7.ministers;

import lab7.City;
import lab7.Cost;
import lab7.Player;
import lab7.exception.TooPoorException;

public class WarGeneral extends Minister {
    public WarGeneral(int intelligence, int experience, int leadership) {
        super(intelligence, experience, leadership);
    }

    /**
     * @param city to collect production points from
     * @return 1.5 times the amount collected by other types of ministers
     */
    @Override
    public int collectProductionPoints(City city) {
        return Math.round(1.5f * super.collectProductionPoints(city));
    }

    /**
     * Example string representation:
     * "WarGeneral | intelligence: 100 | experience: 100 | leadership: 100 | READY" - when isReady() == true
     * "WarGeneral | intelligence: 100 | experience: 100 | leadership: 100 | DONE" - when isReady() == false
     *
     * @return string representation of this object
     */
    @Override
    public String toString() {
        return String.format("WarGeneral | intelligence: %d | experience: %d | leadership: %d",
                intelligence, experience, leadership);
    }

    /**
     * Recruits more troops than superclass implementation,
     * troops added to city = 50 + round(leadership * 0.2).
     * <p>
     * Recruitment still costs 500 golds. Throw an exception
     * when player does not have enough gold.
     *
     * @param player
     * @param city
     * @throws TooPoorException
     */
    @Override
    public void recruitTroops(Player player, City city) throws TooPoorException {
        //TODO:2.3
        // If player has less than 500 golds, throw a TooPoorException, the cost is (gold: 500, production: 0, science: 0)

        // Given gode
        if (player.getGold() < 500) {
            Cost temp = new Cost(500, 0 , 0);
            throw new TooPoorException(player, temp);
        } else {
            player.addGold(-500);
            city.addTroops(50 + Math.round(leadership * 0.2f));
        }
    }
}
