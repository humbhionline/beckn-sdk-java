package in.succinct.beckn.metrics;

import in.succinct.beckn.BecknObject;
import in.succinct.beckn.Provider;

public class ProviderMetric extends BecknObject {
    public Provider getProvider(){
        return get(Provider.class, "provider");
    }
    public void setProvider(Provider provider){
        set("provider",provider);
    }
    public int getNumOrdersReceived(){
        return getInteger("num_orders_received");
    }
    public void setNumOrdersReceived(int num_orders_received){
        set("num_orders_received",num_orders_received);
    }

    public int getNumItemsOrdered(){
        return getInteger("num_items_ordered");
    }
    public void setNumItemsOrdered(int num_items_ordered){
        set("num_items_ordered",num_items_ordered);
    }


    public int getNumOrdersFulfilled(){
        return getInteger("num_orders_fulfilled");
    }
    public void setNumOrdersFulfilled(int num_orders_fulfilled){
        set("num_orders_fulfilled",num_orders_fulfilled);
    }

    public int getNumItemsFulfilled(){
        return getInteger("num_items_fulfilled");
    }
    public void setNumItemsFulfilled(int num_items_fulfilled){
        set("num_items_fulfilled",num_items_fulfilled);
    }

    public int getNumOrdersShorted(){
        return getInteger("num_orders_shorted");
    }
    public void setNumOrdersShorted(int num_orders_shorted){
        set("num_orders_shorted",num_orders_shorted);
    }

    public int getNumItemsShorted(){
        return getInteger("num_items_shorted");
    }
    public void setNumItemsShorted(int num_items_shorted){
        set("num_items_shorted",num_items_shorted);
    }


    public int getNumOrdersReturned(){
        return getInteger("num_orders_returned");
    }
    public void setNumOrdersReturned(int num_orders_returned){
        set("num_orders_returned",num_orders_returned);
    }
    public int getNumItemsReturned(){
        return getInteger("num_items_returned");
    }
    public void setNumItemsReturned(int num_items_returned){
        set("num_items_returned",num_items_returned);
    }


    public int getNumMinutesToPack(){
        return getInteger("num_minutes_to_pack");
    }
    public void setNumMinutesToPack(int num_minutes_to_pack){
        set("num_minutes_to_pack",num_minutes_to_pack);
    }


    public int getNumMinutesToPickUp(){
        return getInteger("num_minutes_to_pick_up");
    }
    public void setNumMinutesToPickUp(int num_minutes_to_pick_up){
        set("num_minutes_to_pick_up",num_minutes_to_pick_up);
    }

    public int getNumMinutesToDeliver(){
        return getInteger("num_minutes_to_deliver");
    }
    public void setNumMinutesToDeliver(int num_minutes_to_deliver){
        set("num_minutes_to_deliver",num_minutes_to_deliver);
    }

    public int getNumOrdersPickedFromStore(){
        return getInteger("num_orders_picked_from_store");
    }
    public void setNumOrdersPickedFromStore(int num_orders_picked_from_store){
        set("num_orders_picked_from_store",num_orders_picked_from_store);
    }
    public int getNumOrdersDeliveredToCustomerLocation(){
        return getInteger("num_orders_delivered_to_customer_location");
    }
    public void setNumOrdersDeliveredToCustomerLocation(int num_orders_delivered_to_customer_location){
        set("num_orders_delivered_to_customer_location",num_orders_delivered_to_customer_location);
    }


}
