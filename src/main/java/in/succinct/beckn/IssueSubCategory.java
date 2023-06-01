package in.succinct.beckn;

import in.succinct.beckn.BecknObjectBase.EnumConvertor;

public enum IssueSubCategory {
    SC_01,
    SC_02,
    SC_03;


    static final class IssueSubCategoryConvertor extends EnumConvertor<IssueSubCategory> {
        @Override
        public String toString(IssueSubCategory value) {
            return super.toString(value).substring(3);
        }

        @Override
        public IssueSubCategory valueOf(String enumRepresentation) {
            return super.valueOf("SC_"+enumRepresentation);
        }
    }
    public static final IssueSubCategoryConvertor convertor = new IssueSubCategoryConvertor();
}

