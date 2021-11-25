import java.util.ArrayList;

public abstract class Course {

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
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

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public int getRequiredCredit() {
        return requiredCredit;
    }

    public void setRequiredCredit(int requiredCredit) {
        this.requiredCredit = requiredCredit;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public int getEcts() {
        return ects;
    }

    public void setEcts(int ects) {
        this.ects = ects;
    }

    public int getCurve() {
        return curve;
    }

    public void setCurve(int curve) {
        this.curve = curve;
    }

    public ArrayList<Course> getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(ArrayList<Course> prerequisites) {
        this.prerequisites = prerequisites;
    }

    public ArrayList<Section> getSectionList() {
        return sectionList;
    }

    public void setSectionList(ArrayList<Section> sectionList) {
        this.sectionList = sectionList;
    }

    private int courseId;
    private String name;
    private int capacity;
    private String classroom;
    private int credit;
    private Semester semester;
    private int requiredCredit;
    private ArrayList<Student> students;
    private int ects;
    private int curve;
    private ArrayList<Course> prerequisites;
    private ArrayList<Section> sectionList;

    public Course(int courseId, String name, int capacity, String classroom, int credit, int requiredCredit, int ects, int curve) {
        this.courseId = courseId;
        this.name = name;
        this.capacity = capacity;
        this.classroom = classroom;
        this.credit = credit;
        this.requiredCredit = requiredCredit;
        this.ects = ects;
        this.curve = curve;
    }

}
