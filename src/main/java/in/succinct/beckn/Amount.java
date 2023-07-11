package in.succinct.beckn;

import org.json.simple.JSONObject;

public class Amount extends BecknObject{
    public Amount() {
    }

    public Amount(String payload) {
        super(payload);
    }

    public Amount(JSONObject object) {
        super(object);
    }
    public String getCurrency(){
        return get("currency");
    }
    public void setCurrency(String currency){
        set("currency",currency);
    }
    public double getValue(){
        return getDouble("value");
    }
    public void setValue(double value){
        set("value",String.valueOf(value));
    }



}
