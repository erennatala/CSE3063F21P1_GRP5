import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Grader {

    private Course course;


    private final Map<Integer,String> DDSTable = Map.ofEntries(
            Map.entry(100,"AA"),
            Map.entry(95,"AA"),
            Map.entry(90,"AA"),
            Map.entry(85,"BA"),
            Map.entry(80,"BB"),
            Map.entry(75,"CB"),
            Map.entry(70,"CC"),
            Map.entry(65,"CC"),
            Map.entry(60,"DC"),
            Map.entry(55,"DC"),
            Map.entry(50,"DD"),
            Map.entry(45,"FD"),
            Map.entry(0,"FF")
    );
    private final Map<String, Double> creditTable = Map.ofEntries(
            Map.entry("AA", 4.00),
            Map.entry("BA",3.50),
            Map.entry("BB", 3.00),
            Map.entry("CB",2.50),
            Map.entry("CC",2.00),
            Map.entry("DC",1.50),
            Map.entry("DD",1.00),
            Map.entry("FD", 0.50),
            Map.entry("FF",0.00)
    );

    public Grader(Course course) {
        this.course = course;
    }

    public Course getCourse() {
        return course;
    }

//    public void calculateCGPA(Student student){
//        List<Course> courses = new ArrayList<>();
//        courses.addAll(student.getFailedCourses());
//        courses.addAll(student.getPastCourses());
//        double allTakenCredits = 0;
//
//        for(Course c : courses){
//            allTakenCredits += course.getCredit();
//        }
//        double currentCredit = 0;
//        for(Course course: student.getActiveCourses()){
//            currentCredit += course.getCredit();
//        }
//        double CGPA = (student.getCgpa()*allTakenCredits + currentCredit * student.getGpa())/;
//
//    }

    public void setCourse(Course course) {
        this.course = course;
    }

    private int round(double successGrade) {
        return (int)(Math.floor(successGrade / 5) * 5);
    }

    private void initializeGrade(Student student, int YSS, int YIS) {
        Grade grade = new Grade(student,course, YSS, YIS);
        student.getGradeMap().put(course,grade);
        double successGrade = grade.getSuccessGrade();
        int roundedGrade = round(successGrade);
        if(roundedGrade<45){
            roundedGrade = 0;
        }
        assignLetterGrade(roundedGrade,grade);
        addFailPassed(student,grade.getLetterGrade());
        student.getTranscript().addCourse(course);

        String letterGrade = grade.getLetterGrade();
        double activeGrade = student.getTranscript().getActiveGrade();
        activeGrade += creditTable.get(letterGrade) * course.getCredit();
        student.getTranscript().setActiveGrade(activeGrade);
    }

    private void addFailPassed(Student student,String letterGrade){
        if("FF".equals(letterGrade)){
            student.addFailedCourse(course);
        }else{
            int credit = (int)course.getCredit();
            credit += student.getCompletedCredit();
            student.setCompletedCredit(credit);
            student.addPastCourse(course);
        }
    }

    private void assignLetterGrade(int roundedGrade,Grade grade){
        String letterGrade = DDSTable.get(roundedGrade);
        grade.setLetterGrade(letterGrade);
    }

    private int generateRandomGrade(){
        // Generates random number less than 45 with 0.02 probability, greater than 45 with 0.98 probability
        // Calculated SuccessGrade has approximately 0.1 probability to be less than 45
        Random random = new Random();
        int num = random.nextInt(50);
        if(num<=1){
            return random.nextInt(45);
        }
        else{
            return random.nextInt(100-45+1)+45;
        }
    }
    private void assignRandomGrade() {
        Course course = this.course;
        for (Student student : course.getStudents()) {
            int YSS = generateRandomGrade();
            int YIS = generateRandomGrade();
            initializeGrade(student, YSS, YIS);
        }
    }
    public void startGrading() {
        assignRandomGrade();
    }

}
