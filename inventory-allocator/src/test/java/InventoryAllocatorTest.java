import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

/**
 * Test utility to test InventoryAllocator class for different orders and different warehouse items.
 * */
public class InventoryAllocatorTest {

    private static final Logger logger = Logger.getLogger(InventoryAllocatorTest.class.getName());
    private InventoryAllocator inventoryAllocator = InventoryAllocator.getInventoryAllocatorInstance();

    /**
     * The following test case tests for when there are enough multiple orders
     * and multiple warehouses that are not empty
     * Result expected is proper shipment list containing items ordered from warehouses.
     * */
    @Test
    public void properManyShipmentManyOrderItemsManyWarehouses() {
        // orders
        Order order = new Order();
        order.addOrders("apple",60);
        order.addOrders("orange",300);

        //warehouses
        Warehouse warehouse1 = new Warehouse("FirstWarehouse");
        warehouse1.addOrders("apple",15);
        warehouse1.addOrders("orange", 200);
        Warehouse warehouse2 = new Warehouse("SecondWarehouse");
        warehouse2.addOrders("apple",50);
        warehouse2.addOrders("orange", 100);
        List <Warehouse> warehouses = new ArrayList<>();
        warehouses.add(warehouse1);
        warehouses.add(warehouse2);

        // shipments expected
        List<Shipment> expectedShipments = new ArrayList<>();
        Shipment firstShipmentShipment = new Shipment("FirstWarehouse");
        firstShipmentShipment.addOrders("apple", 15);
        firstShipmentShipment.addOrders("orange",  200);
        Shipment secondShipment = new Shipment("SecondWarehouse");
        secondShipment.addOrders("apple", 45);
        secondShipment.addOrders("orange", 100);
        expectedShipments.add(firstShipmentShipment);
        expectedShipments.add(secondShipment);

        List<Shipment> finalShipments = inventoryAllocator.allocateShipment(order, warehouses);

        // expected shipment == finalShipments
        assertEquals(expectedShipments, finalShipments);
    }

    /**
     * The following test case tests for when there is only order and one warehouse
     * Result expected is proper shipment list containing item ordered from only warehouse.
     * */
    @Test
    public void properShipmentWithOnlyOneOrderSingleWarehouses() {
        // order
        Order order = new Order();
        order.addOrders("apple",60);

        //warehouse
        Warehouse warehouse = new Warehouse("FirstWarehouse");
        warehouse.addOrders("apple",320);
        warehouse.addOrders("orange", 10);
        warehouse.addOrders("banana",30);
        warehouse.addOrders("mango", 70);
        List <Warehouse> warehouses = new ArrayList<>();
        warehouses.add(warehouse);

        // shipments expected
        List<Shipment> expectedShipments = new ArrayList<>();
        Shipment shipment = new Shipment("FirstWarehouse");
        shipment.addOrders("apple", 60);
        expectedShipments.add(shipment);

        List<Shipment> finalShipments = inventoryAllocator.allocateShipment(order, warehouses);

        // expected shipment == finalShipments
        assertEquals(expectedShipments, finalShipments);
    }

    /**
     * The following test case tests for when there are enough multiple orders
     * and only single warehouses that are not empty
     * Result expected is proper shipment list containing items ordered from first only warehouses.
     * */
    @Test
    public void properShipmentWithManyOrderItemsSingleWarehouse() {
        // orders
        Order order = new Order();
        order.addOrders("apple",60);
        order.addOrders("orange",300);
        order.addOrders("banana",6);
        order.addOrders("mango",30);

        //warehouses
        Warehouse warehouse = new Warehouse("FirstWarehouse");
        warehouse.addOrders("apple",90);
        warehouse.addOrders("orange", 500);
        warehouse.addOrders("banana",9);
        warehouse.addOrders("mango", 50);
        warehouse.addOrders("pineapple",190);
        warehouse.addOrders("coconut", 150);
        List <Warehouse> warehouses = new ArrayList<>();
        warehouses.add(warehouse);

        // shipments expected
        List<Shipment> expectedShipments = new ArrayList<>();
        Shipment shipment = new Shipment("FirstWarehouse");
        shipment.addOrders("apple", 60);
        shipment.addOrders("orange",  300);
        shipment.addOrders("banana", 6);
        shipment.addOrders("mango",  30);
        expectedShipments.add(shipment);
        List<Shipment> finalShipments = inventoryAllocator.allocateShipment(order, warehouses);

        // expected shipment == finalShipments
        assertEquals(expectedShipments, finalShipments);
    }

    /**
     * The following test case tests for when there are enough multiple orders
     * and multiple warehouses that are not empty
     * Result expected is a single proper shipment list containing items ordered from first warehouse.
     * */
    @Test
    public void properSingleShipmentManyOrderItemsManyWarehouses() {
        // orders
        Order order = new Order();
        order.addOrders("apple",60);
        order.addOrders("orange",300);

        //warehouses
        Warehouse warehouse1 = new Warehouse("FirstWarehouse");
        warehouse1.addOrders("apple",90);
        warehouse1.addOrders("orange", 600);
        Warehouse warehouse2 = new Warehouse("SecondWarehouse");
        warehouse2.addOrders("apple",50);
        warehouse2.addOrders("orange", 300);
        Warehouse warehouse3 = new Warehouse("ThirdWarehouse");
        warehouse3.addOrders("apple",30);
        warehouse3.addOrders("orange", 500);
        List <Warehouse> warehouses = new ArrayList<>();
        warehouses.add(warehouse1);
        warehouses.add(warehouse2);
        warehouses.add(warehouse3);

        // shipments expected
        List<Shipment> expectedShipments = new ArrayList<>();
        Shipment firstShipmentShipment = new Shipment("FirstWarehouse");
        firstShipmentShipment.addOrders("apple", 60);
        firstShipmentShipment.addOrders("orange",  300);
        expectedShipments.add(firstShipmentShipment);

        List<Shipment> finalShipments = inventoryAllocator.allocateShipment(order, warehouses);

        // expected shipment == finalShipments
        assertEquals(expectedShipments, finalShipments);
    }

    /**
     * The following test case tests for when there are enough multiple orders
     * and multiple warehouses that are not empty
     * Result expected is a single proper shipment list containing items ordered from first warehouse.
     * */
    @Test
    public void incompleteShipmentWithOneOrderItemOneWarehouses() {
        // order
        Order order = new Order();
        order.addOrders("apple",60);

        //warehouse
        Warehouse warehouse1 = new Warehouse("FirstWarehouse");
        warehouse1.addOrders("apple",10);
        warehouse1.addOrders("orange", 60);
        List <Warehouse> warehouses = new ArrayList<>();
        warehouses.add(warehouse1);

        // empty shipments expected
        List<Shipment> expectedShipments = new ArrayList<>();
        List<Shipment> finalShipments = inventoryAllocator.allocateShipment(order, warehouses);

        // expected shipment == finalShipments  == empty list (as they are no enough items in the ware house)
        assertEquals(expectedShipments, finalShipments);
    }

    /**
     * The following test case tests for when there are enough multiple orders
     * and multiple warehouses that are not empty
     * Result expected is a single proper shipment list containing items ordered from first warehouse.
     * */
    @Test
    public void incompleteShipmentWithManyOrderItemOneWarehouses() {
        // order
        Order order = new Order();
        order.addOrders("apple",60);
        order.addOrders("banana",150);
        order.addOrders("orange",10);

        //warehouse
        Warehouse warehouse1 = new Warehouse("FirstWarehouse");
        warehouse1.addOrders("apple",10);
        warehouse1.addOrders("banana", 60);
        List <Warehouse> warehouses = new ArrayList<>();
        warehouses.add(warehouse1);

        // empty shipments expected
        List<Shipment> expectedShipments = new ArrayList<>();
        List<Shipment> finalShipments = inventoryAllocator.allocateShipment(order, warehouses);

        // expected shipment == finalShipments  == empty list (as they are no enough items in the ware house)
        assertEquals(expectedShipments, finalShipments);
    }

    /**
     * The following test case tests for when there are enough multiple orders
     * and multiple warehouses that are not empty
     * Result expected is a single proper shipment list containing items ordered from first warehouse.
     * */
    @Test
    public void incompleteShipmentWithManyOrderItemManyWarehouses() {
        // order
        Order order = new Order();
        order.addOrders("apple",60);
        order.addOrders("banana",150);
        order.addOrders("orange",10);

        //warehouse
        Warehouse warehouse1 = new Warehouse("FirstWarehouse");
        warehouse1.addOrders("apple",10);
        warehouse1.addOrders("banana", 60);
        Warehouse warehouse2 = new Warehouse("SecondWarehouse");
        warehouse2.addOrders("apple",40);
        warehouse2.addOrders("orange", 4);
        List <Warehouse> warehouses = new ArrayList<>();
        warehouses.add(warehouse2);
        warehouses.add(warehouse2);

        // empty shipments expected
        List<Shipment> expectedShipments = new ArrayList<>();
        List<Shipment> finalShipments = inventoryAllocator.allocateShipment(order, warehouses);

        // expected shipment == finalShipments  == empty list (as they are no enough items in the ware house)
        assertEquals(expectedShipments, finalShipments);
    }


    /**
     * The following test case tests for when there are enough orders, but warehouses are empty
     * Hence, there will be no shipments to make for the orders.
     * Result expected is empty list.
     * */
    @Test
    public void properOrderItemsWithWarehousesEmpty() {
        Order order = new Order();
        order.addOrders("apple",10);  // Orders (not empty)

        List <Warehouse> warehouses = new ArrayList<>(); // empty warehouses
        List<Shipment> expectedShipments = new ArrayList<>();
        List<Shipment> actualShipments = inventoryAllocator.allocateShipment(order, warehouses);

        /* empty list */
        assertEquals(expectedShipments, actualShipments);
    }

    /**
     * The following test case tests for when there are no orders and also warehouses are empty
     * Hence, there is nothing to ship
     * Result expected is empty list.
     * */
    @Test
    public void nonEmptyOrderEmptyWarehouse() {
        Order order = new Order(); // empty order
        List <Warehouse> warehouses = new ArrayList<>(); // empty warehouse
        List<Shipment> expectedShipments = new ArrayList<>(); // empty shipment list
        List<Shipment> actualShipments = inventoryAllocator.allocateShipment(order, warehouses);

        /* empty list */
        assertEquals(expectedShipments, actualShipments);
    }

    /**
     * The following test case tests for when there are no orders, but warehouses are not empty
     * Result expected is empty list.
     * */
    @Test
    public void noneOrdersWithWarehouseNotEmpty() {
        Order order = new Order(); // empty order

        // warehouses (not empty)
        Warehouse warehouse1 = new Warehouse("Warehouse1");
        warehouse1.addOrders("apple", 10);
        warehouse1.addOrders("orange", 30);
        warehouse1.addOrders("banana", 70);
        Warehouse warehouse2 = new Warehouse("Warehouse2");
        warehouse2.addOrders("apple", 10);
        warehouse2.addOrders("orange", 30);
        warehouse2.addOrders("banana", 70);

        List<Warehouse> warehouses = new ArrayList<>();
        warehouses.add(warehouse1);
        warehouses.add(warehouse2);
        List<Shipment> expectedShipments = new ArrayList<>();
        List<Shipment> finalShipments = inventoryAllocator.allocateShipment(order, warehouses);

        /* empty list */
        assertEquals(expectedShipments, finalShipments);
    }


    /**
     * The following test case tests for when there 1 only order and one warehouse with exact match
     * Result expected is a shipment with ordered item.
     * */
    @Test
    public void happyCaseExactInventoryMatch() {
        Order order = new Order(); // one order
        order.addOrders("apple", 1);

        // one warehouse
        Warehouse warehouse1 = new Warehouse("owd");
        warehouse1.addOrders("apple", 1);
        List<Warehouse> warehouses = new ArrayList<>();
        warehouses.add(warehouse1);

        List<Shipment> expectedShipments = new ArrayList<>();
        Shipment shipment = new Shipment("owd");
        shipment.addOrders("apple", 1);
        expectedShipments.add(shipment);
        List<Shipment> finalShipments = inventoryAllocator.allocateShipment(order, warehouses);

        /* exact match */
        assertEquals(expectedShipments, finalShipments);
    }


    /**
     * The following test case tests when there are no enough inventories and hence, no allocations
     * Result expected is a empty shipment list
     * */
    @Test
    public void notEnoughInventoriesNoAllocations() {
        Order order = new Order(); // one order
        order.addOrders("apple",1);

        // one warehouse
        Warehouse warehouse1 = new Warehouse("owd");
        warehouse1.addOrders("apple", 0);
        List<Warehouse> warehouses = new ArrayList<>();
        warehouses.add(warehouse1);

        // shipments expected
        List<Shipment> expectedShipments = new ArrayList<>();
        List<Shipment> finalShipments = inventoryAllocator.allocateShipment(order, warehouses);

        /* empty list : [] */
        assertEquals(expectedShipments, finalShipments);
    }


    /**
     * The following test case tests when we should split an order item across warehouses
     * if that is the only way to completely ship an item.
     *
     * Result expected is shipment list
     * */
    @Test
    public void splittingOrderAcrossMultipleWarehouses() {
        Order order = new Order(); // one order
        order.addOrders("apple", 10);

        // one warehouse
        Warehouse warehouse1 = new Warehouse("owd");
        warehouse1.addOrders("apple", 5);
        Warehouse warehouse2 = new Warehouse("dm");
        warehouse2.addOrders("apple", 5);
        List<Warehouse> warehouses = new ArrayList<>();
        warehouses.add(warehouse1);
        warehouses.add(warehouse2);

        // shipments expected
        List<Shipment> expectedShipments = new ArrayList<>();
        Shipment firstShipmentShipment = new Shipment("owd");
        firstShipmentShipment.addOrders("apple", 5);
        Shipment secondShipment = new Shipment("dm");
        secondShipment.addOrders("apple", 5);
        expectedShipments.add(firstShipmentShipment);
        expectedShipments.add(secondShipment);

        List<Shipment> finalShipments = inventoryAllocator.allocateShipment(order, warehouses);

        // expected shipment == finalShipments
        assertEquals(expectedShipments, finalShipments);
    }
}

