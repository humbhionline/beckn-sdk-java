package in.succinct.beckn;

import org.json.simple.JSONObject;

import java.time.Duration;

public class Quote extends BecknObject {
    public Quote() {
        super();
    }

    public void setPrice(Price price) {
        set("price",price);
    }
    public  Price getPrice(){
        return get(Price.class,"price");
    }

    public long getTtl(){
        String ttl = get("ttl");
        return ttl == null ? 10 : Duration.parse(ttl).getSeconds();
    }
    public void setTtl(long seconds){
        set("ttl",Duration.ofSeconds(seconds).toString());
    }

    public void setBreakUp(BreakUp breakUp) {
        set("breakup",breakUp);
    }
    public BreakUp getBreakUp(){
        return get(BreakUp.class,"breakup");
    }
}
