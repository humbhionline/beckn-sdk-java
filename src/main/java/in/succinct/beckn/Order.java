package in.succinct.beckn;

import java.util.Date;

public class Order extends BecknObjectWithId {
    public Order() {
        super();
    }

    public Provider getProvider(){
        return get(Provider.class,"provider");
    }
    public void setProvider(Provider provider){
        set("provider",provider.getInner());
    }

    public Location getProviderLocation(){
        return get(Location.class,"provider_location");
    }

    public void setProviderLocation(Location location){
        set("provider_location",location.getInner());
    }

    public Items getItems(){
        return get(Items.class,"items");
    }

    public void setItems(Items  items){
        set("items",items.getInner());
    }

    public Billing getBilling(){
        return get(Billing.class,"billing");
    }
    public void setBilling( Billing billing){
        set("billing",billing.getInner());
    }

    public Fulfillment getFulfillment(){
        return get(Fulfillment.class,"fulfillment");
    }
    public void setFulfillment(Fulfillment fulfillment){
        set("fulfillment",fulfillment.getInner());
    }
    public Quote getQuote(){
        return get(Quote.class,"quote");
    }
    public void setQuote(Quote quote){
        set("quote",quote.getInner());
    }

    public Payment getPayment(){
        return get(Payment.class,"payment");
    }
    public void setPayment(Payment payment){
        set("payment",payment.getInner());
    }

    public Date getCreatedAt(){
        return getDate("created_at",TIMESTAMP_FORMAT);
    }
    public Date getUpdatedAt(){
        return getDate("updated_at",TIMESTAMP_FORMAT);
    }
    public void setCreatedAt(Date date){
        set("created_at",date,TIMESTAMP_FORMAT);
    }
    public void setUpdatedAt(Date date){
        set("updated_at",date,TIMESTAMP_FORMAT);
    }
    public void setState(String state){
        set("state",state);
    }
    public String getState(){
        return get("state");
    }


}
