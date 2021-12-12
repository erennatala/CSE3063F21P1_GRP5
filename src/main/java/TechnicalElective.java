public class TechnicalElective extends ElectiveCourse{
    final int minimumCredit = 155;

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
