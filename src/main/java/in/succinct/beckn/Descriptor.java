package in.succinct.beckn;

public class Descriptor extends BecknObject {
    public Descriptor(){
        super();
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

    public String getSymbol(){
        return get("symbol");
    }
    public void setSymbol(String symbol){
        set("symbol",symbol);
    }
    public String getShortDesc(){
        return get("short_desc");
    }
    public void setShortDesc(String short_desc){
        set("short_desc",short_desc);
    }
    public String getLongDesc(){
        return get("long_desc");
    }
    public void setLongDesc(String long_desc){
        set("long_desc",long_desc);
    }
    public Images getImages(){
        return get(Images.class, "images");
    }
    public void setImages(Images images){
        set("images",images);
    }
    public String getAudio(){
        return get("audio");
    }
    public void setAudio(String audio){
        set("audio",audio);
    }

    public String get3dRender(){
        return get("3d_render");
    }
    public void set3dRender(String render3d){
        set("3d_render",render3d);
    }
}
