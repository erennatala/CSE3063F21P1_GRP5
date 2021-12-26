public abstract class Error {//A class for errors which may occur for student's course taking process
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

    public Course raiseCourse(){

        return null;
    }
    public String raiseError(){
        return "Abstract Error";
    }
}
