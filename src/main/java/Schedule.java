import org.jetbrains.annotations.NotNull;


public class Schedule implements Comparable<Schedule>{//A class for Schedule
    private String day;//day attribute for days in schedule
    private int startTime;//startTime attribute shows the start time in schedule
    private int endTime;//endTime attribute shows the start time in schedule

    public Schedule(String day, String start, String end) {
        this.day = day;
        String [] startParsed = start.split(":");//splits start
        String [] endParsed = end.split(":");//splits end
        this.startTime = Integer.parseInt(startParsed[0] + startParsed[1]);
        this.endTime = Integer.parseInt(endParsed[0] + endParsed[1]);
    }


    public String getDay() {
        return day;
    }

    public Schedule setDay(String day) {
        this.day = day;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Schedule)) return false;

        Schedule schedule = (Schedule) o;

        if (startTime != schedule.startTime) return false;
        if (endTime != schedule.endTime) return false;
        return getDay().equals(schedule.getDay());
    }

    @Override
    public int hashCode() {
        int result = getDay().hashCode();
        result = 31 * result + startTime;
        result = 31 * result + endTime;
        return result;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "day='" + day + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }

    @Override
    public int compareTo(@NotNull Schedule o) {
        if (this.day.equals(o.getDay())) {
            // if two schedule collides return 1
            if((o.startTime < this.endTime && o.startTime >= this.startTime) || (o.endTime <= this.endTime && o.endTime > this.startTime)){
                return 1;
            }else if(o.startTime < this.startTime && o.endTime > this.endTime){
                return 1;
            }
        }
        return 0;
    }
}
