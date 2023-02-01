package in.succinct.beckn;

import in.succinct.beckn.BreakUp.BreakUpElement;
import in.succinct.beckn.BreakUp.BreakUpElement.BreakUpCategory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.HashSet;
import java.util.Set;

public class BreakUp extends BecknObjects<BreakUpElement> {
    public BreakUp(){
        super();
    }
    public BreakUpElement createElement(BreakUpCategory type, String title, Price price){
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

        public BreakUpCategory getType(){
            String s = get("type");
            return s == null ? null : BreakUpCategory.valueOf(s);
        }
        public void setType(BreakUpCategory type){
            set("type",type == null ? null : type.toString());
        }

        protected final BecknObject extendedAttributes = new BecknObject();
        public String getItemId(){
            return extendedAttributes.get("item_id");
        }
        public void setItemId(String item_id){
            extendedAttributes.set("item_id",item_id);
        }
        public Quantity getItemQuantity(){
            return extendedAttributes.get(Quantity.class, "item_quantity");
        }
        public void setItemQuantity(Quantity item_quantity){
            extendedAttributes.set("item_quantity",item_quantity);
        }
        
        
        public enum BreakUpCategory {
            item,
            delivery,
            packing,
            tax,
            misc,
            discount,
        }
    }
}
