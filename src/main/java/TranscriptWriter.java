import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class TranscriptWriter {// TranscriptWriter class writes the transcript

    private StudentExpert studentExpert;

    public TranscriptWriter(StudentExpert studentExpert) {
        this.studentExpert = studentExpert;
    }

    public void writeTranscript(Map<String, Object> transcriptMap, int studentId) { //the function takes the transcript map and studentId as parameters

        JSONObject transcriptMap1 = new JSONObject(transcriptMap);//transriptMap JSONObjects gets created

        try {//writes the transcripts and names them with student's ID
            FileWriter outputFile = new FileWriter("transcripts/" + transcriptMap1.get("ID").toString() + ".json");
            outputFile.write(transcriptMap1.toString(4));
            outputFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void startWriter() {//it will take all students one by and sends the information to writeTranscript function

        Map<Integer, Student> studentMap = studentExpert.getStudentMap();
        Iterator<Map.Entry<Integer, Student>> studentIterator = studentMap.entrySet().iterator();

        while (studentIterator.hasNext()) {
            Map.Entry<Integer, Student> iteratorMap = (Map.Entry<Integer, Student>) studentIterator.next();
            Student student = iteratorMap.getValue();
            Transcript transcript = student.getTranscript();
            transcript.getTranscriptMap().put("CGPA: ", student.getCgpa());
            transcript.getTranscriptMap().put("Semester: ", student.getSemester().getSemesterId());
            transcript.getTranscriptMap().put("Completed Credit", student.getCompletedCredit());
            writeTranscript(transcript.getTranscriptMap(), student.getId());

        }
    }
}
