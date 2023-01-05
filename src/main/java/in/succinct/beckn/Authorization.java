package in.succinct.beckn;

import org.json.simple.JSONObject;

import java.util.Date;

public class Authorization extends BecknObject{

    public Authorization() {
    }

    public Authorization(String payload) {
        super(payload);
    }

    public Authorization(JSONObject object) {
        super(object);
    }

    public String getType(){
        return get("type");
    }
    public void setType(String type){
        set("type",type);
    }

    public String getToken(){
        return get("token");
    }
    public void setToken(String token){
        set("token",token);
    }
    public Date getValidFrom(){
        return getTimestamp("valid_from");
    }
    public void setValidFrom(Date valid_from){
        set("valid_from",valid_from,TIMESTAMP_FORMAT);
    }
    public Date getValidTo(){
        return getTimestamp("valid_to");
    }
    public void setValidTo(Date valid_from){
        set("valid_to",valid_from,TIMESTAMP_FORMAT);
    }
    public String getStatus(){
        return get("status");
    }
    public void setStatus(String status){
        set("status",status);
    }
}
