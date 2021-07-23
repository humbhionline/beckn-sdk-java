package in.succinct.beckn;

public class City extends BecknObject {
    public City() {
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
