import java.util.ArrayList;

public class StudentExpert {
    private ArrayList<Student> students;


    public void createStudent(int id, String name, String surname, ArrayList<String> emails){
        Student student = new Student(id,name,surname, emails);
        students.add(student);
    }
    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }
}
