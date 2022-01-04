import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.logging.LogManager;

public class Registrator {//A class for registration process

    private Student student;//attribute for student
    private Random randomGenerator = new Random();//random generator
    private Approver approver;//attribute for approver
    private CourseExpert courseExpert;//courseExpert attribute

    public Registrator() {

    }

    public Registrator(Student student, CourseExpert courseExpert) {
        this.student = student;
        this.courseExpert = courseExpert;
        this.approver = new Approver(student);
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

//    public Random getRandomGenerator() {
//        return randomGenerator;
//    }
//
//    public void setRandomGenerator(Random randomGenerator) {
//        this.randomGenerator = randomGenerator;
//    }

//    public Approver getApprover() {
//        return approver;
//    }
//
//    public void setApprover(Approver approver) {
//        this.approver = approver;
//    }

    public CourseExpert getCourseExpert() {
        return courseExpert;
    }

    public void setCourseExpert(CourseExpert courseExpert) {
        this.courseExpert = courseExpert;
    }

    public void addBasket(Course course) {
        student.addCourseToBasket(course);
    }

    public void assignNextSemester(Student student,Semester semester) {//the method given below assigns the next semester via active courses of the student
        try {
            // student.gradeMap ?
            Transcript transcript = student.getTranscript();
            double activeGrade = transcript.getActiveGrade();
            double cumulativeGrade = transcript.getCumulativeGrade() + activeGrade;
            transcript.setCumulativeGrade(cumulativeGrade);
            student.calculateGPA();
            transcript.setActiveGrade(0);

            transcript.addGPA();
            transcript.setActiveCredit(0);
            student.setGpa(0);
            student.calculateCGPA();
            student.setSemester(semester);
            transcript.addSemester(semester);
            student.clearActiveCourses();
        }catch(NullPointerException e){

        }
    }

    public void addBasketToActiveCourse() {
        //the function creates lists for course basket and failed courses and makes a compare and remove process.
        student.basketToActiveCourses();
        student.addActiveCredit();
        student.clearCourseBasket();
    }

    public Course selectRandomElective(Course course) {//A method for selecting elective course randomly
        int index;
        Course elective = null;
        do {
            if (course instanceof TechnicalElective) {//first condition looks for the Technical Elective courses
                index = randomGenerator.nextInt(courseExpert.getTechnicalList().size());
                elective = courseExpert.getTechnicalList().get(index);
            } else if (course instanceof NT_UElective) {//second condition checks for the NT_UE Elective courses
                index = randomGenerator.nextInt(courseExpert.getNT_UList().size());
                elective = courseExpert.getNT_UList().get(index);
            } else if (course instanceof FacultyTechnicalElective) {//third condition checks for the Faculty Technical Elective Courses
                index = randomGenerator.nextInt(courseExpert.getFacultyTechnicalList().size());
                elective = courseExpert.getFacultyTechnicalList().get(index);
            }
            // If course is not approved or it already exist in student basket or taken course ?
        } while (!approver.approveCourse(elective));
        return elective;

    }

    public void startRegistration() {//the method given below starts the registration process

        Semester semester = student.getSemester();
        Logger logger = Logger.getLogger(this.getClass().getName());
        logger.info("Registering "+student.getName()+" "+student.getSurname()+" to "+semester.getSemesterId()+". semester");
        // Select Failed courses with matching semester
        for (Course course : student.getFailedCourses()) {
            if (approver.approveCourse(course)) addBasket(course);

        }

        List<Course> NontakenCourseList = student.getNonTakenCourses();
        Iterator<Course> iterator = NontakenCourseList.iterator();

        // Take courses from Nontaken
        while (iterator.hasNext()) { //iterator run via while loop
            Course next = iterator.next();
            if (next instanceof ElectiveCourse) {
                Course course = selectRandomElective(next);

                if (approver.approveCourse(course)) {
                    addBasket(course);
                    iterator.remove();
                }
            }
            else if(approver.approveCourse(next)) {
                addBasket(next);
                iterator.remove();
            }


        }

        for (Course course : semester.getCourseList()) {//for loop gets into semester's course list

            if (course instanceof ElectiveCourse) {
                Course elective = selectRandomElective(course);
                addBasket(elective);
            } else if (approver.approveCourse(course)) {
                addBasket(course);
            }
        }
        //send instructor approval
        logger.info("Sending course basket to advisor approval");
        Instructor instructor = student.getAdvisor();
        instructor.approveStudentBasket(student);
        addBasketToActiveCourse();



    }

}

