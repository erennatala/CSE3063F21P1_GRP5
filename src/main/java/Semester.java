import java.util.ArrayList;
import java.util.List;

public class Semester {//A class for semesters
    private Integer semesterId;//semesterId takes the ID of semester
    private List<Course> courseList = new ArrayList<>();//courseList is a ArrayList that contains the courses for related semester
    private String season;//season takes the season of the semester

    public Semester(int semesterId, String season) {
        this.semesterId = semesterId;
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

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public void addCourse(Course course) {
        courseList.add(course);
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

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
        return "Semester{" + "semesterId=" + semesterId + '}';
    }
}
