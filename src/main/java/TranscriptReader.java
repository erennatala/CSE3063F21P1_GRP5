import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class TranscriptReader {

    private JSONParser parser = new JSONParser();

    @SuppressWarnings("unchecked")
    public void readTranscriptJson(StudentExpert studentExpert, CourseExpert courseExpert, InstructorExpert instructorExpert) {// the function reads the transcripts from json files
        Logger logger = Logger.getLogger(this.getClass().getName());
        try {//the function takes the transcript files that have already created and puts into the process and if transcript exists, function reads it and creates the student. After it, function  adds the student's information which contained in transcript
            File folder = new File("transcripts");//pathname
            File[] listOfFiles = folder.listFiles();

            for (int i = 0; i < Objects.requireNonNull(listOfFiles).length; i++) {//for loop ranges length of listOfFiles
                File file = listOfFiles[i];

                try {
                    JSONObject curr_input = (JSONObject) parser.parse(new FileReader(file.toString()));

                    String name = curr_input.get("Name").toString();
                    String surname = curr_input.get("Surname").toString();
                    int id = Integer.parseInt(curr_input.get("ID").toString());
                    float cgpa = Float.parseFloat(curr_input.get("CGPA").toString());
                    int semester = Integer.parseInt(curr_input.get("Semester").toString());
                    String advisor = curr_input.get("Advisor").toString();
                    List<String> emails = (List<String>) curr_input.get("Email");
                    int completedCredit = Integer.parseInt(curr_input.get("Completed Credit").toString());

                    studentExpert.createStudent(id, name, surname, emails, courseExpert.getSemesterMap().get(semester));

                    Student student = studentExpert.getStudentMap().get(id);
                    Instructor instructor = instructorExpert.findInstructor(advisor);
                    student.setAdvisor(instructor);
                    instructor.addAdvisees(student);
                    student.setCgpa(cgpa);
                    student.setCompletedCredit(completedCredit);

                    for (Object o : curr_input.keySet()) {
                        try {
                            if (o.toString().substring(0, 8).equals("Semester") && !(o.toString().equals("Semester"))) {
                                JSONObject currSemester = (JSONObject) curr_input.get(o);
                                JSONObject courses = (JSONObject) currSemester.get("Courses");

                                for (Object c : courses.keySet()) {
                                    Course course = courseExpert.findCourse(c.toString());
                                    Grade grade = new Grade(student, course, courses.get(c).toString());

                                    student.getGradeMap().put(course, grade);

                                    if (grade.getLetterGrade().equals("FF")) {
                                        student.getFailedCourses().add(course);
                                    } else {
                                        student.getPastCourses().add(course);
                                    }
                                }
                                student.getTranscript().getTranscriptMap().put(o.toString(), (Map<String, Object>) currSemester);
                            }
                        } catch (StringIndexOutOfBoundsException s) {
                        }
                    }

                } catch (FileNotFoundException e) {
                    logger.error(e.getMessage());
                } catch (IOException e) {
                    logger.error(e.getMessage());
                } catch (ParseException e) {
                    logger.error(e.getMessage());
                } catch (NullPointerException e) {
                    logger.error(e.getMessage());
                }
            }
        } catch (NullPointerException e) {
            logger.error(e.getMessage());
        }
    }

}
