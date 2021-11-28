import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.awt.desktop.SystemEventListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class InputReader {

    JSONParser parser = new JSONParser(); //her json okumada kulanılacak

    private int instructorId = 1000;
    private int check = 0;
    private Instructor inst;
    private int instCheck = 1;

    public void readCourseJson(CourseExpert courseExpert, InstructorExpert instructorExpert) { //oku, instructor objesine kurs ata

        try {
            JSONArray curr_input = (JSONArray) parser.parse(new FileReader("curriculum.json"));

            for (Object o : curr_input) {
                JSONObject courses = (JSONObject) o;

                List<String> l = new ArrayList<String>(courses.keySet());

                for (int i = 0; i<l.size(); i++) {
                    JSONArray data_title = (JSONArray) courses.get(l.get(i)); //title altındaki dersler arrayi

                    for (int j = 0; j < data_title.size(); j++) {

                        JSONObject obj = (JSONObject) data_title.get(j);

//                        JSONArray schedule = (JSONArray) obj.get("Schedule");

                        String creditEdited;
                        String ectsEdited;
                        String instructor = obj.get("Instructor").toString();
                        String[] instructorName = instructor.split(" ");

                        if (instructor.equals("")) {
                            instCheck = 0;
                        }
                        else {
                            if (instructorExpert.getInstructors().size() == 0) {
                                inst = instructorExpert.createInstructor(instructorId, instructorName[0], instructorName[1]);
                                instCheck = 1;
                            }
                            else {
                                for (int k = 0; k<instructorExpert.getInstructors().size(); k++) {

                                    if (instructorName[0].equals(instructorExpert.getInstructors().get(k).getName()) == true && instructorName[1].equals(instructorExpert.getInstructors().get(k).getSurname()) == true) {
                                        check = 1;
                                        break;
                                    }
                                    else {
                                        check = 0;
                                    }
                                }
                                if (check == 0) {
                                    inst = instructorExpert.createInstructor(instructorId, instructorName[0], instructorName[1]);
                                    instCheck = 1;
                                    instructorId++;
                                }
                            }
                        }

                        if (obj.get("Credit").toString().length() == 4) {
                            creditEdited = obj.get("Credit").toString().substring(0,1) + "." + obj.get("Credit").toString().substring(2,3);
                        }
                        else {
                            creditEdited = obj.get("Credit").toString().substring(0,2) + "." + obj.get("Credit").toString().substring(3,4);
                        }

                        if (obj.get("ECTS").toString().length() == 4) {
                            ectsEdited = obj.get("ECTS").toString().substring(0,1) + "." + obj.get("ECTS").toString().substring(2,3);
                        }
                        else {
                            ectsEdited = obj.get("ECTS").toString().substring(0,2) + "." + obj.get("ECTS").toString().substring(3,4);
                        }

                        if (l.get(i).substring(0,8).equals("Semester")) {
                            int semesterId = Integer.parseInt(l.get(i).substring(l.get(i).length()-1));
                            if (instCheck == 1) {
                                inst.addGivenCourse(courseExpert.createCourse(obj.get("courseId").toString(), obj.get("courseName").toString(), Integer.parseInt(obj.get("Capacity").toString()), Float.parseFloat(creditEdited), Float.parseFloat(ectsEdited), "Must", semesterId, inst));
                            }
//                            else {
//                                courseExpert.createCourse(obj.get("courseId").toString(), obj.get("courseName").toString(), Integer.parseInt(obj.get("Capacity").toString()), Float.parseFloat(creditEdited), Float.parseFloat(ectsEdited), "Must", semesterId);
//                            }
                        }
                        else if (l.get(i).substring(0,9).equals("(ENG-FTE)")) {
                            if (instCheck == 1) {
                                inst.addGivenCourse(courseExpert.createCourse(obj.get("courseId").toString(), obj.get("courseName").toString(), Integer.parseInt(obj.get("Capacity").toString()), Float.parseFloat(creditEdited), Float.parseFloat(ectsEdited), "FTE", inst));
                            }
//                            else {
//                                courseExpert.createCourse(obj.get("courseId").toString(), obj.get("courseName").toString(), Integer.parseInt(obj.get("Capacity").toString()), Float.parseFloat(creditEdited), Float.parseFloat(ectsEdited), "FTE");
//                            }
                        }
                        else if (l.get(i).substring(0,4).equals("(TE)")) {
                            if (instCheck == 1) {
                                inst.addGivenCourse(courseExpert.createCourse(obj.get("courseId").toString(), obj.get("courseName").toString(), Integer.parseInt(obj.get("Capacity").toString()), Float.parseFloat(creditEdited), Float.parseFloat(ectsEdited), "TE", inst));
                            }
//                            else {
//                                courseExpert.createCourse(obj.get("courseId").toString(), obj.get("courseName").toString(), Integer.parseInt(obj.get("Capacity").toString()), Float.parseFloat(creditEdited), Float.parseFloat(ectsEdited), "TE");
//                            }
                        }
                        else if (l.get(i).substring(0,14).equals("(NTE / ENG-UE)")) {
                            if (instCheck == 1) {
                                inst.addGivenCourse(courseExpert.createCourse(obj.get("courseId").toString(), obj.get("courseName").toString(), Integer.parseInt(obj.get("Capacity").toString()), Float.parseFloat(creditEdited), Float.parseFloat(ectsEdited), "NTE-UE", inst));
                            }
//                            else {
//                                courseExpert.createCourse(obj.get("courseId").toString(), obj.get("courseName").toString(), Integer.parseInt(obj.get("Capacity").toString()), Float.parseFloat(creditEdited), Float.parseFloat(ectsEdited), "NTE-UE");
//                            }
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
    }

    public void readStudentJson(int startIndex, StudentExpert studentExpert) {

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
    }

//    public void readInstructorJson(InstructorExpert instructorExpert) {
//
//        try {
//            JSONArray instructor_input = (JSONArray) parser.parse(new FileReader("instructors.json"));
//
//            for (Object o : instructor_input) {
//
//                JSONObject instructors = (JSONObject) o;
//
//                long id = (long) instructors.get("instructorID");
//                String name = (String) instructors.get("name");
//                String surname = (String) instructors.get("surname");
//                String email = (String) instructors.get("email");
//
//                ArrayList<String> emailToArray = new ArrayList<String>();
//
//                emailToArray.add(email);
//
//                instructorExpert.createInstructor((int)id, name, surname, emailToArray);
//            }
//            }
//        catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//    }
}

