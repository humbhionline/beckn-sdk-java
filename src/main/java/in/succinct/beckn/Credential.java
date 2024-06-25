package in.succinct.beckn;

import org.json.simple.JSONObject;

public class Credential extends BecknObjectWithId{
    public Credential() {
    }

    public Credential(String payload) {
        super(payload);
    }

    public Credential(JSONObject object) {
        super(object);
    }

    public String getUrl(){
        return get("url");
    }
    public void setUrl(String url){
        set("url",url);
    }

    public String getType(){
        return get("type","VerifiableCredential");
    }
    public void setType(String type){
        set("type",type);
    }


    public static class Credentials extends BecknObjects<Credential> {
        public Credentials() {
        }
    }
}
