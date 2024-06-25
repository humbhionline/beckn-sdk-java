package in.succinct.beckn;

import org.json.simple.JSONObject;

public class Form extends BecknObject{
    public Form() {
    }

    public Form(String payload) {
        super(payload);
    }

    public Form(JSONObject object) {
        super(object);
    }

    public String getUrl(){
        return get("url");
    }
    public void setUrl(String url){
        set("url",url);
    }

    public String getData(){
        return get("data");
    }
    public void setData(String data){
        set("data",data);
    }

    public String getMimeType(){
        return get("mime_type");
    }
    public void setMimeType(String mime_type){
        set("mime_type",mime_type);
    }

    public boolean isResubmit(){
        return getBoolean("resubmit");
    }
    public void setResubmit(boolean resubmit){
        set("resubmit",resubmit);
    }

    public String getSubmissionId(){
        return get("submission_id");
    }
    public void setSubmissionId(String submission_id){
        set("submission_id",submission_id);
    }

    public static class Auth extends BecknObject {
        public Auth() {
        }

        public Auth(String payload) {
            super(payload);
        }

        public Auth(JSONObject object) {
            super(object);
        }
        public Descriptor getDescriptor(){
            return get(Descriptor.class, "descriptor");
        }
        public void setDescriptor(Descriptor descriptor){
            set("descriptor",descriptor);
        }
        public String getValue(){
            return get("value");
        }
        public void setValue(String value){
            set("value",value);
        }
    }

}
