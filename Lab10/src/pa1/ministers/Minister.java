package pa1.ministers;


import pa1.City;
import pa1.Cost;
import pa1.Player;
import pa1.exceptions.TooPoorException;
import pa1.technologies.Technology;

import java.util.List;

/**
 * An abstract class that represents a minister in the game.
 * All actions in the game are done through ministers.
 * Therefore this class will contain the bulk of your implementation of the game.
 */
public abstract class Minister {

    public int getIntelligence() {
        return intelligence;
    }

    public int getExperience() {
        return experience;
    }

    public int getLeadership() {
        return leadership;
    }

    // Attributes
    protected final int intelligence, experience, leadership;

    private boolean isReady = false;

    /**
     * Initializes the attributes of a minister
     *
     * @param intelligence
     * @param experience
     * @param leadership
     */
    protected Minister(int intelligence, int experience, int leadership) {
        this.intelligence = intelligence;
        this.experience = experience;
        this.leadership = leadership;
    }

    /**
     * @return Whether or not the minister is ready
     */
    public boolean isReady() {
        return isReady;
    }

    /**
     * Minister by default does not provide improvement discount rate
     * therefore this method always returns 1.
     * This behavior may be overridden in a sub-class
     *
     * @return 1
     */
    public double getImprovementDiscountRate() {
        return 1;
    }

    /**
     * Minister by default does not provide tech discount rate
     * therefore this method always returns 1.
     * This behavior may be overridden in a sub-class
     *
     * @return 1
     */
    public double getTechDiscountRate() {
        return 1;
    }

    /**
     * Changes isReady to true
     */
    public void beginTurn() {
        isReady = true;
    }

    /**
     * Changes isReady to false
     */
    public void endTurn() {
        isReady = false;
    }


    /**
     * Collect gold from a city
     * amount collected = (city population + minister experience + minister leadership) * (# of banks  + 1) * 0.2
     *
     * @param city to collect gold from
     * @return amount of gold collected
     */
    public int collectTax(City city) {
        float tax = (city.getPopulation() + experience + leadership) * (city.getBanks() + 1) * 0.2f;
        return Math.round(tax);
    }

    /**
     * Collect science points from a city
     * amount collected = (city population + minister experience + minister intelligence) * (# of universities  + 1) * 0.2
     *
     * @param city to collect science points from
     * @return amount of science points collected
     */
    public int collectSciencePoints(City city) {
        float points = (city.getPopulation() + experience + intelligence) * (city.getUniversities() + 1) * 0.2f;
        return Math.round(points);
    }


    /**
     * Collect production points from a city
     * amount collected = (city population + minister intelligence + minister leadership) * (# of roads  + 1) * 0.2
     *
     * @param city to collect production points from
     * @return amount of production points collected
     */
    public int collectProductionPoints(City city) {
        float points = (city.getPopulation() + intelligence + leadership) * (city.getRoads() + 1) * 0.2f;
        return Math.round(points);
    }

    /**
     * Build a bank in the city
     * 1. Get the cost of building a bank, with applied minister discount
     * 2. Check whether player has enough gold and production points
     * 3. If not, throw an exception
     * 4. Subtract the cost from the player's gold and production point
     * 5. Add number of bank in the city by one
     * <p>
     * HINT:
     * - the Cost class has a getDiscountedCost() method
     * - the Minister class has a getImprovementDiscountRate() method, to obtain the
     * discount rate of building a bank
     *
     * @param player
     * @param city
     * @throws TooPoorException
     */
    public void buildBank(Player player, City city) throws TooPoorException {
        Cost cost = city.getBankCost().getDiscountedCost(getImprovementDiscountRate());
        if (player.getGold() < cost.getGold() || player.getProductionPoint() < cost.getProduction()) {
            throw new TooPoorException(player, cost);
        } else {
            player.decreaseGold(cost.getGold());
            player.decreaseProductionPoint(cost.getProduction());
            city.addBank();
        }
    }

    /**
     * Build a road in the city
     * 1. Get the cost of building a road, with applied minister discount
     * 2. Check whether player has enough gold and production points
     * 3. If not, throw an exception
     * 4. Subtract the cost from the player's gold and production point
     * 5. Add number of road in the city by one
     * <p>
     * HINT:
     * - the Cost class has a getDiscountedCost() method
     * - the Minister class has a getImprovementDiscountRate() method, to obtain the
     * discount rate of building a road
     *
     * @param player
     * @param city
     * @throws TooPoorException
     */
    public void buildRoad(Player player, City city) throws TooPoorException {
        Cost cost = city.getRoadCost().getDiscountedCost(getImprovementDiscountRate());
        if (player.getGold() < cost.getGold() || player.getProductionPoint() < cost.getProduction()) {
            throw new TooPoorException(player, cost);
        } else {
            player.decreaseGold(cost.getGold());
            player.decreaseProductionPoint(cost.getProduction());
            city.addRoad();
        }
    }

    /**
     * Build a university in the city
     * 1. Get the cost of building a university, with applied minister discount
     * 2. Check whether player has enough gold and production points
     * 3. If not, throw an exception
     * 4. Subtract the cost from the player's gold and production point
     * 5. Add number of university in the city by one
     * <p>
     * HINT:
     * - the Cost class has a getDiscountedCost() method
     * - the Minister class has a getImprovementDiscountRate() method, to obtain the
     * discount rate of building a university
     *
     * @param player
     * @param city
     * @throws TooPoorException
     */
    public void buildUniversity(Player player, City city) throws TooPoorException {
        Cost cost = city.getUniversityCost().getDiscountedCost(getImprovementDiscountRate());
        if (player.getGold() < cost.getGold() || player.getProductionPoint() < cost.getProduction()) {
            throw new TooPoorException(player, cost);
        } else {
            player.decreaseGold(cost.getGold());
            player.decreaseProductionPoint(cost.getProduction());
            city.addUniversity();
        }
    }

    /**
     * Attack a target city
     * Call the overloaded method below with ministerBonus = 0.8
     * Will be overridden in the WarGeneral class
     *
     * @param attacker       attacking city
     * @param defender       defending city
     * @param attackingTroops         number of troops deployed for the attack
     * @param technologies  technologies owned by the attacking player
     */
    public String attackCity(City attacker, City defender, int attackingTroops, List<Technology> technologies) {
        return attackCity(attacker, defender, attackingTroops, technologies, 0.8);
    }

    /**
     * Attack a target city
     * Attacking city loses troops equal to min(# of troops attacking, # of troops in the defending city)
     * Defending city loses min(
     *     round(ministerBonus * product of tech attack bonuses * # of attacking troops),
     *     # of troops in the defending city
     * )
     *
     * This method is overridden in the WarGeneral class
     *
     * Print the following messages:
     * "[attacker city name] loses [number of troops lost by attacker] troops while attacking"
     * "[defender city name] loses [number of troops lost by defender] troops while defending"
     *
     * @param attacker        attacking city
     * @param defender        defending city
     * @param attackingTroops number of troops deployed for the attack
     * @param technologies  technologies owned by the attacking player
     */
    protected String attackCity(City attacker, City defender, int attackingTroops, List<Technology> technologies, double ministerBonus) {
        int attackerLost = Math.min(attackingTroops, defender.getTroops());

        double techBonus = 1;
        for (Technology tech : technologies) {
            techBonus *= tech.getAttackBonus();
        }

        int defenderLost = Math.min((int) Math.round(ministerBonus * techBonus * attackingTroops), defender.getTroops());

        attacker.decreaseTroops(attackerLost);
        defender.decreaseTroops(defenderLost);

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s loses %d troops while attacking\n", attacker.getName(), attackerLost));
        sb.append(String.format("%s loses %d troops while defending", defender.getName(), defenderLost));

        return sb.toString();
    }

    /**
     * Improve the crop yields in a city
     * Improve Crop yields by 50 for 500 golds
     * If the player does not have enough gold, throw an exception
     * Else decrease 500 golds from the player and improves crops by 50
     * <p>
     * This method is overridden in the Economist class
     *
     * @param player
     * @param city
     * @throws TooPoorException
     */
    public void improveCropYield(Player player, City city) throws TooPoorException {
        int gold = 500;
        int increment = 50;
        if (player.getGold() < gold)
            throw new TooPoorException(player, new Cost(gold, 0, 0));
        player.decreaseGold(gold);
        city.improveCrops(increment);
    }


    /**
     * Recruit troops to be stationed in a city
     * Recruit 50 troops for 500 golds
     * If the player does not have enough gold, throw an exception
     * <p>
     * Overridden in WarGeneral class
     *
     * @param player
     * @param city
     * @throws TooPoorException
     */
    public void recruitTroops(Player player, City city) throws TooPoorException {
        int gold = 500;
        int increment = 50;
        if (player.getGold() < gold)
            throw new TooPoorException(player, new Cost(gold, 0, 0));
        player.decreaseGold(gold);
        city.addTroops(increment);
    }

    /**
     * Upgrades tech
     * 1. Get the cost of upgrading tech, with applied minister discount
     * 2. Check whether player has enough gold, production, and science points
     * 3. If not, throw an exception
     * 4. Subtract the costs from the player's balance
     * 5. Add level of technology by one
     * <p>
     * HINT:
     * - the Cost class has a getDiscounterCost() method
     * - the Minister class has a getTechDiscountRate() method
     *
     * @param player
     * @param technology
     * @throws TooPoorException
     */
    public void upgradeTech(Player player, Technology technology) throws TooPoorException {
        double rate = getTechDiscountRate();
        Cost cost = technology.getUpgradeCost().getDiscountedCost(rate);
        if (player.getGold() < cost.getGold() || player.getProductionPoint() < cost.getProduction() || player.getSciencePoint() < cost.getScience()) {
            throw new TooPoorException(player, cost);
        }
        player.decreaseGold(cost.getGold());
        player.decreaseProductionPoint(cost.getProduction());
        player.decreaseSciencePoint(cost.getScience());
        technology.addLevel();
    }

}

