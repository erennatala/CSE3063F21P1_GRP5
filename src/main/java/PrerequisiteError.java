public class PrerequisiteError extends Error{
    private Course course;
    private Course required;
    public PrerequisiteError(Student student) {
        super(student);
    }

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
    public void raiseError() {
        System.out.println("The system didn't allow "+course.getCourseId()+" Because student failed prerequisite "+ required.getCourseId());
    }
}