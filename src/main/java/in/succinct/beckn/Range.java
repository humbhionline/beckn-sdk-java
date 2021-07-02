package in.succinct.beckn;

public class Range extends BecknObject{
    public Range() {
    }

    public Range(String payload) {
        super(payload);
    }

    public double getMin(){
        return getDouble("min");
    }
    public void setMin(double min){
        set("min",min);
    }
    public double getMax(){
        return getDouble("max");
    }
    public void setMax(double max){
        set("max",max);
    }


}
