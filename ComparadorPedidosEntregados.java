import java.util.*; 
/**
 * Write a description of class ComparadorPedidosEntregados here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ComparadorPedidosEntregados implements Comparator<DeliveryPerson>
{
    public int compare(DeliveryPerson dp1, DeliveryPerson dp2) {
        if(dp1.ordersDelivered() == dp2.ordersDelivered()){
            return new ComparadorNombreDeliveryPerson().compare(dp1, dp2);
        }
        else{
            if(dp1.ordersDelivered() > dp2.ordersDelivered()){
                return 1;
            }
            else{
                return -1;
            }
        }
    }
}
