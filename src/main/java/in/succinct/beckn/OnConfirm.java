package in.succinct.beckn;

import org.json.simple.JSONObject;

public class OnConfirm extends Request{
    public OnConfirm() {
        super();
    }

    public OnConfirm(String payLoad) {
        super(payLoad);
    }

    public OnConfirm(JSONObject obj){
        super(obj);
    }



}
