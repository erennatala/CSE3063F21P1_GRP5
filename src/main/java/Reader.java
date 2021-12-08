import org.json.JSONArray;

public interface Reader {
    void open();
    void read(JSONArray curr_input);
}
