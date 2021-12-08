import java.util.ArrayList;
import java.util.List;

public class StudentExpert {
    private List<Student> students;

    public StudentExpert() {
        this.students = new ArrayList<Student>();
    }

    private Student getStudent(int id, String name,String surname, List<String> emails){
        Student student = new Student(id,name,surname,emails);
        student.setActiveCourses(new ArrayList<Course>());
        student.setCourseBasket(new ArrayList<Course>());
        student.setPastCourses(new ArrayList<Course>());
        student.setNonTakenCourses(new ArrayList<Course>());
        student.setFailedCourses(new ArrayList<Course>());
        student.setErrors(new ArrayList<Error>());
        return student;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void createStudent(int id, String name, String surname, List<String> emails){
        Student student = getStudent(id, name, surname, emails);
        students.add(student);
    }
    public void showActiveCourses(){
        for (Student student : students) {
            student.showActiveCourse();
        }
    }
}
