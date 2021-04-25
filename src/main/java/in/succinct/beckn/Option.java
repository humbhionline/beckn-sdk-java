package in.succinct.beckn;

import org.json.simple.JSONObject;

public class Option extends BecknObjectWithId {
    public Option() {
        super();
    }
    public Descriptor getDescriptor(){
        return get(Descriptor.class,"descriptor");
    }
    public void setDescriptor(Descriptor descriptor){
        set("descriptor",descriptor.getInner());
    }
}
