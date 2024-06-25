package in.succinct.beckn;


import java.util.Date;

public class Cancellation extends BecknObject{

    public Date getTime(){
        return getTimestamp("time");
    }
    public void setTime(Date time){
        set("time",time,TIMESTAMP_FORMAT);
    }

    public CancelledBy getCancelledBy(){
        return getEnum(CancelledBy.class, "cancelled_by" , CancelledBy.convertor);
    }
    public void setCancelledBy(CancelledBy cancelled_by){
        setEnum("cancelled_by",cancelled_by, CancelledBy.convertor);
    }

    public enum CancelledBy {
        BUYER,
        PROVIDER;

        public static EnumConvertor<CancelledBy> convertor = new EnumConvertor(CancelledBy.class);
    }



    public Option getSelectedReason(){
        return get(Option.class, "reason");
    }
    public void setSelectedReason(Option reason){
        set("reason",reason);
    }
    
    public Descriptor getDescriptor(){
        return get(Descriptor.class, "addition_description");
    }
    public void setDescriptor(Descriptor addition_description){
        set("addition_description",addition_description);
    }
}
