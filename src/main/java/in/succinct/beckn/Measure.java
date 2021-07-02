package in.succinct.beckn;

import java.util.Arrays;

public class Measure extends BecknObject{
    public Measure(){

    }

    public Measure(String payload) {
        super(payload);
    }

    public double getValue(){
        return getDouble("value");
    }
    public void setValue(double value){
        set("value",value);
    }
    
    public double getEstimatedValue(){
        return getDouble("estimated_value");
    }
    public void setEstimatedValue(double estimated_value){
        set("estimated_value",estimated_value);
    }
    public double getComputedValue(){
        return getDouble("computed_value");
    }
    public void setComputedValue(double computed_value){
        set("computed_value",computed_value);
    }

    public String getUnit(){
        return get("unit");
    }
    public void setUnit(String unit){
        set("unit",unit);
    }

    public String getType(){
        return get("type");
    }
    public void setType(String type){
        String[] allowed = new String[]{"CONSTANT","VARIABLE"};
        if (!Arrays.asList(allowed).contains(type)){
            throw new IllegalArgumentException("Only values allowed are " + Arrays.toString(allowed));
        }
        set("type",type);
    }

}
