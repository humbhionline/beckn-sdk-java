package in.succinct.beckn;

import org.json.simple.JSONObject;

public class Subscriber extends BecknObject{
    public static final String SUBSCRIBER_TYPE_BAP = "BAP";
    public static final String SUBSCRIBER_TYPE_BPP = "BPP";
    public static final String SUBSCRIBER_TYPE_LOCAL_REGISTRY = "LREG";
    public static final String SUBSCRIBER_TYPE_COUNTRY_REGISTRY = "CREG";
    public static final String SUBSCRIBER_TYPE_ROOT_REGISTRY = "RREG";
    public static final String SUBSCRIBER_TYPE_BG = "BG";

    public Subscriber() {
    }

    public Subscriber(String payload) {
        super(payload);
    }

    public Subscriber(JSONObject object) {
        super(object);
    }


}
