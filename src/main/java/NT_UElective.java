public class NT_UElective extends ElectiveCourse{

    public NT_UElective(String courseId, String name, int capacity, float credit, float ects, Instructor instructor) {
        super(courseId, name, capacity, credit, ects, instructor);
    }

    @Override
    public String toString() {
        return "NT_UElective{} " + super.toString();
    }
}
