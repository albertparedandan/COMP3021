package lab7;

import lab7.exception.TooPoorException;
import lab7.ministers.Minister;

import java.io.IOException;
import java.util.List;

public class Lab7 {
    
    public static void printState(List<Player> players){
        for (Player player : players){
            System.out.println("\t" + player);
            System.out.println("\tStates of each city of "+player.getName()+":");
            for (City city: player.getCities()){
                System.out.println("\t\t" + city);
            }
        }
    }

    public static void main(String[] args) throws IOException, TooPoorException {
        GameLoader gameLoader = new GameLoader();
        gameLoader.loadPlayers("players.txt");
        List<Player> players = gameLoader.getPlayers();

        for (Player player : players) {
            System.out.println(player.getName() + " has ministers: ");
            for (Minister minister: player.getMinisters()) {
                System.out.println(minister);
            }
            System.out.println(player.getName() + " has cities: ");
            for (City city: player.getCities() ) {
                System.out.println(city);
            }
        }
        System.out.println();

        int taxGold, scienceGold;

        //Turn 1:
        //US's scientist collects science points from US's first city: SF
        scienceGold = players.get(0).getMinisters().get(1).collectSciencePoints(players.get(0).getCities().get(0));
        players.get(0).addGold(scienceGold);
        // CN's war general recruit troops from CN's first city: BJ
        players.get(1).getMinisters().get(1).recruitTroops(players.get(1), players.get(1).getCities().get(0));
        System.out.println("\nTurn 1: \n"+ players.get(0).getName()+" collect science points, "+players.get(1).getName()+" recruits troops.");
        printState(players);

        // Turn2:
        // US's war general recruit troops from US's first city: SF

        //TODO:3.1
        // US's war general recruits troops from US's first city SF.
        // use try-catch to invoke the improveCropYield() method.
        // When a TooPoorException is catched, print the message of the exception.
        // Hint: index of the player US: 0
        //      index of the minister warGeneral: 2
        //      index of the city SF: 0
        //      action: recruitTroops
        try {
            players.get(0).getMinisters().get(2).recruitTroops(players.get(0), players.get(0).getCities().get(0));
        }
        catch (TooPoorException e) {
            System.out.println(e.getMessage());
        }

        //CN's economist collects tax from CN' first city: BJ
        taxGold = players.get(1).getMinisters().get(0).collectTax(players.get(1).getCities().get(0));
        players.get(1).addGold(taxGold);

        System.out.println("\nTurn 2: \n"+ players.get(0).getName()+" recruits troops, "+players.get(1).getName()+" collect tax.");
        printState(players);

        System.out.println("\n Now "+players.get(1).getName()+" tries to improve crop yield.");

        //TODO:3.2
        // CN's economist improves crop yields from CN's first city BJ.
        // use try-catch to invoke the improveCropYield() method.
        // When a TooPoorException is catched, print the message of the exception.
        // Hint: index of the player CN: 1
        //      index of the minister economist: 0
        //      index of the city BJ: 0
        //      action: improveCropYield
        try {
            players.get(1).getMinisters().get(0).improveCropYield(players.get(1), players.get(1).getCities().get(0));
        }
        catch (TooPoorException e) {
            System.out.println(e.getMessage());
        }
    }

}
