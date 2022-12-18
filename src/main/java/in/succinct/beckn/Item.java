package in.succinct.beckn;

import org.json.simple.JSONObject;

public class Item extends BecknObjectWithId {
    public Item() {
        super();
    }
    public Item(String payload){
        super(payload);
    }
    public Item(JSONObject item){
        super(item);
    }

    public String getCategoryId(){
        return get("category_id");
    }
    public void setCategoryId(String criteriaId){
        set("category_id",criteriaId);
    }

    public BecknStrings getCategoryIds(){
        return get(BecknStrings.class, "category_ids");
    }
    public void setCategoryIds(BecknStrings category_ids){
        set("category_ids",category_ids);
    }

    public BecknStrings getLocationIds(){
        return get(BecknStrings.class, "location_ids");
    }
    public void setLocationIds(BecknStrings location_ids){
        set("location_ids",location_ids);
    }

    public BecknStrings getPaymentIds(){
        return get(BecknStrings.class, "payment_ids");
    }
    public void setPaymentIds(BecknStrings payment_ids){
        set("payment_ids",payment_ids);
    }
    public BecknStrings getFulfillmentIds(){
        return get(BecknStrings.class, "fulfillment_ids");
    }
    public void setFulfillmentIds(BecknStrings fulfillment_ids){
        set("fulfillment_ids",fulfillment_ids);
    }

    public Descriptor getDescriptor(){
        return get(Descriptor.class,"descriptor");
    }
    public void setDescriptor(Descriptor descriptor){
        set("descriptor",descriptor);
    }
    public Price getPrice(){
        return get(Price.class,"price");
    }
    public void setPrice(Price price){
        set("price",price);
    }

    public Quantity getQuantity(){
        return get(Quantity.class,"quantity");
    }
    public  void setQuantity(Quantity quantity){
        set("quantity",quantity);
    }

    public String getLocationId(){
        return get("location_id");
    }

    public void setLocationId(String id){
        set("location_id",id);
    }

    public boolean getRecommended(){
        return getBoolean("recommended");
    }
    public void setRecommended(boolean recommended){
        set("recommended",recommended);
    }

    public String getFulfillmentId(){
        return get("fulfillment_id");
    }
    public void setFulfillmentId(String fulfillment_id){
        set("fulfillment_id",fulfillment_id);
    }

    public Tags getTags(){
        return get(Tags.class, "tags");
    }
    public void setTags(Tags tags){
        set("tags",tags);
    }

}
