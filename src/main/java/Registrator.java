import java.util.Random;

public class Registrator {
    //1 adet öğrenciyi 1 dönem kayıt eder
    private Student student;
    private Random randomGenerator = new Random();
    private Approver approver;
    private CourseExpert courseExpert;
    //semester içerisinde dersleri tek tek ara
    //eğer elective gelirse curriculumda elective listlere bak bak
    // baskete eklendikten sonra basketi instructorun approvelaması için gönder

    public Registrator(Student student, CourseExpert courseExpert) {
        this.student = student;
        this.courseExpert = courseExpert;
        this.approver = new Approver(student);
    }

    public void selectCourse() {

    }

    public void addBasket(Course course) {
        student.addCourseToBasket(course);
    }
    public void sendAdvisorApproval(){

    }

    public void startRegistration() {

        Semester semester = student.getSemester();
        for (Course course : student.getFailedCourses()) {
            if (approver.approveCourse(course)) addBasket(course);
        }

        for (Course course : semester.getCourseList()) {
            Course elective = null;
            // Eğer elective ise curriculuma bak ve rastgele bir elective seç
            // curriculumdaki hangi listi alacağını nasılbilicek NTE UE T ?
            // dersi ve öğrenciyi approvera gönder eğer sıkıntı varsa yukarı dön
            // eğer sıkıntı varsa error çalıştır
            if (course instanceof ElectiveCourse) {
                int index = 0;
                do {
                    if (course instanceof TechnicalElective) {
                        index = randomGenerator.nextInt(courseExpert.getTechnicalList().size());
                        elective = courseExpert.getTechnicalList().get(index);
                    } else if (course instanceof NT_UElective) {
                        index = randomGenerator.nextInt(courseExpert.getNT_UList().size());
                        elective = courseExpert.getNT_UList().get(index);
                    } else if (course instanceof FacultyTechnicalElective){
                        index = randomGenerator.nextInt(courseExpert.getFacultyTechnicalList().size());
                        elective = courseExpert.getFacultyTechnicalList().get(index);
                    }

                }while(!approver.approveCourse(elective));
                addBasket(elective);
            }else if (approver.approveCourse(course)) {
                addBasket(course);
            }
        }
        System.out.println(student);
        for (Course course2 :
                student.getCourseBasket()) {
            System.out.println(course2);
        }


    }

}

