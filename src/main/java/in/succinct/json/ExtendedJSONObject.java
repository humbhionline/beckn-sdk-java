package in.succinct.json;

import org.json.simple.JSONObject;

public class ExtendedJSONObject extends ExtendedJSONObjectBase{
    public ExtendedJSONObject(){
        this(new JSONObject());
    }
    public ExtendedJSONObject(String payload){
        super(payload);
        if (getInner() == null){
            throw new RuntimeException("Payload not correct format");
        }
    }
    public ExtendedJSONObject(JSONObject object){
        super(object);
    }





    protected ExtendedJSONObjectBase extendedAttributes = getExtendedAttributes(true);


    public ExtendedJSONObjectBase getExtendedAttributes(){
        return getExtendedAttributes(false);
    }
    protected final ExtendedJSONObjectBase getExtendedAttributes(boolean reset){
        if (reset) {
            if (isExtendedAttributesDisplayed()) {
                return get(ExtendedJSONObjectBase.class, "extended_attributes", true);
            } else {
                return getObjectCreator().create(ExtendedJSONObjectBase.class);
            }
        }else {
            return extendedAttributes;
        }
    }

    public boolean isExtendedAttributesDisplayed(){
        return false;
    }

    @Override
    public void setObjectCreator(ExtendedJSONAwareCreator objectCreator) {
        super.setObjectCreator(objectCreator);
        extendedAttributes = getExtendedAttributes(true);
    }

    @Override
    public void setInner(JSONObject value) {
        super.setInner(value);
        extendedAttributes = getExtendedAttributes(true);
    }
}
