package in.succinct.beckn;

import in.succinct.beckn.BecknObjectBase.EnumConvertor;

public enum IssueCategory {

    ORDER,
    ITEM,
    FULFILLMENT,
    AGENT,
    PAYMENT;


    public static final EnumConvertor<IssueCategory> convertor = new EnumConvertor<>(IssueCategory.class);


}

