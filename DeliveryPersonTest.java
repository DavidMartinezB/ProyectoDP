import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class DeliveryPersonTest.
 * 
 * @version 2024.10.07 DP classes
 */
public class DeliveryPersonTest {
    private DeliveryPerson dp;
    private Order order;
    private DeliveryPerson dp2;
    private Order order2;
    private DeliveryPerson dp3;
    private Order order3;
    private DeliveryCompany company;

    /**
     * Default constructor for test class DeliveryPersonTest
     */
    public DeliveryPersonTest() {
    }

    /**
     * Create a delivery person.
     * 
     * Called before every test case method.
     */
    @Before
    public void setUp() {
        System.out.println("Setting up the test environment...");
        company = new DeliveryCompany("Compañía DP Delivery Cáceres");
        // Location for the order.
        Location pickup = new Location(1, 2);

        order = new MedicalOrder("Kevin", pickup, new Location(14,2),11, 2.2, "Ruta de la Plata",Urgency.EMERGENCY);
        dp = new SpecialDP(company, new Location(3, 3),"DP1");
;
        // Location for the second order.
        Location pickup2 = new Location(1, 3);

        order2 = new NonUrgentOrder("Lucy", pickup2, new Location(2, 6),10, 1.2, "Decathon Cáceres", Surcharge.MEDIUM, Urgency.NONESSENTIAL); 
        dp2 = new CommonDP(company, new Location(12, 14),"DP2");

        // Location for the third order.
        Location pickup3 = new Location(1, 4);

        order3 = new UrgentOrder("Edith", pickup3, new Location(19,19),11, 1.2, "Pintores 19 B", Surcharge.LOW, Urgency.IMPORTANT);
        dp3 = new ExpressDP(company, new Location(10, 10),"DP3");

        // Add orders to the warehouse
        company.addOrder(order);
        company.addOrder(order2);
        company.addOrder(order3);

        System.out.println("Setup complete.");
    }

    /**
     * Tears down the test fixture.
     * 
     * Called after every test case method.
     */
    @After
    public void tearDown() {
        System.out.println("Tearing down the test environment...");
        // Any cleanup code goes here
        System.out.println("Teardown complete.");
    }

    /**
     * Test creation and the initial state of a delivery person.
     */
    @Test
    public void testCreation() {
        System.out.println("Running testCreation...");
        assertEquals(true, dp.isFree());
        assertEquals(true, dp2.isFree());
        assertEquals(true, dp3.isFree());
        System.out.println("testCreation complete.");
    }

    /**
     * Test that a delivery person is no longer free after this person has picked up an order.
     */
    @Test
    public void testPickup() {
        System.out.println("Running testPickup...");
        dp.pickup(order);
        System.out.println("dp picked up order: " + order);
        assertEquals(false, dp.isFree());
        dp2.pickup(order2);
        System.out.println("dp2 picked up order2: " + order2);
        assertEquals(true, dp2.isFree());
        System.out.println("testPickup complete.");
        dp3.pickup(order3);
        System.out.println("dp3 picked up order2: " + order3);
        assertEquals(true, dp3.isFree());
        System.out.println("testPickup complete.");
    }

    /**
     * Test that a delivery person becomes free again after delivering an order.
     */
    @Test
    public void testDeliverOrder() {
        System.out.println("Running testDeliverOrder...");
        dp.pickup(order);
        System.out.println("dp picked up order: " + order);
        dp.deliverOrder();
        System.out.println("dp delivered order: " + order);
        assertEquals(true, dp.isFree());
        dp2.pickup(order2);
        System.out.println("dp2 picked up order2: " + order2);
        dp2.deliverOrder();
        System.out.println("dp2 delivered order2: " + order2);
        assertEquals(true, dp2.isFree());
        System.out.println("testDeliverOrder complete.");
    }

    /**
     * Test that a delivery person picks up and delivers an order within a reasonable number of steps.
     */
    @Test
    public void testDelivery() {
        System.out.println("Running testDelivery...");
        dp.setPickupLocation(order.getLocation());
        System.out.println("dp set pickup location to: " + order.getLocation());
        int dist = dp.distanceToTheTargetLocation();
        for(int i = 0; i <= dist; i++) {
            System.out.println("dp is acting to pick up the order...");
            dp.act(); // Obtiene el paquete
        }
        System.out.println("dp picked up the order.");
        assertEquals(dp.getLocation(), order.getLocation());

        dist = dp.distanceToTheTargetLocation();
        for(int i = 0; i <= dist; i++) {
            System.out.println("dp is acting to deliver the order...");
            dp.act(); // Deja el paquete
        }
        System.out.println("dp delivered the order.");
        assertEquals(0, dp.getOrdersToDeliver().size());

        dp2.setPickupLocation(order2.getLocation());
        System.out.println("dp2 set pickup location to: " + order2.getLocation());
        dist = dp2.distanceToTheTargetLocation();
        for(int i = 0; i <= dist; i++){
            System.out.println("dp2 is acting to pick up the order...");
            dp2.act(); // Obtiene el paquete
        }
        System.out.println("dp2 picked up the order.");
        assertEquals(dp2.getLocation(), order2.getLocation());

        dist = dp2.distanceToTheTargetLocation();
        for(int i = 0; i <= dist; i++) {
            System.out.println("dp2 is acting to deliver the order...");
            dp2.act(); // Deja el paquete
        }
        System.out.println("dp2 delivered the order.");
        assertEquals(0, dp2.getOrdersToDeliver().size());
    }
    
    @Test
    public void testObtainTotalCharge(){
        dp.pickup(order);
        dp2.pickup(order);
        dp2.pickup(order2);
        dp.deliverOrder();
        dp2.deliverOrder();
        dp2.deliverOrder();
        assertEquals(dp.obtainTotalCharge(), 0);
        assertEquals(dp2.obtainTotalCharge(), 10);
    }
    
    @Test
    public void testGetOrder(){
        dp.pickup(order);
        dp2.pickup(order);
        dp2.pickup(order2);
        assertEquals(dp.getFirstOrder(),  order);
        assertEquals(dp2.getFirstOrder(), order);
    }
    
    @Test
    public void testAct(){
        System.out.println("Running testDelivery...");
        dp.setPickupLocation(order.getLocation());
        System.out.println("dp set pickup location to: " + order.getLocation());
        int dist = dp.distanceToTheTargetLocation();
        for(int i = 0; i <= dist; i++) {
            System.out.println("dp is acting to pick up the order...");
            dp.act(); // Obtiene el paquete
        }
        System.out.println("dp picked up the order.");
        assertEquals(dp.getLocation(), order.getLocation());

        dist = dp.distanceToTheTargetLocation();
        for(int i = 0; i <= dist; i++) {
            System.out.println("dp is acting to deliver the order...");
            dp.act(); // Deja el paquete
        }
        System.out.println("dp delivered the order.");
        assertEquals(0, dp.getOrdersToDeliver().size());

        dp2.setPickupLocation(order2.getLocation());
        System.out.println("dp2 set pickup location to: " + order2.getLocation());
        dist = dp2.distanceToTheTargetLocation();
        for(int i = 0; i <= dist; i++){
            System.out.println("dp2 is acting to pick up the order...");
            dp2.act(); // Obtiene el paquete
        }
        System.out.println("dp2 picked up the order.");
        assertEquals(dp2.getLocation(), order2.getLocation());

        dist = dp2.distanceToTheTargetLocation();
        for(int i = 0; i <= dist; i++) {
            System.out.println("dp2 is acting to deliver the order...");
            dp2.act(); // Deja el paquete
        }
        System.out.println("dp2 delivered the order.");
        assertEquals(0, dp2.getOrdersToDeliver().size());

        dp3.setPickupLocation(order3.getLocation());
        System.out.println("dp3 set pickup location to: " + order3.getLocation());
        dist = dp3.distanceToTheTargetLocation();
        for(int i = 0; i <= dist; i++){
            System.out.println("dp3 is acting to pick up the order...");
            dp3.act(); // Obtiene el paquete
        }
        System.out.println("dp3 picked up the order.");
        assertEquals(dp3.getLocation(), order3.getLocation());

        dist = dp3.distanceToTheTargetLocation();
        for(int i = 0; i <= dist; i++) {
            System.out.println("dp3 is acting to deliver the order...");
            dp3.act(); // Deja el paquete
        }
        System.out.println("dp3 delivered the order.");
        assertEquals(0, dp3.getOrdersToDeliver().size());
    }
}