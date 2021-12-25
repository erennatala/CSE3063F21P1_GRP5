import java.util.Map;
import java.util.Random;

public class Grader {

    private Course course;


    private static final Map<Integer,String> DDSTable = Map.ofEntries(
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

    public Grader(Course course) {
        this.course = course;
    }

    public Course getCourse() {
        return course;
    }

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
        student.getTranscript().addCourse(course);
    }
    private void assignLetterGrade(int roundedGrade,Grade grade){
        String letterGrade = DDSTable.get(roundedGrade);
        grade.setLetterGrade(letterGrade);
    }
    private int generateRandomGrade(){
        Random random = new Random();
        int num = random.nextInt(50);
        if(num<=1){
            return random.nextInt(45);
        }
        else{
            int i = random.nextInt(100-45+1)+45;
            return i;
        }
    }
    private void assignRandomGrade() {
        Course course = this.course;
        Random random = new Random();
        int low = 0;
        int high = 101;
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
