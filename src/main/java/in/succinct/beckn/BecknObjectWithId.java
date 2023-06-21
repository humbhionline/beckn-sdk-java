package in.succinct.beckn;

import in.succinct.json.ExtendedJSONObjectWithId;
import org.json.simple.JSONObject;

public class BecknObjectWithId extends ExtendedJSONObjectWithId {
    public BecknObjectWithId(){
        super();
    }
    public BecknObjectWithId(String payload){
        super(payload);
    }
    public BecknObjectWithId(JSONObject object){
        super(object);
    }

}
