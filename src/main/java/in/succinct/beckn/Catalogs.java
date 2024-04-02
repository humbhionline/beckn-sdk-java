package in.succinct.beckn;

import org.json.simple.JSONArray;

public class Catalogs extends BecknObjectsWithId<Catalog>{
    public Catalogs() {
    }

    public Catalogs(String payload) {
        super(payload);
    }

    public Catalogs(JSONArray array) {
        super(array);
    }

}
