package in.succinct.beckn;

public class SellerException {
    private SellerException(){

    }
    public static class InvalidRequestError extends BecknException {
        public InvalidRequestError() {
            this(makeSentence("InvalidRequestError"));
        }

        public InvalidRequestError(String message){
            super("30000", message);
        }
    }

    public static class ProviderNotFound extends BecknException {
        public ProviderNotFound() {
            super("30001", BecknException.makeSentence("ProviderNotFound"));
        }
    }

    public static class ProviderLocationNotFound extends BecknException {
        public ProviderLocationNotFound() {
            super("30002", BecknException.makeSentence("ProviderLocationNotFound"));
        }
    }

    public static class ProviderCategoryNotFound extends BecknException {
        public ProviderCategoryNotFound() {
            super("30003", makeSentence("ProviderCategoryNotFound"));
        }
    }


    public static class ItemNotFound extends BecknException {
        public ItemNotFound() {
            super("30004", BecknException.makeSentence("ItemNotFound"));
        }
    }

    public static class InvalidReturnRequest extends BecknException {
        public InvalidReturnRequest(String message){
            super("30005", message);
        }
        public InvalidReturnRequest() {
            this(makeSentence("InvalidReturnRequest"));
        }
    }

    /*

    public static class CategoryNotFound extends BecknException {
        public CategoryNotFound() {
            super("30005", makeSentence("CategoryNotFound"));
        }
    }

     */


    public static class OfferInvalid extends BecknException {
        public OfferInvalid() {
            super("30006", makeSentence("Offer code invalid"));
        }
    }

    public static class OfferFulfillmentError extends BecknException {
        public OfferFulfillmentError() {
            super("30007", "Offer fulfillment error");
        }
    }

    public static class LocationServiceabilityError extends BecknException {
        public LocationServiceabilityError(String code) {
            super(code, makeSentence("LocationServiceabilityError"));
        }
    }

    public static class PickupLocationServiceabilityError extends LocationServiceabilityError {
        public PickupLocationServiceabilityError() {
            super("30008");
        }
    }

    public static class DropoffLocationServiceabilityError extends LocationServiceabilityError {
        public DropoffLocationServiceabilityError() {
            super("30009");
        }
    }


    public static class DistanceServiceabilityError extends LocationServiceabilityError {
        public DistanceServiceabilityError() {
            super("30010");
        }
    }

    public static class DeliveryPartnerNotAvailable extends BecknException {
        public DeliveryPartnerNotAvailable() {
            super("30011", makeSentence("OrderServiceabilityError"));
        }
    }

    public static class InvalidCancellationReason extends BecknException {
        public InvalidCancellationReason() {
            super("30012", makeSentence("InvalidCancellationReason"));
        }
    }

    public static class InvalidFulfillmentTAT extends BecknException {
        public InvalidFulfillmentTAT(){
            super("30013",makeSentence("InvalidFulfillmentTAT"));
        } 
    }

    public static class CancellationUnexceptable extends BecknException {
        public CancellationUnexceptable(){
            super("30014",makeSentence("CancellationUnexceptable"));
        } 
    }



    public static class InvalidRatingValue extends BecknException {
        public InvalidRatingValue() {
            super("30015", makeSentence("InvalidRatingValue"));
        }
    }

    public static class MinOrderValueError extends BecknException {
        public MinOrderValueError(){
            super("30016",makeSentence("MinOrderValueError"));
        }
    }


    public static class MerchantUnavailable extends BecknException {
        public MerchantUnavailable() {
            super("30017", makeSentence("MerchantUnavailable"));
        }
    }

    public static class InvalidOrder extends BecknException {
        public InvalidOrder(){
            this(makeSentence("InvalidOrder"));
        }
        public InvalidOrder(String message) {
            super("30018", message);
        }
    }

    public static class OrderConfirmError extends BecknException {
        public OrderConfirmError() {
            super("30019", makeSentence("OrderConfirmError"));
        }
    }

    public static class OrderConfirmFailure extends BecknException {
        public OrderConfirmFailure() {
            this(makeSentence("OrderConfirmFailure"));
        }
        public OrderConfirmFailure(String message){
            super("30020",message);
        }
    }
    
    public static class MetchantInactive extends BecknException {
        public MetchantInactive(){
            super("30021",makeSentence("MetchantInactive"));
        } 
    }
    
    public static class StaleRequest extends BecknException {
        public StaleRequest(){
            super("30022",makeSentence("StaleRequest"));
        } 
    }


    public static class GenericBusinessError extends BecknException {
        public GenericBusinessError() {
            this(makeSentence("GenericBusinessError"));
        }
        public GenericBusinessError(String message){
            super("40000",message);
        }
    }

    public static class ActionNotApplicable extends BecknException {
        public ActionNotApplicable() {
            this(makeSentence("ActionNotApplicable"));
        }
        public ActionNotApplicable(String message){
            super("40001", message);
        }
    }

    public static class ItemQuantityUnavailable extends BecknException {
        public ItemQuantityUnavailable() {
            super("40002", makeSentence("ItemQuantityUnavailable"));
        }
    }

    public static class QuoteUnavailable extends BecknException {
        public QuoteUnavailable() {
            super("40003", makeSentence("QuoteUnavailable"));
        }
    }

    public static class PaymentNotSupported extends BecknException {
        public PaymentNotSupported() {
            super("40004", makeSentence("PaymentNotSupported"));
        }
    }

    public static class TrackingNotSupported extends BecknException {
        public TrackingNotSupported() {
            super("40005", makeSentence("TrackingNotSupported"));
        }
    }

    public static class FulfillmentAgentUnavailable extends BecknException {
        public FulfillmentAgentUnavailable() {
            super("40006", makeSentence("FulfillmentAgentUnavailable"));
        }
    }


    public static class GenericPolicyError extends BecknException {
        public GenericPolicyError() {
            super("50000", makeSentence("GenericPolicyError"));
        }
    }

    public static class CancellationNotPossible extends BecknException {
        public CancellationNotPossible() {
            this(makeSentence("CancellationNotPossible"));
        }
        public CancellationNotPossible(String message) {
            super("50001", message);
        }
    }

    public static class UpdationNotPossible extends BecknException {
        public UpdationNotPossible() {
            this( makeSentence("UpdationNotPossible"));
        }
        public UpdationNotPossible(String message) {
            super("50002", message);
        }

    }

    public static class UnsupportedRatingCategory extends BecknException {
        public UnsupportedRatingCategory() {
            super("50003", makeSentence("UnsupportedRatingCategory"));
        }
    }

    public static class SupportUnavailable extends BecknException {
        public SupportUnavailable() {
            super("50004", makeSentence("SupportUnavailable"));
        }
    }

    public static class TermsAndConditionsNotAcceptable extends BecknException {
        public TermsAndConditionsNotAcceptable(){
            super("50005",makeSentence("TermsAndConditionsNotAcceptable"));
        } 
    }
    
    public static class OrderTerminated extends BecknException {
        public OrderTerminated(){
            super("50006",makeSentence("OrderTerminated"));
        } 
    }
    
    public static class InvalidSignature extends BecknException {
        public InvalidSignature(){
            super("30016",makeSentence("InvalidSignature")); //TODO To be fixed after ondc creates the code.
        } 
    }

}
