package in.succinct.beckn;

import org.json.simple.JSONObject;

public class Rating extends BecknObjectWithId{
    public Rating(){
        super();
    }
    public Rating(String payload){
        super(payload);
    }
    public Rating(JSONObject object){
        super(object);
    }

    public String getRatingCategory(){
        return get("rating_category");
    }
    public void setRatingCategory(String rating_category){
        set("rating_category",rating_category);
    }
    public int getValue(){
        return getInteger("value");
    }
    public void setValue(int value){
        set("value",value);
    }
}
