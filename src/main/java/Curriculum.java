import java.util.ArrayList;

public class Curriculum {

    private ArrayList<MandatoryCourse> mandatoryCourses = new ArrayList<MandatoryCourse>();

    private ArrayList<FacultyTechnicalElective> facultyTechnicalElectives = new ArrayList<FacultyTechnicalElective>();

    private ArrayList<NT_UElective> NTUElectives = new ArrayList<NT_UElective>();

    private ArrayList<TechnicalElective> technicalElectives = new ArrayList<TechnicalElective>();

    private ArrayList<Semester> semesterList = new ArrayList<Semester>();

    private ArrayList<Course> courseList;

    public Curriculum(){}

    public Curriculum(ArrayList<Course> courseList) {this.courseList = courseList;}

    public ArrayList<MandatoryCourse> getMandatoryCourses() {return mandatoryCourses;} //SİL

    public ArrayList<FacultyTechnicalElective> getFacultyTechnicalElectives() {return facultyTechnicalElectives;}

    public ArrayList<NT_UElective> getNTUElectives() {return NTUElectives;}

    public ArrayList<TechnicalElective> getTechnicalElectives() {return technicalElectives;}

    public ArrayList<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(ArrayList<Course> courseList) {
        this.courseList = courseList;
    }

    public ArrayList<Semester> getSemesterList() {return semesterList;}

    public void setSemesterList(ArrayList<Semester> semesterList) {this.semesterList = semesterList;}

    public void addSemester(Semester semester) {
        this.semesterList.add(semester);
    }

    public void addCourseToArrayList(Course course, String type, int semesterId) {

        for (Semester semester: semesterList) {
            if (semester.getSemesterId() == semesterId) {
                mandatoryCourses.add((MandatoryCourse) course);
                course.setSemester(semester);
                break;
            }
        }
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


}
