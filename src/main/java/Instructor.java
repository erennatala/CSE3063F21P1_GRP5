import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Instructor extends Person {
    private String fullName;
    private List<Student> advisees = new ArrayList<>(); //ArrayList needs to be corrected it has be to list instead of arrayList which has coming through constructer, there will be list interface instead of collection
    private List<Course> givenCourses = new ArrayList<>();

    public Instructor(int id, String name, String surname, List<String> emails) {
        super(id, name, surname, emails);
        this.fullName = name + " " + surname;
    }

    public Instructor(int id, String name, String surname) {
        super(id, name, surname);
        this.fullName = name + " " + surname;
    }

    public void checkTwoTechnical(Student student) {//the method written below checks the student if he/she tries to take two technical elective courses. If he/she tries error occurres
        List<Course> courseBasket = student.getCourseBasket();
        int count = 0;
        Iterator<Course> iterator = courseBasket.iterator();
        while (iterator.hasNext()) {
            Course next = iterator.next();
            if (next instanceof TechnicalElective) {
                count++;
                if (count > 2) {//after checks for the courses, related course gets added to non taken courses
                    Error technicalElectiveError = new TwoTechnicalElectiveError(student, next);
                    student.addNonTakenCourse(new TechnicalElective());
                    student.addError(technicalElectiveError);
                    iterator.remove();
                    count--;
                }
            }
        }
    }

    public void checkFTE(Student student) {//the function checks for FTE courses of student's course basket
        List<Course> courseBasket = student.getCourseBasket();
        Course course = courseBasket.stream()
                .filter(src -> src instanceof FacultyTechnicalElective)
                .findAny()
                .orElse(null);
        if (course != null) {//if there are no fte courses notInGraduationError occurs
            courseBasket.remove(course);
            Error notInGraduationError = new NotInGraduationError(student, course);
            student.addNonTakenCourse(new FacultyTechnicalElective());
            student.addError(notInGraduationError);
        }

    }

    public void graduationProjectCheck(Student student) {// the function checks for the student's graduation project
        List<Course> courseBasket = student.getCourseBasket();
        if (student.getCompletedCredit() < 165) {//condition looks for student's completed credit if it is enough to take graduation project
            Course course = courseBasket.stream()
                    .filter(src -> src.getCourseId().equals("CSE4197"))
                    .findAny()
                    .orElse(null);
            if (course != null) {
                courseBasket.remove(course);
                Error projectError = new ProjectError(student, course);
                student.addNonTakenCourse(course);
                student.addError(projectError);
            }
        }
    }

    public void checkMinimumCredit(Student student) {//the function checks the student's course basket and iteration process runs.
        List<Course> courseBasket = student.getCourseBasket();
        Iterator<Course> iterator = courseBasket.iterator();
        while (iterator.hasNext()) {
            Course next = iterator.next();
            if (next instanceof TechnicalElective && student.getCompletedCredit() < ((TechnicalElective) next).getMinimumCredit()) {//if condition checks for minimum credit condition to take Technical Elective course
                Error uncompletedCreditError = new UncompletedCreditError(student, next);
                student.addNonTakenCourse(new TechnicalElective());
                student.addError(uncompletedCreditError);
                iterator.remove();
            }
        }
    }
    public Course selectNonTakenCourse(Course course){//the method written below checks for the instances of courses if it is Technical Elective, Faculty Technical Elective or NT_UE Elective then selects the non-taken one
        if(course instanceof MandatoryCourse) {
            return course;
        }else if(course instanceof TechnicalElective){
            return new TechnicalElective();
        }else if(course instanceof FacultyTechnicalElective){
            return new FacultyTechnicalElective();
        }else if(course instanceof NT_UElective){
            return new NT_UElective();
        }
        return null;
    }
    public void collisionCheck(List<Course> courseBasket,Student student){//the function uses a loop to get in course list and, it compares them with each other. By the priority of colliding lectures it will start a delete process. And deleted courses will be sent to non taken courses list.
        List<Course> willBeRemoved = new ArrayList<>();

        Iterator<Course> basketIterator = courseBasket.iterator();
        while(basketIterator.hasNext()){
            Course firstCourse = basketIterator.next();
            if(willBeRemoved.contains(firstCourse)){
                continue;
            }
            Iterator<Course> secondIterator = courseBasket.iterator();
            //tempCourse = secondIterator.next();
            Section firstSection = firstCourse.getSection();
            List<Schedule> firstScheduleList = firstSection.getScheduleList();
            while(secondIterator.hasNext()){
                Course tempCourse = secondIterator.next();
                if (willBeRemoved.contains(tempCourse)){
                    continue;
                }
                if(firstCourse.equals(tempCourse)) {
                    continue;
                }

                Section secondSection = tempCourse.getSection();
                List<Schedule> secondScheduleList = secondSection.getScheduleList();
                List<Schedule> collisions = firstScheduleList.stream()
                        .filter(e -> (secondScheduleList.stream()
                                .filter(d -> (d.compareTo(e) == 1))
                                .count()) >= 1)
                        .collect(Collectors.toList());
                if(!collisions.isEmpty()) {

                        if(firstCourse instanceof MandatoryCourse){
                            Course selectedCourse = selectNonTakenCourse(tempCourse);
                            Error error = new CollisionError(student,firstCourse,tempCourse);
                            student.addError(error);
                            student.addNonTakenCourse(selectedCourse);
                            willBeRemoved.add(tempCourse);

                        }
                        else{
                            Course selectedCourse = selectNonTakenCourse(firstCourse);
                            Error error = new CollisionError(student,tempCourse,firstCourse);
                            student.addError(error);
                            student.addNonTakenCourse(selectedCourse);
                            willBeRemoved.add(firstCourse);

                        }
                }
                //tempCourse = secondIterator.next();
            }
            //firstCourse = basketIterator.next();

        }
        courseBasket.removeAll(willBeRemoved);


//        while(basketIterator.hasNext()){
//            Course firstCourse = basketIterator.next();
//            System.out.println(firstCourse.getName());
//            System.out.println("*************");
//            Iterator<Course> secondIterator = courseBasket.iterator();
//            //tempCourse = secondIterator.next();
//            Section firstSection = firstCourse.getSection();
//            List<Schedule> firstScheduleList = firstSection.getScheduleList();
//            while(secondIterator.hasNext()){
//                Course tempCourse = secondIterator.next();
//                System.out.println(tempCourse.getName());
//                if(firstCourse.equals(tempCourse)) {
//                    System.out.println("hi");
//                    continue;
//                }
//
//                Section secondSection = tempCourse.getSection();
//                List<Schedule> secondScheduleList = secondSection.getScheduleList();
//                List<Schedule> collisions = firstScheduleList.stream()
//                        .filter(e -> (secondScheduleList.stream()
//                                .filter(d -> (d.compareTo(e) == 1))
//                                .count()) >= 1)
//                        .collect(Collectors.toList());
//                if(!collisions.isEmpty()) {
//                    Course selectedCourse = selectNonTakenCourse(tempCourse);
//                    Error error = new CollisionError(student,firstCourse,tempCourse);
//                    student.addNonTakenCourse(selectedCourse);
//                    secondIterator.remove();
////                    if(firstCourse instanceof MandatoryCourse){
////                        Course selectedCourse = selectNonTakenCourse(tempCourse);
////                        Error error = new CollisionError(student,firstCourse,tempCourse);
////                        student.addNonTakenCourse(selectedCourse);
////                        secondIterator.remove();
////                    }
////                    else{
////                        Course selectedCourse = selectNonTakenCourse(firstCourse);
////                        Error error = new CollisionError(student,tempCourse,firstCourse);
////                        student.addNonTakenCourse(selectedCourse);
////                        basketIterator.remove();
////                    }
//                }
//                //tempCourse = secondIterator.next();
//            }
//            //firstCourse = basketIterator.next();
//
//        }

    }

    public void approveStudentBasket(Student student) {//the function checks the student if it is appropriate to approve via season amd elective courses
        String season = student.getSemester().getSeason();
        int semesterId = student.getSemester().getSemesterId();

        if ("Fall".equals(season)) {
            checkTwoTechnical(student);
        }

        if (!(semesterId == 7 || semesterId == 8) && "Fall".equals(season)) {
            checkFTE(student);
        }

        graduationProjectCheck(student);

        checkMinimumCredit(student);

        List<Course> courseBasket = student.getCourseBasket();
        collisionCheck(courseBasket,student);

    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<Student> getAdvisees() {
        return advisees;
    }

    public void setAdvisees(List<Student> advisees) {
        this.advisees = advisees;
    }

    public List<Course> getGivenCourses() {
        return givenCourses;
    }

    public void setGivenCourses(List<Course> givenCourses) {
        this.givenCourses = givenCourses;
    }

    public void addGivenCourse(Course course) {
        givenCourses.add(course);
    }

    public void addAdvisees(Student student) {
        advisees.add(student);
    }

    public void showGivenCourses() {
        for (Course course :
                givenCourses) {
            System.out.println(course.getCourseId());
        }
    }

    @Override
    public String toString() {
        //showGivenCourses();
        return "Instructor{} " + super.toString();
    }


}
