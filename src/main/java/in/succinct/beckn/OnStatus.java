package in.succinct.beckn;

import org.json.simple.JSONObject;

public class OnStatus extends Request{
    public OnStatus() {
        super();
    }

    public OnStatus(String payLoad) {
        super(payLoad);
    }

    public OnStatus(JSONObject obj){
        super(obj);
    }



}
