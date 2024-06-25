package in.succinct.beckn;

import org.json.simple.JSONObject;

public class Fee extends BecknObject {
    public Fee() {
    }

    public Fee(String payload) {
        super(payload);
    }

    public Fee(JSONObject object) {
        super(object);
    }
    
    public String getPercentage(){
        return get("percentage");
    }
    public void setPercentage(String percentage){
        if (!percentage.matches("[+-]?([0-9]*[.])?[0-9]+")){
            throw  new RuntimeException("Invalid Format");
        }
        set("percentage",percentage);
    }

    public Price getAmount(){
        return get(Price.class, "amount");
    }
    public void setAmount(Price amount){
        set("amount",amount);
    }

}
