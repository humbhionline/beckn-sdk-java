package in.succinct.beckn;

import in.succinct.beckn.Descriptor.MediaFile;
import org.json.simple.JSONArray;

public class ReplacementTerm extends BecknObject{
    public FulfillmentState getFulfillmentState(){
        return get(FulfillmentState.class, "fulfillment_state");
    }
    public void setFulfillmentState(FulfillmentState fulfillment_state){
        set("fulfillment_state",fulfillment_state);
    }




    public Time getReplaceWithin(){
        return get(Time.class, "replace_within");
    }
    public void setReplaceWithin(Time replace_within){
        set("replace_within",replace_within);
    }


    public MediaFile getExternalRef(){
        return get(MediaFile.class, "external_ref");
    }
    public void setExternalRef(MediaFile external_ref){
        set("external_ref",external_ref);
    }

    public static class ReplacementTerms extends BecknObjects<ReplacementTerm> {
        public ReplacementTerms() {
        }

        public ReplacementTerms(JSONArray value) {
            super(value);
        }

        public ReplacementTerms(String payload) {
            super(payload);
        }
    }
}
