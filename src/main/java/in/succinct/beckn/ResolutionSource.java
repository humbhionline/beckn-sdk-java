package in.succinct.beckn;
import in.succinct.beckn.BecknObject;  

public class ResolutionSource extends BecknObject{
    public String getType(){
        return get("type");
    }
    public void setType(String type){
        set("type",type);
    }
    public String getLink(){
        return get("link");
    }
    public void setLink(String link){
        set("link",link);
    }

}
