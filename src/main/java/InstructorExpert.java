import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class InstructorExpert {

    private Map<String,Instructor> instructorMap;


    public InstructorExpert() {
        this.instructorMap = new HashMap<>();
    }

    public Map<String, Instructor> getInstructorMap() {
        return instructorMap;
    }

    public void setInstructorMap(Map<String, Instructor> instructorMap) {
        this.instructorMap = instructorMap;
    }

    public void createInstructor(int id, String name, String surname) {
        Instructor instructor = new Instructor(id, name, surname);
        String fullName = instructor.getFullName();
        Map<String,Instructor> instructorMap = this.instructorMap;
        instructorMap.put(fullName, instructor);
    }
    public void showInstructors(){
        Map<String,Instructor> instructorMap = this.instructorMap;
        Iterator<Map.Entry<String,Instructor>> instructorIterator = instructorMap.entrySet().iterator();
        while (instructorIterator.hasNext()) {
            Map.Entry<String, Instructor> tmpMap = (Map.Entry<String, Instructor>) instructorIterator.next();
            System.out.println(tmpMap.getKey()+" = "+tmpMap.getValue());
        }
    }
    public Instructor findInstructor(String fullName){
        Map<String,Instructor> instructorMap = this.instructorMap;
        Instructor instructor = instructorMap.get(fullName);
        return instructor;
    }



}
