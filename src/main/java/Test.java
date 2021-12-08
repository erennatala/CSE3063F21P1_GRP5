import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
//        Simulation simulation = new Simulation();
//        simulation.start();

        List<Semester> semesters= new ArrayList<Semester>();
        for (int i = 1; i < 9; i++) {
            semesters.add(new Semester(i));
        }
        for (int i = 1; i < 9; i++) {
            System.out.println(semesters.indexOf(i));
        }
    }
}
