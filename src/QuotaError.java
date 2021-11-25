public class QuotaError extends Error{

    private Course course;

    public QuotaError(Student student, Course course) {
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
    public void raiseError() {
        super.raiseError();
    }
}