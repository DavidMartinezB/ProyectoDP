/**
 * Model an order to be delivered from one
 * location to another.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 * @version 2024.10.07 DP classes 
 */
public class Order
{
    private String sendingName;
    private Location location;
    private Location destination;
    private int deliveryTime;
    private double weight;
    private String destinationName;
    private String deliveryPersonName;
    
    /**
     * Constructor for objects of class Order
     * @param sendingName The sender's name.
     * @param location The pickup location, must not be null.
     * @param destination The destination location, must not be null.
     * @param deliveryTime The hour of delivery.
     * @param weight  The order's weight
     * @param destinationName The name of the person receiving the order.
     * @throws NullPointerException If either location is null.
     */
    public Order(String sendingName, Location location, Location destination, int deliveryTime, 
                 double weight, String destinationName)      {
        
        if(location == null) {
            throw new NullPointerException("Location location");
        }
        
        if(destination == null) {
            throw new NullPointerException("Destination location");
        }
        
        this.sendingName = sendingName;
        this.location = location;
        this.destination = destination;
        this.deliveryTime = deliveryTime;
        this.weight = weight;
        this.destinationName = destinationName;
        deliveryPersonName = "";
    }
    
    /**
     * Default constructor of class Order
     */
    public Order()  {
        
        sendingName = "";
        location = null;
        destination = null;
        deliveryTime = 0;
        weight = 0.0;
        destinationName = "";
        deliveryPersonName = "";
    }

    /**
     * @return The name of the delivery person.
     */
    public String getDeliveryPersonName()
    {
        return deliveryPersonName;
    }

    /**
     * @return The sender's name.
     */
    public String getSendingName(){
        return sendingName;
    }

    /**
     * @return The delivery time.
     * 
     */
    public int getDeliveryTime(){
        return deliveryTime;
    }

    /**
     * Set the new name of the delivery person.
     * @param The new name of the delivery person.
     */
    public void setDeliveryPersonName(String deliveryPersonName)
    {
        this.deliveryPersonName = deliveryPersonName;
    }

    /**
     * @return The location of the order.
     */
    public Location getLocation()   {
        return location;
    }
    
    /**
     * Set the new location for the order.
     * @param The new location of the order.
     */
    public void setLocation(Location location)   {
        this.location = location;
    }
    
    /**
     * @return The destination location.
     */
    public Location getDestination()
    {
        return destination;
    }

    /**
    * @return The destination name of the order.
    */
    public String getDestinationName ()    {
        return destinationName;
    }
    
    /**
     * Devuelve los detalles del pedido que está por entregarse.
     * @return Una cadena de tipo String con la info del pedido.
     */
    public String showFirstInfo ()     {
        return "from: " + sendingName + " to: " + destinationName + " at: " +
               deliveryTime + " weight: " + weight;
    }
    
    /**
     * Return details of the passenger, such as where it is.
     * @return A string representation of the passenger.
     */
    public String toString()
    {
        return "Order at: " + deliveryTime + " from: " +
        sendingName + " to: " + destinationName;
    }

    /**
     * Show the final information about the order, including the sender's name, the 
     * destination and name of the deliveryPerson who delivers it.
     */
    public String showFinalInfo()
    {
        return " Order delivered at: " + deliveryTime + " by: " + deliveryPersonName + 
               " to: " + destinationName + " from: " + sendingName;
    }

}
