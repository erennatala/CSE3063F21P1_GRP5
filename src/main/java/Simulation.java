public class Simulation {
    private StudentExpert studentExpert;
    private InputReader inputReader;
    private InstructorExpert instructorExpert;

    private Curriculum curriculum = new Curriculum();
    private CourseExpert courseExpert = new CourseExpert(curriculum);

    public Simulation() {}

    public void createSemester() {
        for (int i=1; i<9; i++) {
            Semester semester = new Semester(i);
            curriculum.addSemester(semester);
        }
    }

    public void start() {
        this.studentExpert = new StudentExpert();
        this.inputReader = new InputReader();
        this.instructorExpert = new InstructorExpert();

        createSemester();

        inputReader.readStudentJson(0, studentExpert);
        for (Student student : studentExpert.getStudents())
            System.out.println(student.getId() + " " + student.getName() + " " + student.getSurname());

        System.out.println("************************************************");

//        inputReader.readInstructorJson(instructorExpert);

        inputReader.readCourseJson(courseExpert, instructorExpert);
        for (int i = 0; i < curriculum.getMandatoryCourses().size(); i++) {
//            System.out.println(curriculum.getMandatoryCourses().get(i).getCourseId() + " " + curriculum.getMandatoryCourses().get(i).getName());

        }

        for (Course course : curriculum.getMandatoryCourses()) { //ali, betül
            System.out.println(course.getName() + " " + course.getInstructor().getName());
//        for (Instructor instructor: instructorExpert.getInstructors()) {
//            System.out.println(instructor.getId() + " " + instructor.getName() + " " + instructor.getSurname() + " " + instructor.getGivenCourses());
//        }
        }
    }
}
