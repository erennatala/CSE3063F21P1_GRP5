import java.util.ArrayList;

public class MandatoryCourse extends Course{
    private ArrayList<LabCourse> labList;

    public MandatoryCourse(String courseId) {
        super(courseId);
    }

    public MandatoryCourse(String courseId, String name, int capacity, float credit, float ects, Semester semester, Instructor instructor) {
        super(courseId, name, capacity, credit, semester, ects, instructor);
    }

    public ArrayList<LabCourse> getLabList() {
        return labList;
    }

    public void setLabList(ArrayList<LabCourse> labList) {
        this.labList = labList;
    }

    @Override
    public String toString() {
        return "MandatoryCourse{} " + super.toString();
    }
}
