import java.util.HashMap;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Order class which stores different orders made
 * */
public class Order {
    private static final Logger logger = Logger.getLogger(Order.class.getName());
    private HashMap<String, Integer> orderItems = new HashMap<String, Integer>(); // map to store the orders and it's quantity

    /**
     * @param orderItemName
     * @param orderItemQuantity
     */
    public void addOrders(String orderItemName, int orderItemQuantity){
        if(orderItemQuantity <0 || orderItemName.isEmpty()){
            throw new IllegalArgumentException("Please pass valid input");
        }
        orderItems.put(orderItemName, orderItemQuantity);
    }

    /**
     *
     * @param orderItemName
     * @return quantity of the orderItem which was ordered
     */
    public int getOrderItemsQuantity(String orderItemName) {
        return orderItems.getOrDefault(orderItemName, -1);
    }

    /**
     * @return set of name of the orderItems
     */
    public Set<String> getOrderItemsName() {
        return orderItems.keySet();
    }

    /**
     * @param orderItemName
     */
    public void removeOrderItems(String orderItemName) {
        orderItems.remove(orderItemName);
    }

    /**
     * @return size of orderItems
     */
    public boolean getOrderSize() {
        return orderItems.size() == 0;
    }
}
