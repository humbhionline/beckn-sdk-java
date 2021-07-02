package in.succinct.beckn;

import org.bouncycastle.jcajce.provider.digest.Blake2b.Blake2b512;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;
import org.json.simple.JSONAware;
import org.json.simple.JSONValue;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.Security;
import java.util.Base64;

public abstract class BecknAware<T extends JSONAware> implements Serializable {
    static {
        if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
            Security.addProvider(new BouncyCastleProvider());
        }
    }

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
        MessageDigest digest = new Blake2b512();
        digest.reset();
        digest.update(req.getBytes(StandardCharsets.UTF_8));
        byte[] hash = digest.digest();
        String hex = Hex.toHexString(hash);
        String bs64 = Base64.getEncoder().encodeToString(hex.getBytes()) ;
        return bs64;
    }


}
