public class TechnicalElective extends ElectiveCourse {//A class for Technical Elective courses and, it extends from ElectiveCourse class
    private final int minimumCredit = 155;

    public TechnicalElective() {
        super();
    }

    public TechnicalElective(String courseId, String name, int capacity, float credit, float ects, Instructor instructor) {
        super(courseId, name, capacity, credit, ects, instructor);
    }

    public int getMinimumCredit() {
        return minimumCredit;
    }

    @Override
    public String toString() {
        return "TechnicalElective{} " + super.toString();
    }
}
