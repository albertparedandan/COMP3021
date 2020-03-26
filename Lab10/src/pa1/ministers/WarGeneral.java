package pa1.ministers;


import pa1.City;
import pa1.Cost;
import pa1.Player;
import pa1.exceptions.TooPoorException;
import pa1.technologies.Technology;

import java.util.List;


public class WarGeneral extends Minister {

    public WarGeneral(int intelligence, int experience, int leadership) {

        super(intelligence, experience, leadership);
    }

    /**
     * @param city to collect production points from
     * @return 1.5 times the amount collected by other types of minister
     */
    @Override
    public int collectProductionPoints(City city) {

        return Math.round(1.5f * super.collectProductionPoints(city));
    }

    /**
     * Recruits more troops than superclass implementation,
     * troops added to city = 50 + round(leadership * 0.2).
     *
     * Recruitment still costs 500 golds. Throw an exception
     * when player does not have enough gold.
     *
     * @param player
     * @param city
     * @throws TooPoorException
     */
    @Override
    public void recruitTroops(Player player, City city) throws TooPoorException {
        int gold = 500;
        if (player.getGold() < gold) {
            throw new TooPoorException(player, new Cost(gold, 0, 0));
        }
        int increment = 50 + Math.round(leadership * 0.2f);
        player.decreaseGold(gold);
        city.addTroops(increment);
    }


    /**
     * WarGeneral gets attack bonus when attacking a city.
     * Call the protected attackCity() method in the super class with
     *     ministerBonus = 1 + (intelligence + experience + leadership) / 1500.0;
     *
     * @param attacker       attacking city
     * @param defender       defending city
     * @param attackingTroops         number of troops deployed for the attack
     * @param technologies  technologies owned by the attacking player
     */
    @Override
    public String attackCity(City attacker, City defender, int attackingTroops, List<Technology> technologies) {
        double ministerBonus = 1 + (intelligence + experience + leadership) / 1500.0;
        return super.attackCity(attacker, defender, attackingTroops, technologies, ministerBonus);
    }

    /**
     * @return string representation of this object
     */
    @Override
    public String toString() {
        return String.format(
                "WarGeneral   %d/%d/%d   %s",
                experience, leadership, intelligence,
                isReady() ? "READY" : "DONE"
        );
    }
}
