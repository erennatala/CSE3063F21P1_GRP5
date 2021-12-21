import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Section {

    private int sectionId;
    private Course course;
    private Instructor instructor;
    private Map<String, ArrayList<String>> scheduleList;

    public Section(int sectionId, Course course, Instructor instructor, Map<String, ArrayList<String>> scheduleList) {
        this.sectionId = sectionId;
        this.course = course;
        this.instructor = instructor;
        this.scheduleList = scheduleList;
    }

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Map<String, ArrayList<String>> getScheduleList() {return scheduleList;}

    public void setScheduleList(Map<String, ArrayList<String>> scheduleList) {this.scheduleList = scheduleList;}
}
