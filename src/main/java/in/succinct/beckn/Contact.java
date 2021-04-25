package in.succinct.beckn;

import java.util.HashMap;
import java.util.Map;

public class Contact extends BecknObjects<Tag> {
    public Contact() {
        super();
    }

    private Map<String,Tag> tags = new HashMap<>();
    public void setEmail(Email email){
        if (tags.containsKey(Email.TAG_NAME)){
            remove(email);
        }
        add(email);
        tags.put(Email.TAG_NAME,email);
    }
    public void setPhone(Phone phone){
        if (tags.containsKey(Phone.TAG_NAME)){
            remove(phone);
        }
        add(phone);
        tags.put(Phone.TAG_NAME,phone);
    }

    public void setUrl(Url url){
        if (tags.containsKey(Url.TAG_NAME)){
            remove(url);
        }
        add(url);
        tags.put(Url.TAG_NAME,url);
    }

    public Email getEmail(){
        return (Email)tags.get(Email.TAG_NAME);
    }
    public Phone getPhone(){
        return (Phone)tags.get(Phone.TAG_NAME);
    }
    public Url getUrl(){
        return (Url)tags.get(Url.TAG_NAME);
    }

}
