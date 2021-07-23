package in.succinct.beckn;

public class Country extends BecknObject {
    public Country() {
        super();
    }

    public String getName(){
        return get("name");
    }
    public void setName(String name){
        set("name",name);
    }
    public String getCode(){
        return get("code");
    }
    public void setCode(String code){
        set("code",code);
    }
}
