import java.util.*; 

/**
 * Compare delivery persons by name in ascending order.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class ComparadorUrgenciaOrder implements Comparator<Order>{
    public int compare(Order o1, Order o2){  
        if((o1.getUrgency().compareTo(o2.getUrgency())) == 0){
            return new ComparadorHoraEntregaOrder().compare(o1,o2);
        }
        else{
            if((o1.getUrgency().compareTo(o2.getUrgency())) > 0){
                return 1;
            }   
            else{
                return -1;
            }
        }
    } 
}