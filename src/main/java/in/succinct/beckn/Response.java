package in.succinct.beckn;

import org.json.simple.JSONObject;




public class Response extends BecknObject {
    public Response(String payload){
        super(payload);
    }
    public Response(JSONObject object){
        super(object);
    }
    public Response(Acknowledgement acknowledgement){
        super();
        setAcknowledgement(acknowledgement);
    }

    public Acknowledgement getAcknowledgement(){
        Message message = get(Message.class,"message");
        return message.getAcknowledgement();
    }


    public void setAcknowledgement(Acknowledgement acknowledgement){
        JSONObject ack = new JSONObject();
        ack.put("ack",acknowledgement.getInner());
        set("message",ack);
    }

    public void setError(Error error){
        set("error",error);
    }
    public Error getError(){
        return get(Error.class,"error");
    }

}
