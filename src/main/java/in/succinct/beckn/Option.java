package in.succinct.beckn;

public class Option extends BecknObjectWithId {
    public Option() {
        super();
    }
    public Descriptor getDescriptor(){
        return get(Descriptor.class,"descriptor");
    }
    public void setDescriptor(Descriptor descriptor){
        set("descriptor",descriptor);
    }
}
