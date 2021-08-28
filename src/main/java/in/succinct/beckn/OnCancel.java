package in.succinct.beckn;

import org.json.simple.JSONObject;

public class OnCancel extends Request{
    public OnCancel() {
        super();
    }

    public OnCancel(String payLoad) {
        super(payLoad);
    }

    public OnCancel(JSONObject obj){
        super(obj);
    }




}
