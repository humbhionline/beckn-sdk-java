package in.succinct.beckn;

public class FulfillmentStop extends BecknObject {
    public FulfillmentStop() {
        super();
    }

    public Location getLocation(){
        return get("location");
    }
    public void setLocation(Location location){
        set("location",location);
    }

    public Time getTime(){
        return get("time");
    }
    public void setTime(Time time){
        set("time",time);
    }

    public Descriptor getInstructions(){
        return get("instructions");
    }
    public void setInstructions(Descriptor instructions){
        set("instructions",instructions);
    }
    public Contact getContact(){
        return get("contact");
    }
    public void setContact(Contact contact){
        set("contact",contact);
    }
}
