package in.succinct.beckn;

import java.util.ArrayList;
import java.util.List;

public class BecknException extends RuntimeException {

    private String errorCode;

    public BecknException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public static String makeSentence(String camel) {


        List<Integer> upper = new ArrayList<Integer>();
        byte[] bytes = camel.getBytes();
        for (int i = 0; i < bytes.length; i++) {
            byte b = bytes[i];
            if (b > 65 && b < 90) {
                upper.add(i);
            }
        }

        StringBuilder b = new StringBuilder(camel);
        for (int i = upper.size() - 1; i >= 0; i--) {
            Integer index = upper.get(i);
            if (index != 0)
                b.replace(index, index + 1, String.valueOf(Character.toLowerCase(b.charAt(index))));
            b.insert(index, " ");
        }

        return b.toString();
    }


}
