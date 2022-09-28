package in.succinct.beckn;

public class Agent extends Person{
    
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

    public String getName(){
        return get("name");
    }
    public void setName(String name){
        set("name",name);
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
