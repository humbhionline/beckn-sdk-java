package in.succinct.beckn;

import in.succinct.beckn.BecknObject;
import java.util.HashSet;
import java.util.Set;

public class Scalar extends BecknObject {
    public Scalar() { super(); }


    public double getValue(){
        return getDouble("value" ,0D);
    }
    public void setValue(double value){
        set("value",value);
    }

    public String getUnit(){
        return get("unit");
    }
    public void setUnit(String unit){
        set("unit",unit);
    }

    public double getEstimatedValue(){
        return getDouble("estimated_value",0D);
    }
    public void setEstimatedValue(double estimated_value){
        set("estimated_value",estimated_value);
    }

    public double getComputedValue(){
        return getDouble("computed_value",0D);
    }
    public void setComputedValue(double computed_value){
        set("computed_value",computed_value);
    }

    public Type getType(){
        return getEnum(Type.class, "type");
    }
    public void setType(Type type){
        setEnum("type",type);
    }

    public enum Type {
        CONSTANT,
        VARIABLE
    }

    public Range getRange(){
        return get(Range.class,"range");
    }
    public void setRange(Range range){
        set("range",range);
    }
}
