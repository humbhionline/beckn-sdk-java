package in.succinct.beckn;

import com.venky.core.util.ObjectUtil;
import in.succinct.beckn.BecknObject;
import in.succinct.beckn.Quantity;
import org.json.simple.JSONObject;

public class ItemQuantity extends BecknObject {
    public ItemQuantity() {
        super();
        setUnitized(new Unitized());
    }
    
    public Unitized getUnitized(){
        return get(Unitized.class, "unitized");
    }
    public void setUnitized(Unitized unitized){
        set("unitized",unitized);
    }

    public static class Unitized extends Quantity{
        public Unitized() {
            super();
            setCount(1);
            Measure measure = new Measure();
            measure.setUnit("unit");
            measure.setValue(1);
            setMeasure(measure);
        }

    }

    public Quantity getAllocated(){
        return get(Quantity.class,"allocated");
    }
    public void setAllocated(Quantity allocated){
        set("allocated",allocated);
    }

    public Quantity getAvailable(){
        return get(Quantity.class,"available");
    }
    public void setAvailable(Quantity available){
        set("available",available);
    }

    public Quantity getMaximum(){
        return get(Quantity.class,"maximum");
    }
    public void setMaximum(Quantity maximum){
        set("maximum",maximum);
    }

    public Quantity getMinimum(){
        return get(Quantity.class,"minimum");
    }
    public void setMinimum(Quantity minimum){
        set("minimum",minimum);
    }

    public Quantity getSelected(){
        return get(Quantity.class,"selected");
    }
    public void setSelected(Quantity selected){
        set("selected",selected);
    }
}
