package in.succinct.beckn;

import in.succinct.beckn.BecknObject;
import in.succinct.beckn.Quantity;

public class ItemQuantity extends BecknObject {
    public ItemQuantity() {super();}

    public Quantity getAllocated(){
        return get(Quantity.class,"allocated");
    }
    public void setAllocated(Quantity allocated){
        set("allocated",allocated.getInner());
    }

    public Quantity getAvailable(){
        return get(Quantity.class,"available");
    }
    public void setAvailable(Quantity available){
        set("available",available.getInner());
    }

    public Quantity getMaximum(){
        return get(Quantity.class,"maximum");
    }
    public void setMaximum(Quantity maximum){
        set("maximum",maximum.getInner());
    }

    public Quantity getMinimum(){
        return get(Quantity.class,"minimum");
    }
    public void setMinimum(Quantity minimum){
        set("minimum",minimum.getInner());
    }

    public Quantity getSelected(){
        return get(Quantity.class,"selected");
    }
    public void setSelected(Quantity selected){
        set("selected",selected.getInner());
    }
}
