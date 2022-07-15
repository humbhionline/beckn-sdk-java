package in.succinct.beckn;

import org.json.simple.JSONObject;

public class OnSupport extends Request{
    public OnSupport() {
        super();
    }

    public OnSupport(String payLoad) {
        super(payLoad);
    }

    public OnSupport(JSONObject obj){
        super(obj);
    }



}
