package in.succinct.beckn;

import org.json.simple.JSONArray;

public class List extends BecknObjects<Tag>{

    public List() {
    }

    public List(JSONArray value) {
        super(value);
    }
}
