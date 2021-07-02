package in.succinct.beckn;

public class Customer extends Person{
    public Customer() {
        super();
    }

    public String getEmail(){
        return get("email");
    }
    public void setEmail(String email){
        set("email",email);
    }
    public String getPhone(){
        return get("phone");
    }
    public void setPhone(String phone){
        set("phone",phone);
    }
    public Tags getTags(){
        return get(Tags.class, "tags");
    }
    public void setTags(Tags tags){
        set("tags",tags);
    }
}
