package in.succinct.beckn;

import com.venky.core.security.Crypt;
import org.json.simple.JSONAware;
import org.json.simple.JSONValue;

import java.io.Serializable;

public abstract class BecknAware<T extends JSONAware> implements Serializable {

    protected BecknAware(T value){
        if (value == null){
            throw new NullPointerException();
        }
        setInner(value);
    }

    @SuppressWarnings("unchecked")
    protected BecknAware(String payload){
        setPayload(payload);
    }

    @SuppressWarnings("unchecked")
    protected static <T extends JSONAware> T parse(String payload){
        try {
            return (T) JSONValue.parseWithException(payload);
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    @SuppressWarnings("unchecked")
    public void setPayload(String payload){
        this.payload =payload;
        setInner(parse(payload));
    }

    private String payload;
    private T value ;

    public void setInner(T value){
        this.value = value;
    }

    public T getInner(){
        return this.value;
    }

    private BecknAwareCreator objectCreator = new BecknAwareCreator();
    public BecknAwareCreator getObjectCreator(){
        return objectCreator;
    }
    public void setObjectCreator(BecknAwareCreator objectCreator){
        this.objectCreator = objectCreator;
    }

    public static class BecknAwareCreator implements Serializable {
        public <B> B create(Class<B> clazz){
            try {
                B b = clazz.getConstructor().newInstance();
                if (b instanceof BecknAware){
                    ((BecknAware)b).setObjectCreator(BecknAwareCreator.this);
                }
                return b;
            }catch (Exception ex){
                throw new RuntimeException(ex);
            }
        }
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
