package lab3;

public class Classroom {
    private int roomID;
    private String classroomName;
    private int capacity;

    Classroom(int roomID, String classroomName, int capacity) {
        this.roomID = roomID;
        this.classroomName = classroomName;
        this.capacity = capacity;
    }

    int getClassroomID() {
        return this.roomID;
    }

    String getClassroomName() {
        return this.classroomName;
    }

    int getCapacity() {
        return this.capacity;
    }

    void printClassroom() {
        System.out.println("Classroom: " + this.classroomName + ": Capacity: " + this.capacity);
    }
}
