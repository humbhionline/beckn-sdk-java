package in.succinct.beckn;

import org.json.simple.JSONObject;

public class OnTrack extends Request{
    public OnTrack() {
        super();
    }

    public OnTrack(String payLoad) {
        super(payLoad);
    }

    public OnTrack(JSONObject obj){
        super(obj);
    }



}
