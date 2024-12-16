
/**
 * Write a description of class NonUrgentOrder here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class NonUrgentOrder extends Order
{
    //CONSTRUCTOR DE LA SUBCLASE NonUrgentOrder
    public NonUrgentOrder (String sendingName, Location location, Location destination, int deliveryTime, 
                 double weight, String destinationName, Surcharge surcharge, Urgency urgency)   {
        super(sendingName, location, destination, deliveryTime, 
                weight, destinationName, surcharge, urgency);
    }
    
    /**
     * @return The surcharge's value of the order.
     */
    @Override
    public int charge ()   {
        return getSurcharge().getValue();
    }
    
    /**
     * Return an integer number that the delivery person must increase to the order.  
     * @return An integer value.
     */
    @Override
    public int calculateEvaluationDP ()     {
        return 5;
    }

    @Override
    public String showInitialInfo(){
        return super.showInitialInfo() + " <charge: " + getSurcharge().getName() + " (value: " + getSurcharge().getValue() + ")>";
    }
}
