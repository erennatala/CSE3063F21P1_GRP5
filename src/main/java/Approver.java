import java.util.ArrayList;
import java.util.List;

public class Approver {

    private Student student;
    private Course course;

    public Approver(Student student) {
        this.student = student;
    }

    // getter setter silinince sari highlight ile hata veriyor
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
    // capacityChecker checks the capacity of related courses for student
    public boolean capacityChecker() {

        int capacity = course.getCapacity();
        List<Student> studentList = course.getStudents();
        if (capacity > studentList.size()) {

            return true;
        } else {
            QuotaError quotaError = new QuotaError(student, course);
            //quotaError.raiseError();
            student.addError(quotaError);
            return false;
        }


    }

    // the method below compares the student's passed courses and prerequisite courses
    public boolean prerequisiteChecker() {
        List<Course> pastCourses = student.getPastCourses();
        List<Course> prerequisiteCourses = course.getPrerequisites();
        boolean isApproved = true;

        for (Course required : prerequisiteCourses) {
            if (!pastCourses.contains(required)) {
                PrerequisiteError prerequisiteError = new PrerequisiteError(student, course, required);
                student.addError(prerequisiteError);
                isApproved = false;

            }
        }
        return isApproved;
    }

    public boolean isElectiveTaken() {
        List<Course> pastCourses = student.getPastCourses();
        List<Course> courseBasket = student.getCourseBasket();
        if (pastCourses.contains(course)) {
            return false;
        } else if (courseBasket.contains(course)) {
            return false;
        } else return true;
    }

    // approveCourse checks the both method above returns true or false
    public boolean approveCourse(Course course) {
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
