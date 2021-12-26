import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class TranscriptReader {

    private JSONParser parser = new JSONParser();

    @SuppressWarnings("unchecked")
    public void readTranscriptJson(StudentExpert studentExpert, CourseExpert courseExpert, InstructorExpert instructorExpert) {

        try {
            File folder = new File("transcripts");
            File[] listOfFiles = folder.listFiles();

            for (int i = 0; i < Objects.requireNonNull(listOfFiles).length; i++) {
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

                    studentExpert.createStudent(id, name, surname, emails, courseExpert.getSemesterMap().get(semester));

                    Student student = studentExpert.getStudentMap().get(id);
                    Instructor instructor = instructorExpert.findInstructor(advisor);
                    student.setAdvisor(instructor);
                    instructor.addAdvisees(student);
                    student.setCgpa(cgpa);

                    for (Object o : curr_input.keySet()) { //Semester geziyor
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
                            }
                        } catch (StringIndexOutOfBoundsException s) {
                        }
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

}
