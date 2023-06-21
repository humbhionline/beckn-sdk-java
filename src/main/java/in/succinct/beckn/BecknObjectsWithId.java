package in.succinct.beckn;

import com.venky.core.util.ObjectUtil;
import in.succinct.json.ExtendedJSONObjectsWithId;
import org.json.simple.JSONArray;

import java.util.HashMap;
import java.util.Map;

public class BecknObjectsWithId<T extends BecknObjectWithId> extends ExtendedJSONObjectsWithId<T> {
    public BecknObjectsWithId(){
        super();
    }
    public BecknObjectsWithId(JSONArray array){
        super(array);
    }
    public BecknObjectsWithId(String payload){
        super(payload);
    }

}
