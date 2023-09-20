package in.succinct.beckn;

public class Price extends Amount {
    public Price() {
        super();
    }

    public Double getMinimumValue(){
        return getValue("minimum");
    }
    public void setMinimumValue(Double minimum){
        setValue("minimum",minimum);
    }
    public Double getMaximumValue(){
        return getValue("maximum");
    }
    public void setMaximumValue(Double maximum){
        setValue("maximum",maximum);
    }
    @Deprecated
    public Double getEstimatedValue(){
        return getValue("estimated");
    }
    public void setEstimatedValue(Double estimatedValue){
        setValue("estimated",estimatedValue);
    }

    public Double getComputedValue(){
        return getValue("computed");
    }
    public void setComputedValue(Double value){
        setValue("computed",value);
    }
    public Double getListedValue(){
        return getValue("listed");
    }
    public void setListedValue(Double value){
        setValue("listed",value);
    }
    public Double getOfferedValue(){
        return getValue("offered");
    }
    public void setOfferedValue(Double value){
        setValue("offered",value);
    }

    private Double getValue(String prefix){
        return getDouble(prefix+"_value",null);
    }
    private void setValue(String prefix,Double value){
        set(prefix+"_value",value);
    }




}
