public class UncompletedCreditError extends Error{
    private Course course;

    public UncompletedCreditError(Student student, Course course) {
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
    public String raiseError() {
        return "The advisor didn't approve " + course.getCourseId() + " because student completed credits < 155";
    }
}
