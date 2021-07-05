package in.succinct.beckn;

public class Organization extends BecknObject {
    public String getName(){
        return get("name");
    }
    public String getEmail(){
        return get("email");
    }
    public BecknStrings getPhones(){
        return get(BecknStrings.class,"phones");
    }
}
