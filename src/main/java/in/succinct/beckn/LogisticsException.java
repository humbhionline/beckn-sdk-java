package in.succinct.beckn;

public class LogisticsException {

    public static class LocationServiceabilityError extends BecknException {
        public LocationServiceabilityError(String code) {
            super(code, makeSentence("LocationServiceabilityError"));
        }
    }

    public static class PickupLocationServiceabilityError extends LocationServiceabilityError {
        public PickupLocationServiceabilityError() {
            super("60001");
        }
    }

    public static class DropoffLocationServiceabilityError extends LocationServiceabilityError {
        public DropoffLocationServiceabilityError() {
            super("60002");
        }
    }


    public static class DistanceServiceabilityError extends LocationServiceabilityError {
        public DistanceServiceabilityError() {
            super("60003");
        }
    }

    public static class DeliveryPartnersNotAvailable extends BecknException {
        public DeliveryPartnersNotAvailable() {
            super("60004", makeSentence("OrderServiceabilityError"));
        }
    }

    public static class InvalidSignature extends BecknException {
        public InvalidSignature() {
            super("60005", makeSentence("InvalidSignature"));
        }
    }

    public static class InvalidRequestError extends BecknException {
        public InvalidRequestError() {
            super("60006", makeSentence("InvalidRequestError"));
        }
    }
    public static class CancellationNotPossible extends BecknException {
        public CancellationNotPossible() {
            super("60007", "Policy Error");
        }
    }

    public static class OrderConfirmError extends BecknException {
        public OrderConfirmError() {
            super("65001", makeSentence("OrderConfirmError"));
        }
    }

}
