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

    private static final Map<Integer,String> DDSTable= Map.of(
            90,"AA",
            85,"BA",
            80,"BB",
            75,"CB",
            65,"CC",
            55,"DC",
            50,"DD",
            45,"FD",
            0,"FF"

    );
    private Course course;

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
