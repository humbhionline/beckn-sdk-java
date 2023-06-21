package in.succinct.beckn;

import in.succinct.json.ExtendedJSONObjects;
import org.json.simple.JSONArray;

import java.lang.reflect.ParameterizedType;

public class BecknObjects<T> extends ExtendedJSONObjects<T> implements  Iterable<T>{

    public BecknObjects(){
        super();
    }

    public BecknObjects(JSONArray value) {
        super(value);
    }

    public BecknObjects(String payload) {
        super(payload);
    }

}
