import java.io.File;
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

    public Simulation() {
    }

    public void startRegistration() {
        //register students
        Map<Integer, Student> studentMap = studentExpert.getStudentMap();
        Iterator<Map.Entry<Integer, Student>> studentIterator = studentMap.entrySet().iterator();
        while (studentIterator.hasNext()) {
            Map.Entry<Integer, Student> newMap = (Map.Entry<Integer, Student>) studentIterator.next();
            Student student = newMap.getValue();
            //student.setSemester(courseExpert.getSemesterMap().get(8));
            Registrator registrator = new Registrator(student, courseExpert);
            // Register 1 Student to 1 semester
            registrator.startRegistration();
            //student.getActiveCourses().forEach(System.out::println);
        }
    }


    public void startGrading() {
        for (Course course : courseExpert.getCourses()) {
            Grader grader = new Grader(course);
            grader.startGrading();
        }
    }
    public void assignNextSemester(){
        Map<Integer, Student> studentMap = studentExpert.getStudentMap();
        for (Student student : studentMap.values()) {
            int nextSemesterID = student.getSemester().getSemesterId()+1;
            Semester semester = courseExpert.getSemesterMap().get(nextSemesterID);
            Registrator registrator = new Registrator();
            registrator.assignNextSemester(student,semester);
        }
        courseExpert.clearCourses();
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

    public void addAllCoursesTogether() {
        List<Course> courses = new ArrayList<>();
        courses.addAll(courseExpert.getMandatoryCourses());
        courses.addAll(courseExpert.getTechnicalList());
        courses.addAll(courseExpert.getFacultyTechnicalList());
        courses.addAll(courseExpert.getNT_UList());
        courseExpert.setCourses(courses);
    }
    public void checkTranscriptFolder(){
        File file = new File("transcripts/");
        if(!file.exists()){
            file.mkdir();
        }
    }

    public void start() {
        checkTranscriptFolder();
        InputReader inputReader = this.inputReader;
        StudentExpert studentExpert = this.studentExpert;
        InstructorExpert instructorExpert = this.instructorExpert;
        CourseExpert courseExpert = this.courseExpert;

        inputReader.readInstructorJson(instructorExpert);
        inputReader.readCourseJson(courseExpert, instructorExpert);
        addAllCoursesTogether();
        inputReader.readPrerequisiteJson(courseExpert);
        studentExpert.setInstructors(new ArrayList<>(instructorExpert.getInstructorMap().values()));

//        for (Course course: courseExpert.getCourses()){
//            //System.out.println(course.getCourseId());
//            try {
//                for (Schedule schedule : course.getSection().getScheduleList()) {
//                    //System.out.println(schedule);
//                }
//            }catch (NullPointerException e){
//                System.out.println(course.getCourseId());
//                e.printStackTrace();
//            }
//
//        }


        int startIndex = 0;
        for (int i = 1; i < 8; i++) {
            if (i % 2 == 1) {
                inputReader.readStudentJson(startIndex + ((i - 1) * 35), studentExpert, courseExpert.getSemesterMap().get(1));
            }
            // after create start registration
            startRegistration();
            startGrading();
            assignNextSemester();

        }
//        startRegistration();
//        startGrading();
        TranscriptWriter transcriptWriter = new TranscriptWriter(studentExpert);
        transcriptWriter.startWriter();
        // 1 semester bittikten sonra gerekli değerleri arttır listleri sıfırla
        // input config

//        transcriptReader.readTranscriptJson(studentExpert, courseExpert, instructorExpert);
//        studentExpert.showStudents();

    }
}
