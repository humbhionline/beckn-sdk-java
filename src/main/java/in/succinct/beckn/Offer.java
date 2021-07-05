package in.succinct.beckn;

public class Offer extends BecknObjectWithId{
    public Descriptor getDescriptor(){
        return get(Descriptor.class, "descriptor");
    }
    public void setDescriptor(Descriptor descriptor){
        set("descriptor",descriptor);
    }

    public BecknStrings getLocationIds(){
        return get(BecknStrings.class, "location_ids");
    }
    public void setLocationIds(BecknStrings location_ids){
        set("location_ids",location_ids);
    }
    
    public BecknStrings getCategoryIds(){
        return get(BecknStrings.class, "category_ids");
    }
    public void setCategoryIds(BecknStrings category_ids){
        set("category_ids",category_ids);
    }

    public BecknStrings getItemIds(){
        return get(BecknStrings.class, "item_ids");
    }
    public void setItemIds(BecknStrings item_ids){
        set("item_ids",item_ids);
    }

    public Time getTime(){
        return get(Time.class, "time");
    }
    public void setTime(Time time){
        set("time",time);
    }

}
