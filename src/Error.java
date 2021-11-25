public abstract class Error {
    private Student student;

    public Error(Student student) {
        this.student = student;
    }

    public void raiseError(){
        System.out.println("error");
    }
}
