package in.succinct.beckn;

import in.succinct.json.JSONAwareWrapper;
import in.succinct.json.JSONAwareWrapper.JSONAwareWrapperCreator;
import org.json.simple.JSONArray;

import java.util.StringTokenizer;

@SuppressWarnings("all")
public interface ImagesHolder {
    default Images getImages() {
        JSONArray o = get("images");
        if (o == null){
            return null;
        }
        if (o.isEmpty()){
            return getObjectCreator().create(Images.class);
        }
        if (o.get(0) instanceof String) {
            Images images = getObjectCreator().create(Images.class);
            o.forEach(s->{
                images.add((String)s);
            });
            return images;
        } else {
            return get(Images.class, "images");
        }
    }

    default void setImages(Images images) {
        set("images", images);
    }



    public <W extends JSONAwareWrapper> W get(Class<W> clazz, String name);
    public <W> W get(String key);
    public void set(String key, String value);
    public <W extends JSONAwareWrapper> void set(String key, W value);
    JSONAwareWrapperCreator getObjectCreator();
}