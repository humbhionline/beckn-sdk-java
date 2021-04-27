package in.succinct.beckn;

import org.json.simple.JSONObject;

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
        return getDate("timestamp", TIMESTAMP_FORMAT);
    }
    public void setTimestamp(Date timestamp){
        set("timestamp",timestamp,TIMESTAMP_FORMAT);
    }

    public Duration getDuration(){
        return Duration.parse(get("duration"));
    }
    public void setDuration(Duration duration){
        set("duration",duration.toString());
    }

    public Range getRange(){
        return get("range");
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
            return getDate("start",TIMESTAMP_FORMAT);
        }
        public void setStart(Date start){
            set("start",start,TIMESTAMP_FORMAT);
        }

        public Date getEnd(){
            return getDate("end",TIMESTAMP_FORMAT);
        }
        public void setEnd(Date end){
            set("end",end,TIMESTAMP_FORMAT);
        }

    }
}
