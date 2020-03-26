package lab6;

public class Lab6 {
    public static Player Alice, Bob;
    public static City FL, NY;

    public static void printState(){
        System.out.println("\t" + Alice);
        System.out.println("\t" + Bob);
        System.out.println("\tStates of each city:");
        System.out.println("\t" + FL);
        System.out.println("\t" + NY);
    }

    public static void main(String[] args){

        Alice = new Player("Alice", 1000);
        Bob = new Player("Bob", 1500);

        Minister economist1 = new Economist(80, 80, 80);
        Minister scientist1 = new Scientist(100, 90, 100);
        Minister warGeneral1 = new WarGeneral(90, 80, 100);
        Alice.getMinisters().add(economist1);
        Alice.getMinisters().add(scientist1);
        Alice.getMinisters().add(warGeneral1);

        Minister economist2 = new Economist(90, 80, 70);
        Minister scientist2 = new Scientist(60, 90, 80);
        Minister warGeneral2 = new WarGeneral(100, 80, 100);
        Bob.getMinisters().add(economist2);
        Bob.getMinisters().add(scientist2);
        Bob.getMinisters().add(warGeneral2);

        FL = new City(0, "FL", 100, 100, 100);
        NY = new City(1, "NY", 100, 120, 120);
        Alice.getCities().add(FL);
        Bob.getCities().add(NY);

        System.out.println(Alice.getName() + " has ministers: ");
        for (int i=0; i < Alice.getMinisters().size(); i++) {
            System.out.println(Alice.getMinisters().get(i));
        }

        System.out.println(Bob.getName() + " has ministers: ");
        for (int i=0; i<Bob.getMinisters().size(); i++){
            System.out.println(Bob.getMinisters().get(i));
        }
        System.out.println();

        int taxGold, scienceGold;

        // Turn 1:
        //Alice's economist collects tax from Alices' first city: FL
        taxGold = Alice.getMinisters().get(0).collectTax(Alice.getCities().get(0));
        Alice.addGold(taxGold);
        //Bob's scientist collects science points from Bob's first city: NY
        scienceGold = Bob.getMinisters().get(1).collectSciencePoints(Bob.getCities().get(0));
        Bob.addGold(scienceGold);

        System.out.println("States after turn 1:");
        printState();

        //Turn 2:
        //Alice's scientist collects science points from Alice's first city: FL
        scienceGold = Alice.getMinisters().get(1).collectSciencePoints(Alice.getCities().get(0));
        Alice.addGold(scienceGold);
        // Bob's war general recruit troops from Bob's first city: NY
        Bob.getMinisters().get(2).recruitTroops(Bob, Bob.getCities().get(0));

        System.out.println("\nStates after turn 2:");
        printState();

        // Turn3:
        // Alice's war general recruit troops from Alice's first city: FL
        Alice.getMinisters().get(2).recruitTroops(Alice, Alice.getCities().get(0));
        //Bob's economist collects tax from Bob' first city: NY
        taxGold = Bob.getMinisters().get(0).collectTax(Bob.getCities().get(0));
        Bob.addGold(taxGold);

        System.out.println("\nStates after turn 3:");
        printState();

    }

}
