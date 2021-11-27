public class TechnicalElective extends ElectiveCourse{
    private int minimumCredit;

    public TechnicalElective(String courseId, String name, int capacity, String classroom, int credit, int requiredCredit, int ects, int curve, int minimumCredit) {
        super(courseId, name, capacity, classroom, credit, requiredCredit, ects, curve);
        this.minimumCredit = minimumCredit;
    }

    public int getMinimumCredit() {
        return minimumCredit;
    }

    public void setMinimumCredit(int minimumCredit) {
        this.minimumCredit = minimumCredit;
    }
}
