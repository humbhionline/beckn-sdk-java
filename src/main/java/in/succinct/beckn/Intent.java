package in.succinct.beckn;

public class Intent extends BecknObject {
    public Intent(){
        super();
    }
    public Intent(String payload){
        super(payload);
    }

    public Fulfillment getFulfillment(){
        return get(Fulfillment.class, "fulfillment");
    }
    public void setFulfillment(Fulfillment fulfillment){
        set("fulfillment",fulfillment);
    }

    public Provider getProvider(){
        return get(Provider.class, "provider");
    }
    public void setProvider(Provider provider){
        set("provider",provider);
    }

    public Item getItem(){
        return get(Item.class, "item");
    }
    public void setItem(Item item){
        set("item",item);
    }

}
