package lab4;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Cleaner {
// @formatter:off
    private static Tasks tasks = Tasks.getTasksObj(); //need to be static because it is used in  printAllCleaners static method
    private int cleanerID;
    private String name;          // This is the name of the cleaner
    private Date joinDate;        // This is the join date of the cleaner
    private Date currentDate;     // This is the current date, use for calculating how long the cleaner has joined the company
    private Integer numOfTasks=0; // This is holding the amount of tasks assigned to the cleaner, it is a wrapper class for int, see the lecture note "Strings and Wrapper Classes" for the details
    private static int numOfCleaners=0;  // Number of cleaner(s) created so far
    private static int maxNumOfTasks=3;  //Max number of tasks could be assigned to a cleaner
// @formatter:on


    public Cleaner(String name, String joinDate) {//construct new Cleaner obj, cleanerID equals to numOfCleaners, after that add 1 to numOfCleaners to update it
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Hong_Kong"));//set the timezone to HKT
        cleanerID = numOfCleaners;                 // joinDate = the date/time the obj is constructed,
        numOfCleaners++;
        this.name = name;                        // no msg is displayed when this constructor is running
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss"); // tells Java the format of the Date information being stored in a String

        try {// ignore this part at this point, we add this because the parse method of Dateformat class would generate an exception, and in Java we need to use try-catch to enclose any method that would generate this kind of exception
            this.joinDate = df.parse(joinDate);


        } catch (ParseException e) {
            e.printStackTrace();
        }

        currentDate = new Date();
        int joinedDays = (int) ((currentDate.getTime() - this.joinDate.getTime()) / (24 * 60 * 60 * 1000)); //when you do the division (long)/(int), the result will be long, so we need to cast it to int
        System.out.printf("Cleaner %s joined the company on %s, he/she has joined the company for %d days.\n", this.name, this.joinDate, joinedDays);

    }


    public static int returnNumOfCleaners() {
        return numOfCleaners;
    }

    public Integer returnID() {
        return cleanerID;
    }


    public void addToTask(Integer tasksID) {
        // add this cleaner to the task with ID equals to taskID
        // if this cleaner has been assigned maxNumOfTasks amount of tasks return immediately
        // otherwise try to add him/her to the task using the provided addCleaner method of the tasks object
        // the provided addCleaner method will return true if the cleaner is successfully added to the task
        // otherwise the addCleaner method will return false. Use the return value to output proper sentence (see the lab description)
        // if the cleaner is successfully added to a task, remember to increase his/her numOfTasks by 1.
        if (this.numOfTasks == this.maxNumOfTasks) {
            System.out.println("Staff " + this.name + " not added to the cleaning task " + tasksID + ", max number of tasks assigned");
            return;
        }

        boolean result = tasks.addCleaner(this, tasksID);


     if (result) {
         this.numOfTasks++;
         System.out.println("Cleaner " + this.name + " joins the cleaning task " + tasksID + ".");
         return;
        }

        else {
            System.out.println("Cleaner " + this.name + " is already in the cleaning task " + tasksID + ", not being added again!");
        }

    }


    public void leaveTask(Integer tasksID) {
        // remove the cleaner from the task with ID equals to taskID using the provided removeCleaner method of the tasks object
        // the removeCleaner method will remove the cleaner if he/she is found in the task, otherwise it will return false
        // use this return value to decide what to be outputted (see the lab description)

        boolean result = tasks.removeCleaner(this, tasksID);

        if (result) {
            System.out.println("Cleaner " + this.name + " has left the tasks " + tasksID + ".");
        }
        else {
            System.out.println("Cleaner " + this.name + " is not in the tasks " + tasksID + ", not left!");
        }
    }


    public static void printAllCleaners(Integer tasksID) {
        // get all the cleaners working for the task with ID equals to taskID using the getAllCleaners method provided
        // the getAllCleaners method will return an array of cleaners, output the proper message according to the return value from getAllCleaners method
        // see the lab description for what to be outputted.

        Cleaner [] cleans = tasks.getAllCleaners(tasksID);
        System.out.println("The following is/are all the cleaner(s) of the task " + tasksID + ":");
        for (int i = 0; i < cleans.length; i++) {
            if (i == cleans.length - 1) {
                System.out.print(cleans[i].name);
            } else {
                System.out.print(cleans[i].name + ",");
            }
        }
        System.out.println();
    }


    public static void printCleanerAllocation() {
        // print the total number of cleaner(s) that has/have been assigned to each of the tasks,
        // when there is no tasks (i.e. allTaskIDs[] is empty output "No tasks!"
        // the following line has been provided to you. a[] will be holding all the task IDs of the tasks in the system
        // output message according to the size of a[].
        // again see the lab description to see what to output.
        Integer[] allTaskIDs = tasks.getAllTaskIDs();
        if (allTaskIDs.length == 0) {
            System.out.println("No tasks!");
            return;
        }
        for (int i = 0; i < allTaskIDs.length; i++) {
            Cleaner [] cleans = tasks.getAllCleaners(allTaskIDs[i]);
            int num = cleans.length;
            System.out.println("This cleaning tasks " + allTaskIDs[i] + ", has a total of " + num + " cleaner(s) working on it.");
        }
    }

}