package in.succinct.beckn;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.lang.reflect.ParameterizedType;

public class BecknObjects<T extends BecknObject> extends BecknAware<JSONArray> {
    Class<T> clazz ;
    public BecknObjects(){
        super(new JSONArray());
        ParameterizedType pt = (ParameterizedType)getClass().getGenericSuperclass();
        this.clazz = (Class<T>) pt.getActualTypeArguments()[0];
    }

    public void add(T t){
        getInner().add(t.getInner());
    }
    public void remove(T t){
        getInner().remove(t.getInner());
    }

    public T get(int index){
        JSONObject element = (JSONObject) getInner().get(index);
        try {
            T t =  clazz.getConstructor().newInstance();
            t.setInner(element);
            return t;
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    public int size(){
        return getInner().size();
    }

    @Override
    public boolean equals(Object o) {
        if ( o != null && getClass() == o.getClass() ){
            BecknObjects other = (BecknObjects)o;
            return (other.clazz == clazz && other.getInner().equals(getInner()));
        }
        return false;
    }


}
