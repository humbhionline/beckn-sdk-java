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
}
