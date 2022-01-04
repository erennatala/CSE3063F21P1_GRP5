import java.util.ArrayList;
import java.util.List;

public abstract class Course { //Courses in the program will run through Course class
    private String courseId; // courseId attribute takes the ID of the course
    private String name; // name attribute takes the name if the course
    private int capacity; // capacity attribute takes the capacity of the course
    private float credit; // credit attribute takes the credits of the course
    private Semester semester;// semester will show the semester of the course
    private List<Student> students = new ArrayList<>(); // List of students shows the students who are registered for the course
    private float ects; // ects attribute takes the ects of the class
    private List<Course> prerequisites = new ArrayList<>();// prerequisites is the list that contains prerequisites courses of the courses
    private Section section; // section attribute takes the sections of the course
    private Instructor instructor; // instructor attribute holds the Instructor of the course

    protected Course() {
    }

    protected Course(String courseId, String name, int capacity, float credit, Semester semester, float ects, Instructor instructor) {
        this.courseId = courseId;
        this.name = name;
        this.capacity = capacity;
        this.credit = credit;
        this.semester = semester;
        this.ects = ects;
        this.instructor = instructor;
    }

    protected Course(String courseId, String name, int capacity, float credit, float ects, Instructor instructor) {
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

    public void clearStudents(){
        this.students.clear();
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public float getEcts() {
        return ects;
    }

//    public void setEcts(float ects) {
//        this.ects = ects;
//    }

    public List<Course> getPrerequisites() {
        return prerequisites;
    }

    public void addPrerequisite(Course course){
        this.prerequisites.add(course);
    }

//    public void setPrerequisites(List<Course> prerequisites) {
//        this.prerequisites = prerequisites;
//    }

    public Instructor getInstructor() {return instructor;}

    public void setInstructor(Instructor instructor) {this.instructor = instructor;}

    public void addStudentToArraylist(Student student) {
        List<Student> students = this.students;
        students.add(student);
    }

    public Section getSection() {
        return section;
    }

    public Course setSection(Section section) {
        this.section = section;
        return this;
    }

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
