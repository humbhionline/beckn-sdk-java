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

    public Items getItems(){
        return get(Items.class,"items");
    }

    public void setQuote(Quote quote) {
        set("quote",quote.getInner());
    }
    public Quote getQuote(){
        return get(Quote.class,"quote");
    }

    public Initialized getInitialized(){
        return get(Initialized.class, "initialized");
    }
    public void setInitialized(Initialized initialized){
        set("initialized",initialized);
    }
}
