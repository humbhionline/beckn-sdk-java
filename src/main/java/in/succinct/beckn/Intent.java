package in.succinct.beckn;

public class Intent extends BecknObject {
    public Intent(){
        super();
    }

    public Fulfillment getFulfillment(){
        return get(Fulfillment.class,"fulfillment");
    }


    public Provider getProvider(){
        return get(Provider.class, "provider");
    }

    public Item getItem(){
        return get(Item.class,"item");
    }

}
