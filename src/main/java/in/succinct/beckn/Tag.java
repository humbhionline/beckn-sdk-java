package in.succinct.beckn;

public class Tag extends BecknObject{
    public boolean getDisplay(){
        return getBoolean("display");
    }
    public void setDisplay(boolean display){
        set("display",display);
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

    public String getValue(){
        return get("value");
    }
    public void setValue(String value){
        set("value",value);
    }
}
