package in.succinct.beckn;

import org.json.simple.JSONArray;

public class Providers extends BecknObjectsWithId<Provider> {
    public Providers() {
        super();
    }
    public Providers(JSONArray p){
        super(p);
    }
}