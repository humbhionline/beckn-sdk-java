package in.succinct.beckn;

import org.json.simple.JSONObject;

import java.util.regex.Pattern;


public class Odr extends BecknObject{
    public Odr() {
    }

    public Odr(String payload) {
        super(payload);
    }

    public Odr(JSONObject object) {
        super(object);
    }

    public String getName(){
        return get("name");
    }
    public void setName(String name){
        set("name",name);
    }
    public String getAboutInfo(){
        return get("about_info");
    }
    public void setAboutInfo(String about_info){
        set("about_info",about_info);
    }
    public String getUrl(){
        return get("url");
    }
    public void setUrl(String url){
        set("url",url);
    }

    public Representative getRepresentative(){
        return get(Representative.class, "representative");
    }
    public void setRepresentative(Representative representative){
        set("representative",representative);
    }

    public PricingModel getPricingModel(){
        return get(PricingModel.class, "pricing_model");
    }
    public void setPricingModel(PricingModel pricing_model){
        set("pricing_model",pricing_model);
    }


    public static class PricingModel extends BecknObject {
        public Price getPrice(){
            return get(Price.class, "price");
        }
        public void setPrice(Price price){
            set("price",price);
        }

        public String getPricingInfo(){
            return get("pricing_info");
        }
        public void setPricingInfo(String pricing_info){
            set("pricing_info",pricing_info);
        }

    }
    public Rating getRating(){
        return get(Rating.class, "resolution_rating");
    }
    public void setRating(Rating resolutions_rating){
        set("resolution_rating",resolutions_rating);
    }



    public static class Odrs extends BecknObjects<Odr> {

    }

    public static class Rating extends BecknObject{
        public String getRatingValue(){
            return get("rating_value");
        }
        public void setRatingValue(String rating_value){
            Pattern pattern =Pattern.compile("[0-9]*.?[0-9]*%");
            if (!pattern.matcher(rating_value).matches()){
                throw new RuntimeException("Invalid format");
            }

            set("rating_value",rating_value);
        }

    }
}
