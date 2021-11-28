import java.util.Random;

public class Registrator {
    //1 adet öğrenciyi 1 dönem kayıt eder
    private Student student;
    private Semester semester;
    private Curriculum curriculum;
    private Random randomGenerator;
    //semester içerisinde dersleri tek tek ara
    //eğer elective gelirse curriculumda elective listlere bak bak
    // baskete eklendikten sonra basketi instructorun approvelaması için gönder


    public Registrator(Student student, Semester semester, Curriculum curriculum) {
        this.student = student;
        this.semester = semester;
        this.curriculum = curriculum;
        this.randomGenerator = new Random();
    }

    public void selectCourse() {

    }

    public void addBasket(Course course) {
        student.addActiveCourse(course);


    }

    public void startRegistration() {
        Approver approver = new Approver(student);
        //Öncelik kalan kurslar
//        for (Course course : student.getFailedCourses()) {
//            //try to add basket / approve
//            if (approver.approveCourse(course)) {
//                addBasket(course);
//            }
            //?
            //1 adet ders ve 1 adet öğrenciyi approvera gönderir eğer sıkıntı yoksa basket a ekler
            //Sıkıntı varsa error çalıştır // Errorların approverda oluşturulması lazım ? erroru orda oluştur buraya return et ? sonra öğrencinin erroruna koy ? Genel errora koy?

       // }
        //Semesterda alması gerekn kursları kontrol et
        for (Course course : semester.getCourseList()) {
            // Eğer elective ise curriculuma bak ve rastgele bir elective seç
            // curriculumdaki hangi listi alacağını nasılbilicek NTE UE T ?
            // dersi ve öğrenciyi approvera gönder eğer sıkıntı varsa yukarı dön
            // eğer sıkıntı varsa error çalıştır
            if (course instanceof ElectiveCourse) {
                //getlist(type overload) at curriculum
//                do {
//                    //get random elective from list
//
//                }while (approver.approveCourse(course));
                continue;
           }
            addBasket(course);
//                if (course instanceof MandatoryCourse) {
//                    if (approver.approveCourse(course))
//                        addBasket(course);
//                    //error ?
//                }
                // Mandatory ise
                // approver a gönder

                // sıkıntı yoksa basket'e ekle ?

            }
        }

    }

