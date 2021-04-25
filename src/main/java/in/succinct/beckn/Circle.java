package in.succinct.beckn;

public class Circle extends BecknObject {
    public Circle(){
        super();
    }

    public double getRadius(){
        return getDouble("radius");
    }
    public void setRadius(double radius){
        set("radius",radius);
    }

}
