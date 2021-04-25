package in.succinct.beckn;

import org.json.simple.JSONObject;

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
}
