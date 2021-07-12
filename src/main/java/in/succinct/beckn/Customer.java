package in.succinct.beckn;

public class Customer extends BecknObject{

    public Customer() {
        super();
    }

    public Person getPerson(){
        return get(Person.class, "person");
    }
    public void setPerson(Person person){
        set("person",person);
    }
    public Contact getContact(){
        return get(Contact.class, "contact");
    }
    public void setContact(Contact contact){
        set("contact",contact);
    }

}
