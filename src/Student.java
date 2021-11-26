import java.util.ArrayList;

public class Student extends Person {
    private Semester semester;
    private ArrayList<Course> courseBasket;
    private Boolean IsBasketApproved;
    private int grade;
    private float gpa;
    private float cgpa;
    private Instructor advisor;
    private int completedCredit;
    private ArrayList<Course> activeCourses;
    private ArrayList<Course> pastCourses;
    private ArrayList<Course> nonTakenCourses;
    private ArrayList<Course> failedCourses;

    public Student(int id, String name, String surname, ArrayList<String> emails) {
        super(id, name, surname, emails);
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public ArrayList<Course> getCourseBasket() {
        return courseBasket;
    }

    public void setCourseBasket(ArrayList<Course> courseBasket) {
        this.courseBasket = courseBasket;
    }

    public Boolean getBasketApproved() {
        return IsBasketApproved;
    }

    public void setBasketApproved(Boolean basketApproved) {
        IsBasketApproved = basketApproved;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
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

    public ArrayList<Course> getActiveCourses() {
        return activeCourses;
    }

    public void setActiveCourses(ArrayList<Course> activeCourses) {
        this.activeCourses = activeCourses;
    }

    public ArrayList<Course> getPastCourses() {
        return pastCourses;
    }

    public void setPastCourses(ArrayList<Course> pastCourses) {
        this.pastCourses = pastCourses;
    }

    public ArrayList<Course> getNonTakenCourses() {
        return nonTakenCourses;
    }

    public void setNonTakenCourses(ArrayList<Course> nonTakenCourses) {
        this.nonTakenCourses = nonTakenCourses;
    }

    public ArrayList<Course> getFailedCourses() {
        return failedCourses;
    }

    public void setFailedCourses(ArrayList<Course> failedCourses) {
        this.failedCourses = failedCourses;
    }




}
