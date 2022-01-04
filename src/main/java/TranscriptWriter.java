import org.apache.log4j.Logger;
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
        Logger logger = Logger.getLogger(this.getClass().getName());
        JSONObject transcriptMap1 = new JSONObject(transcriptMap);//transriptMap JSONObjects gets created

        try {//writes the transcripts and names them with student's ID
            FileWriter outputFile = new FileWriter("transcripts/" + transcriptMap1.get("ID").toString() + ".json");
            outputFile.write(transcriptMap1.toString(4));
            outputFile.close();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

    }

    public void startWriter() {//it will take all students one by and sends the information to writeTranscript function
        Logger logger = Logger.getLogger(this.getClass().getName());
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
                try {
                    transcript.getTranscriptMap().put("Semester", student.getSemester().getSemesterId());
                }catch (NullPointerException np) {
                    logger.error(np.getMessage());
                }
                transcript.getTranscriptMap().put("Completed Credit", student.getCompletedCredit());
                writeTranscript(transcript.getTranscriptMap(), student.getId());
            }catch(NullPointerException e){
                logger.error(e.getMessage());
            }
        }

    }
}
