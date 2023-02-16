package in.succinct.beckn;

public class Contact extends BecknObject {
    public Contact() {
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

    public String flatten() {
        StringBuilder builder = new StringBuilder();
        builder.append(_flat(getEmail()));
        builder.append(_flat(getPhone()));
        return builder.toString();
    }
}
