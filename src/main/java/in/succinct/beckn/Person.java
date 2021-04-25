package in.succinct.beckn;

import org.json.simple.JSONObject;

import java.util.Date;

public class Person extends BecknObjectWithId {
    public Person() {
        super();
    }
    public Name getName(){
        return get(Name.class,"name");
    }
    public void setName(Name name){
        set("name",name);
    }
    public Date getDob(){
        return getDate("dob",DATE_FORMAT);
    }
    public void setDob(Date date){
        set("dob",date,DATE_FORMAT);
    }

    public static class Name extends BecknObject {
        public Name(){
            super();
        }
        public String getFull(){
            return get("full");
        }
        public void setFull(String full){
            set("full",full);
        }
        public String getGivenName(){
            return get("given_name");
        }
        public void setGivenName(String name){
            set("given_name",name);
        }
        public String getFamilyName(){
            return get("family_name");
        }
        public void setFamilyName(String name){
            set("family_name",name);
        }

        public String getAdditionalName(){
            return get("additional_name");
        }
        public String getHonorificPrefix(){
            return get("honorific_prefix");
        }
        public String getHonorificSuffix(){
            return get("honorific_suffix");
        }
    }
}
