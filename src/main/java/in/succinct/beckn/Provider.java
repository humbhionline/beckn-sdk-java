package in.succinct.beckn;

import java.time.Duration;
import java.util.Date;

public class Provider extends BecknObjectWithId {
    public Provider(){
        super();
    }
    public Provider(String payload){
        super(payload);
    }
    public Descriptor getDescriptor(){
        return get(Descriptor.class,"descriptor");
    }
    public void setDescriptor(Descriptor descriptor){
        set("descriptor",descriptor.getInner());
    }

    public Locations getLocations(){
        return get(Locations.class,"locations");
    }
    public void setLocations(Locations locations){
        set("locations",locations.getInner());
    }
    public  Items getItems(){
        return get(Items.class,"items");
    }
    public void setItems(Items items){
        set("items",items.getInner());
    }

    public Categories getCategories(){
        return get(Categories.class,"categories");
    }
    public void setCategories(Categories categories){
        set("categories",categories.getInner());
    }

    public Fulfillments getFulfillments(){
        return get(Fulfillments.class, "fulfillments");
    }
    public void setFulfillments(Fulfillments fulfillments){
        set("fulfillments",fulfillments);
    }

    public Payments getPayments(){
        return get(Payments.class, "payments");
    }
    public void setPayments(Payments payments){
        set("payments",payments);
    }


    public String getBppId(){
        return get("bpp_id");
    }
    public void setBppId(String bpp_id){
        set("bpp_id",bpp_id);
    }

    public Tags getTags(){
        return get(Tags.class, "tags");
    }
    public void setTags(Tags tags){
        set("tags",tags);
    }

    public long getTtl(){
        String ttl = get("ttl");
        return ttl == null ? 10 : Duration.parse(ttl).getSeconds();
    }
    public void setTtl(long seconds){
        set("ttl",Duration.ofSeconds(seconds).toString());
    }

    public Offers getOffers(){
        return get(Offers.class, "offers");
    }
    public void setOffers(Offers offers){
        set("offers",offers);
    }

    public Date getExp(){
        return getTimestamp("exp");
    }
    public void setExp(Date exp){
        set("exp",exp,TIMESTAMP_FORMAT);
    }


    public String getCategoryId(){
        return get("category_id");
    }
    public void setCategoryId(String category_id){
        set("category_id",category_id);
    }

    public Rating getRating(){
        return get(Rating.class,"rating");
    }
    public void setRating(Rating rating){
        set("rating",rating.getInner());
    }

    public Time getTime(){
        return get(Time.class,"time");
    }
    public void setTime(Time time){
        set("time",time.getInner());
    }

    public boolean getRateable(){
        return get("rateable");
    }
    public void setRateable(boolean rateable){
        set("rateable",rateable);
    }

    protected final BecknObject extendedAttributes = new BecknObject();
    public String getFssaiLicenceNo(){
        return extendedAttributes.get("fssai_licence_no");
    }
    public void setFssaiLicenceNo(String fssai_licence_no){
        extendedAttributes.set("fssai_licence_no",fssai_licence_no);
    }
}
