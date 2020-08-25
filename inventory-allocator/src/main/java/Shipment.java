import java.util.HashMap;
import java.util.logging.Logger;

/**
 * Shipment class which to store a valid shipment
 * */
public class Shipment {
    private static final Logger logger = Logger.getLogger(Shipment.class.getName());
    private HashMap<String, Integer> orderItems = new HashMap<String, Integer>(); // map to store the orders and it's quantity in the given warehouse
    private String warehouseName = null;

    /**
     * Constructor which sets warehouse name
     *
     * @param warehouseName
     * */
    public Shipment(String warehouseName) {
        this.warehouseName = warehouseName;
    }

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
     * Overriding the default equals method to compare two shipments
     *
     * @param obj (Object of Shipment Instance)
     * @return true/false based on shipment match
     * */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Shipment))
            return false;

        Shipment shipment = (Shipment) obj;
        boolean compareWareHouseNames = shipment.warehouseName == this.warehouseName;
        boolean compareShipmentList =   shipment.orderItems.equals(this.orderItems);

        return compareWareHouseNames && compareShipmentList;
    }
}
