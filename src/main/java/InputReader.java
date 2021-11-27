import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class InputReader {

    JSONParser parser = new JSONParser(); //her json okumada kulanılacak

    public ArrayList<Course> readCourseJson() {

        ArrayList<Course> course_list = new ArrayList<Course>();

        try {
            JSONArray curr_input = (JSONArray) parser.parse(new FileReader("curriculum.json"));

            for (Object o : curr_input) {
                JSONObject courses = (JSONObject) o;

                List<String> l = new ArrayList<String>(courses.keySet());

                for (int i = 0; i<l.size(); i++) {
                    JSONArray data_title = (JSONArray) courses.get(l.get(i));
                    for (int j = 0; j < data_title.size(); j++) {

                        JSONObject obj = (JSONObject) data_title.get(j);

                        if ((obj.get("courseId").toString().substring(0,3).equals("TEx")) || (obj.get("courseId").toString().substring(0,3).equals("FTE") ) || (obj.get("courseId").toString().substring(0,3).equals("UEx")) || (obj.get("courseId").toString().substring(0,3).equals("NTE"))){

                        }
                        else {

                            Course course = new MandatoryCourse(obj.get("courseId").toString(), obj.get("courseName").toString(), 20, "SINIF", Integer.parseInt(obj.get("CR").toString()), 3, Integer.parseInt(obj.get("ECTS").toString()), 5); //KAPASİTE SINIF REQUIRED DEĞİŞ
                            course_list.add(course);
                        }
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
        return course_list;
    }

    public void readStudentJson(int startIndex, StudentExpert studentExpert) {

        ArrayList<Student> student_list = new ArrayList<Student>();

        try {
            JSONArray student_input = (JSONArray) parser.parse(new FileReader("students.json"));

            int number = 999+startIndex;

            for (Object o : student_input) {

                JSONObject students = (JSONObject) o;

//                if(number==1270){break;}

                if (Integer.parseInt(students.get("index").toString()) < startIndex) {
                    continue;
                }
                if (Integer.parseInt(students.get("index").toString()) == startIndex+71) {
                    break;
                }

                number++;

                //FileWriter student_append = new FileWriter("src/main/resources/" + number + ".json");

                String name = (String) students.get("name");
                String surname = (String) students.get("surname");
                JSONArray email = (JSONArray) students.get("email");

//                JSONArray array = new JSONArray();
//
//                JSONObject put_students = new JSONObject();
//
//                put_students.put("name", name);
//                put_students.put("surname", surname);
//                put_students.put("email",email);
//
//                array.add(put_students);
//
//                JSONObject parameters = new JSONObject(); //BURAYA BAK
//
//                parameters.put("Student Information", array);
//
//                student_append.write(parameters.toJSONString());
//
//                student_append.close();
                studentExpert.createStudent(number, name, surname, email);
                //Student student = new Student(number, name, surname, email);

//                student.setStudentName(name);
//                student.setStudentSurname(surname);

                //student_list.add(student);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //return student_list;
    }
}

