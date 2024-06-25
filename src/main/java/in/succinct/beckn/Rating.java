package in.succinct.beckn;

import org.json.simple.JSONArray;
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

    public RatingCategory getRatingCategory(){
        return getEnum(RatingCategory.class, "rating_category", new EnumConvertor<>(RatingCategory.class));
    }
    public void setRatingCategory(RatingCategory rating_category){
        setEnum("rating_category",rating_category,new EnumConvertor<>(RatingCategory.class));
    }



    public int getValue(){
        return getInteger("value");
    }
    public void setValue(int value){
        set("value",value);
    }




    public enum RatingCategory {
        Item,
        Order,
        Provider,
        Fulfillment,
        Agent,
        Support

    }

    public static class RatingAck extends Message {
        public RatingAck() {
        }

        public RatingAck(String payload) {
            super(payload);
        }

        public boolean isFeedbackAck(){
            return getBoolean("feedback_ack");
        }
        public void setFeedbackAck(boolean feedback_ack){
            set("feedback_ack",feedback_ack);
        }
        
        public boolean isRatingAck(){
            return getBoolean("rating_ack");
        }
        public void setRatingAck(boolean rating_ack){
            set("rating_ack",rating_ack);
        }
    }

    public static class Ratings extends BecknObjectsWithId<Rating> {
        public Ratings() {
        }

        public Ratings(JSONArray array) {
            super(array);
        }

        public Ratings(String payload) {
            super(payload);
        }
    }
}
