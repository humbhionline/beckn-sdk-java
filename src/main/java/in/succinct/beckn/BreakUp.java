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
        BreakUpElement element =new BreakUpElement();
        element.setTitle(title);
        element.setPrice(price);
        element.setType(type);
        return element;
    }



    public static class BreakUpElement extends BecknObject {
        public BreakUpElement(){
            super();
        }
        public String getTitle(){
            return get("title");
        }
        public void setTitle(String title){
            set("title",title);
        }
        public Item getItem(){
            return get(Item.class, "item");
        }
        public void setItem(Item item){
            set("item",item);
        }
        public Price getPrice(){
            return get(Price.class, "price");
        }
        public void setPrice(Price price){
            set("price",price);
        }

        public String getType(){
            return get("type");
        }
        public void setType(String type){
            set("type",type);
        }
    }
}
