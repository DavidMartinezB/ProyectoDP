import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeliveryCompanyTest {
    private DeliveryCompany company;
    private DeliveryPerson dp1;
    private DeliveryPerson dp2;
    private DeliveryPerson dp3;
    private DeliveryPerson dp4;
    private DeliveryPerson dp5;
    private Order order1;
    private Order order2;
    private Order order3;
    private Order order4;
    private Order order5;

    /**
     * Default constructor for test class DeliveryCompanyTest
     */
    public DeliveryCompanyTest() {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp() {
        company = new DeliveryCompany("Compañía DP Delivery Cáceres");
        dp1 = new SpecialDP(company, new Location(3, 3), "DP1");
        dp2 = new ExpressDP(company, new Location(10, 10), "DP2");
        dp3 = new CommonDP(company, new Location(12, 14), "DP3");
        dp4 = new SpecialDP(company, new Location(5, 5), "DP4");
        dp5 = new ExpressDP(company, new Location(8, 8), "DP5");

        company.addDeliveryPerson(dp1);
        company.addDeliveryPerson(dp2);
        company.addDeliveryPerson(dp3);
        company.addDeliveryPerson(dp4);
        company.addDeliveryPerson(dp5);

        Location whLocation = company.getWareHouse().getLocation();
        order1 = new UrgentOrder("Agnes", whLocation, new Location(2, 6), 10, 1.2, "Decathon Cáceres", Surcharge.MEDIUM,
                Urgency.NONESSENTIAL);
        order2 = new UrgentOrder("Gru", whLocation, new Location(5, 2), 10, 1.5, "Pintores", Surcharge.LOW,
                Urgency.IMPORTANT);
        order3 = new MedicalOrder("Kevin", whLocation, new Location(14, 2), 11, 2.2, "Ruta de la Plata",
                Urgency.EMERGENCY);
        order4 = new NonUrgentOrder("Lucy", whLocation, new Location(7, 1), 10, 1.2, "Cruz de los caídos",
                Surcharge.LOW, Urgency.NONESSENTIAL);
        order5 = new MedicalOrder("Stuart", whLocation, new Location(9, 3), 12, 1.8, "Hospital Cáceres",
                Urgency.EMERGENCY);

        company.addOrder(order1);
        company.addOrder(order2);
        company.addOrder(order3);
        company.addOrder(order4);
        company.addOrder(order5);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown() {
        company = null;
        dp1 = null;
        dp2 = null;
        dp3 = null;
        order1 = null;
        order2 = null;
        order3 = null;
    }

    @Test
    public void testAddDeliveryPerson() {
        assertEquals(5, company.getDeliveryPersons().size());
    }

    @Test
    public void testAddOrder() {
        assertEquals(5, company.getOrders().size());
    }

    @Test
    public void testRequestPickup() {
        assertTrue(company.requestPickup(order1));
        assertTrue(company.requestPickup(order2));
        assertTrue(company.requestPickup(order3));
    }

    @Test
    public void testArrivedAtPickup() {
        Order[] orders = { order1, order2, order3, order4, order5 };
        DeliveryPerson[] deliveryPersons = { dp1, dp2, dp3, dp4, dp5 };

        for (int i = 0; i < orders.length; i++) {
            Order order = orders[i];
            DeliveryPerson dp = deliveryPersons[i];

            company.requestPickup(order);
            Location targetLocation = order.getLocation();
            int distance = dp.distanceToTheTargetLocation();

            // Simula el movimiento del DeliveryPerson hasta que llegue a la ubicación de
            // recogida
            for (int j = 0; j < distance; j++) {
                System.out.println("Distancia : " + distance);
                System.out.println("Ubicación actual: " + dp.getLocation());
                dp.act();
            }

            company.arrivedAtPickup(dp); // Llama al método arrivedAtPickup
            assertEquals(targetLocation, dp.getLocation(),
                    "El DeliveryPerson debería estar en la ubicación de recogida");
        }
    }

    @Test
    public void testArrivedAtDestination() {
        Order[] orders = { order1, order2, order3, order4, order5 };
        DeliveryPerson[] deliveryPersons = { dp1, dp2, dp3, dp4, dp5 };

        for (int i = 0; i < orders.length; i++) {
            Order order = orders[i];
            DeliveryPerson dp = deliveryPersons[i];

            dp.pickup(order);
            dp.setTargetLocation(order.getDestination());
            int distance = dp.distanceToTheTargetLocation();
            System.out.println("Distancia : " + distance);

            // Simula el movimiento del DeliveryPerson hasta que llegue a la ubicación de
            // destino
            for (int j = 0; j < distance + 1; j++) {
                System.out.println("Distancia : " + distance);
                System.out.println("Ubicación actual: " + dp.getLocation());
                dp.act();
            }

            assertEquals(order.getDestination(), dp.getLocation(),
                    "El DeliveryPerson debería estar en la ubicación de destino");
        }
    }
}