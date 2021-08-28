package in.succinct.beckn;

import org.json.simple.JSONObject;

public class OnSelect extends Request{
    public OnSelect() {
        this(new JSONObject());
    }

    public OnSelect(String payLoad) {
        super(payLoad);
    }
    public OnSelect(JSONObject object){
        super(object);
    }




}
