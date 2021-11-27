public class Simulation {
    private StudentExpert studentExpert;
    private InputReader inputReader;

    public Simulation() {

    }

    public void start(){
        this.studentExpert = new StudentExpert();
        this.inputReader = new InputReader();

        inputReader.readStudentJson(0,studentExpert);
        for(Student student: studentExpert.getStudents())
            System.out.println(student.getId()+" "+student.getName()+" "+student.getSurname());



    }
    

}
