package in.succinct.beckn;

public class Error extends BecknObject {
    public Error() {
        super();
    }

    public Type getType(){
        return Type.valueOf(get("type",Type.DOMAIN_ERROR.toString()));
    }
    public void setType(Type type){
        set("type",type.toString());
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
        CONTEXT_ERROR(){
            @Override
            public String toString() {
                return "CONTEXT-ERROR";
            }

        },
        CORE_ERROR(){
            @Override
            public String toString() {
                return "CORE-ERROR";
            }

        },
        DOMAIN_ERROR(){
            @Override
            public String toString() {
                return "DOMAIN-ERROR";
            }
        },
        POLICY_ERROR(){
            @Override
            public String toString() {
                return "POLICY-ERROR";
            }

        },
        JSON_SCHEMA_ERROR(){
            @Override
            public String toString() {
                return "JSON-SCHEMA-ERROR";
            }

        },
    }
}
