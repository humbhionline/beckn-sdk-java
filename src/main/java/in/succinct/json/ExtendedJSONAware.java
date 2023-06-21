package in.succinct.json;

import com.venky.core.security.Crypt;
import org.json.simple.JSONAware;
import org.json.simple.JSONValue;

import java.io.Serializable;

public abstract class ExtendedJSONAware<T extends JSONAware> implements Serializable {

    protected ExtendedJSONAware(T value){
        if (value == null){
            throw new NullPointerException();
        }
        setInner(value);
    }

    @SuppressWarnings("unchecked")
    protected ExtendedJSONAware(String payload){
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

    private ExtendedJSONAwareCreator objectCreator = new ExtendedJSONAwareCreator();
    public ExtendedJSONAwareCreator getObjectCreator(){
        return objectCreator;
    }
    public void setObjectCreator(ExtendedJSONAwareCreator objectCreator){
        this.objectCreator = objectCreator;
    }

    public static class ExtendedJSONAwareCreator implements Serializable {
        public <B> B create(Class<B> clazz){
            try {
                B b = clazz.getConstructor().newInstance();
                if (b instanceof ExtendedJSONAware){
                    ((ExtendedJSONAware)b).setObjectCreator(ExtendedJSONAwareCreator.this);
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
