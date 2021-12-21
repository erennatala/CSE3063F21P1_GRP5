import java.util.*;

public class StudentExpert {
    //private List<Student> students;
    private Map<Integer,Student>studentMap = new HashMap<>();

    public StudentExpert() {
    }

    private Student getStudent(int id, String name,String surname, List<String> emails,Semester semester){
        Student student = new Student(id,name,surname,emails,semester);
        return student;
    }

    public Map<Integer, Student> getStudentMap() {
        return studentMap;
    }

    public void setStudentMap(Map<Integer, Student> studentMap) {
        this.studentMap = studentMap;
    }

    public void createStudent(int id, String name, String surname, List<String> emails,Semester semester){
        Student student = getStudent(id, name, surname, emails,semester);
        Map<Integer,Student> studentMap = getStudentMap();
        studentMap.put(Integer.valueOf(id),student);
    }

    public void showStudents(){
        Map<Integer,Student> studentMap = getStudentMap();
        Iterator<Map.Entry<Integer,Student>> studentIterator = studentMap.entrySet().iterator();
        while(studentIterator.hasNext()){
            Map.Entry<Integer,Student> tmpMap = (Map.Entry<Integer, Student>) studentIterator.next();
            System.out.println(tmpMap.getKey()+" = "+tmpMap.getValue());
        }

    }
}
