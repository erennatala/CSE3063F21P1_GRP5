import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Simulation {
    private StudentExpert studentExpert = new StudentExpert();
    private InputReader inputReader = new InputReader();
    private InstructorExpert instructorExpert = new InstructorExpert();
    private CourseExpert courseExpert = new CourseExpert();

    public Simulation() {}


    public void startRegistration(){
        //register students
        Map<Integer,Student> studentMap = studentExpert.getStudentMap();
        Iterator<Map.Entry<Integer,Student>> studentIterator =  studentMap.entrySet().iterator();
        while(studentIterator.hasNext()){
            Map.Entry<Integer,Student> newMap = (Map.Entry<Integer, Student>) studentIterator.next();
            Student student = newMap.getValue();
            Registrator registrator = new Registrator(student,getCourseExpert());
            registrator.startRegistration();
            break;
        }

    }

    /*public void startGrading(){
        //grading operations
        for (Student student :studentExpert.getStudents()
                ) {


        }
    }*/

    public StudentExpert getStudentExpert() {
        return studentExpert;
    }

    public void setStudentExpert(StudentExpert studentExpert) {
        this.studentExpert = studentExpert;
    }

    public InputReader getInputReader() {
        return inputReader;
    }

    public void setInputReader(InputReader inputReader) {
        this.inputReader = inputReader;
    }

    public InstructorExpert getInstructorExpert() {
        return instructorExpert;
    }

    public void setInstructorExpert(InstructorExpert instructorExpert) {
        this.instructorExpert = instructorExpert;
    }

    public CourseExpert getCourseExpert() {
        return courseExpert;
    }

    public void setCourseExpert(CourseExpert courseExpert) {
        this.courseExpert = courseExpert;
    }

    public void addAllCoursesTogether(){
        List<Course> courses = new ArrayList<>();
        courses.addAll(courseExpert.getMandatoryCourses());
        courses.addAll(courseExpert.getTechnicalList());
        courses.addAll(courseExpert.getFacultyTechnicalList());
        courses.addAll(courseExpert.getNT_UList());
        courseExpert.setCourses(courses);
    }
    public void start() {
        InputReader inputReader = getInputReader();
        StudentExpert studentExpert = getStudentExpert();
        InstructorExpert instructorExpert = getInstructorExpert();
        CourseExpert courseExpert = getCourseExpert();

        inputReader.readInstructorJson(instructorExpert);
        inputReader.readCourseJson(courseExpert, instructorExpert);
        addAllCoursesTogether();
        inputReader.readPrerequisiteJson(courseExpert);

//        instructorExpert.showInstructors();
//        courseExpert.showMandatoryList();
//        System.out.println("******************************");
//        courseExpert.showFacultyTechnicalList();
//        System.out.println("******************************");
//        courseExpert.showNT_UElectiveList();
//        System.out.println("******************************");
//        courseExpert.showTechnicalElectiveList();
//        System.out.println("******************************");



//        for (int i = 0; i < curriculum.getMandatoryCourses().size(); i++) {
////            System.out.println(curriculum.getMandatoryCourses().get(i).getCourseId() + " " + curriculum.getMandatoryCourses().get(i).getName());
//
//        }

//        for (Course course : curriculum.getMandatoryCourses()) { //ali, betül
//            System.out.println(course.getName() + " " + course.getInstructor().getName());
////        for (Instructor instructor: instructorExpert.getInstructors()) {
////            System.out.println(instructor.getId() + " " + instructor.getName() + " " + instructor.getSurname() + " " + instructor.getGivenCourses());
////        }
//        }
        int startIndex = 0;
        for(int i=1;i<9;i++){
            if (i%2==1){
                inputReader.readStudentJson(startIndex+((i-1)*35),studentExpert,courseExpert.getSemesterMap().get(1));
                break;
//                for (Student student :
//                        studentExpert.getStudents()) {
//                    for (Semester semester :
//                            curriculum.getSemesterList()) {
//                        if (semester.getSemesterId() == i)
//                            student.setSemester(semester);
//                    }
//
//                }
            }
            // after create start registration


        }
        startRegistration();
        //studentExpert.showStudents();
//        for (Student student : studentExpert.getStudents())
//            System.out.println(student.getId() + " " + student.getName() + " " + student.getSurname());
//
//        System.out.println("************************************************");


    }
}
