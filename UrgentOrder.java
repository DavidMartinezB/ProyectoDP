
/**
 * Write a description of class UrgentOrder here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class UrgentOrder extends Order
{
    //CONSTRUCTOR DE LA SUBCLASE UrgentOrder
    public UrgentOrder (String sendingName, Location location, Location destination, int deliveryTime, 
                 double weight, String destinationName, Surcharge surcharge)   {
        super(sendingName, location, destination, deliveryTime, 
                weight, destinationName, surcharge, Urgency.IMPORTANT);
    }
    
    /**
     * @return Double the surcharge's value of the order.
     */
    public int charge ()   {
        return getSurcharge().getValue() * 2;
    }
    
    /**
     * Return an integer number that the delivery person must increase to the order.  
     * @return An integer value.
     */
    public int calculateEvaluationDP ()     {
        return 10;
    }
}
