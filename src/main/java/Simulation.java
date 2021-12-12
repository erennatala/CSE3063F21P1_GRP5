public class Simulation {
    private StudentExpert studentExpert;
    private InputReader inputReader;
    private InstructorExpert instructorExpert;

    private Curriculum curriculum = new Curriculum();
    private CourseExpert courseExpert = new CourseExpert();

    public Simulation() {}


    /*public void startRegistration(){
        //register students
        for (Student student:studentExpert.getStudents()) {
            Registrator registrator = new Registrator(student,student.getSemester(),curriculum);
            registrator.startRegistration();
        }
    }*/

    /*public void startGrading(){
        //grading operations
        for (Student student :studentExpert.getStudents()
                ) {


        }
    }*/
    public void start() {
        this.studentExpert = new StudentExpert();
        this.inputReader = new InputReader();
        this.instructorExpert = new InstructorExpert();
        inputReader.readInstructorJson(instructorExpert);
        inputReader.readCourseJson(courseExpert, instructorExpert);
        instructorExpert.showInstructors();
        System.out.println(courseExpert.getMandatoryCourses());
        System.out.println(courseExpert.getFacultyTechnicalList());
        System.out.println(courseExpert.getNT_UList());
        System.out.println(courseExpert.getTechnicalList());

        //createSemester();

        //inputReader.readStudentJson(0, studentExpert);



        //
//        for (int i = 0; i < curriculum.getMandatoryCourses().size(); i++) {
////            System.out.println(curriculum.getMandatoryCourses().get(i).getCourseId() + " " + curriculum.getMandatoryCourses().get(i).getName());
//
//        }

//        for (Course course : curriculum.getMandatoryCourses()) { //ali, betül
//            System.out.println(course.getName() + " " + course.getInstructor().getName());
////        for (Instructor instructor: instructorExpert.getInstructors()) {
////            System.out.println(instructor.getId() + " " + instructor.getName() + " " + instructor.getSurname() + " " + instructor.getGivenCourses());
////        }
//        }
        int startIndex = 0;
        /*for(int i=1;i<9;i++){
            if (i%2==1){
                inputReader.readStudentJson(startIndex+((i-1)*35),studentExpert);
                for (Student student :
                        studentExpert.getStudents()) {
                    for (Semester semester :
                            curriculum.getSemesterList()) {
                        if (semester.getSemesterId() == i)
                            student.setSemester(semester);
                    }
                    
                }
            }
            startRegistration();
            studentExpert.showActiveCourses();

            startGrading();


        }*/
//        for (Student student : studentExpert.getStudents())
//            System.out.println(student.getId() + " " + student.getName() + " " + student.getSurname());
//
//        System.out.println("************************************************");


    }
}
