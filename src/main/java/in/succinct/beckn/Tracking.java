package in.succinct.beckn;

import org.json.simple.JSONObject;

public class Tracking extends BecknObjectWithId{

    public Tracking() {
    }

    public Tracking(String payload) {
        super(payload);
    }

    public Tracking(JSONObject object) {
        super(object);
    }

    public void setUrl(String url){
        set("url",url);
    }

    public String getUrl(){
        return get("url");
    }

    public Location getLocation(){
        return get(Location.class, "location");
    }
    public void setLocation(Location location){
        set("location",location);
    }
    
    public Status getStatus(){
        return getEnum(Status.class, "status");
    }
    public void setStatus(Status status){
        setEnum("status",status);
    }

    public enum Status {
        active,
        inactive
    }
}
