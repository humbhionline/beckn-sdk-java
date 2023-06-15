package in.succinct.beckn.recon;

import in.succinct.beckn.BecknObject;
import in.succinct.beckn.BecknObjectWithId;
import in.succinct.beckn.BecknObjects;
import in.succinct.beckn.Descriptor;
import in.succinct.beckn.Order;
import in.succinct.beckn.Price;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Date;

public class Settlement extends BecknObjectWithId {


    public String getReference(){
        return get("reference");
    }
    public void setReference(String reference){
        set("reference",reference);
    }


    public Date getInitiatedAt(){
        return getTimestamp("initiated_at");
    }
    public void setInitiatedAt(Date initiated_at){
        set("initiated_at",initiated_at,TIMESTAMP_FORMAT);
    }


    public Date getInformedAt(){
        return getTimestamp("informed_at");
    }
    public void setInformedAt(Date informed_at){
        set("informed_at",informed_at,TIMESTAMP_FORMAT);
    }

    public String getCollectorAppId(){
        return get("collector_app_id");
    }
    public void setCollectorAppId(String collector_app_id){
        set("collector_app_id",collector_app_id);
    }

    public String getReceiverAppId(){
        return get("receiver_app_id");
    }
    public void setReceiverAppId(String receiver_app_id){
        set("receiver_app_id",receiver_app_id);
    }

    public String getReceiverAppUri(){
        return get("receiver_app_uri");
    }
    public void setReceiverAppUri(String receiver_app_uri){
        set("receiver_app_uri",receiver_app_uri);
    }



    public static class SettledOrders extends BecknObjects<SettledOrder> {

    }

    public static class SettledOrder extends Order {

        public String getInvoiceNo(){
            return get("invoice_no");
        }
        public void setInvoiceNo(String invoice_no){
            set("invoice_no",invoice_no);
        }

        public Taxes getWithHeldTaxes(){
            return get(Taxes.class, "with_held_taxes");
        }
        public void setWithHeldTaxes(Taxes with_held_taxes){
            set("with_held_taxes",with_held_taxes);
        }

        public Price getCollectorCharges(){
            return get(Price.class, "collector_charges");
        }
        public void setCollectorCharges(Price collector_charges){
            set("collector_charges",collector_charges);
        }

        public OrderReconStatus getOrderReconStatus(){
            return getEnum(OrderReconStatus.class, "order_recon_status",OrderReconStatus.convertor);
        }
        public void setOrderReconStatus(OrderReconStatus order_recon_status){
            setEnum("order_recon_status",order_recon_status,OrderReconStatus.convertor);
        }

        public enum OrderReconStatus {
            PROVISIONAL,
            FINALE,
            DEEMED_SETTLED;

            public static EnumConvertor<OrderReconStatus> convertor = new OrdinalBasedEnumConvertor<>(OrderReconStatus.class);

        }
        public static class Payer extends BecknObject {
            public String getName(){
                return get("name");
            }
            public void setName(String name){
                set("name",name);
            }
            public String getAddress(){
                return get("address");
            }
            public void setAddress(String address){
                set("address",address);
            }
            public String getAccountNo(){
                return get("account_no");
            }
            public void setAccountNo(String account_no){
                set("account_no",account_no);
            }
            public String getBankCode(){
                return get("bank_code");
            }
            public void setBankCode(String bank_code){
                set("bank_code",bank_code);
            }
            public String getVpa(){
                return get("vpa");
            }
            public void setVpa(String vpa){
                set("vpa",vpa);
            }


        }
        public Payer getPayer(){
            return get(Payer.class, "payer");
        }
        public void setPayer(Payer payer){
            set("payer",payer);
        }


        public enum SettlementReasonCode {
            ORDER_PAYMENT,
            PART_REFUND,
            REFUND,
            CORRECTION,
            BUYER_APP_FEE_PAYMENT,
            BUYER_APP_FEE_GST_PAYMENT;

            public static EnumConvertor<SettlementReasonCode> convertor = new OrdinalBasedEnumConvertor<>(SettlementReasonCode.class);
        }

        public SettlementReasonCode getSettlementReasonCode(){
            return getEnum(SettlementReasonCode.class, "settlement_reason_code",SettlementReasonCode.convertor);
        }
        public void setSettlementReasonCode(SettlementReasonCode settlement_reason_code){
            setEnum("settlement_reason_code",settlement_reason_code,SettlementReasonCode.convertor);
        }

        public String getTransactionId(){
            return get("transaction_id");
        }
        public void setTransactionId(String transaction_id){
            set("transaction_id",transaction_id);
        }



        public enum ReconStatus {
            PAID,
            OVER_PAID,
            UNDER_PAID,
            NOT_PAID,
        }

        public ReconStatus getReconStatus(){
            return getEnum(ReconStatus.class, "recon_status" , new OrdinalBasedEnumConvertor<>(ReconStatus.class));
        }
        public void setReconStatus(ReconStatus recon_status){
            setEnum("recon_status",recon_status, new OrdinalBasedEnumConvertor<>(ReconStatus.class));
        }

        public Price getDiffAmount(){
            return get(Price.class, "diff_amount");
        }
        public void setDiffAmount(Price diff_amount){
            set("diff_amount",diff_amount);
        }


        public ReconStatus getCounterpartyReconStatus(){
            return getEnum(ReconStatus.class, "counterparty_recon_status");
        }
        public void setCounterpartyReconStatus(ReconStatus counterparty_recon_status){
            setEnum("counterparty_recon_status",counterparty_recon_status);
        }
        
        public Price getCounterpartyDiffAmount(){
            return get(Price.class, "counterparty_diff_amount");
        }
        public void setCounterpartyDiffAmount(Price counterparty_diff_amount){
            set("counterparty_diff_amount",counterparty_diff_amount);
        }

        public Descriptor getMessage(){
            return get(Descriptor.class, "message");
        }
        public void setMessage(Descriptor message){
            set("message",message);
        }


    }



    public static class Taxes extends BecknObjects<Tax> {
        public Taxes() {
        }

        public Taxes(JSONArray value) {
            super(value);
        }

        public Taxes(String payload) {
            super(payload);
        }
    }

    public static class Tax extends BecknObject {
        public Tax() {
        }

        public Tax(String payload) {
            super(payload);
        }

        public Tax(JSONObject object) {
            super(object);
        }

        public Heading getHeading(){
            return getEnum(Heading.class, "heading");
        }
        public void setHeading(Heading heading){
            setEnum("heading",heading);
        }


        public enum Heading {
            GST,
            INCOME_TAX,
        }
        public Price getAmount(){
            return get(Price.class, "amount");
        }
        public void setAmount(Price price){
            set("amount",price);
        }
    }
}
