public class Grade {

    private Course course;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public char getLetterGrade() {
        return letterGrade;
    }

    public void setLetterGrade(char letterGrade) {
        this.letterGrade = letterGrade;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    private char letterGrade;
    private int grade;

    public Grade(Course course, char letterGrade, int grade) {
        this.course = course;
        this.letterGrade = letterGrade;
        this.grade = grade;
    }


}
