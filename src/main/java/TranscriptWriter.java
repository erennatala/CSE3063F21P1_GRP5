import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class TranscriptWriter {

    private StudentExpert studentExpert;

    public TranscriptWriter(StudentExpert studentExpert) {
        this.studentExpert = studentExpert;
    }

    public void writeTranscript(Map<String, Object> transcriptMap, int studentId) {

        JSONObject transcriptMap1 = new JSONObject(transcriptMap);

        try {
            FileWriter outputFile = new FileWriter("transcripts/" + transcriptMap1.get("ID").toString() + ".json");
            outputFile.write(transcriptMap1.toString(4));
            outputFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void startWriter() {

        Map<Integer, Student> studentMap = studentExpert.getStudentMap();
        Iterator<Map.Entry<Integer, Student>> studentIterator = studentMap.entrySet().iterator();

        while (studentIterator.hasNext()) {
            try {
                Map.Entry<Integer, Student> iteratorMap = (Map.Entry<Integer, Student>) studentIterator.next();
                Student student = iteratorMap.getValue();
                Transcript transcript = student.getTranscript();
                double CGPA = student.getCgpa();
                double roundOff = (double) Math.round(CGPA * 100) / 100;
                transcript.getTranscriptMap().put("CGPA", roundOff);
                transcript.getTranscriptMap().put("Semester", student.getSemester().getSemesterId());
                transcript.getTranscriptMap().put("Completed Credit", student.getCompletedCredit());
                writeTranscript(transcript.getTranscriptMap(), student.getId());
            }catch(NullPointerException e){
            }
        }

    }
}
