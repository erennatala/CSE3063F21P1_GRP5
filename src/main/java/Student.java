import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student extends Person {
    private Semester semester;
    private Boolean IsBasketApproved;
    private Grade grade;
    private Map<Course,Grade> gradeMap = new HashMap<>();
    private float gpa;
    private float cgpa;
    private Instructor advisor;
    private int completedCredit;
    private List<Course> activeCourses = new ArrayList<>();
    private List<Course> courseBasket = new ArrayList<>();
    private List<Course> pastCourses = new ArrayList<>();
    private List<Course> nonTakenCourses = new ArrayList<>();
    private List<Course> failedCourses = new ArrayList<>();
    private List<Error> errors = new ArrayList<>();

    public Student() {
    }

    public Student(int id, String name, String surname) {
        super(id, name, surname);
    }

    public Student(int id, String name, String surname, List<String> emails) {
        super(id, name, surname, emails);
    }

    public Student(int id, String name, String surname, List<String> emails, Semester semester) {
        super(id, name, surname, emails);
        this.semester = semester;
    }

    public Map<Course, Grade> getGradeMap() {
        return gradeMap;
    }

    public Student setGradeMap(Map<Course, Grade> gradeMap) {
        this.gradeMap = gradeMap;
        return this;
    }

    //This can be achieved by getters and setters at StudentExpert
    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public Boolean getBasketApproved() {
        return IsBasketApproved;
    }

    public void setBasketApproved(Boolean basketApproved) {
        IsBasketApproved = basketApproved;
    }

    public float getGpa() {
        return gpa;
    }

    public void setGpa(float gpa) {
        this.gpa = gpa;
    }

    public float getCgpa() {
        return cgpa;
    }

    public void setCgpa(float cgpa) {
        this.cgpa = cgpa;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Instructor getAdvisor() {
        return advisor;
    }

    public void setAdvisor(Instructor advisor) {
        this.advisor = advisor;
    }

    public int getCompletedCredit() {
        return completedCredit;
    }

    public void setCompletedCredit(int completedCredit) {
        this.completedCredit = completedCredit;
    }

    public List<Course> getActiveCourses() {
        return activeCourses;
    }

    public void setActiveCourses(List<Course> activeCourses) {
        this.activeCourses = activeCourses;
    }

    public void addActiveCourse(Course course) {
        this.activeCourses.add(course);
    }

    public List<Course> getCourseBasket() {
        return courseBasket;
    }

    public void setCourseBasket(List<Course> courseBasket) {
        this.courseBasket = courseBasket;
    }

    public void addCourseToBasket(Course course) {
        this.courseBasket.add(course);
    }

    public List<Course> getPastCourses() {
        return pastCourses;
    }

    public void setPastCourses(List<Course> pastCourses) {
        this.pastCourses = pastCourses;
    }

    public void addPastCourse(Course course) {
        this.pastCourses.add(course);
    }

    public List<Course> getNonTakenCourses() {
        return nonTakenCourses;
    }

    public void setNonTakenCourses(List<Course> nonTakenCourses) {
        this.nonTakenCourses = nonTakenCourses;
    }

    public void addNonTakenCourse(Course course) {
        this.nonTakenCourses.add(course);
    }

    public List<Course> getFailedCourses() {
        return failedCourses;
    }

    public void setFailedCourses(List<Course> failedCourses) {
        this.failedCourses = failedCourses;
    }

    public void addFailedCourse(Course course) {
        this.failedCourses.add(course);
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    public void addError(Error error) {
        this.errors.add(error);
    }


    public void showActiveCourse() {
        System.out.println(getName() + " " + getSurname());
        for (Course course : activeCourses) {
            System.out.println(course.getName());
        }
    }

    @Override
    public String toString() {
        return "Student{" + "semester=" + semester + "} " + super.toString();
    }
}
