package in.succinct.beckn;

public class Quantity extends BecknObject{
    public double getMeasure(){
        return getDouble("measure");
    }
    public void setMeasure(double measure){
        set("measure",measure);
    }
    public int getCount(){
        return getInteger("count");
    }
    public void setCount(int count){
        set("count",count);
    }
}
