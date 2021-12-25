import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Registrator {

    private Student student;
    private Random randomGenerator = new Random();
    private Approver approver;
    private CourseExpert courseExpert;

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

    public Random getRandomGenerator() {
        return randomGenerator;
    }

    public void setRandomGenerator(Random randomGenerator) {
        this.randomGenerator = randomGenerator;
    }

    public Approver getApprover() {
        return approver;
    }

    public void setApprover(Approver approver) {
        this.approver = approver;
    }

    public CourseExpert getCourseExpert() {
        return courseExpert;
    }

    public void setCourseExpert(CourseExpert courseExpert) {
        this.courseExpert = courseExpert;
    }

    public void addBasket(Course course) {
        student.addCourseToBasket(course);
    }

    public void addBasketToActiveCourse() {
        Student student = this.student;
        List<Course> courseBasket = student.getCourseBasket();
        List<Course> activeCourses = student.getActiveCourses();
        activeCourses.addAll(courseBasket);
        for (Course course : courseBasket) {
            course.addStudentToArraylist(student);
        }
        courseBasket.clear();
    }

    public Course selectRandomElective(Course course) {

        int index;
        Course elective = null;
        do {
            if (course instanceof TechnicalElective) {
                index = randomGenerator.nextInt(courseExpert.getTechnicalList().size());
                elective = courseExpert.getTechnicalList().get(index);
            } else if (course instanceof NT_UElective) {
                index = randomGenerator.nextInt(courseExpert.getNT_UList().size());
                elective = courseExpert.getNT_UList().get(index);
            } else if (course instanceof FacultyTechnicalElective) {
                index = randomGenerator.nextInt(courseExpert.getFacultyTechnicalList().size());
                elective = courseExpert.getFacultyTechnicalList().get(index);
            }
            // If course is not approved or it already exist in student basket or taken course ?
        } while (!approver.approveCourse(elective));
        return elective;

    }

    public void startRegistration() {

        Semester semester = student.getSemester();
        // Select non taken courses with matching semester
        //
        for (Course course : student.getFailedCourses()) {
            if (approver.approveCourse(course)) addBasket(course);
        }

        List<Course> NontakenCourseList = student.getNonTakenCourses();
        Iterator<Course> iterator = NontakenCourseList.iterator();
        // Take courses from Nontaken
        while (iterator.hasNext()) {
            Course next = iterator.next();
            if (next instanceof ElectiveCourse) {
                Course course = selectRandomElective(next);
                addBasket(course);
                iterator.remove();
            }
            if (approver.approveCourse(next)) {
                addBasket(next);
                iterator.remove();
            }

        }

        for (Course course : semester.getCourseList()) {

            if (course instanceof ElectiveCourse) {
                Course elective = selectRandomElective(course);
                addBasket(elective);
            } else if (approver.approveCourse(course)) {
                addBasket(course);
            } else System.out.println(course);
        }
        //send instructor approval
        Instructor instructor = student.getAdvisor();
        instructor.approveStudentBasket(student);
        addBasketToActiveCourse();

    }

}

