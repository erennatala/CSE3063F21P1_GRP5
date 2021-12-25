import java.util.*;

public class StudentExpert {
    //private List<Student> students;
    private Map<Integer, Student> studentMap = new HashMap<>();
    private List<Instructor> instructors;

    public StudentExpert() {
    }

    private Student getStudent(int id, String name, String surname, List<String> emails, Semester semester) {
        Student student = new Student(id, name, surname, emails, semester);
        return student;
    }

    public Map<Integer, Student> getStudentMap() {
        return studentMap;
    }

    public void setStudentMap(Map<Integer, Student> studentMap) {
        this.studentMap = studentMap;
    }

    public List<Instructor> getInstructors() {
        return instructors;
    }

    public void setInstructors(List<Instructor> instructors) {
        this.instructors = instructors;
    }

    public void createStudent(int id, String name, String surname, List<String> emails, Semester semester) {
        Random random = new Random();
        Student student = getStudent(id, name, surname, emails, semester);
        List<Instructor> instructors = getInstructors();
        int index = random.nextInt(instructors.size());
        Instructor instructor = instructors.get(index);
        student.setAdvisor(instructor);
        instructor.addAdvisees(student);
        Map<Integer, Student> studentMap = getStudentMap();
        studentMap.put(Integer.valueOf(id), student);
        student.setTranscript(new Transcript(student));
    }

    public void showStudents() {
        Map<Integer, Student> studentMap = this.studentMap;
        Iterator<Map.Entry<Integer, Student>> studentIterator = studentMap.entrySet().iterator();
        while (studentIterator.hasNext()) {
            Map.Entry<Integer, Student> tmpMap = (Map.Entry<Integer, Student>) studentIterator.next();
            System.out.println(tmpMap.getKey() + " = " + tmpMap.getValue());
        }

    }
}
