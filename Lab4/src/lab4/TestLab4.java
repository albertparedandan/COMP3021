package lab4;

public class TestLab4 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println("Main in TestLab4 started\n");
        System.out.printf("Total number of cleaner(s) is %d\n\n", Cleaner.returnNumOfCleaners());
        Cleaner alan = new Cleaner("Alan", "12/10/1993 09:00:00");
        Cleaner ben = new Cleaner("Ben", "01/31/1997 09:00:00");
        Cleaner candy = new Cleaner("Candy", "03/22/2008 14:00:00");
        Cleaner david = new Cleaner("David", "07/10/2009 14:00:00");
        Cleaner eric = new Cleaner("Eric", "01/25/2010 10:00:00");
        Cleaner frank = new Cleaner("Frank", "01/25/2010 09:00:00");
        Cleaner gabriel = new Cleaner("Gabriel", "01/02/1991 12:00:00");
        System.out.printf("Total number of cleaner(s) is %d\n\n", Cleaner.returnNumOfCleaners());
        alan.addToTask(1);
        eric.addToTask(1);
        ben.addToTask(2);
        eric.addToTask(2);
        candy.addToTask(2);
        candy.addToTask(2);
        david.addToTask(2);
        eric.addToTask(3);
        eric.addToTask(3);
        frank.addToTask(3);
        eric.addToTask(3);
        frank.addToTask(4);
        eric.addToTask(4);
        gabriel.addToTask(5);

        System.out.println();
        alan.leaveTask(2);
        alan.leaveTask(1);
        alan.addToTask(2);
        System.out.println();
        Cleaner.printAllCleaners(1);
        Cleaner.printAllCleaners(2);
        Cleaner.printAllCleaners(3);
        Cleaner.printAllCleaners(4);
        Cleaner.printAllCleaners(5);
        System.out.println();
        Cleaner.printCleanerAllocation();

    }

}
