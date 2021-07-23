package in.succinct.beckn;

public class User extends BecknObject{

    public User() {
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

    /** Not in Spec **/

    public Location getLocation(){
        return get(Location.class, "location");
    }
    public void setLocation(Location location){
        set("location",location);
    }
}
