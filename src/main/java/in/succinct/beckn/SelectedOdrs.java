package in.succinct.beckn;

import in.succinct.beckn.Odr.Odrs;
import org.json.simple.JSONArray;

public class SelectedOdrs extends BecknObject {


   public Representative getRepresentative(){
       return get(Representative.class, "representative");
   }
   public void setRepresentative(Representative representative){
       set("representative",representative);
   }


    public Odrs getOdrs(){
        return get(Odrs.class, "odrs");
    }
    public void setOdrs(Odrs odrs){
        set("odrs",odrs);
    }


    public static class SelectedOdrsList extends BecknObjects<SelectedOdrs> {

        public SelectedOdrsList() {
        }

        public SelectedOdrsList(JSONArray value) {
            super(value);
        }

        public SelectedOdrsList(String payload) {
            super(payload);
        }
    }
}
