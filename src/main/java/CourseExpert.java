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

    public Course createCourse(String courseId, String name, int capacity, float credit, float ects, String type, int semesterId, Instructor instructor) {
        Course mandatoryCourse = new MandatoryCourse(courseId, name, capacity, credit, ects);
        mandatoryCourse.setInstructor(instructor);
        curriculum.addCourseToArrayList(mandatoryCourse, "Must", semesterId);
        return mandatoryCourse;
}
//    public Course createCourse(String courseId, String name, int capacity, float credit, float ects, String type, int semesterId) {
//        Course mandatoryCourse = new MandatoryCourse(courseId, name, capacity, credit, ects);
//        curriculum.addCourseToArrayList(mandatoryCourse, "Must", semesterId);
//        return mandatoryCourse;
//    }

    public Course createCourse(String courseId, String name, int capacity, float credit, float ects, String type, Instructor instructor) {
        if (type.equals("TE")) {
            Course teCourse = new TechnicalElective(courseId, name, capacity, credit, ects, 155);
            teCourse.setInstructor(instructor);
            curriculum.addCourseToArrayList(teCourse, "TE");
            return teCourse;
        } else if (type.equals("FTE")) {
            Course fteCourse = new FacultyTechnicalElective(courseId, name, capacity, credit, ects);
            fteCourse.setInstructor(instructor);
            curriculum.addCourseToArrayList(fteCourse, "FTE");
            return fteCourse;
        } else if (type.equals("NTE-UE")) {
            Course nt_uCourse = new NT_UElective(courseId, name, capacity, credit, ects);
            nt_uCourse.setInstructor(instructor);
            curriculum.addCourseToArrayList(nt_uCourse, "NTE-UE");
            return nt_uCourse;
        }
        return null;
    }

//    public Course createCourse(String courseId, String name, int capacity, float credit, float ects, String type) {
//        if (type.equals("TE")) {
//            Course teCourse = new TechnicalElective(courseId, name, capacity, credit, ects, 155);
//            curriculum.addCourseToArrayList(teCourse, "TE");
//            return teCourse;
//        } else if (type.equals("FTE")) {
//            Course fteCourse = new FacultyTechnicalElective(courseId, name, capacity, credit, ects);
//            curriculum.addCourseToArrayList(fteCourse, "FTE");
//            return fteCourse;
//        } else if (type.equals("NTE-UE")) {
//            Course nt_uCourse = new NT_UElective(courseId, name, capacity, credit, ects);
//            curriculum.addCourseToArrayList(nt_uCourse, "NTE-UE");
//            return nt_uCourse;
//        }
//        return null;
//    }

}
