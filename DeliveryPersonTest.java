import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class DeliveryPersonTest.
 *
 * @author  David J. Barnes and Michael Kölling
 * @version 2016.02.29
 * @version 2024.10.07 DP classes 
 */
public class DeliveryPersonTest
{
    private DeliveryPerson dp;
    private Order order;
    private DeliveryPerson dp2;
    private Order order2;
    /**
     * Default constructor for test class DeliveryPersonTest
     */
    public DeliveryPersonTest()
    {
    }

    /**
     * Create a delivery person.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        DeliveryCompany company = new DeliveryCompany("Compañía DP Delivery Cáceres");
        // Starting position for the taxi.
        Location dpLocation = new Location(0, 0);
        // Locations for the order.
        Location pickup = new Location(1, 2);
        Location destination = new Location(5, 6);
         
        order = new Order("Kevin", pickup, destination, 10, 1.2, "Decathon Cáceres");
        dp = new DeliveryPerson(company, dpLocation,"DP1");

        // Starting position
        Location dpLocation2 = new Location(2, 2);
        // Locations for the order.
        Location pickup2 = new Location(1, 3);
        Location destination2 = new Location(4, 2);
         
        order2 = new Order("Gru", pickup2, destination2, 7, 2.3, "Decathon Cáceres");
        dp2 = new DeliveryPerson(company, dpLocation2,"DP2");
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    /**
     * Test creation and the initial state of a delivery person.
     */
    @Test
    public void testCreation()
    {
        assertEquals(true, dp.isFree());
    }

    /**
     * Test that a delivery person is no longer free after this person has
     * picked up an order.
     */
    @Test
    public void testPickup()
    {
        dp.pickup(order);
        assertEquals(false, dp.isFree());

        dp2.pickup(order2);
        assertEquals(false, dp.isFree());
    }

    /**
     * Test that a delivery person becomes free again after delivering
     * an order.
     */
    @Test
    public void testDeliverOrder()
    {
        dp.deliverOrder();
        assertEquals(true, dp.isFree());
        dp2.deliverOrder();
        assertEquals(true, dp.isFree());
    }

    /**
     * Test that a delivery person picks up and delivers an order within
     * a reasonable number of steps.
     */
    @Test
    public void testDelivery()
    {
        dp.setPickupLocation(order.getLocation());
        while (dp.isFree()) { 
            dp.act();                               //Optiene el paquete
        }
        assertEquals(false, dp.isFree());
        while(!dp.isFree()){
            dp.act();                               //Deja el paquete
        }
        assertEquals(true, dp.isFree());

        dp2.setPickupLocation(order2.getLocation());
        while (dp2.isFree()) { 
            dp2.act();                               //Optiene el paquete
        }
        assertEquals(false, dp2.isFree());
        while(!dp2.isFree()){
            dp2.act();                               //Deja el paquete
        }
        assertEquals(true, dp2.isFree());
    }

    @Test
    public void s()
    {
    }
}





