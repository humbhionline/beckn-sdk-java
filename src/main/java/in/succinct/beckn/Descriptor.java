package in.succinct.beckn;

public class Descriptor extends BecknObject {
    public Descriptor(){
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
