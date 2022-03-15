package in.succinct.beckn;

import org.json.simple.JSONObject;

public class Response extends BecknObject {
    public Response(String payload){
        super(payload);
    }
    public Response(Context context, Acknowledgement acknowledgement){
        super();
        setContext(context);
        setAcknowledgement(acknowledgement);
    }
    public Response(Acknowledgement acknowledgement){
        this(null,acknowledgement);
    }

    public  Context getContext(){
        return get(Context.class,"context");
    }

    public Acknowledgement getAcknowledgement(){
        Message message = get(Message.class,"message");
        return message.getAcknowledgement();
    }

    public void setContext(Context context){
        if (context != null) {
            set("context", context);
        }
    }

    public void setAcknowledgement(Acknowledgement acknowledgement){
        JSONObject ack = new JSONObject();
        ack.put("ack",acknowledgement.getInner());
        set("message",ack);
    }

}
