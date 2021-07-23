package in.succinct.beckn;

import com.venky.core.util.ObjectHolder;
import com.venky.core.util.ObjectUtil;
import com.venky.geo.GeoCoder;
import com.venky.geo.GeoCoordinate;
import com.venky.geo.GeoLocation;
import org.json.simple.JSONObject;

import java.math.BigDecimal;
import java.util.StringTokenizer;

public class Location extends BecknObjectWithId {
    public Location() {
        super();
    }
    public Location(String payload){
        super(payload);
    }


    ObjectHolder<GeoCoordinate> gps = null ;
    public GeoCoordinate getGps(){
        if (this.gps == null){
            this.gps = new ObjectHolder<>(null);
            String gps = get("gps");
            if (!ObjectUtil.isVoid(gps)) {
                StringTokenizer tokenizer = new StringTokenizer(gps, ",");
                String lat = tokenizer.nextToken();
                String lng = tokenizer.nextToken();
                this.gps.set(new GeoCoordinate(new BigDecimal(lat),new BigDecimal(lng)));
            }
        }
        return this.gps.get();
    }
    public void setGps(GeoCoordinate coordinate){
        set("gps",String.format("%f,%f",coordinate.getLat(),coordinate.getLng()));
        if (this.gps == null){
            this.gps = new ObjectHolder<>(coordinate);
        }else {
            this.gps.set(coordinate);
        }
    }

    public City getCity(){
        return get(City.class,"city");
    }
    public void setCity(City city){
        set("city",city);
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
        return get(Address.class,"address");
    }
    public void setAddress(Address address){
        set("address",address);
    }

    public Descriptor getDescriptor(){
        return get(Descriptor.class,"descriptor");
    }

    public void setDescriptor(Descriptor descriptor){
        set("descriptor",descriptor);
    }


}
