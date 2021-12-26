import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Section {

    private int sectionId;
    private Course course;
    private Instructor instructor;
    //private Map<String, ArrayList<String>> scheduleList;
    private List<Schedule> scheduleList;

    public Section(int sectionId, Course course, Instructor instructor, List<Schedule> scheduleList) {
        this.sectionId = sectionId;
        this.course = course;
        this.instructor = instructor;
        this.scheduleList = scheduleList;
    }

    public List<Schedule> getScheduleList() {
        return scheduleList;
    }

    public Section setScheduleList(List<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
        return this;
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


}
