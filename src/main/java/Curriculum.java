import java.util.ArrayList;
import java.util.List;

public class Curriculum {

    private ArrayList<MandatoryCourse> mandatoryCourses = new ArrayList<MandatoryCourse>();

    private ArrayList<FacultyTechnicalElective> facultyTechnicalElectives = new ArrayList<FacultyTechnicalElective>();

    private List<NT_UElective> NTUElectives = new ArrayList<>();

    private List<TechnicalElective> technicalElectives = new ArrayList<>();

    private ArrayList<Semester> semesterList = new ArrayList<Semester>();

    private ArrayList<Course> courseList;

    public Curriculum(){}

    public Curriculum(ArrayList<Course> courseList) {
        this.courseList = courseList;
    }

    public ArrayList<MandatoryCourse> getMandatoryCourses() {
        return mandatoryCourses;
    } //SİL

    public List<FacultyTechnicalElective> getFacultyTechnicalElectives() {
        return facultyTechnicalElectives;
    }

    public List getNTUElectives() {
        return NTUElectives;
    }

    public List getTechnicalElectives() {
        return technicalElectives;
    }

    public ArrayList<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(ArrayList<Course> courseList) {
        this.courseList = courseList;
    }

    public ArrayList<Semester> getSemesterList() {
        return semesterList;
    }

    public void setSemesterList(ArrayList<Semester> semesterList) {
        this.semesterList = semesterList;
    }

    public void addSemester(Semester semester) {
        this.semesterList.add(semester);
    }

    public void addCourseToArrayList(Course course, String type, Integer semesterId) {
        Semester semester = semesterList.stream()
                .filter(semester1->semesterId.equals(semester1.getSemesterId()))
                .findAny()
                .orElse(null);

//        for (Semester semester: semesterList) {
//            if (semester.getSemesterId() == semesterId) {
//                mandatoryCourses.add((MandatoryCourse) course);
//                semester.addCourse(course);
//                course.setSemester(semester);
//                break;
//            }
//        }
    }

    public void addCourseToArrayList(Course course, String type) {
        if (type.equals("TE")) {
            technicalElectives.add((TechnicalElective) course);
        } else if (type.equals("FTE")) {
            facultyTechnicalElectives.add((FacultyTechnicalElective) course);
        } else if (type.equals("NTE-UE")) {
            NTUElectives.add((NT_UElective) course);
        }
    }
    public List<ElectiveCourse> getElectiveList(Course course){
        return this.getTechnicalElectives();
    }
    public List<ElectiveCourse> getElectiveList(NT_UElective course){
        return this.getNTUElectives();
    }
//    public ArrayList<NT_UElective> getElectiveList(NT_UElective course){
//        return this.getNTUElectives();
//    }
//    public ArrayList<FacultyTechnicalElective> getElectiveList(FacultyTechnicalElective course){
//        return this.getFacultyTechnicalElectives();
//    }
//    public ArrayList<ElectiveCourse> getElectiveList(ElectiveCourse course){
//        if (course instanceof TechnicalElective)
//            return this.getTechnicalElectives();
//
//    }


}
