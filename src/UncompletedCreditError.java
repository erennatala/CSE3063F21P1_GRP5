public class UncompletedCreditError extends Error{

    public UncompletedCreditError(Student student) {
        super(student);
    }

    @Override
    public void raiseError() {
        super.raiseError();
    }
}
