package in.succinct.beckn;

import com.venky.core.date.DateUtils;
import org.json.simple.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.time.Duration;
import java.util.Date;

public class Context extends BecknObject {

    public Context() {
        super();
    }

    public String getDomain(){
        return get("domain");
    }

    public Country getCountry(){
        return get(Country.class,"country");
    }
    public City getCity(){
        return get(City.class,"city");
    }

    public String getAction(){
        return get("action");
    }

    public String getCoreVersion() {
        return get("core_version");
    }

    public String getDomainVersion(){
        return get("domain_version");
    }

    public String getBapId(){
        return get("bap_id");
    }

    public String getBapUri(){
        return get("bap_uri");
    }
    public String getBppId(){
        return get("bpp_id");
    }

    public String getBppUri(){
        return get("bpp_uri");
    }

    public String getTransactionId(){
        return get("transaction_id");
    }
    public String getMessageId(){
        return get("message_id");
    }

    public Date getTimestamp(){
        DateFormat format = DateUtils.getFormat("dd/MM/yyyy HH:mm:ssXXX");
        try {
            return format.parse(get("timestamp"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public String getKey(){
        return get("key");
    }

    public long getTtl(){
        String ttl = get("ttl");
        return ttl == null ? 10 : Duration.parse(ttl).getSeconds();
    }

}
