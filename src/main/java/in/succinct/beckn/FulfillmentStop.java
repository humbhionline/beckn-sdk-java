package in.succinct.beckn;

import org.json.simple.JSONObject;

public class FulfillmentStop extends BecknObject {
    public FulfillmentStop() {
        super();
    }

    public FulfillmentStop(JSONObject stop) {
        super(stop);
    }
    public FulfillmentStop(String payload){
        super(payload);
    }

    public Location getLocation(){
        return get(Location.class,"location");
    }
    public void setLocation(Location location){
        set("location",location);
    }

    public Time getTime(){
        return get(Time.class, "time");
    }
    public void setTime(Time time){
        set("time",time);
    }
    public Descriptor getInstructions(){
        return get(Descriptor.class,"instructions");
    }
    public void setInstructions(Descriptor instructions){
        set("instructions",instructions);
    }
    public Contact getContact(){
        return get(Contact.class,"contact");
    }
    public void setContact(Contact contact){
        set("contact",contact);
    }

    public Person getPerson(){
        return get(Person.class, "person");
    }
    public void setPerson(Person person){
        set("person",person);
    }

    public String getAuthorization(){
        return get("authorization");
    }
    public void setAuthorization(String authorization){
        set("authorization",authorization);
    }


}
