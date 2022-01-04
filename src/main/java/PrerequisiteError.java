public class PrerequisiteError extends Error {//PrerequisiteError class extends from Error class. It will occur when a student tries to take a course which he/she did not pass the prerequisite course of that course.
    private Course course;
    private Course required;

    public PrerequisiteError(Student student, Course course, Course required) {
        super(student);
        this.course = course;
        this.required = required;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Course getRequired() {
        return required;
    }

    public void setRequired(Course required) {
        this.required = required;
    }

    @Override
    public Course raiseCourse() {
        return course;
    }

    @Override
    public String raiseError() {
        return "The system did not allow to take " + course.getCourseId() + " because student failed prerequisite " + required.getCourseId();
    }
}
