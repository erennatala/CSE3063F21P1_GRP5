import java.util.*;

public class Transcript {//A class for Transcript
    private Map<String, Object> transcriptMap;//transcriptMap as Map
    private Student student;//student attribute

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

    public void addSemester(Semester semester) {//the method written below adds semester and puts semester to semesterMap. Also, it puts student's gpa to transcriptMap
        Map<String, Object> semesterMap = createSemester();
        String semesterString = "Semester".concat(semester.getSemesterId().toString());
        transcriptMap.put(semesterString, semesterMap);
        transcriptMap.put("GPA", student.getGpa());
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

}
