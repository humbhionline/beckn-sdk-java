package in.succinct.beckn;

import com.venky.core.date.DateUtils;
import com.venky.core.util.MultiException;
import com.venky.core.util.ObjectHolder;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class BecknObject extends BecknAware<JSONObject> {
    public BecknObject(){
        this(new JSONObject());
    }
    public BecknObject(String payload){
        super(payload);
        if (!(getInner() instanceof JSONObject)){
            throw new RuntimeException("Payload not correct format");
        }
    }
    public BecknObject(JSONObject object){
        super(object);
    }

    private final Map<String,ObjectHolder<BecknAware>> attributeMap = new HashMap<>();
    public <T extends BecknAware> T get(Class<T> clazz,String name){
        return get(clazz,name,false);
    }
    public <T extends BecknAware> T get(Class<T> clazz,String name,boolean createIfAbsent){
        if (attributeMap.containsKey(name)){
            return (T)attributeMap.get(name).get();
        }
        JSONObject inner = getInner();
        JSONAware clazzInner = (JSONAware) inner.get(name);
        try {
            T t = null ;
            if (clazzInner != null || createIfAbsent){
                t = clazz.getConstructor().newInstance();
                if (clazzInner != null) {
                    t.setInner(clazzInner);
                }else {
                    clazzInner = t.getInner();
                    inner.put(name,clazzInner);
                }
            }
            attributeMap.put(name,new ObjectHolder<>(t));
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
        if (value == null){
            rm(key);
            return;
        }

        set(key,value.getInner());
        attributeMap.put(key,new ObjectHolder<>(value));
    }
    public void set(String key, JSONAware value){
        if (value == null) {
            rm(key);
            return;
        }

        getInner().put(key,value);
        attributeMap.remove(key);
    }
    public void set(String key, String value){
        if (value == null) {
            rm(key);
            return;
        }

        getInner().put(key,value);
    }
    public void set(String key, boolean value){
        getInner().put(key,value);
    }

    public void rm(String  key){
        getInner().remove(key);
        attributeMap.remove(key);
    }

    public static DateFormat TIMESTAMP_FORMAT_WITH_MILLS =  new SimpleDateFormat(DateUtils.ISO_8601_24H_FULL_FORMAT);
    public static DateFormat TIMESTAMP_FORMAT_WO_MILLIS = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");

    public static DateFormat TIMESTAMP_FORMAT =  TIMESTAMP_FORMAT_WITH_MILLS;

    public static final DateFormat[] TIMESTAMP_FORMATS = new DateFormat[] {TIMESTAMP_FORMAT_WITH_MILLS, TIMESTAMP_FORMAT_WO_MILLIS};

    public static final DateFormat DATE_FORMAT = new SimpleDateFormat(DateUtils.ISO_DATE_FORMAT_STR);
    static {
        for (DateFormat t : TIMESTAMP_FORMATS ){
            t.setTimeZone(TimeZone.getTimeZone("UTC"));
        }
    }


    public void set(String key, Date date, DateFormat format){
        if (date == null) {
            rm(key);
            return;
        }
        set(key,format.format(date));
    }
    public void set(String key, Double value){
        if (value == null) {
            rm(key);
            return;
        }
        getInner().put(key,value);
    }
    public void set(String key, int value){
        getInner().put(key,value);
    }
    public Date getTimestamp(String key){
        MultiException multiException = new MultiException();
        for (DateFormat f : TIMESTAMP_FORMATS){
            try {
                return getDate(key,f);
            }catch (Exception ex){
                multiException.add(ex);
            }
        }
        throw multiException;
    }
    public Date getDate(String key){
        return getDate(key,DATE_FORMAT);
    }
    private Date getDate(String key, DateFormat format){
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
        return Double.valueOf(String.valueOf(get(key,0.0D)));
    }
    public int getInteger(String key){
        return Integer.valueOf(String.valueOf(get(key,0)));
    }
    public long getLong(String key){
        return Long.valueOf(String.valueOf(get(key,0L)));
    }
    public boolean getBoolean(String key){
        return Boolean.valueOf(String.valueOf(get(key,false)));
    }


    public <T extends BecknObject> T cast(Class<T> clazz){
        try {
            T t = clazz.getConstructor().newInstance();
            t.setInner(this.getInner());
            return t;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
