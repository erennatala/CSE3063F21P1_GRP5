import org.json.JSONArray;

public class CourseReader implements Reader{

    CourseExpert courseExpert;
    public CourseReader() {
    }

    public CourseReader(CourseExpert courseExpert) {
        this.courseExpert = courseExpert;
    }

    @Override
    public void open() {

    }

    @Override
    public void read(JSONArray curr_input) {

    }
}
