import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Simulation {
    private StudentExpert studentExpert = new StudentExpert();
    private InputReader inputReader = new InputReader();
    private InstructorExpert instructorExpert = new InstructorExpert();
    private CourseExpert courseExpert = new CourseExpert();
    private TranscriptReader transcriptReader = new TranscriptReader();

    public Simulation() {}


    public void startRegistration(){
        //register students
        Map<Integer,Student> studentMap = studentExpert.getStudentMap();
        Iterator<Map.Entry<Integer,Student>> studentIterator =  studentMap.entrySet().iterator();
        while(studentIterator.hasNext()){
            Map.Entry<Integer,Student> newMap = (Map.Entry<Integer, Student>) studentIterator.next();
            Student student = newMap.getValue();
            //student.setSemester(courseExpert.getSemesterMap().get(8));
            Registrator registrator = new Registrator(student,getCourseExpert());
            // Register 1 Student to 1 semester
            registrator.startRegistration();
            //student.getActiveCourses().forEach(System.out::println);
        }
    }


    public void startGrading(){
        for (Course course : courseExpert.getCourses()) {
            Grader grader = new Grader(course);
            grader.startGrading();
//            for (Student student: course.getStudents()){
//                System.out.println(student.getGradeMap());
//            }
            break;
        }
    }

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
        InputReader inputReader = this.inputReader;
        StudentExpert studentExpert = this.studentExpert;
        InstructorExpert instructorExpert = this.instructorExpert;
        CourseExpert courseExpert = this.courseExpert;

        inputReader.readInstructorJson(instructorExpert);
        inputReader.readCourseJson(courseExpert, instructorExpert);
        addAllCoursesTogether();
        inputReader.readPrerequisiteJson(courseExpert);
        studentExpert.setInstructors(new ArrayList<Instructor>(instructorExpert.getInstructorMap().values()));
//        Course course = courseExpert.getMandatoryCourses().stream()
//                .filter(src -> src.getCourseId().equals("CSE4197"))
//                .findAny()
//                .orElse(null);
//        System.out.println(course);
//        System.out.println("******");


        int startIndex = 0;
        for(int i=1;i<7;i++){
            if (i%2==1){
                inputReader.readStudentJson(startIndex+((i-1)*35),studentExpert,courseExpert.getSemesterMap().get(1));
                break;
            }
            // after create start registration
            startRegistration();

        }
        startRegistration();
        startGrading();

        //transcriptReader.readTranscriptJson(studentExpert, courseExpert, instructorExpert);

    }
}
