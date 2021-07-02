package in.succinct.beckn;

public class Agent extends Person{
    
    public Agent() {
    }

    public String getPhone(){
        return get("phone");
    }
    public void setPhone(String phone){
        set("phone",phone);
    }
    
    public String getEmail(){
        return get("email");
    }
    public void setEmail(String email){
        set("email",email);
    }

    public Tags getTags(){
        return get(Tags.class, "tags");
    }
    public void setTags(Tags tags){
        set("tags",tags);
    }

}
