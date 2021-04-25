package in.succinct.beckn;

public class Provider extends BecknObjectWithId {
    public Provider(){
        super();
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


}
