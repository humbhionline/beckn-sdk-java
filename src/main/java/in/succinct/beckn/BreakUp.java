package in.succinct.beckn;

import in.succinct.beckn.BreakUp.BreakUpElement;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.HashSet;
import java.util.Set;

public class BreakUp extends BecknObjects<BreakUpElement> {
    public BreakUp(){
        super();
    }
    public BreakUpElement createElement(String type,String title, Price price){
        validate(type);
        BreakUpElement element =new BreakUpElement(type);
        element.set("title",title);
        element.set("price",price.getInner());
        return element;
    }


    private static Set<String> types = new HashSet<>(){{
        add("item");
        add("offer");
        add("add-on");
        add("fulfillment");
    }};

    private void validate(String type){
        if (!types.contains(type)){
            throw new RuntimeException("Invalid type:" +  type);
        }
    }

    public static class BreakUpElement extends BecknObject {
        public BreakUpElement(){
            super();
        }
        public BreakUpElement(String type) {
            this();
            set("type",type);
        }
    }
}
