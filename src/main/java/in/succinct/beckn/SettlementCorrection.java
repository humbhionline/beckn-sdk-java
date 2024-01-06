package in.succinct.beckn;

import in.succinct.beckn.Order.ReconStatus;
import in.succinct.beckn.Order.Status;


public class SettlementCorrection extends BecknObject {

    public String getIssueInitiatorRef(){
        return get("issue_initiator_ref");
    }
    public void setIssueInitiatorRef(String issue_initiator_ref){
        set("issue_initiator_ref",issue_initiator_ref);
    }
    
    public String getIssueResponderRef(){
        return get("issue_responder_ref");
    }
    public void setIssueResponderRef(String issue_responder_ref){
        set("issue_responder_ref",issue_responder_ref);
    }

    public String getIssueType(){
        return get("issue_type");
    }
    public void setIssueType(String issue_type){
        set("issue_type",issue_type);
    }
    public String getIssueSubtype(){
        return get("issue_subtype");
    }
    public void setIssueSubtype(String issue_subtype){
        set("issue_subtype",issue_subtype);
    }


    public String getOrderId() {
        return get("order_id");
    }

    public void setOrderId(String order_id) {
        set("order_id", order_id);
    }

    public Amount getOrderAmount() {
        return get(Amount.class, "order_amount");
    }

    public void setOrderAmount(Amount order_amount) {
        set("order_amount", order_amount);
    }


    public Amount getOrderCorrectionAmount() {
        return get(Amount.class, "order_correction_amount");
    }

    public void setOrderCorrectionAmount(Amount order_amount) {
        set("order_correction_amount", order_amount);
    }

    public Status getStatus() {
        return getEnum(Status.class, "order_state");
    }

    public void setStatus(Status order_state) {
        setEnum("order_state", order_state);
    }



    public Amount getPreviousSettledAmount(){
        return get(Amount.class, "previous_settled_amount");
    }
    public void setPreviousSettledAmount(Amount previous_settled_amount){
        set("previous_settled_amount",previous_settled_amount);
    }

    public BecknStrings getPrevSettlementReferenceNo(){
        return get(BecknStrings.class, "prev_settlement_reference_no");
    }
    public void setPrevSettlementReferenceNo(BecknStrings prev_settlement_reference_no){
        set("prev_settlement_reference_no",prev_settlement_reference_no);
    }

    public String getSettlementId() {
        return get("settlement_id");
    }

    public void setSettlementId(String settlement_id) {
        set("settlement_id", settlement_id);
    }

    public String getSettlementReference() {
        return get("settlement_reference");
    }

    public void setSettlementReference(String settlement_reference) {
        set("settlement_reference", settlement_reference);
    }

    public ReconStatus getReconStatus() {
        return getEnum(ReconStatus.class, "recon_status", ReconStatus.convertor);
    }

    public void setReconStatus(ReconStatus recon_status) {
        setEnum("recon_status", recon_status, ReconStatus.convertor);
    }

    public Amount getDiffAmount(){
        return get(Amount.class, "diff_amount");
    }
    public void setDiffAmount(Amount diff_amount){
        set("diff_amount",diff_amount);
    }
    public Descriptor getMessage() {
        return get(Descriptor.class, "message");
    }

    public void setMessage(Descriptor message) {
        set("message", message);
    }

}