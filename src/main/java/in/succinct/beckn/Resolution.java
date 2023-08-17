package in.succinct.beckn;

public class Resolution extends BecknObject {
    public String getResolution(){
        return get("resolution");
    }
    public void setResolution(String resolution){
        set("resolution",resolution);
    }
    
    public String getResolutionRemarks(){
        return get("resolution_remarks");
    }
    public void setResolutionRemarks(String resolution_remarks){
        set("resolution_remarks",resolution_remarks);
    }
    
    public String getGroResolutionRemarks(){
        return get("gro_resolution_remarks");
    }
    public void setGroResolutionRemarks(String gro_remarks){
        set("gro_resolution_remarks",gro_remarks);
    }

    public String getDisputeResolutionRemarks(){
        return get("dispute_resolution_remarks");
    }
    public void setDisputeResolutionRemarks(String dispute_resolution_remarks){
        set("dispute_resolution_remarks",dispute_resolution_remarks);
    }

    public ResolutionStatus getResolutionStatus(){
        return getEnum(ResolutionStatus.class, "resolution_status", ResolutionStatus.convertor);
    }
    public void setResolutionStatus(ResolutionStatus resolution_status){
        setEnum("resolution_status",resolution_status, ResolutionStatus.convertor);
    }

    public enum ResolutionStatus {
        RESOLVED,
        REJECTED;


        static final EnumConvertor<ResolutionStatus> convertor = new EnumConvertor<>(ResolutionStatus.class);
    }


    public ResolutionAction getResolutionAction(){
        return getEnum(ResolutionAction.class, "resolution_action", ResolutionAction.convertor);
    }
    public void setResolutionAction(ResolutionAction resolution_action){
        setEnum("resolution_action",resolution_action,ResolutionAction.convertor);
    }


    public enum ResolutionAction {
        REFUND,
        REPLACEMENT,
        NO_ACTION;

        @Override
        public String toString() {
            return convertor.toString(this);
        }
        public static final EnumConvertor<ResolutionAction> convertor = new EnumConvertor<>(ResolutionAction.class);
    }


    public Double getRefundAmount(){
        return getDouble("refund_amount", null);
    }
    public void setRefundAmount(Double refund_amount){
        set("refund_amount",refund_amount);
    }


}
