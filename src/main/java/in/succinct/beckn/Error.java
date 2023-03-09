package in.succinct.beckn;

import in.succinct.beckn.Error.Type.TypeConverter;

public class Error extends BecknObject {
    public Error() {
        super();
    }

    public Type getType(){
        return getEnum(Type.class,"type",new TypeConverter());
    }
    public void setType(Type type){
        setEnum("type",type,new TypeConverter());
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
        JSON_SCHEMA_ERROR;
        public static class TypeConverter extends EnumConvertor<Type>{}
    }
}
