import java.util.ArrayList;

public class CollisionError extends Error{
    private ArrayList<Course> courses;

    public CollisionError(Student student, ArrayList<Course> courses) {
        super(student);
        this.courses = courses;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }
}
