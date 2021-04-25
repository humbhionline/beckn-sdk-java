package in.succinct.beckn;

import org.json.simple.JSONObject;

public class Billing extends BecknObject {
    public Billing(){
        super();
    }

    public Person getCustomer(){
        return get(Person.class,"customer");
    }
    public void setCustomer(Person person){
        set("customer",person);
    }

    public Organization getOrganization(){
        return get(Organization.class,"organization");
    }

    public void setOrganization(Organization organization){
        set("organization",organization);
    }


    public BillToLocation getLocation(){
        return get(BillToLocation.class,"location");
    }
    public void setLocation(BillToLocation location){
        set("location",location);
    }

    public String getTaxNumber(){
        return get("tax_number");
    }

    public void setTaxNumber(String taxNumber){
        set("tax_number",taxNumber);
    }

    public Time getTime(){
        return get(Time.class,"time");
    }
    public void setTime(Time time){
        set("time",time);
    }

    public static class BillToLocation extends Location {

        public BillToLocation() {
            super();
        }

        public String getEmail(){
            return get("email");
        }

        public void setEmail(String email){
            set("email",email);
        }

    }
}
