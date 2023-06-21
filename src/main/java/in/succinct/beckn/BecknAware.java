package in.succinct.beckn;

import in.succinct.json.ExtendedJSONAware;
import org.json.simple.JSONAware;

import java.io.Serializable;

public abstract class BecknAware<T extends JSONAware> extends ExtendedJSONAware<T> {

    protected BecknAware(T value) {
        super(value);
    }

    protected BecknAware(String payload) {
        super(payload);
    }

    public static class BecknAwareCreator extends ExtendedJSONAwareCreator {
    }

}
