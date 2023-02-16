package in.succinct.beckn;

import in.succinct.beckn.Payment.PaymentStatus;

public class SettlementDetail extends BecknObjectWithId {

    public enum SettlementCounterparty {
        BUYER,
        BUYER_APP,
        SELLER_APP,
        LOGISTICS_PROVIDER;
        public String toString(){
            return super.toString().toLowerCase().replace('_','-');
        }

    }
    public enum SettlementPhase {
        SALE_AMOUNT,
        WITHHOLDING_AMOUNT,
        REFUND;
        public String toString(){
            return super.toString().toLowerCase().replace('_','-');
        }
    }

    public SettlementCounterparty getSettlementCounterparty(){
        String s= get("settlement_counterparty");
        return s == null ? null : SettlementCounterparty.valueOf(s.toUpperCase().replace('-','_'));
    }

    public void setSettlementCounterparty(SettlementCounterparty settlement_counterparty){
        set("settlement_counterparty",settlement_counterparty == null ? null : settlement_counterparty.toString());
    }
    public SettlementPhase getSettlementPhase(){
        String s =  get("settlement_phase");
        return s == null ? null : SettlementPhase.valueOf(s.toUpperCase().replace('-','_'));
    }
    public void setSettlementPhase(SettlementPhase settlement_phase){
        set("settlement_phase",settlement_phase == null ? null : settlement_phase.toString());
    }
    public double getSettlementAmount(){
        return getDouble("settlement_amount");
    }
    public void setSettlementAmount(double settlement_amount){
        set("settlement_amount",settlement_amount);
    }

    public enum SettlementType {
        NEFT,
        RTGS,
        UPI;
        public String toString(){
            return super.toString().toLowerCase();
        }
    }
    public SettlementType getSettlementType(){
        String s = get("settlement_type");
        return s == null ? null : SettlementType.valueOf(s.toUpperCase());
    }

    public void setSettlementType(SettlementType settlement_type){
        set("settlement_type",settlement_type == null ? null : settlement_type.toString());
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

    public PaymentStatus getSettlementStatus(){
        String s = get("settlement_status");
        return s== null ? null : PaymentStatus.valueOf(s.replace('-','_'));
    }
    public void setSettlementStatus(PaymentStatus settlement_status){
        set("settlement_status",settlement_status == null ? null : settlement_status.toString());
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
