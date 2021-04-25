package in.succinct.beckn;

public class Acknowledgement extends BecknObject {
    public enum Status{
        ACK,
        NACK
    }
    public Acknowledgement(){
        super();
    }
    public Acknowledgement(Status status){
        super();
        setStatus(status);
    }

    public Status getStatus(){
        return Status.valueOf(get("status"));
    }

    public void setStatus(Status status){
        set("status",status.toString());
    }

    public void setSignature(String signature){
        set("signature",signature);
    }

    public String getSignature(){
        return get("signature");
    }
}
