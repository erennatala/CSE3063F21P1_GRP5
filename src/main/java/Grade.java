public class Grade {

    private Course course;
    private String letterGrade;
    private double successGrade;
    private double zScore;
    private double tScore;
    private int YSS;
    private int YIS;


    public Grade(Course course, int grade) {
        this.course = course;
        this.successGrade = grade;
    }

    public Grade(Course course, int YSS, int YIS) {
        this.course = course;
        this.YSS = YSS;
        this.YIS = YIS;
        calculateSuccessGrade();
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getLetterGrade() {
        return letterGrade;
    }

    public void setLetterGrade(String letterGrade) {
        this.letterGrade = letterGrade;
    }

    public int getYSS() {
        return YSS;
    }

    public void setYSS(int YSS) {
        this.YSS = YSS;
    }

    public int getYIS() {
        return YIS;
    }

    public void setYIS(int YIS) {
        this.YIS = YIS;
    }

    public double getSuccessGrade() {
        return successGrade;
    }

    public void setSuccessGrade(double successGrade) {
        this.successGrade = successGrade;
    }

    public void calculateSuccessGrade() {
        double sg = (getYSS() * 0.4) + (getYIS() * 0.6);
        setSuccessGrade(sg);
    }

    public double getzScore() {
        return zScore;
    }

    public void setzScore(double zScore) {
        this.zScore = zScore;
    }

    public double gettScore() {
        return tScore;
    }

    public void settScore(double tScore) {
        this.tScore = tScore;
    }
}
