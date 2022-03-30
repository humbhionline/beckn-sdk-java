package in.succinct.beckn;

import com.venky.core.security.Crypt;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.json.simple.JSONAware;
import org.json.simple.JSONValue;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

public abstract class BecknAware<T extends JSONAware> implements Serializable {

    protected BecknAware(T value){
       this.value = value;
        if (value == null){
            throw new NullPointerException();
        }
    }

    protected BecknAware(String payload){
        this((T)JSONValue.parse(payload));
        this.payload = payload;

    }

    private String payload;
    private T value ;

    public void setInner(T value){
        this.value = value;
    }

    public T getInner(){
        return this.value;
    }

    @Override
    public String toString() {
        if (payload == null){
            return value.toJSONString();
        }
        return payload;
    }

    public String hash(){
        return generateBlakeHash(toString());
    }

    public static String generateBlakeHash(String req) {
        System.out.println("Blake in :" + req);

        String hash = Crypt.getInstance().toBase64(Crypt.getInstance().digest("BLAKE2B-512",req));

        System.out.println("Blake out :" + hash);

        return hash;
    }


}
