package in.succinct.beckn;

import org.json.simple.JSONArray;

import java.time.Duration;
import java.util.Date;

public class Provider extends BecknObjectWithId implements TagGroupHolder {
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
        set("descriptor",descriptor);
    }

    public Locations getLocations(){
        return get(Locations.class,"locations");
    }
    public void setLocations(Locations locations){
        set("locations",locations);
    }
    public  Items getItems(){
        return get(Items.class,"items");
    }
    public void setItems(Items items){
        set("items",items);
    }

    public Categories getCategories(){
        return get(Categories.class,"categories");
    }
    public void setCategories(Categories categories){
        set("categories",categories);
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
        return extendedAttributes.get("bpp_id");
    }
    public void setBppId(String bpp_id){
        extendedAttributes.set("bpp_id",bpp_id);
    }
    public Long getTtl(){
        String ttl = get("ttl");
        return ttl == null ? null : Duration.parse(ttl).getSeconds();
    }
    public void setTtl(Long ttl){
        set("ttl", ttl == null ? null : Duration.ofSeconds(ttl).toString());
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

    public Integer getRating(){
        return getInteger("rating", null);
    }
    public void setRating(Integer rating){
        set("rating",rating);
    }

    public Time getTime(){
        return get(Time.class,"time");
    }
    public void setTime(Time time){
        set("time",time);
    }

    public Boolean isRateable(){
        return getBoolean("rateable",null);
    }
    public void setRateable(Boolean rateable){
        set("rateable",rateable);
    }

    public boolean isExtendedAttributesDisplayed(){
        return true;
    }
    public String getFssaiLicenceNo(){
        return extendedAttributes.get("fssai_licence_no");
    }
    public void setFssaiLicenceNo(String fssai_licence_no){
        extendedAttributes.set("fssai_licence_no",fssai_licence_no);
    }

    @Override
    public TagGroups getTags() {
        return TagGroupHolder.super.getTags();
    }

    @Override
    public void setTags(TagGroups tags) {
        TagGroupHolder.super.setTags(tags);
    }

    public ServiceablityTags getServiceablityTags(){
        return extendedAttributes.get(ServiceablityTags.class, "tags");
    }
    public void setServiceablityTags(ServiceablityTags tags){
        extendedAttributes.set("tags",tags);
    }

    public static class ServiceablityTags extends TagGroups {

        public ServiceablityTags() {
        }

        public ServiceablityTags(JSONArray value) {
            super(value);
        }

    }

}
