public class ProjectError extends Error {//ProjectError class extends from Error class, it may occur when student did not finish the project
    private Course course;

    public ProjectError(Student student, Course course) {
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
        return "The advisor didn't approve " + course.getCourseId() + " because student completed credits < 165";
    }
}
