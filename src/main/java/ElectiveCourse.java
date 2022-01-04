public class ElectiveCourse extends Course {//A class for elective courses and it extends from Course class

    public ElectiveCourse() {
        super();
    }

    public ElectiveCourse(String courseId, String name, int capacity, float credit, float ects, Instructor instructor) {
        super(courseId, name, capacity, credit, ects, instructor);
    }
}
