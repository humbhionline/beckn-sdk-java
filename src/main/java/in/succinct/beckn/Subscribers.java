package in.succinct.beckn;

import org.json.simple.JSONArray;

public class Subscribers extends BecknObjects<Subscriber> {
    public Subscribers() {
    }

    public Subscribers(JSONArray value) {
        super(value);
    }

    public Subscribers(String payload) {
        super(payload);
    }
}