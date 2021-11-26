import java.util.ArrayList;

public class Instructor extends Person{
    private ArrayList<Student> advisees; //list needs to be corrected it has be to list instead of arrayList which has coming through constructer, there will be list interface instead of collection


    private ArrayList<Course> givenCourses;

    public ArrayList<Student> getAdvisees() {
        return advisees;
    }

    public void setAdvisees(ArrayList<Student> advisees) {
        this.advisees = advisees;
    }

    public ArrayList<Course> getGivenCourses() {
        return givenCourses;
    }

    public void setGivenCourses(ArrayList<Course> givenCourses) {
        this.givenCourses = givenCourses;
    }

    public Instructor(int id, String name, String surname, ArrayList<String> emails) {
        super(id, name, surname, emails);
    }
}
