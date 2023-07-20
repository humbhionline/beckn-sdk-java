package in.succinct.beckn;

import in.succinct.json.JSONAwareWrapper.EnumConvertor;

public enum Role {

    COMPLAINANT_PARTY(Representative.COMPLAINANT,0), // Buyer or seller
    COMPLAINANT_PLATFORM(Representative.COMPLAINANT,1), //BAP OR BPP
    COMPLAINANT_GRO(Representative.COMPLAINANT,2),

    RESPONDENT_PARTY(Representative.RESPONDENT,0), // Seller or buyer
    RESPONDENT_PLATFORM(Representative.RESPONDENT,1), // BPP OR BAP
    RESPONDENT_GRO(Representative.RESPONDENT,2),

    CASCADED_RESPONDENT_PARTY(Representative.RESPONDENT,0), //Could be drivber
    CASCADED_RESPONDENT_PLATFORM(Representative.RESPONDENT,2), //LOGISTICS PARTY
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

    public int getEscalation() {
        return  escalation;
    }


    public static final EnumConvertor<Role> convertor = new EnumConvertor<>(Role.class);
}
