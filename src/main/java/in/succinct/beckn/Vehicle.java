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

    public Integer getWheelsCount(){
        return getInteger("wheels_count");
    }
    public void setWheelsCount(Integer wheels_count){
        set("wheels_count",wheels_count == null ? null  :String.valueOf(wheels_count));
    }
    
    public String getCargoVolume(){
        return get("cargo_volume");
    }
    public void setCargoVolume(String cargo_volume){
        set("cargo_volume",cargo_volume);
    }

    public String getWheelchairAccess(){
        return get("wheelchair_access");
    }
    public void setWheelchairAccess(String wheelchair_access){
        set("wheelchair_access",wheelchair_access);
    }
    public String getCode(){
        return get("code");
    }
    public void setCode(String code){
        set("code",code);
    }
    public String getEmissionStandard(){
        return get("emission_standard");
    }
    public void setEmissionStandard(String emission_standard){
        set("emission_standard",emission_standard);
    }

}
