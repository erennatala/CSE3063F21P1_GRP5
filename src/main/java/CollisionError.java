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

    public CollisionError setFirstCourse(Course firstCourse) {
        this.firstCourse = firstCourse;
        return this;
    }

    public Course getSecondCourse() {
        return secondCourse;
    }

    public CollisionError setSecondCourse(Course secondCourse) {
        this.secondCourse = secondCourse;
        return this;
    }

    @Override
    public Course raiseCourse() {
        return secondCourse;
    }

    @Override
    public String raiseError() {
        return "Advisor didn't approve "+secondCourse.getName()+" because of collision with "+firstCourse.getName()+" in schedule";
    }
}
