public abstract class Error {
    private Student student;

    protected Error(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String raiseError(){
        return "Abstract Error";
    }
}
