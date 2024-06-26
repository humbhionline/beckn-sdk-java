package in.succinct.beckn;

import in.succinct.json.JSONAwareWrapper.EnumConvertor;

public enum PaymentType {
    ON_ORDER ,
    ON_FULFILLMENT,
    PRE_FULFILLMENT,
    POST_FULFILLMENT;

    public static class PaymentTypeConverter extends EnumConvertor<PaymentType> {}
}