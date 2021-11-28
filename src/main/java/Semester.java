import java.util.ArrayList;

public class Semester {
    private int semesterId;
    private ArrayList<Course> courseList;

    public Semester(int semesterId) {this.semesterId = semesterId;}

    public Semester(int semesterId, ArrayList<Course> courseList) {
        this.semesterId = semesterId;
        this.courseList = courseList;
    }

    public int getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(int semesterId) {
        this.semesterId = semesterId;
    }

    public ArrayList<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(ArrayList<Course> courseList) {
        this.courseList = courseList;
    }


}
