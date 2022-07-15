package in.succinct.beckn;

import org.json.simple.JSONObject;

public class OnRating extends Request{
    public OnRating() {
        super();
    }

    public OnRating(String payLoad) {
        super(payLoad);
    }

    public OnRating(JSONObject obj){
        super(obj);
    }



}
