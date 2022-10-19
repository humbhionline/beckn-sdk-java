package in.succinct.beckn;

import java.util.Date;

public class Person extends BecknObjectWithId {
    public Person() {
        super();
    }
    public String getName(){
        return get("name");
    }
    public void setName(String name){
        set("name",name);
    }

    public Date getDob(){
        return getDate("dob");
    }
    public void setDob(Date date){
        set("dob",date,DATE_FORMAT);
    }

    public String getGender(){
        return get("gender");
    }
    public void setGender(String gender){
        set("gender",gender);
    }

}
