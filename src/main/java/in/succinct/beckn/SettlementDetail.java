package in.succinct.beckn;

import java.util.HashSet;
import java.util.Set;

public class SettlementDetail extends BecknObjectWithId {
    public String getSettlementCounterparty(){
        return get("settlement_counterparty");
    }
    static final Set<String> SETTLEMENT_COUNTERPARTY = new HashSet<String>(){{
        add("buyer");
        add("buyer-app");
        add("seller-app");
        add("logistics-provider");
    }};
    public void setSettlementCounterparty(String settlement_counterparty){
        if (!SETTLEMENT_COUNTERPARTY.contains(settlement_counterparty)){
            throw new IllegalArgumentException();
        }
        set("settlement_counterparty",settlement_counterparty);
    }
    public String getSettlementPhase(){
        return get("settlement_phase");
    }
    static final Set<String> SETTLEMENT_PHASE = new HashSet<String>(){{
        add("sale-amount");
        add("withholding-amount");
        add("refund");
    }};
    public void setSettlementPhase(String settlement_phase){
        if (!SETTLEMENT_PHASE.contains(settlement_phase)){
            throw new IllegalArgumentException();
        }
        set("settlement_phase",settlement_phase);
    }
    public double getSettlementAmount(){
        return getDouble("settlement_amount");
    }
    public void setSettlementAmount(double settlement_amount){
        set("settlement_amount",settlement_amount);
    }
    public String getSettlementType(){
        return get("settlement_type");
    }
    static final Set<String> SETTLEMENT_TYPE = new HashSet<String>(){{
        add("neft");
        add("rtgs");
        add("upi");
    }};
    public void setSettlementType(String settlement_type){
        if (!SETTLEMENT_TYPE.contains(settlement_type)){
            throw new IllegalArgumentException();
        }
        set("settlement_type",settlement_type);
    }
    public String getSettlementBankAccountNo(){
        return get("settlement_bank_account_no");
    }
    public void setSettlementBankAccountNo(String settlement_bank_account_no){
        set("settlement_bank_account_no",settlement_bank_account_no);
    }

    public String getSettlementIfscCode(){
        return get("settlement_ifsc_code");
    }
    public void setSettlementIfscCode(String settlement_ifsc_code){
        set("settlement_ifsc_code",settlement_ifsc_code);
    }

    public String getUpiAddress(){
        return get("upi_address");
    }
    public void setUpiAddress(String upi_address){
        set("upi_address",upi_address);
    }

    public String getBankName(){
        return get("bank_name");
    }
    public void setBankName(String bank_name){
        set("bank_name",bank_name);
    }

    public String getBranchName(){
        return get("branch_name");
    }
    public void setBranchName(String branch_name){
        set("branch_name",branch_name);
    }

    public String getBeneficiaryName(){
        return get("beneficiary_name");
    }
    public void setBeneficiaryName(String beneficiary_name){
        set("beneficiary_name",beneficiary_name);
    }

    public String getBeneficiaryAddress(){
        return get("beneficiary_address");
    }
    public void setBeneficiaryAddress(String beneficiary_address){
        set("beneficiary_address",beneficiary_address);
    }

    public String getSettlementStatus(){
        return get("settlement_status");
    }
    static final Set<String> SETTLEMENT_STATUS = new HashSet<String>(){{
        add("PAID");
        add("NOT-PAID");
    }};
    public void setSettlementStatus(String settlement_status){
        if (!SETTLEMENT_STATUS.contains(settlement_status)){
            throw new IllegalArgumentException();
        }
        set("settlement_status",settlement_status);
    }

    public String getSettlementReference(){
        return get("settlement_reference");
    }
    public void setSettlementReference(String settlement_reference){
        set("settlement_reference",settlement_reference);
    }

    public String getSettlementTimestamp(){
        return get("settlement_timestamp");
    }
    public void setSettlementTimestamp(String settlement_timestamp){
        set("settlement_timestamp",settlement_timestamp);
    }
}
