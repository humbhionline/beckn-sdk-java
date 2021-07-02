package in.succinct.beckn;

public class Fulfillment extends BecknObjectWithId {

    public Fulfillment(){
        super();
    }


    public FulfillmentType getType(){
        return FulfillmentType.valueOf(get("type"));
    }

    public void setType(FulfillmentType type){
        set("type",type.toString());
    }
    public enum FulfillmentType {
        home_delivery,
        store_pickup,
        return_to_origin,
    }

    public FulfillmentStop getStart(){
        return get(FulfillmentStop.class,"start");
    }
    public void setStart(FulfillmentStop start){
        set("start",start);
    }

    public FulfillmentStop getEnd(){
        return get(FulfillmentStop.class,"end");
    }
    public void setEnd(FulfillmentStop end){
        set("end",end);
    }

    public boolean getTracking(){
        return getBoolean("tracking");
    }
    public void setTracking(boolean tracking){
        set("tracking",tracking);
    }

    public String getAgent(){
        return get("agent");
    }
    public void setAgent(String agent){
        set("agent",agent);
    }

    public State getState(){
        return get(State.class, "state");
    }
    public void setState(State state){
        set("state",state);
    }
    public void setState(String state){
        getState().getDescriptor().setCode(state);
    }

    public Customer getCustomer(){
        return get(Customer.class, "customer");
    }
    public void setCustomer(Customer customer){
        set("customer",customer);
    }

}
