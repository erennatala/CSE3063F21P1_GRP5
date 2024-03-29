import org.apache.log4j.Logger;

import java.util.*;

public class CourseExpert {
    private List<Course> courses = new ArrayList<>();
    private List<Course> mandatoryCourses = new ArrayList<>();
    private List<ElectiveCourse> facultyTechnicalList = new ArrayList<>();
    private List<ElectiveCourse> NT_UList = new ArrayList<>();
    private List<ElectiveCourse> technicalList = new ArrayList<>();
    private Map<Integer, Semester> semesterMap = new HashMap<>();
    private Map<Course, List<Integer>> errorMap = new HashMap<>();

    public CourseExpert() {
        createSemester();
    }

    public void createSemester() { //Semesters get created in below methods based on semester number
        int semesterId;
        for (int i = 1; i < 9; i++) {
            semesterId = i;
            String season;
            if (i % 2 == 1)
                season = "Fall";
            else season = "Spring";
            this.semesterMap.put(semesterId, new Semester(semesterId, season)); //they will be named as Fall or Spring again based on the semester number
        }
    }

    public void clearCourses() {
        for (Course course : courses) {
            course.clearStudents();
        }
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Course> getMandatoryCourses() {
        return mandatoryCourses;
    }

    public List<ElectiveCourse> getFacultyTechnicalList() {
        return facultyTechnicalList;
    }

    public List<ElectiveCourse> getNT_UList() {
        return NT_UList;
    }

    public List<ElectiveCourse> getTechnicalList() {
        return technicalList;
    }

    public Map<Integer, Semester> getSemesterMap() {
        return semesterMap;
    }

    public void addMandatoryCourse(Course course) {
        List<Course> mandatoryCourses = this.mandatoryCourses;
        mandatoryCourses.add(course);
    }

    public void addNTUElective(ElectiveCourse electiveCourse) {
        List<ElectiveCourse> NT_UList = this.NT_UList;
        NT_UList.add(electiveCourse);
    }

    public void addTechnicalElective(ElectiveCourse electiveCourse) {
        List<ElectiveCourse> technicalList = this.technicalList;
        technicalList.add(electiveCourse);
    }

    public void addFacultyTechnicalElective(ElectiveCourse electiveCourse) {
        List<ElectiveCourse> facultyTechnicalList = this.facultyTechnicalList;
        facultyTechnicalList.add(electiveCourse);
    }

    public ElectiveCourse getElectiveFactory(String type, String courseId, String name, int capacity, float credit, float ects, Instructor instructor) {// this is a factory design pattern for elective courses
        if (type == null) {
            return null;
        }
        if (type.equalsIgnoreCase("NTE-UE")) { //checks for if the course is a Non-Technical or a University Elective
            ElectiveCourse nt_uElective = new NT_UElective(courseId, name, capacity, credit, ects, instructor);
            if (!(courseId.equals("NTExxx") || courseId.equals("UE"))) { //if the course has a NTExxx or UE code it will not be added
                addNTUElective(nt_uElective);
                this.courses.add(nt_uElective);//
            }
            return nt_uElective;
        } else if (type.equalsIgnoreCase("TE")) {//checks for if the course is a Technical Elective
            ElectiveCourse technicalElective = new TechnicalElective(courseId, name, capacity, credit, ects, instructor);
            if (!courseId.equals("TExxx")) { //if the course has a TExxx code it will not be added
                addTechnicalElective(technicalElective);
                this.courses.add(technicalElective);//
            }
            return technicalElective;
        } else if (type.equalsIgnoreCase("FTE")) {//checks for if the course is a Faculty Technical Elective
            ElectiveCourse facultyTechnicalElective = new FacultyTechnicalElective(courseId, name, capacity, credit, ects, instructor);
            if (!courseId.equals("FTExxx")) { //if the course has a FTExxx code it will not be added
                addFacultyTechnicalElective(facultyTechnicalElective);
                this.courses.add(facultyTechnicalElective);//
            }
            return facultyTechnicalElective;
        }
        return null;
    }

    public Course createCourse(String courseId, String name, int capacity, float credit, float ects, String type, int semesterId, Instructor instructor) { //courses get created in the given function
        Course mandatoryCourse;
        Map<Integer, Semester> semesterMap = this.semesterMap;
        Semester semester = semesterMap.get(Integer.valueOf(semesterId));
        if (type.equals("Must")) {// if the course has a must type it will be created as mandatory course
            mandatoryCourse = new MandatoryCourse(courseId, name, capacity, credit, ects, semester, instructor);
            addMandatoryCourse(mandatoryCourse);
            semester.addCourse(mandatoryCourse);
            this.courses.add(mandatoryCourse);//
            Logger logger = Logger.getLogger(this.getClass().getName());
            logger.info("Course created: " + mandatoryCourse);
            return mandatoryCourse;
        } else {// if course has not must type it will not be created as mandatory, instead of it, it will be created as elective course
            ElectiveCourse electiveCourse = getElectiveFactory(type, courseId, name, capacity, credit, ects, instructor);
            addMandatoryCourse(electiveCourse);
            semester.addCourse(electiveCourse);
            this.courses.add(electiveCourse);//
            Logger logger = Logger.getLogger(this.getClass().getName());
            logger.info("Course created: " + electiveCourse);
            return electiveCourse;
        }

    }

    public Course createCourse(String courseId, String name, int capacity, float credit, float ects, String type, Instructor instructor) {
        Course electiveCourse = getElectiveFactory(type, courseId, name, capacity, credit, ects, instructor);
        Logger logger = Logger.getLogger(this.getClass().getName());
        logger.info("Course created: " + electiveCourse);
        return electiveCourse;
    }

    public Course findCourse(String courseId) {
        Course course = courses.stream()
                .filter(courseTmp -> courseId.equals(courseTmp.getCourseId()))
                .findAny()
                .orElse(null);
        return course;
    }

    public void addPrerequisite(Course mainCourse, Course prerequisiteCourse) {
        Logger logger = Logger.getLogger(this.getClass().getName());
        logger.info("Prerequisite: " + mainCourse.getCourseId() + "->" + prerequisiteCourse.getCourseId());
        mainCourse.addPrerequisite(prerequisiteCourse);
    }

    public Section createSection(int sectionId, Course course, Instructor instructor, List<Schedule> scheduleList) {
        return new Section(sectionId, course, instructor, scheduleList);
    }

    public void addSection(Section section) {
        Course course = section.getCourse();
        course.setSection(section);
    }
}
