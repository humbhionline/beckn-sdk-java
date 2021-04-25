package in.succinct.beckn;

public class Country extends BecknObject {
    public Country() {
        super();
    }
    public String getName(){
        return get("name");
    }
    public String getCode(){
        return get("code");
    }

}
