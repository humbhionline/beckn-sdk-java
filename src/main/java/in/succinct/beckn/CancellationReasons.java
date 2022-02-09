package in.succinct.beckn;

import org.json.simple.JSONArray;

public class CancellationReasons extends BecknObjectsWithId<Option>{

    public CancellationReasons() {
        super();
    }

    public CancellationReasons(JSONArray value) {
        super(value);
    }

    public CancellationReasons(String payload) {
        super(payload);
    }
}
