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



    public enum Role {

        COMPLAINANT_PARTY(COMPLAINANT,0), // Buyer or seller
        COMPLAINANT_PLATFORM(COMPLAINANT,1), //BAP OR BPP
        COMPLAINANT_GRO(COMPLAINANT,2),

        RESPONDENT_PARTY(RESPONDENT,0), // Seller or buyer
        RESPONDENT_PLATFORM(RESPONDENT,1), // BPP OR BAP
        RESPONDENT_GRO(RESPONDENT,2),

        CASCADED_RESPONDENT_PARTY(RESPONDENT,0), //Could be drivber
        CASCADED_RESPONDENT_PLATFORM(RESPONDENT,2), //LOGISTICS PARTY
        CASCADED_RESPONDENT_GRO(RESPONDENT,2),

        ODR_ARBITRATOR(BOTH,3);

        int bits;
        int escalation;
        Role(int bits, int escalation){
            this.bits = bits;
            this.escalation = escalation;
        }
        public boolean isComplainant(){
            return (this.bits & COMPLAINANT) > 0;
        }

        public boolean isRespondent(){
            return (bits & RESPONDENT) > 0;
        }

        public int getEscalation() {
            return  escalation;
        }


        public static final EnumConvertor<Role> convertor = new EnumConvertor<>(Role.class);
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
