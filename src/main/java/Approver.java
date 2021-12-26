import java.util.ArrayList;
import java.util.List;

public class Approver {// Approver class checks the student if he/she is able to take a course

    private Student student;
    private Course course;

    public Approver(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public boolean capacityChecker() {// capacityChecker checks the capacity of related courses for student

        int capacity = course.getCapacity();
        List<Student> studentList = course.getStudents();
        if (capacity > studentList.size()) { // if capacity of the class larger than student number, student can take the class

            return true;
        } else { //if there is not enough capacity it gets quota error and adds it to the student's error list
            QuotaError quotaError = new QuotaError(student, course);
            student.addError(quotaError);
            return false;
        }


    }

    public boolean prerequisiteChecker() {// the method below compares the student's passed courses and prerequisite courses
        List<Course> pastCourses = student.getPastCourses();
        List<Course> prerequisiteCourses = course.getPrerequisites();
        boolean isApproved = true;

        for (Course required : prerequisiteCourses) { //if student have not taken the required class the prerequisite error will be added to student's error list
            if (!pastCourses.contains(required)) {
                PrerequisiteError prerequisiteError = new PrerequisiteError(student, course, required);
                student.addError(prerequisiteError);
                isApproved = false;

            }
        }
        return isApproved;
    }


    public boolean isElectiveTaken() {// checks for the elective courses in student's taken courses if he/she already have taken it

        List<Course> pastCourses = student.getPastCourses();
        List<Course> courseBasket = student.getCourseBasket();
        if (pastCourses.contains(course)) {//if the past courses of the student contains the selected elective course it will return with false statement
            return false;
        } else if (courseBasket.contains(course)) {//if the course basket of the student contains the selective course it will return with false statement
            return false;
        } else return true;
    }


    public boolean approveCourse(Course course) {// approveCourse checks the both method above returns true or false
        boolean capacityCheck = true;
        boolean prerequisiteCheck;
        boolean electiveCheck = true;

        this.course = course;
        if (course instanceof ElectiveCourse) {
            capacityCheck = capacityChecker();
            electiveCheck = isElectiveTaken();
        }
        prerequisiteCheck = prerequisiteChecker();
        return capacityCheck && electiveCheck && prerequisiteCheck;
    }

}
