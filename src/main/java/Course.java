import java.util.ArrayList;
import java.util.List;

public abstract class Course {
    private String courseId;
    private String name;
    private int capacity;
    private float credit;
    private Semester semester;
    private List<Student> students = new ArrayList<>(); // hashmap olucak
    private float ects;
    private List<Course> prerequisites = new ArrayList<>();
    private List<Section> sectionList = new ArrayList<>();
    private Instructor instructor;

    public Course(String courseId) {
        this.courseId = courseId;
    }

    public Course(String courseId, String name, int capacity, float credit, Semester semester, float ects, Instructor instructor) {
        this.courseId = courseId;
        this.name = name;
        this.capacity = capacity;
        this.credit = credit;
        this.semester = semester;
        this.ects = ects;
        this.instructor = instructor;
    }

    public Course(String courseId, String name, int capacity, float credit, float ects, Instructor instructor) {
        this.courseId = courseId;
        this.name = name;
        this.capacity = capacity;
        this.credit = credit;
        this.ects = ects;
        this.instructor = instructor;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public float getCredit() {
        return credit;
    }

    public void setCredit(float credit) {
        this.credit = credit;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public float getEcts() {
        return ects;
    }

    public void setEcts(float ects) {
        this.ects = ects;
    }

    public List<Course> getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(ArrayList<Course> prerequisites) {
        this.prerequisites = prerequisites;
    }

    public List<Section> getSectionList() {
        return sectionList;
    }

    public void setSectionList(ArrayList<Section> sectionList) {
        this.sectionList = sectionList;
    }

    public Instructor getInstructor() {return instructor;}

    public void setInstructor(Instructor instructor) {this.instructor = instructor;}

    @Override
    public String toString() {
        return "Course{" +
                "courseId='" + courseId + '\'' +
                ", name='" + name + '\'' +
                ", instructor=" + instructor +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;

        Course course = (Course) o;

        return getCourseId().equals(course.getCourseId());
    }

    @Override
    public int hashCode() {
        return getCourseId().hashCode();
    }
}
