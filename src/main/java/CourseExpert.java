import java.util.*;

public class CourseExpert {
    //semester create fonksiyonu ?
    //private ArrayList<Course> courses;
    private List<Course> courses = new ArrayList<>();
    private List<Course> mandatoryCourses = new ArrayList<>();
    private List<ElectiveCourse> facultyTechnicalList = new ArrayList<>();
    private List<ElectiveCourse> NT_UList = new ArrayList<>();
    private List<ElectiveCourse> technicalList = new ArrayList<>();
    private Map<Integer,Semester> semesterMap= new HashMap<Integer,Semester>();
    //private ArrayList<Instructor> instructors; // buna bak

    public CourseExpert() {
        createSemester();
    }
    public void createSemester() {
        int semesterId;
        for (int i = 1; i < 9; i++) {
            semesterId = i;
            this.semesterMap.put(semesterId,new Semester(semesterId));
        }
    }
//    public void showSemester(){
//        Map<Integer,Semester> semesterMap = getSemesterMap();
//        Iterator<Map.Entry<Integer,Semester>> new_Iterator = semesterMap.entrySet().iterator();
//
//        while(new_Iterator.hasNext()){
//            Map.Entry<Integer,Semester> new_map = (Map.Entry<Integer, Semester>) new_Iterator.next();
//            System.out.println(new_map.getKey()+" = "+new_map.getValue());
//        }
//    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Course> getMandatoryCourses() {
        return mandatoryCourses;
    }

    public void setMandatoryCourses(List<Course> mandatoryCourses) {
        this.mandatoryCourses = mandatoryCourses;
    }

    public List<ElectiveCourse> getFacultyTechnicalList() {
        return facultyTechnicalList;
    }

    public void setFacultyTechnicalList(List<ElectiveCourse> facultyTechnicalList) {
        this.facultyTechnicalList = facultyTechnicalList;
    }

    public List<ElectiveCourse> getNT_UList() {
        return NT_UList;
    }

    public void setNT_UList(List<ElectiveCourse> NT_UList) {
        this.NT_UList = NT_UList;
    }

    public List<ElectiveCourse> getTechnicalList() {
        return technicalList;
    }

    public void setTechnicalList(List<ElectiveCourse> technicalList) {
        this.technicalList = technicalList;
    }

    public Map<Integer, Semester> getSemesterMap() {
        return semesterMap;
    }

    public void setSemesterMap(Map<Integer, Semester> semesterMap) {
        this.semesterMap = semesterMap;
    }
    public void addMandatoryCourse(Course course){
        List<Course> mandatoryCourses = getMandatoryCourses();
        mandatoryCourses.add(course);
    }
    public void addNTUElective(ElectiveCourse electiveCourse){
        List<ElectiveCourse> NT_UList = getNT_UList();
        NT_UList.add(electiveCourse);
    }
    public void addTechnicalElective(ElectiveCourse electiveCourse){
        List<ElectiveCourse> technicalList = getTechnicalList();
        technicalList.add(electiveCourse);
    }

    public void addFacultyTechnicalElective(ElectiveCourse electiveCourse) {
        List<ElectiveCourse> facultyTechnicalList = getFacultyTechnicalList();
        facultyTechnicalList.add(electiveCourse);
    }
    public ElectiveCourse getElectiveFactory(String type,String courseId, String name, int capacity, float credit, float ects,Instructor instructor){
        if (type == null){
            return null;
        }
        if(type.equalsIgnoreCase("NTE-UE")){
            ElectiveCourse nt_uElective = new NT_UElective(courseId, name, capacity, credit, ects, instructor);
            addNTUElective(nt_uElective);
            return nt_uElective;
        }
        else if (type.equalsIgnoreCase("TE")){
            ElectiveCourse technicalElective = new TechnicalElective(courseId, name, capacity, credit, ects, instructor);
            addTechnicalElective(technicalElective);
            return technicalElective;
        }
        else if (type.equalsIgnoreCase("FTE")){
            ElectiveCourse facultyTechnicalElective = new FacultyTechnicalElective(courseId, name, capacity, credit, ects, instructor);
            addFacultyTechnicalElective(facultyTechnicalElective);
            return facultyTechnicalElective;
        }
        return null;
    }
    public Course createCourse(String courseId, String name, int capacity, float credit, float ects, String type, int semesterId, Instructor instructor){
        Course mandatoryCourse;
        Map<Integer,Semester> semesterMap = getSemesterMap();
        Semester semester = semesterMap.get(semesterId);
        if(type.equals("Must")){
            mandatoryCourse = new MandatoryCourse(courseId,name,capacity,credit,ects,semester,instructor);
            addMandatoryCourse(mandatoryCourse);
            semester.addCourse(mandatoryCourse);
            return mandatoryCourse;
        }
        else{
            ElectiveCourse electiveCourse = getElectiveFactory(type, courseId, name, capacity, credit, ects,instructor);
            System.out.println(electiveCourse);
            addMandatoryCourse(electiveCourse);
            semester.addCourse(electiveCourse);
            return electiveCourse;
        }

    }

    public Course createCourse(String courseId, String name, int capacity, float credit, float ects, String type, Instructor instructor){
        return getElectiveFactory(type,courseId,name,capacity, credit,ects,instructor);
    }


}
