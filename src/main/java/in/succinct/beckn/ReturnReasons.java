package in.succinct.beckn;

import org.json.simple.JSONArray;

public class ReturnReasons extends BecknObjectsWithId<Option>{

    public ReturnReasons() {
        super();
    }

    public ReturnReasons(JSONArray value) {
        super(value);
    }

    public ReturnReasons(String payload) {
        super(payload);
    }

    public enum ReturnReasonCode {
        ITEM_NOT_REQUIRED(false ),
        ITEM_PRICE_TOO_HIGH(false),
        ITEM_DAMAGED(true , IssueSubCategory.ITEM_QUALITY),
        ITEM_QUANTITY_MISMATCH(true,IssueSubCategory.ITEM_QUANTITY_MISMATCH),
        ITEM_MISMATCH(true,IssueSubCategory.ITEM_MISMATCH);



        final boolean refundEligible;
        final IssueSubCategory issueSubCategory;
        ReturnReasonCode(boolean refundEligible){
            this(refundEligible,null);
        }
        ReturnReasonCode(boolean refundEligible , IssueSubCategory issueSubCategory){
            this.refundEligible = refundEligible;
            this.issueSubCategory = issueSubCategory;
        }

        public IssueSubCategory getIssueSubCategory() {
            return issueSubCategory;
        }

        public static final EnumConvertor<ReturnReasonCode> convertor = new OrdinalBasedEnumConvertor<>(ReturnReasonCode.class);



    }

    public enum ReturnRejectReason {
        ITEM_DAMAGED(IssueSubCategory.ITEM_QUALITY),
        ITEM_PACKAGING_DAMAGED(IssueSubCategory.FULFILLMENT_PACKAGING_BAD),
        ITEM_USED(IssueSubCategory.ITEM_QUALITY),
        ITEM_INCOMPLETE(IssueSubCategory.ITEM_QUALITY),
        ITEM_MISMATCH(IssueSubCategory.ITEM_MISMATCH)
        ;

        final IssueSubCategory issueSubCategory;
        ReturnRejectReason(IssueSubCategory issueSubCategory){
            this.issueSubCategory = issueSubCategory;
        }
        EnumConvertor<ReturnRejectReason> convertor = new OrdinalBasedEnumConvertor<>(ReturnRejectReason.class);
    }
}
