import java.util.ArrayList;
import java.util.List;

public class Semester {
    private Integer semesterId;
    private List<Course> courseList= new ArrayList<Course>();

    public Semester(int semesterId) {
        this.semesterId = semesterId;
    }

    public Semester(int semesterId, ArrayList<Course> courseList) {
        this.semesterId = semesterId;
        this.courseList = courseList;
    }

    public Integer getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(Integer semesterId) {
        this.semesterId = semesterId;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(ArrayList<Course> courseList) {
        this.courseList = courseList;
    }
    public void addCourse(Course course){
        courseList.add(course);
    }

}
