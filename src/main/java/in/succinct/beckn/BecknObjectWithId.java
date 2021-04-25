package in.succinct.beckn;

import com.venky.core.util.ObjectUtil;
import org.json.simple.JSONObject;

public class BecknObjectWithId extends BecknObject {
    public BecknObjectWithId(){
        super();
    }

    public String getId(){
        return get("id");
    }
    public void setId(String id){
        if (!ObjectUtil.isVoid(getId())){
            throw new RuntimeException("Id cannot be changed!");
        }
        set("id",id);
    }


}
