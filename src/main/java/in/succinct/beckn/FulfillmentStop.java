package in.succinct.beckn;

import com.venky.core.util.ObjectHolder;
import com.venky.core.util.ObjectUtil;
import com.venky.geo.GeoCoordinate;
import org.json.simple.JSONObject;

import java.math.BigDecimal;
import java.util.StringTokenizer;

public class FulfillmentStop extends BecknObject {
    public FulfillmentStop() {
        super();
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

    public Circle getCircle(){
        return get(Circle.class,"circle");
    }

}
