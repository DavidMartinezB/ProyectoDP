import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Set;
import java.util.Iterator;
import java.util.Map;

/**
 * The test class WareHouseTest.
 * 
 * @version 2024.10.07 DP classes
 */
public class WareHouseTest {
    private WareHouse wareHouse;
    private Order order1;
    private Order order2;
    private Order order3;
    private DeliveryPerson dp1;


    /**
     * Default constructor for test class WareHouseTest
     */
    public WareHouseTest() {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp() {
        this.wareHouse = new WareHouse();
        this.order1 = new UrgentOrder("Alice", new Location(1, 1), new Location(2, 2), 10, 1.0, "Sender1", Surcharge.MEDIUM, Urgency.EMERGENCY);
        this.order2 = new MedicalOrder("Bob", new Location(3, 3), new Location(4, 4), 5, 2.0, "Sender2", Urgency.IMPORTANT);
        this.order3 = new NonUrgentOrder("Charlie", new Location(5, 5), new Location(6, 6), 10, 1.5, "Sender3", Surcharge.LOW, Urgency.NONESSENTIAL);
        this.dp1 = new CommonDP(new DeliveryCompany("Company1"), new Location(3, 3), "DP1");
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown() {
        System.out.println("Tearing down the test environment...");
        this.wareHouse = null;
        this.order1 = null;
        this.order2 = null;
        this.order3 = null;
        this.dp1 = null;
        System.out.println("Teardown complete.");
    }

    /**
     * Test adding orders to the warehouse.
     */
    @Test
    public void testAddOrder() {
        this.wareHouse.addOrder(this.order1);
        this.wareHouse.addOrder(this.order2);
        this.wareHouse.addOrder(this.order3);

        Set<Order> orders = this.wareHouse.getOrders();
        Iterator<Order> iterator = orders.iterator();

        System.out.println("Orders in warehouse after adding:");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        assertEquals(3, orders.size());

        // Check the order based on sorting logic (urgency > delivery time > destination name)
        iterator = orders.iterator();
        assertEquals(this.order1, iterator.next()); // Highest urgency (EMERGENCY)
        assertEquals(this.order2, iterator.next()); // Second highest urgency (IMPORTANT)
        assertEquals(this.order3, iterator.next()); // Lowest urgency (NONESSENTIAL)
    }

    @Test
    public void testAddDeliveredOrder() {
        this.wareHouse.addDeliveredOrder(this.order1, this.dp1);

        Map<Order, DeliveryPerson> deliveredOrders = this.wareHouse.getDeliveredOrders();
        assertEquals(1, deliveredOrders.size());
        assertTrue(deliveredOrders.containsKey(this.order1));
        assertEquals(this.dp1, deliveredOrders.get(this.order1));
    }

    /**
     * Test getting delivered orders from the warehouse.
     */
    @Test
    public void testGetDeliveredOrders() {
        this.wareHouse.addDeliveredOrder(this.order1, this.dp1);

        Map<Order, DeliveryPerson> deliveredOrders = this.wareHouse.getDeliveredOrders();
        assertNotNull(deliveredOrders);
        assertEquals(1, deliveredOrders.size());
        assertTrue(deliveredOrders.containsKey(this.order1));
        assertEquals(this.dp1, deliveredOrders.get(this.order1));
    }

    /**
     * Test retrieving the first order from the warehouse.
     */
    @Test
    public void testRetrieveFirstOrder() {
        this.wareHouse.addOrder(this.order1);
        this.wareHouse.addOrder(this.order2);

        Order retrievedOrder = this.wareHouse.retrieveFirstOrder();
        System.out.println("First retrieved order: " + retrievedOrder);

        assertEquals(this.order1, retrievedOrder); // Order with highest urgency
        assertEquals(1, this.wareHouse.getOrders().size());

        retrievedOrder = this.wareHouse.retrieveFirstOrder();
        System.out.println("Second retrieved order: " + retrievedOrder);

        assertEquals(this.order2, retrievedOrder); // Next highest urgency
        assertTrue(this.wareHouse.getOrders().isEmpty()); // No more orders
    }

    /**
     * Test getting the location of the warehouse.
     */
    @Test
    public void testGetLocation() {
        Location location = this.wareHouse.getLocation();
        System.out.println("Warehouse location: " + location);
        assertEquals(5, location.getX());
        assertEquals(5, location.getY());
    }

    /**
     * Test getting all orders from the warehouse.
     */
    @Test
    public void testGetAllOrders() {
        this.wareHouse.addOrder(this.order1);
        this.wareHouse.addOrder(this.order2);
        this.wareHouse.addOrder(this.order3);

        Set<Order> orders = this.wareHouse.getOrders();
        assertEquals(3, orders.size());
        assertTrue(orders.contains(this.order1));
        assertTrue(orders.contains(this.order2));
        assertTrue(orders.contains(this.order3));
    }

    /**
     * Test handling duplicate orders.
     */
    @Test
    public void testHandleDuplicateOrders() {
        this.wareHouse.addOrder(this.order1);
        this.wareHouse.addOrder(this.order1); // Add the same order again

        Set<Order> orders = this.wareHouse.getOrders();
        assertEquals(1, orders.size()); // Should still be 1 if duplicates are not allowed
    }

    /**
     * Test behavior with an empty warehouse.
     */
    @Test
    public void testEmptyWarehouse() {
        assertTrue(this.wareHouse.getOrders().isEmpty());

        Order retrievedOrder = this.wareHouse.retrieveFirstOrder();
        assertNull(retrievedOrder); // Should return null if no orders are present
    }
}