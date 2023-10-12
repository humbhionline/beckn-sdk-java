package in.succinct.beckn;

import java.util.Date;

public class Intent extends BecknObject implements TagGroupHolder {
    public Intent(){
        super();
    }
    public Intent(String payload){
        super(payload);
    }

    public Descriptor getDescriptor(){
        return get(Descriptor.class, "descriptor");
    }
    public void setDescriptor(Descriptor descriptor){
        set("descriptor",descriptor);
    }

    public Fulfillment getFulfillment(){
        return get(Fulfillment.class, "fulfillment");
    }
    public void setFulfillment(Fulfillment fulfillment){
        set("fulfillment",fulfillment);
    }

    public Provider getProvider(){
        return get(Provider.class, "provider");
    }
    public void setProvider(Provider provider){
        set("provider",provider);
    }

    public Item getItem(){
        return get(Item.class, "item");
    }
    public void setItem(Item item){
        set("item",item);
    }


    public Category getCategory(){
        return get(Category.class,"category");
    }
    public void setCategory(Category category){
        set("category",category);
    }

    public Payment getPayment(){
        return get(Payment.class, "payment");
    }
    public void setPayment(Payment payment){
        set("payment",payment);
    }

    @Override
    public boolean isExtendedAttributesDisplayed() {
        return true;
    }

    public boolean isIncrementalRequest(){
        return getStartTime() != null || getEndTime() != null;
    }

    public boolean isIncrementalRequestStartTrigger(){
        return extendedAttributes.getBoolean("incremental_request_start_trigger");
    }
    public void setIncrementalRequestStartTrigger(boolean incremental_request_start_trigger){
        extendedAttributes.set("incremental_request_start_trigger",incremental_request_start_trigger);
    }

    public boolean isIncrementalRequestEndTrigger(){
        return extendedAttributes.getBoolean("incremental_request_end_trigger");
    }
    public void setIncrementalRequestEndTrigger(boolean incremental_request_end_trigger){
        extendedAttributes.set("incremental_request_end_trigger",incremental_request_end_trigger);
    }

    public Date getStartTime(){
        return extendedAttributes.getTimestamp("start_time");
    }
    public void setStartTime(Date start_time){
        extendedAttributes.set("start_time",start_time,TIMESTAMP_FORMAT);
    }

    public Date getEndTime(){
        return extendedAttributes.getTimestamp("end_time");
    }
    public void setEndTime(Date end_time){
        extendedAttributes.set("end_time",end_time,TIMESTAMP_FORMAT);
    }


}
