public class NT_UElective extends ElectiveCourse {//NT_UElective class extends from ElectiveCourse class, it created for NT_UE elective courses

    public NT_UElective() {
        super();
    }

    public NT_UElective(String courseId, String name, int capacity, float credit, float ects, Instructor instructor) {
        super(courseId, name, capacity, credit, ects, instructor);
    }

    @Override
    public String toString() {
        return "NT_UElective{} " + super.toString();
    }
}
