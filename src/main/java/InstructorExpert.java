import java.util.ArrayList;

public class InstructorExpert {

    private ArrayList<Instructor> instructors;

    public InstructorExpert() {
        this.instructors = new ArrayList<Instructor>();
    }

    public Instructor createInstructor(int id, String name, String surname) {
        Instructor instructor = new Instructor(id, name, surname);
        instructors.add(instructor);
        return instructor;
    }

    public ArrayList<Instructor> getInstructors() { return instructors; }

    public void setInstructors(ArrayList<Instructor> instructors) { this.instructors = instructors; }

}
