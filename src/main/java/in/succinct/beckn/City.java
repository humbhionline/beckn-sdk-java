package in.succinct.beckn;

public class City extends BecknObject {
    public City() {
        super();
    }
    public String getName(){
        return get("name");
    }
    public String getCode(){
        return get("code");
    }

}
