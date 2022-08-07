package in.succinct.beckn;

public class Message extends BecknObject {
    public Message() {
        super();
    }
    public Message(String payload){
        super(payload);
    }
    public Intent getIntent(){
        return get(Intent.class,"intent");
    }
    public void setIntent(Intent intent){
        set("intent",intent.getInner());
    }
    public Catalog getCatalog(){
        return get(Catalog.class,"catalog");
    }

    public void setCatalog(Catalog catalog){
        set("catalog",catalog.getInner());
    }

    public Order getOrder(){
        return get(Order.class,"order");
    }

    public void setOrder(Order order){
        set("order",order.getInner());
    }

    public Order getInitialized(){
        return getOrder();
    }
    public void setInitialized(Order initialized){
        setOrder(initialized);
    }

    public Items getItems(){
        return get(Items.class,"items");
    }

    public void setQuote(Quote quote) {
        set("quote",quote.getInner());
    }
    public Quote getQuote(){
        return get(Quote.class,"quote");
    }

    public Order getSelected(){
        return getOrder();
    }
    public void setSelected(Order selected){
        setOrder(selected);
    }

    public Acknowledgement getAcknowledgement(){
        return get(Acknowledgement.class,"ack");
    }

    public Rating getRating(){
        return get(Rating.class, "rating");
    }
    public void setRating(Rating rating){
        set("rating",rating);
    }
    
    
    public String getPhone(){
        return get("phone");
    }
    public void setPhone(String phone){
        set("phone",phone);
    }
    
    public String getEmail(){
        return get("email");
    }
    public void setEmail(String email){
        set("email",email);
    }

    public String getUrl(){
        return get("url");
    }
    public void setUrl(String url){
        set("url",url);
    }

    public Tracking getTracking(){
        return get(Tracking.class, "tracking");
    }
    public void setTracking(Tracking tracking){
        set("tracking",tracking);
    }

}
