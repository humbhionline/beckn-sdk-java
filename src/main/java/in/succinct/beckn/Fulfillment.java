package in.succinct.beckn;

import org.json.simple.JSONArray;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

public class Fulfillment extends BecknObjectWithId {

    public Fulfillment() {
        super();
    }

    public Fulfillment(String payload) {
        super(payload);
    }

    public FulfillmentType getType() {
        String s = get("type");
        return s == null ? null : FulfillmentType.valueOf(s);
    }

    public void setType(FulfillmentType type) {
        set("type", type == null ? null : type.toString());
    }

    public enum FulfillmentType {
        home_delivery(1),
        store_pickup(2),
        store_pickup_and_home_delivery(1|2),
        return_to_origin(4);

        int bits;
        FulfillmentType(int bits){
            this.bits = bits;
        }
        public boolean matches(FulfillmentType other){
            return (other == null || (other.bits & bits) > 0);
        }

    }

    public FulfillmentStop getStart() {
        return get(FulfillmentStop.class, "start");
    }

    public void setStart(FulfillmentStop start) {
        set("start", start);
    }

    public FulfillmentStop getEnd() {
        return get(FulfillmentStop.class, "end");
    }

    public void setEnd(FulfillmentStop end) {
        set("end", end);
    }

    public boolean getTracking() {
        return getBoolean("tracking");
    }

    public void setTracking(boolean tracking) {
        set("tracking", tracking);
    }

    public Agent getAgent() {
        return get("agent");
    }

    public void setAgent(Agent agent) {
        set("agent", agent);
    }

    public State getState() {
        return getState(false);
    }

    public State getState(boolean create) {
        return get(State.class, "state", create);
    }

    public void setState(State state) {
        set("state", state);
    }

    public void setState(String state) {
        getState(true).getDescriptor(true).setCode(state);
    }

    public User getCustomer() {
        return get(User.class, "customer");
    }

    public void setCustomer(User customer) {
        set("customer", customer);
    }

    public Vehicle getVehicle() {
        return get(Vehicle.class, "vehicle");
    }

    public void setVehicle(Vehicle vehicle) {
        set("vehicle", vehicle);
    }

    public Tags getTags() {
        return get(Tags.class, "tags");
    }

    public void setTags(Tags tags) {
        set("tags", tags);
    }

    public boolean getRateable() {
        return getBoolean("rateable");
    }

    public void setRateable(boolean rateable) {
        set("rateable", rateable);
    }

    public String getProviderId() {
        return get("provider_id");
    }

    public void setProviderId(String provider_id) {
        set("provider_id", provider_id);
    }

    public int getRating() {
        return get("rating");
    }

    public void setRating(int rating) {
        set("rating", rating);
    }

    public Contact getContact() {
        return get(Contact.class, "contact");
    }

    public void setContact(Contact contact) {
        set("contact", contact);
    }

    //Extended attributes rationalized from networks

    protected final BecknObject extendedAttributes = new BecknObject();
    public String getCategory(){
        return extendedAttributes.get("category");
    }
    public void setCategory(String category){
        extendedAttributes.set("category",category);
    }

    public Duration getTAT(){
        String tat =  extendedAttributes.get("tat");
        return tat == null ? null : Duration.parse(tat);
    }
    public void setTAT(Duration tat){
        extendedAttributes.set("tat",tat == null ? null : tat.toString());
    }

    public String getProviderName(){
        return extendedAttributes.get("provider_name");
    }
    public void setProviderName(String provider_name){
        extendedAttributes.set("provider_name",provider_name);
    }

    public ServiceablityTags getServiceablityTags(){
        return extendedAttributes.get(ServiceablityTags.class, "tags");
    }
    public void setServiceablityTags(ServiceablityTags tags){
        extendedAttributes.set("tags",tags);
    }

    public static class ServiceablityTags extends BecknObjects<Tag> {

        public ServiceablityTags() {
        }

        public ServiceablityTags(JSONArray value) {
            super(value);
        }

    }

    public static class List extends BecknObjects<Tag>{

        public List() {
        }

        public List(JSONArray value) {
            super(value);
        }
    }

}


