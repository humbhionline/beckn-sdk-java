package in.succinct.beckn;

public class OrderFulfillment extends Fulfillment{
    public OrderFulfillment() {
        super();
    }
    public Contact getCustomer(){
        return get(Contact.class,"customer");
    }


}
