package in.succinct.beckn;

import com.venky.core.date.DateUtils;
import com.venky.core.date.Time;
import com.venky.core.util.MultiException;
import com.venky.core.util.ObjectHolder;
import com.venky.core.util.ObjectUtil;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
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

    public <T extends Enum<T>> void setEnum(String key, T value) {
        setEnum(key,value,null);
    }
    public <T extends Enum<T>> void setEnum(String key, T value , EnumConvertor<T> convertor){
        set(key,convertor == null ? ( value == null ? null : value.name() ) : convertor.toString(value));
    }

    public <T extends Enum<T>> T getEnum(Class<T> enumClass, String key){
        return getEnum(enumClass, key, null);
    }
    public <T extends Enum<T>> T getEnum(Class<T> enumClass, String key, EnumConvertor<T> convertor){
        String value = get(key);
        return convertor == null ? (value == null ? null :  Enum.valueOf(enumClass,value)) : convertor.valueOf(value);
    }
    public static abstract class EnumConvertor<T extends Enum<T>> {
        @SuppressWarnings("unchecked")
        public Class<T> getEnumClass(){
            ParameterizedType pt = (ParameterizedType)this.getClass().getGenericSuperclass();
            return (Class<T>) pt.getActualTypeArguments()[0];
        }

        public T valueOf(String enumRepresentation) {
            return enumRepresentation == null ? null : Enum.valueOf(getEnumClass(),enumRepresentation.replace('-','_'));
        }
        public String toString(T value){
            return value == null ? null : value.toString().replace('_','-');
        }
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
    public static final DateFormat TIME_FORMAT = new SimpleDateFormat(DateUtils.APP_TIME_FORMAT_STR);

    static {
        for (DateFormat t : TIMESTAMP_FORMATS ){
            t.setTimeZone(TimeZone.getTimeZone("UTC"));
        }
        TIME_FORMAT.setTimeZone(TimeZone.getTimeZone("UTC"));
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
    public void set(String key, long value){
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
    public Date getTime(String key){
        return getDate(key,TIME_FORMAT);
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
        return Double.parseDouble(String.valueOf(get(key,0.0D)));
    }
    public int getInteger(String key){
        return Integer.parseInt(String.valueOf(get(key,0)));
    }
    public long getLong(String key){
        return Long.parseLong(String.valueOf(get(key,0L)));
    }
    public boolean getBoolean(String key){
        return Boolean.parseBoolean(String.valueOf(get(key,false)));
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

    public <T extends  BecknObject> void load(T from){
        if (!hasCommonAncestor(this,from)){
            throw new IllegalArgumentException("Incompatible type of the parameter");
        }

        Class<? extends BecknObject> otherClass = from.getClass();

        Map<String,Method> selfSetters = new HashMap<>();
        Map<String,Method> selfGetters = new HashMap<>();
        Map<String,Method> otherGetters = new HashMap<>();
        for (Method m : otherClass.getMethods()) {
            if ((m.getName().startsWith("get") || m.getName().startsWith("is")) && m.getParameterCount() == 0){
                otherGetters.put(m.getName(),m);
            }
        }

        for (Method m : getClass().getMethods()) {
            if ((m.getName().startsWith("set") && m.getParameterCount() == 1 )) {
                String setterName = m.getName();
                String getterName = String.format("g%s",setterName.substring(1));

                Method otherGetter = otherGetters.get(getterName);
                if (otherGetter !=  null && hasCommonAncestor(m.getParameterTypes()[0],otherGetter.getReturnType())){
                    selfSetters.put(m.getName(), m);
                }
            }else if ((m.getName().startsWith("get") || m.getName().startsWith("is")) && m.getParameterCount() == 0){
                selfGetters.put(m.getName(),m);
            }
        }

        for (Map.Entry<String,Method> entry : otherGetters.entrySet()) {

            String getterName = entry.getKey();
            String setterName = String.format("s%s",getterName.substring(1));

            Method otherGetter = entry.getValue();
            Method selfSetter = selfSetters.get(setterName);
            Method selfGetter = selfGetters.get(getterName);

            if (selfSetter == null || selfGetter == null) {
                continue;
            }

            Class<?> otherFieldType = otherGetter.getReturnType();
            Class<?> selfFieldType = selfSetter.getParameterTypes()[0];
            try {
                if (BecknObject.class.isAssignableFrom(otherFieldType) && BecknObject.class.isAssignableFrom(selfFieldType)) {
                    BecknObject otherField = (BecknObject) otherGetter.invoke(from);
                    if (otherField == null){
                        continue;
                    }
                    selfSetter.invoke(this, selfFieldType.getConstructor().newInstance());
                    BecknObject selfField = (BecknObject) selfGetter.invoke(this);
                    selfField.load(otherField);
                } else {
                    selfSetter.invoke(this, otherGetter.invoke(from));
                }
            }catch (Exception ex){
                throw new RuntimeException(ex);
            }
        }


    }

    public static boolean hasCommonAncestor(Class<?> targetType, Class<?> sourceType) {
        Class<?> commonAncestor = targetType;
        while (!commonAncestor.isAssignableFrom(sourceType)){
            commonAncestor = commonAncestor.getSuperclass();
        }
        if (commonAncestor == BecknObject.class || commonAncestor == Object.class){
            return false;
        }
        return true;
    }


    public static  <T extends BecknObject, S extends BecknObject> boolean hasCommonAncestor(T target, S source){
        Class<?> commonAncestor = target.getClass();
        Class<?> sourceType = source.getClass();
        while (!commonAncestor.isAssignableFrom(sourceType)){
            commonAncestor = commonAncestor.getSuperclass();
        }
        if (commonAncestor == BecknObject.class){
            return false;
        }
        return true;
    }

    protected String _flat(String s){
        return s == null ? "" : " " + s;
    }

}
