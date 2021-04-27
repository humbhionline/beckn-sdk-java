package in.succinct.beckn;

import com.venky.core.date.DateUtils;
import com.venky.core.util.ObjectHolder;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BecknObject extends BecknAware<JSONObject> {
    public BecknObject(){
        super(new JSONObject());
    }
    public BecknObject(String payload){
        super(payload);
        if (!(getInner() instanceof JSONObject)){
            throw new RuntimeException("Payload not correct format");
        }
    }

    private Map<String,ObjectHolder<BecknAware>> attributeMap = new HashMap<>();

    public <T extends BecknAware> T get(Class<T> clazz,String name){
        JSONObject inner = getInner();
        JSONAware clazzInner = (JSONAware) inner.get(name);
        try {
            T t = null;
            if (clazzInner != null){
                t = clazz.getConstructor().newInstance();
                t.setInner(clazzInner);
            }
            return t;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public <T> T get(String key){
        return (T)getInner().get(key) ;
    }

    public <T> T get(String key, T returnIfNull){
        T ret = get(key);
        return ret != null ? ret : returnIfNull;
    }

    public <T extends BecknAware> void set(String key, T value){
        set(key,value.getInner());
    }
    public void set(String key, JSONAware value){
        getInner().put(key,value);
    }
    public void set(String key, String value){
        getInner().put(key,value);
    }
    public void set(String key, boolean value){
        getInner().put(key,value);
    }

    public static final DateFormat TIMESTAMP_FORMAT = DateUtils.getFormat("dd/MM/yyyy HH:mm:ssXXX");
    public static final DateFormat DATE_FORMAT = DateUtils.getFormat("dd/MM/yyyy");


    public void set(String key, Date date, DateFormat format){
        set(key,format.format(date));
    }
    public void set(String key, Double value){
        set(key,value.toString());
    }
    public Date getDate(String key, DateFormat format){
        String value = get(key);
        if (value == null){
            return null;
        }

        try {
            return format.parse(value);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public double getDouble(String key){
        return Double.valueOf(get(key,0.0D));
    }
    public int getInteger(String key){
        return Integer.valueOf(get(key,0));
    }
    public long getLong(String key){
        return Long.valueOf(get(key,0L));
    }
    public boolean getBoolean(String key){
        return Boolean.valueOf(get(key,false));
    }


}
