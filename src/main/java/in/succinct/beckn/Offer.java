package in.succinct.beckn;

public class Offer extends BecknObjectWithId{
    public Descriptor getDescriptor(){
        return get(Descriptor.class, "descriptor");
    }
    public void setDescriptor(Descriptor descriptor){
        set("descriptor",descriptor);
    }

    public Ids getLocationIds(){
        return get(Ids.class, "location_ids");
    }
    public void setLocationIds(Ids location_ids){
        set("location_ids",location_ids);
    }
    
    public Ids getCategoryIds(){
        return get(Ids.class, "category_ids");
    }
    public void setCategoryIds(Ids category_ids){
        set("category_ids",category_ids);
    }

    public Ids getItemIds(){
        return get(Ids.class, "item_ids");
    }
    public void setItemIds(Ids item_ids){
        set("item_ids",item_ids);
    }

    public Time getTime(){
        return get(Time.class, "time");
    }
    public void setTime(Time time){
        set("time",time);
    }

}
