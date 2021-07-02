package in.succinct.beckn;

public class Quantity extends BecknObject{
    public Measure getMeasure(){
        return get(Measure.class, "measure");
    }
    public void setMeasure(Measure measure){
        set("measure",measure);
    }
    public int getCount(){
        return getInteger("count");
    }
    public void setCount(int count){
        set("count",count);
    }
}
