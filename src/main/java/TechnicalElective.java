public class TechnicalElective extends ElectiveCourse{
    private int minimumCredit;

    public TechnicalElective(String courseId, String name, int capacity, float credit, float ects, int minimumCredit) {
        super(courseId, name, capacity, credit, ects);
        this.minimumCredit = minimumCredit;
    }

    public int getMinimumCredit() {
        return minimumCredit;
    }

    public void setMinimumCredit(int minimumCredit) {
        this.minimumCredit = minimumCredit;
    }
}
