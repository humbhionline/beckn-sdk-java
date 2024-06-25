package in.succinct.beckn;

import org.json.simple.JSONObject;

public class State extends BecknObject{
    public State() {
    }

    public State(String payload) {
        super(payload);
    }

    public State(JSONObject object) {
        super(object);
    }

    public String getCode(){
        return get("code");
    }
    public void setCode(String code){
        set("code",code);
    }

    public String getName(){
        return get("name");
    }
    public void setName(String name){
        set("name",name);
    }
}
