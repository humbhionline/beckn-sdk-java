package in.succinct.beckn;

import com.venky.cache.UnboundedCache;
import com.venky.core.util.ObjectUtil;
import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class BecknObjectsWithId<T extends BecknObjectWithId> extends BecknObjects<T> {
    boolean unique ;
    public BecknObjectsWithId(){
        this(new JSONArray());
    }
    public BecknObjectsWithId(String payload){
        this((JSONArray) parse(payload));
    }
    public BecknObjectsWithId(JSONArray array){
        this(array,true);
    }
    public BecknObjectsWithId(JSONArray array,boolean unique){
        super(array);
        this.unique= unique;
        loadMap();
    }
    @Override
    public T get(int index) {
        T t = super.get(index);
        if (unique && map != null && t != null) {
            //Make sure only one object exists for the id.
            List<T> values   = map.get(t.getId()); // Be careful changing this. possibility of recursion with loadMap()
            if (values.isEmpty()){
                values.add(t);
            }else {
                t = values.get(0);
            }
        }
        return t;
    }

    @Override
    public void add(Object t1){
        loadMap();
        T t = (T)t1;
        if (ObjectUtil.isVoid(t.getId())){
            t.setDefaultId();
        }
        List<T> list = map.get(t.getId());
        if (unique) {
            if (!list.isEmpty()){
                throw new RuntimeException("ID already exists");
            }
        }
        list.add(t);
        super.add(t);
    }
    public void remove(T t){
        if (ObjectUtil.isVoid(t.getId())){
            throw new RuntimeException("ID missing");
        }
        List<T> values = map.get(t.getId());
        if (values.remove(t)){
            super.remove(t);
        }
    }

    @Override
    public void setInner(JSONArray value) {
        super.setInner(value);
        map = null;
    }

    Map<String, List<T>> map = null;
    public List<T> all(String id){
        loadMap();
        return id == null ? null : map.get(id);
    }
    public T get(String id){

        List<T> l = all(id);
        if (l == null || l.isEmpty()){
            return null;
        }

        return l.get(0);
    }
    private void loadMap(){
        if (map == null){
            Map<String,List<T>> tmpMap =  new UnboundedCache<>() {
                @Override
                protected List<T> getValue(String Id) {
                    return new ArrayList<>();
                }
            };
            for (int i = 0 ; i < size() ; i ++){
                T t = get(i);
                tmpMap.get(t.getId()).add(t);
            }
            map = tmpMap;
        }
    }

}
