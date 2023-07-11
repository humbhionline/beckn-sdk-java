package in.succinct.beckn;

public class Price extends Amount {
    public Price() {
        super();
    }

    public double getMinimumValue(){
        return getValue("minimum");
    }
    public void setMinimumValue(double minimum){
        setValue("minimum",minimum);
    }
    public double getMaximumValue(){
        return getValue("maximum");
    }
    public void setMaximumValue(double maximum){
        setValue("maximum",maximum);
    }
    @Deprecated
    public double getEstimatedValue(){
        return getValue("estimated");
    }
    public void setEstimatedValue(double estimatedValue){
        setValue("estimated",estimatedValue);
    }

    public double getComputedValue(){
        return getValue("computed");
    }
    public void setComputedValue(double value){
        setValue("computed",value);
    }
    public double getListedValue(){
        return getValue("listed");
    }
    public void setListedValue(double value){
        setValue("listed",value);
    }
    public double getOfferedValue(){
        return getValue("offered");
    }
    public void setOfferedValue(double value){
        setValue("offered",value);
    }

    private double getValue(String prefix){
        return getDouble(prefix+"_value");
    }
    private void setValue(String prefix,double value){
        set(prefix+"_value",String.valueOf(value));
    }




}
