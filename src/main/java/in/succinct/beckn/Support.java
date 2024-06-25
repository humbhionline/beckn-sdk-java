package in.succinct.beckn;

import org.json.simple.JSONObject;

public class Support extends BecknObject{
    public Support() {
    }

    public Support(String payload) {
        super(payload);
    }

    public Support(JSONObject object) {
        super(object);
    }
    
    public String getOrderId(){
        return get("order_id");
    }
    public void setOrderId(String order_id){
        set("order_id",order_id);
    }
    
    public String getRefId(){
        return get("ref_id");
    }
    public void setRefId(String ref_id){
        set("ref_id",ref_id);
    }

    public String getCallbackPhone(){
        return get("callback_phone");
    }
    public void setCallbackPhone(String callback_phone){
        set("callback_phone",callback_phone);
    }

    public String getPhone(){
        return get("phone");
    }
    public void setPhone(String phone){
        set("phone",phone);
    }

    public String getEmail(){
        return get("email");
    }
    public void setEmail(String email){
        set("email",email);
    }
    public String getUrl(){
        return get("url");
    }
    public void setUrl(String url){
        set("url",url);
    }

    public Documents getDocuments(){
        return get(Documents.class, "documents");
    }
    public void setDocuments(Documents documents){
        set("documents",documents);
    }
}
