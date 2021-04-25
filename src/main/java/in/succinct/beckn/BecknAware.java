package in.succinct.beckn;

import org.bouncycastle.crypto.digests.Blake2bDigest;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;
import org.json.simple.JSONAware;
import org.json.simple.JSONValue;

import java.io.Serializable;
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
            payload = value.toJSONString();
        }
        return payload;
    }

    public String hash(){
        return generateBlakeHash(toString());
    }

    public static String generateBlakeHash(String req) {
        Blake2bDigest blake2bDigest = new Blake2bDigest(512);
        byte[] test = req.getBytes();
        blake2bDigest.update(test, 0, test.length);
        byte[] hash = new byte[blake2bDigest.getDigestSize()];
        blake2bDigest.doFinal(hash, 0);
        String hex = Hex.toHexString(hash);
        String bs64 = Base64.getUrlEncoder().encodeToString(hex.getBytes());
        return bs64;
    }


}
