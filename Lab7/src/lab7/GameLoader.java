package lab7;

import lab7.exception.InvalidMinisterTypeException;
import lab7.ministers.Economist;
import lab7.ministers.Minister;
import lab7.ministers.Scientist;
import lab7.ministers.WarGeneral;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.util.Scanner;


/**
 * Holds the necessary components for running the game
 */
public class GameLoader {
    private List<Player> players;

    /**
     * Loads player data from text file
     *
     * @param filename
     * @throws IOException
     */
    public void loadPlayers(String filename) throws IOException {
        //TODO:4
        // 1. use try-with-resources syntax to ensure the file is closed
        // 2. read the number of players, then read an empty line
        // 3. for each player:
        //    3.1 read playerName, gold, sciencePoint, productionPoint, numCities and numMinisters separated by blank characters.
        //    3.2 create a player of corresponding values
        //    3.3 for (int i=1; i<numCities; i++):
        //            read population, troops, cropYields
        //            create a corresponding city object and add to the player's city list
        //    3.4 for (int i=1; i<numMinisters; i++):
        //            read type, intelligence, experience, leadership
        //            use switch-case to create a corresponding minister object and add to the player's minister list
        //            check for invalid formats and throw custom exceptions.
        //              (When there is any invalid minister type, throw InvalidMinisterTypeException.
        //               Only "WarGeneral", "Scientist" and "Economist" are allowed.)
        //    3.5 add the player to the ArrayList<Player> players
        // Hint: use add() method of ArrayList.
        players = new ArrayList<>();
        File inputFile = new File(filename);
        try(
            Scanner reader = new Scanner(inputFile);
        ) {
            int numOfPlayers = reader.nextInt();
            String line = "";
            for (int i = 0; i < numOfPlayers; i++) {
                String name = reader.next();
                int gold = reader.nextInt();
                int sciencePoint = reader.nextInt();
                int productionPoint = reader.nextInt();
                Player newPlayer = new Player(name, gold, sciencePoint, productionPoint);
                this.players.add(newPlayer);

                int numOfCity = reader.nextInt();
                int numOfMin = reader.nextInt();

                for (int j = 0; j < numOfCity; j++) {
                    int cityID = reader.nextInt();
                    String cName = reader.next();
                    int pop = reader.nextInt();
                    int troops = reader.nextInt();
                    int cropYields= reader.nextInt();

                    City temp = new City(cityID, cName, pop, troops, cropYields);
                    this.players.get(i).getCities().add(temp);
                }

                for (int j = 0; j < numOfMin; j++) {
                    String mName = reader.next();
                    int intel = reader.nextInt();
                    int exp = reader.nextInt();
                    int lead = reader.nextInt();

                    if (mName.equals("Scientist")) {
                        Scientist temp = new Scientist(intel, exp, lead);
                        this.players.get(i).getMinisters().add(temp);
                    }
                    else if (mName.equals("Economist")) {
                        Economist temp = new Economist(intel, exp, lead);
                        this.players.get(i).getMinisters().add(temp);
                    }
                    else if (mName.equals("WarGeneral")) {
                        WarGeneral temp = new WarGeneral(intel, exp, lead);
                        this.players.get(i).getMinisters().add(temp);
                    }
                    else {
                        throw new InvalidMinisterTypeException("Only \"WarGeneral\", \"Scientist\" and \"Economist\" are allowed");
                    }
                }
            }
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

}
