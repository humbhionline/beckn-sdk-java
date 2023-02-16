package in.succinct.beckn;

public class Address extends BecknObject {
    public Address() {
        super();
    }

    public String getDoor(){
        return get("door");
    }
    public void setDoor(String door){
        set("door",door);
    }
    public String getName(){
        return get("name");
    }
    public void setName(String name){
        set("name",name);
    }
    public String getBuilding(){
        return get("building");
    }
    public void setBuilding(String building){
        set("building",building);
    }
    public String getStreet(){
        return get("street");
    }
    public void setStreet(String street){
        set("street",street);
    }

    public String getLocality(){
        return get("locality");
    }
    public void setLocality(String locality){
        set("locality",locality);
    }

    public String getWard(){
        return get("ward");
    }
    public void setWard(String ward){
        set("ward",ward);
    }

    public String getCity(){
        return get("city");
    }
    public void setCity(String city){
        set("city",city);
    }
    public String getState(){
        return get("state");
    }
    public void setState(String state){
        set("state",state);
    }
    public String getCountry(){
        return get("country");
    }
    public void setCountry(String country){
        set("country",country);
    }
    public String getPinCode(){
        return get("area_code");
    }
    public void setPinCode(String area_code){
        set("area_code",area_code);
    }

    public String getZipCode(){
        return getPinCode();
    }
    public void setZipCode(String zipCode){
        setPinCode(zipCode);
    }

    public String getAreaCode(){
        return getPinCode();
    }
    public void setAreaCode(String areaCode){
        setPinCode(areaCode);
    }

    public String flatten() {
        StringBuilder s = new StringBuilder();
        s.append(_flat(getName()));
        s.append(_flat(getDoor()));
        s.append(_flat(getBuilding()));
        s.append(_flat(getStreet()));
        s.append(_flat(getLocality())).append(_flat(getWard())).append(_flat(getCity())).append(_flat(getState())).append(_flat(getCountry())).append(_flat(getPinCode()));
        return s.toString();
    }

}
