package lab3;
import java.util.Arrays;

public class University {
    private String name;
    private String startYear;
    private static int numOfUniversities;
    private int arraySize;
    private int numOfClassrooms;
    private Classroom [] classrooms;

    University(String name, String startYear) {
        this.numOfUniversities++;
        this.arraySize = 3;
        this.classrooms = new Classroom[arraySize];
        this.numOfClassrooms = 0;
        this.name = name;
        this.startYear = startYear;
    }

    String[] getInfo() {
        String [] result = new String[2];
        result[0] = this.name;
        result[1] = this.startYear;
        return result;
    }

    boolean createAndAddClassroom(int roomID, String classroomName, int capacity) {
        if (this.numOfClassrooms == 0) {
            this.classrooms[0] = new Classroom(roomID, classroomName, capacity);
            this.numOfClassrooms++;
            return true;
        }
        else {
            for (int i = 0; i < this.numOfClassrooms; i++) {
                if (this.classrooms[i].getClassroomID() == roomID) {
                    return false;
                }
            }
        }

        Classroom c1 = new Classroom(roomID, classroomName, capacity);
        if (this.numOfClassrooms == this.arraySize) {
            Classroom [] dummy = new Classroom[this.arraySize+1];
            for (int i = 0; i < this.arraySize; i++) {
                dummy[i] = this.classrooms[i];
            }
            dummy[this.arraySize] = c1;
            this.arraySize++;
            this.classrooms = dummy;
            this.numOfClassrooms++;
            return true;
        }
        else {
            this.classrooms[this.numOfClassrooms] = c1;
            this.numOfClassrooms++;
            return true;
        }

    }

    void listAllClassrooms() {
        int cap = getTotalCapacity();
        System.out.println("Here is the list of all the classrooms for " + name + " :");

        for (int i = 0; i < this.numOfClassrooms; i++ ) {
            this.classrooms[i].printClassroom();
        }

        System.out.println("This University has a total seat capacity of " + cap + " seat(s)");
        System.out.println();
    }

    int getTotalCapacity() {
        int result = 0;
        if (numOfClassrooms == 0) {
            return 0;
        }
        for (int i = 0; i < this.numOfClassrooms; i++) {
            result = result + this.classrooms[i].getCapacity();
        }

        return result;
    }

    static int getNumOfUniversities() {
        return numOfUniversities;
    }
}