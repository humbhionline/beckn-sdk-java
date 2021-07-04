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
        return get(Order.class, "initialized");
    }
    public void setInitialized(Order initialized){
        set("initialized",initialized);
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
        return get(Order.class, "selected");
    }
    public void setSelected(Order selected){
        set("selected",selected);
    }
}
