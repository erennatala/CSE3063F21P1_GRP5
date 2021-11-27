import java.util.ArrayList;

public class InstructorExpert {

    private ArrayList<Instructor> instructors;

    public InstructorExpert() {
        this.instructors = new ArrayList<Instructor>();
    }

    public void createInstructor(int id, String name, String surname, ArrayList<String> emails) {
        Instructor instructor = new Instructor(id, name, surname, emails);
        instructors.add(instructor);
    }

    public ArrayList<Instructor> getInstructors() { return instructors; }

    public void setInstructors(ArrayList<Instructor> instructors) { this.instructors = instructors; }

}
