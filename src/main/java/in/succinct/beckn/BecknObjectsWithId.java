package in.succinct.beckn;

import com.venky.core.util.ObjectUtil;

import java.util.HashMap;
import java.util.Map;

public class BecknObjectsWithId<T extends BecknObjectWithId> extends BecknObjects<T> {
    public BecknObjectsWithId(){
        super();
        loadMap();
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


    Map<String,T> map = null;
    public T get(String id){
        loadMap();
        return map.get(id);
    }
    private void loadMap(){
        if (map == null){
            map =  new HashMap<>();
            for (int i = 0 ; i < size() ; i ++){
                T t = get(i);
                map.put(t.getId(),t);
            }
        }
    }
    public void put(String id, T t){
        loadMap();
        map.put(id,t);
    }




}
