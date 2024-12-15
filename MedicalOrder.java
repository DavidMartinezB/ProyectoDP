
/**
 * Write a description of class MedicalOrder here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MedicalOrder extends Order
{
    //CONSTRUCTOR DE LA SUBCLASE MedicalOrder
    public MedicalOrder (String sendingName, Location location, Location destination, int deliveryTime, 
                 double weight, String destinationName, Urgency urgency)   {
        super(sendingName, location, destination, deliveryTime, 
                weight, destinationName, null ,urgency);
    }
    
    /**
     * @return A null value for the surcharge.
     */
    public int charge ()   {
        return 0;
    }
    
    /**
     * Return an integer number that the delivery person must increase to the order.  
     * @return An integer value.
     */
    public int calculateEvaluationDP ()     {
        return 15;
    }
}
