import java.util.*; 

/**
 * Compare delivery persons by name in ascending order.
 * 
 * @author DP Clasess
 * @version 2024.10.07 DP classes
 */
public class ComparadorDeliveryPerson implements Comparator<DeliveryPerson>
{
    public int compare(DeliveryPerson dp1, DeliveryPerson dp2){  
        int dis1 = dp1.getLocation().distance(dp1.getTargetLocation());
        int dis2 = dp2.getLocation().distance(dp2.getTargetLocation());
        int distanceCompare = Integer.compare(dis1,dis2);
        
        if (distanceCompare != 0)   {
            return distanceCompare;
        }
        
        return (dp1.getName().compareTo(dp2.getName()));
    }
}

