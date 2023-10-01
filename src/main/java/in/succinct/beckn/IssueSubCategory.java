package in.succinct.beckn;

import com.venky.core.util.Bucket;
import in.succinct.json.JSONAwareWrapper.EnumConvertor;

import java.util.HashMap;
import java.util.Map;

public enum IssueSubCategory {
    ITEM_MISSING,
    ITEM_QUANTITY_MISMATCH,
    ITEM_MISMATCH,
    ITEM_QUALITY,
    ITEM_EXPIRED,

    FULFILLMENT_WRONG_ADDRESS,
    FULFILLMENT_NOT_DELIVERED,
    FULFILLMENT_DELIVERED_LATE,
    FULFILLMENT_PACKAGING_BAD,
    FULFILLMENT_BUYER_NOT_FOUND,
    FULFILLMENT_SELLER_NOT_FOUND,
    FULFILLMENT_PACKAGING_INFO_MISMATCH,
    FULFILLMENT_INCORRECTLY_MARKED_DELIVERED,

    PAYMENT_REFUND_NOT_RECEIVED,PAYMENT_UNDER_PAID,PAYMENT_OVER_PAID,PAYMENT_NOT_PAID;

    public IssueCategory getCategory(){
        return IssueCategory.valueOf(name().split("_")[0]);
    }
    static final Map<IssueCategory,String> prefix = new HashMap<>(){{
        put(IssueCategory.ITEM,"ITM");
        put(IssueCategory.FULFILLMENT,"FLM");
        put(IssueCategory.PAYMENT,"PMT");
    }};
    static final Map<IssueSubCategory,String> tos = new HashMap<>(){{
        Map<IssueCategory, Bucket> counts = new HashMap<>();

        for (IssueSubCategory sc : IssueSubCategory.values()){
            Bucket b = counts.get(sc.getCategory());
            if (b == null){
                b = new Bucket();
                counts.put(sc.getCategory(),b);
                }
            b.increment();
            put(sc,String.format("%s%02d",sc.getPrefix(),b.intValue()));

        }
    }};
    static final Map<String,IssueSubCategory> tov = new HashMap<>(){{
       tos.forEach((sc,s)-> put(s,sc));
    }};

    public String getPrefix(){
        return prefix.get(getCategory());
    }



    public static final EnumConvertor<IssueSubCategory> convertor = new EnumConvertor<>(IssueSubCategory.class){
        @Override
        public IssueSubCategory valueOf(String enumRepresentation) {
            return enumRepresentation == null ? null : tov.get(enumRepresentation);
        }

        @Override
        public String toString(IssueSubCategory value) {
            return value == null ? null : tos.get(value);
        }
    };
}

