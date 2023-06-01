package in.succinct.beckn;

import in.succinct.beckn.BecknObjectBase.EnumConvertor;

public enum IssueCategory {

    ORDER,
    TRANSACTION,
    FULFILLMENT,
    PRODUCT,
    PRODUCT_SERVICE,
    SERVICE,
    PAYMENT;


    public static final EnumConvertor<IssueCategory> convertor = new EnumConvertor<>(IssueCategory.class);


}

