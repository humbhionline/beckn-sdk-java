package in.succinct.beckn;

import java.time.Duration;
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


    public String getType(){
        return get("type");
    }
    static final Set<String> TYPES = new HashSet<>(){{
        add("ON-ORDER");
        add("PRE-FULFILLMENT");
        add("ON-FULFILLMENT");
        add("POST-FULFILLMENT");
    }};
    public void setType(String type){
        if (!TYPES.contains(type)){
            throw new IllegalArgumentException();
        }
        set("type",type);
    }

    public String getStatus(){
        return get("status");
    }

    static final Set<String> STATUSES = new HashSet<>(){{
       add("PAID");
       add("NOT-PAID");
    }};
    public void setStatus(String status){
        if (!STATUSES.contains(status)){
            throw new IllegalArgumentException();
        }
        set("status",status);
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

    public String getCollectedBy(){
        return get("collected_by");
    }
    static final Set<String> COLLECTED_BY = new HashSet<String>(){{
        add("BAP");
        add("BPP");
    }};
    public void setCollectedBy(String collected_by){
        if (!COLLECTED_BY.contains(collected_by)){
            throw new IllegalArgumentException();
        }
        set("collected_by",collected_by);
    }

    protected final BecknObject extendedAttributes = new BecknObject();
    public String getCollectedByStatus(){
        return extendedAttributes.get("collected_by_status");
    }
    public void setCollectedByStatus(String collected_by_status){
        extendedAttributes.set("collected_by_status",collected_by_status);
    }


    public String getBuyerAppFinderFeeType(){
        return extendedAttributes.get("buyer_app_finder_fee_type");
    }
    public void setBuyerAppFinderFeeType(String buyer_app_finder_fee_type){
        extendedAttributes.set("buyer_app_finder_fee_type",buyer_app_finder_fee_type);
    }

    public double getBuyerAppFinderFeeAmount(){
        return extendedAttributes.getDouble("buyer_app_finder_fee_amount");
    }
    public void setBuyerAppFinderFeeAmount(double buyer_app_finder_fee_amount){
        extendedAttributes.set("buyer_app_finder_fee_amount",buyer_app_finder_fee_amount);
    }

    public String getWithholdingAmountStatus(){
        return extendedAttributes.get("withholding_amount_status");
    }
    public void setWithholdingAmountStatus(String withholding_amount_status){
        extendedAttributes.set("withholding_amount_status",withholding_amount_status);
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

    public String getReturnWindowStatus(){
        return extendedAttributes.get("return_window_status");
    }
    public void setReturnWindowStatus(String return_window_status){
        extendedAttributes.set("return_window_status",return_window_status);
    }

    public String getSettlementBasisStatus(){
        return extendedAttributes.get("settlement_basis_status");
    }
    public void setSettlementBasisStatus(String settlement_basis_status){
        extendedAttributes.set("settlement_basis_status",settlement_basis_status);
    }

    public String getSettlementBasis(){
        return extendedAttributes.get("settlement_basis");
    }
    public void setSettlementBasis(String settlement_basis){
        extendedAttributes.set("settlement_basis",settlement_basis);
    }

    public Duration getSettlementWindow(){
        String sw = extendedAttributes.get("settlement_window");
        return sw == null ? null : Duration.parse(sw);
    }
    public void setSettlementWindow(Duration settlement_window){
        extendedAttributes.set("settlement_window",settlement_window == null ? null : settlement_window.toString());
    }

    public String getSettlementWindowStatus(){
        return extendedAttributes.get("settlement_window_status");
    }
    public void setSettlementWindowStatus(String settlement_window_status){
        extendedAttributes.set("settlement_window_status",settlement_window_status);
    }
    public SettlementDetails getSettlementDetails(){
        return extendedAttributes.get(SettlementDetails.class, "settlement_details");
    }
    public void setSettlementDetails(SettlementDetails settlement_details){
        extendedAttributes.set("settlement_details",settlement_details);
    }

}
