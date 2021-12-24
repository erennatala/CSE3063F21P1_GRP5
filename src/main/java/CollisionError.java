import java.util.ArrayList;

public class CollisionError extends Error{
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
    public String raiseError() {
        return "Advisor didn't approve "+secondCourse.getName()+" because of collision with "+firstCourse.getName()+" in schedule";
    }
}
