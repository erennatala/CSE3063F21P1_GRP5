
import org.json.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.*;

public class Simulation {
    private StudentExpert studentExpert = new StudentExpert();
    private InputReader inputReader = new InputReader();
    private InstructorExpert instructorExpert = new InstructorExpert();
    private CourseExpert courseExpert = new CourseExpert();
    private TranscriptReader transcriptReader = new TranscriptReader();
    private Map<String,Object> departmentOutput = new HashMap<>();
    private int lastStudent;
    private int firstStudent;

    public Simulation() {
    }

    public void startRegistration() {
        //register students
        Map<Integer, Student> studentMap = studentExpert.getStudentMap();
        Iterator<Map.Entry<Integer, Student>> studentIterator = studentMap.entrySet().iterator();
        while (studentIterator.hasNext()) {
            Map.Entry<Integer, Student> newMap = (Map.Entry<Integer, Student>) studentIterator.next();
            Student student = newMap.getValue();
            Registrator registrator = new Registrator(student, courseExpert);
            registrator.startRegistration();
        }
    }

    public void prepareDepartmentOutput(int firstStudent,int lastStudent){
        departmentOutput.put("First Student",firstStudent);
        departmentOutput.put("Last Student",lastStudent);
        try {
            FileWriter outputFile = new FileWriter("DepartmentOutput.json");
            JSONObject obj = new JSONObject(departmentOutput);
            outputFile.write(obj.toString(4));
            outputFile.close();
            // Errors
        }catch (IOException e) {

        }

    }

    public void readDepartmentOutput(){
       setLastStudent(Integer.parseInt(inputReader.readLastStudent()));
       setFirstStudent(Integer.parseInt(inputReader.readFirstStudent()));
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

    public void simulateSemester(){

        startRegistration();
        startGrading();
        assignNextSemester();
    }

    public int getLastStudent() {
        return lastStudent;
    }

    public Simulation setLastStudent(int lastStudent) {
        this.lastStudent = lastStudent;
        return this;
    }

    public int getFirstStudent() {
        return firstStudent;
    }

    public Simulation setFirstStudent(int firstStudent) {
        this.firstStudent = firstStudent;
        return this;
    }

    public void initializeStudents(String generation, String season){
            int firstStudent;
            int lastStudent;
        if (generation.equals("1")){

            firstStudent = 999;
            lastStudent = 999;
            setLastStudent(lastStudent);
            setFirstStudent(firstStudent);


            int lastSemester;
            if(season.equalsIgnoreCase("Fall")){

                lastSemester = 7;
            }
            else if (season.equalsIgnoreCase("Spring")){
                lastSemester = 8;
            }
            else{
                System.out.println("Error ocurred while initialization");
                lastSemester = 0;
            }
            // Create Random students for all semester using implemented simulation processes
            for (int i = 1; i < lastSemester; i++) {
                if (i % 2 == 1) {
                    lastStudent = inputReader.readStudentJson(lastStudent, studentExpert, courseExpert.getSemesterMap().get(1));
                    setLastStudent(lastStudent);
                }
                simulateSemester();
            }
            if(season.equalsIgnoreCase("Fall")){
                int superLast = getLastStudent();
                int ls = inputReader.readStudentJson(lastStudent, studentExpert, courseExpert.getSemesterMap().get(1));
                setLastStudent(ls);
            }

        }else {

            readDepartmentOutput();
            transcriptReader.readTranscriptJson(studentExpert, courseExpert, instructorExpert);
            if(season.equalsIgnoreCase("Fall")){
                int superLast = getLastStudent();
                int ls = inputReader.readStudentJson(superLast, studentExpert, courseExpert.getSemesterMap().get(1));
                setLastStudent(ls);
            }

        }

    }

    public void start() {

        checkTranscriptFolder();
        // Read other inputs
        inputReader.readInstructorJson(instructorExpert);
        inputReader.readCourseJson(courseExpert, instructorExpert);
        addAllCoursesTogether();
        inputReader.readPrerequisiteJson(courseExpert);
        studentExpert.setInstructors(new ArrayList<>(instructorExpert.getInstructorMap().values()));

        // Read Config Parameters
        String season = inputReader.readSeasonParameter();
        String generation = inputReader.readGenerationParameter();
        // Initialize Students Depending On Input Parameters

        initializeStudents(generation,season);

        simulateSemester();
        // Write call for transcript after simulation
        //prepareDepartmentOutput();
        TranscriptWriter transcriptWriter = new TranscriptWriter(studentExpert);
        transcriptWriter.startWriter();


        prepareDepartmentOutput(lastStudent,firstStudent);

    }
}
