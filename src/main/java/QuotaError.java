public class QuotaError extends Error{//QuotaError class extends from Error class, it will occur when there is not enough capacity for the course that student tries to take

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
    public String raiseError() {
        return "The student could not register for " + course.getCourseId() + " because of a quota problem.";
    }
}
