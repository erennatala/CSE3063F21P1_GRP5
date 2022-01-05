import org.apache.log4j.Logger;
import org.json.JSONObject;
import java.io.*;
import java.util.*;

public class Simulation {
    private StudentExpert studentExpert = new StudentExpert();
    private InputReader inputReader = new InputReader();
    private InstructorExpert instructorExpert = new InstructorExpert();
    private CourseExpert courseExpert = new CourseExpert();
    private TranscriptReader transcriptReader = new TranscriptReader();
    private Map<String, Object> departmentOutput = new HashMap<>();
    private int lastStudent;
    private int firstStudent;

    public Simulation() {
    }

    public void startRegistration() {//the method starts the registration of students
        //register students
        Logger logger = Logger.getLogger(this.getClass().getName());
        logger.info("Starting Registration Process");
        Map<Integer, Student> studentMap = studentExpert.getStudentMap();
        Iterator<Map.Entry<Integer, Student>> studentIterator = studentMap.entrySet().iterator();
        while (studentIterator.hasNext()) {
            Map.Entry<Integer, Student> newMap = (Map.Entry<Integer, Student>) studentIterator.next();
            Student student = newMap.getValue();
            Registrator registrator = new Registrator(student, courseExpert);
            registrator.startRegistration();
        }
        logger.info("Registration Process Finished");
    }

    public void prepareDepartmentOutput(int firstStudent, int lastStudent) {
        Logger logger = Logger.getLogger(this.getClass().getName());
        departmentOutput.put("First Student", firstStudent);
        departmentOutput.put("Last Student", lastStudent);
        Map<Course, List<Integer>> errorMap = studentExpert.prepareErrorOutput();
        List<String> strList = new ArrayList<>();
        for (Course course : errorMap.keySet()) {
            List<Integer> tmpList = errorMap.get(course);
            if (tmpList.get(0) != 0)
                strList.add(tmpList.get(0).toString() + " STUDENTS COULDNT REGISTER FOR " + course.getCourseId() + " DUE TO THE COLLIISION PROBLEMS");
            if (tmpList.get(1) != 0)
                strList.add(tmpList.get(1).toString() + " STUDENTS COULDNT REGISTER FOR " + course.getCourseId() + " DUE TO THE NOT IN GRADUATION PROBLEMS");
            if (tmpList.get(2) != 0)
                strList.add(tmpList.get(2).toString() + " STUDENTS COULDNT REGISTER FOR " + course.getCourseId() + " DUE TO THE PREREQUISITE PROBLEMS");
            if (tmpList.get(3) != 0)
                strList.add(tmpList.get(3).toString() + " STUDENTS COULDNT REGISTER FOR GRADUATION PROJECT");
            if (tmpList.get(4) != 0)
                strList.add(tmpList.get(4).toString() + " STUDENTS COULDNT REGISTER FOR " + course.getCourseId() + " DUE TO THE QUOTA PROBLEMS");
            if (tmpList.get(5) != 0)
                strList.add(tmpList.get(5).toString() + " STUDENTS COULDNT REGISTER FOR " + course.getCourseId() + " DUE TO THE TWO TECHNICAL ELECTIVE PROBLEMS");
            if (tmpList.get(6) != 0)
                strList.add(tmpList.get(6).toString() + " STUDENTS COULDNT REGISTER FOR " + course.getCourseId() + " DUE TO THE UNCOMPLETED CREDIT PROBLEMS");
            departmentOutput.put("Errors", strList);
        }

        try {
            FileWriter outputFile = new FileWriter("DepartmentOutput.json");
            JSONObject obj = new JSONObject(departmentOutput);
            outputFile.write(obj.toString(4));
            outputFile.close();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

    }

    public void readDepartmentOutput() {
        setLastStudent(Integer.parseInt(inputReader.readLastStudent()));
        setFirstStudent(Integer.parseInt(inputReader.readFirstStudent()));
    }

    public void startGrading() {//the method written below starts the grading with using for loop via Grader
        Logger logger = Logger.getLogger(this.getClass().getName());
        logger.info("Starting Grading Process");
        for (Course course : courseExpert.getCourses()) {
            Grader grader = new Grader(course);
            grader.startGrading();
        }
        logger.info("Grading Process Finished");
    }

    public void assignNextSemester() {//the function assigns the next semester for student
        Logger logger = Logger.getLogger(this.getClass().getName());
        logger.info("Assigning all students to next semester");
        Map<Integer, Student> studentMap = studentExpert.getStudentMap();
        for (Student student : studentMap.values()) {
            int nextSemesterID = student.getSemester().getSemesterId() + 1;
            Semester semester = courseExpert.getSemesterMap().get(nextSemesterID);
            Registrator registrator = new Registrator();
            registrator.assignNextSemester(student, semester);
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

    public void checkTranscriptFolder() {//the function checks for the transcript folder
        Logger logger = Logger.getLogger(this.getClass().getName());
        logger.info("Checking for if transcript folder exist");
        File file = new File("Java_Project/src/main/resources/transcripts/");
        if (!file.exists()) {//if folder does not exist, it gets created
            file.mkdir();
        }
    }


    public void simulateSemester() {
        Logger logger = Logger.getLogger(this.getClass().getName());
        logger.info("Simulating Semester");
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

    public void initializeStudents(String generation, String season) {
        Logger logger = Logger.getLogger(this.getClass().getName());

        int firstStudent;
        int lastStudent;
        if (generation.equals("1")) {
            logger.info("Creating Random Students");
            firstStudent = 999;
            lastStudent = 999;
            setLastStudent(lastStudent);
            setFirstStudent(firstStudent);


            int lastSemester;
            if (season.equalsIgnoreCase("Fall")) {

                lastSemester = 7;
            } else if (season.equalsIgnoreCase("Spring")) {
                lastSemester = 8;
            } else {
                logger.error("Error occured while initialization");
                lastSemester = 0;
            }
            // Create Random students for all semester using implemented simulation processes
            for (int i = 1; i < lastSemester; i++) {
                if (i % 2 == 1) {
                    int superLast = getLastStudent();
                    lastStudent = inputReader.readStudentJson(superLast, studentExpert, courseExpert.getSemesterMap().get(1));
                    setLastStudent(lastStudent);
                }
                simulateSemester();
            }
            if (season.equalsIgnoreCase("Fall")) {
                int superLast = getLastStudent();
                int ls = inputReader.readStudentJson(superLast, studentExpert, courseExpert.getSemesterMap().get(1));
                setLastStudent(ls);
            }

        } else {
            logger.info("Reading Students From Existed Transcript");
            readDepartmentOutput();
            transcriptReader.readTranscriptJson(studentExpert, courseExpert, instructorExpert);
            if (season.equalsIgnoreCase("Fall")) {
                int superLast = getLastStudent();
                int ls = inputReader.readStudentJson(superLast, studentExpert, courseExpert.getSemesterMap().get(1));
                setLastStudent(ls);
            }
        }
    }

    public void start() {

        Logger logger = Logger.getLogger(this.getClass().getName());
        logger.info("Simulation Started");
        checkTranscriptFolder();

        // Read other inputs

        inputReader.readInstructorJson(instructorExpert);
        inputReader.readCourseJson(courseExpert, instructorExpert);
        inputReader.readPrerequisiteJson(courseExpert);
        studentExpert.setInstructors(new ArrayList<>(instructorExpert.getInstructorMap().values()));

        // Read Config Parameters
        String season = inputReader.readSeasonParameter();
        String generation = inputReader.readGenerationParameter();

        // Initialize Students Depending On Input Parameters
        logger.info("Initializing Students");
        initializeStudents(generation, season);
        simulateSemester();

        // Write call for transcript after simulation
        logger.info("Preparing Department Output and Transcripts");
        studentExpert.prepareErrorOutput();
        TranscriptWriter transcriptWriter = new TranscriptWriter(studentExpert);
        transcriptWriter.startWriter();
        prepareDepartmentOutput(firstStudent, lastStudent);
        logger.info("Simulation Finished");

    }
}
