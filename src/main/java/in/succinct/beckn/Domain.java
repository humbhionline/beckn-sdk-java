package in.succinct.beckn;

import in.succinct.beckn.Descriptor.MediaFile;
import org.json.simple.JSONObject;

public class Domain extends BecknObject{
    public Domain() {
    }

    public Domain(String payload) {
        super(payload);
    }

    public Domain(JSONObject object) {
        super(object);
    }
    public String getName(){
        return get("name");
    }
    public void setName(String name){
        set("name",name);
    }

    public String getCode(){
        return get("code");
    }
    public void setCode(String code){
        set("code",code);
    }

    public MediaFile getAdditionalInfo(){
        return get(MediaFile.class, "additional_info");
    }
    public void setAdditionalInfo(MediaFile media_file){
        set("additional_info",media_file);
    }


}
