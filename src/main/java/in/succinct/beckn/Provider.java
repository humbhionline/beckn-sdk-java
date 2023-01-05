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
}
