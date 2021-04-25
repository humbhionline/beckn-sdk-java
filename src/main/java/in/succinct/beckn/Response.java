package in.succinct.beckn;

public class Response extends BecknObject {
    public Response(Context context, Acknowledgement acknowledgement){
        super();
        setContext(context);
        setAcknowledgement(acknowledgement);
    }

    public  Context getContext(){
        return get(Context.class,"context");
    }

    public Acknowledgement getAcknowledgement(){
        return get(Acknowledgement.class,"message");
    }

    public void setContext(Context context){
        set("context",context.toString());
    }

    public void setAcknowledgement(Acknowledgement acknowledgement){
        set("message",acknowledgement.toString());
    }

}
