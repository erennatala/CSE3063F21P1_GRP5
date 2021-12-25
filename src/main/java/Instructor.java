import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Instructor extends Person {
    private String fullName;
    private List<Student> advisees = new ArrayList<>(); //list needs to be corrected it has be to list instead of arrayList which has coming through constructer, there will be list interface instead of collection
    private List<Course> givenCourses = new ArrayList<>();

    public Instructor(int id, String name, String surname, List<String> emails) {
        super(id, name, surname, emails);
        this.fullName = name + " " + surname;
    }

    public Instructor(int id, String name, String surname) {
        super(id, name, surname);
        this.fullName = name + " " + surname;
    }

    public void checkTwoTechnical(Student student) {
        List<Course> courseBasket = student.getCourseBasket();
        int count = 0;
        Iterator<Course> iterator = courseBasket.iterator();
        while (iterator.hasNext()) {
            Course next = iterator.next();
            if (next instanceof TechnicalElective) {
                count++;
                if (count > 2) {
                    TwoTechnicalElectiveError technicalElectiveError = new TwoTechnicalElectiveError(student, next);
                    student.addNonTakenCourse(new TechnicalElective());
                    student.addError(technicalElectiveError);
                    iterator.remove();
                    count--;
                }
            }
        }
    }

    public void checkFTE(Student student) {
        List<Course> courseBasket = student.getCourseBasket();
        Course course = courseBasket.stream()
                .filter(src -> src instanceof FacultyTechnicalElective)
                .findAny()
                .orElse(null);
        if (course != null) {
            courseBasket.remove(course);
            NotInGraduationError notInGraduationError = new NotInGraduationError(student, course);
            student.addNonTakenCourse(new FacultyTechnicalElective());
            student.addError(notInGraduationError);
        }

    }

    public void graduationProjectCheck(Student student) {
        List<Course> courseBasket = student.getCourseBasket();
        if (student.getCompletedCredit() < 165) {
            Course course = courseBasket.stream()
                    .filter(src -> src.getCourseId().equals("CSE4197"))
                    .findAny()
                    .orElse(null);
            if (course != null) {
                courseBasket.remove(course);
                ProjectError projectError = new ProjectError(student, course);
                student.addNonTakenCourse(course);
                student.addError(projectError);
            }
        }
    }

    public void checkMinimumCredit(Student student) {
        List<Course> courseBasket = student.getCourseBasket();
        Iterator<Course> iterator = courseBasket.iterator();
        while (iterator.hasNext()) {
            Course next = iterator.next();
            if (next instanceof TechnicalElective && student.getCompletedCredit() < ((TechnicalElective) next).getMinimumCredit()) {
                UncompletedCreditError uncompletedCreditError = new UncompletedCreditError(student, next);
                student.addNonTakenCourse(new TechnicalElective());
                student.addError(uncompletedCreditError);
                iterator.remove();
            }
        }
    }
//    public void collisionCheck(List<Course> courseBasket){
//        List<Course> newBasket = new ArrayList<>(courseBasket);
//
//        Iterator<Course> basketIterator = courseBasket.iterator();
//        Course firstCourse = basketIterator.next();
//        Course tempCourse = null;
//
//        while(basketIterator.hasNext()){
//
//            Iterator<Course> secondIterator = courseBasket.iterator();
//            tempCourse = secondIterator.next();
//            Section firstSection = firstCourse.getSection();
//            while(secondIterator.hasNext()){
//                List<>
//
//
//                tempCourse = secondIterator.next();
//            }
//            firstCourse = basketIterator.next();
//
//        }
//
//
//
//    }

    public void approveStudentBasket(Student student) {
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

        // Look for collision
        // Elective to nonTaken

//        for (int i = 0; i < student.getCourseBasket().size(); i++) { //basket
//            try {
//
//                List<String> mainBasketScheduleDays = new ArrayList<>(student.getCourseBasket().get(i).getSectionList().get(0).getScheduleList().keySet());
//
//                for (int j = i + 1; j < student.getCourseBasket().size(); j++) {
//                    try {
//                        List<String> secondBasketScheduleDays = new ArrayList<String>(student.getCourseBasket().get(j).getSectionList().get(0).getScheduleList().keySet());
//
//                        for (int k = 0; k < mainBasketScheduleDays.size(); k++) {//basket içindeki dersin içindeki sectionın içindeki schedulelist
//
//                            for (int z = 0; z < secondBasketScheduleDays.size(); z++) {
//
//                                String mainStart = student.getCourseBasket().get(i).getSectionList().get(0).getScheduleList().get(mainBasketScheduleDays.get(k)).get(0);
//                                String mainEnd = student.getCourseBasket().get(i).getSectionList().get(0).getScheduleList().get(mainBasketScheduleDays.get(k)).get(1);
//
//                                String[] mainStartParts = mainStart.split(":");
//                                String[] mainEndParts = mainEnd.split(":");
//
//                                int mainStartParsed = Integer.parseInt(mainStartParts[0] + mainStartParts[1]);
//                                int mainEndParsed = Integer.parseInt(mainEndParts[0] + mainEndParts[1]);
//
//                                String secondStart = student.getCourseBasket().get(j).getSectionList().get(0).getScheduleList().get(secondBasketScheduleDays.get(z)).get(0);
//                                String secondEnd = student.getCourseBasket().get(j).getSectionList().get(0).getScheduleList().get(secondBasketScheduleDays.get(z)).get(1);
//
//                                String[] secondStartParts = secondStart.split(":");
//                                String[] secondEndParts = secondEnd.split(":");
//
//                                int secondStartParsed = Integer.parseInt(secondStartParts[0] + secondStartParts[1]);
//                                int secondEndParsed = Integer.parseInt(secondEndParts[0] + secondEndParts[1]);
//
//                                if (mainBasketScheduleDays.get(k).equals(secondBasketScheduleDays.get(z))) { //aynı gün
//
//                                    if ((secondStartParsed <= mainEndParsed && secondStartParsed >= mainStartParsed) || (secondEndParsed <= mainEndParsed && secondEndParsed >= mainStartParsed)) {
//                                        System.out.println("same day, nearly same hours, collision" + " " + student.getCourseBasket().get(i).getCourseId() + " and " + student.getCourseBasket().get(j).getCourseId());
//                                        Error error = new CollisionError(student,student.getCourseBasket().get(i),student.getCourseBasket().get(j));
//                                        student.addError(error);
//                                        student.addNonTakenCourse(student.getActiveCourses().get(j));
//                                        student.getCourseBasket().remove(student.getCourseBasket().get(j));
//                                    }
//                                }
//                            }
//                        }
//                    } catch (IndexOutOfBoundsException e) {
//                        System.out.println("schedule is null " + student.getCourseBasket().get(j).getCourseId());
//                    }
//                }
//            } catch (IndexOutOfBoundsException e) {
//                System.out.println("schedule is null " + student.getCourseBasket().get(i).getCourseId());
//            }
//        }
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
