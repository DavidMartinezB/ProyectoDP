ximport static org.junit.Assert.*;
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
    Order order;
    Location location;
    Location destination;
    
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
        location = new Location(3,5);
        destination = new Location(6,1);
        order = new Order("Pablo", location, destination, 11, 1.6, "Banana Vintage Cáceres");
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
        assertEquals("Pablo", order.getSendingName());
        assertEquals(location, order.getLocation());
        assertEquals(destination, order.getDestination());
        assertEquals(11, order.getDeliveryTime());
        assertEquals(1.6, order.getWeight(), 0.001);
        assertEquals("Banana Vintage Cáceres", order.getDestinationName());
        //Misma prueba realizada de otra manera:
        //para location
        assertEquals(order.getLocation().getX(),3);
        assertEquals(order.getLocation().getY(),5);
        //para destination
        assertEquals(order.getDestination().getX(),6);
        assertEquals(order.getDestination().getY(),1);
    }

    /**
     * Test of the getDeliveryPersonName method.
     * Ensure that this method gets and returns the name of the delivery person correctly.
     */
    @Test
    public void testGetDeliveryPersonName()
    {
        order.setDeliveryPersonName("Felicia");
        assertEquals("Felicia", order.getDeliveryPersonName());
        //Misma prueba utilizando otra aserción:
        assertTrue(order.getDeliveryPersonName() == "Felicia");
    }

    /**
     * Test of the getDestination method.
     * Ensure that this method gets and returns the destination location correctly.
     */
    @Test
    public void testGetDestination ()
    {
        assertEquals(order.getDestination(),destination);
        //Misma prueba utilizando otra aserción:
        assertTrue(order.getDestination() == destination);
    }
}
