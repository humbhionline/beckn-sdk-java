package in.succinct.beckn;

import com.venky.core.util.ObjectHolder;
import com.venky.core.util.ObjectUtil;
import com.venky.geo.GeoCoordinate;

import java.math.BigDecimal;
import java.util.StringTokenizer;

public class Circle extends BecknObject {
    public Circle(){
        super();
    }

    public Scalar getRadius(){
        return get(Scalar.class, "radius");
    }
    public void setRadius(Scalar radius){
        set("radius",radius);
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

}
