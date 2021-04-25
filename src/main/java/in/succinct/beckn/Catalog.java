package in.succinct.beckn;

public class Catalog extends BecknObjectWithId {
    public Catalog(){
        super();
    }

    public Descriptor getDescriptor(){
        return get(Descriptor.class,"descriptor");
    }
    public void setDescriptor(Descriptor descriptor){
        set("descriptor", descriptor);
    }

    public Providers getProviders(){
        return get(Providers.class,"providers");
    }

    public void setProviders(Providers providers){
        set("providers",providers);
    }




}
