package in.succinct.beckn;

public class QuantitySummary extends BecknObject{
    public Allocated getAllocated(){
        return get(Allocated.class,"allocated");
    }
    public void setAllocated(Allocated allocated){
        set("allocated",allocated);
    }
    public Available getAvailable(){
        return get(Available.class,"available");
    }
    public void setAvailable(Available available){
        set("available",available);
    }
    public Maximum getMaximum(){
        return get(Maximum.class, "maximum");
    }
    public void setMaximum(Maximum maximum){
        set("maximum",maximum);
    }
    public Minimum getMinimum(){
        return get(Minimum.class, "minimum");
    }
    public void setMinimum(Minimum minimum){
        set("minimum",minimum);
    }

    public Selected getSelected(){
        return get(Selected.class, "selected");
    }
    public void setSelected(Selected selected){
        set("selected",selected);
    }

    public static class Allocated extends Quantity {}
    public static class Available extends Quantity {}
    public static class Maximum extends Quantity {}
    public static class Minimum extends Quantity {}
    public static class Selected extends Quantity {}

}
