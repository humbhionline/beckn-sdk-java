package in.succinct.beckn;

import org.json.simple.JSONArray;

public class RatingCategories extends BecknObjects<String>{

    public RatingCategories() {
        super();
    }

    public RatingCategories(JSONArray value) {
        super(value);
    }

    public RatingCategories(String payload) {
        super(payload);
    }
}
