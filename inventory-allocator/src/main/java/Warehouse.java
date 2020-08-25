import java.util.HashMap;
import java.util.logging.Logger;

/**
 * Warehouse class to store the items available at a warehouse (given warehouse name)
 * */
public class Warehouse {

    private static final Logger logger = Logger.getLogger(Warehouse.class.getName());
    private HashMap<String, Integer> orderItems = new HashMap<String, Integer>();// map to store the orders and it's quantity for a given warehouse
    private String warehouseName = null;

    public Warehouse(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    /**
     * Adds new orders to the warehouse
     *
     * @param orderName
     * @param orderQuantity
     */
    public void addOrders(String orderName, int orderQuantity) {
        orderItems.put(orderName,orderQuantity);
    }

    /**
     *
     * @param order
     * @return Shipment or null if warehouse has no orderItems to ship.
     */
    public Shipment processOrder(Order order) {
        HashMap<String, Integer> ordersToShip = new HashMap<>();
        for (String orderName : order.getOrderItemsName()) {
            if (orderItems.containsKey(orderName)){
                int orderQuantity = Math.min(order.getOrderItemsQuantity(orderName), orderItems.get(orderName));
                ordersToShip.put(orderName, orderQuantity);
            }
        }

        /* When there are no orders to ship from a warehouse, shipment list is empty. Hence, returning null */
        if(ordersToShip.size() == 0) {
            return null;
        }

        Shipment shipment = new Shipment(this.warehouseName);
        try {
            for (HashMap.Entry<String, Integer> orderItem : ordersToShip.entrySet()) {
                String orderName = orderItem.getKey();
                int orderQuantity = orderItem.getValue();

                /* Adding a particular shipment for a given order from a warehouse */
                shipment.addOrders(orderName, orderQuantity);

                /* Reducing the number of orderItems quantity from orders that are shipped from given warehouse */
                if (orderQuantity >= order.getOrderItemsQuantity(orderName)) {
                    order.removeOrderItems(orderName);
                } else {
                    order.addOrders(orderName, order.getOrderItemsQuantity(orderName) - orderQuantity);
                }

                /* Reducing the number of orderItems quantity from warehouse that are shipped for a given order */
                if (orderQuantity >= orderItems.get(orderName)) {
                    orderItems.remove(orderName);
                } else {
                    orderItems.put(orderName, orderItems.get(orderName) - orderQuantity);
                }
            }
        } catch (IllegalArgumentException e) {
                logger.info("Invalid input. Check the input parameters passed : " + e);
        }

        return shipment;
    }
}
