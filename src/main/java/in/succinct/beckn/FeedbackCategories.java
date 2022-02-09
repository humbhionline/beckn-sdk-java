package in.succinct.beckn;

import org.json.simple.JSONArray;

public class FeedbackCategories extends RatingCategories{

    public FeedbackCategories() {
        super();
    }

    public FeedbackCategories(JSONArray value) {
        super(value);
    }

    public FeedbackCategories(String payload) {
        super(payload);
    }
}
