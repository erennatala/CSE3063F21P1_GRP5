import java.util.ArrayList;

public class CourseExpert {

    private ArrayList<Course> courses;

    private Curriculum curriculum;

    private ArrayList<Instructor> instructors;

    public CourseExpert(Curriculum curriculum) {
        this.courses = new ArrayList<Course>();
        this.curriculum = curriculum;
    }

    public CourseExpert(Curriculum curriculum, ArrayList<Instructor> instructors) {
        this.courses = new ArrayList<Course>();
        this.curriculum = curriculum;
        this.instructors = instructors;
    }

    public void createCourse(String courseId, String name, int capacity, float credit, float ects, String type, int semesterId) {
        if (type.equals("Must")) {
            Course mandatoryCourse = new MandatoryCourse(courseId, name, capacity, credit, ects);
            curriculum.addCourseToArrayList(mandatoryCourse, "Must", semesterId);
        }
}
    public void createCourse(String courseId, String name, int capacity, float credit, float ects, String type) {
        if (type.equals("TE")) {
            Course teCourse = new TechnicalElective(courseId, name, capacity, credit, ects, 155);
            curriculum.addCourseToArrayList(teCourse, "TE");
        } else if (type.equals("FTE")) {
            Course fteCourse = new FacultyTechnicalElective(courseId, name, capacity, credit, ects);
            curriculum.addCourseToArrayList(fteCourse, "FTE");
        } else if (type.equals("NTE-UE")) {
            Course nt_uCourse = new NT_UElective(courseId, name, capacity, credit, ects);
            curriculum.addCourseToArrayList(nt_uCourse, "NTE-UE");
        }
    }
}
