import org.apache.log4j.Logger;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class InstructorExpert {// A class for holds the functions for instructor

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

    public void createInstructor(int id, String name, String surname) {//the function written below creates the instructor
        Instructor instructor = new Instructor(id, name, surname);
        String fullName = instructor.getFullName();
        Map<String,Instructor> instructorMap = this.instructorMap;
        instructorMap.put(fullName, instructor);
        Logger logger = Logger.getLogger(this.getClass().getName());
        logger.info("Instructor Created: " + instructor);
    }

    public Instructor findInstructor(String fullName){//the function finds the instructors by their name
        Map<String,Instructor> instructorMap = this.instructorMap;
        Instructor instructor = instructorMap.get(fullName);
        return instructor;
    }

}
