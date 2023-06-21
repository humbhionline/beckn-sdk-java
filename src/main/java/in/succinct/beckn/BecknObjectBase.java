package in.succinct.beckn;

import in.succinct.json.ExtendedJSONObjectBase;
import org.json.simple.JSONObject;

public class BecknObjectBase extends ExtendedJSONObjectBase {
    public BecknObjectBase(JSONObject value) {
        super(value);
    }

    public BecknObjectBase(String payload) {
        super(payload);
    }

    public BecknObjectBase(){
        super();
    }

    public static class EnumConvertor<T extends Enum<T>> extends ExtendedJSONObjectBase.EnumConvertor<T> {
        public EnumConvertor(Class<T> clazz) {
            super(clazz);
        }

        protected EnumConvertor() {
            super();
        }
    }

    public static class OrdinalBasedEnumConvertor<T extends Enum<T>> extends ExtendedJSONObjectBase.OrdinalBasedEnumConvertor<T> {
        public OrdinalBasedEnumConvertor(Class<T> clazz) {
            super(clazz);
        }

        protected OrdinalBasedEnumConvertor() {
            super();
        }
    }
}
