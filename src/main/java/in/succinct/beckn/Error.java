package in.succinct.beckn;

public class Error extends BecknObject {
    public Error() {
        super();
    }

    public Type getType(){
        return get("type",Type.DOMAIN_ERROR);
    }
    public String getCode(){
        return get("code");
    }
    public String getMessage(){
        return get("message");
    }
    public void setCode(String code){
        set("code",code);
    }
    public void setMessage(String message){
        set("message",message);
    }


    public enum Type {
        CONTEXT_ERROR,
        CORE_ERROR,
        DOMAIN_ERROR,
        POLICY_ERROR,
        JSON_SCHEMA_ERROR,
    }
}
