import java.util.ArrayList;

public class MandatoryCourse extends Course{
    private ArrayList<LabCourse> labList;

    public MandatoryCourse(String courseId, String name, int capacity, float credit, float ects) {
        super(courseId, name, capacity, credit, ects);
    }

    public ArrayList<LabCourse> getLabList() {
        return labList;
    }

    public void setLabList(ArrayList<LabCourse> labList) {
        this.labList = labList;
    }
}
