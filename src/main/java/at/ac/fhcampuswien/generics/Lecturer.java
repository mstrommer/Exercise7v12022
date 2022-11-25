package at.ac.fhcampuswien.generics;

public class Lecturer extends Person {
    private final int SVNR;
    private String note;

    public Lecturer(String name, int SVNR, String note) {
        super(name);
        this.SVNR = SVNR;
        this.note = note;
    }

    public int getSVNR(){
        return SVNR;
    }
}
