package in.succinct.beckn;

import java.util.Date;

public class State extends BecknObject{
    public State() {
    }
    public Descriptor getDescriptor(){
        return get(Descriptor.class, "descriptor");
    }
    public void setDescriptor(Descriptor descriptor){
        set("descriptor",descriptor);
    }

    public Date getUpdatedAt(){
        return getDate("updated_at",TIMESTAMP_FORMAT);
    }
    public void setUpdatedAt(Date updated_at){
        set("updated_at",updated_at,TIMESTAMP_FORMAT);
    }

    public String getUpdatedBy(){
        return get("updated_by");
    }
    public void setUpdatedBy(String updated_by){
        set("updated_by",updated_by);
    }
}
