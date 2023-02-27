package in.succinct.beckn;

import com.venky.core.security.Crypt;
import in.succinct.beckn.BecknObject.BecknObjectCreator;
import org.json.simple.JSONAware;
import org.json.simple.JSONValue;

import java.io.Serializable;

public abstract class BecknAware<T extends JSONAware> implements Serializable {

    protected BecknAware(T value){
        this.value = value;
        if (value == null){
            throw new NullPointerException();
        }
    }

    @SuppressWarnings("unchecked")
    protected BecknAware(String payload){
        this((T)parse(payload));
        this.payload = payload;
    }

    @SuppressWarnings("unchecked")
    protected static <T extends JSONAware> T parse(String payload){
        try {
            return (T) JSONValue.parseWithException(payload);
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    private String payload;
    private T value ;

    public void setInner(T value){
        this.value = value;
    }

    public T getInner(){
        return this.value;
    }

    private BecknObjectCreator objectCreator = new BecknObjectCreator();
    public BecknObjectCreator getObjectCreator(){
        return objectCreator;
    }
    public void setObjectCreator(BecknObjectCreator objectCreator){
        this.objectCreator = objectCreator;
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
        return Crypt.getInstance().toBase64(Crypt.getInstance().digest("BLAKE2B-512",req));
    }


}
