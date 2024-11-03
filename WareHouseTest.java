import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

public class WareHouseTest {
    private WareHouse wareHouse;
    private Order order1;
    private Order order2;
    private Order order3;

    public WareHouseTest() {
    }

    @Before
    public void setUp() {
        this.wareHouse = new WareHouse();
        this.order1 = new Order("Alice", new Location(1, 1), new Location(2, 2), 10, 1.0, "Sender1");
        this.order2 = new Order("Bob", new Location(3, 3), new Location(4, 4), 5, 2.0, "Sender2");
        this.order3 = new Order("Charlie", new Location(5, 5), new Location(6, 6), 10, 1.5, "Sender3");
    }

    @Test
    public void testAddOrder() {
        this.wareHouse.addOrder(this.order1);
        this.wareHouse.addOrder(this.order2);
        this.wareHouse.addOrder(this.order3);

        List<Order> orders = this.wareHouse.getOrders();
        System.out.println("Orders in warehouse after adding:");
        for (Order order : orders) {
            System.out.println(order);
        }

        assertEquals(3, orders.size());
        assertEquals(this.order2, orders.get(0)); // Order with earliest delivery time
        assertEquals(this.order1, orders.get(1)); // Order with same delivery time but earlier sender name
        assertEquals(this.order3, orders.get(2)); // Order with same delivery time but later sender name
    }

    @Test
    public void testRetrieveFirstOrder() {
        this.wareHouse.addOrder(this.order1);
        this.wareHouse.addOrder(this.order2);

        Order retrievedOrder = this.wareHouse.retrieveFirstOrder();
        System.out.println("First retrieved order: " + retrievedOrder);
        System.out.println("Orders in warehouse after first retrieval:");
        for (Order order : this.wareHouse.getOrders()) {
            System.out.println(order);
        }

        assertEquals(this.order2, retrievedOrder); // Order with earliest delivery time
        assertEquals(1, this.wareHouse.getOrders().size());
        assertEquals(this.order1, this.wareHouse.getOrders().get(0)); // Remaining order

        retrievedOrder = this.wareHouse.retrieveFirstOrder();
        System.out.println("Second retrieved order: " + retrievedOrder);
        System.out.println("Orders in warehouse after second retrieval:");
        for (Order order : this.wareHouse.getOrders()) {
            System.out.println(order);
        }

        assertEquals(this.order1, retrievedOrder); // Next order
        assertTrue(this.wareHouse.getOrders().isEmpty()); // No more orders
    }

    @Test
    public void testGetLocation() {
        Location location = this.wareHouse.getLocation();
        System.out.println("Warehouse location: " + location);
        assertEquals(5, location.getX());
        assertEquals(5, location.getY());
    }

    @Test
    public void testGetOrders() {
        System.out.println("Running testGetOrders...");

        // Initially, the warehouse should have no orders
        List<Order> orders = this.wareHouse.getOrders();
        assertTrue(orders.isEmpty());
        System.out.println("Initial orders in warehouse: " + orders.size());

        // Add orders to the warehouse
        this.wareHouse.addOrder(this.order1);
        this.wareHouse.addOrder(this.order2);
        this.wareHouse.addOrder(this.order3);

        // Get the orders from the warehouse
        orders = this.wareHouse.getOrders();
        System.out.println("Orders in warehouse after adding:");
        for (Order order : orders) {
            System.out.println(order);
        }

        // Verify the orders
        assertEquals(3, orders.size());
        assertEquals(this.order2, orders.get(0)); // Order with earliest delivery time
        assertEquals(this.order1, orders.get(1)); // Order with same delivery time but earlier sender name
        assertEquals(this.order3, orders.get(2)); // Order with same delivery time but later sender name

        System.out.println("testGetOrders complete.");
    }
} 