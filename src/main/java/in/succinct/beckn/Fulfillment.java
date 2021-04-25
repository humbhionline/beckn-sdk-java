package in.succinct.beckn;

public class Fulfillment extends BecknObject {

    public Fulfillment(){
        super();
    }


    public FulfillmentType getType(){
        return FulfillmentType.valueOf(get("type"));
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

}
