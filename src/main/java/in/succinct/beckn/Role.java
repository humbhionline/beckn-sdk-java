package in.succinct.beckn;

import in.succinct.beckn.Issue.EscalationLevel;
import in.succinct.json.JSONAwareWrapper.EnumConvertor;

public enum Role {

    COMPLAINANT_PARTY(Representative.COMPLAINANT,0), // Buyer or seller
    COMPLAINANT_PLATFORM(Representative.COMPLAINANT,1), //BAP OR BPP
    COMPLAINANT_GRO(Representative.COMPLAINANT,2),

    RESPONDENT_PARTY(Representative.RESPONDENT,0), // Seller or buyer
    RESPONDENT_PLATFORM(Representative.RESPONDENT,1), // BPP OR BAP
    RESPONDENT_GRO(Representative.RESPONDENT,2),

    CASCADED_RESPONDENT_PARTY(Representative.RESPONDENT,0), //Could be drivber
    CASCADED_RESPONDENT_PLATFORM(Representative.RESPONDENT,1), //LOGISTICS PARTY
    CASCADED_RESPONDENT_GRO(Representative.RESPONDENT,2),

    ODR_ARBITRATOR(Representative.BOTH,3);



    int bits;
    int escalation;
    Role(int bits, int escalation){
        this.bits = bits;
        this.escalation = escalation;
    }
    public boolean isComplainant(){
        return (this.bits & Representative.COMPLAINANT) > 0;
    }

    public boolean isRespondent(){
        return (bits & Representative.RESPONDENT) > 0;
    }

    public static final int ESCALATION_LEVEL_PARTY = 0 ;
    public static final int ESCALATION_LEVEL_PLATFORM = 1 ;
    public static final int ESCALATION_LEVEL_GRO = 2 ;
    public static final int ESCALATION_LEVEL_ODR = 3 ;




    public int getEscalation() {
        return  escalation;
    }

    public static final EnumConvertor<Role> convertor = new EnumConvertor<>(Role.class);
}
