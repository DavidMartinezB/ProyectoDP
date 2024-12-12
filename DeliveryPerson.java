import java.util.*;
/**
 * Model the common elements of delivery persons.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 * @version 2024.10.07 DP classes 
 */
public abstract class DeliveryPerson 
{
    // The Delivery Company of this DeliveryPerson.
    private DeliveryCompany company;
    // Where the person is.
    private Location location;
    // Where the person is headed.
    private  Location targetLocation;
    // Record how often the person has nothing to do.
    private int idleCount; 
    //name of the delivery person
    private String name;
    //number of orders distributed
    private int ordersDelivered;
    //orders to be delivered
    private Set<Order> ordersToDeliver;
    //valuation
    private int valuation;
    //maximum number of orders that can be transported
    private int maxLoad;
    //total accumulated amount that has to be collected
    private int totalCharged;

    /**
     * Constructor of class DeliveryPerson
     * @param company The delivery person's company. Must not be null.
     * @param location The delivery person's starting point. Must not be null.
     * @throws NullPointerException If company or location is null.
     */
    public DeliveryPerson(DeliveryCompany company, Location location, String name, int maxLoad)
    {
        if(company == null) {
            throw new NullPointerException("company");
        }
        if(location == null) {
            throw new NullPointerException("location");
        }
        this.company = company;
        this.location = location;
        this.name = name;
        this.targetLocation = null;
        this.idleCount = 0;
        this.ordersDelivered= 0;
        this.ordersToDeliver = new TreeSet<>(new ComparadorUrgenciaOrder());
        this.valuation = 0;
        this.maxLoad = maxLoad;
        this.totalCharged = 0;
    }

    /**
     * @return the name of the delivery person
     */
    public String getName()
    {
        return name;
    }

    /**
     * Change the name of the DelievryPerson.
     * @param newName The new name of the DeliveryPerson.
     */
    public void setName(String newName){
        name = newName;
    }

    /**
     * Get the location.
     * @return Where this delivery person is currently located.
     */
    public Location getLocation()
    {
        return location;
    }

    /**
     * Set the current location.
     * @param location Where it is. Must not be null.
     * @throws NullPointerException If location is null.
     */
    public void setLocation(Location location)
    {
        if(location != null) {
            this.location = location;
        }
        else {
            throw new NullPointerException();
        }
    }

    /**
     * Get the target location.
     * @return Where this delivery person is currently headed, or null
     *         if it is idle.
     */
    public Location getTargetLocation()
    {
        return targetLocation;
    }

    /**
     * Set the required target location.
     * @param location Where to go. Must not be null.
     * @throws NullPointerException If location is null.
     */
    public void setTargetLocation(Location location)
    {
        if(location != null) {
            targetLocation = location;
        }
        else {
            throw new NullPointerException();
        }
    }

    /**
     * Receive a pickup location. This becomes the
     * target location.
     * @param location The pickup location.
     */
    public void setPickupLocation(Location location)
    {
        setTargetLocation(location);
    }

    /**
     * @return on how many steps this delivery person has been idle.
     */
    public int getIdleCount()
    {
        return idleCount;
    }

    public Order getFirstOrder(){
        return (Order)((TreeSet)ordersToDeliver).first();
    }

    public Set<Order> getOrdersToDeliver(){
        return ordersToDeliver;
    }

    public int getMaxLoad(){
        return maxLoad;
    }

    public int getValuation(){
        return valuation;
    }

    public void setValuation(int newValuation){
        valuation = newValuation;
    }


    
    /**
     * Change the value of IdleCount.
     * @param newIdleCount The new IdleCount.
     */
    public void setIdleCount(int newIdleCount){
        idleCount = newIdleCount;
    }

    /**
     * @return how many orders this delivery person has delivered.
     */
    public int ordersDelivered()
    {
        return ordersDelivered;
    }

    /**
     * Change the value of ordersDelivered.
     * @param i The new number of orders delivered.
     */
    public void setOrdersDelivered(int i){
        ordersDelivered = i;
    }

    /**
     * Receive an order.
     * Set order's destination as its target location.
     * @param order The order.
     */
    public void pickup(Order order)
    {
        if(ordersToDeliver.size() < maxLoad){
            setTargetLocation(order.getDestination());
            ((TreeSet)ordersToDeliver).addFirst(order);
        }
        else{
            throw new IllegalArgumentException("El DeliveryPerson no puede llevar mas Orders.");
        }
    }   

    /**
     * @return the company the Delivery Person works for.
     */
    public DeliveryCompany getDeliveryCompany(){
        return  company;
    }

    /**
     * Changes the company where the Delivery Person works.
     * @param newCompany The new company.
     */
    public void setDeliveryCompany(DeliveryCompany newCompany){
        company = newCompany;
    }

    /**
     * Has the delivery person a target Location?
     * @return Whether or not this delivery person has a target Location.
     */
    public boolean hasTargetLocation(){
        return getTargetLocation() != null;
    }

    /**
     * Clear the target location.
     */
    public void clearTargetLocation()
    {
        targetLocation = null;
    }

    /**
     * Increment the number of steps on which this delivery person
     * has been idle.
     */
    public void incrementIdleCount()
    {
        idleCount++;
    }

    public int obtainTotalCharge(){
        return totalCharged;
    }

    public void incTotalCharged(int charge){
        totalCharged = totalCharged + charge;
    }

    public void updateValuation(int newValuation){
        valuation = valuation + newValuation;
    }

    /**
     * Return details of the delivery person, such as where he/she is.
     * @return A string representation of the delivery person.
     */
    public String toString()
    {
        return getClass().getName() + " " +getName()+" at " + getLocation();
    }

    /**
     * Is the delivery person free?
     * @return Whether or not this delivery person is free.
     */
    public boolean isFree()
    {
        boolean free;
        if(ordersToDeliver.isEmpty()){
            free = true;
        }
        else{
            free = false;
        }
        return free;
    }

    /**
     * Notify the company of our arrival at a pickup location.
     */
    public void notifyPickupArrival()
    {
        company.arrivedAtPickup(this);
    }

    /**
     * Notify the company of our arrival at an order's destination.
     */
    public void notifyOrderArrival(Order order)
    {
        company.arrivedAtDestination(this, order);
    }

    /**
     * Deliver the order.
     */
    public void deliverOrder()
    {
        notifyOrderArrival(getFirstOrder());
        clearTargetLocation();
        incTotalCharged(getFirstOrder().charge());
        updateValuation(getFirstOrder().calculateEvaluationDP());
        ((TreeSet)ordersToDeliver).pollFirst();
        if(!ordersToDeliver.isEmpty()){
            setTargetLocation(getFirstOrder().getDestination());
        }
    }

    /**
     * Increment the number of orders this delivery person has delivered.
     */
    protected void incrementOrdersDelivered()
    {
        ordersDelivered++;
    }

    /**
     * Get the distance to the target location from the current location.
     * @return distance to target location.
     */
    public int distanceToTheTargetLocation()
    {
        return location.distance(targetLocation);
    }

    /**
     * Carry out a delivery person's actions.
     */
    public void act()
    {
        if(!hasTargetLocation()){
            incrementIdleCount();
        }
        else{
            location = location.nextLocation(targetLocation);
            if (this.isFree() && this.getLocation().equals(this.getTargetLocation())) {
                notifyPickupArrival();
            }
            else{
                if(!this.isFree() && this.getLocation().equals(this.getTargetLocation())){
                    deliverOrder();
                    incrementOrdersDelivered();
                }
            }
        }
    }

    public String showInitialInfo(){
        return toString();
    }
 
    /**
     * Return details of the delivery person, such as the name, the location,
     * number of delivered orders and time (steps) without moving.
     * @return A string representation of the delivery person.
     */
    public String showFinalInfo()
    {
        return toString() + " - orders delivered: " + ordersDelivered() + " - non active for: " + getIdleCount() +" times";
    }
}
