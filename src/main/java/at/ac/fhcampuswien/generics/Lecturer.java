package at.ac.fhcampuswien.generics;

public class Lecturer extends Person {
    private final int SVNR;

    public Lecturer(String name, int SVNR) {
        super(name);
        this.SVNR = SVNR;
    }

    public int getSVNR(){
        return SVNR;
    }
}
