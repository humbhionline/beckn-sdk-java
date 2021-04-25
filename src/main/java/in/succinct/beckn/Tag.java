package in.succinct.beckn;

public class Tag extends BecknObject {
    public Tag() {
        super();
    }
    public String getKey(){
        return get("key");
    }
    public void setKey(String key){
        set("key",key);
    }
    public String getValue(){
        return get("value");
    }
    public void setValue(String value){
        set("value",value);
    }
}
