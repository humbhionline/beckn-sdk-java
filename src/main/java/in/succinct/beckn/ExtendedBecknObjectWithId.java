package in.succinct.beckn;

import org.json.simple.JSONObject;

public class ExtendedBecknObjectWithId extends BecknObjectWithId{

    public ExtendedBecknObjectWithId() {
    }

    public ExtendedBecknObjectWithId(String payload) {
        super(payload);
    }

    public ExtendedBecknObjectWithId(JSONObject object) {
        super(object);
    }

    protected BecknObject extendedAttributes = getExtendedAttributes();

    @Override
    public void setObjectCreator(BecknObjectCreator objectCreator) {
        super.setObjectCreator(objectCreator);
        extendedAttributes.setObjectCreator(objectCreator);
    }

    protected final BecknObject getExtendedAttributes(){
        if (isExtendedAttributesDisplayed()) {
            return get(BecknObject.class, "extended_attributes", true);
        }else {
            return new BecknObject();
        }
    }

    public boolean isExtendedAttributesDisplayed(){
        return true;
    }
}
