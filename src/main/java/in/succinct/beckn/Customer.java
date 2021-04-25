package in.succinct.beckn;

public class Customer extends Person{
    public Customer() {
        super();
    }

    public Contact getContact(){
        return get(Contact.class,"contact");
    }

    public void setContact(Contact contact){
        set("contact",contact);
    }
}
