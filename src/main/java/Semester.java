import java.util.ArrayList;
import java.util.List;

public class Semester {
    private Integer semesterId;
    private List<Course> courseList= new ArrayList<Course>();
    private String season;

    public Semester(int semesterId, String season) {
        this.semesterId = semesterId; this.season = season;
    }

    public Semester(int semesterId, ArrayList<Course> courseList, String season) {
        this.semesterId = semesterId;
        this.courseList = courseList;
        this.season = season;
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

    public String getSeason() {return season;}

    public void setSeason(String season) {this.season = season;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Semester)) return false;

        Semester semester = (Semester) o;

        return getSemesterId().equals(semester.getSemesterId());
    }

    @Override
    public int hashCode() {
        return getSemesterId().hashCode();
    }

    @Override
    public String toString() {
        return "Semester{" +
                "semesterId=" + semesterId +
                '}';
    }
}
