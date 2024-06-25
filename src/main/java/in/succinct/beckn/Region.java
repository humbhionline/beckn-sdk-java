package in.succinct.beckn;

import org.json.simple.JSONObject;

public class Region extends BecknObject{
    public Region() {
    }

    public Region(String payload) {
        super(payload);
    }

    public Region(JSONObject object) {
        super(object);
    }

    public int getDimensions(){
        return getInteger("dimensions");
    }
    public void setDimensions(int dimensions){
        set("dimensions",String.valueOf(dimensions));
    }

    public String getType(){
        return get("type");
    }
    public void setType(String type){
        set("type",type);
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

    public String getBoundary(){
        return get("boundary");
    }
    public void setBoundary(String boundary){
        set("boundary",boundary);
    }

    public String getMapUrl(){
        return get("map_url");
    }
    public void setMapUrl(String map_url){
        set("map_url",map_url);
    }

}
