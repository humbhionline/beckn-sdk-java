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
    public void setAction(String action){
        set("action",action);
    }


    public String getCoreVersion() {
        return get("core_version");
    }
    public void setCoreVersion(String coreVersion){
        set("core_version",coreVersion);
    }

    public String getBapId(){
        return get("bap_id");
    }
    public void setBapId(String bap_id){
        set("bap_id",bap_id);
    }

    public String getBapUri(){
        return get("bap_uri");
    }
    public void setBapUri(String bap_uri){
        set("bap_uri",bap_uri);
    }


    public String getBppId(){
        return get("bpp_id");
    }
    public void setBppId(String bpp_id){
        set("bpp_id",bpp_id);
    }

    public String getBppUri(){
        return get("bpp_uri");
    }
    public void setBppUri(String bpp_uri){
        set("bpp_uri",bpp_uri);
    }

    public String getTransactionId(){
        return get("transaction_id");
    }
    public void setTransactionId(String transaction_id){
        set("transaction_id",transaction_id);
    }

    public String getMessageId(){
        return get("message_id");
    }
    public void setMessageId(String message_id){
        set("message_id",message_id);
    }

    public Date getTimestamp(){
        return getDate("timestamp",TIMESTAMP_FORMAT);
    }
    public void setTimestamp(Date timestamp){
        set("timestamp",timestamp,TIMESTAMP_FORMAT);
    }

    public String getKey(){
        return get("key");
    }
    public void setKey(String key){
        set("key",key);
    }
    public long getTtl(){
        String ttl = get("ttl");
        return ttl == null ? 10 : Duration.parse(ttl).getSeconds();
    }
    public void setTtl(long ttl){
        set("ttl",Duration.ofSeconds(ttl).toString());
    }

}
