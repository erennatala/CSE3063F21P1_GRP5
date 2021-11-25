import java.util.ArrayList;
import java.util.Date;

public class Section {

    public Section(int sectionId, ArrayList<Student> students, Date date, Course course, Instructor instructor) {
        this.sectionId = sectionId;
        this.students = students;
        this.date = date;
        this.course = course;
        this.instructor = instructor;
    }

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    private int sectionId;
    private ArrayList<Student> students;
    private Date date;
    private Course course;
    private Instructor instructor;

}
