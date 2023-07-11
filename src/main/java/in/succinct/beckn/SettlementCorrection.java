package in.succinct.beckn;

import in.succinct.beckn.Order.ReconStatus;
import in.succinct.beckn.Order.Status;


public class SettlementCorrection extends BecknObject {
    public String getIssueId() {
        return get("issue_id");
    }

    public void setIssueId(String issue_id) {
        set("issue_id", issue_id);
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

    public double getPreviousSettledAmount() {
        return getDouble("previous_settled_amount");
    }

    public void setPreviousSettledAmount(double previous_settled_amount) {
        set("previous_settled_amount", previous_settled_amount);
    }

    public String getPreviousSettlementReference() {
        return get("previous_settlement_reference");
    }

    public void setPreviousSettlementReference(String previous_settlement_reference) {
        set("previous_settlement_reference", previous_settlement_reference);
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

    public Double getDiffAmount() {
        return getDouble("diff_amount", null);
    }

    public void setDiffAmount(Double diff_amount) {
        set("diff_amount", diff_amount);
    }

    public Descriptor getMessage() {
        return get(Descriptor.class, "message");
    }

    public void setMessage(Descriptor message) {
        set("message", message);
    }

}