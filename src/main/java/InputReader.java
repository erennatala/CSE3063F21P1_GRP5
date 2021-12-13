import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputReader {

    JSONParser parser = new JSONParser(); //her json okumada kulanılacak

    private int instructorId = 1000;
    private int check = 0;
    private Instructor inst;
    private int instCheck = 1;
    private String day;
    private String start_time;
    private String end_time;

    public void readCourseJson(CourseExpert courseExpert, InstructorExpert instructorExpert) { //oku, instructor objesine kurs ata

        try {
            JSONArray curr_input = (JSONArray) parser.parse(new FileReader("curriculum.json"));

            for (Object o : curr_input) {
                JSONObject courses = (JSONObject) o;

                List<String> l = new ArrayList<String>(courses.keySet());

                for (int i = 0; i<l.size(); i++) {
                    JSONArray data_title = (JSONArray) courses.get(l.get(i));//title altındaki dersler arrayi

                    for (int j = 0; j < data_title.size(); j++) {

                        JSONObject obj = (JSONObject) data_title.get(j);

                        Course course = null;
                        String courseId = obj.get("courseId").toString();
                        String courseName = obj.get("courseName").toString();
                        int capacity = Integer.parseInt(obj.get("Capacity").toString());
                        float credit = Integer.parseInt(obj.get("Credit").toString());
                        float ects = Integer.parseInt(obj.get("Credit").toString());
                        String type = obj.get("Type").toString();
                        String instructor = obj.get("Instructor").toString();

                        if (!courseId.equals("NTExxx") && !courseId.equals("TExxx") && !courseId.equals("FTExxx") && !courseId.equals("UE") && instructor.equals("") && !type.equals("Must")) {
                            continue;
                        }

                        Instructor instructorObject = instructorExpert.findInstructor(instructor);

                        if (l.get(i).substring(0,8).equals("Semester")) {
                            int semesterId = Integer.parseInt(l.get(i).substring(l.get(i).length()-1));
                            course = courseExpert.createCourse(courseId, courseName, capacity, credit, ects, type, semesterId, instructorObject);
                        }
                        else if (l.get(i).substring(0,9).equals("(ENG-FTE)")) {
                            course = courseExpert.createCourse(courseId, courseName, capacity, credit, ects, type, instructorObject);
                        }
                        else if (l.get(i).substring(0,4).equals("(TE)")) {
                            course = courseExpert.createCourse(courseId, courseName, capacity, credit, ects, type, instructorObject);
                        }
                        else if (l.get(i).substring(0,14).equals("(NTE / ENG-UE)")) {
                            course = courseExpert.createCourse(courseId, courseName, capacity, credit, ects, type, instructorObject);
                        }
                        if (instructorObject != null)
                            instructorObject.addGivenCourse(course);
                    }
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

    public void readStudentJson(int startIndex, StudentExpert studentExpert,Semester semester) {

        try {
            JSONArray student_input = (JSONArray) parser.parse(new FileReader("students.json"));

            int number = 999+startIndex;

            for (Object o : student_input) {

                JSONObject students = (JSONObject) o;

                if (Integer.parseInt(students.get("index").toString()) < startIndex) {
                    continue;
                }
                if (Integer.parseInt(students.get("index").toString()) == startIndex+71) {
                    break;
                }

                number++;


                String name = (String) students.get("name");
                String surname = (String) students.get("surname");
                JSONArray email = (JSONArray) students.get("email");

                studentExpert.createStudent(number, name, surname, email,semester);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void readInstructorJson(InstructorExpert instructorExpert) {

        try {
            JSONArray instructor_input = (JSONArray) parser.parse(new FileReader("instructors.json"));

            for (Object o : instructor_input) {

                JSONObject instructors = (JSONObject) o;

                long id = (long) instructors.get("instructorID");
                String name = (String) instructors.get("name");
                String surname = (String) instructors.get("surname");

                instructorExpert.createInstructor((int)id, name, surname);
            }
            }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}

