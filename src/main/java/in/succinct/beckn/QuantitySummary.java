package in.succinct.beckn;

public class QuantitySummary extends BecknObject{
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
        return get(Quantity.class, "maximum");
    }
    public void setMaximum(Quantity maximum){
        set("maximum",maximum);
    }
    public Quantity getMinimum(){
        return get(Quantity.class, "minimum");
    }
    public void setMinimum(Quantity minimum){
        set("minimum",minimum);
    }

    public Quantity getSelected(){
        return get(Quantity.class, "selected");
    }
    public void setSelected(Quantity selected){
        set("selected",selected);
    }

}
