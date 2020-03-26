package lab3;

public class Lab3 {
    public static void main(String[] args){
        int classroomsAdded=0;
        String [] strs=new String[2];
        University u1=new University("HKU","1911");
        University u2=new University("CUHK","1963");
        University u3=new University("HKUST","1991");

        if(u1.createAndAddClassroom( 0,"MBG07", 100)){
            classroomsAdded++;
        }

        if(u1.createAndAddClassroom(0,"CBA", 150)){
            classroomsAdded++;
        }

        if(u1.createAndAddClassroom( 1,"CBA",150)){
            classroomsAdded++;
        }
        if(u2.createAndAddClassroom(0,"SWC LT",495)){
            classroomsAdded++;
        }
        if(u2.createAndAddClassroom(0,"UCA 102",30)){
            classroomsAdded++;
        }
        if(u2.createAndAddClassroom(0,"UCA 104",50)){
            classroomsAdded++;
        }
        if(u2.createAndAddClassroom(1,"UCA 103",45)){
            classroomsAdded++;
        }
        if(u3.createAndAddClassroom(0,"LTF",134)){
            classroomsAdded++;
        }
        if(u3.createAndAddClassroom(1,"2407",126)){
            classroomsAdded++;
        }
        if(u3.createAndAddClassroom(2,"2464",122)){
            classroomsAdded++;
        }
        if(u3.createAndAddClassroom(2,"2464",122)){
            classroomsAdded++;
        }
        if(u3.createAndAddClassroom(3,"LTK",106)){
            classroomsAdded++;
        }
        if(u3.createAndAddClassroom(4,"2302",74)){
            classroomsAdded++;
        }
        if(u3.createAndAddClassroom(5,"4213",67)){
            classroomsAdded++;
        }
        if(u3.createAndAddClassroom(6,"4619",126)){
            classroomsAdded++;
        }
        if(u3.createAndAddClassroom(7,"Acad 1103",100)){
            classroomsAdded++;
        }
        if(u3.createAndAddClassroom(9,"4210",67)){
            classroomsAdded++;
        }
        System.out.println("There are a total of "+University.getNumOfUniversities()+" Universities:\n");
        strs=u1.getInfo();
        System.out.println("This is "+strs[0]+".");
        System.out.println("Established in "+strs[1]+".");
        u1.listAllClassrooms();

        strs=u2.getInfo();
        System.out.println("This is "+strs[0]+".");
        System.out.println("Established in "+strs[1]+".");
        u2.listAllClassrooms();

        strs=u3.getInfo();
        System.out.println("This is "+strs[0]+".");
        System.out.println("Established in "+strs[1]+".");
        u3.listAllClassrooms();


    }
}
