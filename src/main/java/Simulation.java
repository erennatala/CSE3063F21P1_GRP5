
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

    public void startRegistration() {//the method starts the registration of students
        //register students
        System.out.println("--Start point of the startRegistration function--");
        Map<Integer, Student> studentMap = studentExpert.getStudentMap();
        Iterator<Map.Entry<Integer, Student>> studentIterator = studentMap.entrySet().iterator();
        while (studentIterator.hasNext()) {
            Map.Entry<Integer, Student> newMap = (Map.Entry<Integer, Student>) studentIterator.next();
            Student student = newMap.getValue();
            Registrator registrator = new Registrator(student, courseExpert);
            registrator.startRegistration();
        }
        System.out.println("--End point of the startRegistration function--");
    }

    public void prepareDepartmentOutput(int firstStudent,int lastStudent){
        departmentOutput.put("First Student",firstStudent);
        departmentOutput.put("Last Student",lastStudent);
        Map<Course,List<Integer>> errorMap = studentExpert.prepareErrorOutput();
        List<String> strList = new ArrayList<>();
        for(Course course: errorMap.keySet()){
            List<Integer> tmpList = errorMap.get(course);
            if(tmpList.get(0)!=0)
                strList.add(tmpList.get(0).toString()+" STUDENTS COULDNT REGISTER FOR "+course.getCourseId()+" DUE TO THE COLLIISION PROBLEMS");
            if(tmpList.get(1)!=0)
                strList.add(tmpList.get(1).toString()+" STUDENTS COULDNT REGISTER FOR "+course.getCourseId()+" DUE TO THE NOT IN GRADUATION PROBLEMS");
            if(tmpList.get(2)!=0)
                 strList.add(tmpList.get(2).toString()+" STUDENTS COULDNT REGISTER FOR "+course.getCourseId()+" DUE TO THE PREREQUISITE PROBLEMS");
            if(tmpList.get(3)!=0)
                strList.add(tmpList.get(3).toString()+" STUDENTS COULDNT REGISTER FOR GRADUATION PROJECT");
            if(tmpList.get(4)!=0)
                strList.add(tmpList.get(4).toString()+" STUDENTS COULDNT REGISTER FOR "+course.getCourseId()+" DUE TO THE QUOTA PROBLEMS");
            if(tmpList.get(5)!=0)
                strList.add(tmpList.get(5).toString()+" STUDENTS COULDNT REGISTER FOR "+course.getCourseId()+" DUE TO THE TWO TECHNICAL ELECTIVE PROBLEMS");
            if(tmpList.get(6)!=0)
                strList.add(tmpList.get(6).toString()+" STUDENTS COULDNT REGISTER FOR "+course.getCourseId()+" DUE TO THE UNCOMPLETED CREDIT PROBLEMS");
            departmentOutput.put("Errors",strList);
        }

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

    public void startGrading() {//the method written below starts the grading with using for loop via Grader
        System.out.println("--Start point of the starGrading function--");
        for (Course course : courseExpert.getCourses()) {
            Grader grader = new Grader(course);
            grader.startGrading();
        }
        System.out.println("--End point of the startGrading function--");
    }
    public void assignNextSemester(){//the function assigns the next semester for student
        System.out.println("--Start point of the assignNextSemester function--");
        Map<Integer, Student> studentMap = studentExpert.getStudentMap();
        for (Student student : studentMap.values()) {
            int nextSemesterID = student.getSemester().getSemesterId()+1;
            Semester semester = courseExpert.getSemesterMap().get(nextSemesterID);
            Registrator registrator = new Registrator();
            registrator.assignNextSemester(student,semester);
        }
        courseExpert.clearCourses();
        System.out.println("--End point of the assignNextSemester function--");
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

    public void addAllCoursesTogether() {//method written below provides adding all courses together via courseExpert
       // System.out.println("--Start point of the addAllCoursesTogether function--");
//        List<Course> courses = new ArrayList<>();
//        courses.addAll(courseExpert.getMandatoryCourses());
//        courses.addAll(courseExpert.getTechnicalList());
//        courses.addAll(courseExpert.getFacultyTechnicalList());
//        courses.addAll(courseExpert.getNT_UList());
//        courseExpert.setCourses(courses);
        System.out.println(courseExpert.getCourses().size());
        //System.out.println("--End point of the addAllCoursesTogether function--");
    }
    public void checkTranscriptFolder(){//the function checks for the transcript folder
        //System.out.println("--Start point of the checkTranscriptFolder function--");
        File file = new File("transcripts/");
        if(!file.exists()){//if folder does not exist, it gets created
            file.mkdir();
        }
        //System.out.println("--End point of the checkTranscriptFolder function--");
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
                    int superLast = getLastStudent();
                    lastStudent = inputReader.readStudentJson(superLast, studentExpert, courseExpert.getSemesterMap().get(1));
                    setLastStudent(lastStudent);
                }
                simulateSemester();
            }
            if(season.equalsIgnoreCase("Fall")){
                int superLast = getLastStudent();
                int ls = inputReader.readStudentJson(superLast, studentExpert, courseExpert.getSemesterMap().get(1));
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
        studentExpert.prepareErrorOutput();
        TranscriptWriter transcriptWriter = new TranscriptWriter(studentExpert);
        transcriptWriter.startWriter();
        prepareDepartmentOutput(firstStudent,lastStudent);

    }
}
