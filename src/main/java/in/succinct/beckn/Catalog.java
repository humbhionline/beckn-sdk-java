package in.succinct.beckn;

public class Catalog extends BecknObjectWithId {
    public Catalog(){
        super();
    }

    public Descriptor getDescriptor(){
        return get(Descriptor.class,"bpp/descriptor");
    }
    public void setDescriptor(Descriptor descriptor){
        set("bpp/descriptor", descriptor);
    }

    public Providers getProviders(){
        return get(Providers.class,"bpp/providers");
    }

    public void setProviders(Providers providers){
        set("bpp/providers",providers);
    }



    public Fulfillments getFulfillments(){
        return get(Fulfillments.class, "bpp/fulfillments");
    }
    public void setFulfillments(Fulfillments fulfillments){
        set("bpp/fulfillments",fulfillments);
    }

}
