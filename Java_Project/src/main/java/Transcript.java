import java.util.*;

public class Transcript {
    private Map<String, Object> transcriptMap;
    private Student student;
    private double activeCredit = 0;
    private double activeGrade = 0;

    private double cumulativeCredit = 0;
    private double cumulativeGrade = 0;

    public Transcript(Student student) {
        this.student = student;
        this.transcriptMap = new HashMap<>();
        this.transcriptMap.put("ID", student.getId());
        this.transcriptMap.put("Name", student.getName());
        this.transcriptMap.put("Surname", student.getSurname());
        this.transcriptMap.put("Email", student.getEmails());
        this.transcriptMap.put("Advisor", student.getAdvisor().getFullName());
        Semester semester = student.getSemester();
        addSemester(semester);

    }

    public Map<String, Object> createSemester() {//function creates a semester and puts courses as HashMap and errors as ArrayList
        Map<String, Object> semesterMap = new HashMap<>();
        semesterMap.put("Courses", new HashMap<String, Object>());
        semesterMap.put("Errors", new ArrayList<>());
        return semesterMap;
    }

    public double getActiveCredit() {
        return activeCredit;
    }

    public void setActiveCredit(double activeCredit) {
        this.activeCredit = activeCredit;
    }

    public double getActiveGrade() {
        return activeGrade;
    }

    public void setActiveGrade(double activeGrade) {
        this.activeGrade = activeGrade;
    }

    public double getCumulativeCredit() {
        return cumulativeCredit;
    }

    public void setCumulativeCredit(double cumulativeCredit) {
        this.cumulativeCredit = cumulativeCredit;
    }

    public double getCumulativeGrade() {
        return cumulativeGrade;
    }

    public void setCumulativeGrade(double cumulativeGrade) {
        this.cumulativeGrade = cumulativeGrade;
    }

    public void addSemester(Semester semester) {
        Map<String, Object> semesterMap = createSemester();
        String semesterString = "Semester".concat(semester.getSemesterId().toString());
        transcriptMap.put(semesterString, semesterMap);
    }

    @SuppressWarnings("unchecked")
    public void addCourse(Course course) {// the function adds course via semester name and transcriptMap
        String semesterName = "Semester".concat(getStudent().getSemester().getSemesterId().toString());
        Map<String, Object> semesterMap = (HashMap<String, Object>) transcriptMap.get(semesterName);
        Map<String, Object> courseMap = (HashMap<String, Object>) semesterMap.get("Courses");
        String letterGrade = student.getGradeMap().get(course).getLetterGrade();
        courseMap.put(course.getCourseId(), letterGrade);

    }

    public Map<String, Object> getTranscriptMap() {
        return transcriptMap;
    }

    public void setTranscriptMap(Map<String, Object> transcriptMap) {
        this.transcriptMap = transcriptMap;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @SuppressWarnings("unchecked")
    public void addError(Error error) {
        String semesterName = "Semester".concat(getStudent().getSemester().getSemesterId().toString());
        Map<String, Object> semesterMap = (HashMap<String, Object>) transcriptMap.get(semesterName);
        List<String> errorList = (ArrayList<String>) semesterMap.get("Errors");
        errorList.add(error.raiseError());

    }

    @SuppressWarnings("unchecked")
    public void addGPA() {
        String semesterName = "Semester".concat(getStudent().getSemester().getSemesterId().toString());
        Map<String, Object> semesterMap = (HashMap<String, Object>) transcriptMap.get(semesterName);
        double GPA = student.getGpa();
        double roundOff = (double) Math.round(GPA * 100) / 100;
        semesterMap.put("ActiveCredit", activeCredit);
        semesterMap.put("GPA", roundOff);
    }

}
