package in.succinct.beckn;

import org.json.simple.JSONObject;

public class OnSearch extends Request{
    public OnSearch() {
        this(new JSONObject());
    }

    public OnSearch(String payLoad) {
        super(payLoad);
    }

    public OnSearch(JSONObject object){
        super(object);
    }




}
