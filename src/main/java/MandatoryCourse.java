import java.util.ArrayList;

public class MandatoryCourse extends Course{
    private ArrayList<LabCourse> labList;

    public MandatoryCourse(String courseId, String name, int capacity, String classroom, int credit, int requiredCredit, int ects, int curve) {
        super(courseId, name, capacity, classroom, credit, requiredCredit, ects, curve);
    }

    public ArrayList<LabCourse> getLabList() {
        return labList;
    }

    public void setLabList(ArrayList<LabCourse> labList) {
        this.labList = labList;
    }
}
