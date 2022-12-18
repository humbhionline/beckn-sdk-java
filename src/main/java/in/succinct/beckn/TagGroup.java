package in.succinct.beckn;

public class TagGroup extends BecknObject{
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

    public Tags getList(){
        return get(Tags.class, "list");
    }
    public void setList(Tags tags){
        set("list",tags);
    }
}
