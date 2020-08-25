import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * InventoryAllocator class for allocating shipments based on orders made from different warehouses.
 * */
public class InventoryAllocator {
    private static final Logger logger = Logger.getLogger(InventoryAllocator.class.getName());
    private static InventoryAllocator inventoryAllocator;
    private InventoryAllocator() {}

    /**
     * InventoryAllocator instance method. Singleton Design Pattern.
     * @return single instance of InventoryAllocator
     * */
    public static InventoryAllocator getInventoryAllocatorInstance() {
        if(inventoryAllocator == null)
            inventoryAllocator = new InventoryAllocator();
        return inventoryAllocator;
    }

    /**
     * @param order
     * @param warehouses
     * @return list of shipments
     */
    public List<Shipment> allocateShipment(Order order, List<Warehouse> warehouses) {
        List<Shipment> shipments = new ArrayList<Shipment>();

        /* Iterating over all the warehouses to find the items to ship from the order list */
        for(Warehouse warehouse: warehouses) {
            Shipment shipment = warehouse.processOrder(order);
            if(shipment != null) {
                shipments.add(shipment);
            }
            if(order.getOrderSize()) {
                logger.info("All shipments are done");
                return shipments;
            }
        }

        /* returning empty shipment when there are valid items found in the warehouse or orders is empty */
        List<Shipment> emptyShipment = new ArrayList<Shipment>();
        return emptyShipment;
    }
}
