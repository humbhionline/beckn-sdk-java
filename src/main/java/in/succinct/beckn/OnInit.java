package in.succinct.beckn;

import org.json.simple.JSONObject;

public class OnInit extends Request{
    public OnInit() {
        super();
    }

    public OnInit(String payLoad) {
        super(payLoad);
    }
    public OnInit(JSONObject object){
        super(object);
    }




}
