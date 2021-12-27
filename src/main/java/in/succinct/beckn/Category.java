package in.succinct.beckn;

public class Category extends BecknObjectWithId{
    public Category(){
        super();
    }
    public Category(String  payload){
        super(payload);
    }
    public Descriptor getDescriptor(){
        return get(Descriptor.class,"descriptor");
    }
    public void setDescriptor(Descriptor descriptor){
        set("descriptor",descriptor.getInner());
    }
}
