package in.succinct.beckn;

public class Image extends BecknObject{
    public Image(){
        super();
    }
    public String getDescription(){
        return get("description");
    }
    public void setDescription(String description){
        set("description",description);
    }

    public String getUrl(){
        return get("url");
    }
    public void setUrl(String url){
        set("url",url);
    }
    public SizeType getSizeType(){
        return getEnum(SizeType.class, "size_type");
    }
    public void setSizeType(SizeType size_type){
        setEnum("size_type",size_type);
    }

    public enum SizeType {
        xs,
        sm,
        md,
        lg,
        xl,
        custom

    }

    public int getWidth(){
        return getInteger("width");
    }
    public void setWidth(int width){
        set("width",String.valueOf(width));
    }
    public int getHeight(){
        return getInteger("height");
    }
    public void setHeight(int height){
        set("height",String.valueOf(height));
    }
    
}
