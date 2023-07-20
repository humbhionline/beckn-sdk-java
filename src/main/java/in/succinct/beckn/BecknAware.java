package in.succinct.beckn;

import com.venky.core.security.Crypt;
import in.succinct.json.JSONAwareWrapper;
import org.json.simple.JSONAware;
import org.json.simple.JSONValue;

import java.io.Serializable;

public abstract class BecknAware<T extends JSONAware> extends JSONAwareWrapper<T> {


    protected BecknAware(T value) {
        super(value);
    }

    protected BecknAware(String payload) {
        super(payload);
    }


    public String hash(){
        return generateBlakeHash(toString());
    }

    public static String generateBlakeHash(String req) {
        return Crypt.getInstance().toBase64(Crypt.getInstance().digest("BLAKE2B-512",req));
    }


}
