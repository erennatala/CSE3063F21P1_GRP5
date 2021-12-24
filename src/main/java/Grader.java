import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Grader {
    // YSSL: 35
    // BNAL: 35
    // BDKL: 20

    private final int BDKL = 20;
    @SuppressWarnings("FieldCanBeLocal")
    private final int YSSL = 35;
    @SuppressWarnings("FieldCanBeLocal")
    private final int BNAL = 35;
    private Course course;


    private static final Map<Integer,String> DDSTable = Map.ofEntries(
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
        int result = (int)(Math.floor(successGrade / 5) * 5);
        return result;
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
    }
    private void assignLetterGrade(int roundedGrade,Grade grade){
        String letterGrade = DDSTable.get(roundedGrade);
        grade.setLetterGrade(letterGrade);
    }

    private void assignRandomGrade() {
        Course course = this.course;
        Random random = new Random();
        int low = 0;
        int high = 101;
        for (Student student : course.getStudents()) {
            int YSS = random.nextInt(low + high) + low;
            int YIS = random.nextInt(low + high) + low;
            initializeGrade(student, YSS, YIS);
        }
    }
    public void startGrading() {
        assignRandomGrade();
    }


}
