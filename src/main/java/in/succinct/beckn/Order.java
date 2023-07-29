package in.succinct.beckn;

import com.venky.core.util.Bucket;
import in.succinct.beckn.Order.Status.StatusConverter;

import in.succinct.beckn.Payment.PaymentStatus;
import org.json.simple.JSONArray;

import java.util.Date;

public class Order extends BecknObjectWithId {
    public Order() {
        super();
    }
    public Order(String payload){
        super(payload);
    }

    public Provider getProvider(){
        return get(Provider.class,"provider");
    }
    public void setProvider(Provider provider){
        set("provider",provider);
    }

    public Location getProviderLocation(){
        return get(Location.class,"provider_location");
    }

    public void setProviderLocation(Location location){
        set("provider_location",location);
    }

    public Items getItems(){
        return get(Items.class,"items");
    }
    public void setItems(Items  items){
        set("items",items);
    }

    public AddOns getAddOns(){
        return get(AddOns.class, "add_ons");
    }
    public void setAddOns(AddOns add_ons){
        set("add_ons",add_ons);
    }

    public Offers getOffers(){
        return get(Offers.class, "offers");
    }
    public void setOffers(Offers offers){
        set("offers",offers);
    }


    public Billing getBilling(){
        return get(Billing.class,"billing");
    }
    public void setBilling( Billing billing){
        set("billing",billing);
    }

    public Fulfillment getFulfillment(){
        return get(Fulfillment.class,"fulfillment");
    }
    public void setFulfillment(Fulfillment fulfillment){
        set("fulfillment",fulfillment);
    }

    public Fulfillments getFulfillments(){
        return get(Fulfillments.class, "fulfillments");
    }
    public void setFulfillments(Fulfillments fulfillments){
        set("fulfillments",fulfillments);
    }

    public Quote getQuote(){
        return get(Quote.class,"quote");
    }
    public void setQuote(Quote quote){
        set("quote",quote);
    }

    public Payment getPayment(){
        return get(Payment.class,"payment");
    }
    public void setPayment(Payment payment){
        set("payment",payment);
    }

    public Date getCreatedAt(){
        return getTimestamp("created_at");
    }
    public Date getUpdatedAt(){
        return getTimestamp("updated_at");
    }
    public void setCreatedAt(Date date){
        set("created_at",date,TIMESTAMP_FORMAT);
    }
    public void setUpdatedAt(Date date){
        set("updated_at",date,TIMESTAMP_FORMAT);
    }
    public void setState(Status state){
        setEnum("state", state, new StatusConverter());
    }
    public Status getState(){
        return getEnum(Status.class, "state", new StatusConverter());
    }



    public Documents getDocuments(){
        return get(Documents.class,"documents");
    }
    public void setDocuments(Documents documents){
        set("documents",documents);
    }

    public boolean isExtendedAttributesDisplayed(){
        return true;
    }

    public Cancellation getCancellation(){
        return extendedAttributes.get(Cancellation.class, "cancellation");
    }
    public void setCancellation(Cancellation cancellation){
        extendedAttributes.set("cancellation",cancellation);
    }

    public Tags getTags(){
        return get(Tags.class, "tags");
    }
    public void setTags(Tags tags){
        set("tags",tags);
    }


    public enum Status {
        Awaiting_Agent_Acceptance,
        Reaching_Pickup_Location,
        Reached_Pickup_Location,

        Out_for_delivery,
        Created,
        Accepted,
        In_progress,
        Completed,
        Cancelled;

        public static class StatusConverter extends EnumConvertor<Status> {

        }
    }
    public static class Orders extends BecknObjectsWithId<Order> {
        public Orders() {
        }

        public Orders(JSONArray array) {
            super(array);
        }

        public Orders(String payload) {
            super(payload);
        }
    }
    // Fields added for Reconcilliation and Settlements
    public String getInvoiceNo(){
        return extendedAttributes.get("invoice_no");
    }
    public void setInvoiceNo(String invoice_no){
        extendedAttributes.set("invoice_no",invoice_no);
    }

    public Amount getIncomeTax(){
        return extendedAttributes.get(Price.class, "income_tax");
    }
    public void setIncomeTax(Amount income_tax){
        extendedAttributes.set("income_tax",income_tax);
    }

    public Amount getGst(){
        return extendedAttributes.get(Amount.class, "gst");
    }
    public void setGst(Amount gst){
        extendedAttributes.set("gst",gst);
    }

    public Amount getIncomeTaxWithheld(){
        return extendedAttributes.get(Amount.class, "income_tax_withheld");
    }
    public void setIncomeTaxWithheld(Amount income_tax_withheld){
        extendedAttributes.set("income_tax_withheld",income_tax_withheld);
    }

    public Amount getGstWithheld(){
        return extendedAttributes.get(Amount.class, "gst_withheld");
    }
    public void setGstWithheld(Amount gst_withheld){
        extendedAttributes.set("gst_withheld",gst_withheld);
    }

    public OrderReconStatus getOrderReconStatus(){
        return extendedAttributes.getEnum(OrderReconStatus.class, "order_recon_status");
    }
    public void setOrderReconStatus(OrderReconStatus order_recon_status){
        extendedAttributes.setEnum("order_recon_status",order_recon_status);
    }

    public SettlementCorrection getSettlementCorrection(){
        return extendedAttributes.get(SettlementCorrection.class, "settlement_correction");
    }
    public void setSettlementCorrection(SettlementCorrection settlement_correction){
        extendedAttributes.set("settlement_correction",settlement_correction);
    }


    public String getCollectorSubscriberId(){
        return extendedAttributes.get("collector_subscriber_id");
    }
    public void setCollectorSubscriberId(String collector_subscriber_id){
        extendedAttributes.set("collector_subscriber_id",collector_subscriber_id);
    }

    public String getReceiverSubscriberId(){
        return extendedAttributes.get("receiver_subscriber_id");
    }
    public void setReceiverSubscriberId(String receiver_subscriber_id){
        extendedAttributes.set("receiver_subscriber_id",receiver_subscriber_id);
    }


    public Payer getPayer(){
        return extendedAttributes.get(Payer.class, "payer");
    }
    public void setPayer(Payer payer){
        extendedAttributes.set("payer",payer);
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
        return extendedAttributes.getEnum(SettlementReasonCode.class, "settlement_reason_code",SettlementReasonCode.convertor);
    }
    public void setSettlementReasonCode(SettlementReasonCode settlement_reason_code){
        extendedAttributes.setEnum("settlement_reason_code",settlement_reason_code,SettlementReasonCode.convertor);
    }

    public String getCollectionTransactionId(){
        return extendedAttributes.get("collection_transaction_id");
    }
    public void setCollectionTransactionId(String transaction_id){
        extendedAttributes.set("collection_transaction_id",transaction_id);
    }

    public String getSettlementId(){
        return extendedAttributes.get("settlement_id");
    }
    public void setSettlementId(String settlement_id){
        extendedAttributes.set("settlement_id",settlement_id);
    }
    
    public String getSettlementReference(){
        return extendedAttributes.get("settlement_reference");
    }
    public void setSettlementReference(String settlement_reference){
        extendedAttributes.set("settlement_reference",settlement_reference);
    }


    public enum ReconStatus {
        PAID,
        OVER_PAID,
        UNDER_PAID,
        NOT_PAID;

        public static EnumConvertor<ReconStatus> convertor = new OrdinalBasedEnumConvertor<>(ReconStatus .class);

    }

    public ReconStatus getReconStatus(){
        return extendedAttributes.getEnum(ReconStatus.class, "recon_status" , ReconStatus.convertor);
    }
    public void setReconStatus(ReconStatus recon_status){
        extendedAttributes.setEnum("recon_status",recon_status, ReconStatus.convertor);
    }

    public Double getDiffAmount(){
        return extendedAttributes.getDouble("diff_amount", null);
    }
    public void setDiffAmount(Double diff_amount){
        extendedAttributes.set("diff_amount",diff_amount);
    }


    public ReconStatus getCounterPartyReconStatus(){
        return extendedAttributes.getEnum(ReconStatus.class, "counter_party_recon_status");
    }
    public void setCounterPartyReconStatus(ReconStatus counter_party_recon_status){
        extendedAttributes.setEnum("counter_party_recon_status",counter_party_recon_status);
    }

    public Double getCounterPartyDiffAmount(){
        return extendedAttributes.getDouble("counter_party_diff_amount",null);
    }
    public void setCounterPartyDiffAmount(Double counter_party_diff_amount){
        extendedAttributes.set("counter_party_diff_amount",counter_party_diff_amount);
    }

    public Descriptor getReconMessage(){
        return extendedAttributes.get(Descriptor.class, "message");
    }
    public void setReconMessage(Descriptor recon_message){
        extendedAttributes.set("message",recon_message);
    }


    public enum OrderReconStatus {
        PROVISIONAL,
        FINALE,
        DEEMED_SETTLED;

        public static EnumConvertor<OrderReconStatus> convertor = new OrdinalBasedEnumConvertor<>(OrderReconStatus.class);

    }

}
