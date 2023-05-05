package in.succinct.beckn;

import org.json.simple.JSONObject;

public class BecknObject extends BecknObjectBase{
    public BecknObject(){
        this(new JSONObject());
    }
    public BecknObject(String payload){
        super(payload);
        if (getInner() == null){
            throw new RuntimeException("Payload not correct format");
        }
    }
    public BecknObject(JSONObject object){
        super(object);
    }





    protected BecknObjectBase extendedAttributes = getExtendedAttributes(true);


    public BecknObjectBase getExtendedAttributes(){
        return getExtendedAttributes(false);
    }
    protected final BecknObjectBase getExtendedAttributes(boolean reset){
        if (reset) {
            if (isExtendedAttributesDisplayed()) {
                return get(BecknObjectBase.class, "extended_attributes", true);
            } else {
                return getObjectCreator().create(BecknObjectBase.class);
            }
        }else {
            return extendedAttributes;
        }
    }

    public boolean isExtendedAttributesDisplayed(){
        return false;
    }

    @Override
    public void setObjectCreator(BecknAwareCreator objectCreator) {
        super.setObjectCreator(objectCreator);
        extendedAttributes = getExtendedAttributes(true);
    }

    @Override
    public void setInner(JSONObject value) {
        super.setInner(value);
        extendedAttributes = getExtendedAttributes(true);
    }
}
