package in.succinct.beckn;

import com.venky.core.util.ObjectHolder;
import com.venky.core.util.ObjectUtil;
import com.venky.geo.GeoCoordinate;

import java.math.BigDecimal;
import java.util.StringTokenizer;

public class Location extends BecknObjectWithId implements AddressHolder{
    public Location() {
        super();
    }
    public Location(String payload){
        super(payload);
    }


    ObjectHolder<GeoCoordinate> gps = null ;
    public GeoCoordinate getGps(){
        if (this.gps != null){
            return this.gps.get();
        }
        String gps = get("gps");
        if (ObjectUtil.isVoid(gps)) {
            return null;
        }
        StringTokenizer tokenizer = new StringTokenizer(gps, ",");
        String lat = tokenizer.nextToken().trim();
        String lng = tokenizer.nextToken().trim();
        this.gps = new ObjectHolder<>(new GeoCoordinate(new BigDecimal(lat),new BigDecimal(lng)));
        return this.gps.get();
    }
    public void setGps(GeoCoordinate coordinate){
        if (coordinate == null){
            rm("gps");
        }else {
            set("gps", String.format("%f,%f", coordinate.getLat(), coordinate.getLng()));
        }
        if (this.gps == null){
            this.gps = new ObjectHolder<>(coordinate);
        }else {
            this.gps.set(coordinate);
        }
    }

    public String getDistrict(){
        return get("district");
    }
    public void setDistrict(String district){
        set("district",district);
    }


    public City getCity(){
        return get(City.class,"city");
    }
    public void setCity(City city){
        set("city",city);
    }

    public State getState(){
        return get(State.class, "state");
    }
    public void setState(State state){
        set("state",state);
    }

    public Country getCountry(){
        return get(Country.class,"country");
    }
    public void setCountry(Country country){
        set("country",country);
    }

    public Circle getCircle(){
        return get(Circle.class ,"circle");
    }
    public void setCircle(Circle circle){
        set("circle",circle);
    }



    public Address getAddress(){
        return AddressHolder.super.getAddress();
    }
    public void setAddress(Address address){
        AddressHolder.super.setAddress(address);
    }

    public Descriptor getDescriptor(){
        return get(Descriptor.class,"descriptor");
    }

    public void setDescriptor(Descriptor descriptor){
        set("descriptor",descriptor);
    }

    public Time getTime(){
        return get(Time.class,"time");
    }
    public void setTime(Time time){
        set("time",time);
    }

    public String getMapUrl(){
        return get("map_url");
    }
    public void setMapUrl(String map_url){
        set("map_url",map_url);
    }

    public String getAreaCode(){
        return get("area_code");
    }
    public void setAreaCode(String area_code){
        set("area_code",area_code);
    }

    public String getPinCode(){
        return getAreaCode();
    }
    public void setPinCode(String pin_code){
        setAreaCode(pin_code);
    }

    public int getRating(){
        return getInteger("rating");
    }
    public void setRating(int rating){
        set("rating",rating);
    }

}
