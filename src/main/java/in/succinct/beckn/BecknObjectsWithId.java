package in.succinct.beckn;

import com.venky.core.util.ObjectUtil;
import org.json.simple.JSONArray;

import java.util.HashMap;
import java.util.Map;

public class BecknObjectsWithId<T extends BecknObjectWithId> extends BecknObjects<T> {
    public BecknObjectsWithId(){
        this(new JSONArray());
    }
    public BecknObjectsWithId(JSONArray array){
        super(array);
        loadMap();
    }
    public BecknObjectsWithId(String payload){
        super(payload);
        loadMap();
    }

    @Override
    public T get(int index) {
        T t = super.get(index);
        if (map != null ) {
            return map.get(t.getId()); // Be careful changing this. possibility of recursion with loadMap()
        }
        return t;
    }

    public void add(T t){
        if (ObjectUtil.isVoid(t.getId())){
            throw new RuntimeException("ID missing");
        }
        if (null != map.put(t.getId(),t)){
            // There was a previous value.
            throw new RuntimeException("ID Already exists");
        }
        super.add(t);
    }
    public void remove(T t){
        if (ObjectUtil.isVoid(t.getId())){
            throw new RuntimeException("ID missing");
        }
        if (null != map.remove(t.getId())) {
            super.remove(t);
        }
    }

    @Override
    public void setInner(JSONArray value) {
        super.setInner(value);
        map = null;
    }

    Map<String,T> map = null;
    public T get(String id){
        loadMap();
        return map.get(id);
    }
    private void loadMap(){
        if (map == null){
            Map tmpMap =  new HashMap<>();
            for (int i = 0 ; i < size() ; i ++){
                T t = get(i);
                tmpMap.put(t.getId(),t);
            }
            map = tmpMap;
        }
    }
    public void put(String id, T t){
        loadMap();
        map.put(id,t);
    }




}
