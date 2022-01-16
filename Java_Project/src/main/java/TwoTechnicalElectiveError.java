public class TwoTechnicalElectiveError extends Error {//TwoTechnicalElectiveError class extends from Error class, it will occur if student already taken two Technical Elective courses in Fall semester
    private Course course;

    public TwoTechnicalElectiveError(Student student, Course course) {
        super(student);
        this.course = course;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public Course raiseCourse() {
        return course;
    }

    @Override
    public String raiseError() {
        return "The advisor didn't approve " + course.getCourseId() + " because student already took 2 TE and in FALL semester only 2 TE can be taken.";
    }
}
