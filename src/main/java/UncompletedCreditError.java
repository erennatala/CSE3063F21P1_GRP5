public class UncompletedCreditError extends Error{//UncompletedCreditError class extends from Error class, and it will occur if student has any uncompleted credit
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
    public Course raiseCourse() {
        return course;
    }

    @Override
    public String raiseError() {
        return "The advisor didn't approve " + course.getCourseId() + " because student completed credits < 155";
    }
}
