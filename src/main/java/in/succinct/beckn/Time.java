package in.succinct.beckn;

import java.time.Duration;
import java.util.Date;

public class Time extends BecknObject {
    public Time() {
        super();
    }
    public String getLabel(){
        return get("label");
    }
    public void setLabel(String label){
        set("label",label);
    }
    public Date getTimestamp(){
        return getTimestamp("timestamp");
    }
    public void setTimestamp(Date timestamp){
        set("timestamp",timestamp,TIMESTAMP_FORMAT);
    }

    public Duration getDuration(){
        String d = get("duration");
        return d == null ? null : Duration.parse(d);
    }
    public void setDuration(Duration duration){
        set("duration", duration == null ? null  :duration.toString());
    }

    public Range getRange(){
        return get(Range.class,"range");
    }
    public void setRange(Range range){
        set("range",range);
    }

    public String getDays(){
        return get("days");
    }
    public void setDays(String days){
        set("days",days);
    }
    
    public static class Range extends BecknObject {
        public Date getStart(){
            return getTime("start");
        }
        public void setStart(Date start){
            set("start",start,TIME_FORMAT);
        }

        public Date getEnd(){
            return getTime("end");
        }
        public void setEnd(Date end){
            set("end",end,TIME_FORMAT);
        }

    }
    public Schedule getSchedule(){
        return get(Schedule.class,"schedule");
    }
    public void setSchedule(Schedule schedule){
        set("schedule",schedule);
    }
}
