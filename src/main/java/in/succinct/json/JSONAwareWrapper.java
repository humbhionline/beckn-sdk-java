package in.succinct.json;

import com.venky.core.date.DateUtils;
import com.venky.core.util.MultiException;
import com.venky.core.util.ObjectHolder;
import com.venky.core.util.ObjectUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TimeZone;

public class JSONAwareWrapper<T extends JSONAware> implements Serializable {

    protected JSONAwareWrapper(T value){
        if (value == null){
            throw new NullPointerException();
        }
        setInner(value);
    }

    protected JSONAwareWrapper(String payload){
        setPayload(payload);
    }

    @SuppressWarnings("unchecked")
    public static <T extends JSONAware> T parse(String payload){
        try {
            return (T) JSONValue.parseWithException(payload);
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    public void setPayload(String payload){
        this.payload =payload;
        setInner(parse(payload));
    }


    private String payload;
    private T value ;

    public void setInner(T value){
        this.value = value;
        attributeMap.clear();
    }

    public T getInner(){
        return this.value;
    }

    public JSONObject getInnerObject(){
        return (JSONObject) this.value;
    }

    public JSONArray getInnerArray(){
        return (JSONArray) this.value;
    }

    @Override
    public String toString() {
        if (payload == null){
            return value.toJSONString();
        }
        return payload;
    }

    public JSONAwareWrapperCreator getObjectCreator(){
        return objectCreator;
    }

    private JSONAwareWrapperCreator objectCreator = new JSONAwareWrapperCreator();
    public void setObjectCreator(JSONAwareWrapperCreator objectCreator){
        this.objectCreator = objectCreator;
        attributeMap().clear();
    }


    public static class JSONAwareWrapperCreator implements Serializable {
        @SuppressWarnings("all")
        public <B> B create(Class<B> clazz){
            try {
                B b = clazz.getConstructor().newInstance();
                if (b instanceof JSONAwareWrapper){
                    ((JSONAwareWrapper)b).setObjectCreator(JSONAwareWrapperCreator.this);
                }
                return b;
            }catch (Exception ex){
                throw new RuntimeException(ex);
            }
        }
    }

    /* Refactored from Beckn Object base*/
    @SuppressWarnings("all")
    private transient Map<String, ObjectHolder<JSONAwareWrapper>> attributeMap = new HashMap<>();

    @SuppressWarnings("all")
    public <W extends JSONAwareWrapper> W get(Class<W> clazz,String name){
        return get(clazz,name,false);
    }
    @SuppressWarnings("all")
    public <W extends JSONAwareWrapper> W get(Class<W> clazz,String name,boolean createIfAbsent){
        if (attributeMap().containsKey(name)){
            Object value = attributeMap().get(name).get();
            if (value != null ){
                if (clazz.isAssignableFrom(value.getClass())) {
                    return (W) value;
                }else {
                    return null;
                }
            }
        }
        JSONObject inner = (JSONObject) getInner();
        JSONAware clazzInner = (JSONAware) inner.get(name);
        try {
            W w = null ;
            if (clazzInner != null || createIfAbsent){
                w = getObjectCreator().create(clazz);
                if (clazzInner != null) {
                    w.setInner(clazzInner);
                }else {
                    clazzInner = w.getInner();
                    inner.put(name,clazzInner);
                }
            }
            attributeMap().put(name,new ObjectHolder<>(w));
            return w;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    @SuppressWarnings("unchecked")
    public <W> W get(String key){
        return (W)((JSONObject)getInner()).get(key) ;
    }

    public <W> W get(String key, W returnIfNull){
        W ret = get(key);
        return ret != null ? ret : returnIfNull;
    }

    @SuppressWarnings("all")
    public <W extends JSONAwareWrapper> void set(String key, W value){
        if (value == null){
            rm(key);
            return;
        }

        set(key,value.getInner());
        attributeMap().put(key,new ObjectHolder<>(value));
    }

    public <E extends Enum<E>> void setEnum(String key, E value) {
        setEnum(key,value,null);
    }
    public <E extends Enum<E>> void setEnum(String key, E value , EnumConvertor<E> convertor){
        set(key,convertor == null ? ( value == null ? null : value.name() ) : convertor.toString(value));
    }

    public <E extends Enum<E>> E getEnum(Class<E> enumClass, String key){
        return getEnum(enumClass, key, null);
    }
    public <E extends Enum<E>> E getEnum(Class<E> enumClass, String key, EnumConvertor<E> convertor){
        String value = get(key);
        return convertor == null ? (value == null ? null :  Enum.valueOf(enumClass,value)) : convertor.valueOf(value);
    }
    @SuppressWarnings("all")
    public static class EnumConvertor<T extends Enum<T>> {
        Class<T> enumClass;
        public EnumConvertor(Class<T> clazz){
            this.enumClass = clazz;
        }
        protected EnumConvertor(){
            ParameterizedType pt = (ParameterizedType)this.getClass().getGenericSuperclass();
            this.enumClass = (Class<T>) pt.getActualTypeArguments()[0];
        }
        @SuppressWarnings("unchecked")
        public Class<T> getEnumClass(){
            return enumClass;
        }

        public T valueOf(String enumRepresentation) {
            return enumRepresentation == null ? null : Enum.valueOf(getEnumClass(),enumRepresentation.replace('-','_'));
        }
        public String toString(T value){
            return value == null ? null : value.name().replace('_','-');
        }
    }
    public static class OrdinalBasedEnumConvertor<T extends Enum<T>> extends EnumConvertor<T> {
        String prefix = "" ;
        public OrdinalBasedEnumConvertor(Class<T> clazz,String prefix) {
            super(clazz);
            this.prefix = prefix;
        }
        public OrdinalBasedEnumConvertor(Class<T> clazz) {
            this(clazz,"");
        }

        protected OrdinalBasedEnumConvertor() {
            super();
        }

        public T valueOf(String enumRepresentation) {
            return enumRepresentation == null ? null : getEnumClass().getEnumConstants()[Integer.parseInt(enumRepresentation.substring(prefix.length())) - 1];
        }
        public String toString(T value){
            String fmt = String.format("%%%s0%dd", prefix,String.valueOf(getEnumClass().getEnumConstants().length).length() + 1);
            return value == null ? null : String.format(fmt,value.ordinal() + 1);
        }
    }

    @SuppressWarnings("all")
    public void set(String key, JSONAware value){
        if (value == null) {
            rm(key);
            return;
        }

        getInnerObject().put(key,value);
        attributeMap().remove(key);
    }
    @SuppressWarnings("all")
    public void set(String key, String value){
        if (ObjectUtil.isVoid(value)) {
            rm(key);
            return;
        }

        getInnerObject().put(key,value);
    }
    @SuppressWarnings("all")
    public void set(String key, Boolean value){
        if (value == null ){
            rm(key);
            return;
        }
        getInnerObject().put(key,value);
    }

    public void rm(String  key){
        remove(key);
    }

    public static DateFormat TIMESTAMP_FORMAT_WITH_MILLS =  new SimpleDateFormat(DateUtils.ISO_8601_24H_FULL_FORMAT);
    public static DateFormat TIMESTAMP_FORMAT_WO_MILLIS = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");

    public static DateFormat TIMESTAMP_FORMAT =  TIMESTAMP_FORMAT_WITH_MILLS;

    public static final DateFormat[] TIMESTAMP_FORMATS = new DateFormat[] {TIMESTAMP_FORMAT_WITH_MILLS, TIMESTAMP_FORMAT_WO_MILLIS};

    public static final DateFormat DATE_FORMAT = new SimpleDateFormat(DateUtils.ISO_DATE_FORMAT_STR);
    public static final DateFormat APP_DATE_FORMAT = new SimpleDateFormat(DateUtils.APP_DATE_FORMAT_STR);

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

    @SuppressWarnings("all")
    public void set(String key, Number value){
        if (value == null){
            rm(key);
            return;
        }
        getInnerObject().put(key,value);
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
    public Double getDouble(String key){
        return getDouble(key,0.0D);
    }
    public Double getDouble(String key,Double ifNull){
        Object o = get(key);
        return o == null ? ifNull : Double.valueOf(String.valueOf(o));
    }
    public Integer getInteger(String key) {
        return getInteger(key,0);
    }

    public Integer getInteger(String key,Integer ifNull){
        Object o = get(key);
        return o == null ? ifNull : Integer.valueOf(String.valueOf(o));
    }
    public Long getLong(String key) {
        return getLong(key,0L);
    }
    public Long getLong(String key, Long ifNull){
        Object o = get(key);
        return o == null ? ifNull : Long.valueOf(String.valueOf(o));
    }
    public Boolean getBoolean(String key){
        return getBoolean(key,false);
    }
    public Boolean getBoolean(String key,Boolean ifNull){
        Object o = get(key);
        return o == null ? ifNull : Boolean.valueOf(String.valueOf(o));
    }

    @SuppressWarnings("all")
    protected Map<String,ObjectHolder<JSONAwareWrapper>> attributeMap(){
        if (attributeMap == null){
            synchronized (this) {
                if (attributeMap == null) {
                    attributeMap = new HashMap<>();
                }
            }
        }
        return attributeMap;
    }
    public boolean hasAdditionalProperties(){
        return false;
    }

    protected String _flat(String s){
        return _flat(s," ");
    }
    protected String _flat(String s,String prefix){
        return prefix + (ObjectUtil.isVoid(s) ? " " : s) ;
    }
    
    
    /* from beckn objects */


    public int size(){
        T inner = getInner();
        if (inner instanceof JSONObject){
            return getInnerObject().size();
        }else if (inner instanceof JSONArray){
            return getInnerArray().size();
        }else {
            throw new RuntimeException("Don't know what JSONAware object's size is asked for.!");
        }
    }

    public boolean isEmpty(){
        return size() == 0;
    }


    @Override
    @SuppressWarnings("all")
    public boolean equals(Object o) {
        if ( o != null && getClass() == o.getClass() ){
            JSONAwareWrapper other = (JSONAwareWrapper)o;
            return (other.getInner().equals(getInner()));
        }
        return false;
    }

    @SuppressWarnings("all")
    public <O> O get(int index,Class<O> childClazz){
        Object element = getInnerArray().get(index);

        if (element == null){
            return null;
        }
        try {
            O t ;
            if (element instanceof JSONObject && JSONAwareWrapper.class.isAssignableFrom(childClazz) ){
                t = getObjectCreator().create(childClazz);
                ((JSONAwareWrapper)t).setInner((JSONObject) element);
            }else {
                t = childClazz.getConstructor(String.class).newInstance(String.valueOf(element));
            }
            return t;
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }
    @SuppressWarnings("all")
    public  <O> void add(O o, boolean reset){
        JSONArray inner = getInnerArray();

        if (o instanceof JSONAwareWrapper){
            inner.add(((JSONAwareWrapper)o).getInner());
        }else {
            inner.add(o);
        }
    }
    public <O> void add(O o){
        add(o,false);
    }
    @SuppressWarnings("all")
    public <O> void remove(O o){
        JSONAware inner = getInner();
        if (inner instanceof JSONObject){
            ((JSONObject)inner).remove(o);
            attributeMap().remove(o);
        }else if (inner instanceof JSONArray){
            if (o instanceof JSONAwareWrapper) {
                ((JSONArray) inner).remove(((JSONAwareWrapper)o).getInner());
            }else {
                ((JSONArray) inner).remove(o);
            }
        }
    }
    public void clear(){
        JSONAware inner = getInner();
        if (inner instanceof JSONObject){
            ((JSONObject)inner).clear();
        }else if (inner instanceof JSONArray){
            ((JSONArray)inner).clear();
        }
    }


    public <O> Iterator<O> iterator(Class<O> childClazz) {
        if (getInner() instanceof JSONArray){
            return new Iterator<>() {
                int i = 0;
                @Override
                public boolean hasNext() {
                    return i < size();
                }

                @Override
                public O next() {
                    return get(i++,childClazz);
                }
            };
        }else {
            return new Iterator<>() {
                @Override
                public boolean hasNext() {
                    return false;
                }

                @Override
                public O next() {
                    return null;
                }
            };
        }
    }

    public <O> O min(Comparator<O> comparator,Class<O> elementClass){
        int size = size();
        if (size == 0){
            return null;
        }
        O min = get(0,elementClass);
        for (int i = 1 ; i < size ; i ++){
            O t = get(i,elementClass);
            if (comparator.compare(min,t) > 0){
                min = t;
            }
        }
        return min;
    }
}
