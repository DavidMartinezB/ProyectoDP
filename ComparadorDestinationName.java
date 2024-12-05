import java.util.*; 

/**
 * Write a description of class ComparadorDestinationName here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ComparadorDestinationName implements Comparator<Order>{
    public int compare(Order o1, Order o2){
        return o1.getDestinationName().compareTo(o2.getDestinationName());
    }
}
