public class FacultyTechnicalElective extends ElectiveCourse {// A class for Faculty Technical Elective courses and, it extends from ElectiveCourse class

    public FacultyTechnicalElective() {
        super();
    }

    public FacultyTechnicalElective(String courseId, String name, int capacity, float credit, float ects, Instructor instructor) {
        super(courseId, name, capacity, credit, ects, instructor);
    }

    @Override
    public String toString() {
        return "FacultyTechnicalElective{} " + super.toString();
    }
}
