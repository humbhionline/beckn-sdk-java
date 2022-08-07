package in.succinct.beckn;

import org.json.simple.JSONObject;

public class Tracking extends BecknObject{

    public Tracking() {
    }

    public Tracking(String payload) {
        super(payload);
    }

    public Tracking(JSONObject object) {
        super(object);
    }

    public String getUrl(){
        return get("url");
    }
    public void setUrl(String url){
        set("url",url);
    }

    public String getStatus(){
        return get("status");
    }
    public void setStatus(String status){
        set("status",status);
    }
}
