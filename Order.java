/**
 * Model an order to be delivered from one
 * location to another.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 * @version 2024.10.07 DP classes 
 */
public abstract class Order  {
    private String sendingName;
    private Location location;
    private Location destination;
    private int deliveryTime;
    private double weight;
    private String destinationName;
    private String deliveryPersonName;
    private Surcharge surcharge;
    private Urgency urgency;
    
    /**
     * Constructor for objects of class Order
     * @param sendingName The sender's name.
     * @param location The pickup location, must not be null.
     * @param destination The destination location, must not be null.
     * @param deliveryTime The hour of delivery.
     * @param weight  The order's weight
     * @param destinationName The name of the person receiving the order.
     * @param surcharge The surcharge of the order`s delivery.
     * @param The urgency's level of the order.
     * @throws NullPointerException If either location is null.
     */
    public Order(String sendingName, Location location, Location destination, int deliveryTime, 
                 double weight, String destinationName, Surcharge surcharge, Urgency urgency)      {
        
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
        this.surcharge = surcharge;
        this.urgency = urgency;
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

    //GETTERS
    /**
     * @return The sender's name.
     */
    public String getSendingName(){
        return sendingName;
    }
    
    /**
     * @return The location of the order.
     */
    public Location getLocation()   {
        return location;
    }
    
    /**
     * @return The destination location.
     */
    public Location getDestination()
    {
        return destination;
    }
    
    /**
     * @return The delivery time.
     */
    public int getDeliveryTime(){
        return deliveryTime;
    }
    
    /**
     * @return The weight of the order.
     */
    public double getWeight()   {
        return weight;
    }
    
    /**
    * @return The destination name of the order.
    */
    public String getDestinationName ()    {
        return destinationName;
    }
    
    /**
     * @return The name of the delivery person.
     */
    public String getDeliveryPersonName()
    {
        return deliveryPersonName;
    }

    /**
     * @return The surcharge of the order's delivery.
     */
    public Surcharge getSurcharge()
    {
        return surcharge;
    }
    
    /**
     * @return The urgency's level of the order.
     */
    public Urgency getUrgency()
    {
        return urgency;
    }
    
    
    //SETTERS
    /**
     * Set the new name of the sender's name.
     * @param The new name of the sender's name.
     */
    public void setSendingName(String sendingName)    {
        this.sendingName = sendingName;
    }
    
    /**
     * Set the new location for the order.
     * @param The new location of the order.
     */
    public void setLocation(Location location)   {
        if(location != null) {
            this.location = location;
        }
        else {
            throw new NullPointerException();
        }
    }
    
    /**
     * Set the new destination for the order.
     * @param The new destination of the order.
     */
    public void setDestination(Location destination)   {
        if(location != null) {
            this.destination = destination;
        }
        else {
            throw new NullPointerException();
        }
    }
    
    /**
     * Set the new delivery time of the order.
     * @param The new delivery time of the order.
     */
    public void setDeliveryTime(int deliveryTime)   {
        this.deliveryTime = deliveryTime;
    }
    
    /**
     * Set the new weight of the order.
     * @param The new weight of the order.
     */
    public void setWeight(double weight)     {
        this.weight = weight;
    }
    
    /**
     * Set the new name of the destination.
     * @param The new name of the destination.
     */
    public void setDestinationName(String destinationName)    {
        this.destinationName = destinationName;
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
     * Set the new surcharge of the order.
     * @param The new surcharge of the order.
     */
    public void setSurcharge(Surcharge surcharge)
    {
        this.surcharge = surcharge;
    }
    
    /**
     * Set the new urgency's level of the order.
     * @param The new urgency's level of the order.
     */
    public void setUrgency(Urgency urgency)
    {
        this.urgency = urgency;
    }
    
    
    /**
     * Devuelve los detalles del pedido que está por entregarse.
     * @return Una cadena de tipo String con la info del pedido.
     */
    public String showInitialInfo()     {
        return getClass().getName() + " from: " + sendingName + " to: " + destinationName + " at: " +
               deliveryTime + " weight: " + weight + " from: " + location.getX() + " - " + location.getY() + 
               " to: " + destination.getX() + " - " + destination.getY() + " <urgency: " + urgency.getName() + " (value: " + urgency.getValue() + ")>";
    }
    
    /**
     * Return details of the passenger, such as where it is.
     * @return A string representation of the passenger.
     */
    public String toString()
    {
        return "Order at: " + deliveryTime + " from: " +
        sendingName + " to: " + destinationName + " (charge: " + charge() + ")";
    }

    /**
     * Show the final information about the order, including the sender's name, the 
     * destination and name of the deliveryPerson who delivers it.
     */
    public String showFinalInfo()
    {
        return getClass().getName() + " delivered at: " + deliveryTime + " by: " + deliveryPersonName + 
               " to: " + destinationName + " from: " + sendingName;
    }

    //METODO ABSTRACTO
    public abstract int charge();
    
    //METODO ABSTRACTO
    public abstract int calculateEvaluationDP();
    
}