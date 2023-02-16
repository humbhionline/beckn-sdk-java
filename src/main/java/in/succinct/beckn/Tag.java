package in.succinct.beckn;


import org.json.simple.JSONArray;

public class Tag extends BecknObject{
    public Tag(){
        super();
    }
    public Tag(String code, Object value){
        this(code,null,value);
    }
    public Tag(String code, String name,Object value){
        setCode(code);
        if (name != null) setName(name);
        if (value instanceof String) {
            setValue((String)value);
        }else if (value instanceof List){
            setList((List)value);
        }

    }
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

    public List getList(){
        return get(List.class, "list");
    }
    public void setList(List list){
        set("list",list);
    }


    public static class List extends BecknObjects<Tag>{

        public List() {
        }

        public List(JSONArray value) {
            super(value);
        }
    }
}
