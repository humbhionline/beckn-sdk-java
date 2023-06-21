package in.succinct.beckn;

import in.succinct.json.ExtendedJSONObject;
import org.json.simple.JSONObject;

public class BecknObject extends ExtendedJSONObject {

    public BecknObject() {
        super();
    }

    public BecknObject(String payload) {
        super(payload);
    }

    public BecknObject(JSONObject object) {
        super(object);
    }

}
