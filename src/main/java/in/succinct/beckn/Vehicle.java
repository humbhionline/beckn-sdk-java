package in.succinct.beckn;

public class Vehicle extends BecknObject{

    public Category getCategory(){
        return get(Category.class, "category");
    }
    public void setCategory(Category category){
        set("category",category);
    }
    public int getCapacity(){
        return getInteger("capacity");
    }
    public void setCapacity(int capacity){
        set("capacity",capacity);
    }
    public String getMake(){
        return get("make");
    }
    public void setMake(String make){
        set("make",make);
    }
    public String getModel(){
        return get("model");
    }
    public void setModel(String model){
        set("model",model);
    }
    public String getSize(){
        return get("size");
    }
    public void setSize(String size){
        set("size",size);
    }
    public String getVariant(){
        return get("variant");
    }
    public void setVariant(String variant){
        set("variant",variant);
    }
    public String getColor(){
        return get("color");
    }
    public void setColor(String color){
        set("color",color);
    }
    public String getEnergyType(){
        return get("energy_type");
    }
    public void setEnergyType(String energy_type){
        set("energy_type",energy_type);
    }
    public String getRegistration(){
        return get("registration");
    }
    public void setRegistration(String registration){
        set("registration",registration);
    }

}
