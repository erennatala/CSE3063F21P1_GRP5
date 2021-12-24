import java.util.ArrayList;
import java.util.List;

public class Instructor extends Person{
    private String fullName;
    private List<Student> advisees = new ArrayList<>(); //list needs to be corrected it has be to list instead of arrayList which has coming through constructer, there will be list interface instead of collection
    private List<Course> givenCourses = new ArrayList<>();

    public Instructor(int id, String name, String surname, ArrayList<String> emails) {
        super(id, name, surname, emails);
        this.fullName= name+" "+surname;
    }

    public Instructor(int id, String name, String surname) {
        super(id, name, surname);
        this.fullName = name + " " + surname;
    }

    public void approveStudentBasket(Student student){

        for (Course course : student.getCourseBasket()) {
            System.out.println(course.getCourseId());
        }

        int technicalCount = 0;

        for (int b = 0; b<student.getCourseBasket().size(); b++) {

            if (student.getCourseBasket().get(b) instanceof TechnicalElective) {   // Look for 2 technical
                technicalCount++;

                if (technicalCount > 2) {
                    TwoTechnicalElectiveError technicalElectiveError = new TwoTechnicalElectiveError(student, student.getCourseBasket().get(b));
                    student.addError(technicalElectiveError);
                    student.getCourseBasket().remove(student.getCourseBasket().get(b));

                    technicalCount--;
                }
            }
        }

        for (Course course: student.getCourseBasket()) {
            int semesterId = student.getSemester().getSemesterId();
            String season = student.getSemester().getSeason();

            if (course instanceof FacultyTechnicalElective && season.equals("Fall")) {  // Student can not take FTE course in Fall semester unless in graduation year
                if (!(semesterId == 7 || semesterId == 8)) {
                    NotInGraduationError notInGraduationError = new NotInGraduationError(student, course);
                    student.addError(notInGraduationError);

                    student.getCourseBasket().remove(course);
                }
            }
        }

        for (Course course: student.getCourseBasket()) {
            if (course.getCourseId().equals("CSE4197") && student.getCompletedCredit() < 165){ // Student can not take graduation project because completed credits < 165
                ProjectError projectError = new ProjectError(student, course);
                student.addError(projectError);

                student.getCourseBasket().remove(course);
            }
        }

        for (int c=student.getCourseBasket().size()-1; c>=0; c--) {
            if ((student.getCourseBasket().get(c) instanceof TechnicalElective)){   // Look for uncompleted credit
                if ((student.getCompletedCredit() < ((TechnicalElective) student.getCourseBasket().get(c)).getMinimumCredit())){
                    UncompletedCreditError uncompletedCreditError = new UncompletedCreditError(student, student.getCourseBasket().get(c));
                    student.addError(uncompletedCreditError);

                    student.getCourseBasket().remove(student.getCourseBasket().get(c));
                }
            }
        }
            // Look for collision

        for (int i = 0; i<student.getCourseBasket().size(); i++) { //basket
            try {

                List<String> mainBasketScheduleDays = new ArrayList<String>(student.getCourseBasket().get(i).getSectionList().get(0).getScheduleList().keySet());

                for (int j = i+1; j<student.getCourseBasket().size(); j++) {
                    try {
                        List<String> secondBasketScheduleDays = new ArrayList<String>(student.getCourseBasket().get(j).getSectionList().get(0).getScheduleList().keySet());

                        for (int k = 0; k < mainBasketScheduleDays.size(); k++) {//basket içindeki dersin içindeki sectionın içindeki schedulelist

                            for (int z = 0; z < secondBasketScheduleDays.size(); z++) {

                                String mainStart = student.getCourseBasket().get(i).getSectionList().get(0).getScheduleList().get(mainBasketScheduleDays.get(k)).get(0);
                                String mainEnd = student.getCourseBasket().get(i).getSectionList().get(0).getScheduleList().get(mainBasketScheduleDays.get(k)).get(1);

                                String[] mainStartParts = mainStart.split(":");
                                String[] mainEndParts = mainEnd.split(":");

                                int mainStartParsed = Integer.parseInt(mainStartParts[0]+mainStartParts[1]);
                                int mainEndParsed = Integer.parseInt(mainEndParts[0]+mainEndParts[1]);

                                String secondStart = student.getCourseBasket().get(j).getSectionList().get(0).getScheduleList().get(secondBasketScheduleDays.get(z)).get(0);
                                String secondEnd = student.getCourseBasket().get(j).getSectionList().get(0).getScheduleList().get(secondBasketScheduleDays.get(z)).get(1);

                                String[] secondStartParts = secondStart.split(":");
                                String[] secondEndParts = secondEnd.split(":");

                                int secondStartParsed = Integer.parseInt(secondStartParts[0]+secondStartParts[1]);
                                int secondEndParsed = Integer.parseInt(secondEndParts[0]+secondEndParts[1]);

                                if (mainBasketScheduleDays.get(k).equals(secondBasketScheduleDays.get(z))) { //aynı gün

                                    if ((secondStartParsed <= mainEndParsed && secondStartParsed >= mainStartParsed) || (secondEndParsed <= mainEndParsed && secondEndParsed >= mainStartParsed)) {
                                        System.out.println("same day, nearly same hours, collision" + " " + student.getCourseBasket().get(i).getCourseId() + " and " + student.getCourseBasket().get(j).getCourseId());

                                        student.getCourseBasket().remove(student.getCourseBasket().get(j));
                                    }
                                }
                            }
                        }
                    }
                    catch (IndexOutOfBoundsException e) {
                        System.out.println("schedule is null " + student.getCourseBasket().get(j).getCourseId());
                    }
                }
            }
            catch (IndexOutOfBoundsException e) {
                System.out.println("schedule is null " + student.getCourseBasket().get(i).getCourseId());
            }
        }
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
        givenCourses = givenCourses;
        givenCourses.add(course);
    }
    public void addAdvisees(Student student){
        advisees = advisees;
        advisees.add(student);
    }

    public void showGivenCourses(){
        for (Course course :
                givenCourses) {
            System.out.println(course.getCourseId());
        }
    }

//    public void checkCollision(Student student, List<Course> courseBasket) {
//        courseBasket = new ArrayList<Course>();
//
//        courseBasket.add(student.getCourseBasket().get(2));
//
//        for (int i = 0; i<student.getCourseBasket().size(); i++) { //basket
//
//            List<String> mainBasketScheduleDays = new ArrayList<String>(student.getCourseBasket().get(i).getSectionList().get(0).getScheduleList().keySet());
//
//            for (int k = 0; k < mainBasketScheduleDays.size(); k++) { //basket içindeki dersin içindeki sectionın içindeki schedulelist
//
//                for (int j = 0; j<courseBasket.size(); j++) {
//
//                    List<String> secondBasketScheduleDays = new ArrayList<String>(courseBasket.get(j).getSectionList().get(0).getScheduleList().keySet());
//
//                    for (int z = 0; z < secondBasketScheduleDays.size(); z++) {
//
//                        String mainStart = student.getCourseBasket().get(i).getSectionList().get(0).getScheduleList().get(mainBasketScheduleDays.get(k)).get(0);
//                        String mainEnd = student.getCourseBasket().get(i).getSectionList().get(0).getScheduleList().get(mainBasketScheduleDays.get(k)).get(1);
//
//                        String[] mainStartParts = mainStart.split(":");
//                        String[] mainEndParts = mainEnd.split(":");
//
//                        int mainStartParsed = Integer.parseInt(mainStartParts[0]+mainStartParts[1]);
//                        int mainEndParsed = Integer.parseInt(mainEndParts[0]+mainEndParts[1]);
//
//                        String secondStart = courseBasket.get(j).getSectionList().get(0).getScheduleList().get(secondBasketScheduleDays.get(z)).get(0);
//                        String secondEnd = courseBasket.get(j).getSectionList().get(0).getScheduleList().get(secondBasketScheduleDays.get(z)).get(1);
//
//                        String[] secondStartParts = secondStart.split(":");
//                        String[] secondEndParts = secondEnd.split(":");
//
//                        int secondStartParsed = Integer.parseInt(secondStartParts[0]+secondStartParts[1]);
//                        int secondEndParsed = Integer.parseInt(secondEndParts[0]+secondEndParts[1]);
//
//                        if (mainBasketScheduleDays.get(k).equals(secondBasketScheduleDays.get(z))) { //aynı gün
//
//                            if ((secondStartParsed <= mainEndParsed && secondStartParsed >= mainStartParsed) || (secondEndParsed <= mainEndParsed && secondEndParsed >= mainStartParsed)) {
//                                System.out.println("same day, nearly same hours, collision" + " " + student.getCourseBasket().get(i).getCourseId() + " and " + courseBasket.get(j).getCourseId());
//                            }
//                        }
//
//
//                    }
//                }
//            }
//        }
//    }

    @Override
    public String toString() {
        //showGivenCourses();
        return "Instructor{} " + super.toString();
    }


}
