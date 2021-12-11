import java.util.ArrayList;
import java.util.List;

public class Instructor extends Person{
    private String fullName;
    private List<Student> advisees = new ArrayList<>(); //list needs to be corrected it has be to list instead of arrayList which has coming through constructer, there will be list interface instead of collection
    private List<Course> givenCourses = new ArrayList<>();

    public Instructor(int id, String name, String surname, ArrayList<String> emails) {
        super(id, name, surname, emails);
        this.fullName= name+" "+surname;
    }

    public Instructor(int id, String name, String surname) {
        super(id, name, surname);
        this.fullName = name + " " + surname;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<Student> getAdvisees() {
        return advisees;
    }

    public void setAdvisees(List<Student> advisees) {
        this.advisees = advisees;
    }

    public List<Course> getGivenCourses() {
        return givenCourses;
    }

    public void setGivenCourses(List<Course> givenCourses) {
        this.givenCourses = givenCourses;
    }

    public void addGivenCourse(Course course) {
        givenCourses = getGivenCourses();
        givenCourses.add(course);
    }
    public void addAdvisees(Student student){
        advisees = getAdvisees();
        advisees.add(student);
    }

    @Override
    public String toString() {
        return "Instructor{} " + super.toString();
    }
}
