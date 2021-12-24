import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;


public class Transcript {
    private Map<String,Object> transcriptMap= new HashMap<>();
    private Student student;

    public Transcript(Student student) {
        this.student = student;
        this.transcriptMap = new HashMap<>();
        this.transcriptMap.put("ID", student.getId());
        this.transcriptMap.put("Name",student.getName());
        this.transcriptMap.put("Surname",student.getSurname());
        this.transcriptMap.put("Email",student.getEmails());
        this.transcriptMap.put("Semester",student.getSemester());
        this.transcriptMap.put("CGPA",student.getCgpa());
        this.transcriptMap.put("Advisor",student.getAdvisor());
        Semester semester = student.getSemester();
        addSemester(semester);

    }
    public Map<String, Object> createSemester(Semester semester) {
        Map<String, Object> semesterMap = new HashMap<>();
        semesterMap.put("Courses", new HashMap<String, Object>());
        semesterMap.put("Errors", new ArrayList<>());
        return semesterMap;
    }

    public void addSemester(Semester semester) {
        Map<String, Object> semesterMap = createSemester(semester);
        String semesterString = "Semester".concat(semester.getSemesterId().toString());
        transcriptMap.put(semesterString,semesterMap);
        transcriptMap.put("GPA",student.getGpa());
    }
    @SuppressWarnings("unchecked")
    public void addCourse(Course course){
        String semesterName = "Semester".concat(getStudent().getSemester().getSemesterId().toString());
        Map<String,Object> semesterMap = (HashMap<String,Object>)transcriptMap.get(semesterName);
        Map<String,Object> courseMap = (HashMap<String,Object>)semesterMap.get("Courses");
        //String letterGrade = student.getGradeMap().get(course).getLetterGrade();
        //courseMap.put(course.getCourseId(),letterGrade);

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
        Map<String,Object> semesterMap = (HashMap<String,Object>)transcriptMap.get(semesterName);
        List<String> errorList = (ArrayList<String>)semesterMap.get("Errors");
        errorList.add(error.raiseError());

    }

}
