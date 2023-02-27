package in.succinct.beckn;

import in.succinct.beckn.BecknObject;

import java.util.HashSet;
import java.util.Set;

public class FeedbackUrl extends BecknObject {
    public FeedbackUrl() { super();}

    public String getUrl(){
        return get("url");
    }
    public void setUrl(String url){
        set("url",url);
    }

    public String getTlMethod(){
        return get("tl_method");
    }
    static final Set<String> TL_METHOD = new HashSet<String>(){{
        add("http/get");
        add("http/post");
    }};
    public void setTlMethod(String tl_method){
        if (!TL_METHOD.contains(tl_method)){
            throw new IllegalArgumentException();
        }
        set("tl_method",tl_method);
    }

    public static class Params extends BecknObject {
        public String getFeedbackId(){
            return get("feedback_id");
        }
        public void setFeedbackId(String feedback_id){
            set("feedback_id",feedback_id);
        }

        public String getAdditionalProperties(){
            return get("additionalProperties");
        }
        public void setAdditionalProperties(String additionalProperties){
            set("additionalProperties",additionalProperties);
        }
        @Override
        public boolean hasAdditionalProperties() {
            return true;
        }
    }
}
