package in.succinct.beckn;

import org.json.simple.JSONArray;

import java.lang.reflect.ParameterizedType;
import java.util.Comparator;
import java.util.Iterator;

public class BecknObjects<T> extends BecknAware<JSONArray> implements  Iterable<T>{
    Class<T> clazz ;
    public BecknObjects(){
        this(new JSONArray());
    }

    @SuppressWarnings("unchecked")
    public BecknObjects(JSONArray value) {
        super(value);
        ParameterizedType pt = (ParameterizedType)getClass().getGenericSuperclass();
        this.clazz = (Class<T>) pt.getActualTypeArguments()[0];
    }

    @SuppressWarnings("unchecked")
    public BecknObjects(String payload) {
        super(payload);
        ParameterizedType pt = (ParameterizedType)getClass().getGenericSuperclass();
        this.clazz = (Class<T>) pt.getActualTypeArguments()[0];
    }

    public T get(int index){
        return  super.get(index,clazz);
    }

    public int size(){
        return getInner().size();
    }
    public boolean isEmpty(){
        return getInner().isEmpty();
    }

    @Override
    @SuppressWarnings("all")
    public boolean equals(Object o) {
        return super.equals(o) && (((BecknObjects)o).clazz == clazz);
    }


    @Override
    public Iterator<T> iterator() {
        return super.iterator(clazz);
    }

    public T min(Comparator<T> comparator){
        return super.min(comparator,clazz);
    }
}
