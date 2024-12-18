import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class OrderTest.
 *
 * @author  David J. Barnes and Michael Kölling
 * @version 2016.02.29
 * @version 2024.10.07 DP classes 
 */
public class OrderTest
{
    Order order1;
    Order order2;
    Order order3;
    Location location1;
    Location destination1;
    Location location2;
    Location destination2;
    Location location3;
    Location destination3;
    
    /**
     * Default constructor for test class OrderTest
     */
    public OrderTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        location1 = new Location(3,5);
        destination1 = new Location(6,1);
        location2 = new Location(8,3);
        destination2 = new Location(2,6);
        location3 = new Location(13,5);
        destination3 = new Location(4,10);
        
        order1 = new UrgentOrder("Pablo", location1, destination1, 11, 1.6, "Banana Vintage Cáceres",
                                Surcharge.MEDIUM, Urgency.IMPORTANT);
        order2 = new MedicalOrder("Lucía", location2, destination2, 16, 2.4, "Decathlon Cáceres",
                                Urgency.EMERGENCY);
        order3 = new NonUrgentOrder("Pepe", location3, destination3, 19, 3.6, "Ferretería AlClavo",
                                Surcharge.LOW, Urgency.NONESSENTIAL);
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
     * Test basic creation of an order.
     * Ensure that the location and destination locations
     * have been set.
     */
    @Test
    public void testCreation()
    {
        assertEquals("Pablo", order1.getSendingName());
        assertEquals(location1, order1.getLocation());
        assertEquals(destination1, order1.getDestination());
        assertEquals(11, order1.getDeliveryTime());
        assertEquals(1.6, order1.getWeight(), 0.001);
        assertEquals("Banana Vintage Cáceres", order1.getDestinationName());
        //Misma prueba realizada de otra manera:
        //para location
        assertEquals(order1.getLocation().getX(),3);
        assertEquals(order1.getLocation().getY(),5);
        //para destination
        assertEquals(order1.getDestination().getX(),6);
        assertEquals(order1.getDestination().getY(),1);
    }

    /**
     * Test of the getDeliveryPersonName method.
     * Ensure that this method gets and returns the name of the delivery person correctly.
     */
    @Test
    public void testGetDeliveryPersonName()
    {
        order2.setDeliveryPersonName("Felicia");
        assertEquals("Felicia", order2.getDeliveryPersonName());
        //Misma prueba utilizando otra aserción:
        assertTrue(order2.getDeliveryPersonName() == "Felicia");
    }

    /**
     * Test of the getDestination method.
     * Ensure that this method gets and returns the destination location correctly.
     */
    @Test
    public void testGetDestination ()
    {
        assertEquals(order3.getDestination(),destination3);
        //Misma prueba utilizando otra aserción:
        assertTrue(order3.getDestination() == destination3);
    }
    
    /**
     * Test of the charge method.
     * Ensure that this method gets and returns the surcharge value correctly.
     */
    @Test
    public void getCharge ()
    {
        assertEquals(order1.charge(), 20);
        assertEquals(order2.charge(), 0);
        assertEquals(order3.charge(), 4);
        //Misma prueba utilizando otra aserción:
        assertTrue(order1.charge() == 20);
        assertTrue(order2.charge() == 0);
        assertTrue(order3.charge() == 4);
    }
    
    /**
     * Test of the calculateEvaluationDP method.
     * Ensure that this method gets and returns the new valoration value of the delivery person correctly.
     */
    @Test
    public void testCalculateEvaluationDP ()
    {
        assertEquals(order1.calculateEvaluationDP(), 10);
        assertEquals(order2.calculateEvaluationDP(), 15);
        assertEquals(order3.calculateEvaluationDP(), 5);
        //Misma prueba utilizando otra aserción:
        assertTrue(order1.calculateEvaluationDP() == 10);
        assertTrue(order2.calculateEvaluationDP() == 15);
        assertTrue(order3.calculateEvaluationDP() == 5);
    }
}
