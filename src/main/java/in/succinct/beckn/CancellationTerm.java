package in.succinct.beckn;

import in.succinct.beckn.Descriptor.MediaFile;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class CancellationTerm extends BecknObject{
    public CancellationTerm() {
    }

    public CancellationTerm(String payload) {
        super(payload);
    }

    public CancellationTerm(JSONObject object) {
        super(object);
    }

    public FulfillmentState getFulfillmentState(){
        return get(FulfillmentState.class, "fulfillment_state");
    }
    public void setFulfillmentState(FulfillmentState fulfillment_state){
        set("fulfillment_state",fulfillment_state);
    }

    public boolean isReasonRequired(){
        return getBoolean("reason_required");
    }
    public void setReasonRequired(boolean reason_required){
        set("reason_required",reason_required);
    }

    public Time getTime(){
        return get(Time.class, "cancel_by");
    }
    public void setTime(Time time){
        set("cancel_by",time);
    }

    public Fee getCancellationFee(){
        return get(Fee.class, "cancellation_fee");
    }
    public void setCancellationFee(Fee cancellation_fee){
        set("cancellation_fee",cancellation_fee);
    }

    public Xinput getXInput(){
        return get(Xinput.class, "xinput");
    }
    public void setXInput(Xinput x_input){
        set("xinput",x_input);
    }

    public MediaFile getExternalRef(){
        return get(MediaFile.class, "external_ref");
    }
    public void setExternalRef(MediaFile external_ref){
        set("external_ref",external_ref);
    }


    public static class CancellationTerms extends BecknObjects<CancellationTerm>{
        public CancellationTerms() {
        }

        public CancellationTerms(JSONArray value) {
            super(value);
        }

        public CancellationTerms(String payload) {
            super(payload);
        }
    }

}
