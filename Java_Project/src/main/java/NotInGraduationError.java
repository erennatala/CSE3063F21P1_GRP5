public class NotInGraduationError extends Error {//NotInGraduationError class extends from Error class and, it will occur when student is not in graduation.
    private Course course;

    public NotInGraduationError(Student student, Course course) {
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
        return "The advisor didn't approve " + course.getCourseId() + " because students can't take FTE in FALL semester unless they are graduating this semester";
    }
}
