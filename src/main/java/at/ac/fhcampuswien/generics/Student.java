package at.ac.fhcampuswien.generics;

public class Student extends Person {
    private final int STUDENT_ID;

    public Student(String name, int ID){
        super(name);
        this.STUDENT_ID = ID;
    }

    public int getStudentID(){
        return STUDENT_ID;
    }

}
