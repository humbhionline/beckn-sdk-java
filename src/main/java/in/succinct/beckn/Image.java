package in.succinct.beckn;

public class Image extends BecknObject{
    public String getDescription(){
        return get("description");
    }
    public void setDescription(String description){
        set("description",description);
    }
}
