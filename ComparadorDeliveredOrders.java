import java.util.Comparator;

/**
 * Write a description of class ComparadorDeliverdedOrders here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ComparadorDeliveredOrders implements Comparator<Order>
{
    @Override
    public int compare(Order o1, Order o2) {
        int senderComparison = o1.getSendingName().compareTo(o2.getSendingName());
        if (senderComparison != 0) {
            return senderComparison;
        }
        return Integer.compare(o1.getDeliveryTime(), o2.getDeliveryTime());
    }
}
