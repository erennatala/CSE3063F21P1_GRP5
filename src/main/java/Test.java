import java.util.*;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] args) {
       Simulation simulation = new Simulation();
       simulation.start();

//        List<Schedule> list1 = new ArrayList<>();
//        list1.add(new Schedule("Monday","16:00","16:50"));
//        list1.add(new Schedule("Tuesday","16:00","16:50"));
//        List<Schedule> list2 = new ArrayList<>();
//        list2.add(new Schedule("Monday","16:00","16:50"));
//        list2.add(new Schedule("Tuesday","12:00","12:50"));
//
//        List<Schedule> collisions = list1.stream()
//                .filter(
//                        e -> (
//                                list2.stream()
//                                        .filter(
//                                                d ->
//                                                        (d.compareTo(e) == 1)
//                                        ).count()
//                        ) >= 1
//                )
//                .collect(Collectors.toList());
//        System.out.println(list2);
//
//        System.out.println(collisions);

//        unavailable = list1.stream()
//                .filter(
//                        e -> (
//                                list2.stream()
//                                        .filter(
//                                                d ->
//                                                        d.equals(e)
//                                        )
//                                        .count()
//                        ) < 1
//                )
//                .collect(Collectors.toList())
//        ;







//       List<String> emails = new ArrayList<>();
//
//       emails.add("keremmican@gmail.com");
//       Map<String, Object> transcriptMap = new HashMap<>();
//
//       transcriptMap.put("ID", 150119629);
//       transcriptMap.put("Name","Kerem");
//       transcriptMap.put("Surname","Mican");
//       transcriptMap.put("Emails", emails);
//       transcriptMap.put("Semester",8);
//       transcriptMap.put("CGPA",8);
//       transcriptMap.put("Advisor","Gülen Greenvood");
//
//
//
//       TranscriptWriter transcriptWriter = new TranscriptWriter();
//
//       transcriptWriter.writeTranscript(transcriptMap, 150119629);


//        Map<Integer,Semester> semesterMap = new HashMap<Integer,Semester>();
//        Integer semesterId;
//        for (int i = 1; i < 9; i++) {
//            semesterId = Integer.valueOf(i);
//            semesterMap.put(semesterId,new Semester(semesterId));
//        }
//        Iterator<Map.Entry<Integer,Semester>> new_Iterator = semesterMap.entrySet().iterator();
//
//        while(new_Iterator.hasNext()){
//            Map.Entry<Integer,Semester> new_map = (Map.Entry<Integer, Semester>) new_Iterator.next();
//            System.out.println(new_map.getKey()+" = "+new_map.getValue());
//        }


    }
}
