import java.util.*; 

/**
 * Compare delivery persons by name in ascending order.
 * 
 * @author DP Clasess
 * @version 2024.10.07 DP classes
 */
public class ComparadorNombreDeliveryPerson implements Comparator<DeliveryPerson>
{
    public int compare(DeliveryPerson dp1, DeliveryPerson dp2){  
        return (dp1.getName().compareTo(dp2.getName()));
    } 
}
