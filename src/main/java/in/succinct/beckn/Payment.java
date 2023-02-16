package in.succinct.beckn;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Payment extends BecknObjectWithId {
    public Payment(){
        super();
    }
    public Payment(String payload){
        super(payload);
    }
    public String getUri(){
        return get("uri");
    }
    public void setUri(String uri){
        set("uri",uri);
    }
    public String getTlMethod(){
        return get("tl_method");
    }
    static final Set<String> TL_METHODS = new HashSet<>(){{
       add("http/get");
       add("http/post");
    }};
    public void setTlMethod(String tl_method){
        if  (TL_METHODS.contains(tl_method)) {
            set("tl_method", tl_method);
        }else {
            throw new IllegalArgumentException();
        }
    }
    public Params getParams(){
        return get(Params.class,"params");
    }
    public void setParams(Params params){
        set("params",params);
    }


    public PaymentType getType(){
        String s = get("type");
        return s == null ? null : PaymentType.valueOf(s);
    }
    public enum PaymentType {
        ON_ORDER ("ON-ORDER"),
        ON_FULFILLMENT("ON-FULFILLMENT"),
        PRE_FULFILLMENT ("PRE-FULFILLMENT"),
        POST_FULFILLMENT("POST-FULFILLMENT");

        PaymentType(String tos){
            this.tos = tos;
        }
        final String tos;
        public String toString(){
            return tos;
        }
    }
    ;
    public void setType(PaymentType type){
        set("type",type == null ? null : type.toString());
    }

    public enum PaymentStatus {
        PAID,
        NOT_PAID,
        PENDING;

        public String toString(){
            return super.toString().replace('_','-');
        }
    }
    public PaymentStatus getStatus(){
        String s =  get("status");
        return s == null ? null : PaymentStatus.valueOf(s);
    }

    public void setStatus(PaymentStatus status){
        set("status",status == null ? null : status.toString());
    }

    public Time getTime(){
        return get(Time.class,"time");
    }
    public void setTime(Time time){
        set("time",time);
    }

    public static class Params extends BecknObject{
        public String getTransactionId(){
            return get("transaction_id");
        }
        public void setTransactionId(String transaction_id){
            set("transaction_id",transaction_id);
        }

        public double getAmount(){
            return getDouble("amount");
        }
        public void setAmount(double amount){
            set("amount",String.valueOf(amount));
        }

        public String getCurrency(){
            return get("currency");
        }
        public void setCurrency(String currency){
            set("currency",currency);
        }


    }

    public CollectedBy getCollectedBy(){
        String s = get("collected_by");
        return s == null ? null : CollectedBy.valueOf(s);
    }

    public enum CollectedBy {
        BAP,
        BPP
    }
    public void setCollectedBy(CollectedBy collected_by){
        set("collected_by",collected_by == null ? null : collected_by.toString());
    }

    protected final BecknObject extendedAttributes = new BecknObject();
    public NegotiationStatus getCollectedByStatus(){
        String status =  extendedAttributes.get("collected_by_status");
        return status == null ? null : NegotiationStatus.valueOf(status);
    }
    public void setCollectedByStatus(NegotiationStatus collected_by_status){
        extendedAttributes.set("collected_by_status",collected_by_status == null ? null : collected_by_status.toString());
    }


    public CommissionType getBuyerAppFinderFeeType(){
        String s = extendedAttributes.get("buyer_app_finder_fee_type");
        return s == null ? null : CommissionType.valueOf(s);
    }
    public void setBuyerAppFinderFeeType(CommissionType buyer_app_finder_fee_type){
        extendedAttributes.set("buyer_app_finder_fee_type",buyer_app_finder_fee_type == null ? null :buyer_app_finder_fee_type.toString());
    }

    public double getBuyerAppFinderFeeAmount(){
        return extendedAttributes.getDouble("buyer_app_finder_fee_amount");
    }
    public void setBuyerAppFinderFeeAmount(double buyer_app_finder_fee_amount){
        extendedAttributes.set("buyer_app_finder_fee_amount",buyer_app_finder_fee_amount);
    }

    public NegotiationStatus getWithholdingAmountStatus(){
        String s = extendedAttributes.get("withholding_amount_status");
        return s == null ? null : NegotiationStatus.valueOf(s);
    }
    public void setWithholdingAmountStatus(NegotiationStatus withholding_amount_status){
        extendedAttributes.set("withholding_amount_status",withholding_amount_status == null ? null  : withholding_amount_status.toString());
    }

    public double getWithholdingAmount(){
        return extendedAttributes.getDouble("withholding_amount");
    }
    public void setWithholdingAmount(double withholding_amount){
        extendedAttributes.set("withholding_amount",withholding_amount);
    }
    public Duration getReturnWindow(){
        return Duration.parse(extendedAttributes.get("return_window"));
    }
    public void setReturnWindow(Duration return_window){
        extendedAttributes.set("return_window",return_window.toString());
    }

    public NegotiationStatus getReturnWindowStatus(){
        String s = extendedAttributes.get("return_window_status");
        return s == null ? null : NegotiationStatus.valueOf(s);
    }
    public void setReturnWindowStatus(NegotiationStatus return_window_status){
        extendedAttributes.set("return_window_status",return_window_status == null ? null : return_window_status.toString());
    }

    public NegotiationStatus getSettlementBasisStatus(){
        String s = extendedAttributes.get("settlement_basis_status");
        return s == null ? null : NegotiationStatus.valueOf(s);
    }
    public void setSettlementBasisStatus(NegotiationStatus settlement_basis_status){
        extendedAttributes.set("settlement_basis_status",settlement_basis_status == null ? null : settlement_basis_status.toString());
    }

    public SettlementBasis getSettlementBasis(){
        String s = extendedAttributes.get("settlement_basis");
        return s == null ? null : SettlementBasis.valueOf(s);
    }
    public void setSettlementBasis(SettlementBasis settlement_basis){
        extendedAttributes.set("settlement_basis",settlement_basis == null ? null : settlement_basis.toString());
    }

    public Duration getSettlementWindow(){
        String sw = extendedAttributes.get("settlement_window");
        return sw == null ? null : Duration.parse(sw);
    }
    public void setSettlementWindow(Duration settlement_window){
        extendedAttributes.set("settlement_window",settlement_window == null ? null : settlement_window.toString());
    }

    public NegotiationStatus getSettlementWindowStatus(){
        String ws = extendedAttributes.get("settlement_window_status");
        return null == ws ? null : NegotiationStatus.valueOf(ws);
    }
    public void setSettlementWindowStatus(NegotiationStatus settlement_window_status){
        extendedAttributes.set("settlement_window_status",settlement_window_status == null ? null :settlement_window_status.toString());
    }
    public SettlementDetails getSettlementDetails(){
        return extendedAttributes.get(SettlementDetails.class, "settlement_details");
    }
    public void setSettlementDetails(SettlementDetails settlement_details){
        extendedAttributes.set("settlement_details",settlement_details);
    }



    public enum NegotiationStatus {
        Assert,
        Agree,
        DisAgree,
        Terminate,
    }

    public enum CommissionType{
        Percent,
        Amount
    }

    public enum SettlementBasis {
        Collection,
        Shipment,
        Delivery
    }

}
