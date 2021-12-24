import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class TranscriptWriter {

    private StudentExpert studentExpert;

    public void writeTranscript(Map<String, Object> transcriptMap, int studentId) { //bir öğrenci transkripti yazma
//        JSONObject transcriptMap = (JSONObject) transcript.getTranscriptMap();
        JSONObject transcriptMap1 = new JSONObject(transcriptMap);

        try {
            FileWriter outputFile = new FileWriter("transcripts/" + transcriptMap1.get("ID").toString() + ".json");
            outputFile.write(transcriptMap1.toString(4));
            outputFile.close();
        }
        catch (IOException e) {}

    }

    public void startWriter() {
        Map<Integer, Student> studentMap = studentExpert.getStudentMap();

        Iterator<Map.Entry<Integer,Student>> studentIterator = studentMap.entrySet().iterator();
        while (studentIterator.hasNext()) {
            Map.Entry<Integer, Student> iteratorMap = (Map.Entry<Integer, Student>) studentIterator.next();

            Student student = iteratorMap.getValue();
            Transcript transcript = student.getTranscript();

//            writeTranscript(transcript, student.getId());
        }
    }
}
