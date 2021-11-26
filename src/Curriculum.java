import java.util.ArrayList;

public class Curriculum {

    private ArrayList<Course> courseList;
    public Curriculum(ArrayList<Course> courseList) {
        this.courseList = courseList;
    }

    public ArrayList<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(ArrayList<Course> courseList) {
        this.courseList = courseList;
    }

}
