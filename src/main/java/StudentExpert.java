import org.apache.log4j.Logger;

import java.util.*;

public class StudentExpert {
    //private List<Student> students;
    private Map<Integer, Student> studentMap = new HashMap<>();//attribute studentMap as HashMap
    private List<Instructor> instructors;//instructors as instructor list

    public StudentExpert() {
    }

    public Map<Course,List<Integer>> prepareErrorOutput(){
        Map<Course,List<Integer>> departmentError = new HashMap<>();

        for(Student student: studentMap.values()){
            List<Error> errorList = student.getErrors();
            for(Error error: errorList){
                int index = 7;
                if(error instanceof CollisionError){
                    index = 0;
                }
                else if(error instanceof NotInGraduationError){
                    index = 1;
                }
                else if(error instanceof PrerequisiteError){
                    index = 2;
                }
                else if(error instanceof ProjectError){
                    index = 3;
                }
                else if(error instanceof QuotaError){
                    index = 4;
                }
                else if(error instanceof TwoTechnicalElectiveError){
                    index = 5;
                }
                else if(error instanceof UncompletedCreditError){
                    index = 6;
                }
                Course course = error.raiseCourse();
                Integer count;
                try{
                //count = departmentError.get(course).get(index);
                    if(!departmentError.containsKey(course)){
                        List<Integer> list = new ArrayList<>(Collections.nCopies(7,0));
                        departmentError.put(course, list);
                    }else{
                        count = departmentError.get(course).get(index);
                        count++;
                        departmentError.get(course).set(index,count);
                    }

                }catch(NullPointerException e){
                    count = 0;
                }catch(IndexOutOfBoundsException e) {
                    continue;
                }

            }
        }
       return departmentError;
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

    public void createStudent(int id, String name, String surname, List<String> emails, Semester semester) {//the function creates the student with given parameters such as id, name, surname, emails and semester
        Logger logger = Logger.getLogger(this.getClass().getName());
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
        logger.info("Student created for: " + student);
    }

    public void showStudents() {//via the studentMap the function prints the students by the help of iteration
        Map<Integer, Student> studentMap = this.studentMap;
        Iterator<Map.Entry<Integer, Student>> studentIterator = studentMap.entrySet().iterator();
        while (studentIterator.hasNext()) {
            Map.Entry<Integer, Student> tmpMap = (Map.Entry<Integer, Student>) studentIterator.next();
            System.out.println(tmpMap.getKey() + " = " + tmpMap.getValue());
        }

    }
}
