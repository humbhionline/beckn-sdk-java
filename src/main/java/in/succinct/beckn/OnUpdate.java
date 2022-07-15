package in.succinct.beckn;

import org.json.simple.JSONObject;

public class OnUpdate extends Request{
    public OnUpdate() {
        super();
    }

    public OnUpdate(String payLoad) {
        super(payLoad);
    }

    public OnUpdate(JSONObject obj){
        super(obj);
    }



}
