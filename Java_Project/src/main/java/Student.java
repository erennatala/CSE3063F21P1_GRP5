import org.apache.log4j.Logger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student extends Person {//A class for Students and it extends from Person class
    private Semester semester;//semester attribute to take semester of students that passed or active
    private Map<Course, Grade> gradeMap = new HashMap<>();//gradeMap is a HashMap for grading
    private float gpa = 0;//attribute for gpa
    private float cgpa = 0;//attribute for cgpa
    private Instructor advisor;//advisor attribute for instructor of the student
    private int completedCredit;//completedCredit takes the credits that student has completed
    private List<Course> activeCourses = new ArrayList<>();//ArrayList takes the active courses of the student
    private List<Course> courseBasket = new ArrayList<>();//ArrayList for student's course basket
    private List<Course> pastCourses = new ArrayList<>();//ArrayList takes the student's past courses
    private List<Course> nonTakenCourses = new ArrayList<>();//ArrayList takes the courses that student has not taken yet
    private List<Course> failedCourses = new ArrayList<>();// ArrayList takes the student's failed courses
    private List<Error> errors = new ArrayList<>();//ArrayList for errors that occurred
    private Transcript transcript;//transcript attribute for student's transcript

    public Student() {
        super();
    }

    public Student(int id, String name, String surname, List<String> emails, Semester semester) {
        super(id, name, surname, emails);
        this.semester = semester;
    }

    public Map<Course, Grade> getGradeMap() {
        return gradeMap;
    }

    //This can be achieved by getters and setters at StudentExpert
    public Semester getSemester() {
        return semester;
    }


    public void setSemester(Semester semester) {
        this.semester = semester;
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

    public void clearActiveCourses() {
        this.activeCourses.clear();
    }

    public void basketToActiveCourses() {
        this.activeCourses.addAll(courseBasket);
    }

    public void clearCourseBasket() {
        this.courseBasket.clear();
    }

    public void addActiveCredit() {

        for (Course course : courseBasket) {
            if (failedCourses.contains(course)) {
                double cumulativeCredit = transcript.getCumulativeCredit();
                cumulativeCredit -= course.getCredit();
                transcript.setCumulativeCredit(cumulativeCredit);
                failedCourses.remove(course);
            }

            double activeCredit = transcript.getActiveCredit();
            activeCredit += course.getCredit();
            transcript.setActiveCredit(activeCredit);

            double cumulativeCredit = transcript.getCumulativeCredit();
            cumulativeCredit += course.getCredit();
            transcript.setCumulativeCredit(cumulativeCredit);
            course.addStudentToArraylist(this);
        }
    }

    public List<Course> getCourseBasket() {
        return courseBasket;
    }


    public void addCourseToBasket(Course course) {
        this.courseBasket.add(course);
    }

    public List<Course> getPastCourses() {
        return pastCourses;
    }


    public void addPastCourse(Course course) {
        this.pastCourses.add(course);
    }

    public List<Course> getNonTakenCourses() {
        return nonTakenCourses;
    }

    public void addNonTakenCourse(Course course) {
        this.nonTakenCourses.add(course);
    }

    public List<Course> getFailedCourses() {
        return failedCourses;
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
        transcript.addError(error);
        Logger logger = Logger.getLogger(this.getClass().getName());
        logger.info(error.raiseError());
    }

    public void calculateGPA() {
        double activeCredit = transcript.getActiveCredit();
        double activeGrade = transcript.getActiveGrade();
        double result = activeGrade / activeCredit;
        setGpa((float) result);
    }

    public void calculateCGPA() {
        double cumulativeCredit = transcript.getCumulativeCredit();
        double cumulativeGrade = transcript.getCumulativeGrade();
        double result = cumulativeGrade / cumulativeCredit;
        setCgpa((float) result);
    }

    public Transcript getTranscript() {
        return transcript;
    }

    public void setTranscript(Transcript transcript) {
        this.transcript = transcript;
    }


    @Override
    public String toString() {
        return "Student{" + "semester=" + semester + "} " + super.toString() + " advisor=" + advisor;
    }
}
