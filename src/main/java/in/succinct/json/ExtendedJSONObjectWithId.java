package in.succinct.json;

import com.venky.core.util.ObjectUtil;
import org.json.simple.JSONObject;

public class ExtendedJSONObjectWithId extends ExtendedJSONObject {
    public ExtendedJSONObjectWithId(){
        super();
    }
    public ExtendedJSONObjectWithId(String payload){
        super(payload);
    }
    public ExtendedJSONObjectWithId(JSONObject object){
        super(object);
    }

    public String getId(){
        return get("id");
    }
    public void setId(String id){
        if (!ObjectUtil.isVoid(id) && !ObjectUtil.isVoid(getId()) && !ObjectUtil.equals(getId(),id)){
            throw new RuntimeException("Id cannot be changed!");
        }
        set("id",id);
    }


}
