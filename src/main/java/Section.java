import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Section {//section class for courses

    private int sectionId;//sectionId attribute takes the ID of the section
    private Course course;//course attribute shows the course of the section
    private Instructor instructor;//Instructor attribute shows the instructor of the section
    //private Map<String, ArrayList<String>> scheduleList;
    private List<Schedule> scheduleList;//section has a scheduleList as list

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
