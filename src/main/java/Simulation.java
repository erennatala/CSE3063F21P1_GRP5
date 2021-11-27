public class Simulation {
    private StudentExpert studentExpert;
    private InputReader inputReader;
    private InstructorExpert instructorExpert;

    public Simulation() {

    }

    public void start(){
        this.studentExpert = new StudentExpert();
        this.inputReader = new InputReader();
        this.instructorExpert = new InstructorExpert();

        inputReader.readStudentJson(0,studentExpert);
        for(Student student: studentExpert.getStudents())
            System.out.println(student.getId()+" "+student.getName()+" "+student.getSurname());

        System.out.println("************************************************");

        inputReader.readInstructorJson(instructorExpert);
        for (Instructor instructor: instructorExpert.getInstructors()) {
            System.out.println(instructor.getId() + " " + instructor.getName() + " " + instructor.getSurname() + " " + instructor.getEmails().get(0));
        }


    }
}
