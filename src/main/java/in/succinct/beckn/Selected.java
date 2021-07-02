package in.succinct.beckn;

public class Selected extends BecknObject{
    public Provider getProvider(){
        return get(Provider.class, "provider");
    }
    public void setProvider(Provider provider){
        set("provider",provider);
    }

    public Location getLocation(){
        return get(Location.class, "provider_location");
    }
    public void setLocation(Location provider_location){
        set("provider_location",provider_location);
    }

    public Items getItems(){
        return get(Items.class, "items");
    }
    public void setItems(Items items){
        set("items",items);
    }

    public AddOns getAddOns(){
        return get(AddOns.class, "add_ons");
    }
    public void setAddOns(AddOns add_ons){
        set("add_ons",add_ons);
    }

    public Offers getOffers(){
        return get(Offers.class, "offers");
    }
    public void setOffers(Offers offers){
        set("offers",offers);
    }

    public Quote getQuote(){
        return get(Quote.class, "quote");
    }
    public void setQuote(Quote quote){
        set("quote",quote);
    }
    
    public Payment getPayment(){
        return get(Payment.class, "payment");
    }
    public void setPayment(Payment payment){
        set("payment",payment);
    }
}
