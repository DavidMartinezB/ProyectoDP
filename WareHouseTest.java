import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Set;
import java.util.Iterator;
import java.util.Map;

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
        this.order1 = new UrgentOrder("Alice", new Location(1, 1), new Location(2, 2), 10, 1.0, "Sender1", Surcharge.MEDIUM, Urgency.EMERGENCY);
        this.order2 = new MedicalOrder("Bob", new Location(3, 3), new Location(4, 4), 5, 2.0, "Sender2", Surcharge.MEDIUM, Urgency.IMPORTANT);
        this.order3 = new NonUrgentOrder("Charlie", new Location(5, 5), new Location(6, 6), 10, 1.5, "Sender3", Surcharge.LOW, Urgency.NONESSENTIAL);
    }

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
        assertEquals(this.order2, iterator.next()); // Highest urgency (EMERGENCY)
        assertEquals(this.order1, iterator.next()); // Second highest urgency (IMPORTANT)
        assertEquals(this.order3, iterator.next()); // Lowest urgency (NONESSENTIAL)
    }

    @Test
    public void testRetrieveFirstOrder() {
        this.wareHouse.addOrder(this.order1);
        this.wareHouse.addOrder(this.order2);

        Order retrievedOrder = this.wareHouse.retrieveFirstOrder();
        System.out.println("First retrieved order: " + retrievedOrder);

        assertEquals(this.order2, retrievedOrder); // Order with highest urgency
        assertEquals(1, this.wareHouse.getOrders().size());

        retrievedOrder = this.wareHouse.retrieveFirstOrder();
        System.out.println("Second retrieved order: " + retrievedOrder);

        assertEquals(this.order1, retrievedOrder); // Next highest urgency
        assertTrue(this.wareHouse.getOrders().isEmpty()); // No more orders
    }

    @Test
    public void testGetLocation() {
        Location location = this.wareHouse.getLocation();
        System.out.println("Warehouse location: " + location);
        assertEquals(5, location.getX());
        assertEquals(5, location.getY());
    }

}