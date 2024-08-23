package in.succinct.beckn;

import org.json.simple.JSONArray;

public class BecknStrings extends BecknObjects<String>{

    public BecknStrings() {
    }

    public BecknStrings(JSONArray value) {
        super(value);
    }

    public BecknStrings(String payload){
        super(payload);
    }

}
