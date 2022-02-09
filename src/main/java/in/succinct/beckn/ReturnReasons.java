package in.succinct.beckn;

import org.json.simple.JSONArray;

public class ReturnReasons extends BecknObjectsWithId<Option>{

    public ReturnReasons() {
        super();
    }

    public ReturnReasons(JSONArray value) {
        super(value);
    }

    public ReturnReasons(String payload) {
        super(payload);
    }
}
