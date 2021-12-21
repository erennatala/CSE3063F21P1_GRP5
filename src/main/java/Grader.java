import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Grader {
    // YSSL: 35
    // BNAL: 35
    // BDKL: 20

    // Steps:
    // Assign random grade out of 100 to all students
    // Check BDKL conditions
    // Choose proper way of grading
    // If number of students has greater grade than 20 is more than 20 follow :
    // N: Number of student included grading (>20)
    // X: Grade of student out of 100
    // Avg: avg. of course
    // Calculate avarage grade of course round two digit after decimal (Only the students which has a grade more than 20 will be included to avarage --> N)
    // Calculate Standard deviation sd = (1/N) * sqrt(N * E(x^2) - (Ex)^2)
    // Calculate T score as given below:
    // T = 10 * ( (X - Avg) / sd) + 50 --> 10z + 50 z: Standard score
    // Choose related avg and tscore from table
    private final int BDKL = 20;
    private final int YSSL = 35;
    private final int BNAL = 35;
    private Course course;
    private double mean;
    private double standardDeviation;
    private int N;


    public Grader(Course course) {
        this.course = course;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public double getMean() {
        return mean;
    }

    public void setMean(double mean) {
        this.mean = mean;
    }

    public double getStandardDeviation() {
        return standardDeviation;
    }

    public void setStandardDeviation(double standardDeviation) {
        this.standardDeviation = standardDeviation;
    }

    public int getN() {
        return N;
    }

    public void setN(int n) {
        N = n;
    }

    private double round(double value, int precision) {
        if (precision < 0) {
            throw new IllegalArgumentException("precision must be positive");
        }
        BigDecimal d = new BigDecimal(Double.toString(value));
        d = d.setScale(precision, RoundingMode.HALF_UP);
        return d.doubleValue();
    }

    private void initializeGrade(Student student, int YSS, int YIS) {
        Course course = getCourse();
        Grade grade = new Grade(course, YSS, YIS);
        double successGrade = grade.getSuccessGrade();
        if (YSS < YSSL || successGrade < BDKL || successGrade < BNAL) {
            grade.setLetterGrade("FF");
        }
        student.setGrade(grade);
    }

    private int assignRandomGrade() {
        Course course = getCourse();
        Random random = new Random();
        int low = 0;
        int high = 101;

        int count = 0;
        for (Student student : course.getStudents()) {
            int YSS = random.nextInt(low + high) + low;
            int YIS = random.nextInt(low + high) + low;
            initializeGrade(student, YSS, YIS);
            double successGrade = student.getGrade().getSuccessGrade();
            if (successGrade >= BDKL) {
                count++;
            }

        }
        return count;

    }

    private void calculateStandardDeviation(List<Double> grades) {
        // Function to calculate standard deviation of given list
        double standardDeviation = 0.0;
        double mean = getMean();
        for (double grade : grades) {
            standardDeviation += Math.pow(grade - mean, 2);
        }
        setStandardDeviation(round(Math.sqrt(standardDeviation / grades.size()), 2));

    }

    private void calculateMean(List<Double> grades) {
        setMean(round(grades.stream().mapToDouble(d -> d).average().orElse(0.0), 2

        ));
//        return grades.stream()
//                .mapToDouble(d -> d)
//                .average()
//                .orElse(0.0)
//                ;
    }

    private double calculateTscore(double successGrade) {
        double mean = getMean();
        double standardDeviation = getStandardDeviation();
        double zScore = (successGrade - mean) / standardDeviation;
        double tScore = 10 * zScore + 50;

        return round(tScore, 2);

    }

    private void assignTscore(List<Student> includedStudents) {
        for (Student student : includedStudents) {
            Grade grade = student.getGrade();
            double tScore = calculateTscore(grade.getSuccessGrade());
            grade.settScore(tScore);
        }
    }

    public void startGrading() {
        // Number of students included grading (Grade > BDKL)
        Course course = getCourse();
        setN(assignRandomGrade());
        // Check BDKL Conditions
        List<Student> includedStudents = new ArrayList<>();
        List<Double> grades = new ArrayList<>();
        for (Student student : course.getStudents()) {
            Grade grade = student.getGrade();
            if (grade.getSuccessGrade() > BDKL) {
                grades.add(grade.getSuccessGrade());
                includedStudents.add(student);
            }
            // Else
        }

        if (N >= 30) {
            calculateMean(grades);
            calculateStandardDeviation(grades);
            assignTscore(includedStudents);
        } else if (N >= 10) {
            // Assign FF below YSSL from YSS
            calculateMean(grades);
            calculateStandardDeviation(grades);
            assignTscore(includedStudents);

            
        }

    }


}
