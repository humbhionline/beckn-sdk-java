package in.succinct.beckn;

public class AddOn extends BecknObjectWithId{
    public Descriptor getDescriptor(){
        return get(Descriptor.class, "descriptor");
    }
    public void setDescriptor(Descriptor descriptor){
        set("descriptor",descriptor);
    }
    public Price getPrice(){
        return get(Price.class, "price");
    }
    public void setPrice(Price price){
        set("price",price);
    }
    public ItemQuantity getItemQuantity(){
        return get(ItemQuantity.class, "quantity");
    }
    public void setItemQuantity(ItemQuantity quantity){
        set("quantity",quantity);
    }


}
