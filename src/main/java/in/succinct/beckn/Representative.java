package in.succinct.beckn;

import in.succinct.beckn.AdditionalProperties.AdditionalSources;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Representative extends Agent {
    public String getSubscriberId(){
        return get("subscriber_id");
    }
    public void setSubscriberId(String subscriber_id){
        set("subscriber_id",subscriber_id);
    }

    public Role getRole(){
        return getEnum(Role.class, "role" ,Role.convertor);
    }
    public void setRole(Role role){
        setEnum("role",role,Role.convertor);
        validateRole();
    }

    @Override
    public void setInner(JSONObject value) {
        super.setInner(value);
        validateRole();
    }

    protected void validateRole(){

    }

    public String getChatUrl(){
        return get("chat_url");
    }
    public void setChatUrl(String chat_url){
        set("chat_url",chat_url);
    }

    public String getFaqUrl(){
        return get("faq_url");
    }
    public void setFaqUrl(String faq_url){
        set("faq_url",faq_url);
    }

    public AdditionalSources getAdditionalSources(){
        return get(AdditionalSources.class, "additional_sources");
    }
    public void setAdditionalSources(AdditionalSources additional_sources){
        set("additional_sources",additional_sources);
    }





    public static class Respondent extends Representative {

        @Override
        protected void validateRole(){
            Role role = getRole();
            if (role != null && !role.isRespondent()){
                throw new RuntimeException("Invalid Role");
            }
        }


    }
    public static final int COMPLAINANT = 1;
    public static final int RESPONDENT = 2;
    public static final int BOTH = COMPLAINANT | RESPONDENT;

    public static class Complainant extends Representative {

        @Override
        protected void validateRole(){
            Role role = getRole();
            if (role != null && !role.isComplainant()){
                throw new RuntimeException("Invalid Role");
            }
        }
    }

    public static class Respondents extends BecknObjects<Respondent> {

        public Respondents() {
        }

        public Respondents(JSONArray value) {
            super(value);
        }

        public Respondents(String payload) {
            super(payload);
        }
    }

    public static class Representatives extends BecknObjects<Representative> {

        public Representatives() {
        }

        public Representatives(JSONArray value) {
            super(value);
        }

        public Representatives(String payload) {
            super(payload);
        }
    }
}
