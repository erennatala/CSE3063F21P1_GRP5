public class CollisionError extends Error{ // CollisionError class extends from Error class, and it checks for if there is a collision in students schedule
    private Course firstCourse;
    private Course secondCourse;

    public CollisionError(Student student, Course firstCourse,Course secondCourse) {
        super(student);
        this.firstCourse = firstCourse;
        this.secondCourse = secondCourse;
    }

    public Course getFirstCourse() {
        return firstCourse;
    }

    public Course getSecondCourse() {
        return secondCourse;
    }

    @Override
    public Course raiseCourse() {
        return secondCourse;
    }

    @Override
    public String raiseError() {
        return "Advisor didn't approve "+secondCourse.getCourseId()+" because of collision with "+firstCourse.getCourseId()+" in schedule";
    }
}
