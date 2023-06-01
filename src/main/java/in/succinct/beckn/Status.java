package in.succinct.beckn;

import in.succinct.beckn.BecknObjectBase.EnumConvertor;

public enum Status {
    OPEN,CLOSED;


    public static final EnumConvertor<Status>  convertor = new EnumConvertor<> (Status.class);
}
