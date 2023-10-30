package in.succinct.beckn;

import com.venky.core.date.DateUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

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


    public static DateFormat TIME_FORMAT_NO_SEP =   new SimpleDateFormat("HHmm");

    public static class Range extends BecknObject {
        public Date getStart(){
            return parseDateTime(get("start"),TIMESTAMP_FORMAT,TIME_FORMAT_NO_SEP);
        }
        public void setStart(Date start){
            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
            calendar.setTime(start);
            if (calendar.get(Calendar.YEAR) == 1970){
                set("start",start,TIME_FORMAT_NO_SEP);
            }else {
                set("start",start,TIMESTAMP_FORMAT);
            }
        }

        public Date getEnd(){
            return parseDateTime(get("end"),TIMESTAMP_FORMAT,TIME_FORMAT_NO_SEP);
        }
        public void setEnd(Date end){

            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
            calendar.setTime(end);
            if (calendar.get(Calendar.YEAR) < 2000){
                set("end",end,TIME_FORMAT_NO_SEP);
            }else {
                set("end",end,TIMESTAMP_FORMAT);
            }

        }

    }
    public Schedule getSchedule(){
        return get(Schedule.class,"schedule");
    }
    public void setSchedule(Schedule schedule){
        set("schedule",schedule);
    }
}
