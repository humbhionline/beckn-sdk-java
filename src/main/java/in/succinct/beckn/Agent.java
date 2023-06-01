package in.succinct.beckn;

public class Agent extends BecknObject{
    
    public Agent() {
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

    public Organization getOrganization(){
        return get(Organization.class, "organization");
    }
    public void setOrganization(Organization organization){
        set("organization",organization);
    }

    public Rating getRating(){
        return get(Rating.class, "rating");
    }
    public void setRating(Rating rating){
        set("rating",rating);
    }

    public boolean isRateable(){
        return getBoolean("rateable");
    }
    public void setRateable(boolean rateable){
        set("rateable",rateable);
    }



}
