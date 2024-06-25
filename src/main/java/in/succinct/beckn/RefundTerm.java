package in.succinct.beckn;

import org.json.simple.JSONArray;

public class RefundTerm extends BecknObject{
    public FulfillmentState getFulfillmentState(){
        return get(FulfillmentState.class, "fulfillment_state");
    }
    public void setFulfillmentState(FulfillmentState fulfillment_state){
        set("fulfillment_state",fulfillment_state);
    }

    public boolean isRefundEligible(){
        return getBoolean("refund_eligible");
    }
    public void setRefundEligible(boolean refund_eligible){
        set("refund_eligible",refund_eligible);
    }



    public Time getRefundWithin(){
        return get(Time.class, "refund_within");
    }
    public void setRefundWithin(Time refund_within){
        set("refund_within",refund_within);
    }


    public Price getRefundAmount(){
        return get(Price.class, "refund_amount");
    }
    public void setRefundAmount(Price refund_amount){
        set("refund_amount",refund_amount);
    }


    public static class RefundTerms extends BecknObjects<RefundTerm>{
        public RefundTerms() {
        }

        public RefundTerms(JSONArray value) {
            super(value);
        }

        public RefundTerms(String payload) {
            super(payload);
        }
    }
}
