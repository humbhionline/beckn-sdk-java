package in.succinct.beckn;

import in.succinct.beckn.BecknObject;
import in.succinct.beckn.BecknObjects;
import in.succinct.beckn.BecknStrings;

public class Schedule extends BecknObject {
    public Schedule() {super();}

    public String getFrequency(){
        return get("frequency");
    }
    public void setFrequency(String frequency){
        set("frequency", frequency);
    }

    public BecknStrings getHolidays(){
        return get(BecknStrings.class, "holidays");
    }
    public void setHolidays(BecknStrings holidays){
        set("holidays", holidays);
    }

    public BecknStrings getTimes(){
        return get(BecknStrings.class,"times");
    }
    public void setTimes(BecknStrings times){
        set("times", times);
    }



}
