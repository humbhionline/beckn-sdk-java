package in.succinct.beckn;

public class Item extends BecknObjectWithId {
    public Item() {
        super();
    }

    public String getCategoryId(){
        return get("category_id");
    }
    public void setCategoryId(String criteriaId){
        set("category_id",criteriaId);
    }

    public Descriptor getDescriptor(){
        return get(Descriptor.class,"descriptor");
    }
    public void setDescriptor(Descriptor descriptor){
        set("descriptor",descriptor.getInner());
    }
    public Price getPrice(){
        return get(Price.class,"price");
    }
    public void setPrice(Price price){
        set("price",price.getInner());
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



}
