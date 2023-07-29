package in.succinct.beckn;


import java.util.Date;

public class Cancellation extends BecknObject{
    public String getType(){
        return get("type");
    }
    public void setType(String type){
        set("type",type);
    }
    public String getRefId(){
        return get("ref_id");
    }
    public void setRefId(String ref_id){
        set("ref_id",ref_id);
    }
    public Policies getPolicies(){
        return get(Policies.class, "policies");
    }
    public void setPolicies(Policies policies){
        set("policies",policies);
    }

    public Date getTime(){
        return getTimestamp("time");
    }
    public void setTime(Date time){
        set("time",time,TIMESTAMP_FORMAT);
    }

    public CancelledBy getCancelledBy(){
        return getEnum(CancelledBy.class, "cancelled_by" ,CancelledBy.convertor);
    }
    public void setCancelledBy(CancelledBy cancelled_by){
        setEnum("cancelled_by",cancelled_by, CancelledBy.convertor);
    }

    public enum CancelledBy {
        BUYER,
        PROVIDER;

        public static EnumConvertor<CancelledBy> convertor = new EnumConvertor(CancelledBy.class);
    }

    public Options getReasons(){
        return get(Options.class, "reasons");
    }
    public void setReasons(Options reasons){
        set("reasons",reasons);
    }

    public Option getSelectedReason(){
        return get(Option.class, "selected_reason");
    }
    public void setSelectedReason(Option reason){
        set("selected_reason",reason);
    }
    
    public Descriptor getDescriptor(){
        return get(Descriptor.class, "addition_description");
    }
    public void setDescriptor(Descriptor addition_description){
        set("addition_description",addition_description);
    }
}
