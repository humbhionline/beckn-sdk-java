package in.succinct.beckn;

import java.util.HashSet;
import java.util.Set;

public class Payment extends BecknObjectWithId {
    public Payment(){
        super();
    }
    public Payment(String payload){
        super(payload);
    }
    public String getUri(){
        return get("uri");
    }
    public void setUri(String uri){
        set("uri",uri);
    }
    public String getTlMethod(){
        return get("tl_method");
    }
    static final Set<String> TL_METHODS = new HashSet<>(){{
       add("http/get");
       add("http/post");
    }};
    public void setTlMethod(String tl_method){
        if  (TL_METHODS.contains(tl_method)) {
            set("tl_method", tl_method);
        }else {
            throw new IllegalArgumentException();
        }
    }
    public Params getParams(){
        return get(Params.class,"params");
    }
    public void setParams(Params params){
        set("params",params);
    }


    public String getType(){
        return get("type");
    }
    static final Set<String> TYPES = new HashSet<>(){{
        add("ON-ORDER");
        add("PRE-FULFILLMENT");
        add("ON-FULFILLMENT");
        add("POST-FULFILLMENT");
    }};
    public void setType(String type){
        if (!TYPES.contains(type)){
            throw new IllegalArgumentException();
        }
        set("type",type);
    }

    public String getStatus(){
        return get("status");
    }

    static final Set<String> STATUSES = new HashSet<>(){{
       add("PAID");
       add("NOT-PAID");
    }};
    public void setStatus(String status){
        if (!STATUSES.contains(status)){
            throw new IllegalArgumentException();
        }
        set("status",status);
    }

    public Time getTime(){
        return get(Time.class,"time");
    }
    public void setTime(Time time){
        set("time",time);
    }

    public static class Params extends BecknObject{
        public String getTransactionId(){
            return get("transaction_id");
        }
        public void setTransactionId(String transaction_id){
            set("transaction_id",transaction_id);
        }

        public double getAmount(){
            return getDouble("amount");
        }
        public void setAmount(double amount){
            set("amount",String.valueOf(amount));
        }

        public String getCurrency(){
            return get("currency");
        }
        public void setCurrency(String currency){
            set("currency",currency);
        }


    }

}
